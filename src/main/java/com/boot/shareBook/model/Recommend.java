package com.boot.shareBook.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
public class Recommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long reviewId;
    private long loginId;
    private long recommendCnt;
}
