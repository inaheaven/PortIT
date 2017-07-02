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
	private ResultSet rs;
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
			sql = "select distinct MEDIA_LIBRARY.ML_PATH, portfolio.PF_TITLE ,Profile.PROF_NAME, portfolio.PF_LIKE "
					+ "FROM media_library, tag, Profile, portfolio, prof_pf, tag_use "
					+ "where prof_pf.PROF_ID = Profile.PROF_ID  "
					+ "and prof_pf.PF_ID = portfolio.PF_ID and TAG_USE.TAG_ID = TAG.TAG_ID  "
					+ "and TAG_USE.TAG_USE_TYPE_ID= prof_pf.PF_ID  "
					+ "and MEDIA_LIBRARY.ML_TYPE_ID = portfolio.PF_ID  "
					+ "and tag_use_type = 'portfolio' and  "
					+ " (UPPER(tag.tag_name) like '%"+keyword+"%' or UPPER(portfolio.pf_title) like '%"+keyword+"%') "
					+ "order by portfolio.PF_LIKE desc";
		}
		else{
			sql = "select distinct MEDIA_LIBRARY.ML_PATH, portfolio.PF_TITLE ,Profile.PROF_NAME, portfolio.PF_LIKE "
					+ "FROM media_library, tag, Profile, portfolio, prof_pf, tag_use "
					+ "where prof_pf.PROF_ID = Profile.PROF_ID  "
					+ "and prof_pf.PF_ID = portfolio.PF_ID and TAG_USE.TAG_ID = TAG.TAG_ID  "
					+ "and TAG_USE.TAG_USE_TYPE_ID= prof_pf.PF_ID  "
					+ "and MEDIA_LIBRARY.ML_TYPE_ID = portfolio.PF_ID  "
					+ "and tag_use_type = 'portfolio' and  "
					+ " (UPPER(tag.tag_name) like '%"+keyword+"%' or UPPER(portfolio.pf_title) like '%"+keyword+"%') "
					+ "order by portfolio.PF_LIKE desc";
		}
		
		ArrayList list = new ArrayList();
		try {

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Portfolio portfolio = new Portfolio(); 
				
				portfolio.setMl_path(rs.getString("ml_path"));
				//portfolio.setTag_name(rs.getString("tag_name"));
				portfolio.setPf_title(rs.getString("pf_title"));
				portfolio.setPf_like(rs.getInt("pf_like"));
				portfolio.setProf_name(rs.getString("prof_name"));
				
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
	public List searchAll_port_tag(String keyword,boolean lineup) {
		
		String sql = "";
		
		if(lineup == true){
			sql = "select distinct ML_PATH, tag.tag_name, portfolio.PF_TITLE ,Profile.PROF_NAME, portfolio.PF_LIKE "
					+ "FROM media_library, tag, Profile, portfolio, prof_pf, tag_use "
					+ "where prof_pf.PROF_ID = Profile.PROF_ID  "
					+ "and prof_pf.PF_ID = portfolio.PF_ID and TAG_USE.TAG_ID = TAG.TAG_ID  "
					+ "and TAG_USE.TAG_USE_TYPE_ID= prof_pf.PF_ID  "
					+ "and MEDIA_LIBRARY.ML_TYPE_ID = portfolio.PF_ID  "
					+ "and tag_use_type = 'portfolio' and  "
					+ " (UPPER(tag.tag_name) like '%"+keyword+"%' or UPPER(portfolio.pf_title) like '%"+keyword+"%') "
					+ "order by Pf_regdate desc";
		}
		else{
			sql = "select distinct ML_PATH, tag.tag_name, portfolio.PF_TITLE ,Profile.PROF_NAME, portfolio.PF_LIKE "
					+ "FROM media_library, tag, Profile, portfolio, prof_pf, tag_use "
					+ "where prof_pf.PROF_ID = Profile.PROF_ID  "
					+ "and prof_pf.PF_ID = portfolio.PF_ID and TAG_USE.TAG_ID = TAG.TAG_ID  "
					+ "and TAG_USE.TAG_USE_TYPE_ID= prof_pf.PF_ID  "
					+ "and MEDIA_LIBRARY.ML_TYPE_ID = portfolio.PF_ID  "
					+ "and tag_use_type = 'portfolio' and  "
					+ " (UPPER(tag.tag_name) like '%"+keyword+"%' or UPPER(portfolio.pf_title) like '%"+keyword+"%') "
					+ "order by portfolio.PF_LIKE desc";
		}
		
		ArrayList list = new ArrayList();
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
			System.out.println("searchAll_port_tag() 에서 오류");
			err.printStackTrace();
		}
		
		finally {
			freeConnection();
		}
		return list;
	}
	
	/**
	 * 포트폴리오 다중 검색 (오버로딩) (검색조건 태그 2개)
	 */
	public List searchAll_port(String keyword, String keyword2) {
		String sql = "select distinct MEDIA_LIBRARY.ML_PATH, TAG.TAG_NAME, portfolio.PF_TITLE ,Profile.PROF_NAME, portfolio.PF_LIKE "
				+ "from MEDIA_LIBRARY, TAG, Profile, portfolio, prof_pf, TAG_USE "
				+ "where tag.tag_name like '%"+keyword+"%' and tag.tag_name like '%"+keyword2+"%' "
				+ "and prof_pf.PROF_ID = Profile.PROF_ID  "
				+ "and prof_pf.PF_ID = portfolio.PF_ID and TAG_USE.TAG_ID = TAG.TAG_ID "
				+ "and TAG_USE.TAG_USE_TYPE_ID= prof_pf.PF_ID "
				+ "and MEDIA_LIBRARY.ML_TYPE_ID = portfolio.PF_ID"
				+ "order by portfolio.PF_LIKE desc";
		
		
		ArrayList list = new ArrayList();
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
			System.out.println("searchAll() 에서 오류");
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
		String sql = "select distinct MEDIA_LIBRARY.ML_PATH, TAG.TAG_NAME, portfolio.PF_TITLE ,Profile.PROF_NAME, portfolio.PF_LIKE "
				+ "from MEDIA_LIBRARY, TAG, Profile, portfolio, prof_pf, TAG_USE "
				+ "where tag.tag_name like '%"+keyword+"%' and tag.tag_name like '%"+keyword2+"%' tag.tag_name like '%"+keyword3+"%' "
				+ "and prof_pf.PROF_ID = Profile.PROF_ID  "
				+ "and prof_pf.PF_ID = portfolio.PF_ID and TAG_USE.TAG_ID = TAG.TAG_ID "
				+ "and TAG_USE.TAG_USE_TYPE_ID= prof_pf.PF_ID "
				+ "and MEDIA_LIBRARY.ML_TYPE_ID = portfolio.PF_ID"
				+ "order by portfolio.PF_LIKE desc";
		
		
		ArrayList list = new ArrayList();
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
			System.out.println("searchAll() 에서 오류");
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
			sql = "select distinct prof_img, prof_name,  prof_follower, prof_regdate  "
					+ "from profile join tag_use  "
					+ "on tag_use.tag_use_type_id = profile.prof_id "
					+ "join tag  on tag.tag_id = tag_use.tag_id  "
					+ "where (tag_use_type = 'developer') and "
					+ "(UPPER(tag.tag_name) like '%"+keyword+"%' or UPPER(profile.prof_name) like '%"+keyword+"%') "
					+ "order by prof_regdate desc";
		}	
		else{
			sql = "select distinct prof_img, prof_name,  prof_follower, prof_regdate  "
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
				member.setProf_img(rs.getString("prof_img"));
				member.setProf_name(rs.getString("prof_name"));
				member.setProf_follower(rs.getInt("prof_follower"));

				list.add(member);
			}
		}

		catch (Exception err) {
			System.out.println("member_info() 에서 오류");
			err.printStackTrace();
		}

		finally {
			freeConnection();
		}
		return list;
	}
	/**
	 * 멤버 태그 검색 결과
	 */
	public List searchAll_member_tag(String keyword,boolean lineup) {
		
		String sql ="";
		if(lineup == true){
			sql = "select prof_img, prof_name, tag.tag_name, prof_follower, prof_regdate  "
					+ "from profile join tag_use  "
					+ "on tag_use.tag_use_type_id = profile.prof_id "
					+ "join tag  on tag.tag_id = tag_use.tag_id  "
					+ "where (tag_use_type = 'developer') and "
					+ "(UPPER(tag.tag_name) like '%"+keyword+"%' or UPPER(profile.prof_name) like '%"+keyword+"%') "
					+ "order by prof_regdate desc";
		}	
		else{
			sql = "select prof_img, prof_name, tag.tag_name, prof_follower, prof_regdate  "
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
				member.setTag_name(rs.getString("tag_name"));
				member.setProf_img(rs.getString("prof_img"));
				member.setProf_name(rs.getString("prof_name"));
				member.setProf_follower(rs.getInt("prof_follower"));
				
				list.add(member);
			}
		}
		
		catch (Exception err) {
			System.out.println("member_info() 에서 오류");
			err.printStackTrace();
		}
		
		finally {
			freeConnection();
		}
		return list;
	}
	/**
	 * 프로젝트 검색 결과(태그 제외)
	 */
	public List searchAll_proj(String keyword,boolean lineup) {
		String sql = "";
		if(lineup == true){
			sql = "select distinct proj_title, prof_name, proj_intro, proj_to , ml_path, proj_regenddate "
					+ " FROM member join proj_app on member.mem_id = proj_app.mem_id	"
					+ "join project on project.proj_id = proj_app.proj_id "
					+ "join profile on member.mem_id = profile.mem_id "
					+ "join MEDIA_LIBRARY on MEDIA_LIBRARY.ML_TYPE_ID = project.proj_ID  "
					+ "join tag_use  on tag_use_type_id = project.proj_id  "
					+ "join tag on tag.tag_id = tag_use.tag_id "
					+ "where tag_use_type = 'project' and "
					+ "(UPPER(tag.tag_name) like '%"+keyword+"%' or (UPPER(project.proj_title ) like '%"+keyword+"%') ";
		}
		else{
			sql =  "select distinct proj_title, prof_name, proj_intro, proj_to , ml_path, proj_regenddate "
					+ " FROM member join proj_app on member.mem_id = proj_app.mem_id	"
					+ "join project on project.proj_id = proj_app.proj_id "
					+ "join profile on member.mem_id = profile.mem_id "
					+ "join MEDIA_LIBRARY on MEDIA_LIBRARY.ML_TYPE_ID = project.proj_ID  "
					+ "join tag_use  on tag_use_type_id = project.proj_id  "
					+ "join tag on tag.tag_id = tag_use.tag_id "
					+ "where tag_use_type = 'project' and "
					+ "(UPPER(tag.tag_name) like '%"+keyword+"%' or (UPPER(project.proj_title ) like '%"+keyword+"%') ";

		}
		ArrayList list = new ArrayList();
				
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Project project = new Project(); 
				//project.setTag_name(rs.getString("tag_name"));
				project.setProj_title(rs.getString("proj_title"));
				project.setProj_intro(rs.getString("proj_intro"));
				project.setProj_to(rs.getInt("proj_to"));
				project.setProf_name(rs.getString("prof_name"));
				//project.setProj_regdate(rs.getDate("proj_regdate"));
				//project.setProj_regenddate(rs.getDate("proj_regenddate")); 
				project.setMl_path(rs.getString("ml_path"));
				
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
	 * 프로젝트 검색 결과(태그 포함)
	 */
	public List searchAll_proj_tag(String keyword,boolean lineup) {
		String sql = "";
		if(lineup == true){
			sql = "select distinct proj_title, prof_name, proj_intro, proj_to , ml_path , tag.tag_name, proj_regenddate "
					+ "from member join proj_app on member.mem_id = proj_app.mem_id	"
					+ "join project on project.proj_id = proj_app.proj_id "
					+ "join profile on member.mem_id = profile.mem_id "
					+ "join MEDIA_LIBRARY on MEDIA_LIBRARY.ML_TYPE_ID = project.proj_ID  "
					+ "join tag_use  on tag_use_type_id = project.proj_id  "
					+ "join tag on tag.tag_id = tag_use.tag_id "
					+ "where tag_use_type = 'project' and (tag.tag_name like '%"+keyword+"%' or project.proj_title like '%"+keyword+"%') ";
		}
		else{
			sql = "select distinct proj_title, prof_name, proj_intro, proj_to , ml_path , tag.tag_name, proj_regenddate "
					+ "from member join proj_app on member.mem_id = proj_app.mem_id	"
					+ "join project on project.proj_id = proj_app.proj_id "
					+ "join profile on member.mem_id = profile.mem_id "
					+ "join MEDIA_LIBRARY on MEDIA_LIBRARY.ML_TYPE_ID = project.proj_ID  "
					+ "join tag_use  on tag_use_type_id = project.proj_id  "
					+ "join tag on tag.tag_id = tag_use.tag_id "
					+ "where tag_use_type = 'project' and (tag.tag_name like '%"+keyword+"%' or project.proj_title like '%"+keyword+"%') ";
		}
		ArrayList list = new ArrayList();
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Project project = new Project(); 
				project.setTag_name(rs.getString("tag_name"));
				project.setProj_title(rs.getString("proj_title"));
				project.setProj_intro(rs.getString("proj_intro"));
				project.setProj_to(rs.getInt("proj_to"));
				//project.setProj_regdate(rs.getDate("proj_regdate"));
				//project.setProj_regenddate(rs.getDate("proj_regenddate")); 
				project.setProf_name(rs.getString("prof_name"));
				project.setMl_path(rs.getString("ml_path"));
				
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
>>>>>>> refs/remotes/origin/dev
}