package spring.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import spring.dao.BoardDao;

@Controller
public class BoardController {
	@Autowired
	private BoardDao board;
	
	public void setFdao(BoardDao board) {
		this.board = board;
	}
	
	@RequestMapping(value="list.do") 
	public String listForm(@RequestParam(value="pageNum", defaultValue="1") int pageNum, Model model) {
		int pageCount = board.count();
		int countPerPage = 5;
		int pagePerGroup = 5;
		PageNavigator nav = new PageNavigator(countPerPage, pagePerGroup, pageNum, pageCount);
		
		List<ListDTO> list = board.list(pageNum);
		
		model.addAttribute("list", list);
		model.addAttribute("count", pageCount);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", 10);
		model.addAttribute("nav", nav);
		return "list";
	}
	
	@RequestMapping(value="write.do", method=RequestMethod.GET)
	public String writeForm() {
		return "writeForm";
	}
	
	@RequestMapping(value="update.do", method=RequestMethod.GET)
	public String updateForm(HttpServletRequest request, Model model) {
		int no = Integer.parseInt(request.getParameter("no")) ;
		WriteDTO select = board.select(no);
		model.addAttribute("select", select);
		return "writeForm";
	}
	
	@RequestMapping(value="delete.do", method=RequestMethod.GET)
	public String delete(HttpServletRequest request) {
		int no = Integer.parseInt(request.getParameter("no")) ;
		int success = board.delete(no);
		System.out.println(success);
		return "redirect:list.do";
	}
	
	@RequestMapping(value="writeAction.do", method=RequestMethod.POST)
	public String insert(@ModelAttribute("write") WriteDTO write, @RequestParam("photo") MultipartFile photo) {
		System.out.println("writeAction.do 실행");
		
		int success = 0;
		Date dt = new Date();
		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
		write.setWriteDate(sdft.format(dt));
		String o_name = photo.getOriginalFilename();
		System.out.println("o_name : "+ o_name );
		upload(photo, write);
		System.out.println(success);
		System.out.println("writeAction.do 완료");
		return "redirect:list.do";
	}
	
	@RequestMapping(value="updateAction.do", method=RequestMethod.POST)
	public String update(@ModelAttribute("update") WriteDTO update) {
		Date dt = new Date();
		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
		update.setWriteDate(sdft.format(dt));
		int success = board.update(update);
		System.out.println(success);
		return "redirect:list.do";
	}
	
	public void upload(MultipartFile photo, WriteDTO write) {
		int success = 0;
		String o_name = photo.getOriginalFilename();
		Random r = new Random();
		int i = r.nextInt(50);
		String s_name = i +"_"+ o_name;
		String filePath = "D:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/boardEx/download/"+ s_name;
		File newFile = new File(filePath);
		write.setFilePath(filePath);
		write.setFileName(s_name);
		
		try {
			if(write.getNo() == 0) {
				success = board.insert(write);
				photo.transferTo(newFile);
			} else {
				success = board.update(write);
				photo.transferTo(newFile);
			}
			System.out.println(success);
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
}
