package me.ouohoon.basicreview.comment.service;

import me.ouohoon.basicreview.comment.request.CommentCreateRequest;
import me.ouohoon.basicreview.comment.request.CommentUpdateRequest;
import me.ouohoon.basicreview.comment.response.CommentResponse;
import me.ouohoon.basicreview.user.domain.User;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CommentService {

    /**
     * 댓글 목록 조회
     * @param postId 조회할 게시글 id
     * @param page 페이지네이션 페이지
     * @param size 페이지네이션 사이즈
     * @param orders 정렬 기준 목록
     * @return 댓글 목록
     */
    List<CommentResponse> findAll(Long postId, int page, int size, List<Sort.Order> orders);

    /**
     * 댓글 생성
     * @param postId 댓글 생성할 게시글 id
     * @param user 요청 유저
     * @param request 댓글 생성 요청 정보
     * @return 생성한 댓글 정보
     */
    CommentResponse comment(Long postId, User user, CommentCreateRequest request);

    /**
     * 댓글 수정
     * @param postId 댓글 수정할 게시글 id
     * @param commentId 수정할 댓글 id
     * @param user 요청 유저
     * @param request 댓글 수정 요청 정보
     * @return 수정한 댓글 정보
     */
    CommentResponse update(Long postId, Long commentId, User user, CommentUpdateRequest request);

    /**
     * 댓글 삭제
     * @param postId 댓글 삭제할 게시글 id
     * @param commentId 삭제할 댓글 id
     * @param user 요청 유저
     */
    void delete(Long postId, Long commentId, User user);
}
