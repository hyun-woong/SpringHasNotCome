package com.sparta.springhasnotcome.Controller;

import com.sparta.springhasnotcome.Dto.CommentRequestDto;
import com.sparta.springhasnotcome.Dto.PostRequestDto;
import com.sparta.springhasnotcome.Models.Blog;
import com.sparta.springhasnotcome.Models.Comment;
import com.sparta.springhasnotcome.Repository.BlogRepository;
import com.sparta.springhasnotcome.Repository.CommentRepository;
import com.sparta.springhasnotcome.Security.UserDetailsImpl;
import com.sparta.springhasnotcome.Service.CommentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;
//    private final BlogRepository blogRepository;






//    //댓글작성
//    @PostMapping("api/detail/{id}/comment")
//    public String createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @ModelAttribute CommentRequestDto requestDto){
//        Comment comment = new Comment(requestDto);
//        Blog blog = blogRepository.findById(id).orElseThrow(
//                () -> new NullPointerException("게시글이 존재하지 않습니다.")
//        );
//        comment.setUsername(userDetails.getUser());
//        comment.setBlog(blog);
//        commentRepository.save(comment);
//
//        return "redirect:/api/detail/{id}";
//    }




}
