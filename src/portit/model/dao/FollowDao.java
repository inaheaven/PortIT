package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;


import portit.model.db.DBConnectionMgr;
import portit.model.dto.Follow;
import portit.model.dto.Profile;

public class FollowDao {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs, rs2, rs3;
	private DBConnectionMgr pool;
	private String sql = null;

	public FollowDao() {
		try {
			pool = DBConnectionMgr.getInstance();
			
		} catch (Exception e) {
			System.out.println("커넥션 풀 오류 - FollowDao()");
			e.printStackTrace();
		} 
	}
	
	
	/**
	 * 내가 팔로우한 멤버 리스트 가져오기
	 * @param mem_id (세션의 loginId)
	 * @return list
	 */
	public List myFollowing(int mem_id) {
		ArrayList fwList = new ArrayList();
		
		try {
			conn = pool.getConnection();                        // 보낸 사람이 '(로그인 한)나'
			sql = "SELECT * FROM following fw, profile prof "
					+ "WHERE fw.mem_id_receiver = prof.mem_id AND mem_id_sender = ? ORDER BY fw_id DESC"; 
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs = stmt.executeQuery();
						
			int i = 0;
			while(rs.next()) {
				i++;
				Follow fw = new Follow();
				fw.setFw_id(rs.getInt("fw_id"));
				fw.setMem_id_sender(rs.getInt("mem_id_sender"));
				fw.setMem_id_receiver(rs.getInt("mem_id_receiver"));
				fw.setFw_date(rs.getDate("fw_date"));
				
				int mem_id_receiver = rs.getInt("mem_id_receiver");
				int prof_id = rs.getInt("prof_id");
				
				getProfile(fw, mem_id_receiver);				
				getTag(fw, prof_id);
				
				fwList.add(fw);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt, rs);
		}
		return fwList; 
	}
	
	/**
	 * 팔로우한 사람의 프로필 정보 가져오기 (이미지, 닉네임, 팔로워 수)
	 * @param mem_id (mem_id_receiver)
	 * @return 
	 */
	public void getProfile(Follow fw, int mem_id){
		try{
			sql = "SELECT prof_img, prof_nick, prof_follower FROM profile WHERE mem_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,  mem_id);
			rs2 = stmt.executeQuery();
			
			while(rs2.next()){		
				fw.setProf_img(rs2.getString("prof_img"));
				fw.setProf_nick(rs2.getString("prof_nick"));
				fw.setProf_follower(rs2.getInt("prof_follower"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 팔로우한 사람의 프로필 정보 가져오기 - 태그 부분 종합해서 최대 3개만 출력되도록
	 * @param mem_id (mem_id_receiver)
	 * @return 
	 */
	public void getTag(Follow fw, int prof_id) {
		try{
			sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ "WHERE t.tag_id = tu.tag_id AND tu.tag_use_type = 'prof' AND tu.tag_use_type_id = ? "
					+ "ORDER BY DBMS_RANDOM.RANDOM) WHERE rownum < 4";  // 랜덤하게 3개
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs3 = stmt.executeQuery();
			
			List<String> tags = new ArrayList<>();			
			while(rs3.next()){	
				tags.add(rs3.getString("tag_name"));
			}
			fw.setTags(tags);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	

	/**
	 * 팔로우 삭제 (마이페이지에서)
	 * @param fw_id
	 */
	public void delete(int fw_id) {
		try {
			conn = pool.getConnection();
			
			// 팔로우 받는 사람 id 가져오기
			sql = "SELECT mem_id_receiver FROM following WHERE fw_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, fw_id);
			rs = stmt.executeQuery();
			int mem_id_receiver = 0;
			if(rs.next()) {
				mem_id_receiver = rs.getInt("mem_id_receiver");
			}
			
			// 팔로우 받는 사람의 카운트 -1
			sql = "UPDATE profile SET prof_follower = prof_follower-1 WHERE mem_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id_receiver);
			stmt.executeUpdate();
				
			// 그 후, 팔로우 삭제
			sql = "DELETE FROM following WHERE fw_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, fw_id);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt);
		}
	}

	
	
	/**
	 * 팔로우했을 때
	 */
	public void insert(int mem_id_sender, int mem_id_receiver) { // 로그인id(나), 팔로우받는 사람
		try {
			conn = pool.getConnection();
			
			// 팔로우 추가
			sql = "INSERT INTO following(fw_id, mem_id_sender, mem_id_receiver, fw_date) "
					+ "VALUES(seq_fw_id.nextVal)";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
			
			// 팔로우 받은 사람 카운트 +1
			sql = "UPDATE profile SET prof_follower = prof_follower+1 WHERE mem_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id_receiver);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt);
		}
	}
	
	
	/**
	 * '(로그인한 )나'가 해당 멤버를 팔로우 했는지 T/F
	 */
	public boolean isFollowing(int mem_id_sender, int mem_id_receiver) {
		boolean isFollowing = false;
		try {
			conn = pool.getConnection();
			
			// 팔로우 테이블에 있는지 없는지
			sql = "SELECT * FROM following WHERE mem_id_sender = ? AND mem_id_receiver = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id_sender);
			stmt.setInt(2, mem_id_receiver);
			rs = stmt.executeQuery();
			
			if(rs.next()) { // 팔로잉 했다
				isFollowing = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt);
		}
		
		return isFollowing;
	}
	
	
	
	/**
	 * 팔로우 취소 ( 멤버 상세 페이지에서)
	 * @param fw_id
	 */
	public void cancel(int mem_id_sender, int mem_id_receiver) {
		try {
			conn = pool.getConnection();
			
			// 팔로우 받는 사람의 카운트 -1
			sql = "UPDATE profile SET prof_follower = prof_follower-1 WHERE mem_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id_receiver);
			stmt.executeUpdate();
				
			// 그 후, 팔로우 삭제
			sql = "DELETE FROM following WHERE mem_id_sender = ? AND mem_id_receiver = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id_sender);
			stmt.setInt(2, mem_id_receiver);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt);
		}

	}

	/**
	 * mem_receiver_id by prof_id 
	 */
	public int memidByprofid(int prof_id) {
		int mem_id = 0;
		
		try {
			conn = pool.getConnection();
			
			// 팔로우 받는 사람의 카운트 -1
			sql = "select mem_id from profile where prof_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				mem_id = rs.getInt("mem_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt);
		
		return mem_id;
		}
	}
	
	/**
	 * 팔로우 수 가져오기
	 */
	public int getFollowers(int prof_id) {
		int followers = 0;
		
		try {
			conn = pool.getConnection();
			
			// 팔로우 받는 사람의 카운트 -1
			sql = "select prof_follower from profile where prof_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				followers = rs.getInt("prof_follower");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt);
		}
		return followers;
	}
}

