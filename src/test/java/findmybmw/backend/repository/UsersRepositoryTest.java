package findmybmw.backend.repository;

import findmybmw.backend.model.Users;
import findmybmw.backend.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)  // Add this to prevent test rollback
public class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    @Transactional
    public void testCreateUser() {
        try {
            // Create a new user
            Users user = new Users();
            user.setUsername("testuser2");
            user.setEmail("test@example2.com");
            user.setPassword_hash("hashedpassword1223");
            user.setCreated_at(new Date());
            user.setUpdated_at(new Date());

            System.out.println("Before save: " + user);

            // Save the user
            Users savedUser = usersRepository.save(user);
            System.out.println("After save: " + savedUser);

            // Force flush to database
            usersRepository.flush();

            // Verify the user was saved
            assertThat(savedUser.getId()).isNotNull();
            assertThat(savedUser.getUsername()).isEqualTo("testuser");
            assertThat(savedUser.getEmail()).isEqualTo("test@example.com");

            // Try to fetch the saved user
            Optional<Users> fetchedUser = usersRepository.findById(savedUser.getId());
            System.out.println("Fetched user: " + fetchedUser.orElse(null));

            assertThat(fetchedUser).isPresent();
            assertThat(fetchedUser.get().getUsername()).isEqualTo("testuser");

        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

//    @Test
//    public void testReadUser() {
//        // Create and save a test user
//        Users user = new Users();
//        user.setUsername("readtest");
//        user.setEmail("read@example.com");
//        user.setPassword_hash("hashedpassword456");
//        user.setCreated_at(new Date());
//        user.setUpdated_at(new Date());
//        usersRepository.save(user);
//
//        // Read the user
//        Optional<Users> foundUser = usersRepository.findById(user.getId());
//
//        // Verify the user was found
//        assertThat(foundUser).isPresent();
//        assertThat(foundUser.get().getUsername()).isEqualTo("readtest");
//
//        System.out.println(foundUser);
//    }
//
//    @Test
//    public void testUpdateUser() {
//        // Create and save a test user
//        Users user = new Users();
//        user.setUsername("updatetest");
//        user.setEmail("update@example.com");
//        user.setPassword_hash("hashedpassword789");
//        user.setCreated_at(new Date());
//        user.setUpdated_at(new Date());
//        Users savedUser = usersRepository.save(user);
//
//        // Update the user
//        savedUser.setUsername("updatedusername");
//        savedUser.setUpdated_at(new Date());
//        Users updatedUser = usersRepository.save(savedUser);
//
//        // Verify the update
//        assertThat(updatedUser.getUsername()).isEqualTo("updatedusername");
//    }
//
//    @Test
//    public void testDeleteUser() {
//        // Create and save a test user
//        Users user = new Users();
//        user.setUsername("deletetest");
//        user.setEmail("delete@example.com");
//        user.setPassword_hash("hashedpassword101");
//        user.setCreated_at(new Date());
//        user.setUpdated_at(new Date());
//        Users savedUser = usersRepository.save(user);
//
//        // Delete the user
//        usersRepository.deleteById(savedUser.getId());
//
//        // Verify the deletion
//        Optional<Users> deletedUser = usersRepository.findById(savedUser.getId());
//        assertThat(deletedUser).isEmpty();
//    }
//
//    @Test
//    public void testFindAllUsers() {
//        // Create and save multiple test users
//        Users user1 = new Users();
//        user1.setUsername("user1");
//        user1.setEmail("user1@example.com");
//        user1.setPassword_hash("hashedpassword1");
//        user1.setCreated_at(new Date());
//        user1.setUpdated_at(new Date());
//
//        Users user2 = new Users();
//        user2.setUsername("user2");
//        user2.setEmail("user2@example.com");
//        user2.setPassword_hash("hashedpassword2");
//        user2.setCreated_at(new Date());
//        user2.setUpdated_at(new Date());
//
//        usersRepository.save(user1);
//        usersRepository.save(user2);
//
//        // Find all users
//        List<Users> allUsers = usersRepository.findAll();
//
//        // Verify the users were found
//        assertThat(allUsers).hasSize(2);
//        assertThat(allUsers).extracting(Users::getUsername)
//                .containsExactlyInAnyOrder("user1", "user2");
//    }
//
//    @Test
//    public void testFindByEmail() {
//        // Create and save a test user
//        Users user = new Users();
//        user.setUsername("emailtest");
//        user.setEmail("find@example.com");
//        user.setPassword_hash("hashedpassword202");
//        user.setCreated_at(new Date());
//        user.setUpdated_at(new Date());
//        usersRepository.save(user);
//
//        // Find user by email (you'll need to add this method to your repository)
//        Optional<Users> foundUser = usersRepository.findByEmail("find@example.com");
//
//        // Verify the user was found
//        assertThat(foundUser).isPresent();
//        assertThat(foundUser.get().getUsername()).isEqualTo("emailtest");
//    }
}