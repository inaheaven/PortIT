package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Member;
import portit.model.dto.Portfolio;
import portit.model.dto.Project;

/**
 * 개발자 구성 화면
 */
public class ViewDao {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	
			
	/**
	 * DB연결 생성자
	 */
	public ViewDao() {
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
	 * 전체 멤버를 보여주는 메서드
	 */
	public List member_info() {
		ArrayList list = new ArrayList();
		String sql = "select profile.prof_img, profile.prof_name,  profile.prof_follower "
				+ "from profile "; 
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Member member = new Member(); 
				//member.setTag_name(rs.getString("tag_name"));
				member.setProf_img(rs.getString("prof_img"));
				member.setProf_name(rs.getString("prof_name"));
				member.setProf_follower(rs.getInt("prof_follower"));

				list.add(member);
			}
		}

		catch (Exception err) {
			System.out.println("developer_info() 에서 오류");
			err.printStackTrace();
		}

		finally {
			freeConnection();
		}
		return list;
	}

	/**
	 * 포트폴리오 정보를 불러오는 메서드	  
	 */
	public List portfolio_info() {
		ArrayList list = new ArrayList();
		String sql = "select distinct prof_name, pf_title, pf_like , ml_path "
				+ "from prof_pf join profile on prof_pf.prof_id = profile.prof_id "
				+ "join portfolio on portfolio.pf_id = prof_pf.pf_id , "
				+ "media_library" ;
		
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
			System.out.println("portfolio_info() 에서 오류");
			err.printStackTrace();
		}

		finally {
			freeConnection();
		}
		return list;
	}
	

	/**
	 * 프로젝트 간략 정보
	 */
	public List project_info() {
		ArrayList list = new ArrayList();
		String sql = "select distinct proj_title, tag.tag_name, prof_name, proj_intro, proj_to , ml_path, proj_regenddate, proj_regdate, "
				+ "trunc(proj_regenddate -sysdate) as d_day "
				+ "from member join proj_app on member.mem_id = proj_app.mem_id	"
				+ "join project on project.proj_id = proj_app.proj_id "
				+ "join profile on member.mem_id = profile.mem_id "
				+ "join MEDIA_LIBRARY on MEDIA_LIBRARY.ML_TYPE_ID = project.proj_ID  "
				+ "join tag_use  on tag_use_type_id = project.proj_id "
				+ "join tag on tag.tag_id = tag_use.tag_id "
				+ "where tag_use_type = 'project' ";
				
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Project project = new Project(); 
				project.setTag_name(rs.getString("tag_name"));
				project.setProj_title(rs.getString("proj_title"));
				project.setMl_path(rs.getString("ml_path"));
				project.setProj_to(rs.getInt("proj_to"));
				project.setProj_intro(rs.getString("proj_intro"));				
				project.setProf_name(rs.getString("prof_name"));				
				project.setProj_regenddate(rs.getDate("proj_regenddate"));
				project.setD_day(rs.getInt("d_day"));
				
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