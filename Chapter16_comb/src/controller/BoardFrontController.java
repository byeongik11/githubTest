package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.Action;
import board.action.BoardDeleteProAction;
import board.action.BoardDetailAction;
import board.action.BoardListAction;
import board.action.BoardModifyFormAction;
import board.action.BoardModifyProAction;
import board.action.BoardReplyFormAction;
import board.action.BoardReplyProAction;
import board.action.BoardWriteProAction;
import vo.ActionForward;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardFrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String RequestURI = request.getRequestURI(); // 프로젝트 + 파일경로
		String contextPath = request.getContextPath(); // 프로젝트경로만
		String command = RequestURI.substring(contextPath.length());
		System.out.println(command);
		ActionForward forward = null;
		Action action = null;
		System.out.println(RequestURI);
		System.out.println(contextPath);
		System.out.println(command);
		
		if (command.equals("/boardWriteForm.bo")) {
			forward = new ActionForward();
			forward.setPath("/board/qna_board_write.jsp");
		} else if (command.equals("/boardWritePro.bo")) {
			action = new BoardWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardList.bo")) { // 글목록
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardDetail.bo")) { // 글 상세
			action = new BoardDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardModifyForm.bo")) { // 글수정
			action = new BoardModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardModifyPro.bo")) { // 글수정 프로세스
			action = new BoardModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/boardReplyForm.bo")) { // 답변달기
			action = new BoardReplyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/boardReplyPro.bo")) { //답변달기 프로세스	
			action = new BoardReplyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/boardDeleteForm.bo")) {  // 글삭제 비즈니스 로직이 필요없음(action)
			int board_num = (Integer.parseInt(request.getParameter("board_num")));
			String page = request.getParameter("page");
			
			request.setAttribute("board_num", board_num);
			request.setAttribute("page", page);
			forward = new ActionForward();
			forward.setPath("/board/qna_board_delete.jsp");
			
			
		} else if(command.equals("/boardDeletePro.bo")) {
			action = new BoardDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		
		if (forward != null) {
			if (forward.isRedirect()) { // ActionFoward 의 redirect가 true이면
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}

	}
}