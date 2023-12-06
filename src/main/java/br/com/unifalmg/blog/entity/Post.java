package br.com.unifalmg.blog.entity;

import lombok.*;

import javax.persistence.*;
import javax.xml.stream.events.Comment;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "db", name = "post")
public class Post implements Serializable {

    @Id
    private Integer id;

    private String title;

    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // TODO: Add a list of comments
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

}
