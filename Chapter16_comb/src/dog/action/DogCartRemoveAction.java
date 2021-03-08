package dog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog.svc.DogCartRemoveService;
import vo.ActionForward;

public class DogCartRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		try {
			String[] kindArray = request.getParameterValues("remove");
			DogCartRemoveService dogCartRemoveService = new DogCartRemoveService();
			dogCartRemoveService.cartRemove(request,kindArray);
			forward = new ActionForward("dogCartList.dog", true);  //리다이렉트
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
