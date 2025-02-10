package findmybmw.backend.repository;

import findmybmw.backend.model.Reviews;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ReviewsRepositoryTest {

    @Autowired
    private ReviewsRepository reviewRepository;

//    private Reviews createSampleReview() {
//        Reviews review = new Reviews();
//        review.setUserId(23);
//        review.setModelId(1);
//        review.setTitle("Test BMW Review");
//        review.setContent("This is a test review content for BMW.");
//        review.setRating(3.4);
//        review.setStatus("PUBLISHED");
//        review.setCreatedAt(new Date());
//        review.setUpdatedAt(new Date());
//        review.setPublishedAt(new Date());
//        return review;
//    }
//
//    @Test
//    public void testCreateReview() {
//        Reviews review = createSampleReview();
//        Reviews savedReview = reviewRepository.save(review);
//
//        System.out.println(savedReview);
//
////        assertThat(savedReview.getPostId()).isNotNull();
////        assertThat(savedReview.getTitle()).isEqualTo("Test BMW Review");
////        assertThat(savedReview.getRating()).isEqualTo(5);
////        assertThat(savedReview.getCreatedAt()).isNotNull();
//    }
//
//    @Test
//    public void testFindReviewById() {
//        // Create and save a review
//        Reviews review = createSampleReview();
//        Reviews savedReview = reviewRepository.save(review);
//
//        // Find the review by ID
//        Optional<Reviews> foundReview = reviewRepository.findById(savedReview.getPostId());

//        assertThat(foundReview).isPresent();
//        assertThat(foundReview.get().getTitle()).isEqualTo("Test BMW Review");
//        assertThat(foundReview.get().getRating()).isEqualTo(5);
    //}

//    @Test
//    public void testUpdateReview() {
//        // Create and save a review
//        Reviews reviews = createSampleReview();
//        Reviews savedReview = reviewRepository.save(reviews);
//
//        // Update the review
//        savedReview.setTitle("Updated BMW Review");
//        savedReview.setRating(4.0);
//        Reviews updatedReview = reviewRepository.save(savedReview);
//
//        assertThat(updatedReview.getTitle()).isEqualTo("Updated BMW Review");
//        assertThat(updatedReview.getRating()).isEqualTo(4);
//        assertThat(updatedReview.getUpdatedAt()).isNotNull();
//    }

//    @Test
//    public void testDeleteReview() {
//        // Create and save a review
//        Review review = createSampleReview();
//        Review savedReview = reviewRepository.save(review);
//
//        // Delete the review
//        reviewRepository.deleteById(savedReview.getPostId());
//
//        // Verify deletion
//        Optional<Review> deletedReview = reviewRepository.findById(savedReview.getPostId());
//        assertThat(deletedReview).isEmpty();
//    }
//
//    @Test
//    public void testFindByUserId() {
//        // Create and save multiple reviews for the same user
//        Review review1 = createSampleReview();
//        Review review2 = createSampleReview();
//        review2.setTitle("Second BMW Review");
//
//        reviewRepository.save(review1);
//        reviewRepository.save(review2);
//
//        // Find reviews by user ID using custom query method
//        List<Review> userReviews = reviewRepository.findByUserId(1);
//
//        assertThat(userReviews).isNotEmpty();
//        assertThat(userReviews).hasSize(2);
//    }
//
//    @Test
//    public void testFindByModelId() {
//        // Create and save reviews for different models
//        Review review1 = createSampleReview();
//        Review review2 = createSampleReview();
//        review2.setModelId(2);
//
//        reviewRepository.save(review1);
//        reviewRepository.save(review2);
//
//        // Find reviews by model ID
//        List<Review> modelReviews = reviewRepository.findByModelId(1);
//
//        assertThat(modelReviews).isNotEmpty();
//        assertThat(modelReviews).hasSize(1);
//        assertThat(modelReviews.get(0).getModelId()).isEqualTo(1);
//    }
//
//    @Test
//    public void testFindByStatus() {
//        // Create and save reviews with different statuses
//        Review publishedReview = createSampleReview();
//        Review draftReview = createSampleReview();
//        draftReview.setStatus("DRAFT");
//
//        reviewRepository.save(publishedReview);
//        reviewRepository.save(draftReview);
//
//        // Find published reviews
//        List<Review> publishedReviews = reviewRepository.findByStatus("PUBLISHED");
//
//        assertThat(publishedReviews).isNotEmpty();
//        assertThat(publishedReviews.get(0).getStatus()).isEqualTo("PUBLISHED");
//    }
}