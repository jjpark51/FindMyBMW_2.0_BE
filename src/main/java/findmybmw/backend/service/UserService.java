package findmybmw.backend.service;

import findmybmw.backend.model.Users;
import findmybmw.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public String getUsernameById(Integer userId) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getUsername();
    }

    public Integer getUserIdByUsername(String username) {
        Users user = usersRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getId();
    }
}
