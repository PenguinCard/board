package com.java.board.board.service;

import java.util.List;

import com.java.board.board.dto.BoardDTO;

public interface BoardService {
	public List<BoardDTO> selectBoardList(BoardDTO dto) throws Exception;
	public void insertBoard(BoardDTO dto) throws Exception;
	public BoardDTO selectBoardDetail(int no) throws Exception;
	public void updateHits(int no) throws Exception;
	public void updateBoard(BoardDTO dto) throws Exception;
	public void deleteBoard(int no) throws Exception;
	public int selectMaxNo() throws Exception;
	public String selectFileName(int no) throws Exception;
	public int articleDecimal(BoardDTO dto) throws Exception;
}
