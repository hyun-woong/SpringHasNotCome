package com.sparta.springhasnotcome.Controller;

import com.sparta.springhasnotcome.Dto.PostRequestDto;
import com.sparta.springhasnotcome.Models.Blog;
import com.sparta.springhasnotcome.Repository.BlogRepository;
import com.sparta.springhasnotcome.Security.UserDetailsImpl;
import com.sparta.springhasnotcome.Service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//html에서 로그인 유저의 이름 값을 가져옴
@Controller
@RequiredArgsConstructor
public class HomeController {

//    private final BlogRepository blogRepository;
private final BlogService blogService;


    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        return "index";
    }

    //게시글 작성 페이지로 이동
    @RequestMapping("/post")
    public String getPost(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        model.addAttribute("username", userDetails.getUsername());
        return "post";
    }

//    //게시글 저장
//    @PostMapping("/post")
//    public String write(PostRequestDto postRequestDto){
//        blogService.savePost(postRequestDto);
//        return "redirect:/";
//    }

//
//    //게시글 상세 페이지로 이동
//    @GetMapping("/api/detail/{id}")
//    public String getdetail(@PathVariable Long id){
//        Blog blog = blogRepository.findById(id).orElseThrow(
//                () -> new NullPointerException("게시글이 존재하지 않습니다.")
//        );
//        return "detail";
//    }

//    @GetMapping("/")
//    public String detail(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        model.addAttribute("username", userDetails.getUsername());
//        return "detail";
//    }
//
//    @GetMapping("/")
//    public String post(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        model.addAttribute("username", userDetails.getUsername());
//        return "post";
//    }


}