package dog.svc;
// 개 상품 상세정보보기 요청 비지니스 로직
import vo.Dog;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DogDAO;
public class DogViewService {
// 상세정보보기를 눌렀을때 조회수를 증가시켜야 됨
	public Dog getDogView(int id) {
		Connection con = null;
		int updateCount = 0;
		Dog dog = null;
		try {
			con = getConnection();
			DogDAO dogDAO = DogDAO.getInstance();
			dogDAO.setConnection(con);
			updateCount = dogDAO.updateReadCount(id); 
			
			if(updateCount > 0) {
				commit(con);
				dog = dogDAO.selectDog(id);
			} else {
				rollback(con);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(con);
		}
		return dog;
	}

}
