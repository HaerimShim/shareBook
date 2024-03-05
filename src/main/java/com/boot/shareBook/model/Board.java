package com.boot.shareBook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String writer;
    private Long rating;
    private String content;
    private String comment;
    private Long recommend;
    private Long report;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String user;
}
