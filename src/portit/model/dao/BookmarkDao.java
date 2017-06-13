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
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private DBConnectionMgr pool;
	private String sql = null;

	public BookmarkDao() {
		try {
			ds = (DataSource) new InitialContext().lookup("");
			con = ds.getConnection();
		} catch (Exception err) {
			System.out.println("DBCP 연결 실패 : " + err);
		}
	}

	public void freeCon() {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception err) {
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception err) {
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception err) {
			}
		}
	}

	/**
	 * DB
	 */
	private void freeConnection() {
		try {
			pool.freeConnection(con, pstmt, rs);
			if (con != null)
				System.out.println("DB ");
		} catch (Exception e) {
			System.out.println("연결실패 freeConnection()");
			e.printStackTrace();
		}
	}

	// BOOKMARK 추가
	public Portfolio getBookmark(int pf_id,int mem_id) {
		String sql = "";
		Portfolio dto = new Portfolio();//포트폴리오 제목 좋아요수, 
		
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pf_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setPf_title(rs.getString("pf_title"));//포트폴리오제목
				dto.setPf_like(rs.getInt("pf_like"));//포트폴리오 좋아요수
				dto.setProf_name(rs.getString("prof_name")); //포트폴리오 작성자 이름
				dto.setTag_name(rs.getString("tag_name")); //포트폴리오에 사용된 기술 태그
				//dto.setBm_date(rs.getDate("bm_date"));//포트폴리오에 사용된 기술 태그
			}
		} catch (Exception err) {
			System.out.println("getList() : " + err);
		} finally {
			freeCon();
		}
		return dto;

	}

	// BOOKMARK 삭제
	public void BookmarkDelete(int bm_id) {
		String sql = "";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception err) {
			System.out.println("DBCP 연결 실패 : " + err);
		} finally {
			freeCon();
		}

	}
}
