package findmybmw.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "users")  // Use ASCII characters
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")  // Use ASCII characters
    private Integer id;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "email")
    private String email;


    @Column(name = "password_hash")
    private String password_hash;

    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "username")
    private String username;






}