package truc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import b.truc.BoardVO;
import util.copy.JdbcUtil;





public class UserDAO {

	private static UserDAO instance = new UserDAO();
	
	private DataSource ds;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public UserDAO() {
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
		}
	}

	
	
	public static UserDAO getInstance() {
		return instance;
	}
	//회원가입 메서드
		public int join(UserVO vo) {
			
			int result = 0; //결과를 반환할 변수
			String sql = "insert into users(id,pw,name,num1,num2,num3,email1,email2,add1,add2) values(?,?,?,?,?,?,?,?,?,?)";
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getPw());
				pstmt.setString(3, vo.getName());
				pstmt.setString(4, vo.getNum1());
				pstmt.setString(5, vo.getNum2());
				pstmt.setString(6, vo.getNum3());
				pstmt.setString(7, vo.getEmail1());
				pstmt.setString(8, vo.getEmail2());
				pstmt.setString(9, vo.getAdd1());
				pstmt.setString(10, vo.getAdd2());
		
				result = pstmt.executeUpdate();//성공 실패 결과를 1 또는 0으로 반환
				
			} catch (Exception e) {
				e.printStackTrace();
			}	finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
			
			return result;
		}
		
		

		//중복검사 메서드
		public int CheckId(String id) {
			int result = 0; //중복값이 없는 경우
			
			String sql = "select id from users where id = ? ";
			
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				
				
				rs = pstmt.executeQuery();
				if (rs.next()) {//rs.next() 존재한다는 것은 중복
					result = 1;
					
				}else {
					result = 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
			return result;
		}
		
		
		
		
	
	//로그인 메서드
	public UserVO login(String id, String pw) {
		
		UserVO vo = new UserVO();
		
		String sql = "select * from users where id = ? and pw = ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setNum1(rs.getString("num1"));
				vo.setNum2(rs.getString("num2"));
				vo.setNum3(rs.getString("num3"));
				vo.setEmail1(rs.getString("email1"));
				vo.setEmail2(rs.getString("email2"));
				vo.setAdd1(rs.getString("add1"));
				vo.setAdd2(rs.getString("add2"));
				
				
				
			}else {
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	//정보 수정 메서드
		public int update(UserVO vo) {
			int result = 0;
			String sql = "update users set pw=?,name=?,num1=?,num2=?,num3=?,email1=?,email2=?,add1=?,add2=? where id=? ";
			try {
				conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getNum1());
			pstmt.setString(4, vo.getNum2());
			pstmt.setString(5, vo.getNum3());
			pstmt.setString(6, vo.getEmail1());
			pstmt.setString(7, vo.getEmail2());
			pstmt.setString(8, vo.getAdd1());
			pstmt.setString(9, vo.getAdd2());
			pstmt.setString(10, vo.getId());
			
			result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {

				JdbcUtil.close(conn, pstmt, rs);

			}
		
			return 1;
		}
		
		
		//회원정보 삭제
		public int delete(UserVO vo) {
			int result = 0;
			String sql = "delete from users where id = ?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getId());
				
				result = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(conn, pstmt, null);
			}
			return result;
		}
		//게시판과 퓨전 합체 서비스
		public ArrayList<BoardVO> boardService(String name) {
			ArrayList<BoardVO> list = new ArrayList<>();
			BoardVO vo = new BoardVO();
			String sql = "select * from board b\r\n" + 
					"left outer join users e\r\n" + 
					"on b.name = e.id\r\n" + 
					"where e.id = ?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					vo.setBno(rs.getInt("bno"));
					vo.setName(rs.getString("name"));
					vo.setTitle(rs.getString("title"));
					vo.setContent(rs.getString("content"));
					vo.setRegdate(rs.getTimestamp("regdate"));
					
				}
				list.add(vo);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
			return list;
		}
	
}
