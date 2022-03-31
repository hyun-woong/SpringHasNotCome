package com.sparta.springhasnotcome.Service;

import com.sparta.springhasnotcome.Dto.CommentRequestDto;
import com.sparta.springhasnotcome.Models.Comment;
import com.sparta.springhasnotcome.Repository.CommentRepository;
import com.sparta.springhasnotcome.Security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

   private final CommentRepository commentRepository;

    @Transactional
    public Long update(Long id, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        return comment.update(requestDto);
    }

    public Long deleteComment(Long id) {
        commentRepository.deleteById(id);
        return id;
    }

    public Comment createComment(CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        Comment comment = new Comment(requestDto, userDetails.getUser().getUsername());
        return commentRepository.save(comment);
    }
}
