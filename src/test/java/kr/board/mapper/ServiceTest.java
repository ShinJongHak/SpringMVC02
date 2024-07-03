package kr.board.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.bit.service.BoardService;
import kr.board.entity.Board;
import kr.board.entity.Criteria;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ServiceTest {
	
	@Autowired
	BoardService service;
	
	@Test
	public void getListTest() {
		Criteria cri = new Criteria();
		List<Board> list= service.boardList(cri);
		
		for(Board vo : list) {
			System.out.println(vo);
			log.info(vo);
		}
	}

}
