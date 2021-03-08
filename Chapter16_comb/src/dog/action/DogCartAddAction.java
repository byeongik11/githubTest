package dog.action;
// 장바구니 담기 요청 처리
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog.svc.DogCartAddService;
import vo.ActionForward;
import vo.Dog;

public class DogCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id")); //id 파라미터로 가져오기
		ActionForward forward = null;
		try {
			DogCartAddService dogCartAddService = new DogCartAddService();
			Dog cartDog = dogCartAddService.getCartDog(id);  //개 상품 정보를 얻어옴
			dogCartAddService.addCart(request,cartDog);		// 상품정보를 얻어와서 장바구니 항목으로 추가하는 항목
			forward = new ActionForward("dogCartList.dog", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return forward;
	}

}
