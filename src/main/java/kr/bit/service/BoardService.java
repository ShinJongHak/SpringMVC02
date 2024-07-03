package kr.bit.service;

import java.util.List;

import kr.board.entity.Board;
import kr.board.entity.Criteria;
import kr.board.entity.Member;

public interface BoardService {
	public List<Board> boardList(Criteria cri);
	public Member login(Member vo);
	public void register(Board vo);
	public Board get(int idx);
	public void modify(Board vo);
	public void remove(int idx);
	public void replyProcess(Board vo);
	public int totalCount();
}
