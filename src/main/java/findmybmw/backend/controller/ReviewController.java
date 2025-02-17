package findmybmw.backend.controller;

import findmybmw.backend.model.Media;
import findmybmw.backend.model.Reviews;
import findmybmw.backend.service.MediaService;
import findmybmw.backend.service.ReviewService;
import findmybmw.backend.service.UserService;
import findmybmw.backend.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

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
    public List<Reviews> getReviewsByModelId(@PathVariable Integer modelId) {
        return reviewService.getReviewsByModelId(modelId);
    }

    @GetMapping("/{postId}/media")
    public ResponseEntity<List<Media>> getMediaByPostId(@PathVariable Integer postId) {
        List<Media> mediaList = mediaService.getMediaByPostId(postId);
        return ResponseEntity.ok(mediaList);
    }

    @PostMapping
    public ResponseEntity<Reviews> createReview(
            @RequestHeader("Authorization") String token,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("rating") Double rating,
            @RequestParam("modelId") Integer modelId,
            @RequestParam("publishedAt") String publishedAt,
            @RequestParam(value = "photos", required = false) MultipartFile[] photos) throws IOException {
        String jwtToken = token.substring(7);
        String username = jwtTokenUtil.extractUsername(jwtToken);
        Integer userId = reviewService.getUserIdByUsername(username);

        Reviews review = new Reviews();
        review.setUserId(userId);
        review.setTitle(title);
        review.setContent(content);
        review.setRating(rating);
        review.setModelId(modelId);
        review.setPublishedAt(new Date());
        Reviews createdReview = reviewService.createReview(review);

        if (photos != null && photos.length > 0) {
            for (MultipartFile photo : photos) {
                if (!photo.isEmpty()) {
                    String fileName = photo.getOriginalFilename();
                    Path filePath = Paths.get(UPLOAD_DIR, fileName);
                    try {
                        Files.createDirectories(filePath.getParent());
                        Files.write(filePath, photo.getBytes());

                        Media media = new Media();
                        media.setUserId(userId);
                        media.setPostId(createdReview.getPostId());
                        media.setFileName(fileName);
                        media.setFileType(photo.getContentType());
                        media.setFilePath(filePath.toString());
                        mediaService.saveMedia(media);
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException("Failed to store file " + fileName, e);
                    }
                }
            }
        }

        return ResponseEntity.ok(createdReview);
    }

    @PutMapping("/update/{modelId}/{postId}")
    public ResponseEntity<Reviews> updateReview(@PathVariable Integer postId, @RequestBody Reviews reviewDetails) {
        Reviews updatedReview = reviewService.updateReview(postId, reviewDetails);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/delete/{modelId}/{postId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer postId) {
        reviewService.deleteReview(postId);
        return ResponseEntity.noContent().build();
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
