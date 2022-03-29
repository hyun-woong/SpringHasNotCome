package com.sparta.springhasnotcome.Controller;

import com.sparta.springhasnotcome.Security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//html에서 로그인 유저의 이름 값을 가져옴
@Controller
public class HomeController {
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

    //게시글 상세 페이지로 이동
    @RequestMapping("/detail")
    public String getdetail(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        model.addAttribute("username", userDetails.getUsername());
        return "detail";
    }

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