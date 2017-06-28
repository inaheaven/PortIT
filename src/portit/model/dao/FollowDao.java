package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Bookmark;
import portit.model.dto.Follow;
import portit.model.dto.Portfolio;
import portit.model.dto.Profile;
import portit.model.dto.Tag;

/**
 * Follow愿��젴 Dao
 * 
 * @author hyang
 *
 */
public class FollowDao {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	private String sql = null;

	public FollowDao() {
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

	/**
	 * follow 異붽�
	 * 
	 * @param fw_id
	 * @return
	 */
	public List<Follow> getFollow() {
		String sql = "SELECT * FROM PROFILE WHERE prof_id=?";
		List<Follow> foList = new ArrayList<>();

		//Tag dto1 = new Tag(); 

		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Follow follow = new Follow();

				follow.setProf_name(rs.getString("prof_name"));
				/*
				 * follow.setPf_title(rs.getString("pf_title"));
				 * follow.setPf_like(Integer.parseInt(rs.getString("pf_like")));
				 */
				foList.add(follow);

				/*
				 * conn = pool.getConnection();
				 * 
				 * stmt = conn.prepareStatement(sql); stmt.setInt(1, prof_id);
				 * rs = stmt.executeQuery();
				 * 
				 * while (rs.next()) {
				 * profile.setProf_img(rs.getString("prof_img"));//�봽濡쒗븘�궗吏�
				 * profile.setProf_name(rs.getString("prof_name"));//媛쒕컻�옄�씠由� }
				 * 
				 * // DB�뿉�꽌 Follower �닔 議고쉶 sql =
				 * "SELECT COUNT(*) FROM FOLLOWING WHERE mem_id_receiver=?";
				 * stmt = conn.prepareStatement(sql); stmt.setInt(1,
				 * profile.getProf_id()); rs = stmt.executeQuery();
				 * while(rs.next()) { // 議고쉶 寃곌낵瑜� DTO�뿉 ���옣
				 * profile.setProf_follower(rs.getInt(1)); }
				 * 
				 * // DB�뿉�꽌 �깭洹� �궗�슜 �뀒�씠釉� 議고쉶
				 * 
				 * sql = "SELECT * FROM TAGUSE tu " + "INNER JOIN TAG t " +
				 * "ON tu.tag_id=t.tag_id " +
				 * "WHERE tu.tag_use_type=? AND tu.tag_use_type_id=?";
				 * 
				 * stmt = conn.prepareStatement(sql); stmt.setString(1,
				 * "profile"); stmt.setInt(2, profile.getProf_id()); rs =
				 * stmt.executeQuery();
				 * 
				 * while(rs.next()) { // 議고쉶 寃곌낵瑜� DTO�뿉 ���옣 if
				 * (rs.getString("tag_type").equals("tool")) {
				 * profile.setProf_language(rs.getString("t.tag_type"));
				 * 
				 * } }
				 */
			}
		} catch (Exception err) {
			System.out.println("getList() : " + err);
		} finally {
			freeConnection();
		}

		return foList;
	}

	/**
	 * following �궘�젣
	 * 
	 * @param fw_id
	 */
	public void deleteFollow(int fw_id) {
		String sql = "delete from following where fw_id =" + fw_id + "";

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
/*
//	
//	 *  follow 추가
//	 * @param fw_id
//	 * @return
//	 
	public Profile getFollow(int prof_id) {
		String sql = "SELECT * FROM PROFILE WHERE prof_id=?";
		//String sql = "";

		Profile profile = new Profile();
		//Tag dto1 = new Tag(); 
		
		try {
			conn = pool.getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				profile.setProf_img(rs.getString("prof_img"));//프로필사진
				profile.setProf_name(rs.getString("prof_name"));//개발자이름
			}
			
			// DB에서 Follower 수 조회
			sql = "SELECT COUNT(*) FROM FOLLOWING WHERE mem_id_receiver=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, profile.getProf_id());
			rs = stmt.executeQuery();
			while(rs.next()) {
				// 조회 결과를 DTO에 저장
				profile.setProf_follower(rs.getInt(1));
			}
			
			// DB에서 태그 사용 테이블 조회
			
			sql = "SELECT * FROM TAGUSE tu "
					+ "INNER JOIN TAG t "
					+ "ON tu.tag_id=t.tag_id "
					+ "WHERE tu.tag_use_type=? AND tu.tag_use_type_id=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "profile");
			stmt.setInt(2, profile.getProf_id());
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				// 조회 결과를 DTO에 저장
				if (rs.getString("tag_type").equals("tool")) {
					profile.setProf_language(new Tag()
							.setTag_id(rs.getInt("t.tag_id"))
							.setTag_type(rs.getString("t.tag_type"))
							.setTag_name(rs.getString("t.tag_name")));
			
		}
			}
			
		}catch (Exception err) {
			System.out.println("getList() : " + err);
		} finally {
			freeConnection();
		}
		
	
		
		return profile;
	}

	
//	 *  following 삭제
//	 * @param fw_id
//	 
	public void deleteFollow(int fw_id) {
		String sql = "delete from following where fw_id ="+fw_id+"";

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
	
*/
