package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Media;
import portit.model.dto.Profile;
import portit.model.dto.Tag;


/**
 * 프로필 관련 DAO
 *
 */
public class ProfileDao {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	private String sql = null;

	private MediaDao mediaDao;
	private PortfolioDao portfolioDao;
	private ProjectDao projectDao;
	private TagDao tagDao;
	
	public ProfileDao() {
		try {
			pool = DBConnectionMgr.getInstance();
			MediaDao mediaDao = new MediaDao();
			PortfolioDao portfolioDao = new PortfolioDao();
			ProjectDao projectDao = new ProjectDao();
			TagDao tagDao = new TagDao();
		} catch (Exception e) {
			System.out.println("커넥션 풀 오류 - ProfileDao()");
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
			System.out.println("DB 접속 오류 :");
			e.printStackTrace();
		}
	}

	/**
	 * DB 접속 해제
	 */
	private void freeConnection() {
		try {
			pool.freeConnection(conn, stmt, rs);
			if (conn != null) {
				System.out.println("DB 접속 해제");
			}
		} catch (Exception e) {
			System.out.println("DB 접속해제 오류 :");
			e.printStackTrace();
		}
	}
	
	/**
	 * 회원번호로 프로필 조회
	 * @param mem_id
	 * @return
	 */
	public Profile selectOneByMemId(int mem_id) {
		Profile profile = new Profile();
		getConnection();
		try {
			sql = "SELECT * FROM profile WHERE mem_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				profile.setProf_id(rs.getInt("prof_id"))
				.setMem_id(rs.getInt("mem_id"))
				.setProf_nick(rs.getString("prof_nick"))
				.setProf_name(rs.getString("prof_name"))
				.setProf_intro(rs.getString("prof_intro"))
				.setProf_website(rs.getString("prof_website"))
				.setProf_github(rs.getString("prof_github"))
				.setProf_facebook(rs.getString("prof_facebook"))
				.setProf_follower(rs.getInt("prof_follower"));
			}
			
			// 미디어 정보
			List<Media> mediaList = mediaDao.selectList(conn, "profile", profile.getProf_id());
			profile.setProf_img(mediaList.get(0).getMl_path())
			.setProf_background(mediaList.get(1).getMl_path());
			
			// 태그 정보
			List<Tag> prof_tags_language = tagDao.selectList(conn, "language", "profile", profile.getProf_id());
			List<Tag> prof_tags_tool = tagDao.selectList(conn, "tool", "profile", profile.getProf_id());
			List<Tag> prof_tags_field = tagDao.selectList(conn, "field", "profile", profile.getProf_id());
			profile.setProf_tags_language(prof_tags_language)
			.setProf_tags_tool(prof_tags_tool)
			.setProf_tags_field(prof_tags_field);
			
			// 스킬셋 정보
			Map<String, Integer> prof_skillset = new HashMap<String, Integer>();
			for (int i = 0; i < prof_tags_language.size(); i++) {
				if (prof_tags_language.get(i).getProf_skill_level() > 0) {
					prof_skillset.put(prof_tags_language.get(i).getTag_name(), prof_tags_language.get(i).getProf_skill_level());
				}
			}
			for (int i = 0; i < prof_tags_tool.size(); i++) {
				if (prof_tags_tool.get(i).getProf_skill_level() > 0) {
					prof_skillset.put(prof_tags_tool.get(i).getTag_name(), prof_tags_tool.get(i).getProf_skill_level());
				}
			}
			profile.setProf_skillset(prof_skillset);
			
			// 포트폴리오 정보
			profile.setProf_myPf(portfolioDao.selectListByMemId(mem_id));
			
			// 프로젝트 정보
			profile.setProf_myProj(projectDao.selectListByMemId(mem_id));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return profile;
	}
	
	/**
	 * 회원 닉네임으로 프로필 조회
	 * @param prof_nick
	 * @return
	 */
	public Profile selectOneByUsername(String prof_nick) {
		Profile profile = new Profile();
		getConnection();
		try {
			sql = "SELECT * FROM profile WHERE prof_nick=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, prof_nick);
			rs = stmt.executeQuery();
			while (rs.next()) {
				profile.setProf_id(rs.getInt("prof_id"))
				.setMem_id(rs.getInt("mem_id"))
				.setProf_nick(rs.getString("prof_nick"))
				.setProf_name(rs.getString("prof_name"))
				.setProf_intro(rs.getString("prof_intro"))
				.setProf_website(rs.getString("prof_website"))
				.setProf_github(rs.getString("prof_github"))
				.setProf_facebook(rs.getString("prof_facebook"))
				.setProf_follower(rs.getInt("prof_follower"));
			}
			
			// 미디어 정보
			List<Media> mediaList = mediaDao.selectList(conn, "profile", profile.getProf_id());
			profile.setProf_img(mediaList.get(0).getMl_path())
			.setProf_background(mediaList.get(1).getMl_path());
			
			// 태그 정보
			List<Tag> prof_tags_language = tagDao.selectList(conn, "language", "profile", profile.getProf_id());
			List<Tag> prof_tags_tool = tagDao.selectList(conn, "tool", "profile", profile.getProf_id());
			List<Tag> prof_tags_field = tagDao.selectList(conn, "field", "profile", profile.getProf_id());
			profile.setProf_tags_language(prof_tags_language)
			.setProf_tags_tool(prof_tags_tool)
			.setProf_tags_field(prof_tags_field);
			
			// 스킬셋 정보
			Map<String, Integer> prof_skillset = new HashMap<String, Integer>();
			for (int i = 0; i < prof_tags_language.size(); i++) {
				if (prof_tags_language.get(i).getProf_skill_level() > 0) {
					prof_skillset.put(prof_tags_language.get(i).getTag_name(), prof_tags_language.get(i).getProf_skill_level());
				}
			}
			for (int i = 0; i < prof_tags_tool.size(); i++) {
				if (prof_tags_tool.get(i).getProf_skill_level() > 0) {
					prof_skillset.put(prof_tags_tool.get(i).getTag_name(), prof_tags_tool.get(i).getProf_skill_level());
				}
			}
			profile.setProf_skillset(prof_skillset);
			
			// 포트폴리오 정보
			profile.setProf_myPf(portfolioDao.selectListByMemId(profile.getMem_id()));
			
			// 프로젝트 정보
			profile.setProf_myProj(projectDao.selectListByMemId(profile.getMem_id()));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return profile;
	}
	
	/**
	 * 프로필 추가
	 * @param mem_id
	 * @param profile
	 * @return
	 */
	public int insert(int mem_id, Profile profile) {
		int rows = 0;
		getConnection();
		try {
			// 프로필
			sql = "INSERT INTO profile("
					+ "prof_id, mem_id, prof_nick, prof_name, prof_intro, prof_img, "
					+ "prof_background, prof_website, prof_github, prof_facebook, "
					+ "prof_regdate, prof_follower) "
					+ "VALUES(LPAD(seq_prof_id.nextval, 4, '0'),?,?,?,?,?,?,?,?,?,SYSDATE,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			stmt.setString(2, profile.getProf_nick());
			stmt.setString(3, profile.getProf_name());
			stmt.setString(4, profile.getProf_intro());
			stmt.setString(5, profile.getProf_img());
			stmt.setString(6, profile.getProf_background());
			stmt.setString(7, profile.getProf_website());
			stmt.setString(8, profile.getProf_github());
			stmt.setString(9, profile.getProf_facebook());
			stmt.setInt(10, 0);
			rows += stmt.executeUpdate();
			
			sql = "SELECT seq_prof_id.currval FROM DUAL";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			rs.next();
			int articleId = rs.getInt(1);
			
			// 태그
			List<Tag> prof_tags_language = profile.getProf_tags_language();
			List<Tag> prof_tags_tool = profile.getProf_tags_tool();
			List<Tag> prof_tags_field = profile.getProf_tags_field();
			for (int i = 0; i < prof_tags_language.size(); i++) {
				Tag tag = prof_tags_language.get(i);
				tagDao.insertTag(conn, tag);
				tagDao.insertTagUse(conn, "profile", articleId, tag);
			}
			for (int i = 0; i < prof_tags_tool.size(); i++) {
				Tag tag = prof_tags_tool.get(i);
				tagDao.insertTag(conn, tag);
				tagDao.insertTagUse(conn, "profile", articleId, tag);
			}
			for (int i = 0; i < prof_tags_field.size(); i++) {
				Tag tag = prof_tags_field.get(i);
				tagDao.insertTag(conn, tag);
				tagDao.insertTagUse(conn, "profile", articleId, tag);
			}
			
			// 스킬셋
			Map<String, Integer> prof_skillset = profile.getProf_skillset();
			for (String key : prof_skillset.keySet()) {
				Tag tag = new Tag().setTag_name(key).setProf_skill_level(prof_skillset.get(key));
				tagDao.updateTagUse(conn, "profile", articleId, tag);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return rows;
	}
	
	/**
	 * 회원번호로 프로필 수정
	 * @param mem_id
	 * @param profile
	 * @return
	 */
	public int updateByMemId(int prof_id, Profile profile) {
		int rows = 0;
		getConnection();
		try {
			sql = "UPDATE profile SET prof_nick=?, prof_name=?, prof_intro=?, prof_img=?, "
					+ "prof_background=?, prof_website=?, prof_github=?, prof_facebook=? "
					+ "WHERE prof_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, profile.getProf_nick());
			stmt.setString(2, profile.getProf_name());
			stmt.setString(3, profile.getProf_intro());
			stmt.setString(4, profile.getProf_img());
			stmt.setString(5, profile.getProf_background());
			stmt.setString(6, profile.getProf_website());
			stmt.setString(7, profile.getProf_github());
			stmt.setString(8, profile.getProf_facebook());
			stmt.setInt(9, prof_id);
			rows += stmt.executeUpdate();
			
			// 태그
			List<Tag> prof_tags_language = profile.getProf_tags_language();
			List<Tag> prof_tags_tool = profile.getProf_tags_tool();
			List<Tag> prof_tags_field = profile.getProf_tags_field();
			for (int i = 0; i < prof_tags_language.size(); i++) {
				Tag tag = prof_tags_language.get(i);
				tagDao.insertTag(conn, tag);
				tagDao.insertTagUse(conn, "profile", prof_id, tag);
			}
			for (int i = 0; i < prof_tags_tool.size(); i++) {
				Tag tag = prof_tags_tool.get(i);
				tagDao.insertTag(conn, tag);
				tagDao.insertTagUse(conn, "profile", prof_id, tag);
			}
			for (int i = 0; i < prof_tags_field.size(); i++) {
				Tag tag = prof_tags_field.get(i);
				tagDao.insertTag(conn, tag);
				tagDao.insertTagUse(conn, "profile", prof_id, tag);
			}
			
			// 스킬셋
			Map<String, Integer> prof_skillset = profile.getProf_skillset();
			for (String key : prof_skillset.keySet()) {
				Tag tag = new Tag().setTag_name(key).setProf_skill_level(prof_skillset.get(key));
				tagDao.updateTagUse(conn, "profile", prof_id, tag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return rows;
	}
	
	/**
	 * 프로필 삭제
	 * @param prof_id
	 * @return
	 */
	public int deleteByMemId(int prof_id) {
		int rows = 0;
		getConnection();
		try {
			tagDao.deleteTagUse(conn, "profile", prof_id);
			
			sql = "DELETE profile WHERE mem_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rows += stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return rows;
	}
	
	
	/*
	// 같은 프로필 번호를 쓰는 프로필이 있는지 검사
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
	
	// (수정·삭제시) 작성자가 맞는지 검사
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
	
	// 프로필 입력
	public Profile addprofile(Profile dto) {
		String sql = "INSERT into profile"
				+	"values(seq_prof_id.nextVal,mem_id,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		 
		try{
				conn = pool.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, dto.getProf_img());
				stmt.setString(2, dto.getProf_background());
				stmt.setString(3, dto.getProf_name());
				stmt.setString(4, dto.getProf_nick());
				stmt.setString(5, dto.getProf_intro());
				stmt.setString(7, dto.getProf_website());
				stmt.setString(8, dto.getProf_github());
				stmt.setString(9, dto.getProf_facebook());
				stmt.setInt(10, dto.getProf_follower());
				stmt.setString(11, dto.getTag_name());
				stmt.setString(12, dto.getTag_name());
				stmt.setInt(13, dto.getProf_skill_level());
				stmt.executeUpdate();
				
				//list.add(dto);
			}
			
			catch(Exception err){
				System.out.println("profile() : " + err);
			}
			finally{
				freeConnection();
		}
			return dto;
	}
	// 프로필 수정  
	// 입력했던 것을 불러온다.
	public Profile getProfile(int prof_id){
		String sql="select * from profile where prof_id =?";
		
		Profile dto = new Profile();
		   
		try{
			conn = pool.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,  prof_id);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				dto.setProf_id(prof_id);
				dto.setProf_img(rs.getString("Prof_img"));
				dto.setProf_background(rs.getString("Prof_background"));
				dto.setProf_name(rs.getString("Prof_name"));
				dto.setProf_nick(rs.getString("Prof_nick"));
				dto.setProf_intro(rs.getString("Prof_intro"));
				dto.setProf_website(rs.getString("Prof_website"));				
				dto.setProf_github(rs.getString("Prof_github"));				
				dto.setProf_facebook(rs.getString("Prof_facebook"));				
				dto.setProf_follower(rs.getInt("Prof_follower"));				
				dto.setTag_name(rs.getString("Tag_name"));				
				dto.setTag_name(rs.getString("Tag_name"));				
				dto.setProf_skill_level(rs.getInt("Prof_skill_level"));				
			}
		}
		catch(Exception err){
			System.out.println("profile() : " + err);
		}
		finally{
			freeConnection();
		}
		return dto;
		
	}
	// 프로필 수정 란(내용값 수정)
	public void updateProfile(Profile dto){
		
		String sql = "update profile set prof_img=?, prof_background=?, prof_name=?, prof_nick=?, prof_intro=?"
				+ ", prof_img=?, prof_background=?, prof_website=?, prof_github=?, prof_facebook=?, prof_facebook=?, prof_regdate"
				+ ", prof_follower=?, Tag_name=?, Tag_name=?, Prof_skill_level=? where prof_id=?";
		
		
		try{
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getProf_img());
			stmt.setString(2, dto.getProf_background());
			stmt.setString(3, dto.getProf_name());
			stmt.setString(4, dto.getProf_nick());
			stmt.setString(5, dto.getProf_intro());
			stmt.setString(7, dto.getProf_website());
			stmt.setString(8, dto.getProf_github());
			stmt.setString(9, dto.getProf_facebook());
			stmt.setInt(10, dto.getProf_follower());
			stmt.setString(11, dto.getTag_name());
			stmt.setString(12, dto.getTag_name());
			stmt.setInt(13, dto.getProf_skill_level());
			stmt.executeUpdate();
		}
		catch(Exception err){
			System.out.println("DBCP 연결 실패 : " + err);
		}
		finally{
			freeConnection();
		}
		
	}
	
// 프로필 삭제
	// deleteEmp_proc.jsp
	public void deleteProfile(int prof_id){
		String sql = "delete from profile where prof_id ="+prof_id+"";
		
		try{
			conn = pool.getConnection();			
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		}
		catch(Exception err){
			System.out.println("DBCP 연결 실패 : " + err);
		}
		finally{
			freeConnection();
		}
		
	}
	
	// 상세페이지를 위한 전체 select + dto에 저장
	public Profile selectForDetail(int mem_id) {
		Profile pf = new Profile();
		try {
			conn = pool.getConnection();
			
			sql = "SELECT * FROM profile WHERE mem_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				pf.setProf_id(rs.getInt("prof_id"));
				pf.setMem_id(rs.getInt("mem_id"));
				pf.setProf_nick(rs.getString("prof_nick"));
				pf.setProf_name(rs.getString("prof_name"));
				pf.setProf_intro(rs.getString("prof_intro"));
				pf.setProf_img(rs.getString("prof_img"));
				pf.setProf_background(rs.getString("prof_background"));
				pf.setProf_website(rs.getString("prof_website"));
				pf.setProf_github(rs.getString("prof_github"));
				pf.setProf_facebook(rs.getString("prof_facebook"));
				pf.setProf_regdate(rs.getDate("prf_regdate"));
				pf.setProf_follower(rs.getInt("prof_follower"));
				
				getTag(pf, rs.getInt(mem_id));
				
//				private String prof_language;
//				private String prof_tool;
//				private String prof_field;
//				private String tag_name;
//				private int prof_skill_level;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt);
		}
		
		return pf;
	}
	
	//  profile에서 쓴 태그
	public void getTag(Profile pf, int mem_id) {
		try{
			sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ "WHERE t.tag_id = tu.tag_id AND tu.tag_use_type = 'prof' AND tu.tag_use_type_id = ? "
					+ "ORDER BY DBMS_RANDOM.RANDOM) WHERE rownum < 4";  // 랜덤하게 3개
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs2 = stmt.executeQuery();
			
			List<String> tags = new ArrayList<>();			
			while(rs2.next()){	
				tags.add(rs.getString("tag_name"));
			}
			pf.setTags(tags);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}*/
	
	/**
	 * profile 의 portfolio
	 * 
	 */

	
	/**
	 * profile 의 project
	 */
}
