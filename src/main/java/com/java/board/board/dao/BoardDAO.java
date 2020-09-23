package com.java.board.board.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.java.board.board.dto.BoardDTO;

public interface BoardDAO {
	public List<BoardDTO> selectBoardList(BoardDTO dto) throws DataAccessException;
	public void insertBoard(BoardDTO dto) throws DataAccessException;
	public BoardDTO selectBoardDetail(int no) throws DataAccessException;
	public void updateHits(int no) throws DataAccessException;
	public void updateBoard(BoardDTO dto) throws DataAccessException;
	public void deleteBoard(int no) throws DataAccessException;
	public int selectMaxNo() throws DataAccessException;
	public String selectFileName(int no) throws DataAccessException;
	public int articleDecimal(BoardDTO dto) throws DataAccessException;
}
