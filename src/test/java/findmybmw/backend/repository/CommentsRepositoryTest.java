package findmybmw.backend.repository;

import findmybmw.backend.model.Comments;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)  // This will prevent transaction rollback
public class CommentsRepositoryTest {

    @Autowired
    private CommentsRepository commentsRepository;

    @Test
    public void testCreateComment() {
        // Create a new comment
        Comments comment = new Comments();
        comment.setContent("Test comment content");
        comment.setCreated_at(new Date());
        comment.setPost_id(1);
        comment.setUser_id(23);

        // Save the comment directly using repository
        Comments savedComment = commentsRepository.save(comment);

        // Force flush to ensure immediate persistence
        commentsRepository.flush();

        // Print the saved comment for verification
        System.out.println(savedComment.toString());

        // Verify the saved comment
        assertThat(savedComment.getId()).isNotNull();
        assertThat(savedComment.getContent()).isEqualTo("Test comment content");
        assertThat(savedComment.getPost_id()).isEqualTo(1);
        assertThat(savedComment.getUser_id()).isEqualTo(23);
        assertThat(savedComment.getCreated_at()).isNotNull();
    }

//    @Test
//    public void testFindCommentById() {
//        // Create and save a comment
//        Comments comment = new Comments();
//        comment.setContent("Test finding comment");
//        comment.setCreated_at(new Date());
//        comment.setPost_id(2);
//        comment.setUser_id(24);
//
//        Comments savedComment = commentsRepository.save(comment);
//        commentsRepository.flush();
//
//        // Find the comment by ID
//        Optional<Comments> foundComment = commentsRepository.findById(savedComment.getId());
//
//        // Verify the found comment
//        assertThat(foundComment).isPresent();
//        assertThat(foundComment.get().getContent()).isEqualTo("Test finding comment");
//        assertThat(foundComment.get().getPost_id()).isEqualTo(2);
//        assertThat(foundComment.get().getUser_id()).isEqualTo(24);
//    }
//
//    @Test
//    public void testUpdateComment() {
//        // Create and save a comment
//        Comments comment = new Comments();
//        comment.setContent("Original content");
//        comment.setCreated_at(new Date());
//        comment.setPost_id(3);
//        comment.setUser_id(25);
//
//        Comments savedComment = commentsRepository.save(comment);
//        commentsRepository.flush();
//
//        // Update the comment
//        savedComment.setContent("Updated content");
//        Comments updatedComment = commentsRepository.save(savedComment);
//        commentsRepository.flush();
//
//        // Verify the update
//        assertThat(updatedComment.getContent()).isEqualTo("Updated content");
//        assertThat(updatedComment.getId()).isEqualTo(savedComment.getId());
//    }
//
//    @Test
//    public void testDeleteComment() {
//        // Create and save a comment
//        Comments comment = new Comments();
//        comment.setContent("Comment to delete");
//        comment.setCreated_at(new Date());
//        comment.setPost_id(4);
//        comment.setUser_id(26);
//
//        Comments savedComment = commentsRepository.save(comment);
//        commentsRepository.flush();
//
//        // Delete the comment
//        commentsRepository.deleteById(savedComment.getId());
//        commentsRepository.flush();
//
//        // Verify the deletion
//        Optional<Comments> deletedComment = commentsRepository.findById(savedComment.getId());
//        assertThat(deletedComment).isEmpty();
//    }
}