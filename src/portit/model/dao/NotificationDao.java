package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Notification;

public class NotificationDao {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	private String sql = null;
	
	public NotificationDao() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch(Exception e) {
			System.out.println("커넥션 풀 오류 - NotificationDao()");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 전체 알림 테이블에서 '내' 알림 가져오기 
	 */
	public List myNoti(int mem_id, String sorting) {
		ArrayList ntList = new ArrayList();
		
		try {
			conn = pool.getConnection();
			sql = "SELECT * FROM NOTIFICATION WHERE mem_id_receiver = ? ";
			
			if(sorting.equals("like"))
				sql += " and nt_type = 'like'";
			else if(sorting.equals("upload"))
				sql += " and nt_type = 'upload'";
			else if(sorting.equals("message"))
				sql += " and nt_type = 'message'";
			else if(sorting.equals("project"))
				sql += " and nt_type = 'project'";
			else if(sorting.equals("follow"))
				sql += " and nt_type = 'follow'";
				
			sql += " ORDER BY nt_id DESC";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs = stmt.executeQuery();
			
			
			
			while(rs.next()) {
				Notification nt = new Notification();				
				nt.setNt_id(rs.getInt("nt_id"));
				nt.setMem_id_sender(rs.getInt("mem_id_sender"));
				nt.setMem_id_receiver(rs.getInt("mem_id_receiver"));
				nt.setNt_date(rs.getDate("nt_date"));
				nt.setNt_type(rs.getString("nt_type"));
				nt.setNt_type_id(rs.getInt("nt_type_id"));
				nt.setNt_isread(rs.getString("nt_isread").charAt(0));
				ntList.add(nt);
				System.out.println(rs.getInt("nt_id"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt, rs);
		}
		
		return ntList;
	}
	
	/**
	 * header의 알림에 가져오기
	 * 	- 안 읽은 알림 중 최신 5개를 띄운다.
	 * 
	 */
	public List headerNoti(int mem_id) {
		ArrayList ntList = new ArrayList();
		try {
			conn = pool.getConnection();
			sql = "SELECT * FROM NOTIFICATION WHERE mem_id_receiver = ? and nt_isread = 'n' ORDER BY nt_id DESC";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Notification nt = new Notification();				
				nt.setNt_id(rs.getInt("nt_id"));
				nt.setMem_id_sender(rs.getInt("mem_id_sender"));
				nt.setMem_id_receiver(rs.getInt("mem_id_receiver"));
				nt.setNt_date(rs.getDate("nt_date"));
				nt.setNt_type(rs.getString("nt_type"));
				nt.setNt_type_id(rs.getInt("nt_type_id"));
				nt.setNt_isread(rs.getString("nt_isread").charAt(0));
				ntList.add(nt);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt, rs);
		}
		
		return ntList;
	}
	
	/**
	 * mem_id_sender의 닉네임 가져오기
	 */
	public String getSenderName(int mem_id_sender){
		String mem_sender_name = "";
		String mem_email = "";
		try {
			conn = pool.getConnection();
			sql = "SELECT DISTINCT nt.mem_id_sender, prof.prof_nick, mem.mem_email "
					+ "FROM notification nt, profile prof, member mem "
					+ "WHERE nt.mem_id_sender = prof.mem_id and prof.mem_id = mem.mem_id and nt.mem_id_sender = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id_sender);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				mem_sender_name = rs.getString("prof_nick");
				mem_email = rs.getString("mem_email");
			}
			
			//  ***************** 닉네임 등록 안했을 경우 Email반환.
			if(mem_sender_name == null){ 
				StringTokenizer token = new StringTokenizer(mem_email, "@");
				mem_sender_name = token.nextToken();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt, rs);
		}
		
		return mem_sender_name;
	}
	
	
	/**
	 * 알림 추가
	 * 
	 *	***** 변수 nt_type *****
	 *	1. 내 포폴 좋아요 : like
	 *	2. 팔로워의 포폴 업로드 : upload
	 * 	3. 메세지 : message
	 * 	4. 프로젝트 신청 : project
	 *  5. 나를 팔로잉 : follow
	 *  
	 */
	public void insert(Notification nt) {
		try {
			conn = pool.getConnection();
			sql = "INSERT INTO notification(nt_id, mem_id_sender, mem_id_receiver, nt_date, nt_type, nt_type_id, nt_isread) "
					+ "VALUES(seq_nt_id.nextVal, ?, ?, sysdate, ?, ?, 'n')";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, nt.getMem_id_sender());
			stmt.setInt(2, nt.getMem_id_receiver());
			stmt.setString(3, nt.getNt_type());
			stmt.setInt(4, nt.getNt_type_id());
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt);
		}
	}
	
	/**
	 * 알림 삭제
	 */
	public void delete(int nt_id) {
		try {
			conn = pool.getConnection();
			sql = "DELETE FROM notification WHERE nt_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, nt_id);
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt);
		}
	}
	
	
	/**
	 * 알림 읽음
	 */
	public void isRead(int nt_id) {
		try {
			conn = pool.getConnection();
			sql = "UPDATE notification SET nt_isread = 'y' WHERE nt_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, nt_id);
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt);
		}
	}
	
	/**
	 * nt_isread 값 반환
	 */
	public char getNt_isread(int nt_id) {
		char isread = 0;
		
		try {
			conn = pool.getConnection();
			sql = "SELECT nt_isread FROM NOTIFICATION WHERE nt_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, nt_id);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				isread = rs.getString("nt_isread").charAt(0);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt, rs);
		}
		
		return isread;
	}
	
}