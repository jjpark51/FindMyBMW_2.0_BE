package findmybmw.backend.service;

import findmybmw.backend.model.Comments;
import findmybmw.backend.repository.CommentsRepository;
import findmybmw.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private UsersRepository usersRepository;

    public List<Comments> getAllComments() {
        return commentsRepository.findAll();
    }

    public Optional<Comments> getCommentById(Integer id) {
        return commentsRepository.findById(id);
    }

    public List<Comments> getCommentsByPostId(Integer postId) {
        return commentsRepository.findByPostId(postId);
    }

    public Comments createComment(Comments comment) {
        return commentsRepository.save(comment);
    }

    public Comments updateComment(Integer id, Comments commentDetails) {
        Comments comment = commentsRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setContent(commentDetails.getContent());
        return commentsRepository.save(comment);
    }

    public void deleteComment(Integer id) {
        commentsRepository.deleteById(id);
    }

    public Integer getUserIdByUsername(String username) {
        return usersRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found")).getId();
    }
}
