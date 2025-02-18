package findmybmw.backend.repository;

import findmybmw.backend.model.ReviewsLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewLikesRepository extends JpaRepository<ReviewsLike, Integer> {
    Optional<ReviewsLike> findByUserIdAndPostId(Integer userId, Integer postId);
    long countByPostId(Integer postId);
    void deleteByPostId(Integer postId);  // Add this method

}
