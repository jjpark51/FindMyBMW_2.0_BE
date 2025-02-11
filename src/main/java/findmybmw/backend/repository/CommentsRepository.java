package findmybmw.backend.repository;

import findmybmw.backend.model.Comments;
import findmybmw.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {
    Optional<Comments> findById(Integer id);
    List<Comments> findByPostId(Integer postId);
}
