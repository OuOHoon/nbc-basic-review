package me.ouohoon.basicreview.like.repository;

import me.ouohoon.basicreview.like.domain.Like;

import me.ouohoon.basicreview.like.domain.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;



public interface LikeRepository extends JpaRepository<Like, LikeId> {
}
