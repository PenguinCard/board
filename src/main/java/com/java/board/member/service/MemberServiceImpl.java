package com.java.board.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.board.member.dao.MemberDAO;
import com.java.board.member.dto.MemberDTO;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO dao;
	
	@Override
	public void addMember(MemberDTO dto) throws Exception {
		dao.addMember(dto);
	}

	@Override
	public boolean loginCheck(MemberDTO dto) throws Exception {
		return dao.loginCheck(dto);
	}
	
	@Override
	public MemberDTO memberInfo(MemberDTO dto) throws Exception {
		return dao.memberInfo(dto);
	}

	@Override
	public void delMember(int num) throws Exception {
		dao.delMember(num);
	}

	@Override
	public void modMember(MemberDTO dto) throws Exception {
		dao.modMember(dto);
	}

	@Override
	public boolean idOverlapChk(String id) throws Exception {
		return dao.idOverlapChk(id);
	}
}
