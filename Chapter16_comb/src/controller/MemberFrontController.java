package controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.action.Action;
import member.action.MemberDeleteAction;
import member.action.MemberJoinAction;
import member.action.MemberListAction;
import member.action.MemberLoginAction;
import member.action.MemberViewAction;
import vo.ActionForward;

@WebServlet("*.me")
public class MemberFrontController extends javax.servlet.http.HttpServlet 
{
	static final long serialVersionUID = 1L;
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		System.out.println(command);
		ActionForward forward=null;
		Action action=null;

		if(command.equals("/memberLogin.me")){		//로그인폼으로
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/member/loginForm.jsp");
		}else if(command.equals("/memberJoin.me")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/member/joinForm.jsp");
			
		} else if (command.equals("/memberLogout.me")) { // 로그아웃
			HttpSession session = request.getSession();
			session.invalidate();
			forward = new ActionForward();
			forward.setRedirect(true);  			//리다이렉트로 전송
			forward.setPath("main.jsp");	
	
		}else if(command.equals("/memberLoginAction.me")){
			action = new MemberLoginAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/memberJoinAction.me")){
			action = new MemberJoinAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/memberListAction.me")){
			action = new MemberListAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/memberViewAction.me")){
			action = new MemberViewAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/memberDeleteAction.me")){
			action = new MemberDeleteAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(forward != null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher=
						request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}  	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request,response);
	}   	  	      	    
}