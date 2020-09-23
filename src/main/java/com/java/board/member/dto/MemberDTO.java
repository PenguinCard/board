package com.java.board.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	private int m_num;
	private String m_id;
	private String m_pw;
	private String m_name;
	private String m_phone;
	private String m_email;
}
