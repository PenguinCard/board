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

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@RequestMapping("/{str}Form")	
	public String memberForm(@PathVariable("str") String str) {
		return "member/"+str+"Form";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST, produces="application/text; charset=utf-8")
	@ResponseBody
	public String login(MemberDTO dto, HttpSession sess) throws Exception{
		if(service.loginCheck(dto)) {
			sess.setAttribute("memInfo", service.memberInfo(dto));
			return "true";
		} else {
			return "false";
		}
	}
	
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpSession sess) {
		ModelAndView mav=new ModelAndView("redirect:/board/");
		sess.removeAttribute("memInfo");
		return mav;
	}
	
	@RequestMapping("/addMember.do")
	public ModelAndView addMember(@RequestParam Map<String, String> map) throws Exception {
		ModelAndView mav=new ModelAndView("redirect:/board/");
		String id=map.get("id");
		String pw=map.get("pw");
		String name=map.get("name");
		String phone=map.get("phone1")+"-"+map.get("phone2")+"-"+map.get("phone3");
		String email=map.get("email1")+"@"+map.get("email2");
		MemberDTO dto=new MemberDTO(0, id, pw, name, phone, email);
		service.addMember(dto);
		return mav;
	}
	
	@RequestMapping("/delete.do")
	public ModelAndView delMember(@RequestParam("num") int num, HttpSession sess) throws Exception {
		ModelAndView mav=new ModelAndView("redirect:/board/");
		sess.removeAttribute("memInfo");
		service.delMember(num);
		return mav;
	}
	
	@RequestMapping("/update.do")
	public ModelAndView modMember(@RequestParam Map<String, String> map, HttpSession sess) throws Exception {
		ModelAndView mav=new ModelAndView("redirect:/board/");
		String phone=map.get("phone1")+"-"+map.get("phone2")+"-"+map.get("phone3");
		String email=map.get("email1")+"@"+map.get("email2");
		MemberDTO dto=new MemberDTO(0, map.get("id"), map.get("pw"), null, phone, email);
		service.modMember(dto);
		sess.setAttribute("memInfo", service.memberInfo(dto));
		return mav;
	}
	
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
