package kr.or.ddit.mvcwork;

import java.util.List;

public class BoardServiceImpl implements BoardService {
	private BoardDao bdDao;
	
	 public BoardServiceImpl() {
		bdDao =new BoardDaoImpl();
	}
	
	@Override
	public List<BoardVO> findout(BoardVO bv) {
		return bdDao.findout(bv);
	}

	@Override
	public int delete(String no) {
		return bdDao.delete(no);
	}

	@Override
	public int repair(BoardVO bv) {
		return bdDao.repair(bv);
	}

	@Override
	public List<BoardVO> selectview() {
		return bdDao.selectview( );
	}

	@Override
	public int write(BoardVO bv) {
		return bdDao.write(bv);
	}

	@Override
	public boolean getSelect(String no) {
		return bdDao.getSelect(no);
	}

}
