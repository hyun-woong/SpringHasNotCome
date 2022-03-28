package com.sparta.springhasnotcome.Controller;

import com.sparta.springhasnotcome.Dto.BlogRequestDto;
import com.sparta.springhasnotcome.Models.Blog;
import com.sparta.springhasnotcome.Repository.BlogRepository;
import com.sparta.springhasnotcome.Repository.UserRepository;
import com.sparta.springhasnotcome.Service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogRepository blogRepository;
    private final BlogService blogService;

    //메인 화면
    @GetMapping("/home")
    public List<Blog> getblog(){
        return blogRepository.findAllByOrderByModifiedAtDesc();
    }

    //게시글 상세페이지
    @GetMapping("/home/{id}")
    public Blog getdetail(@PathVariable Long id){
        return blogRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시글이 존재하지 않습니다.")
        );
    }

    //게시글 저장
    @PostMapping("/home")
    public Blog createpost(@RequestBody BlogRequestDto requestDto){
        Blog blog = new Blog(requestDto);
        return blogRepository.save(blog);
    }

    //게시글 삭제
    @DeleteMapping("/home/{id}")
    public Long deletePost(@PathVariable Long id){
        blogRepository.deleteById(id);
        return id;
    }

    //게시글 수정
    @PutMapping("/home/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody BlogRequestDto requestDto){
        blogService.update(id, requestDto);
        return id;
    }


}
