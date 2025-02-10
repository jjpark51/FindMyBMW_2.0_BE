package findmybmw.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "comments")  // Add this line to specify the table name
@Data
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer id;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "post_id")
    private Integer post_id;


    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "content")
    private String content;


}
