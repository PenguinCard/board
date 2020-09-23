package com.java.board.member.service;

import com.java.board.member.dto.MemberDTO;

public interface MemberService {
	public void addMember(MemberDTO dto) throws Exception;
	public boolean loginCheck(MemberDTO dto) throws Exception;
	public MemberDTO memberInfo(MemberDTO dto) throws Exception;
	public void delMember(int num) throws Exception;
	public void modMember(MemberDTO dto) throws Exception;
	public boolean idOverlapChk(String id) throws Exception;
}
