package com.sparta.springhasnotcome.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.springhasnotcome.Dto.SignupRequestDto;
import com.sparta.springhasnotcome.Models.User;
import com.sparta.springhasnotcome.Repository.UserRepository;
import com.sparta.springhasnotcome.Service.KakaoUserService;
import com.sparta.springhasnotcome.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final KakaoUserService kakaoUserService;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository, KakaoUserService kakaoUserService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.kakaoUserService = kakaoUserService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

//    // 회원 가입 페이지
//    @GetMapping("/user/signup")
//    public String signup() {
//        return "signup";
//    }

    @GetMapping("/user/signup")
    public String signup(Model model) {
        model.addAttribute("requestDto", new SignupRequestDto());
        return "signup";
    }

//    // 회원 가입 요청 처리
//    @PostMapping("/user/signup")
//    public String registerUser(SignupRequestDto requestDto) {
//        userService.registerUser(requestDto);
//        return "redirect:/user/login";
//    }

    @PostMapping("/user/signup")
    public String registerUser(Model model, @Valid @ModelAttribute("requestDto") SignupRequestDto requestDto, BindingResult bindingResult){

        Optional<User> found1 = userRepository.findByUsername(requestDto.getUsername());
        if (found1.isPresent()){
            FieldError fieldError = new FieldError("requestDto", "username", "이미 존재하는 ID입니다.");
            bindingResult.addError(fieldError);
        }

        if (!requestDto.getPassword().equals(requestDto.getPasswordCheck())){
            FieldError fieldError = new FieldError("requestDto", "passwordCheck", "비밀번호가 일치하지 않습니다.");
            bindingResult.addError(fieldError);
        }

        if (requestDto.getPassword().indexOf(requestDto.getUsername()) != -1){
            FieldError fieldError = new FieldError("requestDto", "password", "비밀번호에 닉네임과 같은 값을 넣을 수 없습니다.");
            bindingResult.addError(fieldError);
        }

       Optional<User> found2 = userRepository.findByEmail(requestDto.getEmail());
        if (found2.isPresent()){
            FieldError fieldError = new FieldError("requestDto", "email", "이미 존재하는 email입니다.");
            bindingResult.addError(fieldError);
        }

        if (bindingResult.hasErrors()){
            model.addAttribute("user", "null");
            return "sigup";
        }

        userService.registerUser(requestDto);
        return "redirect:/user/login";

    }

    //kakao login
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }


}