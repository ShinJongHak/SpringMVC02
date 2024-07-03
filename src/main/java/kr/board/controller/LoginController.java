package kr.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.bit.service.BoardService;
import kr.board.entity.Member;

@Controller
@RequestMapping("/login/*")
public class LoginController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("/loginProcess")
	public String loginProcess(Member vo, HttpSession session) {
		Member mvo = boardService.login(vo);
		if(vo != null) {
			session.setAttribute("mvo", mvo);
		}
		
		return "redirect:/board/list";
		
	}
	
	@RequestMapping("/logoutProcess")
	public String logoutProcess(HttpSession session) {
		session.invalidate();
		return "redirect:/board/list";
		
	}

}
