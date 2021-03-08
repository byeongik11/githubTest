package dao;
import static db.JdbcUtil.close;

//답변하기 요청을 처리하는 비즈니스 로직
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.BoardBean;

public class BoardDAO {
	
	DataSource ds;
	Connection con;
	private static BoardDAO boardDAO; //싱글턴
	
	public BoardDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static BoardDAO getInstance() {
		if(boardDAO == null) {
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;      //객체주입
	}
	
	//게시글등록
	public int insertArticle(BoardBean article) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		String sql = null;
		int insertCount = 0;
		
		try {
			pstmt = con.prepareStatement("SELECT MAX(board_num) FROM board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1)+1;
			} else {
				num = 1;
			}
			
			sql = "INSERT INTO board(board_num,board_name,board_pass,board_subject,"
				+ "board_content,board_file,board_re_ref,board_re_lev,board_re_seq,board_readcount,board_date)"
				+ "VALUES(?,?,?,?,?,?,?,0,0,0,now())";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getBOARD_NAME());
			pstmt.setString(3, article.getBOARD_PASS());
			pstmt.setString(4, article.getBOARD_SUBJECT());
			pstmt.setString(5, article.getBOARD_CONTENT());
			pstmt.setString(6, article.getBOARD_FILE());
			pstmt.setInt(7, num);
			
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("boardInsert 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}

	//글 개수 구하기
	public int selectListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement("SELECT COUNT(*) FROM board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1); // 글개수를 listCount 변수에 대입
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getListCount 에러 : " + e);
		} finally {
			close(con);
			close(pstmt);
		}
		return listCount;
	}

	
	// 게시글 내용보기
	public ArrayList<BoardBean> selectArticleList(int page, int limit) {
		ArrayList<BoardBean> list = new ArrayList<BoardBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql = "SELECT * FROM board ORDER BY board_re_ref DESC, "
							  + "board_re_seq ASC limit ?,?";
		
		int startrow = (page-1) * 10; // 읽기 시작할 row 행번호
		
		try {
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardBean board = new BoardBean();
				board.setBOARD_NUM       (rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME      (rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT   (rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT   (rs.getString("BOARD_CONTENT"));
				board.setBOARD_FILE      (rs.getString("BOARD_FILE"));
				board.setBOARD_RE_REF    (rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV    (rs.getInt("BOARD_RE_LEV"));
				board.setBOARD_RE_SEQ    (rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT (rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE      (rs.getDate("BOARD_DATE"));
				list.add(board);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	//조회수 업데이트
	public int updateReadCount(int board_num) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "UPDATE board SET BOARD_READCOUNT = "
				+ 	 "BOARD_READCOUNT+1 WHERE BOARD_NUM = ?";
		
		try {	
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
	// 글 내용상세 보기
	public BoardBean selectArticle(int board_num) {
		BoardBean article = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM board WHERE board_num = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new BoardBean();
				article.setBOARD_NUM(rs.getInt("board_num"));
				article.setBOARD_NAME(rs.getString("board_name"));
				article.setBOARD_SUBJECT(rs.getString("board_subject"));
				article.setBOARD_CONTENT(rs.getString("board_content"));
				article.setBOARD_FILE(rs.getString("board_file"));
				article.setBOARD_RE_REF(rs.getInt("board_re_ref"));
				article.setBOARD_RE_LEV(rs.getInt("board_re_lev"));
				article.setBOARD_RE_SEQ(rs.getInt("board_re_seq"));
				article.setBOARD_READCOUNT(rs.getInt("board_readcount"));
				article.setBOARD_DATE(rs.getDate("board_date"));
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return article;
	}

	

	// 글쓴이인지 확인
	public boolean isArticleBoardWriter(int board_num, String pass) {
		boolean isWriter = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM board WHERE board_num = ? and board_pass = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isWriter = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return isWriter;
	}

	// 게시글 수정
	public int updateArticle(BoardBean article) {	
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_subject = ?, board_content=? WHERE board_num= ? ";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getBOARD_SUBJECT());
			pstmt.setString(2, article.getBOARD_CONTENT());
			pstmt.setInt(3, article.getBOARD_NUM());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	public int insertReplyArticle(BoardBean article) {	//답변등록 update->insert
		int insertCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//관련 글 seq 수정
			String sql = " UPDATE board SET board_re_seq = board_re_seq+1"
					   + " WHERE board_re_ref = ? "
					   + " and board_re_seq > ?   ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, article.getBOARD_RE_REF());
			pstmt.setInt(2, article.getBOARD_RE_SEQ());
			pstmt.executeUpdate();
			
			int num = 1;  // 입력글 번호 생성
			pstmt = con.prepareStatement("SELECT MAX(board_num) from board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1) + 1;  // 계산이 필요하면 컬럼명은 적으면 안됨
			}
			
			
			sql = "INSERT board VALUES(?,?,?,?,?,'',?,?,?,0,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getBOARD_NAME());
			pstmt.setString(3, article.getBOARD_PASS());
			pstmt.setString(4, article.getBOARD_SUBJECT());
			pstmt.setString(5, article.getBOARD_CONTENT());
			pstmt.setInt(6, article.getBOARD_RE_REF());    // 부모글 구분
			pstmt.setInt(7, article.getBOARD_RE_LEV()+1);  // 답글 깊이
			pstmt.setInt(8, article.getBOARD_RE_SEQ()+1);  // 답글 달린 순서, 현재글보다 하나 밑에 있어야되기 때문
			
			insertCount = pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return insertCount;
	}

	// 게시글 삭제
	public int deleteArticle(int board_num) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM board WHERE board_num =?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}
}
