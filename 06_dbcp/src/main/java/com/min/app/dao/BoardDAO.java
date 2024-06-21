package com.min.app.dao;

import java.util.List;
import java.util.Map;

import com.min.app.dto.BoardDTO;

public interface BoardDAO {
  List<BoardDTO> getBoardList(Map<String, Object> params);
  int getBoardCount();
  BoardDTO getBoardByNo(int board_no);
  int insertBoard(BoardDTO board);
  int updateBoard(BoardDTO board);
  int deleteBoard(int board_no);
  void close() throws Exception;
}
