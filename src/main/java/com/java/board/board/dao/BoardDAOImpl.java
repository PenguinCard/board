package com.java.board.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.java.board.board.dto.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	private SqlSession sess;

	@Override
	public List<BoardDTO> selectBoardList(BoardDTO dto) throws DataAccessException {
		return sess.selectList("mappers.board.selectBoardList", dto);
	}

	@Override
	public void insertBoard(BoardDTO dto) throws DataAccessException {
		sess.insert("mappers.board.insertBoard", dto);
	}

	@Override
	public BoardDTO selectBoardDetail(int no) throws DataAccessException {
		return sess.selectOne("mappers.board.selectBoardDetail", no);
	}

	@Override
	public void updateHits(int no) throws DataAccessException {
		sess.update("mappers.board.updateHits", no);
	}

	@Override
	public void updateBoard(BoardDTO dto) throws DataAccessException {
		sess.update("mappers.board.updateBoard", dto);
	}

	@Override
	public void deleteBoard(int no) throws DataAccessException {
		sess.delete("mappers.board.deleteBoard", no);
	}

	@Override
	public int selectMaxNo() throws DataAccessException {
		return sess.selectOne("mappers.board.selectMaxNo");
	}
	
	@Override
	public String selectFileName(int no) throws DataAccessException {
		return sess.selectOne("mappers.board.selectFileName", no);
	}

	@Override
	public int articleDecimal(BoardDTO dto) throws DataAccessException {
		return sess.selectOne("mappers.board.articleDecimal", dto);
	}
}
