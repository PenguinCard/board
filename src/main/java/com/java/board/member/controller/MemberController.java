package com.java.board.member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.board.member.dto.MemberDTO;
import com.java.board.member.service.MemberService;
import com.java.board.utils.Encryption;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	// 공통된 이름을 가진 주소들을 @PathVariable 통해 하나로 묶어서 mapping 
	@RequestMapping("/{str}Form")	
	public String memberForm(@PathVariable("str") String str) {
		return "member/"+str+"Form";
	}
	
	// ajax를 이용한 login
	@RequestMapping(value="/login.do", method=RequestMethod.POST, produces="application/text; charset=utf-8")
	@ResponseBody
	public String login(MemberDTO dto, HttpSession sess) throws Exception{
		// 전송받은 비밀번호를 암호화
		dto.setM_pw(Encryption.sha256(dto.getM_pw()));
		// 로그인 
		if(service.loginCheck(dto)) {
			sess.setAttribute("memInfo", service.memberInfo(dto));
			return "true";
		} else {
			return "false";
		}
	}
	
	// 로그아웃
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpSession sess) {
		ModelAndView mav=new ModelAndView("redirect:/board/");
		sess.removeAttribute("memInfo");
		return mav;
	}
	
	// 회원가입
	@RequestMapping("/addMember.do")
	public ModelAndView addMember(@RequestParam Map<String, String> map) throws Exception {
		ModelAndView mav=new ModelAndView("redirect:/board/");
		String id=map.get("id");
		// pw 암호화
		String pw=Encryption.sha256(map.get("pw"));
		String name=map.get("name");
		String phone=map.get("phone1")+"-"+map.get("phone2")+"-"+map.get("phone3");
		String email=map.get("email1")+"@"+map.get("email2");
		MemberDTO dto=new MemberDTO(0, id, pw, name, phone, email);
		service.addMember(dto);
		return mav;
	}
	
	// 계정탈퇴
	@RequestMapping("/delete.do")
	public ModelAndView delMember(@RequestParam("num") int num, HttpSession sess) throws Exception {
		ModelAndView mav=new ModelAndView("redirect:/board/");
		// session에 저장된 회원정보를 지움
		sess.removeAttribute("memInfo");
		// db에서 회원정보 삭제
		service.delMember(num);
		return mav;
	}
	
	// 정보수정
	@RequestMapping("/update.do")
	public ModelAndView modMember(@RequestParam Map<String, String> map, HttpSession sess) throws Exception {
		// @RequestParam 을 통해 input값들을 받아옴
		ModelAndView mav=new ModelAndView("redirect:/board/");
		String phone=map.get("phone1")+"-"+map.get("phone2")+"-"+map.get("phone3");
		String email=map.get("email1")+"@"+map.get("email2");
		MemberDTO dto=new MemberDTO(0, map.get("id"), Encryption.sha256(map.get("pw")), null, phone, email);
		// db update
		service.modMember(dto);
		// session update
		sess.setAttribute("memInfo", service.memberInfo(dto));
		return mav;
	}
	
	// ajax를 이용한 id 중복체크
	@RequestMapping(value="/idOverlapCheck", method=RequestMethod.POST, produces="application/text; charset=utf-8")
	@ResponseBody
	public String idOverlapCheck(String idVal) throws Exception{
		if(service.idOverlapChk(idVal)) {
			return "중복된 아이디입니다";
		} else {
			return "사용가능한 아이디입니다";
		}
	}
}
