package me.ouohoon.basicreview.like.domain;

import jakarta.persistence.*;
import lombok.*;
import me.ouohoon.basicreview.global.domain.BaseEntity;
import me.ouohoon.basicreview.post.domain.Post;
import me.ouohoon.basicreview.user.domain.User;

@Entity
@Table(name = "likes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Like extends BaseEntity {

    @EmbeddedId
    private LikeId id;

    @Setter
    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @MapsId("postId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
