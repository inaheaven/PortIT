package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Project;
import portit.model.dto.Tag;

/**
 * 프로젝트 모집 구성 화면
 */
public class Proj_viewDao {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	
	/**
	 * DB연결 생성자
	 */
	public Proj_viewDao() {
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
	 * 프로젝트 간략 정보
	 */
	public List project_info() {
		ArrayList list = new ArrayList();
		//쿼리문에  trunc(project.proj_regenddate - sysdate) as "D-Day" 빠짐
		String sql = "select distinct project.proj_id, tag.tag_name, project.proj_title,"
						+ " tag.tag_name, project.proj_to, trunc(project.proj_regenddate - sysdate) "
						+ "from tag join tag_use "
						+ "on tag.tag_id = tag_use.tag_id "
						+ "join project "
						+ "on tag_use.tag_use_type_id = project.proj_id";
				
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Project project = new Project(); 
				project.setTag_name(rs.getString("tag_name"));
				project.setProj_title(rs.getString("proj_title"));
				project.setProj_to(rs.getInt("proj_to"));
				
				//project.setProj_regenddate(rs.getDate("proj_regenddate")); date형식 받는데 오류
				
				list.add(project);
			}
		}
		
		catch (Exception err) {
			System.out.println("proj_load() 에서 오류");
			err.printStackTrace();
		}
		
		finally {
			freeConnection();
		}
		return list;
	}



}