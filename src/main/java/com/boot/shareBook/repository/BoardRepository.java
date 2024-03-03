package com.boot.shareBook.repository;

import com.boot.shareBook.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository <Board, Long> {

}
