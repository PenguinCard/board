package com.java.board.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ShowController {
	
	private static String PATH = "file:///C:/pboard";
	
	@RequestMapping("/show")
	public void show(@RequestParam("no")int no, @RequestParam("img") String img, 
			HttpServletResponse response) throws Exception {
		
		response.setCharacterEncoding("utf-8");
		OutputStream os=response.getOutputStream();

		String route=PATH+"/"+no+"/"+img;
		
		URL url=new URL(route);
		
		InputStream is = url.openStream();
		FileCopyUtils.copy(is, os);
	}
	
	@RequestMapping("/download.do")
	@ResponseStatus(value = HttpStatus.OK)
	public void download(@RequestParam("no")int no, @RequestParam("img") String img
			,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
	    response.setHeader("Content-Disposition", "attachment;fileName=" + img); // 파일 다운로드
		OutputStream os=response.getOutputStream();
		
		String route=PATH+"/"+no+"/"+img;//가져오는 파일 루트
		
		URL url=new URL(route);//그 루트로 url생성
 		
		InputStream is = url.openStream();//생성된 루트로 파일 받아옴
		FileCopyUtils.copy(is, os); //먹고, 뱉고
	}
	
}
