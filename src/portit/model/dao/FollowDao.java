package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Bookmark;
import portit.model.dto.Follow;

/**
 * 
 * @author hyang
 *
 */
public class FollowDao {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private DBConnectionMgr pool;
	private String sql = null;

	public FollowDao() {
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

	// follow 추가
	public Follow FollowDao(int fw_id) {
		String sql = "";
		Follow dto = new Follow();
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fw_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setFw_id(rs.getInt("fw_id"));
				dto.setMem_id_sender(rs.getInt("mem_id_sender"));
				dto.setMem_id_receiver(rs.getInt("mem_id_receiver"));
				dto.setFw_date(rs.getDate("fw_date"));
			}
		} catch (Exception err) {
			System.out.println("getList() : " + err);
		} finally {
			freeCon();
		}
		return dto;

	}

	// following 삭제
	public void FolloDelete(int fw_id) {
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
