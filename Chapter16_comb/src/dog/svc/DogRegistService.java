package dog.svc;

import java.sql.Connection;

import dao.DogDAO;

import static db.JdbcUtil.*;
import vo.Dog;

public class DogRegistService {

	public boolean registDog(Dog dog) {
		boolean isRegistSuccess = false;
		Connection con = null;
		int insertCount  = 0;
		
		try {
			con = getConnection();
			DogDAO dogDAO = DogDAO.getInstance();
			dogDAO.setConnection(con);
			
			insertCount = dogDAO.insertDog(dog);
			
			if(insertCount > 0) {
				commit(con);
				isRegistSuccess = true;
			} else {
				rollback(con);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con);
		}
		
		
		return isRegistSuccess;
	}

}
