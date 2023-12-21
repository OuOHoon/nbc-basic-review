package me.ouohoon.basicreview.post.service;

import me.ouohoon.basicreview.post.request.PostCreateRequest;
import me.ouohoon.basicreview.post.request.PostListRequest;
import me.ouohoon.basicreview.post.request.PostUpdateRequest;
import me.ouohoon.basicreview.post.response.PostDetailResponse;
import me.ouohoon.basicreview.post.response.PostListResponse;
import me.ouohoon.basicreview.user.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    /**
     * 게시글 목록 조회
     * @param request 페이징, 정렬 옵션
     * @return 게시글 목록
     */
    List<PostListResponse> findAll(PostListRequest request);

    /**
     * 게시글 단건 id로 조회
     * @param id 조회할 게시글 id
     * @return 게시글 상세 정보
     */
    PostDetailResponse findById(Long id);

    /**
     * 게시글 생성
     * @param request 게시글 생성 요청 정보
     * @param user 요청 유저
     * @param images 게시글에 첨부할 이미지 파일 목록
     * @return 생성한 게시글 상세 정보
     */
    PostDetailResponse post(PostCreateRequest request, User user, List<MultipartFile> images);

    /**
     * 게시글 수정
     * @param postId 수정할 게시글 id
     * @param request 게시글 수정 요청 정보
     * @param user 요청 유저
     * @return 수정된 게시글 상세 정보
     */
    PostDetailResponse update(Long postId, PostUpdateRequest request, User user);

    /**
     * 게시글 삭제
     * @param postId 삭제할 게시글 id
     * @param user 요청 유저
     */
    void delete(Long postId, User user);
}
