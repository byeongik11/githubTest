package dog.action;
// 개 상품 상세 정보보기 요청 action
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog.svc.DogViewService;
import vo.ActionForward;
import vo.Dog;

public class DogViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		int id = Integer.parseInt(request.getParameter("id"));
		ActionForward forward = null;
		try {
			DogViewService dogViewService = new DogViewService();
			Dog dog = dogViewService.getDogView(id);
			request.setAttribute("dog", dog);
			Cookie todayImageCookie = new Cookie("today" + id, dog.getImage());
			todayImageCookie.setMaxAge(60*60*24);
			response.addCookie(todayImageCookie);
//			forward = new ActionForward("/dogShopping/dogView.jsp", false);
			request.setAttribute("pagefile", "/dogShopping/dogView.jsp");
			forward = new ActionForward("template.jsp",false);  // forward 방식으로 전송
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
	
		return forward;
	}

}
