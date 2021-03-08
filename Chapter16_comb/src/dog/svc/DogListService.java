package dog.svc;

import java.sql.Connection;
import java.util.ArrayList;
import static db.JdbcUtil.*;
import dao.DogDAO;
import vo.Dog;

public class DogListService {

	public ArrayList<Dog> getDogList() {		// 개 상품목록을 arraylist 객체 타입으로 반환
		Connection con = null;	
		ArrayList<Dog> dogList = null;
		try {
			con = getConnection();
			DogDAO dogDAO = DogDAO.getInstance();
			dogDAO.setConnection(con);
			dogList = dogDAO.selectDogList();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con);
		}
		
		
		return dogList;
	}

}
