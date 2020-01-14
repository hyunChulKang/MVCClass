package kr.or.ddit.mvcwork;

import java.util.List;
import java.util.Scanner;

public class BoarMain {
	private Scanner scan = new Scanner(System.in); 
	private BoardService bs;
	int pk;
	
	public BoarMain() {
		bs = new BoardServiceImpl();
	}
	public static void main(String[] args) {
		BoarMain bm =new BoarMain();
		bm.start();
	}

	public void start() {
		System.out.println();
		System.out.println("================게시판=====================");
		
	do {
		System.out.println("1.글작성\t2.글목록\t3.글수정\t4.글삭제\t5.글검색\t0.게시판종료");
		pk=Integer.parseInt(scan.nextLine());
		switch(pk) {

		case 1 :
			write();
			break;
		case 2 : 
			selectview();
			break;
		case 3 : 
			repair();
			break;
		case 4 : 
			delete();
			break;
		case 5 : 
			findout();
			break;
		case 0 :
			System.out.println("게시판종료");
			break;
		default:
			System.out.println("다시입력해주세요.");
		}
	}while(pk !=0);
	
		}

	private void findout() {
		System.out.println();
		System.out.println("검색할 키워드를 입력하세요.");
		String keyword =scan.nextLine();
		
		
		BoardVO bv = new BoardVO();
		bv.setBoard_title(keyword);
		bv.setBoard_content(keyword);
		List<BoardVO> bvList =bs.findout(bv);
		
		if(bvList.size() == 0) {
			System.out.println("검색 결과가 없습니다.");
		}else {
			System.out.println("\t순번\t제목\t작성자\t작성날짜");
			
			for(BoardVO bv2 : bvList) {
				System.out.println("\t" + bv2.getBoard_no() + "\t" + bv2.getBoard_title() + "\t" + bv2.getBoard_writer() + "\t" + bv2.getBoard_date() );
			}
		}
	}

	private void delete() {
		System.out.println();
		System.out.println("삭제할 작성글의 번호를 입력해주세요");
		String no = scan.nextLine();
		
		int cnt = bs.delete(no);
		if(cnt>0) {
			System.out.println("게시글 삭제성공");
		}else {
			System.out.println("게시글 삭제 실패!!");
		}
	}

	private void repair() {
		boolean check= true;
		String no="";
		do {
			System.out.println();
			System.out.println("==========================작성글 수정===========================");
			System.out.println("작성자 번호 >>");
			no =scan.nextLine();
			check=getSelect(no);
			if(check ==false) {
				System.out.println(no + "번 작성글이 없습니다.");
				System.out.println("다시 입력하세요.");
			}
		}while(check ==false);
		System.out.println("수정할 제목 내용을 입력하세요");
		System.out.println("제목 >>");
		String title =scan.nextLine();
		
		System.out.println("수정할 내용");
		String content = scan.nextLine();
		
		BoardVO bv =new BoardVO();
		bv.setBoard_no(no);
		bv.setBoard_title(title);
		bv.setBoard_content(content);
		
		int cnt =bs.repair(bv);
		if(cnt>0) {
			System.out.println("게시글 수정완료");
		}else {
			System.out.println("게시글 수정실패!!");
		}
	}

	private void selectview() {
		System.out.println();
		System.out.println("===========================글 목록=============================");
		System.out.println("순번\t제목\t작성자\t작성날짜\t\t\t내용");
		List<BoardVO> bvList =bs.selectview();
		if(bvList.size() == 0) {
			System.out.println("조회할 게시글이 없습니다.");
		}else {
			for(BoardVO bv : bvList) {
				System.out.println( bv.getBoard_no() + "\t" + bv.getBoard_title() + "\t" + bv.getBoard_writer() + "\t" + bv.getBoard_date() + "\t" + bv.getBoard_content());
			}
		}
		System.out.println("-----------------------------------------------------");
		System.out.println();
	}

	private void write() {
		System.out.println();
		System.out.println("작성자 이름 >>");
		String writer =scan.nextLine();
		System.out.println("제목 >> ");
		String title =scan.nextLine();
		System.out.println("글 내용 >> ");
		String content =scan.nextLine();
		BoardVO bv =new BoardVO();
		bv.setBoard_writer(writer);
		bv.setBoard_title(title);
		bv.setBoard_content(content);
		
		
		int cnt = bs.write(bv);
		
		if(cnt >0) {
			System.out.println(writer+ "게시글 등록완료");
		}else {
			System.out.println(writer+ "게시글 등록실패");
		}
	}

	public boolean getSelect(String no) {
		return bs.getSelect(no);
	}
}
