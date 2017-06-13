package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Bookmark;

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
	public Bookmark BookmarkDao(int bm_id, int mem_id) {
		String sql = "";
		Bookmark dto = new Bookmark();
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setBm_id(rs.getInt("bm_id"));
				dto.setMem_id(rs.getInt("mem_id"));
				dto.setPf_id(rs.getInt("pf_id"));
				dto.setBm_date(rs.getDate("bm_date"));
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
