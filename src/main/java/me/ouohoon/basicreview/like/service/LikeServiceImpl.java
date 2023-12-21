package me.ouohoon.basicreview.like.service;

import lombok.RequiredArgsConstructor;
import me.ouohoon.basicreview.global.exception.BaseException;
import me.ouohoon.basicreview.global.exception.ErrorCode;
import me.ouohoon.basicreview.like.domain.Like;
import me.ouohoon.basicreview.like.domain.LikeId;
import me.ouohoon.basicreview.like.repository.LikeRepository;
import me.ouohoon.basicreview.post.domain.Post;
import me.ouohoon.basicreview.post.repository.PostRepository;
import me.ouohoon.basicreview.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    @Transactional
    public void like(Long postId, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_EXIST_POST));

        if (likeRepository.existsById(new LikeId(user.getId(), postId))) {
            throw new BaseException(ErrorCode.ALREADY_LIKE);
        }

        Like like = new Like(new LikeId(user.getId(), post.getId()), user, post);

        likeRepository.save(like);
    }

    @Transactional
    public void unlike(Long postId, User user) {
        if (!postRepository.existsById(postId)) {
            throw new BaseException(ErrorCode.NOT_EXIST_POST);
        }

        Like like = likeRepository.findById(new LikeId(user.getId(), postId))
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_EXIST_LIKE));
        if (!like.getUser().getId().equals(user.getId())) {
            throw new BaseException(ErrorCode.PERMISSION_DENIED);
        }

        likeRepository.delete(like);
    }
}
