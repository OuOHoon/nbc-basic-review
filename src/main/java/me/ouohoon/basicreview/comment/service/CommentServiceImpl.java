package me.ouohoon.basicreview.comment.service;

import lombok.RequiredArgsConstructor;
import me.ouohoon.basicreview.comment.domain.Comment;
import me.ouohoon.basicreview.comment.repository.CommentRepository;
import me.ouohoon.basicreview.comment.request.CommentCreateRequest;
import me.ouohoon.basicreview.comment.request.CommentUpdateRequest;
import me.ouohoon.basicreview.comment.response.CommentResponse;
import me.ouohoon.basicreview.global.exception.BaseException;
import me.ouohoon.basicreview.global.exception.ErrorCode;
import me.ouohoon.basicreview.post.domain.Post;
import me.ouohoon.basicreview.post.repository.PostRepository;
import me.ouohoon.basicreview.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<CommentResponse> findAll(Long postId, int page, int size, List<Order> orders) {
        Sort sort = Sort.by(orders);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Comment> comments = commentRepository.findAllByPostId(postId, pageable);
        return comments.stream().map(CommentResponse::new).toList();
    }

    @Transactional
    public CommentResponse comment(Long postId, User user, CommentCreateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_EXIST_POST));

        Comment comment = new Comment(request);
        comment.setUser(user);
        post.addComment(comment);
        Comment savedComment = commentRepository.save(comment);
        return new CommentResponse(savedComment);
    }

    @Transactional
    public CommentResponse update(Long postId, Long commentId, User user, CommentUpdateRequest request) {
        if (!postRepository.existsById(postId)) {
            throw new BaseException(ErrorCode.NOT_EXIST_POST);
        }

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_EXIST_COMMENT));
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new BaseException(ErrorCode.PERMISSION_DENIED);
        }

        comment.update(request);
        return new CommentResponse(comment);
    }

    @Transactional
    public void delete(Long postId, Long commentId, User user) {
        if (!postRepository.existsById(postId)) {
            throw new BaseException(ErrorCode.NOT_EXIST_POST);
        }

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_EXIST_COMMENT));
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new BaseException(ErrorCode.PERMISSION_DENIED);
        }

        commentRepository.delete(comment);
    }
}
