package board.svc;

import java.sql.Connection;

import dao.BoardDAO;

import static db.JdbcUtil.*;
import vo.BoardBean;

public class BoardDetailService {

	public BoardBean getArticle(int board_num) {
		BoardBean article = null;
		
		Connection con = null;
		try {
			
			con = getConnection1();
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(con);
			int updateCount = boardDAO.updateReadCount(board_num);
			if(updateCount > 0) {
				commit(con);
			} else {
				rollback(con);
			}
			
			article = boardDAO.selectArticle(board_num);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con);
		}
		
		
		return article;
	}

}
