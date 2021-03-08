package dog.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog.svc.DogCartSearchService;
import vo.ActionForward;
import vo.Cart;

public class DogCartSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DogCartSearchService dogCartSearchService = new DogCartSearchService();
		int startMoney = Integer.parseInt(request.getParameter("startMoney"));
		int endMoney = Integer.parseInt(request.getParameter("endMoney"));
		
		ArrayList<Cart> cartList = dogCartSearchService.getCartSearchList(startMoney,endMoney,request);
		request.setAttribute("cartList", cartList);
		request.setAttribute("startMoney", startMoney);
		request.setAttribute("endMoney", endMoney);
		
		int totalMoney = 0;
		int money = 0;
		
		for(int i=0;i<cartList.size();i++) {
			money = cartList.get(i).getPrice() * cartList.get(i).getQty();
			totalMoney += money;
		}
		
		request.setAttribute("totalMoney", totalMoney);
//		ActionForward forward = new ActionForward("/dogShopping/dogCartList.jsp", false);
		request.setAttribute("pagefile", "/dogShopping/dogCartList.jsp");
		 ActionForward forward = new ActionForward("template.jsp",false);  // forward 방식으로 전송
		
		return forward;
	}

}
