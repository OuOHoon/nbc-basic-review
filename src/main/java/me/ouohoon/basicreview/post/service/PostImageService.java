package me.ouohoon.basicreview.post.service;

import me.ouohoon.basicreview.post.domain.Post;
import org.springframework.web.multipart.MultipartFile;

public interface PostImageService {

    /**
     * 게시글 이미지 s3에 업로드
     * @param post 이미지가 첨부된 게시글
     * @param image 첨부할 이미지 파일
     */
    void uploadImage(Post post, MultipartFile image);
}
