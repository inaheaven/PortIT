package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Media;
import portit.model.dto.Portfolio;
import portit.model.dto.Profile;
import portit.model.dto.Tag;

public class ProfileDao {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs, rs2;
	private DBConnectionMgr pool;
	private String sql = null;

	public ProfileDao() {
		try {
			pool = DBConnectionMgr.getInstance();
			conn = pool.getConnection();

		} catch (Exception e) {
			System.out.println("DB 접속 오류 :");
			e.printStackTrace();
		}
	}

	/**
	 * DB 연결
	 */
	private void getConnection() {
		try {
			conn = pool.getConnection();
			if (conn != null)
				;
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
	 * 프로필 등록(프로필 테이블 + 태크 테이블에 있는 정보 등록)
	 */
	public Profile addprofile(Profile dto, int mem_id) {
		try {
			sql = "update profile set prof_img=?, prof_background=?, prof_name=?, prof_nick=?, prof_intro=?, "
					+ "prof_website=?, prof_github=?, prof_facebook=?  where mem_id = " + mem_id;

			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getProf_img());
			stmt.setString(2, dto.getProf_background());
			stmt.setString(3, dto.getProf_name());
			stmt.setString(4, dto.getProf_nick());
			stmt.setString(5, dto.getProf_intro());
			stmt.setString(6, dto.getProf_website());
			stmt.setString(7, dto.getProf_github());
			stmt.setString(8, dto.getProf_facebook());

			stmt.executeUpdate();

			List tag_lang = dto.getTag_lang();
			List tag_tool = dto.getTag_tool();
			List tag_field = dto.getTag_field();
			List tag_skill = dto.getTag_skill();
			List prof_skill_level = dto.getProf_skill_level();
			//////////////////////////////////////////////////////////
			System.out.println("tool1 : " + tag_tool.get(0).toString());
			System.out.println("1st : " + tag_field.get(0).toString());
			System.out.println("2nd : " + tag_field.get(1).toString());
			// System.out.println("3nd : " + tag_lang.get(2).toString());
			//////////////////////////////////////////////////////////
			List tag_lang2 = new ArrayList();
			List tag_tool2 = new ArrayList();
			List tag_field2 = new ArrayList();
			List tag_skill2 = new ArrayList();

			// 태그 테이블 추가//////////////////////////////////////////////
			// 언어
			if (tag_lang != null) {
				boolean check = true; // 태그 테이블에 있는 데이터인지 아닌지 검사(false이면 태그 테이블에
										// 삽입)

				for (int i = 0; i < tag_lang.size(); i++) {
					check = tagNameCheck(tag_lang.get(i).toString()); 
																		

					if (!check) { // false(테이블에 없는 경우)
						tag_lang2.add(tag_lang.get(i).toString()); 
					}
				}

				// 태그 테이블 insert(tag_lang2만 insert)
				for (int i = 0; i < tag_lang2.size(); i++) {
					sql = "INSERT INTO tag(tag_id, tag_type, tag_name) VALUES(seq_tag_id.nextVal,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "language");
					stmt.setString(2, tag_lang2.get(i).toString());
					stmt.executeUpdate();

				}
			}

			// 태그 툴 입력
			if (tag_tool != null) {
				boolean check;

				for (int i = 0; i < tag_tool.size(); i++) {
					check = tagNameCheck(tag_tool.get(i).toString());

					if (!check) {
						tag_tool2.add(tag_tool.get(i).toString());
					}
				}

				for (int i = 0; i < tag_tool2.size(); i++) {
					sql = "INSERT INTO tag(tag_id, tag_type, tag_name) VALUES(seq_tag_id.nextVal,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "tool");
					stmt.setString(2, tag_tool2.get(i).toString());
					stmt.executeUpdate();

				}
			}

			// 분야(필드)
			if (tag_field != null) {
				boolean check;

				for (int i = 0; i < tag_field.size(); i++) {
					check = tagNameCheck(tag_field.get(i).toString());

					if (!check) {
						tag_field2.add(tag_field.get(i).toString());
					}
				}

				for (int i = 0; i < tag_field2.size(); i++) {
					sql = "INSERT INTO tag(tag_id, tag_type, tag_name) VALUES(seq_tag_id.nextVal,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "field");
					stmt.setString(2, tag_field2.get(i).toString());
					stmt.executeUpdate();
				}
			}

			// 스킬
			if (tag_skill != null) {
				boolean check;

				for (int i = 0; i < tag_skill.size(); i++) {
					check = tagNameCheck(tag_skill.get(i).toString());

					if (!check) {
						tag_skill2.add(tag_skill.get(i).toString());
					}
				}

				for (int i = 0; i < tag_skill2.size(); i++) {
					sql = "INSERT INTO tag(tag_id, tag_type, tag_name) VALUES(seq_tag_id.nextVal,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "skill_level");
					stmt.setString(2, tag_skill2.get(i).toString());
					stmt.executeUpdate();
				}
			}

			// 프로필 아이디를 가져옴
			int req_prof_id = getProf_id(mem_id);
			System.out.println("프로필 아이디 : " + req_prof_id);

			List tag_lang3 = new ArrayList();
			List tag_tool3 = new ArrayList();
			List tag_field3 = new ArrayList();
			List tag_skill3 = new ArrayList();
			List prof_skill_level3 = new ArrayList();

			////////////////// 태그유즈 테이블 입력

			boolean check = true;
			// 입력한 태그의 갯수가 몇 개 인지 갯수 추출

			for (int i = 0; i < tag_lang.size(); i++) {
				if ("".equals(tag_lang.get(i).toString()) || " ".equals(tag_lang.get(i).toString())
						|| null == tag_lang.get(i).toString()) {
					continue;
				} else {
					tag_lang3.add(tagNameToId(tag_lang.get(i).toString()));
				}
			}

			// 태그유즈에서 language등록
			if (tag_lang != null) {
				sql = "INSERT INTO tag_use(" + "tag_use_id, tag_use_type, tag_use_type_id, tag_id"
						+ ") VALUES(seq_tag_use_id.nextVal,?,?,?)";
				for (int i = 0; i < tag_lang3.size(); i++) {
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "prof");
					stmt.setInt(2, req_prof_id);
					stmt.setInt(3, (int) tag_lang3.get(i));
					stmt.executeUpdate();
				}
				System.out.println("프로필 번호 : " + req_prof_id);
			}

			// 태그 유즈에 tool을 넣기 위한 리스트
			for (int i = 0; i < tag_tool.size(); i++) {
				if ("".equals(tag_tool.get(i).toString()) || " ".equals(tag_tool.get(i).toString())
						|| null == tag_tool.get(i).toString()) {
					continue;
				} else {
					tag_tool3.add(tagNameToId(tag_tool.get(i).toString()));
				}
			}

			// 툴 유즈
			if (tag_tool != null) {
				for (int i = 0; i < tag_tool3.size(); i++) {
					sql = "INSERT INTO tag_use(" + "tag_use_id, tag_use_type, tag_use_type_id, tag_id"
							+ ") VALUES(seq_tag_use_id.nextVal,?,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "prof");
					stmt.setInt(2, req_prof_id);
					stmt.setInt(3, (int) tag_tool3.get(i));
					stmt.executeUpdate();
				}
			}

			// 태그 유즈에 field을 넣기 위한 리스트
			for (int i = 0; i < tag_field.size(); i++) {
				if ("".equals(tag_field.get(i).toString()) || " ".equals(tag_field.get(i).toString())
						|| null == tag_field.get(i).toString()) {
					continue;
				} else {
					tag_field3.add(tagNameToId(tag_field.get(i).toString()));
				}
			}

			// 분야(필드) 유즈
			if (tag_field != null) {
				for (int i = 0; i < tag_field3.size(); i++) {
					sql = "INSERT INTO tag_use(" + "tag_use_id, tag_use_type, tag_use_type_id, tag_id"
							+ ") VALUES(seq_tag_use_id.nextVal,?,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "prof");
					stmt.setInt(2, req_prof_id);
					stmt.setInt(3, (int) tag_field3.get(i));
					stmt.executeUpdate();
				}
			}

			// 태그 유즈에 스킬 점수를 넣기 위한 리스트
			for (int i = 0; i < tag_skill.size(); i++) {
				if ("".equals(tag_skill.get(i).toString()) || " ".equals(tag_skill.get(i).toString())
						|| null == tag_skill.get(i).toString()) {
					continue;
				} else {
					tag_skill3.add(tagNameToId(tag_skill.get(i).toString()));
				}
			}

			for (int i = 0; i < prof_skill_level.size(); i++) {
				if ("".equals(prof_skill_level.get(i).toString()) || " ".equals(prof_skill_level.get(i).toString())
						|| null == prof_skill_level.get(i).toString()) {
					continue;
				} else {
					prof_skill_level3.add((prof_skill_level.get(i).toString()));
				}
			}

			// 분야(필드) 유즈
			if (tag_field != null) {
				for (int i = 0; i < tag_skill3.size(); i++) {
					sql = "INSERT INTO tag_use(" + "tag_use_id, tag_use_type, tag_use_type_id, tag_id, prof_skill_level"
							+ ") VALUES(seq_tag_use_id.nextVal,?,?,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "prof");
					stmt.setInt(2, req_prof_id);
					stmt.setInt(3, (int) tag_skill3.get(i));
					stmt.setString(4, prof_skill_level3.get(i).toString());
					stmt.executeUpdate();
				}
			}
		} catch (Exception e) {
			System.out.println("insert 오류 " + e);
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return dto;
	}

	// 프로필 아이디를 가지오는 메서드
	public int getProf_id(int mem_id) {
		int req_prof_id = 0;
		try {
			sql = "select * from profile where mem_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs = stmt.executeQuery();
			rs.next();
			req_prof_id = rs.getInt("prof_id");
		} catch (Exception e) {
			System.out.println("프로필아이디 겟에서 오류");
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return req_prof_id;
	}

	/**
	 * 태그 이름으로 번호 얻기
	 * 
	 * @param tag_name
	 * @return
	 */
	private int tagNameToId(String tag_name) {
		int tag_id = 0;
		getConnection();
		try {
			String sql = "SELECT tag_id FROM tag WHERE tag_name=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag_name);
			rs = stmt.executeQuery();
			if (rs.next()) {
				tag_id = rs.getInt("tag_id"); // 결과 있을 때 내용...
			}

		} catch (Exception e) {
			System.out.println("tagNameToId오류(profileDao)");
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return tag_id;
	}

	/**
	 * 태그이름으로 태그 테이블에 있는지 여부 확인 false = 테이블에 없음(등록해야함) , true= 이미 있는 목록(건너뜀)
	 */
	private boolean tagNameCheck(String tag_name) {
		boolean tag_check = false;

		// null처리
		if ("".equals(tag_name) || " ".equals(tag_name) || null == tag_name) {
			return true;
		}

		getConnection();
		try {
			String sql = "SELECT tag_name FROM tag WHERE tag_name=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag_name);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return true; // 결과 있을 때 내용...
			} else {
				return false; // 결과 없을 때 내용..
			}

		} catch (Exception e) {
			System.out.println("tagCheck오류 : " + e);
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return tag_check;
	}

	/**
	 * 특정 멤버의 태그를 가지고 오는 메서드
	 */
	public List member_tag(int prof_id) {
		try {
			String sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ " WHERE t.tag_id = tu.tag_id AND tu.tag_use_type = 'prof' AND tu.tag_use_type_id = ?) ";

			ArrayList list = new ArrayList();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs2 = stmt.executeQuery();

			List<String> tags = new ArrayList<>();
			while (rs2.next()) {
				tags.add(rs2.getString("tag_name"));
			}
			return tags;
		} catch (Exception e) {
			System.out.println("member_tag 오류 " + e);
		}
		return null;
	}

	/**
	 * 프로필 등록(프로필 테이블 + 태크 테이블에 있는 정보 등록)
	 */
	public Profile updateProfile(Profile dto, int mem_id) {
		try {
			sql = "update profile set prof_img=?, prof_background=?, prof_name=?, prof_nick=?, prof_intro=?, "
					+ "prof_website=?, prof_github=?, prof_facebook=?  where mem_id = " + mem_id;

			
				conn = pool.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, dto.getProf_img());
				stmt.setString(2, dto.getProf_background());
				stmt.setString(3, dto.getProf_name());
				stmt.setString(4, dto.getProf_nick());
				stmt.setString(5, dto.getProf_intro());
				stmt.setString(6, dto.getProf_website());
				stmt.setString(7, dto.getProf_github());
				stmt.setString(8, dto.getProf_facebook());
	
				stmt.executeUpdate();
	
				List tag_lang = dto.getTag_lang();
				List tag_tool = dto.getTag_tool();
				List tag_field = dto.getTag_field();
				List tag_skill = dto.getTag_skill();
				List prof_skill_level = dto.getProf_skill_level();
				//////////////////////////////////////////////////////////
				System.out.println("tool1 : " + tag_tool.get(0).toString());
				System.out.println("1st : " + tag_field.get(0).toString());
				System.out.println("2nd : " + tag_field.get(1).toString());
				// System.out.println("3nd : " + tag_lang.get(2).toString());
				//////////////////////////////////////////////////////////
				List tag_lang2 = new ArrayList();
				List tag_tool2 = new ArrayList();
				List tag_field2 = new ArrayList();
				List tag_skill2 = new ArrayList();				

				List tag_lang3 = new ArrayList();
				List tag_tool3 = new ArrayList();
				List tag_field3 = new ArrayList();
				List tag_skill3 = new ArrayList();
				List prof_skill_level3 = new ArrayList();
				
				List tag_lang4 = new ArrayList();
				List tag_tool4 = new ArrayList();
				List tag_field4 = new ArrayList();
				List tag_skill4 = new ArrayList();
				List prof_skill_level4 = new ArrayList();

				int req_prof_id = getProf_id(mem_id);

			// 태그 테이블 추가//////////////////////////////////////////////
			// 언어
			if (tag_lang != null) {			
				boolean check = true;		//태그 테이블에 있는 데이터인지 아닌지 검사(false이면 태그 테이블에 삽입)

				for (int i = 0; i < tag_lang.size(); i++) {			
					check = tagNameCheck(tag_lang.get(i).toString());	//tag_lang에 입력된 데이터가 태그 테이블에 있는지 체크

					if (!check) {										//false(테이블에 없는 경우)
						tag_lang2.add(tag_lang.get(i).toString());		//tag_lang2에 데이터(태그명) 입력 / insert 해야 할 부분
					}
					else{
						tag_lang3.add(tag_lang.get(i).toString());		//tag_lang3에 데이터(태그명) 입력 / update 해야 할 부분
						tag_lang4.add(tagNameToId(tag_lang.get(i).toString()));		//tag_lnag4에 수정할 데이터의 tag_id를 가져옴
					}
				}
		
				
				//태그 테이블 insert(tag_lang2일때만 insert)
				for (int i = 0; i < tag_lang2.size(); i++) {
					sql = "INSERT INTO tag(tag_id, tag_type, tag_name) VALUES(seq_tag_id.nextVal,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "language");
					stmt.setString(2, tag_lang2.get(i).toString());
					System.out.println("tag_lang2.get(i).toString() " + tag_lang2.get(i).toString());
					stmt.executeUpdate();
		
				}
				
				//태그 테이블 update(tag_lang4일때만 update)
				for (int i = 0; i < tag_lang3.size(); i++) {
					sql = "update tag set tag_name=? where tag_id = ? and tag_type= 'language' ";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, tag_lang3.get(i).toString());
					System.out.println("tag_lang3.get(i).toString() " + tag_lang3.get(i).toString());
					stmt.setInt(2, (int) tag_lang4.get(i));
					System.out.println("tag_lang4.get(i) " + tag_lang4.get(i));
					stmt.executeUpdate();
					
				}
			}
			/*			
			///////////////////
			// 태그 툴 입력
			if (tag_tool != null) {			
				boolean check = true;		//태그 테이블에 있는 데이터인지 아닌지 검사(false이면 태그 테이블에 삽입)

				for (int i = 0; i < tag_tool.size(); i++) {			
					check = tagNameCheck(tag_tool.get(i).toString());	//tag_lang에 입력된 데이터가 태그 테이블에 있는지 체크

					if (!check) {										//false(테이블에 없는 경우)
						tag_tool2.add(tag_tool.get(i).toString());		//tag_lang2에 데이터(태그명) 입력 / insert 해야 할 부분
					}
					else{
						tag_tool3.add(tag_tool.get(i).toString());		//tag_lang3에 데이터(태그명) 입력 / update 해야 할 부분
						tag_tool4.add(tagNameToId(tag_tool.get(i).toString()));		//tag_lnag4에 수정할 데이터의 tag_id를 가져옴
					}
				}
		
				
				//태그 테이블 insert(tag_lang2일때만 insert)
				for (int i = 0; i < tag_tool2.size(); i++) {
					sql = "INSERT INTO tag(tag_id, tag_type, tag_name) VALUES(seq_tag_id.nextVal,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "tool");
					stmt.setString(2, tag_tool2.get(i).toString());
					System.out.println("tag_tool2.get(i).toString() " + tag_tool2.get(i).toString());
					stmt.executeUpdate();
		
				}
				
				//태그 테이블 update(tag_lang4일때만 update)
				for (int i = 0; i < tag_tool3.size(); i++) {
					sql = "update tag set tag_name=? where tag_id = ? and tag_type= 'tool' ";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, tag_tool3.get(i).toString());
					System.out.println("tag_tool3.get(i).toString() " + tag_tool3.get(i).toString());
					stmt.setInt(2, (int) tag_tool4.get(i));
					System.out.println("tag_tool4.get(i) " + tag_tool4.get(i));
					stmt.executeUpdate();
					
				}
			}
		
			
			////////////////////////
			// 분야(필드)
			if (tag_field != null) {			
				boolean check = true;		//태그 테이블에 있는 데이터인지 아닌지 검사(false이면 태그 테이블에 삽입)

				for (int i = 0; i < tag_field.size(); i++) {			
					check = tagNameCheck(tag_field.get(i).toString());	//tag_lang에 입력된 데이터가 태그 테이블에 있는지 체크

					if (!check) {										//false(테이블에 없는 경우)
						tag_field2.add(tag_field.get(i).toString());		//tag_lang2에 데이터(태그명) 입력 / insert 해야 할 부분
					}
					else{
						tag_field3.add(tag_field.get(i).toString());		//tag_lang3에 데이터(태그명) 입력 / update 해야 할 부분
						tag_field4.add(tagNameToId(tag_field.get(i).toString()));		//tag_lnag4에 수정할 데이터의 tag_id를 가져옴
					}
				}
		
				
				//태그 테이블 insert(tag_field2일때만 insert)
				for (int i = 0; i < tag_field2.size(); i++) {
					sql = "INSERT INTO tag(tag_id, tag_type, tag_name) VALUES(seq_tag_id.nextVal,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "field");
					stmt.setString(2, tag_field2.get(i).toString());
					System.out.println("tag_field2.get(i).toString() " + tag_field2.get(i).toString());
					stmt.executeUpdate();
		
				}
				
				//태그 테이블 update(tag_field4일때만 update)
				for (int i = 0; i < tag_field3.size(); i++) {
					sql = "update tag set tag_name=? where tag_id = ? and tag_type= 'language' ";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, tag_field3.get(i).toString());
					System.out.println("tag_field4.get(i).toString() " + tag_field3.get(i).toString());
					stmt.setInt(2, (int) tag_field4.get(i));
					System.out.println("tag_field4.get(i) " + tag_field4.get(i));
					stmt.executeUpdate();
					
				}
			}

			///////////////////
			// 스킬
			if (tag_skill != null) {			
				boolean check = true;		//태그 테이블에 있는 데이터인지 아닌지 검사(false이면 태그 테이블에 삽입)

				for (int i = 0; i < tag_skill.size(); i++) {			
					check = tagNameCheck(tag_skill.get(i).toString());	//tag_lang에 입력된 데이터가 태그 테이블에 있는지 체크

					if (!check) {										//false(테이블에 없는 경우)
						tag_skill2.add(tag_skill.get(i).toString());		//tag_lang2에 데이터(태그명) 입력 / insert 해야 할 부분
					}
					else{
						tag_skill3.add(tag_skill.get(i).toString());		//tag_lang3에 데이터(태그명) 입력 / update 해야 할 부분
						tag_skill4.add(tagNameToId(tag_skill.get(i).toString()));		//tag_lnag4에 수정할 데이터의 tag_id를 가져옴
					}
				}
		
				
				//태그 테이블 insert(tag_skill2일때만 insert)
				for (int i = 0; i < tag_skill2.size(); i++) {
					sql = "INSERT INTO tag(tag_id, tag_type, tag_name) VALUES(seq_tag_id.nextVal,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "skill_level");
					stmt.setString(2, tag_skill2.get(i).toString());
					System.out.println("tag_skill2.get(i).toString() " + tag_skill2.get(i).toString());
					stmt.executeUpdate();
		
				}
				
				//태그 테이블 update(tag_skill4일때만 update)
				for (int i = 0; i < tag_skill3.size(); i++) {
					sql = "update tag set tag_name=? where tag_id = ? and tag_type= 'language' ";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, tag_skill3.get(i).toString());
					System.out.println("tag_field4.get(i).toString() " + tag_field3.get(i).toString());
					stmt.setInt(2, (int) tag_field4.get(i));
					System.out.println("tag_field4.get(i) " + tag_field4.get(i));
					stmt.executeUpdate();
					
				}
			}

	*/
			List tag_lang5 = new ArrayList();
			List tag_tool5 = new ArrayList();
			List tag_field5 = new ArrayList();
			List tag_skill5 = new ArrayList();
			List prof_skill_level5 = new ArrayList();
			////////////////// 태그유즈 테이블 입력

			//tag_use테이블에서 해당하는 프로필의 아이디를 지우고 다시 받음(update)			
			sql = "DELETE FROM tag_use where tag_use_type='prof' and tag_use_type_id = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, req_prof_id);
			stmt.executeQuery();

			boolean check = true;  // 입력한 태그의 갯수가 몇 개 인지 갯수 추출

			
			// 태그 유즈에 language를 입력
			for (int i = 0; i < tag_lang.size(); i++) {
				if ("".equals(tag_lang.get(i).toString()) || " ".equals(tag_lang.get(i).toString())
						|| null == tag_lang.get(i).toString()) {
					continue;
				} 
				else {
					tag_lang5.add(tagNameToId(tag_lang.get(i).toString()));
				}
				System.out.println("랭 크기 : " +tag_lang5);
			}				
			
			
			if (tag_lang != null) {					
				sql = "INSERT INTO tag_use( tag_use_id, tag_use_type, tag_use_type_id, tag_id )"
						+ " VALUES(seq_tag_use_id.nextVal,?,?,?)";
				for (int i = 0; i < tag_lang5.size(); i++) {
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "prof");
					stmt.setInt(2, req_prof_id);
					stmt.setInt(3, (int) tag_lang5.get(i));
					stmt.executeUpdate();
				}
			}
			/*		
		/////////////////////////////////
			// 태그 유즈에 tool 입력
			for (int i = 0; i < tag_tool.size(); i++) {
				if ("".equals(tag_tool.get(i).toString()) || " ".equals(tag_tool.get(i).toString())
						|| null == tag_tool.get(i).toString()) {
					continue;
				} 
				else {
					tag_tool5.add(tagNameToId(tag_tool.get(i).toString()));
				}
				System.out.println("툴 번호:"+tagNameToId(tag_tool.get(i).toString()));
			}				
			System.out.println("툴 크기 : " + tag_lang5.size());
			
			if (tag_tool != null) {					
				sql = "INSERT INTO tag_use( tag_use_id, tag_use_type, tag_use_type_id, tag_id )"
						+ " VALUES(seq_tag_use_id.nextVal,?,?,?)";
				for (int i = 0; i < tag_lang5.size(); i++) {
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "prof");
					stmt.setInt(2, req_prof_id);
					stmt.setInt(3, (int) tag_tool5.get(i));
					stmt.executeUpdate();
				}
			}

		
				
			// 태그 유즈에 field을 넣기 위한 리스트
			for (int i = 0; i < tag_field.size(); i++) {
				if ("".equals(tag_field.get(i).toString()) || " ".equals(tag_field.get(i).toString())
						|| null == tag_field.get(i).toString()) {
					continue;
				} else {
					tag_field3.add(tagNameToId(tag_field.get(i).toString()));
				}
			}

			// 분야(필드) 유즈
			if (tag_field != null) {
				for (int i = 0; i < tag_field3.size(); i++) {
					sql = "INSERT INTO tag_use(" + "tag_use_id, tag_use_type, tag_use_type_id, tag_id"
							+ ") VALUES(seq_tag_use_id.nextVal,?,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "prof");
					stmt.setInt(2, req_prof_id);
					stmt.setInt(3, (int) tag_field3.get(i));
					stmt.executeUpdate();
				}
			}

			// 태그 유즈에 스킬 점수를 넣기 위한 리스트
			for (int i = 0; i < tag_skill.size(); i++) {
				if ("".equals(tag_skill.get(i).toString()) || " ".equals(tag_skill.get(i).toString())
						|| null == tag_skill.get(i).toString()) {
					continue;
				} else {
					tag_skill3.add(tagNameToId(tag_skill.get(i).toString()));
				}
			}

			for (int i = 0; i < prof_skill_level.size(); i++) {
				if ("".equals(prof_skill_level.get(i).toString()) || " ".equals(prof_skill_level.get(i).toString())
						|| null == prof_skill_level.get(i).toString()) {
					continue;
				} else {
					prof_skill_level3.add((prof_skill_level.get(i).toString()));
				}
			}

			// 분야(필드) 유즈
			if (tag_field != null) {
				for (int i = 0; i < tag_skill3.size(); i++) {
					sql = "INSERT INTO tag_use(" + "tag_use_id, tag_use_type, tag_use_type_id, tag_id, prof_skill_level"
							+ ") VALUES(seq_tag_use_id.nextVal,?,?,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "prof");
					stmt.setInt(2, req_prof_id);
					stmt.setInt(3, (int) tag_skill3.get(i));
					stmt.setString(4, prof_skill_level3.get(i).toString());
					stmt.executeUpdate();
				}
			}
*/			
		} catch (Exception e) {
			System.out.println("insert 오류 " + e);
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return dto;
	}

	/**
	 * 프로필 리스트
	 */
	public Profile getProfile(int mem_id) {
		Profile dto = new Profile();
		sql = "select distinct mem_id,prof_id, prof_name, prof_nick, prof_website, prof_github, profile.prof_regdate, "
				+ " prof_facebook, prof_regdate, prof_follower,prof_img,prof_background,prof_intro "
				+ " from tag join tag_use on tag.tag_id = tag_use.tag_id "
				+ " join profile on tag_use_type_id = profile.prof_id  where mem_id=" + mem_id + ""
				+ " order by profile.prof_regdate desc";

		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {

				dto.setProf_id(rs.getInt("prof_id"));
				dto.setProf_nick(rs.getString("prof_nick"));
				dto.setProf_name(rs.getString("prof_name"));
				dto.setProf_intro(rs.getString("prof_intro"));
				dto.setProf_img(rs.getString("prof_img"));
				dto.setProf_background(rs.getString("prof_background"));
				dto.setProf_website(rs.getString("prof_website"));
				dto.setProf_github(rs.getString("prof_github"));
				dto.setProf_facebook(rs.getString("prof_facebook"));
				dto.setProf_regdate(rs.getDate("prof_regdate"));

				int prof_id = rs.getInt("prof_id");

				dto.setTag_lang(tags_lang(prof_id));
				dto.setTag_tool(tags_tool(prof_id));
				dto.setTag_field(tags_field(prof_id));
				dto.setTag_skill(tags_skill(prof_id));
				dto.setProf_skill_level(prof_skill_levels(prof_id));
			}

		} catch (Exception err) {
			System.out.println("getProfile 오류: " + err);
			err.printStackTrace();
		} finally {
			freeConnection();
		}

		return dto;
	}

	/**
	 * language 태그 리스트를 가지고 오는 메서드
	 */
	public List tags_lang(int prof_id) {
		try {
			String sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ " WHERE t.tag_id = tu.tag_id and tu.tag_use_type = 'prof' and"
					+ " t.tag_type='language' and tu.tag_use_type_id = ?) ";

			ArrayList list = new ArrayList();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs2 = stmt.executeQuery();

			List<String> tags = new ArrayList<>();
			while (rs2.next()) {
				tags.add(rs2.getString("tag_name"));
			}
			return tags;
		} catch (Exception e) {
			System.out.println("tags_lang 오류 " + e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * tool 태그 리스트를 가지고 오는 메서드
	 */
	public List tags_tool(int prof_id) {
		try {
			String sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ " WHERE t.tag_id = tu.tag_id and tu.tag_use_type = 'prof' and"
					+ " t.tag_type='tool' and tu.tag_use_type_id = ?) ";

			ArrayList list = new ArrayList();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs2 = stmt.executeQuery();

			List<String> tags = new ArrayList<>();
			while (rs2.next()) {
				tags.add(rs2.getString("tag_name"));
			}
			return tags;
		} catch (Exception e) {
			System.out.println("tags_tool 오류 " + e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * field 태그 리스트를 가지고 오는 메서드
	 */
	public List tags_field(int prof_id) {
		try {
			String sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ " WHERE t.tag_id = tu.tag_id and tu.tag_use_type = 'prof' and"
					+ " t.tag_type='field' and tu.tag_use_type_id = ?) ";

			ArrayList list = new ArrayList();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs2 = stmt.executeQuery();

			List<String> tags = new ArrayList<>();
			while (rs2.next()) {
				tags.add(rs2.getString("tag_name"));
			}
			return tags;
		} catch (Exception e) {
			System.out.println("tags_field 오류 " + e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * skill_level 태그 리스트를 가지고 오는 메서드
	 */
	public List tags_skill(int prof_id) {
		try {
			String sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ " WHERE t.tag_id = tu.tag_id and tu.tag_use_type = 'prof' and"
					+ " t.tag_type='skill_level' and tu.tag_use_type_id = ?) ";

			ArrayList list = new ArrayList();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs2 = stmt.executeQuery();

			List<String> tags = new ArrayList<>();
			while (rs2.next()) {
				tags.add(rs2.getString("tag_name"));
			}
			return tags;
		} catch (Exception e) {
			System.out.println("tags_skill 오류 " + e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * skill_level 점수 태그 리스트를 가지고 오는 메서드
	 */
	public List prof_skill_levels(int prof_id) {
		try {
			String sql = "SELECT prof_skill_level FROM (SELECT * FROM tag t, tag_use tu "
					+ " WHERE t.tag_id = tu.tag_id and tu.tag_use_type = 'prof' and"
					+ " t.tag_type='skill_level' and tu.tag_use_type_id = ?) ";

			ArrayList list = new ArrayList();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prof_id);
			rs2 = stmt.executeQuery();

			List<String> tags = new ArrayList<>();
			while (rs2.next()) {
				tags.add(rs2.getString("prof_skill_level"));
			}
			return tags;
		} catch (Exception e) {
			System.out.println("prof_skill_levels 오류 " + e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 같은 프로필 번호를 쓰는 프로필이 있는지 검사
	 * 
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
	 * 
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
			if (mem_id == rs.getInt(1)) {
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
	 * 프로필 삭제
	 * 
	 * @param eno
	 */

	// deleteEmp_proc.jsp
	public void deleteProfile(int prof_id) {
		String sql = "delete from profile where prof_id =" + prof_id + "";

		try {
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println("DBCP 연결 실패 : " + err);
		} finally {
			freeConnection();
		}

	}

	/**
	 * 닉네임으로 프로필 번호 얻기
	 * 
	 * @param nick
	 * @return
	 */
	public int nickToId(String nick) {
		int prof_id = 0;
		getConnection();
		try {
			String sql = "SELECT prof_id FROM profile WHERE prof_nick=?";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			rs.next();
			prof_id = rs.getInt("prof_id");
			return prof_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prof_id;
	}

	/**
	 * 프로필 번호로 닉네임 얻기
	 * 
	 * @param nick
	 * @return
	 */
	public String idToNick(int mem_id) {
		String prof_nick = null;
		getConnection();
		try {
			String sql = "SELECT prof_nick FROM profile WHERE mem_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs = stmt.executeQuery();
			rs.next();
			prof_nick = rs.getString("prof_nick");
			return prof_nick;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prof_nick;
	}

}
