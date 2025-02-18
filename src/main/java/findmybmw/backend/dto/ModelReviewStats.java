package findmybmw.backend.dto;

import lombok.Data;

@Data
public class ModelReviewStats {
    private Double avgOverallRating;
    private Double avgMaintenanceRating;
    private Double avgPerformanceRating;
    private Double avgComfortRating;
    private Double avgSafetyRating;
    private Double avgValueRating;
    private Integer totalReviews;
    private int[] starDistribution; // Array to store count of 1-5 star reviews
}