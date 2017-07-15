package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import portit.util.FileDelete;
import portit.model.dto.Media;

/**
 * 미디어 라이브러리 DAO
 *
 */
public class MediaDao {

	private PreparedStatement stmt;
	private ResultSet rs;
	private String sql;
	
	/**
	 * 미디어 정보 리스트 조회
	 * @param conn 작업을 요청하는 DB 커넥션
	 * @param articleType 게시물 구분
	 * @param articleId 게시물 ID
	 * @return 미디어 정보 목록
	 */
	public List<Media> selectList(Connection conn, String articleType, int articleId) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mediaList;
	}
	
	/**
	 * 미디어 정보 등록
	 * @param conn 작업을 요청하는 DB 커넥션
	 * @param media 미디어 DTO
	 * @return 작업된 행 갯수
	 */
	public int insert(Connection conn, Media media) {
		int rows = 0;
		try {
			sql = "INSERT INTO media_library"
					+ "(ml_id, ml_type, ml_type_id, ml_path) "
					+ "VALUES(LPAD(seq_ml_id.nextval, 4, '0'),?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, media.getMl_type());
			stmt.setInt(2, media.getMl_type_id());
			stmt.setString(3, media.getMl_path());
			rows = stmt.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	/**
	 * 미디어 정보 수정
	 * @param conn 작업을 요청하는 DB 커넥션
	 * @param media
	 * @return 작업된 행 갯수
	 */
	public int update(Connection conn, Media media) {
		int rows = 0;
		try {
			sql = "MERGE INTO media_library ml "
					+ "USING (SELECT * FROM media_library WHERE ml_type=? AND ml_type_id=?)  "
					+ "ON ml.ml_id=mls.ml_id"
					+ "WHEN MATCHED THEN "
					+ "UPDATE SET ml.ml_path=? "
					+ "WHERE ml.ml_path!=? AND ml_type=? AND ml_id=? "
					+ "WHEN NOT MATCHED THEN "
					+ "INSERT (ml_id, ml_type, ml_type_id, ml_path) "
					+ "VALUES(seq_ml_id.nextVal, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, media.getMl_type());
			stmt.setInt(2, media.getMl_type_id());
			stmt.setString(3, media.getMl_path());
			stmt.setString(4, media.getMl_path());
			stmt.setString(5, media.getMl_type());
			stmt.setInt(6, media.getMl_id());
			stmt.setString(7, media.getMl_type());
			stmt.setInt(8, media.getMl_type_id());
			stmt.setString(9, media.getMl_path());
			rows = stmt.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	/**
	 * 미디어 정보 삭제
	 * @param conn 작업을 요청하는 DB 커넥션
	 * @param articleType 게시물 구분
	 * @param articleId 게시물 ID
	 * @return 작업된 행 갯수
	 */
	public int delete(Connection conn, String articleType, int articleId) {
		int rows = 0;
		try {
			// 기존 미디어 정보 조회
			List<Media> mediaList = selectList(conn, articleType, articleId);
			
			// 기존 파일 삭제
			deleteFile(mediaList);
			
			// DB에서 정보 삭제
			sql = "DELETE media_library WHERE ml_type=?, ml_type_id=?";
			stmt = conn.prepareStatement(sql);
			while (rows < mediaList.size()) {
				Media currentMedia = mediaList.get(rows);
				stmt.setString(1, currentMedia.getMl_type());
				stmt.setInt(2, currentMedia.getMl_type_id());
				stmt.executeUpdate();
				rows++;
			}
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	/**
	 * MediaController의 삭제 메서드 호출
	 * @param mediaList
	 */
	private void deleteFile(List<Media> mediaList) { 
		List<String> pathList = new ArrayList<String>();
		for (int i = 0; i < mediaList.size(); i++) {
			pathList.add(mediaList.get(i).getMl_path());
		}
		FileDelete fileDelete = new FileDelete();
		fileDelete.fileDelete(pathList);
	}
}