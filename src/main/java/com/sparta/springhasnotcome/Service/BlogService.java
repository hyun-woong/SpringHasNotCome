package com.sparta.springhasnotcome.Service;

import com.sparta.springhasnotcome.Dto.PostRequestDto;
import com.sparta.springhasnotcome.Models.Blog;
import com.sparta.springhasnotcome.Models.Comment;
import com.sparta.springhasnotcome.Repository.BlogRepository;
import com.sparta.springhasnotcome.Repository.CommentRepository;
import com.sparta.springhasnotcome.Security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor //final이 있으면 생성자를 자동으로 넣어줌
@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long update(Long id, PostRequestDto requestDto){
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        blog.update(requestDto);
        return blog.getId();
    }

    //게시글 Post 요청 처리
    //사용자 이름을 로그인 아이디로 변경된 값을 서비스에 리턴 후, 리포지토리에 저장
    public Blog createpost(PostRequestDto requestDto) {
        return blogRepository.save(new Blog(requestDto));
    }

    //id 값의 게시글을 삭제
    public Long deletePost(Long id) {
        blogRepository.deleteById(id);
        return id;
    }

    //게시글 리스트 전체 GET
    public List<Blog> getblog() {
       return blogRepository.findAllByOrderByModifiedAtDesc();
    }

//    @Transactional
//    public Blog savePost(PostRequestDto requestDto) {
//        return blogRepository.save(new Blog(requestDto));
//    }

    //게시글 상세보기
    public ModelAndView getOneBlogAndComments(Long id, UserDetailsImpl userDetails) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new NullPointerException("")
        );
        List<Comment> comment = commentRepository.findAllByBlogIdOrderByCreatedAtDesc(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("detail"); // 뷰의 이름
        mv.addObject("data", blog);
        mv.addObject("commentList", comment);
        if (userDetails != null) {
            mv.addObject("user", userDetails.getUser().getUsername());
        } else
            mv.addObject("user", "visitor");
        return mv;
    }
    }
