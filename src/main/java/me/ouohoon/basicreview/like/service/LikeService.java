package me.ouohoon.basicreview.like.service;

import me.ouohoon.basicreview.global.exception.BaseException;
import me.ouohoon.basicreview.global.exception.ErrorCode;
import me.ouohoon.basicreview.like.domain.Like;
import me.ouohoon.basicreview.like.domain.LikeId;
import me.ouohoon.basicreview.post.domain.Post;
import me.ouohoon.basicreview.user.domain.User;
import org.springframework.transaction.annotation.Transactional;

public interface LikeService {

    /**
     * 게시글 좋아요
     * @param postId 좋아요할 게시글 id
     * @param user 요청 유저
     */
    void like(Long postId, User user);

    /**
     * 게시글 좋아요 취소
     * @param postId 좋아요 취소할 게시글 id
     * @param user 요청 유저
     */
    void unlike(Long postId, User user);
}
