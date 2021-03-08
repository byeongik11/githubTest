package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.svc.BoardModifyFormSvc;
import vo.ActionForward;
import vo.BoardBean;

public class BoardReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");
		
		//boardDetailService 사용하면 답글달때도 조회수가 올라가기 때문
		BoardModifyFormSvc boardModifyFormSvc = new BoardModifyFormSvc();
		BoardBean article = boardModifyFormSvc.getAricle(board_num);
		request.setAttribute("article", article);
		request.setAttribute("page", page);
		forward.setPath("/board/qna_board_reply.jsp");
		
		return forward;
	}

}
