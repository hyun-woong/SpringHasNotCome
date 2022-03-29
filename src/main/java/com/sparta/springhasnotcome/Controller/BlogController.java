package com.sparta.springhasnotcome.Controller;

import com.sparta.springhasnotcome.Dto.PostRequestDto;
import com.sparta.springhasnotcome.Models.Blog;
import com.sparta.springhasnotcome.Repository.BlogRepository;
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
    @GetMapping("/detail/{id}")
    public String getdetail(@PathVariable Long id){
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시글이 존재하지 않습니다.")
        );
        return "detail";
    }

    //게시글 저장
    @PostMapping("/api/post")
    public Blog createpost(@RequestBody PostRequestDto requestDto){
        Blog blog = new Blog(requestDto);
        blogRepository.save(blog);
        return blog;
    }

    //게시글 삭제
    @DeleteMapping("/home/{id}")
    public Long deletePost(@PathVariable Long id){
        blogRepository.deleteById(id);
        return id;
    }

    //게시글 수정
    @PutMapping("/home/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        blogService.update(id, requestDto);
        return id;
    }


}
