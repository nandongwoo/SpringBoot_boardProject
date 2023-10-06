package com.example.boardproject.controller;

import com.example.boardproject.dto.CommentDTO;
import com.example.boardproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;


    @PostMapping("/save")
    public ResponseEntity save(@RequestBody CommentDTO commentDTO) {
        System.out.println(commentDTO);
        List<CommentDTO> commentDTOList = commentService.save(commentDTO);
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

}
