package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Bookmark;
import portit.model.dto.Portfolio;

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

	public BookmarkDao() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("而ㅻ꽖�뀡 �� �삤瑜� - FollowDao()");
			e.printStackTrace();
		}
	}

	/**
	 * DB �뿰寃�
	 */
	private void getConnection() {
		try {
			conn = pool.getConnection();
			if (conn != null)
				System.out.println("DB �젒�냽");
		} catch (Exception e) {
			System.out.println("DB �젒�냽 �삤瑜� - getConnection()");
			e.printStackTrace();
		}
	}

	/**
	 * DB �뿰寃� �빐�젣
	 */
	private void freeConnection() {
		try {
			pool.freeConnection(conn, stmt, rs);
			if (conn != null)
				System.out.println("DB �젒�냽 �빐�젣");
		} catch (Exception e) {
			System.out.println("DB �젒�냽�빐�젣 �삤瑜� - freeConnection()");
			e.printStackTrace();
		}
	}
	public List<Portfolio> getMyBookmark(int mem_id) {
		List<Portfolio> portfolioList = new ArrayList<>();
		String sql = "SELECT BM_ID , PF_TITLE,PF_LIKE, PF_NUMOFPERSON FROM BOOKMARK A , PORTFOLIO B "
				+ " WHERE A.PF_ID= B.PF_ID and a.mem_id = ?";

		
		try {
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Portfolio pf = new Portfolio();
				
				pf.setBm_id(rs.getInt("bm_id"));
				pf.setPf_title(rs.getString("pf_title"));
				pf.setPf_like(Integer.parseInt(rs.getString("pf_like")));
				pf.setPf_numofperson(Integer.parseInt(rs.getString("pf_numofperson")));
				portfolioList.add(pf);
			}
		} catch (Exception err) {
			System.out.println("getList() : " + err);
		} finally {
			freeConnection();
		}

		return portfolioList;

	}

	// BOOKMARK 異붽�
	public Portfolio addBookmark(int pf_id, int mem_id) {
		String sql = "";
		Portfolio portfolio = new Portfolio();

		try {

			//BOOKMARK에 MEM_ID=? AND PF_ID=? 조회해서 데이터가 존재하면 delete 없으면 insert
			conn = pool.getConnection();

			sql = "MERGE INTO BOOKMARK B1" + "USING (SELECT MEM_ID, PF_ID FROM BOOKMARK WHERE MEM_ID=? AND PF_ID=?)B2" 
					+ "ON (B1.BM_ID=B2.BM_ID)" + "WHEN MATCHED THEN" + "DELETE FROM BOOKMARK WHERE MEM_ID =B1.MEM_ID"
					+ "WHEN NOT MATCHED THEN" + "INSERT INTO BOOKMARK (BM_ID, MEM_ID,PF_ID,BM_DATE)" 
					+ "VALUES (SEQ_TAG_ID.NEXTVAL,?,?,SYSDATE)";
			

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			stmt.setInt(2, pf_id);
			stmt.setInt(3, mem_id);
			stmt.setInt(4, pf_id);
			stmt.executeUpdate(sql);

		} catch (Exception err) {
			System.out.println("getList() : " + err);
		} finally {
			freeConnection();
		}
		return portfolio;

	}

	// BOOKMARK �궘�젣
	public void deleteBookmark(int bm_id) {
		String sql = "delete from bookmark where bm_id =" + bm_id + "";

		try {
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println("DBCP �뿰寃� �떎�뙣 : " + err);
		} finally {
			freeConnection();
		}

	}
}
