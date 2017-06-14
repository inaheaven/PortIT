package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Profile;
import portit.model.dto.Tag;

/**
 * 프로필 관련 DAO
 * @author gnsngck
 *
 */
public class ProfileDao {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	private String sql = null;
	
	public ProfileDao() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("커넥션 풀 오류 - MemberDao()");
			e.printStackTrace();
		}
	}
	
	/**
	 * DB 연결
	 */
	private void getConnection() {
		try {
			conn = pool.getConnection();
			if (conn != null) System.out.println("DB 접속");
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
			if (conn != null) System.out.println("DB 접속 해제");
		} catch (Exception e) {
			System.out.println("DB 접속해제 오류 - freeConnection()");
			e.printStackTrace();
		}
	}
	
	/**
	 * 같은 프로필 번호를 쓰는 프로필이 있는지 검사
	 * @param prof_id
	 * @return 존재하는 프로필 번호
	 */
	public int findExistingProfile(int prof_id) {
		int result = 0;
		try {
			sql = "SELECT prof_id FROM PROFILE WHERE prof_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs = stmt.executeQuery();
			rs.next();
			if (prof_id == rs.getInt(1)) {
				result = rs.getInt(1);
			} else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * (수정·삭제시) 작성자가 맞는지 검사
	 * @param mem_id
	 * @param prof_id
	 * @return true/false
	 */
	public boolean checkAccessRight(int mem_id, int prof_id) {
		boolean flag = false;
		try {
			sql = "SELECT mem_id FROM PROFILE WHERE prof_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs = stmt.executeQuery();
			rs.next();
			if(mem_id == rs.getInt(1)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 개별 프로필 조회
	 * @param _profile
	 * @return
	 */
	public Profile select(int prof_id) {
		Profile profile = new Profile();
		getConnection();
		try {
			// DB에서 프로필 테이블 조회
			sql = "SELECT * FROM PROFILE WHERE prof_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				// 조회 결과를 DTO에 저장
				profile.setProf_id(rs.getInt("prof_id"))
				.setMem_id(rs.getInt("mem_id"))
				.setProf_nick(rs.getString("prof_nick"))
				.setProf_name(rs.getString("prof_name"))
				.setProf_intro(rs.getString("prof_intro"))
				.setProf_img(rs.getString("prof_img"))
				.setProf_background(rs.getString("prof_background"))
				.setProf_website(rs.getString("prof_website"))
				.setProf_github(rs.getString("prof_github"))
				.setProf_facebook(rs.getString("prof_facebook"))
				.setProf_regdate(new SimpleDateFormat("yyyy-MM-dd")
						.parse(rs.getString("prof_regdate")));
				
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
					if (rs.getString("tag_type").equals("language")) {
						profile.setProf_language(new Tag()
								.setTag_id(rs.getInt("t.tag_id"))
								.setTag_type(rs.getString("t.tag_type"))
								.setTag_name(rs.getString("t.tag_name")));
					}
					if (rs.getString("tag_type").equals("tool")) {
						profile.setProf_language(new Tag()
								.setTag_id(rs.getInt("t.tag_id"))
								.setTag_type(rs.getString("t.tag_type"))
								.setTag_name(rs.getString("t.tag_name")));
					}
					if (rs.getString("tag_type").equals("field")) {
						profile.setProf_language(new Tag()
								.setTag_id(rs.getInt("t.tag_id"))
								.setTag_type(rs.getString("t.tag_type"))
								.setTag_name(rs.getString("t.tag_name")));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// DB 접속 해제
			freeConnection();
		}
		return profile;
	}
	
	/**
	 * 프로필 목록 조회
	 * @return
	 */
	public List<Profile> selectAll() {
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		getConnection();
		
		try {
			// DB에서 프로필 테이블 조회
			Profile profile = new Profile();
			sql = "SELECT * FROM PROFILE ORDER BY ? DESC";
			// 쿼리문에 정렬기준 입력
			String sortBy = "";
			if (sortBy.equals("")) {
				stmt.setString(1, "");
			} else if (sortBy.equals("")) {
				stmt.setString(1, "");
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				// 조회 결과를 DTO에 저장
				profile.setProf_id(rs.getInt("prof_id"))
				.setMem_id(rs.getInt("mem_id"))
				.setProf_nick(rs.getString("prof_nick"))
				.setProf_name(rs.getString("prof_name"))
				.setProf_intro(rs.getString("prof_intro"))
				.setProf_img(rs.getString("prof_img"))
				.setProf_background(rs.getString("prof_background"))
				.setProf_website(rs.getString("prof_website"))
				.setProf_github(rs.getString("prof_github"))
				.setProf_facebook(rs.getString("prof_facebook"))
				.setProf_regdate(new SimpleDateFormat("yyyy-MM-dd")
						.parse(rs.getString("prof_regdate")));
				
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
					if (rs.getString("tag_type").equals("language")) {
						profile.setProf_language(new Tag()
								.setTag_id(rs.getInt("t.tag_id"))
								.setTag_type(rs.getString("t.tag_type"))
								.setTag_name(rs.getString("t.tag_name")));
					}
					if (rs.getString("tag_type").equals("tool")) {
						profile.setProf_language(new Tag()
								.setTag_id(rs.getInt("t.tag_id"))
								.setTag_type(rs.getString("t.tag_type"))
								.setTag_name(rs.getString("t.tag_name")));
					}
					if (rs.getString("tag_type").equals("field")) {
						profile.setProf_language(new Tag()
								.setTag_id(rs.getInt("t.tag_id"))
								.setTag_type(rs.getString("t.tag_type"))
								.setTag_name(rs.getString("t.tag_name")));
					}
				}
				profiles.add(profile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return profiles;
	}
	
	/**
	 * 프로필 작성
	 * @param profile
	 */
	public void insert(Profile profile) {
		getConnection();
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
	}
	
	/**
	 * 프로필 수정
	 * @param profile
	 */
	public void update(Profile profile) {
		getConnection();
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
	}

}
