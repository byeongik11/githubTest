package board.action;
// 글 수정폼 보기 요청처리 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.svc.BoardModifyFormSvc;
import vo.ActionForward;
import vo.BoardBean;

public class BoardModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		  String page= request.getParameter("page");
	      
	      BoardModifyFormSvc boardModifyFormSvc = new BoardModifyFormSvc();
	      BoardBean article = boardModifyFormSvc.getAricle(board_num);
	      request.setAttribute("article", article);
	      request.setAttribute("page", page);
	      forward.setPath("/board/qna_board_modify.jsp");
	      return forward;

	}

}