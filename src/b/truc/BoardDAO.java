package b.truc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import util.copy.JdbcUtil;




public class BoardDAO {
	
	
	
	
	private static BoardDAO instance = new BoardDAO();
	private DataSource ds = null;
	private InitialContext ctx = null;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	private BoardDAO() {
	
		try {
			
			 ctx= new InitialContext(); 
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			System.out.println("에러발생");
		}
	}

	public static BoardDAO getInstance() {
		return instance;
	}
	
	
	//등록 메서드
	public void regist(String name,String title, String content) {
		String sql = "insert into board(bno,name,title,content) values(board_seq.nextval,?,?,?)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
	}
	
	
	public ArrayList<BoardVO> getList(int pageNum, int amount){
		
		ArrayList<BoardVO> list = new ArrayList<>();
		
		String sql = "select *\r\n" + 
				"from(select rownum as rn ,\r\n" + 
				"            bno,\r\n" + 
				"            name,\r\n" + 
				"            title,\r\n" + 
				"            content,\r\n" + 
				"            regdate\r\n" + 
				"            from (select * \r\n" + 
				"                    from board \r\n" + 
				"                    order by bno desc) \r\n" + 
				"                        )\r\n" + 
				"where rn > ? and rn <= ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,(pageNum-1)*amount); 
			pstmt.setInt(2, (pageNum*amount)); 
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bno = rs.getInt("bno");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");
				BoardVO vo = new BoardVO(bno,name,title,content,regdate);
				list.add(vo);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return list;
	
		
	}
	
	
	public int getTotal() {
		
		int total=0;
		String sql = "select count(*) as total from board";
		try {
			conn=ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt("total");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return total;
	}
	
	
	
	
	
	
	
	
	
	
	
//-------------------------------------------------------------------------------
	
	
	public BoardVO getContent(int bno) {
		String sql = "select * from board where bno = ?";
		BoardVO vo = new BoardVO();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				vo.setBno(rs.getInt("bno"));
				vo.setName(rs.getString("name"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getTimestamp("regdate"));
			
				
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return vo;
		

		
	}
//----------------------------------------------------------------------
	//update() 메서드 생성
	
	public void update(String bno,String title, String content){
		BoardVO vo = new BoardVO();
		String sql = "update board set title=?,content=? where bno = ?";
		try {
			conn=ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, bno);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}	
		
	}

	//update() 메서드 생성	
	
	
	public void delete(int bno) {
		
		String sql = "delete from board where bno = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
