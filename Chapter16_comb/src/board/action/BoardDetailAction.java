package board.action;
// 글 상세보기 요청 비지니스 로직
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.svc.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int board_num = 1;
		if(request.getParameter("board_num") != null) {
			
			board_num = Integer.parseInt(request.getParameter("board_num"));
			
		}
		
		String page = request.getParameter("page");
		
		BoardDetailService boardDetailService = new BoardDetailService();
		
		BoardBean article = boardDetailService.getArticle(board_num);
		request.setAttribute("page", page);
		request.setAttribute("article", article);
		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_board_view.jsp");
		return forward;
	}

}
