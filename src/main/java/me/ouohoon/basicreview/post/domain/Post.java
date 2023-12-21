package me.ouohoon.basicreview.post.domain;

import jakarta.persistence.*;
import lombok.*;
import me.ouohoon.basicreview.comment.domain.Comment;
import me.ouohoon.basicreview.global.domain.BaseEntity;
import me.ouohoon.basicreview.like.domain.Like;
import me.ouohoon.basicreview.post.request.PostCreateRequest;
import me.ouohoon.basicreview.post.request.PostUpdateRequest;
import me.ouohoon.basicreview.user.domain.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 500)
    private String title;

    @Column(length = 5000)
    private String content;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<PostImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public Post(PostCreateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }

    public void addImage(PostImage image) {
        this.images.add(image);
        image.setPost(this);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setPost(this);
    }

    public void update(PostUpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }
}
