package board.svc;
import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;

public class BoardModifyFormSvc {

	public BoardBean getAricle(int board_num) {
		 BoardBean article = null;
	      Connection con = null;
	      try {
	         con = getConnection1();
	         BoardDAO boardDAO = BoardDAO.getInstance();
	         boardDAO.setConnection(con);
	         
	         
	         article = boardDAO.selectArticle(board_num);
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         close(con);
	      }
	      
	      
	      
	      return article;
	   }


}
