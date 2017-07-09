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
 * 
 * 통합 검색시, DB에서 자료 가지고 오기.
 *
 */
public class SearchDao {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs, rs2, rs3, rs4;
	private DBConnectionMgr pool;

	
	/**
	 * DB연결 생성자
	 */
	public SearchDao() {
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
	 * 포트폴리오 검색결과  (태그 제외)
	 */
	public List searchAll_port(String keyword,boolean lineup) {
		
		String sql = "";
		
		if(lineup == true){
			sql = "select distinct portfolio.pf_id, prof_name, pf_title, pf_like , ml_path, pf_regdate "
					+ "from prof_pf join profile on prof_pf.prof_id = profile.prof_id "
					+ "join portfolio on portfolio.pf_id = prof_pf.pf_id "
					+ "join tag_use on tag_use.tag_use_type_id = portfolio.pf_id "
					+ "join tag on tag.tag_id = tag_use.tag_id  "
					+ "join media_library on media_library.ml_type_id = portfolio.pf_id  "
					+ "where tag_use_type ='portfolio' and ml_type = 'portf' "
					+ "and (UPPER(tag.tag_name) like '%"+keyword+"%' or UPPER(portfolio.pf_title) like '%"+keyword+"%') "
					+ "order by pf_regdate desc";
		}
		else{
			sql = "select distinct  portfolio.pf_id, prof_name, pf_title, pf_like ,  ml_path, pf_regdate "
					+ "from prof_pf join profile on prof_pf.prof_id = profile.prof_id "
					+ "join portfolio on portfolio.pf_id = prof_pf.pf_id "
					+ "join tag_use on tag_use.tag_use_type_id = portfolio.pf_id "
					+ "join tag on tag.tag_id = tag_use.tag_id  "
					+ "join media_library on media_library.ml_type_id = portfolio.pf_id  "
					+ "where tag_use_type ='portfolio' and ml_type = 'portf' "
					+ "and (UPPER(tag.tag_name) like '%"+keyword+"%' or UPPER(portfolio.pf_title) like '%"+keyword+"%') "
					+ "order by portfolio.PF_LIKE desc";
		}
		
		ArrayList list = new ArrayList();
		try {

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Portfolio portfolio = new Portfolio(); 
				
				portfolio.setPf_id(rs.getInt("pf_id"));
				portfolio.setMl_path(rs.getString("ml_path"));
				//portfolio.setTag_name(rs.getString("tag_name"));
				portfolio.setPf_title(rs.getString("pf_title"));
				portfolio.setPf_like(rs.getInt("pf_like"));
				portfolio.setProf_name(rs.getString("prof_name"));
				portfolio.setPf_regdate(rs.getDate("pf_regdate"));
				int pf_id = rs.getInt("pf_id");
				
				portfolio.setTags(portfolio_tag(pf_id));
				list.add(portfolio);
			}
		}

		catch (Exception err) {
			System.out.println("searchAll_port() 에서 오류");
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
					+ " WHERE t.tag_id = tu.tag_id AND tu.tag_use_type = 'portfolio' AND tu.tag_use_type_id = ?) "
					+ " WHERE rownum < 4 ";

			ArrayList list = new ArrayList();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pf_id);
			rs3 = pstmt.executeQuery();

			List<String> tags = new ArrayList<>();
			while (rs3.next()) {
				tags.add(rs3.getString("tag_name"));
			}
			return tags;
		} 
		catch (Exception e) {
			System.out.println("포트폴리오 태그 " + e);
		}
		return null;
	}
	
	/**
	 * 포트폴리오 다중 검색 (오버로딩) (검색조건 태그 2개)
	 */
	public List searchAll_port(String keyword, String keyword2) {
		String sql = "select distinct  portfolio.pf_id, prof_name, pf_title, pf_like ,  pf_regdate "
				+ "from MEDIA_LIBRARY, TAG, Profile, portfolio, prof_pf, TAG_USE "
				+ "where (UPPER(tag.tag_name) like '%"+keyword+"%') and (UPPER(tag.tag_name) like '%"+keyword2+"%')  "
				+ "and prof_pf.PROF_ID = Profile.PROF_ID  "
				+ "and prof_pf.PF_ID = portfolio.PF_ID and TAG_USE.TAG_ID = TAG.TAG_ID "
				+ "and TAG_USE.TAG_USE_TYPE_ID= prof_pf.PF_ID "
				+ "and MEDIA_LIBRARY.ML_TYPE_ID = portfolio.PF_ID "
				+ "order by portfolio.PF_LIKE desc";
		
		
		ArrayList list = new ArrayList();
		try {
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Portfolio portfolio = new Portfolio(); 
				
				portfolio.setPf_id(rs.getInt("pf_id"));
				portfolio.setProf_name(rs.getString("prof_name"));
				portfolio.setPf_title(rs.getString("pf_title"));
				//portfolio.setMl_path(rs.getString("ml_path"));
				portfolio.setPf_like(rs.getInt("pf_like"));
				portfolio.setPf_regdate(rs.getDate("pf_regdate"));
				int pf_id = rs.getInt("pf_id");
				
				portfolio.setTags(portfolio_tag(pf_id));
				list.add(portfolio);
			}
		}
		
		catch (Exception err) {
			System.out.println("searchAll_keyword2() 에서 오류");
			err.printStackTrace();
		}
		
		finally {
			freeConnection();
		}
		return list;
	}
	
	/**
	 * 포트폴리오 다중 검색 (오버로딩) (검색조건 태그 3개)
	 */
	public List searchAll_port(String keyword, String keyword2, String keyword3) {
		String sql =  "select distinct  portfolio.pf_id, prof_name, pf_title, pf_like ,  pf_regdate "
				+ "from MEDIA_LIBRARY, TAG, Profile, portfolio, prof_pf, TAG_USE "
				+ "where (UPPER(tag.tag_name) like '%"+keyword+"%') and (UPPER(tag.tag_name) like '%"+keyword2+"%') and (UPPER(tag.tag_name) like '%"+keyword3+"%') "
				+ "and prof_pf.PROF_ID = Profile.PROF_ID  "
				+ "and prof_pf.PF_ID = portfolio.PF_ID and TAG_USE.TAG_ID = TAG.TAG_ID "
				+ "and TAG_USE.TAG_USE_TYPE_ID= prof_pf.PF_ID "
				+ "and MEDIA_LIBRARY.ML_TYPE_ID = portfolio.PF_ID "
				+ "order by portfolio.PF_LIKE desc";
		
		ArrayList list = new ArrayList();
		try {
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Portfolio portfolio = new Portfolio(); 
				
			//	portfolio.setMl_path(rs.getString("ml_path"));
				portfolio.setTag_name(rs.getString("tag_name"));
				portfolio.setPf_title(rs.getString("pf_title"));
				portfolio.setPf_like(rs.getInt("pf_like"));
				portfolio.setProf_name(rs.getString("prof_name"));
				int pf_id = rs.getInt("pf_id");
				
				portfolio.setTags(portfolio_tag(pf_id));
				list.add(portfolio);
			}
		}
		
		catch (Exception err) {
			System.out.println("searchAll_keyword3() 에서 오류");
			err.printStackTrace();
		}
		
		finally {
			freeConnection();
		}
		return list;
	}
	
	/**
	 * 멤버 검색 결과(태그 제외)
	 */
	public List searchAll_member(String keyword,boolean lineup) {
		
		String sql ="";
		if(lineup == true){
			sql = "select distinct profile.prof_id, prof_img, prof_name,  prof_follower, prof_regdate  "
					+ "from profile join tag_use  "
					+ "on tag_use.tag_use_type_id = profile.prof_id "
					+ "join tag  on tag.tag_id = tag_use.tag_id  "
					+ "where (tag_use_type = 'developer') and "
					+ "(UPPER(tag.tag_name) like '%"+keyword+"%' or UPPER(profile.prof_name) like '%"+keyword+"%') "
					+ "order by prof_regdate desc";
		}	
		else{
			sql = "select distinct profile.prof_id, prof_img, prof_name,  prof_follower, prof_regdate  "
					+ "from profile join tag_use  "
					+ "on tag_use.tag_use_type_id = profile.prof_id "
					+ "join tag  on tag.tag_id = tag_use.tag_id  "
					+ "where (tag_use_type = 'developer') and "
					+ "(UPPER(tag.tag_name) like '%"+keyword+"%' or UPPER(profile.prof_name) like '%"+keyword+"%') "
					+ "order by profile.prof_follower desc";
		}
		ArrayList list = new ArrayList();
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Member member = new Member(); 
				//member.setTag_name(rs.getString("tag_name"));
				member.setProf_id(rs.getInt("prof_id"));
				member.setProf_img(rs.getString("prof_img"));
				member.setProf_name(rs.getString("prof_name"));
				member.setProf_follower(rs.getInt("prof_follower"));
				member.setProf_regdate(rs.getDate("prof_regdate"));
				int prof_id = rs.getInt("prof_id");
				
				member.setTags(member_tag(prof_id));
				list.add(member);
			
			}
		}

		catch (Exception err) {
			System.out.println("search_member_info() 에서 오류");
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
					+ " WHERE t.tag_id = tu.tag_id AND tu.tag_use_type = 'developer' " + " AND tu.tag_use_type_id = ?) "
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
		return null;
	}
	/**
	 * 프로젝트 검색 결과(태그 제외)
	 */
	public List searchAll_proj(String keyword,boolean lineup) {
		String sql = "";
		if(lineup == true){
			sql =  "select distinct project.proj_id, proj_title, prof_name, proj_intro, proj_to ,  proj_regenddate, trunc(proj_regenddate - sysdate) as d_day  "
					+ "from project join mem_proj on project.proj_id = mem_proj.proj_id "
					+ "join member on member.mem_id = mem_proj.mem_id "
					+ "join profile on profile.mem_id = member.mem_id  "
					+ "join tag_use on tag_use.tag_use_type_id = project.proj_id "
					+ "join tag on tag.tag_id = tag_use.tag_id "
					+ "join media_library on media_library.ml_type_id = project.proj_id "
					+ "where tag_use_type = 'project' and ml_type = 'proj' "
					+ " and (UPPER(tag.tag_name) like '%"+keyword+"%' or UPPER(project.proj_title) like '%"+keyword+"%') ";
		}
		else{
			sql =  "select distinct project.proj_id, proj_title, prof_name, proj_intro, proj_to ,  proj_regenddate, trunc(proj_regenddate - sysdate) as d_day  "
					+ "from project join mem_proj on project.proj_id = mem_proj.proj_id "
					+ "join member on member.mem_id = mem_proj.mem_id "
					+ "join profile on profile.mem_id = member.mem_id  "
					+ "join tag_use on tag_use.tag_use_type_id = project.proj_id "
					+ "join tag on tag.tag_id = tag_use.tag_id "
					+ "join media_library on media_library.ml_type_id = project.proj_id "
					+ "where tag_use_type = 'project' and ml_type = 'proj' "
					+ " and (UPPER(tag.tag_name) like '%"+keyword+"%' or UPPER(project.proj_title) like '%"+keyword+"%') ";

		}
		ArrayList list = new ArrayList();
				
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Project project = new Project(); 
				project.setProj_title(rs.getString("proj_title"));
				project.setProf_name(rs.getString("prof_name"));
				project.setProj_intro(rs.getString("proj_intro"));
				project.setProj_to(rs.getInt("proj_to"));
				//project.setProj_regdate(rs.getDate("proj_regdate"));
				project.setProj_regenddate(rs.getDate("proj_regenddate")); 
				//project.setMl_path(rs.getString("ml_path"));
				project.setD_day(rs.getInt("d_day"));
				
				int proj_id = rs.getInt("proj_id");				
				project.setTags(project_tag(proj_id));			
		
				list.add(project);
			}
		}
		
		catch (Exception err) {
			System.out.println("search_proj_load() 에서 오류");
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
					+ " WHERE t.tag_id = tu.tag_id AND tu.tag_use_type = 'project' " + " AND tu.tag_use_type_id = ?) "
					+ "  WHERE rownum < 4 ";

			ArrayList list = new ArrayList();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, proj_id);
			rs4 = pstmt.executeQuery();

			List<String> tags = new ArrayList<>();
			while (rs4.next()) {
				tags.add(rs4.getString("tag_name"));
			}
			return tags;
		} 
		catch (Exception e) {
			System.out.println("proj_tag 오류" + e);
		}
		return null;
	}
}