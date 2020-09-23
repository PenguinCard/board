package com.java.board.member.dao;

import org.springframework.dao.DataAccessException;

import com.java.board.member.dto.MemberDTO;

public interface MemberDAO {
	public void addMember(MemberDTO dto) throws DataAccessException;
	public boolean loginCheck(MemberDTO dto) throws DataAccessException;
	public MemberDTO memberInfo(MemberDTO dto) throws DataAccessException;
	public void delMember(int num) throws DataAccessException;
	public void modMember(MemberDTO dto) throws DataAccessException;
	public boolean idOverlapChk(String id) throws DataAccessException;
}
