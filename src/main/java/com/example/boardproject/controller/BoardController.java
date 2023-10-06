package com.example.boardproject.controller;

import com.example.boardproject.dto.BoardDTO;
import com.example.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException{
        boardService.save(boardDTO);
        return "redirect:/board";
    }

    /*
         rest api
         /board/10 => 10번글
         /board/20 => 20번글
         /member/5 => 5번회원

         3페이지에 있는 15번글
         /board/3/15
         /board/15?page=3
     */
    @GetMapping
    public String findAll(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                          @RequestParam(value = "type", required = false, defaultValue = "boardTitle") String type,
                          @RequestParam(value = "q", required = false, defaultValue = "") String q) {
        Page<BoardDTO> boardDTOList = boardService.findAll(page, type, q);

        int blockLimit = 3;
        int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < boardDTOList.getTotalPages()) ? startPage + blockLimit - 1 : boardDTOList.getTotalPages();

        model.addAttribute("boardList", boardDTOList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("type", type);
        model.addAttribute("q", q);

        return "boardPages/boardList";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id,
                         @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                         @RequestParam(value = "type", required = false, defaultValue = "boardTitle") String type,
                         @RequestParam(value = "q", required = false, defaultValue = "") String q,
                         Model model) {

            try {
                boardService.increaseHits(id);
                model.addAttribute("boardDetail", boardService.findById(id));
                model.addAttribute("page", page);
                model.addAttribute("type", type);
                model.addAttribute("q", q);
                return "/boardPages/boardDetail";
            } catch (NoSuchElementException e) {
                return "/boardPages/NotFound";
            }

    }

    // 1. 주소로 요청
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return "redirect:/board";
    }
    // 2. axios로 요청
//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteByAxios(@PathVariable("id") Long id){
//        boardService.delete(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "/boardPages/boardUpdate";
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody BoardDTO boardDTO) {
        boardService.update(boardDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
















