package kr.board.mapper;

import java.lang.ProcessHandle.Info;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.board.entity.Board;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MapperTest {
	
	@Autowired
	BoardMapper mapper;
	
	@Test
	public void getListTest() {
		
		List<Board> list= mapper.getList(null);
		
		for(Board vo : list) {
			System.out.println(vo);
			log.info(vo);
		}
	}
	
	@Test
	public void insertTest() {
		 Board vo=new Board();
		 vo.setMemID("bit03");
		 vo.setTitle("C");
		 vo.setContent("새로작성한 글");
		 vo.setWriter("홍길동");
		 //boardMapper.insert(vo);
		  mapper.insertSelectKey(vo);
		log.info(vo);
	 }
	}

