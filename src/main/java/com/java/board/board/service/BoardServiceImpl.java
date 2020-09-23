package com.java.board.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.board.board.dao.BoardDAO;
import com.java.board.board.dto.BoardDTO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO dao;
	
	@Override
	public List<BoardDTO> selectBoardList(BoardDTO dto) throws Exception {
		return dao.selectBoardList(dto);
	}

	@Override
	public void insertBoard(BoardDTO dto) throws Exception {
		dao.insertBoard(dto);
	}

	@Override
	public BoardDTO selectBoardDetail(int no) throws Exception {
		return dao.selectBoardDetail(no);
	}

	@Override
	public void updateHits(int no) throws Exception {
		dao.updateHits(no);
	}

	@Override
	public void updateBoard(BoardDTO dto) throws Exception {
		dao.updateBoard(dto);
	}

	@Override
	public void deleteBoard(int no) throws Exception {
		dao.deleteBoard(no);
	}

	@Override
	public int selectMaxNo() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectMaxNo();
	}
	
	@Override
	public String selectFileName(int no) throws Exception {
		return dao.selectFileName(no);
	}

	@Override
	public int articleDecimal(BoardDTO dto) throws Exception {
		return dao.articleDecimal(dto);
	}
}
