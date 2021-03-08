package dog.action;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog.svc.DogListService;
import vo.ActionForward;
import vo.Dog;

public class DogListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<String> todayImageList = new ArrayList<String>();
		
		Cookie[] cookieArray = request.getCookies();
		
		if(cookieArray != null) {									// arrayList객체에 쿠키객체의값(이미지 이름) 을 요소로 추가하는 부분
			for(int i= 0;i<cookieArray.length;i++) {
				if(cookieArray[i].getName().startsWith("today")) {
					todayImageList.add(cookieArray[i].getValue());
				}
			} 
		}
		 DogListService dogListService = new DogListService();
		 ArrayList<Dog> dogList = dogListService.getDogList();
		 
		 
		 request.setAttribute("dogList", dogList);				// 개 상품 목록 정보
		 request.setAttribute("todayImageList", todayImageList);		// 오늘 본 개 상품 이미지 목록 정보
		 request.setAttribute("pagefile", "/dogShopping/dogList.jsp");
		 ActionForward forward = new ActionForward("template.jsp",false);  // forward 방식으로 전송
		return forward;
		
	}

}
