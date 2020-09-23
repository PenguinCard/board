package com.java.board.board.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.java.board.board.dto.BoardDTO;
import com.java.board.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static int startPage=1;
	private static int currentPage=1;
	private static int endPage=1;
	
	private static String PATH = "C:\\pboard\\";
	
	@Autowired
	private BoardService service;
	
	@RequestMapping("/")
	public ModelAndView boardList(BoardDTO dto, HttpSession sess) throws Exception {
		endPage=service.articleDecimal(dto);
		if(dto.getCount() < 1 || dto.getCount() > endPage) dto.setCount(1);
		currentPage=dto.getCount();
		startPage=((currentPage-1)/5)*5 + 1;
		sess.setAttribute("startPage", startPage);
		sess.setAttribute("currentPage", currentPage);
		sess.setAttribute("endPage", endPage);
		ModelAndView mav=new ModelAndView("board/boardList");
		
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
		
		mav.addObject("boardList", service.selectBoardList(dto));
		return mav;
	}
	
	@RequestMapping("/writeForm")
	public ModelAndView writeForm(@RequestParam(value="parentNo", defaultValue="0") int parentNo) {
		ModelAndView mav=new ModelAndView("board/writeForm");
		mav.addObject("parentNo", parentNo);
		return mav;
	}
	
	// Write(Reply) Process
	@RequestMapping(value="/write.do", method=RequestMethod.POST)
	public ModelAndView write(BoardDTO dto,	@RequestParam("File") MultipartFile file, HttpSession sess) throws Exception {
		ModelAndView mav=new ModelAndView("redirect:/board/");
		
		String origin=file.getOriginalFilename(); //requestparam을 통해서 파일이름을 가져옴
		String path=PATH +service.selectMaxNo();  //경로생성 및 폴더지정이 되어야 file이 testfile로 변환된다.
		
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
		dto.setImageFileName(origin);
		service.insertBoard(dto);
		return mav;
	}
	
	@RequestMapping("/detailBoard")
	public ModelAndView detail(BoardDTO dto) throws Exception {
		ModelAndView mav=new ModelAndView("board/detail");
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
	
	@RequestMapping(value="/shiftLeft", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> shiftLeft(){
		Map<String, Object> map=new HashMap<>();
		if(startPage-5>0) {
			startPage-=5;
		}
		if(endPage >= startPage+4) {
			for(int i=0; i<5; i++) {
				map.put(Integer.toString(i), startPage+i);
			}
		} else {
			for(int i=0; i<endPage%5; i++) {
				map.put(Integer.toString(i), startPage+i);
			}
		}
		return map;
	}
	
	@RequestMapping(value="/shiftRight", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> shiftRight(){
		Map<String, Object> map=new HashMap<>();
		if(startPage+4<endPage) {
			startPage+=5;
		}	
	
		if(endPage >= startPage+4) {
			for(int i=0; i<5; i++) {
				map.put(Integer.toString(i), startPage+i);
			}
		} else {
			for(int i=0; i<endPage%5; i++) {
				map.put(Integer.toString(i), startPage+i);
			}
		}
		return map;
	}
}
