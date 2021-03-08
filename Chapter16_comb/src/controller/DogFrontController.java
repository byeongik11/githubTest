package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog.action.Action;
import dog.action.DogCartAddAction;
import dog.action.DogCartListAction;
import dog.action.DogCartQtyDownAction;
import dog.action.DogCartQtyUpAction;
import dog.action.DogCartRemoveAction;
import dog.action.DogCartSearchAction;
import dog.action.DogListAction;
import dog.action.DogRegistAction;
import dog.action.DogRegistFormAction;
import dog.action.DogViewAction;
import vo.ActionForward;

/**
 * Servlet implementation class DogFrontController
 */
@WebServlet("*.dog")
public class DogFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DogFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		
		String command = requestURI.substring(contextPath.length());
		System.out.println(command);
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/dogList.dog")) {				//상품목록보기
			action = new DogListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/dogView.dog")) {			// 상품상세보기
			action = new DogViewAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/dogRegistForm.dog")) {	// 상품등록폼으로
			action = new DogRegistFormAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/dogRegist.dog")) {		//상품등록처리
			action = new DogRegistAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/dogCartAdd.dog")) {		//장바구니 등록
			action = new DogCartAddAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/dogCartList.dog")) {		//장바구니 리스트
			action = new DogCartListAction();
			
			try {
				forward = action.execute(request, response);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/dogCartSearch.dog")) {	//  장바구니 항목검색
			action = new DogCartSearchAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/dogCartRemove.dog")) {	// 장바구니 항목 삭제
			action = new DogCartRemoveAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/dogCartQtyUp.dog")) { //수량증가
			action = new DogCartQtyUpAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/dogCartQtyDown.dog")) {// 수량감소
			action = new DogCartQtyDownAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if(forward!=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
		
	}

}
