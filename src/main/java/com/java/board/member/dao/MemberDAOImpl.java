package com.java.board.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.java.board.member.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sess;
	
	@Override
	public void addMember(MemberDTO dto) throws DataAccessException {
		sess.insert("mappers.member.addMember", dto);
	}

	@Override
	public boolean loginCheck(MemberDTO dto) throws DataAccessException {
		return sess.selectOne("mappers.member.loginCheck", dto);
	}

	@Override
	public MemberDTO memberInfo(MemberDTO dto) throws DataAccessException {
		return sess.selectOne("mappers.member.memberInfo", dto);
	}

	@Override
	public void delMember(int num) throws DataAccessException {
		sess.delete("mappers.member.delMember", num);
	}

	@Override
	public void modMember(MemberDTO dto) throws DataAccessException {
		sess.update("mappers.member.modMember", dto);
	}

	@Override
	public boolean idOverlapChk(String id) throws DataAccessException {
		// TODO Auto-generated method stub
		return sess.selectOne("mappers.member.idOverlapChk", id);
	}
}
