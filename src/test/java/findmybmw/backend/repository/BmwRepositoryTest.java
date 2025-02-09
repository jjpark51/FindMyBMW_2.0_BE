package findmybmw.backend.repository;

import findmybmw.backend.model.Bmw;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BmwRepositoryTest {

    @Autowired
    private BmwRepository bmwRepository;

    @Test
    @Rollback(true)  // Set to true to rollback test data
    void testDatabaseConnection() {
        // First test - Check if we can read from the database
        var allModels = bmwRepository.findAllModels();
        assertThat(allModels).isNotNull();

        // Second test - Check if we can find specific data
        if (!allModels.isEmpty()) {
            String firstModel = allModels.get(5);
            Bmw foundBmw = bmwRepository.findByModel(firstModel);
            assertThat(foundBmw).isNotNull();
            assertThat(foundBmw.getModel()).isEqualTo(firstModel);

            System.out.println(foundBmw);

            System.out.println(foundBmw.getModel());
        }
    }
}