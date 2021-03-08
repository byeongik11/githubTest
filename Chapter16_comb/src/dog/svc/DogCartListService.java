package dog.svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;
// 장바구니 목록보기 요청을 처리하는 비지니스 로직
public class DogCartListService {

	@SuppressWarnings("unchecked")
	public ArrayList<Cart> getCartList(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ArrayList<Cart> cartList = null;
		try {
			cartList = (ArrayList<Cart>) session.getAttribute("cartList");
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartList;
	}

}
