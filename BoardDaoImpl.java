package kr.or.ddit.mvcwork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.util.DBUtil2;
import kr.or.ddit.util.DBUtil3;

public class BoardDaoImpl implements BoardDao {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public List<BoardVO> findout(BoardVO bv) {
	List<BoardVO> bvList =new ArrayList<>();
		try {
			
			conn =DBUtil2.getConnetion();
			String sql = "select * from jdbc_board where board_content like '%'||? "
							+ " OR board_content like ?||'%' OR board_content like '%'||?||'%'"
							+ " OR board_title like ?||'%' OR board_title like '%'||? "
							+ " OR board_title like '%'||?||'%' ";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoard_content());
			pstmt.setString(2, bv.getBoard_content());
			pstmt.setString(3, bv.getBoard_content());
			pstmt.setString(4, bv.getBoard_title());
			pstmt.setString(5, bv.getBoard_title());
			pstmt.setString(6, bv.getBoard_title());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardVO bv1 = new BoardVO();
				bv1.setBoard_no(rs.getString("board_no"));
				bv1.setBoard_title(rs.getString("board_title"));
				bv1.setBoard_writer(rs.getString("board_writer"));
				bv1.setBoard_date(rs.getString("board_date"));
				bv1.setBoard_content(rs.getString("board_content"));
				
				bvList.add(bv1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	
		return bvList;
	}

	@Override
	public int delete(String no) {
		int cnt =0;
		try {
			conn =DBUtil3.getConnetion();
			String sql ="delete from jdbc_board where board_no = ?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, no);
			
			cnt =pstmt.executeUpdate();
			if(cnt >0) {
				System.out.println("글삭제가 완료되었습니다.");
			}else {
				System.out.println("글삭제에 실패했습니다.");
			}
		}catch(SQLException e) {
			System.out.println("글삭제에 실패했습니다.");
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return cnt;
	}

	@Override
	public int repair(BoardVO bv) {
		int cnt=0;
		try {
			conn= DBUtil3.getConnetion();
			String sql ="update jdbc_board set board_title = ?, board_content= ? where board_no = ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoard_title());
			pstmt.setString(2, bv.getBoard_content());
			pstmt.setString(3, bv.getBoard_no());
			cnt =pstmt.executeUpdate();
			if(cnt >0) {
				System.out.println("정상적으로 글이 수정되었습니다.");
			}else {
				System.out.println("글 수정에 실패했습니다.");
			}
		}catch(SQLException e) {
			System.out.println("글 수정에 실패했습니다.");
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> selectview() {
	List<BoardVO> bvList =new ArrayList<BoardVO>();
		try {
			conn=DBUtil3.getConnetion();
			stmt=conn.createStatement();
			String sql ="select * from jdbc_board ORDER BY board_no desc";
			rs =stmt.executeQuery(sql);
			while(rs.next()){
				BoardVO bv =new BoardVO();
				bv.setBoard_no(rs.getString("board_no"));
				bv.setBoard_title(rs.getString("board_title"));
				bv.setBoard_writer(rs.getString("board_writer"));
				bv.setBoard_date(rs.getString("board_date"));
				bv.setBoard_content(rs.getString("board_content"));
				
				bvList.add(bv);
			}
		} catch (SQLException e) {
			System.out.println("selectview에서 조회실패");
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return bvList;
	}

	@Override
	public int write(BoardVO bv) {
		int cnt =0;
		try {
			conn =DBUtil3.getConnetion();
			String sql ="insert into jdbc_board (board_no, board_title, board_writer, board_date, board_content)"
						+" values (board_seq.nextval, ?, ?, sysdate,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoard_title());
			pstmt.setString(2, bv.getBoard_writer());
			pstmt.setString(3, bv.getBoard_content());
			cnt= pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return cnt;
	}

	private void disConnect() {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public boolean getSelect(String no) {
		boolean check =false;
		try {
			conn=DBUtil3.getConnetion();
			String sql ="select count(*) cnt from jdbc_board where board_no = ? ";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			int cnt=0;
			if(rs.next()) {
				cnt= rs.getInt("cnt");
			}
			if(cnt>0) {
				check =true;
			}
		}catch(SQLException e) {
			System.out.println("조회실패");
			e.printStackTrace();
			check=false;
			
		}finally {
			disConnect();
		}
		return check;
	}

	
	

}
