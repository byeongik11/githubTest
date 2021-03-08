package board.svc;
import java.sql.Connection;

import dao.BoardDAO;

import static db.JdbcUtil.*;
// 글 수정하기 요청 처리
import vo.BoardBean;

public class BoardModifyProService {

	public boolean isArticleWriter(int board_num, String pass) {
		boolean isArticleWriter = false;
		Connection con = null;
		
		try {
			con = getConnection1();
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(con);
			isArticleWriter = boardDAO.isArticleBoardWriter(board_num,pass);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con);
		}
		
		return isArticleWriter;
	}

	// 글 수정작업
	public boolean modifyArticle(BoardBean article) {
		boolean isModifySuccess = false;
		Connection con = null;
		
		try {
			con = getConnection1();
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(con);
			int updateCount = boardDAO.updateArticle(article);
			
			if(updateCount >0) {
				commit(con);
				isModifySuccess = true;
			} else {
				rollback(con);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con);
		}
		return isModifySuccess;
	}

}
