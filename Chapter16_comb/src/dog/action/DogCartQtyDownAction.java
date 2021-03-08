package dog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog.svc.DogCartQtyDownService;
import vo.ActionForward;

public class DogCartQtyDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String kind = request.getParameter("kind");
		try {
			DogCartQtyDownService dogCartQtyDownService = new DogCartQtyDownService();
			dogCartQtyDownService.downCartQty(kind,request);
			forward = new ActionForward("dogCartList.dog", true);
			
		} catch (Exception e) {
		}
		return forward;
	}

}
