package com.sparta.springhasnotcome.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.springhasnotcome.Dto.SignupRequestDto;
import com.sparta.springhasnotcome.Models.User;
import com.sparta.springhasnotcome.Repository.UserRepository;
import com.sparta.springhasnotcome.Security.UserDetailsImpl;
import com.sparta.springhasnotcome.Service.KakaoUserService;
import com.sparta.springhasnotcome.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
  private final KakaoUserService kakaoUserService;
  private final UserRepository userRepository;

  @Autowired
  public UserController(UserService userService, KakaoUserService kakaoUserService, UserRepository userRepository){
      this.userService = userService;
      this.kakaoUserService = kakaoUserService;
      this.userRepository = userRepository;
  }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails == null){
            model.addAttribute("user","null");
        }else{

            model.addAttribute("user",userDetails.getUser().getUsername());
        }
        model.addAttribute("requestDto", new SignupRequestDto());
        return "signup";
    }

//    // 회원 가입 요청 처리
//    @PostMapping("/user/signup")
//    public String registerUser(@Valid SignupRequestDto requestDto){
//      userService.registerUser(requestDto);
//      return "redirect:/user/login";
//    }

    //회원가입 요청처리
    @PostMapping("/user/signup")
    public String registerUser(Model model, @Valid @ModelAttribute("requestDto") SignupRequestDto requestDto, BindingResult bindingResult){

        // 회원 ID 중복 확인
        Optional<User> found1 = userRepository.findByUsername(requestDto.getUsername()); // Optional을 쓰면 null을 받을 수 있다.
        if(found1.isPresent()){ // found가 null이 아니면 true를 출력한다.
            FieldError fieldError = new FieldError("requestDto", "username", "이미 존재하는 ID입니다.");
            bindingResult.addError(fieldError);
        }


        if(!requestDto.getPassword().equals(requestDto.getPasswordCheck())){
            FieldError fieldError = new FieldError("requestDto","passwordCheck","비밀번호가 일치하지 않습니다.");
            bindingResult.addError(fieldError);
        }

        if (requestDto.getPassword().contains(requestDto.getUsername())) {
            FieldError fieldError = new FieldError("requestDto", "password", "비밀번호에 닉네임과 같은 값을 넣을수 없습니다.");
            bindingResult.addError(fieldError);
        }

        // 회원 email 중복 확인
        Optional<User> found2 = userRepository.findByEmail(requestDto.getEmail());
        if(found2.isPresent()){ // found가 null이 아니면 true , true이면 같은이메일이 존재한다는 뜻
            FieldError fieldError = new FieldError("requestDto", "email", "이미 존재하는 email입니다.");
            bindingResult.addError(fieldError);
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("user","null");
            return "signup";
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