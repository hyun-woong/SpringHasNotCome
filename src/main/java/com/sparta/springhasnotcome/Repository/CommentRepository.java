package com.sparta.springhasnotcome.Repository;

import com.sparta.springhasnotcome.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByOrderByModifiedAtDesc();

    List<Comment> findAllByBlogIdOrderByCreatedAtDesc(Long id);
}
