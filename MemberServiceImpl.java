package kr.or.ddit.basic;

import java.util.List;


// Service단에 추가적인 작업을 통해서 Dao로 넘어간다.
// Service단에 기능을 활용하여 보조적인(암호화,메일발송...등등)기능들을 추가생성할수있다.



public class MemberServiceImpl implements IMemberService {
	//사용할 Dao객체변수를 선언한다.
	private IMemberDao memDao;
	
	public MemberServiceImpl() {
		memDao =new MemberDaoImpl();
	}
	@Override
	public int insertMember(MemberVO mv) {
		return memDao.insertMember(mv);
	}

	@Override
	public boolean getMember(String memId) {
		return memDao.getMember(memId);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		return memDao.getAllMemberList();
	}

	@Override
	public int updateMember(MemberVO mv) {
		return memDao.updateMember(mv);
	}

	@Override
	public int delectMember(String memId) {
		return memDao.delectMember(memId);
	}

	public List<MemberVO> getSearchMember(MemberVO mv){
		return memDao.getSearchMember(mv);
		
	}

}
