package dog.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.DogDAO;
import vo.Cart;
import vo.Dog;
// 새로운 장바구니 항목을 추가하는 요청처리 비지니스로직
public class DogCartAddService {

	public Dog getCartDog(int id) {					// 아이디로 개 정보를 가져옴
		Connection con = null;
		Dog dog = null;
		try {
			con = getConnection();
			DogDAO dogDAO = DogDAO.getInstance();
			dogDAO.setConnection(con);
			dog = dogDAO.selectDog(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con);
		}
		return dog;
	}

	@SuppressWarnings("unchecked")
	public void addCart(HttpServletRequest request, Dog cartDog) {		// 장바구니 추가
		HttpSession session = request.getSession();
		ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");
		
		if(cartList == null) {
			cartList = new ArrayList<Cart>();	// cartList가 null이면 새로운 arrayList 추가
			session.setAttribute("cartList", cartList);
			
		}
		
		boolean isNewCart = true; // 장바구니에 담는 항목이 새로운 추가항목인지 아닌지 
		
		for(int i=0;i<cartList.size();i++) {
			if(cartDog.getKind().equals(cartList.get(i).getKind())) {
				isNewCart = false;
				cartList.get(i).setQty(cartList.get(i).getQty()+1);		// 수량을 올려준다.
				break;
			}
		}
		
		if(isNewCart) {
			Cart cart = new Cart();
			cart.setImage(cartDog.getImage());
			cart.setKind(cartDog.getKind());
			cart.setPrice(cartDog.getPrice());
			cart.setQty(1);
			cartList.add(cart);
		}
	}

}
