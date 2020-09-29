package com.java.board.board.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.java.board.board.dto.BoardDTO;
import com.java.board.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	// 페이징 시작페이지, 현재페이지, 마지막페이지
	private static int startPage=1;
	private static int currentPage=1;
	private static int endPage=1;
	
	// 파일저장 공통 경로
	private static String PATH = "C:\\pboard\\";
	
	@Autowired
	private BoardService service;
	
	// select Board List (+ search Keyword)
	@RequestMapping("/")
	public ModelAndView boardList(BoardDTO dto, HttpSession sess) throws Exception {
		// 마지막 페이지는 db에서 (게시글 갯수)/10 을 올림하여 가져옴
		endPage=service.articleDecimal(dto);
		// 입력받은 parameter가 1 ~ 최대페이지를 벗어날 경우 처리하는 if문 -> 첫 페이지로 돌아오게함
		if(dto.getCount() < 1 || dto.getCount() > endPage) dto.setCount(1);
		// 현재페이지는 입력받은 parameter 값
		currentPage=dto.getCount();
		// 시작페이지는 현재페이지-1(5의 배수에서 예외가 발생함)에 5의 배수로 나눈 뒤 5를 곱한 값에 1을 더함
		startPage=((currentPage-1)/5)*5 + 1;
		// session에 시작페이지, 현재페이지, 마지막페이지 실음
		sess.setAttribute("startPage", startPage);
		sess.setAttribute("currentPage", currentPage);
		sess.setAttribute("endPage", endPage);
		ModelAndView mav=new ModelAndView("board/boardList");
		/* 입력받은 parameter의 null값 과 nonspace를 체크하여 값이 있는 경우
		 * if 문에 따라 다음페이지에 item과 value를 넘김
		 * 1. title
		 * 2. writer
		 * 3. contents 
		 */ 
		if(dto.getTitle()!=""&&dto.getTitle()!=null) {
			mav.addObject("item", "title");
			mav.addObject("value", dto.getTitle());
		}
		if(dto.getWriter()!=""&&dto.getWriter()!=null) {
			mav.addObject("item", "writer");
			mav.addObject("value", dto.getWriter());
		}
		if(dto.getContents()!=""&&dto.getContents()!=null) {
			mav.addObject("item", "contents");
			mav.addObject("value", dto.getContents());
		}
		/* dto에 들어있는 항목으로 검색 */
		mav.addObject("boardList", service.selectBoardList(dto));
		return mav;
	}
	
	// Write(Reply)Form : mapping(parentNo 번호가 있을 경우 @RequestParam 을 통해 입력을 받고, 없을 시엔 기본 값을 0으로 설정) 
	@RequestMapping("/writeForm")
	public ModelAndView writeForm(@RequestParam(value="parentNo", defaultValue="0") int parentNo) {
		ModelAndView mav=new ModelAndView("board/writeForm");
		// 다음 페이지에 parentNo을 넘김
		mav.addObject("parentNo", parentNo);
		return mav;
	}
	
	// Write(Reply) Process
	@RequestMapping(value="/write.do", method=RequestMethod.POST)
	public ModelAndView write(BoardDTO dto,	@RequestParam("File") MultipartFile file, HttpSession sess) throws Exception {
		// Form에서 BoardDTO, @RequestParam, Session을 통해 정보를 가져옴 
		ModelAndView mav=new ModelAndView("redirect:/board/");
		// MultiPartfile에서 통해서 원래 파일이름을 가져옴
		String origin=file.getOriginalFilename();
		// 저장위치 : 공통경로 + 게시글번호
		String path=PATH +service.selectMaxNo();
		// 저장위치 디렉토리 생성 + 파일 저장
		try {
			File folder=new File(path);
			if(!folder.exists()) {
				try {
					folder.mkdirs(); //폴더생성
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(!file.isEmpty()&&file.getSize()!=0) {
				File testFile=new File(path +"\\"+ origin);
				file.transferTo(testFile);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		// dto에 이미지 파일 이름을 설정
		dto.setImageFileName(origin);
		service.insertBoard(dto);
		return mav;
	}
	
	// 상세 페이지
	@RequestMapping("/detailBoard")
	public ModelAndView detail(BoardDTO dto) throws Exception {
		ModelAndView mav=new ModelAndView("board/detail");
		// 게시글의 조회수 1을 증가시킴
		service.updateHits(dto.getArticleNo());
		mav.addObject("detail", service.selectBoardDetail(dto.getArticleNo()));
		return mav;
	}
	
	@RequestMapping("/updateForm/{no}")
	public ModelAndView updateForm(@PathVariable("no") int no) throws Exception {
		ModelAndView mav=new ModelAndView("board/updateForm");
		mav.addObject("detail", service.selectBoardDetail(no));
		return mav;
	}
	
	// Update Process
	@RequestMapping("/update.do")
	public ModelAndView update(BoardDTO dto, @RequestParam("File") MultipartFile file) throws Exception {
		String getFileName=service.selectFileName(dto.getArticleNo());
		String origin=file.getOriginalFilename();
		String path=PATH+dto.getArticleNo();
		if(!origin.equals(getFileName)) {
			try {
				File pathFile=new File(path+"\\"+getFileName);
				if(pathFile.isFile()) {
					pathFile.delete();
				}
				if(!file.isEmpty()&&file.getSize()!=0) {
					File testFile=new File(path +"\\"+ origin);
					file.transferTo(testFile);
				}
			} catch(Exception e) { }
		}
		dto.setImageFileName(origin);
		service.updateBoard(dto);
		return new ModelAndView("redirect:/board/");
	}

	// Delete Process
	@RequestMapping("/delete.do/{no}")
	public ModelAndView delete(@PathVariable("no") int no, HttpSession sess) throws Exception {
		ModelAndView mav=new ModelAndView("redirect:/board/");
		String fileName=service.selectFileName(no);
		File folder=new File(PATH+no);
		if(fileName!=""&&fileName!=null) {
			String path=PATH+no+"\\"+fileName;
			File file=new File(path);
			file.delete();
		}
		folder.delete();
		service.deleteBoard(no);
		return mav;
	}
}
