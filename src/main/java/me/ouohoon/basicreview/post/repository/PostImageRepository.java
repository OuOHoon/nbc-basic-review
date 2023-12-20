package me.ouohoon.basicreview.post.repository;

import me.ouohoon.basicreview.post.domain.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}
