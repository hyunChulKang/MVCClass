package kr.or.ddit.mvcwork;

import java.util.List;

public interface BoardService {

	public List<BoardVO> findout(BoardVO bv);
	
	public int delete(String no);
	
	public int repair(BoardVO bv);
	
	public List<BoardVO> selectview();
	
	public int write(BoardVO bv);
	
	public  boolean getSelect(String no);
}
