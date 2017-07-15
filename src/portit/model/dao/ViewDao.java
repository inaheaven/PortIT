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
import portit.model.dto.Timeline;

/**
 * 개발자 구성 화면
 */
public class ViewDao {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs, rs2, rs3, rs4;
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
	 * 포트폴리오 정보를 불러오는 메서드 (태그 제외)	  
	 */
	public List portfolio_info() {
		ArrayList list = new ArrayList();
		String sql = "select distinct portfolio.pf_id, prof_name, pf_title, pf_like , pf_regdate "
				+ "from prof_pf join profile on prof_pf.prof_id = profile.prof_id "
				+ "join portfolio on portfolio.pf_id = prof_pf.pf_id "
				+ "join tag_use on tag_use.tag_use_type_id = portfolio.pf_id "
				+ "join tag on tag.tag_id = tag_use.tag_id  "
				+ "join media_library on media_library.ml_type_id = portfolio.pf_id  "
				+ "where tag_use_type ='pf' and ml_type = 'pf' "
				+ "order by pf_regdate desc";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Portfolio portfolio = new Portfolio();
				portfolio.setPf_id(rs.getInt("pf_id"));
				//portfolio.setMl_path(rs.getString("ml_path"));
				//portfolio.setTag_name(rs.getString("tag_name"));
				portfolio.setPf_title(rs.getString("pf_title"));
				portfolio.setPf_like(rs.getInt("pf_like"));
				portfolio.setProf_name(rs.getString("prof_name"));
				int pf_id = rs.getInt("pf_id");
				
				portfolio.setTags(portfolio_tag(pf_id));
				portfolio.setMl_path2(port_media(pf_id));
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
	 *  포트폴리오 태그 조회
	 */
	public List portfolio_tag(int pf_id) {

		try {
			String sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ " WHERE t.tag_id = tu.tag_id AND tu.tag_use_type = 'pf' AND tu.tag_use_type_id = ?) "
					+ " WHERE rownum < 4 ";

			ArrayList list = new ArrayList();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pf_id);
			rs2 = pstmt.executeQuery();

			List<String> tags = new ArrayList<>();
			while (rs2.next()) {
				tags.add(rs2.getString("tag_name"));
			}
			return tags;
		} 
		catch (Exception e) {
			System.out.println("포트폴리오 태그 " + e);
		}
		
		/* 심봤다
		 
		  finally{
			pool.freeConnection(con, pstmt);
		}
		  */
		
		
		return null;
	}
	/**
	 *	포트폴리오 미디어 (1개만) 
	 */
	public List port_media(int pf_id) {
		try {
			String sql = "SELECT ml_path FROM (SELECT * FROM portfolio pf, media_library ml "
					+ " WHERE pf.pf_id = ml.ml_type_id AND ml.ml_type = 'pf' AND ml.ml_type_id = ? ) "
					+ " WHERE rownum < 2 ";
			
			ArrayList list = new ArrayList();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pf_id);
			rs4 = pstmt.executeQuery();
			
			List<String> media_path = new ArrayList<>();
			while (rs3.next()) {
				media_path.add(rs4.getString("ml_path"));
			}
			return media_path;
		} 
		catch (Exception e) {
			System.out.println("ml_path(proj) 오류" + e);
		}
		
		/* 심봤다
		 
		  finally{
			pool.freeConnection(con, pstmt);
		}
		  */
		
		return null;
	}
	/**
	 * 포트폴리오 정보를 불러오는 메서드 (하나의 포폴 조회)	  
	 */
	public List portfolio_info(int pf_id) {
		ArrayList list = new ArrayList();
		String sql = "select distinct portfolio.pf_id, prof_name, pf_title, pf_like ,  ml_path, pf_regdate "
				+ "from prof_pf join profile on prof_pf.prof_id = profile.prof_id "
				+ "join portfolio on portfolio.pf_id = prof_pf.pf_id "
				+ "join tag_use on tag_use.tag_use_type_id = portfolio.pf_id "
				+ "join tag on tag.tag_id = tag_use.tag_id  "
				+ "join media_library on media_library.ml_type_id = portfolio.pf_id  "
				+ "where tag_use_type ='pf' and ml_type = 'pf' and portfolio.pf_id = ? "
				+ "order by pf_regdate desc";
		
		try {
			
			pstmt = con.prepareStatement(sql);		
			pstmt.setInt(1, pf_id);
			rs = pstmt.executeQuery();
					
			while (rs.next()) {
				Portfolio portfolio = new Portfolio();
				portfolio.setMl_path(rs.getString("ml_path"));
				//portfolio.setTag_name(rs.getString("tag_name"));
				portfolio.setPf_title(rs.getString("pf_title"));
				portfolio.setPf_like(rs.getInt("pf_like"));
				portfolio.setPf_id(rs.getInt("pf_id"));
				portfolio.setProf_name(rs.getString("prof_name"));

			}
		}
		
		catch (Exception err) {
			System.out.println("portfolio_info_(pf_id) 에서 오류");
			err.printStackTrace();
		}
		
		finally {
			freeConnection();
		}
		return list;
	}

	/**
	 * 전체 멤버를 보여주는 메서드(태그x)
	 */
	public List member_info() {
		ArrayList list = new ArrayList();
		String sql = "select distinct mem_id, prof_id, prof_img, prof_name,  prof_follower  "
				+ "from profile join tag_use  "
				+ "on tag_use.tag_use_type_id = profile.prof_id "
				+ "join tag  on tag.tag_id = tag_use.tag_id  "
				+ "where (tag_use_type = 'prof')  ";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Member member = new Member(); 
				//member.setTag_name(rs.getString("tag_name"));
				member.setProf_img(rs.getString("prof_img"));
				member.setProf_name(rs.getString("prof_name"));
				member.setProf_follower(rs.getInt("prof_follower"));
				member.setProf_id(rs.getInt("prof_id"));
				member.setMem_id(rs.getInt("mem_id"));
				
				int prof_id = rs.getInt("prof_id");
				
				member.setTags(member_tag(prof_id));
				list.add(member);
			}
		}

		catch (Exception err) {
			System.out.println("mem_info() 에서 오류");
			err.printStackTrace();
		}

		finally {
			freeConnection();
		}
		return list;
	}
	
	/**
	 * 전체 멤버를 보여주는 메서드(태그o)
	 */
	public List member_tag(int prof_id) {
		try {
			String sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ " WHERE t.tag_id = tu.tag_id AND tu.tag_use_type = 'prof' " + " AND tu.tag_use_type_id = ?) "
					+ " WHERE rownum < 4 ";

			ArrayList list = new ArrayList();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prof_id);
			rs2 = pstmt.executeQuery();

			List<String> tags = new ArrayList<>();
			while (rs2.next()) {
				tags.add(rs2.getString("tag_name"));
			}
			return tags;
		} 
		catch (Exception e) {
			System.out.println("member_tag 오류 " + e);
		}
		
		/* 심봤다
		 
		  finally{
			pool.freeConnection(con, pstmt);
		}
		  */
		
		
		return null;
	}

	/**
	 * 전체 멤버를 보여주는 메서드(한명의 멤버 조회)
	 */
	public List member_info(int prof_id) {
		ArrayList list = new ArrayList();
		String sql = "select distinct prof_id, prof_img, prof_name,  prof_follower, prof_nick, prof_regdate  "
					+ "from profile join tag_use  "
					+ "on tag_use.tag_use_type_id = profile.prof_id "
					+ "join tag  on tag.tag_id = tag_use.tag_id  "
					+ "where (tag_use_type = 'prof') and  prof_id=? "
					+ "order by prof_regdate desc"; 
				
		
		try {
			pstmt = con.prepareStatement(sql);		
			pstmt.setInt(1, prof_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Member member = new Member(); 
				//member.setTag_name(rs.getString("tag_name"));
				member.setProf_img(rs.getString("prof_img"));
				member.setProf_name(rs.getString("prof_name"));
				member.setProf_nick(rs.getString("prof_nick"));
				member.setProf_follower(rs.getInt("prof_follower"));
				member.setProf_id(rs.getInt("prof_id"));
		
			}
		}

		catch (Exception err) {
			System.out.println("mem_info(id) 에서 오류");
			err.printStackTrace();
		}

		finally {
			freeConnection();
		}
		return list;
	}
	/**
	 * 프로젝트 간략 정보 (태그x)
	 */
	public List project_info() {
		ArrayList list = new ArrayList();
		String sql = "select distinct project.proj_id, ml_path, proj_title, prof_name, proj_intro, "
				+ "proj_to ,  proj_regenddate, trunc(proj_regenddate - sysdate) as d_day  "
				+ "from project join mem_proj on project.proj_id = mem_proj.proj_id "
				+ "join member on member.mem_id = mem_proj.mem_id "
				+ "join profile on profile.mem_id = member.mem_id  "
				+ "join tag_use on tag_use.tag_use_type_id = project.proj_id "
				+ "join tag on tag.tag_id = tag_use.tag_id "
				+ "join media_library on media_library.ml_type_id = project.proj_id "
				+ "where tag_use_type = 'proj' and ml_type = 'proj' ";
				
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Project project = new Project(); 
				//project.setTag_name(rs.getString("tag_name"));
				project.setProj_id(rs.getInt("proj_id"));
				project.setProj_title(rs.getString("proj_title"));
				project.setMl_path(rs.getString("ml_path"));
				project.setProj_to(rs.getInt("proj_to"));
				project.setProj_intro(rs.getString("proj_intro"));				
				project.setProf_name(rs.getString("prof_name"));				
				project.setProj_regenddate(rs.getDate("proj_regenddate"));
				project.setD_day(rs.getInt("d_day"));
				
				int proj_id = rs.getInt("proj_id");				
				project.setTags(project_tag(proj_id));
				project.setTags2(project_tag2(proj_id));
			
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
	 * 프로젝트 간략 정보 (태그o)
	 */
	public List project_tag(int proj_id) {
		try {
			String sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ " WHERE t.tag_id = tu.tag_id AND tu.tag_use_type = 'proj' AND tu.tag_use_type_id = ?) "
					+ "  WHERE rownum < 4 ";

			ArrayList list = new ArrayList();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, proj_id);
			rs2 = pstmt.executeQuery();

			List<String> tags = new ArrayList<>();
			while (rs2.next()) {
				tags.add(rs2.getString("tag_name"));
			}
			return tags;
		} 
		catch (Exception e) {
			System.out.println("proj_tag 오류" + e);
		}
		
		/* 심봤다
		 
		  finally{
			pool.freeConnection(con, pstmt);
		}
		  */
		
		
		return null;
	}
	/**
	 * 프로젝트 간략 정보 (태그o -> 분야(필드))
	 */
	public List project_tag2(int proj_id) {
		try {
			String sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ " WHERE t.tag_id = tu.tag_id AND tu.tag_use_type = 'proj' "
					+ " AND tu.tag_use_type_id = ? AND t.tag_type = 'field' )" ;
			
			ArrayList list = new ArrayList();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, proj_id);
			rs3 = pstmt.executeQuery();
			
			List<String> tags = new ArrayList<>();
			while (rs3.next()) {
				tags.add(rs3.getString("tag_name"));
			}
			return tags;
		} 
		catch (Exception e) {
			System.out.println("proj_tag(필드) 오류" + e);
		}
		
		/* 심봤다
		 
		  finally{
			pool.freeConnection(con, pstmt);
		}
		  */
		
		
		return null;
	}
	
	/**
	 * 프로젝트 간략 정보 (하나의 프로젝트 조회)
	 */
	public List project_info(int proj_id) {
		ArrayList list = new ArrayList();
		String sql =  "select distinct project.proj_id, ml_path, proj_title, prof_name, proj_intro, proj_to ,  proj_regenddate, trunc(proj_regenddate - sysdate) as d_day  "
				+ "from project join mem_proj on project.proj_id = mem_proj.proj_id "
				+ "join member on member.mem_id = mem_proj.mem_id "
				+ "join profile on profile.mem_id = member.mem_id  "
				+ "join tag_use on tag_use.tag_use_type_id = project.proj_id "
				+ "join tag on tag.tag_id = tag_use.tag_id "
				+ "join media_library on media_library.ml_type_id = project.proj_id "
				+ "where tag_use_type = 'proj' and ml_type = 'proj' and  proj_id=? ";
				
			
		try {
			pstmt = con.prepareStatement(sql);		
			pstmt.setInt(1, proj_id);
			rs = pstmt.executeQuery();

			
			while (rs.next()) {
				Project project = new Project(); 
				//project.setTag_name(rs.getString("tag_name"));
				project.setProj_title(rs.getString("proj_title"));
				project.setMl_path(rs.getString("ml_path"));
				project.setProj_id(rs.getInt("proj_id"));
				project.setProj_intro(rs.getString("proj_intro"));				
				project.setProf_name(rs.getString("prof_name"));				
				project.setProj_regenddate(rs.getDate("proj_regenddate"));
				//project.setD_day(rs.getInt("d_day"));
				
			}
		}
		catch (Exception err) {
			System.out.println("proj_id() 에서 오류");
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
		String sql = "select distinct portfolio.pf_id, prof_name, pf_title, pf_like , prof_name , pf_regdate "
				+ "from prof_pf join profile on prof_pf.prof_id = profile.prof_id "
				+ "join portfolio on portfolio.pf_id = prof_pf.pf_id "
				+ "join tag_use on tag_use.tag_use_type_id = portfolio.pf_id "
				+ "join tag on tag.tag_id = tag_use.tag_id  "
				+ "join media_library on media_library.ml_type_id = portfolio.pf_id  "
				+ "join member on member.mem_id = profile.mem_id "
				+ "where tag_use_type ='pf' and ml_type = 'pf' and "
				+ "member.mem_id="+mem_id + " " 
				+ "order by pf_regdate desc";
		try {

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Timeline timeline = new Timeline();
				//timeline.setMl_path(rs.getString("ml_path"));
				timeline.setPf_id(rs.getInt("pf_id"));
				timeline.setPf_title(rs.getString("pf_title"));
				timeline.setPf_like(rs.getInt("pf_like"));
				timeline.setProf_name(rs.getString("prof_name"));
				timeline.setPf_regdate(rs.getDate("pf_regdate"));
				
				int pf_id = rs.getInt("pf_id");
				
				timeline.setTags(timeline_info_tag(pf_id));
				timeline.setProf_id2(timeline_info_like(pf_id));
				timeline.setMl_path2(timeline_media(pf_id));
				list.add(timeline);

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
	/**
	 * 타임라인 정보를 불러오는 메서드	(태그) 
	 */
	public List timeline_info_tag(int pf_id) {
		try {
			String sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ " WHERE t.tag_id = tu.tag_id AND tu.tag_use_type = 'pf' AND tu.tag_use_type_id = ?) "
					+ " WHERE rownum < 4 ";

			ArrayList list = new ArrayList();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pf_id);
			rs2 = pstmt.executeQuery();

			List<String> tags = new ArrayList<>();
			while (rs2.next()) {
				tags.add(rs2.getString("tag_name"));
			}
			return tags;
		} 
		catch (Exception e) {
			System.out.println("timeline 태그 " + e);
		}
		
		
		/* 심봤다
		 
		  finally{
			pool.freeConnection(con, pstmt);
		}
		  */
		
		return null;
	}
	
	/**
	 *	좋아요 
	 */
	public List timeline_info_like(int pf_id) {
		try {
			String sql = "select  prof_name, profile.prof_id, member.mem_id , portfolio.pf_id  "
					+ "from member join pf_like on member.mem_id = pf_like.mem_id "
					+ "join portfolio on portfolio.pf_id = pf_like.pf_id "
					+ "join profile on profile.mem_id = member.mem_id "
					+ "where portfolio.pf_id=?";
			
			ArrayList list = new ArrayList();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pf_id);
			rs2 = pstmt.executeQuery();
			
			List<String> like = new ArrayList<>();
			while (rs2.next()) {
				like.add(rs2.getString("prof_name"));
			}
			return like;
		} 
		catch (Exception e) {
			System.out.println("timeline like " + e);
		}
		
		
		/* 심봤다
		 
		  finally{
			pool.freeConnection(con, pstmt);
		}
		  */
		
		return null;
	}
	/**
	 * 타임라인 (미디어 라이브러리)
	 */
	public List timeline_media(int pf_id) {
		try {
			String sql = "SELECT ml_path FROM (SELECT * FROM portfolio pf, media_library ml "
					+ " WHERE pf.pf_id = ml.ml_type_id AND ml.ml_type = 'pf' AND ml.ml_type_id = ? ) "
					+ " WHERE rownum < 3 ";
			
			ArrayList list = new ArrayList();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pf_id);
			rs4 = pstmt.executeQuery();
			
			List<String> media_path = new ArrayList<>();
			while (rs4.next()) {
				media_path.add(rs4.getString("ml_path"));
			}
			return media_path;
		} 
		catch (Exception e) {
			System.out.println("ml_path(proj) 오류" + e);
		}
		
		/* 심봤다
		 
		  finally{
			pool.freeConnection(con, pstmt);
		}
		  */
		
		return null;
	}
}