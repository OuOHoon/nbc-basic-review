package me.ouohoon.basicreview.comment.domain;

import jakarta.persistence.*;
import lombok.*;
import me.ouohoon.basicreview.comment.request.CommentCreateRequest;
import me.ouohoon.basicreview.comment.request.CommentUpdateRequest;
import me.ouohoon.basicreview.global.domain.BaseEntity;
import me.ouohoon.basicreview.post.domain.Post;
import me.ouohoon.basicreview.user.domain.User;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(CommentCreateRequest request) {
        this.content = request.getContent();
    }

    public void update(CommentUpdateRequest request) {
        this.content = request.getContent();
    }
}
