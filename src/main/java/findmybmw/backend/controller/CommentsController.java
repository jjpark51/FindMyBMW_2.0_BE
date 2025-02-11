package findmybmw.backend.controller;

import findmybmw.backend.model.Comments;
import findmybmw.backend.service.CommentsService;
import findmybmw.backend.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "http://localhost:3000") // Ensure CORS is enabled for this controller
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping
    public List<Comments> getAllComments() {
        return commentsService.getAllComments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comments> getCommentById(@PathVariable Integer id) {
        Comments comment = commentsService.getCommentById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/post/{postId}")
    public List<Comments> getCommentsByPostId(@PathVariable Integer postId) {
        return commentsService.getCommentsByPostId(postId);
    }

    @PostMapping
    public ResponseEntity<Comments> createComment(
            @RequestHeader("Authorization") String token,
            @RequestParam("postId") Integer postId,
            @RequestParam("content") String content) {
        String jwtToken = token.substring(7);
        String username = jwtTokenUtil.extractUsername(jwtToken);
        Integer userId = commentsService.getUserIdByUsername(username);

        Comments comment = new Comments();
        comment.setUserId(userId);
        comment.setPostId(postId);
        comment.setContent(content);
        comment.setCreatedAt(new Date());
        Comments createdComment = commentsService.createComment(comment);
        return ResponseEntity.ok(createdComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comments> updateComment(@PathVariable Integer id, @RequestBody Comments commentDetails) {
        Comments updatedComment = commentsService.updateComment(id, commentDetails);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
        commentsService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
