package com.sparta.springhasnotcome.Repository;

import com.sparta.springhasnotcome.Models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findAllByOrderByModifiedAtDesc();
}
