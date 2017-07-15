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
			sql = "SELECT * FROM tag t INNER JOIN tag_use tu ON tu.tag_id=t.tag_id "
					+ "WHERE t.tag_type=? AND tu.tag_use_type=? AND tu.tag_use_type_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tagType);
			stmt.setString(2, articleType);
			stmt.setInt(3, articleId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				tag = new Tag()
						.setTag_id(rs.getInt(1))
						.setTag_type(rs.getString(2))
						.setTag_name(rs.getString(3))
						.setTag_use_id(rs.getInt(4))
						.setTag_use_type(rs.getString(5))
						.setProf_skill_level(rs.getInt(7))
						.setProj_numofperson(rs.getInt(8));
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
	 * @return 등록한 태그 ID
	 */
	public void insertTag(Connection conn, Tag tag) {
		int tag_id = 0;
		try {
			sql = "INSERT INTO tag"
					+ "(tag_id, tag_type, tag_name) "
					+ "VALUES(seq_tag_id.nextval,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag.getTag_type());
			stmt.setString(2, tag.getTag_name());
			stmt.executeUpdate();
			System.out.println("태그 정보 DB에 저장");
			
			sql = "DELETE FROM tag a "
					+ "WHERE a.rowid > (SELECT min(b.rowid) FROM tag b WHERE a.tag_name=b.tag_name)";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
			
			sql = "SELECT seq_tag_id.currVal FROM DUAL";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				tag_id = rs.getInt(1);
			}
			tag.setTag_id(tag_id);
			System.out.println("태그ID 획득하여 DTO에 저장 : "+tag_id);
			
			insertTagUse(conn, tag);
			System.out.println("태그사용 정보 저장");
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			deleteTagUse(conn, tag.getTag_use_type(), tag.getTag_use_type_id());
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
	 * (insertTag()가 호출)
	 * @param conn
	 * @param articleType
	 * @param articleId
	 * @param tag_id
	 * @return
	 */
	public void insertTagUse(Connection conn, Tag tag) {
		try {
			sql = "INSERT INTO tag_use"
					+ "(tag_use_id, tag_use_type, tag_use_type_id, tag_id, prof_skill_level, proj_numofperson) "
					+ "VALUES(seq_tag_use_id.nextVal,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag.getTag_use_type());
			stmt.setInt(2, tag.getTag_use_type_id());
			stmt.setInt(3, tag.getTag_id());
			stmt.setInt(4, tag.getProf_skill_level());
			stmt.setInt(5, tag.getProj_numofperson());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int updateTagUse(Connection conn, String articleType, int articleId, Tag tag) {
		int rows = 0;
		try {
			sql = "SELECT t.tag_id, t.tag_type, tu.prof_skill_level, tu.proj_numofperson "
					+ "FROM tag t INNER JOIN tag_use tu "
					+ "ON t.tag_id=tu.tag_id "
					+ "WHERE t.tag_name=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag.getTag_name());
			rs = stmt.executeQuery();
			if (rs.next()) {
				tag.setTag_id(rs.getInt(1))
				.setTag_type(rs.getString(2))
				.setProf_skill_level(rs.getInt(3))
				.setProj_numofperson(rs.getInt(4));
			}

			sql = "MERGE INTO tag_use tu "
					+ "USING (SELECT * FROM tag_use WHERE tag_use_type=? AND tag_use_type_id=?) t "
					+ "ON tu.tag_use_id=t.tag_use_id "
					+ "WHEN MATCHED THEN "
					+ "UPDATE SET tu.tag_id=? "
					+ "WHERE tu.tag_id!=? AND tag_use_type=? AND tag_use_type_id=? "
					+ "WHEN NOT MATCHED THEN "
					+ "INSERT (tu.tag_use_id, tu.tag_use_type, tu.tag_use_type_id, tu.tag_id) "
					+ "VALUES(seq_tag_use_id.nextVal, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, articleType);
			stmt.setInt(2, articleId);
			stmt.setInt(3, tag.getTag_id());
			stmt.setInt(4, tag.getTag_id());
			stmt.setString(5, articleType);
			stmt.setInt(6, articleId);
			stmt.setString(7, articleType);
			stmt.setInt(8, articleId);
			stmt.setInt(9, tag.getTag_id());
			rows += stmt.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
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
