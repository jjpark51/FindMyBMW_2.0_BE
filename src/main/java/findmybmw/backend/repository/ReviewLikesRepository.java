package findmybmw.backend.repository;

import findmybmw.backend.model.Reviews;
import findmybmw.backend.model.ReviewsLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewLikesRepository extends JpaRepository<ReviewsLike, Integer> {
    List<ReviewsLike> findByUserId(Integer userId);
    List<ReviewsLike> findByPostId(Integer postId);


}
