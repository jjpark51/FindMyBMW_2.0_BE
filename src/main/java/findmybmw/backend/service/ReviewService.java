package findmybmw.backend.service;

import findmybmw.backend.dto.ModelReviewStats;
import findmybmw.backend.model.Reviews;
import findmybmw.backend.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MediaRepository mediaRepository; // Add this

    @Autowired
    private CommentsRepository commentsRepository; // Add this

    @Autowired
    private ReviewLikesRepository reviewLikesRepository; // Add this for likes

    public List<Reviews> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Reviews> getReviewById(Integer postId) {
        return reviewRepository.findById(postId);
    }

    public Reviews createReview(Reviews review) {
        return reviewRepository.save(review);
    }

    public Reviews updateReview(Integer postId, Reviews updatedReview) {
        Reviews existingReview = reviewRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        // Preserve original dates while updating
        updatedReview.setCreatedAt(existingReview.getCreatedAt());
        updatedReview.setPublishedAt(existingReview.getPublishedAt());
        // updatedAt will be set by @PreUpdate

        return reviewRepository.save(updatedReview);
    }

    @Transactional  // Add this annotation to ensure atomic operation
    public void deleteReview(Integer postId) {
        // First delete associated media
        mediaRepository.deleteByPostId(postId);
        commentsRepository.deleteByPostId(postId);
        reviewRepository.deleteById(postId);
    }

    public Integer getUserIdByUsername(String username) {
        return usersRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found")).getId();
    }

    public List<Reviews> getReviewsByModelId(Integer modelId) {
        return reviewRepository.findByModelId(modelId);
    }

    public ModelReviewStats getModelReviewStats(Integer modelId) {
        List<Reviews> modelReviews = reviewRepository.findByModelId(modelId);
        ModelReviewStats stats = new ModelReviewStats();

        if (modelReviews.isEmpty()) {
            // Initialize with zeros if no reviews exist
            stats.setAvgOverallRating(0.0);
            stats.setAvgMaintenanceRating(0.0);
            stats.setAvgPerformanceRating(0.0);
            stats.setAvgComfortRating(0.0);
            stats.setAvgSafetyRating(0.0);
            stats.setAvgValueRating(0.0);
            stats.setTotalReviews(0);
            stats.setStarDistribution(new int[5]);
            return stats;
        }

        double sumOverall = 0, sumMaintenance = 0, sumPerformance = 0,
                sumComfort = 0, sumSafety = 0, sumValue = 0;
        int[] starDist = new int[5];

        for (Reviews review : modelReviews) {
            // Handle null values by using 0.0 as default
            sumOverall += review.getRating() != null ? review.getRating() : 0.0;
            sumMaintenance += review.getMaintenanceRating() != null ? review.getMaintenanceRating() : 0.0;
            sumPerformance += review.getPerformanceRating() != null ? review.getPerformanceRating() : 0.0;
            sumComfort += review.getComfortRating() != null ? review.getComfortRating() : 0.0;
            sumSafety += review.getSafetyRating() != null ? review.getSafetyRating() : 0.0;
            sumValue += review.getValueRating() != null ? review.getValueRating() : 0.0;

            // Calculate star distribution
            if (review.getRating() != null) {
                int starRating = (int) Math.round(review.getRating());
                if (starRating >= 1 && starRating <= 5) {
                    starDist[starRating - 1]++;
                }
            }
        }

        int count = modelReviews.size();
        stats.setAvgOverallRating(sumOverall / count);
        stats.setAvgMaintenanceRating(sumMaintenance / count);
        stats.setAvgPerformanceRating(sumPerformance / count);
        stats.setAvgComfortRating(sumComfort / count);
        stats.setAvgSafetyRating(sumSafety / count);
        stats.setAvgValueRating(sumValue / count);
        stats.setTotalReviews(count);
        stats.setStarDistribution(starDist);

        return stats;
    }
}
