package board.svc;
// 글 등록 요청 처리
import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;
import static db.JdbcUtil.*;
public class BoardWriteProService {

	public boolean registArticle(BoardBean boardBean) throws Exception {
	boolean isWriteSuccess = false;
	Connection con = null;
	try {
		
	con = getConnection1();
	BoardDAO boardDAO = BoardDAO.getInstance();
	boardDAO.setConnection(con);
	int insertCount = boardDAO.insertArticle(boardBean);
	
	if(insertCount > 0) {
		commit(con);
		isWriteSuccess = true;
	} else {
		rollback(con);
	}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		
		close(con);
	}
		return isWriteSuccess;
	}
}
