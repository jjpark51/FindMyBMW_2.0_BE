package findmybmw.backend.controller;

import findmybmw.backend.service.ReviewLikesService;
import findmybmw.backend.service.UserService;
import findmybmw.backend.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@CrossOrigin(origins = "http://localhost:3000") // Ensure CORS is enabled for this controller
public class ReviewLikesController {

    @Autowired
    private ReviewLikesService reviewLikesService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/post/{postId}")
    public ResponseEntity<LikeInfoResponse> getLikeInfo(@PathVariable Integer postId, @RequestHeader(value = "Authorization", required = false) String token) {
        long likeCount = reviewLikesService.getLikeCountByPostId(postId);
        boolean hasLiked = false;
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            String username = jwtTokenUtil.extractUsername(jwtToken);
            Integer userId = userService.getUserIdByUsername(username);
            hasLiked = reviewLikesService.hasUserLikedPost(userId, postId);
        }
        LikeInfoResponse response = new LikeInfoResponse(likeCount, hasLiked);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/toggle/{postId}")
    public ResponseEntity<LikeInfoResponse> toggleLike(@PathVariable Integer postId, @RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        String username = jwtTokenUtil.extractUsername(jwtToken);
        Integer userId = userService.getUserIdByUsername(username);
        reviewLikesService.toggleLike(userId, postId);
        long likeCount = reviewLikesService.getLikeCountByPostId(postId);
        boolean hasLiked = reviewLikesService.hasUserLikedPost(userId, postId);
        LikeInfoResponse response = new LikeInfoResponse(likeCount, hasLiked);
        return ResponseEntity.ok(response);
    }

    public static class LikeInfoResponse {
        private long likeCount;
        private boolean hasLiked;

        public LikeInfoResponse(long likeCount, boolean hasLiked) {
            this.likeCount = likeCount;
            this.hasLiked = hasLiked;
        }

        public long getLikeCount() {
            return likeCount;
        }

        public boolean isHasLiked() {
            return hasLiked;
        }
    }
}
