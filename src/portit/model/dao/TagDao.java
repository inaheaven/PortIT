package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import portit.model.dto.Tag;

/**
 * 태그 DAO
 */
public class TagDao {

	private PreparedStatement stmt;
	private ResultSet rs;
	private String sql;
	
	/**
	 * 태그 정보 리스트 조회
	 * @param conn 작업을 요청하는 DB 커넥션
	 * @param tagType 태그 구분
	 * @param articleType 게시물 구분
	 * @param articleId 게시물 ID
	 * @return 태그 정보 목록
	 */
	public List<Tag> selectList(Connection conn, String tagType, String articleType, int articleId) {
		List<Tag> tagList = new ArrayList<Tag>();
		Tag tag = null;
		try {
			sql = "SELECT * FROM tag_use tu "
					+ "INNER JOIN tag t "
					+ "ON tu.tag_id=t.tag_id "
					+ "WHERE t.tag_type=? AND tu.tag_use_type_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tagType);
			stmt.setInt(2, articleId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				tag = new Tag()
						.setTag_id(rs.getInt("t.tag_id"))
						.setTag_type(rs.getString("t.tag_type"))
						.setTag_name(rs.getString("t.tag_name"))
						.setProf_skill_level(rs.getInt("tu.prof_skill_level"))
						.setProj_numofperson(rs.getInt("tu.proj_numofperson"));
				tagList.add(tag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tagList;
	}
	
	/**
	 * 태그 정보 등록
	 * @param conn 작업을 요청하는 DB 커넥션
	 * @param tag 태그
	 * @return 작업된 행 갯수
	 */
	public int insertTag(Connection conn, Tag tag) {
		int rows = 0;
		try {
			sql = "INSERT INTO tag"
					+ "(tag_id, tag_type, tag_name) "
					+ "VALUES(LPAD(seq_tag_id.nextval, 4, '0'),?,?) "
					+ "WHERE tag_name NOT IN (SELECT tag_name FROM tag)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag.getTag_type());
			stmt.setString(2, tag.getTag_name());
			rows = stmt.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	/**
	 * 태그 정보 삭제
	 * @param conn 작업을 요청하는 DB 커넥션
	 * @param tag 태그
	 * @return 작업된 행 갯수
	 */
	public int deleteTag(Connection conn, Tag tag) {
		int rows = 0;
		try {
			sql = "DELETE tag WHERE tag_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, tag.getTag_id());
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	

	
	/**
	 * 태그 사용 정보 등록
	 * @param conn
	 * @param articleType
	 * @param articleId
	 * @param tag_id
	 * @return
	 */
	public int insertTagUse(Connection conn, String articleType, int articleId, Tag tag) {
		int rows = 0;
		try {
			sql = "INSERT INTO tag_use"
					+ "(tag_use_id, tag_use_type, tag_use_type_id, tag_id, prof_skill_level, proj_numofperson) "
					+ "VALUES(seq_tag_use_id.nextVal,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, articleType);
			stmt.setInt(2, articleId);
			stmt.setInt(3, tag.getTag_id());
			stmt.setInt(4, tag.getProf_skill_level());
			stmt.setInt(5, tag.getProj_numofperson());
			rows = stmt.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	
	public int updateTagUse(Connection conn, String articleType, int articleId, Tag tag) {
		int rows = 0;
		try {
			sql = "UPDATE tag_use SET prof_skill_level=?, proj_numofperson=? "
					+ "WHERE tag_use_type=? AND tag_use_type_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, tag.getProf_skill_level());
			stmt.setString(2, articleType);
			stmt.setInt(3, articleId);
			rows += stmt.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		deleteTagUse(conn, articleType, articleId);
		rows += insertTagUse(conn, articleType, articleId, tag);
		return rows;
	}
	
	public int deleteTagUse(Connection conn, String articleType, int articleId) {
		int rows = 0;
		try {
			sql = "DELETE tag_use WHERE tag_use_type=? AND tag_use_type_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, articleType);
			stmt.setInt(2, articleId);
			rows += stmt.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	
}