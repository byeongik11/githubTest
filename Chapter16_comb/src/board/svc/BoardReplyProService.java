package board.svc;

import java.sql.Connection;

import dao.BoardDAO;

import static db.JdbcUtil.*;
import vo.BoardBean;

public class BoardReplyProService {

	public boolean replyArticle(BoardBean article) {
		boolean isReplySuccess = false;
		Connection con = null;
		
		try {
			con = getConnection1();
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(con);
			int insertCount = boardDAO.insertReplyArticle(article);  // dao로 가서 답변글 등록
			
			if(insertCount > 0) {
				commit(con);
				isReplySuccess = true;
			} else {
				rollback(con);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isReplySuccess;
	}

}
