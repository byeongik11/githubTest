package dog.action;
import java.io.PrintWriter;
import java.util.ArrayList;

//장바구니 목록보기 action
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog.svc.DogCartListService;
import vo.ActionForward;
import vo.Cart;

public class DogCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		
		try {
			DogCartListService dogCartListService = new DogCartListService();
		
			ArrayList<Cart> cartList = dogCartListService.getCartList(request);
			
			
			// 총금액 계산
			int totalMoney = 0;
			int money = 0;
			
			if(cartList!=null) {
				
				for(int i=0;i<cartList.size();i++) {
					money = cartList.get(i).getPrice() * cartList.get(i).getQty(); //장바구니 항목 하나당 금액계산
					totalMoney += money;
				} 
				
				request.setAttribute("totalMoney", totalMoney);
				request.setAttribute("cartList", cartList);
//				forward = new ActionForward("/dogShopping/dogCartList.jsp", false);
				request.setAttribute("pagefile", "/dogShopping/dogList.jsp");
				forward = new ActionForward("template.jsp",false);  // forward 방식으로 전송
			} else {
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('장바구니가 비었습니다.물품을 추가하세요')");
//				forward = new ActionForward("/dogShopping/dogList.jsp", false);
				request.setAttribute("pagefile", "/dogShopping/dogList.jsp");
				forward = new ActionForward("template.jsp",false);  // forward 방식으로 전송
				out.println("</script>");
				
			}
			
				
			
 		}catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}

}
