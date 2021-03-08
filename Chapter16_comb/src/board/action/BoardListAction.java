package board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.svc.BoardListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int page = 1;
		int limit = 10;	//페이지에 보이는 목록갯수
		int limitPage = 10;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		BoardListService boardListService = new BoardListService();
		int listCount = boardListService.getListCount();	///목록 리턴값을 받음
		
		//총페이지수
		int maxPage = (int)((double)listCount/limit+0.95);
		
		
		// 현재 페이지에 보여줄 시작 페이지 수 
		int startPage = (((int)((double)page / limitPage+0.9)) -1) * limitPage + 1;
		
		
		// 현재 페이지에 보여줄 마지막페이지 수
		int endPage = startPage + limitPage -1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		articleList = boardListService.getArticleList(page,limit);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_board_list.jsp");
		
		return forward;
	}

}
