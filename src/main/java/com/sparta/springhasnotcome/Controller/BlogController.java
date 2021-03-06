package com.sparta.springhasnotcome.Controller;

import com.sparta.springhasnotcome.Dto.CommentRequestDto;
import com.sparta.springhasnotcome.Dto.PostRequestDto;
import com.sparta.springhasnotcome.Models.Blog;
import com.sparta.springhasnotcome.Models.Comment;
import com.sparta.springhasnotcome.Repository.BlogRepository;
import com.sparta.springhasnotcome.Repository.CommentRepository;
import com.sparta.springhasnotcome.Security.UserDetailsImpl;
import com.sparta.springhasnotcome.Service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogRepository blogRepository;
    private final BlogService blogService;
    private final CommentRepository commentRepository;

    //메인 화면
    @GetMapping("/home")
    public List<Blog> getblog(){
        return blogService.getblog();
    }

//    //게시글 상세 페이지로 이동
//    @GetMapping("/api/detail/{id}")
//    public Blog getdetail(@PathVariable Long id){
//        return blogRepository.findById(id).orElseThrow(
//                () -> new NullPointerException("게시글이 존재하지 않습니다.")
//        );
//    }

    //상세페이지
    @GetMapping("/blogs/detail")
    public ModelAndView getOneBlogAndComments(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return blogService.getOneBlogAndComments(id, userDetails);
    }


//    게시글 저장
//    받아온 값의 username를 현재 로그인 된 사용자의 이름(아이디)로 바꿔줌
    @PostMapping("/api/post")
    public Blog createpost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        requestDto.setUsername(userDetails.getUsername());
        return blogService.createpost(requestDto);
    }

    //게시글 삭제
    @DeleteMapping("/home/{id}")
    public Long deletePost(@PathVariable Long id){
        return blogService.deletePost(id);
    }

    //게시글 수정
    @PutMapping("/home/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        blogService.update(id, requestDto);
        return id;
    }

////    //댓글 작성
//    @PostMapping("/api/detail/comment")
//    public Comment createcomment(@RequestBody CommentRequestDto requestDto){
//        Comment comment = new Comment(requestDto);
//        commentRepository.save(comment);
//        return comment;
//    }
//    @GetMapping("/api/detail/comment")
//    public List<Comment> getcomment(){
//        return commentRepository.findAllByOrderByModifiedAtDesc();
//    }



}
