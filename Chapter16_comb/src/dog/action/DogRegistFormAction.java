package dog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class DogRegistFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ActionForward forward = new ActionForward("/dogShopping/dogRegistForm.jsp", false);
		request.setAttribute("pagefile", "/dogShopping/dogRegistForm.jsp");
		 ActionForward forward = new ActionForward("template.jsp",false);  // forward 방식으로 전송
		return forward;
	}

}
