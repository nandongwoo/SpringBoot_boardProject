package com.example.boardproject.controller;

import com.example.boardproject.dto.BoardDTO;
import com.example.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/save")
    public String save() {
        return "/boardPages/boardSave";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO){
        boardService.save(boardDTO);
        return "redirect:/board";
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("boardList", boardService.findAll());
        return "/boardPages/boardList";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id")Long id,
                         Model model){
        try {
            boardService.increaseHits(id);
            model.addAttribute("boardDetail", boardService.findById(id));
            System.out.println(boardService.findById(id));
            return "/boardPages/boardDetail";
        }catch (NoSuchElementException e) {
            return "/boardPages/NotFound";
        }
    }


}
















