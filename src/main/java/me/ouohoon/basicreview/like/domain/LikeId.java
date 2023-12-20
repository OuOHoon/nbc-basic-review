package me.ouohoon.basicreview.like.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LikeId implements Serializable {

    private Long userId;

    private Long postId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeId that = (LikeId) o;
        return Objects.equals(getUserId(), that.getUserId())
                && Objects.equals(getPostId(), that.getPostId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getPostId());
    }
}
