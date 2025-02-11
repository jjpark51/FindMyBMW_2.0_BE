package findmybmw.backend.controller;

import findmybmw.backend.model.Reviews;
import findmybmw.backend.service.ReviewService;
import findmybmw.backend.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:3000") // Ensure CORS is enabled for this controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping
    public List<Reviews> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Reviews> getReviewById(@PathVariable Integer postId) {
        Reviews review = reviewService.getReviewById(postId).orElseThrow(() -> new RuntimeException("Review not found"));
        return ResponseEntity.ok(review);
    }

    @GetMapping("/model/{modelId}")
    public List<Reviews> getReviewsByModelId(@PathVariable Integer modelId) {
        return reviewService.getReviewsByModelId(modelId);
    }

    @PostMapping
    public ResponseEntity<Reviews> createReview(
            @RequestHeader("Authorization") String token,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("rating") Double rating,
            @RequestParam("modelId") Integer modelId,
            @RequestParam("publishedAt") String publishedAt,
            @RequestParam(value = "photo", required = false) MultipartFile photo) throws IOException {
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
        // Handle photo upload if necessary
        Reviews createdReview = reviewService.createReview(review);
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
}
