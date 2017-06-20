package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import portit.model.dto.Media;

/**
 * 미디어 라이브러리 DAO
 * @author gnsngck
 *
 */
public class MediaDao {

	private PreparedStatement stmt;
	private ResultSet rs;
	private String sql;
	
	/**
	 * 미디어 파일 리스트 조회
	 * @param conn 작업을 요청하는 DB 커넥션
	 * @param articleType 게시물 구분
	 * @param articleId 게시물 ID
	 * @return 미디어 파일 리스트
	 */
	public List<Media> selectAll(Connection conn, String articleType, int articleId) {
		List<Media> mediaList = new ArrayList<Media>();
		Media media = null;
		try {
			sql = "SELECT * FROM media_library WHERE ml_type=? AND ml_type_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, articleType);
			stmt.setInt(2, articleId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				media = new Media()
						.setMl_id(rs.getInt("ml_id"))
						.setMl_type(rs.getString("ml_type"))
						.setMl_type_id(rs.getInt("ml_type_id"))
						.setMl_path(rs.getString("ml_path"));
				mediaList.add(media);
			}
		} catch (Exception e ) {
			e.printStackTrace();
		}
		return mediaList;
	}
	
	/**
	 * DB에 미디어 파일 정보 등록
	 * @param conn 작업을 요청하는 DB 커넥션
	 * @return 작업된 행 갯수
	 */
	public int insert(Connection conn) {
		int i = 0;
		try {
			sql = "INSERT INTO media_library"
					+ "(ml_id, ml_type, ml_type_id, ml_path) "
					+ "VALUES(?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			i = stmt.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * 미디어 파일 정보 수정
	 * @param conn 작업을 요청하는 DB 커넥션
	 * @param articleType 게시물 구분
	 * @param articleId 게시물 ID
	 * @return 작업된 행 갯수
	 */
	public int update(Connection conn, String articleType, int articleId) {
		int i = 0;
		try {
			List<Media> mediaList = selectAll(conn, articleType, articleId);
			sql = "UPDATE media_library SET ml_id=?, ml_type=?, ml_type_id=?, ml_path=?";
			stmt = conn.prepareStatement(sql);
			while (i < mediaList.size()) {
				Media currentMedia = mediaList.get(i);
				stmt.setInt(1, currentMedia.getMl_id());
				stmt.setString(2, currentMedia.getMl_type());
				stmt.setInt(3, currentMedia.getMl_type_id());
				stmt.setString(4, currentMedia.getMl_path());
				stmt.executeUpdate();
				i++;
			}
			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * 미디어 파일 정보 삭제
	 * @param conn 작업을 요청하는 DB 커넥션
	 * @param articleType 게시물 구분
	 * @param articleId 게시물 ID
	 * @return 작업된 행 갯수
	 */
	public int delete(Connection conn, String articleType, int articleId) {
		int i = 0;
		try {
			List<Media> mediaList = selectAll(conn, articleType, articleId);
			sql = "DELETE media_library WHERE ml_type=?, ml_type_id=?";
			stmt = conn.prepareStatement(sql);
			while (i < mediaList.size()) {
				Media currentMedia = mediaList.get(i);
				stmt.setString(1, currentMedia.getMl_type());
				stmt.setInt(2, currentMedia.getMl_type_id());
				stmt.executeUpdate();
				i++;
			}
			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}
