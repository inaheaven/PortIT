package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Portfolio;

/**
 *  타임라인 구성화면
 */
public class Timeline_ViewDao {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
		
	/**
	 * DB연결 생성자
	 */
	public Timeline_ViewDao() {
		try {
			pool = DBConnectionMgr.getInstance();
			con = pool.getConnection();
		} catch (Exception err) {
			System.out.println("DB접속 오류 : " + err);
		}
	}

	/**
	 * DB 접속 해제
	 */
	private void freeConnection() {
		try {
			pool.freeConnection(con, pstmt, rs);
			if (con != null) {
				System.out.println("DB 접속 해제");
			}
		} catch (Exception e) {
			System.out.println("DB 접속해제 오류 - freeConnection()");
			e.printStackTrace();
		}
	}

	/**
	 * 타임라인 정보를 불러오는 메서드	  
	 */
	public List timeline_info(int mem_id) {
		ArrayList list = new ArrayList();
		String sql = "select distinct MEDIA_LIBRARY.ML_PATH, TAG.TAG_NAME, portfolio.PF_TITLE ,Profile.PROF_NAME, portfolio.PF_LIKE ,portfolio.PF_REGDATE "
				+ "from tag join tag_use "
				+ "on tag.tag_id = tag_use.tag_id "
				+ "join portfolio "
				+ "on portfolio.PF_ID = tag_use.tag_use_type_id "
				+ "join prof_pf "
				+ "on portfolio.PF_ID = prof_pf.pf_id "
				+ "join profile "
				+ "on prof_pf.PROF_ID = Profile.PROF_ID  "
				+ "join MEDIA_LIBRARY "
				+ "on MEDIA_LIBRARY.ML_TYPE_ID = portfolio.PF_ID "
				+ "join pf_like "
				+ "on pf_like.PF_ID = portfolio.pf_id "
				+ "where pf_like.mem_id="+mem_id;
		try {

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Portfolio portfolio = new Portfolio();
				portfolio.setMl_path(rs.getString("ml_path"));
				portfolio.setTag_name(rs.getString("tag_name"));
				portfolio.setPf_title(rs.getString("pf_title"));
				portfolio.setPf_like(rs.getInt("pf_like"));
				portfolio.setProf_name(rs.getString("prof_name"));

				list.add(portfolio);
			}
		}

		catch (Exception err) {
			System.out.println("Timeline_info() 에서 오류");
			err.printStackTrace();
		}

		finally {
			freeConnection();
		}
		return list;
	}	

}