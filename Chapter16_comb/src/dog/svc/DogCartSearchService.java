package dog.svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class DogCartSearchService {

	public ArrayList<Cart> getCartSearchList(int startMoney, int endMoney, HttpServletRequest request) {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<Cart> oldCartList = (ArrayList<Cart>) session.getAttribute("cartList");
		ArrayList<Cart> cartList = new ArrayList<Cart>();
		for(int i=0;i< oldCartList.size();i++) {
			if(oldCartList.get(i).getPrice()>= startMoney && oldCartList.get(i).getPrice() <=endMoney) {
				//검색 가격에 해당하는지 체크
				cartList.add(oldCartList.get(i));
			}
		}
		return cartList;
	}

}
