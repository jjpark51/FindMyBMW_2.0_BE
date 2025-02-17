package findmybmw.backend.service;

import findmybmw.backend.model.ReviewsLike;
import findmybmw.backend.repository.ReviewLikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewLikesService {

    @Autowired
    private ReviewLikesRepository reviewLikesRepository;

    public long getLikeCountByPostId(Integer postId) {
        return reviewLikesRepository.countByPostId(postId);
    }

    public boolean hasUserLikedPost(Integer userId, Integer postId) {
        return reviewLikesRepository.findByUserIdAndPostId(userId, postId).isPresent();
    }

    public void toggleLike(Integer userId, Integer postId) {
        Optional<ReviewsLike> existingLike = reviewLikesRepository.findByUserIdAndPostId(userId, postId);
        if (existingLike.isPresent()) {
            try {
                reviewLikesRepository.delete(existingLike.get());
            } catch (ObjectOptimisticLockingFailureException e) {
                // Handle optimistic locking failure
                throw new RuntimeException("Failed to delete like due to concurrent update", e);
            }
        } else {
            ReviewsLike like = new ReviewsLike();
            like.setUserId(userId);
            like.setPostId(postId);
            try {
                reviewLikesRepository.save(like);
            } catch (ObjectOptimisticLockingFailureException e) {
                // Handle optimistic locking failure
                throw new RuntimeException("Failed to save like due to concurrent update", e);
            }
        }
    }
}
