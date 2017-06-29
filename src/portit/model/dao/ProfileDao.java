package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Follow;
import portit.model.dto.Profile;


/**
 * �봽濡쒗븘 愿��젴 DAO
 * @author hyang
 *
 */
public class ProfileDao {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	private String sql = null;
	
	//HttpRequest req = new HttpRequest();
	//HttpSession session = request.getSession();
	//int mem_id = session.getAttribute("mem_id");
	
	public ProfileDao() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("而ㅻ꽖�뀡 �� �삤瑜� - MemberDao()");
			e.printStackTrace();
		}
	}
	
	/**
	 * DB �뿰寃�
	 */
	private void getConnection() {
		try {
			conn = pool.getConnection();
			if (conn != null) System.out.println("DB �젒�냽");
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
			if (conn != null) System.out.println("DB �젒�냽 �빐�젣");
		} catch (Exception e) {
			System.out.println("DB �젒�냽�빐�젣 �삤瑜� - freeConnection()");
			e.printStackTrace();
		}
	}
	
	/**
	 * 媛숈� �봽濡쒗븘 踰덊샇瑜� �벐�뒗 �봽濡쒗븘�씠 �엳�뒗吏� 寃��궗
	 * @param prof_id
	 * @return 議댁옱�븯�뒗 �봽濡쒗븘 踰덊샇
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
	 * (�닔�젙쨌�궘�젣�떆) �옉�꽦�옄媛� 留욌뒗吏� 寃��궗
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
	 * �봽濡쒗븘 �엯�젰 
	 * @param mem_id 
	 * @param _profile
	 * @return 
	 * @return
	 */
	public Profile addprofile(Profile dto, int mem_id) {
		
		String sql = "UPDATE PROFILE SET prof_img=?, prof_background=?, prof_name=?, prof_nick=?, "
				+ "prof_intro=?, prof_language=?, prof_tool=?, prof_field=?, prof_website=?, "
				+ "prof_github=?, prof_facebook=?, prof_follower=?"
				+ ",Tag_name=?, prof_skill_level=?"
				+ "WHERE mem_id=?";
		
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
				stmt.setString(11, dto.getProf_language());
				stmt.setString(12, dto.getProf_tool());
				stmt.setString(13, dto.getProf_field());
				stmt.setString(14, dto.getTag_name());
				stmt.setInt(15, dto.getProf_skill_level());
				stmt.setInt(16, mem_id);
				stmt.executeUpdate();
				
				//list.add(dto);
				/*
				Profile myProf = this.getProfile(mem_id);
				
				sql="INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES(seq_tag_id.nextval,?)";
			
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, dto.getTag_name());
				stmt.executeUpdate();
				
				sql="INSERT INTO TAGUSE(TAG_ID,TAG_NAME,PROF_SKILL_LEVEL) VALUES(seq_tag_id.nextval,?,?)";
					//SKILL입력
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, dto.getTag_name());
				stmt.setInt(2, dto.getProf_skill_level());
				stmt.executeUpdate();
				
				sql="INSERT INTO TAG(TAG_ID,TAG_TYPE,TAG_NAME) VALUES(seq_tag_id.nextVal,PROF_language,?)";//TAG_ID시퀀드 수정입력
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, dto.getTag_name());
				stmt.executeUpdate();
				
				sql="INSERT INTO TAGUSE(TAG_USE_ID,TAG_USE_TYPE,TAG_USE_TYPE_ID,TAG_ID) "
					+ "VALUES(seq_tag_use_id.nextVal,?,SEQ_TAG_USE_ID.NEXTVAL,Tag_id)";
					//prof_laguage tag, taguse 테이블에 입력
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, dto.getTag_name());
				stmt.setInt(2, dto.getProf_skill_level());
				stmt.executeUpdate();
				
				sql="INSERT INTO TAG(TAG_ID,TAG_TYPE,TAG_NAME) VALUES(seq_tag_id.nextVal,PROF_TOOL,?)";
				sql="INSERT INTO TAGUSE(TAG_USE_ID,TAG_USE_TYPE,TAG_USE_TYPE_ID,TAG_ID) "
					+ "VALUES(MEM_ID,PROF_tool,SEQ_TAG_USE_ID.NEXTVAL,MEM_ID)";
					//prof_tool tag  , taguse  테이블에 입력
				sql="INSERT INTO TAG(TAG_ID,TAG_TYPE,TAG_NAME) VALUES(seq_tag_id.nextVal,PROF_language,?)";//TAG_ID시퀀드 수정입력
				sql="INSERT INTO TAGUSE(TAG_USE_ID,TAG_USE_TYPE,TAG_USE_TYPE_ID,TAG_ID) "
					+ "VALUES(MEM_ID,PROF_field,SEQ_TAG_USE_ID.NEXTVAL,MEM_ID)";
					//prof_field tag,taguse 테이블에 입력
*/				
			}
		
			catch(Exception err){
				System.out.println("profile() : " + err);
			}
			finally{
				freeConnection();
		}
			return dto;
	}
	/**
	 * �봽濡쒗븘 �닔�젙  
	 * �엯�젰�뻽�뜕 寃껋쓣 遺덈윭�삩�떎.
	 * @param profile
	 */
	public Profile getProfile(int mem_id){
		String sql="SELECT PROFILE.PROF_IMG, PROFILE.PROF_BACKGROUND, PROFILE.PROF_NAME, "
				+ "PROFILE.PROF_NICK, PROFILE.PROF_INTRO, TAG_USE.TAG_USE_TYPE, TAG_USE.TAG_USE_TYPE, TAG_USE.TAG_USE_TYPE,"
				+ "PROFILE.WEBSITE, PROFILE.GITBUB, PROFILE.FACEBOOK, PROFILE.FOLLOWER, TAG_USE.TAG_USE_TYPE,"
				+ "TAG_USE.PROF_SKILL_LEVEL "
				+ "FROM PROFILE, TAG_USE"
				+ "WHERE PROFILE.MEM_ID = TAG_USE.TAG_USE_TYPE_ID and TAG_USE.TAG_ID = TAG.TAG_ID";
		
		Profile dto = new Profile();
		   
		try{
			conn = pool.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,  mem_id);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				dto.setProf_id(mem_id);
				dto.setProf_img(rs.getString("prof_img"));
				dto.setProf_background(rs.getString("prof_background"));
				dto.setProf_name(rs.getString("prof_name"));
				dto.setProf_nick(rs.getString("prof_nick"));
				dto.setProf_intro(rs.getString("prof_intro"));
				dto.setProf_language(rs.getString("prof_laguage"));
				dto.setProf_tool(rs.getString("prof_tool"));
				dto.setProf_field(rs.getString("prof_field"));
				dto.setProf_website(rs.getString("prof_website"));				
				dto.setProf_github(rs.getString("prof_github"));				
				dto.setProf_facebook(rs.getString("prof_facebook"));				
				dto.setProf_follower(rs.getInt("prof_follower"));				
				dto.setTag_name(rs.getString("tag_name"));				
				dto.setProf_skill_level(rs.getInt("prof_skill_level"));				
				
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
	/**
	 * �봽濡쒗븘 �닔�젙 ��(�궡�슜媛� �닔�젙)
	 * @param dto
	 */
	
	public void updateProfile(Profile dto){
		
		
		String sql = "UPDATE PROFILE SET prof_img=?, prof_background=?, prof_name=?, prof_nick=?, "
				+ "prof_intro=?, prof_language=?, prof_tool=?, prof_field=?, prof_website=?, "
				+ "prof_github=?, prof_facebook=?, prorof_follower=?"
				+ ",Tag_name=?, prof_skill_level=?"
				+ "WHERE prof_id=?";
	
		sql = "UPDATE TAG_USE SET TAG_TYPE=? AND PROF_SKILL_LEVEL=?";
		
		try{
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getProf_img());
			stmt.setString(2, dto.getProf_background());
			stmt.setString(3, dto.getProf_name());
			stmt.setString(4, dto.getProf_nick());
			stmt.setString(5, dto.getProf_intro());
			stmt.setString(6, dto.getProf_language());
			stmt.setString(7, dto.getProf_tool());
			stmt.setString(8, dto.getProf_field());
			stmt.setString(9, dto.getProf_website());
			stmt.setString(10, dto.getProf_github());
			stmt.setString(11, dto.getProf_facebook());
			stmt.setInt(12, dto.getProf_follower());
			stmt.setString(14, dto.getTag_name());
			stmt.setInt(15, dto.getProf_skill_level());
			stmt.executeUpdate();
		}
		catch(Exception err){
			System.out.println("DBCP �뿰寃� �떎�뙣 : " + err);
		}
		finally{
			freeConnection();
		}
		
	}
	
	
	/**
	 * 상세페이지를 위한 전체 select + dto에 저장
	 */
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
	
	/**
	 *  profile에서 쓴 태그 
	 */
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
	}
	
	/**
	 * profile 의 portfolio
	 * 
	 */

	
	/**
	 * profile 의 project
	 */
}





