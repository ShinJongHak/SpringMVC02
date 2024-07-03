package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.bit.service.BoardService;
import kr.board.entity.Board;
import kr.board.entity.Criteria;
import kr.board.entity.PageMaker;
import kr.board.mapper.BoardMapper;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	 BoardService service;
	
	@GetMapping("/list")
	public String getList(Criteria cri, Model model) {
		List<Board> list=service.boardList(cri);
		// 객체바인딩
		model.addAttribute("list", list); // Model
		// 페이징 처리에 필요한 부분
		PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.totalCount());
		model.addAttribute("pageMaker", pageMaker);		
		return "list"; // View
 	}
	
	@GetMapping("/register")
	public String registerForm() {
		
		return "register";
		
	}
	
	@PostMapping("/register")
	public String register(Board vo, RedirectAttributes rttr) {
		service.register(vo);
		rttr.addFlashAttribute("result", vo.getIdx());
		return "redirect:/board/list";
		
	}
	
	@GetMapping("/get")
	public String get(@RequestParam("idx") int idx, Model model, @ModelAttribute("cri") Criteria cri) {
		Board vo = service.get(idx);
		model.addAttribute("vo", vo);
		return "get"; // /WEB-INF/views/board/get.jsp -> ${cri.page}
	}
	
	@GetMapping("/modify")
	public String modifyForm(@RequestParam("idx") int idx, Model model, @ModelAttribute("cri") Criteria cri) {
		Board vo = service.get(idx);
		model.addAttribute("vo", vo);
		return "modify";
		
	}
	
	@PostMapping("/modify")
	public String modify(Board vo) {
		service.modify(vo);
		
		return "redirect:/board/list";
		
	}
	
	@GetMapping("/remove")
	public String remove(int idx, Criteria cri, RedirectAttributes rttr) {
		service.remove(idx);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());		
		return "redirect:/board/list"; // ?page=2&perPageNum=5 
	}
	
	@GetMapping("/reply")
	public String reply(@RequestParam("idx") int idx, Model model) {
		Board vo = service.get(idx);
		model.addAttribute("vo", vo);
		return "reply";
		
	}
	
	@PostMapping("/reply")
	public String reply(Board vo) {
		service.replyProcess(vo);
		
		return "redirect:/board/list";
		
	}
	

}
