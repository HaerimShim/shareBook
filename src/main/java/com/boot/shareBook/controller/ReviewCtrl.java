package com.boot.shareBook.controller;

import com.boot.shareBook.model.Board;
import com.boot.shareBook.repository.BoardRepository;
import com.boot.shareBook.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewCtrl {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String getReviewList(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "reviewList";
    }

    @GetMapping("/write")
    public String getReviewWrite(Model model) {
        model.addAttribute("board", new Board());
        return "reviewWrite";
    }

    @PostMapping("/write")
    public String postReviewWrite(@ModelAttribute Board board, BindingResult bindingResult) {
        boardValidator.validate(board, bindingResult);
        if(bindingResult.hasErrors()){
            return "reviewWrite";
        }
        boardRepository.save(board);
        return "redirect:/review/list";
    }

    @GetMapping("/read")
    public String getReviewRead(Model model, Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        model.addAttribute("board", board );
        return "reviewRead";
    }

    @GetMapping("/delete")
    public String deleteReview(Model model, Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        boardRepository.delete(board);
        return "redirect:/review/list";
    }
}
