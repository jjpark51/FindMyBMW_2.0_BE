package findmybmw.backend.service;

import findmybmw.backend.model.Reviews;
import findmybmw.backend.repository.ReviewRepository;
import findmybmw.backend.repository.UsersRepository;
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

    public List<Reviews> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Reviews> getReviewById(Integer postId) {
        return reviewRepository.findById(postId);
    }

    public Reviews createReview(Reviews review) {
        return reviewRepository.save(review);
    }

    public Reviews updateReview(Integer postId, Reviews reviewDetails) {
        Reviews review = reviewRepository.findById(postId).orElseThrow(() -> new RuntimeException("Review not found"));
        review.setTitle(reviewDetails.getTitle());
        review.setContent(reviewDetails.getContent());
        review.setRating(reviewDetails.getRating());
        review.setStatus(reviewDetails.getStatus());
        review.setPublishedAt(reviewDetails.getPublishedAt());
        return reviewRepository.save(review);
    }

    public void deleteReview(Integer postId) {
        reviewRepository.deleteById(postId);
    }

    public Integer getUserIdByUsername(String username) {
        return usersRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found")).getId();
    }

    public List<Reviews> getReviewsByModelId(Integer modelId) {
        return reviewRepository.findByModelId(modelId);
    }
}
