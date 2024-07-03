package kr.bit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.board.entity.Board;
import kr.board.entity.Criteria;
import kr.board.entity.Member;
import kr.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardMapper mapper;

	@Override
	public List<Board> boardList(Criteria cri) {
		List<Board> list= mapper.getList(cri);

		return list;
	}

	@Override
	public Member login(Member vo) {
		Member mvo = mapper.login(vo);
		return mvo;
	}

	@Override
	public void register(Board vo) {
		mapper.insertSelectKey(vo);
		
	}

	@Override
	public Board get(int idx) {
		Board vo =mapper.read(idx);
		return vo;
	}

	@Override
	public void modify(Board vo) {
		mapper.update(vo);
		
	}

	@Override
	public void remove(int idx) {
		mapper.delete(idx);
		
	}

	@Override
	public void replyProcess(Board vo) {
		// - 답글만들기
		// 1. 부모글(원글)의 정보를 가져오기(vo->idx)
		Board parent=mapper.read(vo.getIdx());
		System.out.println(vo);
		// 2. 부모글의 boardGroup의 값을->답글(vo)정보에 저장하기
		vo.setBoardGroup(parent.getBoardGroup());
		// 3. 부모글의 boardSequence의 값을 1을 더해서 ->답글(vo)정보에 저장하기
		vo.setBoardSequence(parent.getBoardSequence()+1);
		// 4. 부모글의 boardLevel의 값을 1을 더해서 ->답글(vo)정보에 저장하기
        vo.setBoardLevel(parent.getBoardLevel()+1);
        // 5. 같은 boardGroup에 있는 글 중에서
        //    부모글의 boardSequence보다 큰 값들을 모두 1씩 업데이트하기
        mapper.replySeqUpdate(parent);
        System.out.println();
		// 6. 답글(vo)을 저정하기
        mapper.replyInsert(vo);
	}

	@Override
	public int totalCount() {
		// TODO Auto-generated method stub
		return mapper.totalCount();
	}	

}
