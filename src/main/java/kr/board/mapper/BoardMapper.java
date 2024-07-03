package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.board.entity.Board;
import kr.board.entity.Criteria;
import kr.board.entity.Member;

@Mapper
public interface BoardMapper {
	public List<Board> getList(Criteria cri);
	public void insert(Board vo);
	public void insertSelectKey(Board vo);
	public Board read(int idx);
	public void update(Board vo);
	public void delete(int idx);
	public void replySeqUpdate(Board parent);
	public void replyInsert(Board vo);
	public int totalCount();
	public void boardCount(Board vo);
	public Member login(Member vo);
	
}
