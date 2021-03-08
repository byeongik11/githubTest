package dog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog.svc.DogCartQtyUpService;
import vo.ActionForward;

public class DogCartQtyUpAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String kind = request.getParameter("kind");
		try {
			DogCartQtyUpService dogCartQtyUpService = new DogCartQtyUpService();
			dogCartQtyUpService.upCartQty(kind,request);
			forward = new ActionForward("dogCartList.dog", true);
			
		} catch (Exception e) {
		}
		return forward;
	}

}
