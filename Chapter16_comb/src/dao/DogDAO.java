package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import static db.JdbcUtil.*;
import vo.Dog;

public class DogDAO {
	Connection con;
	private static DogDAO boardDAO;
	
	private DogDAO() {}
	
	public void setConnection(Connection con) {
		this.con = con;
		
	}
	
	public static DogDAO getInstance() {
		if(boardDAO == null) {
			boardDAO = new DogDAO();
		}
		return boardDAO;
	}


	public ArrayList<Dog> selectDogList() {		// 목록보기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Dog> dogList = null;
		String sql = "SELECT * FROM dog";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dogList = new ArrayList<Dog>();
				
				
				do {
					int id         = rs.getInt("id");
					String kind    = rs.getString("kind");
					int price      = rs.getInt("price");
					String image   = rs.getString("image");
					String country = rs.getString("country");
					int height     = rs.getInt("height");
					int weight     = rs.getInt("weight");
					String content = rs.getString("content");
					int readcount  = rs.getInt("readcount");
					dogList.add(new Dog(id, kind, price, image, country, height, weight, content, readcount));
					
				} while(rs.next());
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return dogList;
	}

	public Dog selectDog(int id) {				// id로 상품조회하기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Dog dog = null;
		String sql = "SELECT * FROM dog WHERE id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			
			
			if(rs.next()) {
				int dogId      = rs.getInt("id");
				String kind    = rs.getString("kind");
				int price      = rs.getInt("price");
				String image   = rs.getString("image");
				String country = rs.getString("country");
				int height     = rs.getInt("height");
				int weight     = rs.getInt("weight");
				String content = rs.getString("content");
				int readcount  = rs.getInt("readcount");
				
				dog = new Dog(dogId, kind, price, image, country, height, weight, content, readcount);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return dog;
	}

	public int updateReadCount(int id) {			// id로 조회수 증가
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "UPDATE dog SET readcount = readcount + 1 WHERE id=?";
		
		try {
			pstmt  = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			updateCount = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	public int insertDog(Dog dog) {				// 상품등록
		PreparedStatement pstmt = null;
		int insertCount = 0;
		String sql = "INSERT INTO dog VALUES(dog_seq.nextval,?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dog.getKind());
			pstmt.setInt(2, dog.getPrice());
			pstmt.setString(3,dog.getImage());
			pstmt.setString(4, dog.getCountry());
			pstmt.setInt(5, dog.getHeight());
			pstmt.setInt(6, dog.getWeight());
			pstmt.setString(7, dog.getContent());
			pstmt.setInt(8, dog.getReadcount());
			insertCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCount;
	}

}
