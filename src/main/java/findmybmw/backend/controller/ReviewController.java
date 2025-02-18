package findmybmw.backend.controller;

import findmybmw.backend.dto.ModelReviewStats;
import findmybmw.backend.model.Media;
import findmybmw.backend.model.Reviews;
import findmybmw.backend.service.MediaService;
import findmybmw.backend.service.ReviewService;
import findmybmw.backend.service.UserService;
import findmybmw.backend.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:3000") // Ensure CORS is enabled for this controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private static final String UPLOAD_DIR = "./uploads/";

    @GetMapping
    public List<Reviews> getAllReviews() {
        return reviewService.getAllReviews();
    }



    @GetMapping("/{postId}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable Integer postId) {
        Reviews review = reviewService.getReviewById(postId).orElseThrow(() -> new RuntimeException("Review not found"));
        List<Media> mediaList = mediaService.getMediaByPostId(postId);
        String username = userService.getUsernameById(review.getUserId());
        ReviewResponse response = new ReviewResponse(review, mediaList, username);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/model/{modelId}")
    public List<ReviewListResponse> getReviewsByModelId(@PathVariable Integer modelId) {
        List<Reviews> reviews = reviewService.getReviewsByModelId(modelId);
        return reviews.stream().map(review -> {
            String username = userService.getUsernameById(review.getUserId());
            return new ReviewListResponse(review, username); // Use the new response class
        }).collect(Collectors.toList());
    }

    @GetMapping("/{postId}/media")
    public ResponseEntity<List<Media>> getMediaByPostId(@PathVariable Integer postId) {
        List<Media> mediaList = mediaService.getMediaByPostId(postId);
        return ResponseEntity.ok(mediaList);
    }

    @PutMapping("/update/{modelId}/{postId}")
    public ResponseEntity<Reviews> updateReview(
            @PathVariable Integer modelId,
            @PathVariable Integer postId,
            @RequestHeader("Authorization") String token,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("rating") Double rating,
            @RequestParam("maintenanceRating") Double maintenanceRating,
            @RequestParam("performanceRating") Double performanceRating,
            @RequestParam("comfortRating") Double comfortRating,
            @RequestParam("safetyRating") Double safetyRating,
            @RequestParam("valueRating") Double valueRating,
            @RequestParam(value = "photos", required = false) MultipartFile[] photos) throws IOException {

        try {
            // Get user ID from token
            String username = jwtTokenUtil.extractUsername(token.substring(7));
            Integer userId = userService.getUserIdByUsername(username);

            // Get the existing review with all its data
            Reviews existingReview = reviewService.getReviewById(postId)
                    .orElseThrow(() -> new RuntimeException("Review not found"));

            // Verify ownership
            if (!existingReview.getUserId().equals(userId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            // Update fields while preserving existing data
            existingReview.setTitle(title);
            existingReview.setContent(content);
            existingReview.setRating(rating);
            existingReview.setMaintenanceRating(maintenanceRating);
            existingReview.setPerformanceRating(performanceRating);
            existingReview.setComfortRating(comfortRating);
            existingReview.setSafetyRating(safetyRating);
            existingReview.setValueRating(valueRating);

            // Only update the updatedAt timestamp, preserve other dates
            existingReview.setUpdatedAt(new Date());
            // Keep the original dates
            // No need to set createdAt as it should remain unchanged
            // Keep the original publishedAt date

            // Save the updated review
            Reviews updatedReview = reviewService.updateReview(postId, existingReview);
            // Handle photo uploads if any
            if (photos != null && photos.length > 0) {
                for (MultipartFile photo : photos) {
                    if (!photo.isEmpty()) {
                        String fileName = photo.getOriginalFilename();
                        Path filePath = Paths.get(UPLOAD_DIR, fileName);
                        Files.createDirectories(filePath.getParent());
                        Files.write(filePath, photo.getBytes());

                        Media media = new Media();
                        media.setUserId(userId);
                        media.setPostId(postId);
                        media.setFileName(fileName);
                        media.setFileType(photo.getContentType());
                        media.setFilePath(filePath.toString());
                        mediaService.saveMedia(media);
                    }
                }
            }

            return ResponseEntity.ok(updatedReview);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/delete/{modelId}/{postId}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable Integer postId,
            @RequestHeader("Authorization") String token) {
        String username = jwtTokenUtil.extractUsername(token.substring(7));
        Integer userId = userService.getUserIdByUsername(username);

        Reviews review = reviewService.getReviewById(postId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (!review.getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        reviewService.deleteReview(postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/model/{modelId}/stats")
    public ResponseEntity<ModelReviewStats> getModelReviewStats(@PathVariable Integer modelId) {
        ModelReviewStats stats = reviewService.getModelReviewStats(modelId);
        return ResponseEntity.ok(stats);
    }

    public static class ReviewListResponse {
        private Reviews review;
        private String username;

        public ReviewListResponse(Reviews review, String username) {
            this.review = review;
            this.username = username;
        }

        public Reviews getReview() {
            return review;
        }

        public String getUsername() {
            return username;
        }
    }
    public static class ReviewResponse {
        private Reviews review;
        private List<Media> mediaList;
        private String username;

        public ReviewResponse(Reviews review, List<Media> mediaList, String username) {
            this.review = review;
            this.mediaList = mediaList;
            this.username = username;
        }

        public Reviews getReview() {
            return review;
        }

        public List<Media> getMediaList() {
            return mediaList;
        }

        public String getUsername() {
            return username;
        }
    }
}
