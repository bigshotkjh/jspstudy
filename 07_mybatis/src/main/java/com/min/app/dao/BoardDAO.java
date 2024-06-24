package com.min.app.dao;

import java.util.List;
import java.util.Map;

import com.min.app.dto.BoardDTO;

public interface BoardDAO {
  int getBoardCount();
  List<BoardDTO> getBoardList(Map<String, Object> params);
  BoardDTO getBoardByNo(int boardNo);
  int insertBoard(BoardDTO board);
  int updateBoard(BoardDTO board);
  int deleteBoard(int boardNo);
}
