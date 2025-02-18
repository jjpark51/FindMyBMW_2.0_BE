package findmybmw.backend.repository;

import findmybmw.backend.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Integer> {
    List<Media> findByPostId(Integer postId);
    void deleteByPostId(Integer postId);  // Add this method

}
