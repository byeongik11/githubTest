package board.svc;
import static db.JdbcUtil.close;

import java.sql.Connection;
// 글 목록 보기 요청을 처리하는 비즈니스 로직
import java.util.ArrayList;

import dao.BoardDAO;
import vo.BoardBean;
import static db.JdbcUtil.*;
public class BoardListService {

	
	public int getListCount() {
		int listCount = 0;
		Connection con = null;
		try {
			con = getConnection1();
			BoardDAO boardDAO = BoardDAO.getInstance();  //싱글턴패턴
			boardDAO.setConnection(con);     //객체주입 connection 을 dao에 집어넣는다.
			listCount = boardDAO.selectListCount();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con);
		}
		return listCount;
	}

	public ArrayList<BoardBean> getArticleList(int page, int limit) {
		ArrayList<BoardBean> list = null;
		Connection con = null;
		try {
			con = getConnection1();
			BoardDAO boardDAO = BoardDAO.getInstance();  //싱글턴패턴
			boardDAO.setConnection(con);     //객체주입 connection 을 dao에 집어넣는다.
			list= boardDAO.selectArticleList(page,limit);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con);
		}
		return list;
	}

}
