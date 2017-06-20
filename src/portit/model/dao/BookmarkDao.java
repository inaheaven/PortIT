package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Bookmark;
import portit.model.dto.Portfolio;
import portit.model.dto.Profile;
import portit.model.dto.Tag;

/**
 * 
 * @author hyang
 *
 */
public class BookmarkDao {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	private String sql = null;

	public BookmarkDao() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("커넥션 풀 오류 - FollowDao()");
			e.printStackTrace();
		}
	}
  
	/**
	 * DB 연결
	 */
	private void getConnection() {
		try {
			conn = pool.getConnection();
			if (conn != null)
				System.out.println("DB 접속");
		} catch (Exception e) {
			System.out.println("DB 접속 오류 - getConnection()");
			e.printStackTrace();
		}
	}

	/**
	 * DB 연결 해제
	 */
	private void freeConnection() {
		try {
			pool.freeConnection(conn, stmt, rs);
			if (conn != null)
				System.out.println("DB 접속 해제");
		} catch (Exception e) {
			System.out.println("DB 접속해제 오류 - freeConnection()");
			e.printStackTrace();
		}
	}

	// BOOKMARK 추가
	public Portfolio addBookmark(int pf_id,int mem_id) {
		String sql = "";
		Portfolio portfolio = new Portfolio(); 
		
		try {
			conn = pool.getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pf_id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				portfolio.setPf_title(rs.getString("pf_title"));//포트폴리오제목
				portfolio.setPf_like(rs.getInt("pf_like"));//포트폴리오 좋아요수
				portfolio.setProf_name(rs.getString("prof_name")); //포트폴리오 작성자 이름
			}
				
			
				sql = "SELECT * FROM TAGUSE tu "
						+ "INNER JOIN TAG t "
						+ "ON tu.tag_id=t.tag_id "
						+ "WHERE tu.tag_use_type=? AND tu.tag_use_type_id=?";
				
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "portfolio");
				stmt.setInt(2, portfolio.getPf_id());
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					// 조회 결과를 DTO에 저장
						portfolio.setTag_name(rs.getString("t.tag_name"));
					}

		} catch (Exception err) {
			System.out.println("getList() : " + err);
		} finally {
			freeConnection();
		}
		return portfolio;

	}

	// BOOKMARK 삭제
	public void deleteBookmark(int bm_id) {
		String sql = "delete from bookmark where bm_id ="+bm_id+"";

		try {
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println("DBCP 연결 실패 : " + err);
		} finally {
			freeConnection();
		}

	}
}
