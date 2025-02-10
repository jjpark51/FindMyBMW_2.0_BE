package findmybmw.backend.repository;

import findmybmw.backend.model.Comments;
import findmybmw.backend.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {
    List<Reviews> findByUserId(Integer userId);

    List<Reviews> findByModelId(Integer modelId);

//    List<Reviews> findByStatus(String status);

}
