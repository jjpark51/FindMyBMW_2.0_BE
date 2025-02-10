package findmybmw.backend.repository;

import findmybmw.backend.model.Tags;
import findmybmw.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagsRepository extends JpaRepository<Tags, Integer> {
//    Optional<Tags> findById(Int email);


}
