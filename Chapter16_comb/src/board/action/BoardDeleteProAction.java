package board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.svc.BoardDeleteProService;
import vo.ActionForward;

public class BoardDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String page = request.getParameter("page");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String pass = request.getParameter("BOARD_PASS");
		
		BoardDeleteProService boardDeleteProService = new BoardDeleteProService();
		
		boolean isArticleWriter = boardDeleteProService.isArticleWriter(board_num,pass);
		
		if(!isArticleWriter) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다.')");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		} else {
			// 글쓴이가 맞을 경우 삭제
			boolean isDeleteSuccess = boardDeleteProService.removeArticle(board_num);


			if(!isDeleteSuccess) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제실패')");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			} else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("boardList.bo?page="+ page);
			}
		}
		return forward;
	}

}
