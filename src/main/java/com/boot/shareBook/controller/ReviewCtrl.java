package com.boot.shareBook.controller;

import com.boot.shareBook.model.Board;
import com.boot.shareBook.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewCtrl {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/list")
    public String getReviewList(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "reviewList";
    }
}
