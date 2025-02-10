package findmybmw.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TokenDTOs {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TokenRefreshRequest {
        private String refreshToken;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TokenRefreshResponse {
        private String accessToken;
        private String refreshToken;
        private String tokenType = "Bearer";
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class AuthRespones {
        private String accessToken;
        private String refreshToken;
        private String tokenType = "Bearer";
    }
}
