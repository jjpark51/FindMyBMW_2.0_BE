package findmybmw.backend.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import findmybmw.backend.model.Comments;
import findmybmw.backend.service.CommentsService;
import findmybmw.backend.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable Integer id) {
        Comments comment = commentsService.getCommentById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        String username = commentsService.getUsernameById(comment.getUserId());
        CommentResponse response = new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                username
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/post/{postId}")
    public List<CommentResponse> getCommentsByPostId(@PathVariable Integer postId) {
        List<Comments> comments = commentsService.getCommentsByPostId(postId);
        return comments.stream().map(comment -> new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                commentsService.getUsernameById(comment.getUserId())
        )).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @RequestHeader("Authorization") String token,
            @RequestBody Comments commentDetails) {
        String jwtToken = token.substring(7);
        String username = jwtTokenUtil.extractUsername(jwtToken);
        Integer userId = commentsService.getUserIdByUsername(username);

        Comments comment = new Comments();
        comment.setUserId(userId);
        comment.setPostId(commentDetails.getPostId());
        comment.setContent(commentDetails.getContent());
        comment.setCreatedAt(new Date());
        Comments createdComment = commentsService.createComment(comment);

        CommentResponse response = new CommentResponse(
                createdComment.getId(),
                createdComment.getContent(),
                createdComment.getCreatedAt(),
                username
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Integer id, @RequestBody Comments commentDetails) {
        Comments updatedComment = commentsService.updateComment(id, commentDetails);
        String username = commentsService.getUsernameById(updatedComment.getUserId());
        CommentResponse response = new CommentResponse(
                updatedComment.getId(),
                updatedComment.getContent(),
                updatedComment.getCreatedAt(),
                username
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
        commentsService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    public static class CommentResponse {
        private Integer id;
        private String content;
        private Date createdAt;
        private String username;

        public CommentResponse(Integer id, String content, Date createdAt, String username) {
            this.id = id;
            this.content = content;
            this.createdAt = createdAt;
            this.username = username;
        }

        @JsonProperty
        public Integer getId() {
            return id;
        }

        @JsonProperty
        public String getContent() {
            return content;
        }

        @JsonProperty
        public Date getCreatedAt() {
            return createdAt;
        }

        @JsonProperty
        public String getUsername() {
            return username;
        }
    }
}
