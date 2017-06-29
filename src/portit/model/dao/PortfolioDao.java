package portit.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Portfolio;
import portit.model.dto.Tag;
import portit.model.dto.Media;

/**
 * 포트폴리오 DAO
 */
public class PortfolioDao {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	
	private MediaDao mediaDao;
	
	public PortfolioDao() {
		try {
			pool = DBConnectionMgr.getInstance();
			conn = pool.getConnection();
			mediaDao = new MediaDao();
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
	 * 개별 데이터 조회
	 * @param pf_id
	 * @return DTO
	 */
	public Portfolio selectOne(int pf_id) {
		Portfolio portfolio = null;
		
		// SELECT문 지정
		String sql = "SELECT * FROM portfolio WHERE pf_id=?";
		
		// DB에 접속해서 작업 실행
		getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pf_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				// DB 조회 결과를 DTO에 저장
				portfolio = new Portfolio()
						.setPf_id(rs.getInt("pf_id"))
						.setPf_title(rs.getString("pf_title"))
						.setPf_intro(rs.getString("pf_intro"))
						.setPf_regdate(rs.getDate("pf_regdate"))
						.setPf_startdate(rs.getDate("pf_startdate"))
						.setPf_enddate(rs.getDate("rs_enddate"))
						.setPf_url(rs.getString("pf_repository"));
				
				// 작성자명을 조회해서 DTO에 저장
				sql = "SELECT prof.prof_name FROM profile prof "
						+ "INNER JOIN prof_pf pp "
						+ "ON prof.prof_id=pp.prof_id WHERE prof.pf_id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, portfolio.getPf_id());
				rs = stmt.executeQuery();
				rs.next();
				portfolio.setPf_authorName(rs.getString(1));
				
				// 좋아요 수를 조회해서 DTO에 저장
				sql = "SELECT COUNT(*) FROM pf_like WHERE pf_id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, portfolio.getPf_id());
				rs = stmt.executeQuery();
				rs.next();
				portfolio.setPf_like(rs.getInt(1));
				
				// 태그 사용 데이터를 조회해서 DTO에 저장
				List<Tag> tags_language = new ArrayList<Tag>();
				List<Tag> tags_tool= new ArrayList<Tag>();
				List<Tag> tags_field= new ArrayList<Tag>();
				sql = "SELECT * FROM tag_use tu INNER JOIN tag t "
						+ "ON tu.tag_id=t.tag_id "
						+ "WHERE tu.tag_use_type=? AND tu.tag_use_type_id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "portfolio");
				stmt.setInt(2, portfolio.getPf_id());
				rs = stmt.executeQuery();
				while (rs.next()) {
					if ("language".equalsIgnoreCase(rs.getString("t.tag_type"))) {
						Tag tag_language = new Tag()
								.setTag_id(rs.getInt("t.tag_id"))
								.setTag_type(rs.getString("t.tag_type"))
								.setTag_name("t.tag_name");
						tags_language.add(tag_language);
					}
					else if ("tool".equalsIgnoreCase(rs.getString("t.tag_type"))) {
						Tag tag_tool = new Tag()
								.setTag_id(rs.getInt("t.tag_id"))
								.setTag_type(rs.getString("t.tag_type"))
								.setTag_name("t.tag_name");
						tags_tool.add(tag_tool);
					}
					else if ("field".equalsIgnoreCase(rs.getString("t.tag_type"))) {
						Tag tag_field = new Tag()
								.setTag_id(rs.getInt("t.tag_id"))
								.setTag_type(rs.getString("t.tag_type"))
								.setTag_name("t.tag_name");
						tags_field.add(tag_field);
					}
				}
				portfolio.setPf_tags_language(tags_language);
				portfolio.setPf_tags_tool(tags_tool);
				portfolio.setPf_tags_field(tags_field);
				
				// 미디어 데이터를 조회해서 DTO에 저장
				List<Media> mediae = new ArrayList<Media>();
				sql = "SELECT * FROM media_library WHERE ml_type=? AND ml_type_id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "portfolio");
				stmt.setInt(2, portfolio.getPf_id());
				rs = stmt.executeQuery();
				while (rs.next()) {
					Media media = new Media()
							.setMl_id(rs.getInt("ml_id"))
							.setMl_type(rs.getString("ml_type"))
							.setMl_type_id(rs.getInt("ml_type_id"))
							.setMl_path(rs.getString("ml_path"));
					mediae.add(media);
				}
				portfolio.setPf_mediae(mediae);
			}
		} catch (Exception e) {
			System.out.println("DB 조회 오류 :");
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return portfolio;
	}
	
	/**
	 * 전체 데이터 조회
	 * @param sort 정렬할 칼럼
	 * @param keyword 검색어
	 * @param tagList 검색에 사용할 태그
	 * @return DTO 목록
	 */
	public List<Portfolio> selectList(String sort, String keyword, List<Tag> tagList) {
		List<Portfolio> portfolios = new ArrayList<Portfolio>();
		
		// SELECT문 지정
		String sql = "SELECT * FROM portfolio";
		// 검색 조건 추가
		if (!keyword.isEmpty() || tagList != null) {
			sql += " WHERE";
		}
		if (!keyword.isEmpty()) {
			sql += " pf_title LIKE '%" + keyword + "%'"
					+ " OR pf_intro LIKE '%" + keyword + "%'";
		}
		if (!sort.isEmpty()) {
			sql += " ORDER BY " + sort + " DESC";
		}
		
		// DB에 접속해서 작업 실행
		getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				// DB 조회 결과를 DTO에 저장
				Portfolio portfolio = new Portfolio()
						.setPf_id(rs.getInt("pf_id"))
						.setPf_title(rs.getString("pf_title"))
						.setPf_intro(rs.getString("pf_intro"))
						.setPf_regdate(rs.getDate("pf_regdate"))
						.setPf_startdate(rs.getDate("pf_startdate"))
						.setPf_enddate(rs.getDate("rs_enddate"))
						.setPf_url(rs.getString("pf_repository"));
				
				// 작성자명을 조회해서 DTO에 저장
				sql = "SELECT prof.prof_name FROM profile prof "
						+ "INNER JOIN prof_pf pp "
						+ "ON prof.prof_id=pp.prof_id WHERE prof.pf_id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, portfolio.getPf_id());
				rs = stmt.executeQuery();
				rs.next();
				portfolio.setPf_authorName(rs.getString(1));
				
				// 좋아요 수를 조회해서 DTO에 저장
				sql = "SELECT COUNT(*) FROM pf_like WHERE pf_id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, portfolio.getPf_id());
				rs = stmt.executeQuery();
				rs.next();
				portfolio.setPf_like(rs.getInt(1));
				
				// 태그 사용 데이터를 조회해서 DTO에 저장
				List<Tag> tags_language = new ArrayList<Tag>();
				List<Tag> tags_tool= new ArrayList<Tag>();
				List<Tag> tags_field= new ArrayList<Tag>();
				sql = "SELECT * FROM tag_use tu INNER JOIN tag t "
						+ "ON tu.tag_id=t.tag_id "
						+ "WHERE tu.tag_use_type=? AND tu.tag_use_type_id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "portfolio");
				stmt.setInt(2, portfolio.getPf_id());
				rs = stmt.executeQuery();
				while (rs.next()) {
					if ("language".equalsIgnoreCase(rs.getString("t.tag_type"))) {
						Tag tag_language = new Tag()
								.setTag_id(rs.getInt("t.tag_id"))
								.setTag_type(rs.getString("t.tag_type"))
								.setTag_name("t.tag_name");
						tags_language.add(tag_language);
					}
					else if ("tool".equalsIgnoreCase(rs.getString("t.tag_type"))) {
						Tag tag_tool = new Tag()
								.setTag_id(rs.getInt("t.tag_id"))
								.setTag_type(rs.getString("t.tag_type"))
								.setTag_name("t.tag_name");
						tags_tool.add(tag_tool);
					}
					else if ("field".equalsIgnoreCase(rs.getString("t.tag_type"))) {
						Tag tag_field = new Tag()
								.setTag_id(rs.getInt("t.tag_id"))
								.setTag_type(rs.getString("t.tag_type"))
								.setTag_name("t.tag_name");
						tags_field.add(tag_field);
					}
				}
				portfolio.setPf_tags_language(tags_language);
				portfolio.setPf_tags_tool(tags_tool);
				portfolio.setPf_tags_field(tags_field);
				
				// 미디어 데이터를 조회해서 DTO에 저장
				List<Media> mediae = new ArrayList<Media>();
				sql = "SELECT * FROM media_library WHERE ml_type=? AND ml_type_id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "portfolio");
				stmt.setInt(2, portfolio.getPf_id());
				rs = stmt.executeQuery();
				while (rs.next()) {
					Media media = new Media()
							.setMl_id(rs.getInt("ml_id"))
							.setMl_type(rs.getString("ml_type"))
							.setMl_type_id(rs.getInt("ml_type_id"))
							.setMl_path(rs.getString("ml_path"));
					mediae.add(media);
				}
				portfolio.setPf_mediae(mediae);
				
				// 데이터 객체를 목록에 저장
				portfolios.add(portfolio);
			}
		} catch (Exception e) {
			System.out.println("DB 조회 오류 :");
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return portfolios;
	}
	
	/**
	 * 데이터 추가
	 * @param pf_id
	 * @return 추가된 데이터 개수
	 */
	public int insert(Portfolio portfolio) {
		int rows = 0;
		try {
			// INSERT문 지정
			String sql = "INSERT INTO portfolio("
					+ "pf_id, pf_title, pf_intro, pf_regdate, pf_like, "
					+ "pf_startdate, pf_enddate, pf_numofperson, pf_repository"
					+ ") VALUES(?,?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "seq_pf_id.nextVal");
			stmt.setString(2, portfolio.getPf_title());
			stmt.setString(3, portfolio.getPf_intro());
			stmt.setString(4, "SYSDATE");
			stmt.setInt(5, 0);
			stmt.setDate(6, new Date(portfolio.getPf_startdate().getTime()));
			stmt.setDate(7, new Date(portfolio.getPf_enddate().getTime()));
			stmt.setInt(6, portfolio.getPf_numofperson());
			stmt.setString(6, portfolio.getPf_url());
			rows += stmt.executeUpdate();

			// 태그사용 추가
			sql = "INSERT INTO tag_use("
					+ "tag_use_id, tag_use_type, tag_use_type_id, tag_id"
					+ ") VALUES(?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "seq_tag_use_id.nextVal");
			stmt.setString(2, "portfolio");
			stmt.setInt(3, portfolio.getPf_id());
			stmt.setString(4, );
			rows += stmt.executeUpdate();
			
			// 미디어 라이브러리 추가
			
			
			// 프로필과 포트폴리오 연결
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	/**
	 * 데이터 수정
	 * @param pf_id
	 * @return 수정된 데이터 개수
	 */
	public int update(Portfolio portfolio) {
		int rows = 0;
		// UPDATE문 지정
		String sql = "";
		return rows;
	}
	
	/**
	 * 데이터 삭제
	 * @param prof_id
	 * @return 삭제된 데이터 개수
	 */
	public int delete(Portfolio portfolio) {
		int rows = 0;
		// DELETE문 지정
		String sql = null;
		getConnection();
		try {
			// 미디어 데이터 삭제
			mediaDao.delete(conn, "portfolio", portfolio.getPf_id());
			
			// 포트폴리오의 좋아요 데이터 삭제
			sql = "DELETE FROM pf_like WHERE pf_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, portfolio.getPf_id());
			stmt.executeUpdate();
			
			// 내가 등록한 포트폴리오 데이터 삭제
			sql = "DELETE FROM prof_pf WHERE pf_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, portfolio.getPf_id());
			stmt.executeUpdate();
			
			// 포트폴리오 데이터 삭제
			sql = "DELETE FROM portfolio WHERE pf_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, portfolio.getPf_id());
			rows += stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("DB 삭제 오류 :");
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return rows;
	}
	
	
	// 특정인이 작성한 데이터 조회
	public List<Portfolio> selectSomeones(int mem_id) {
		List<Portfolio> portfolios = new ArrayList<Portfolio>();
		getConnection();
		try {
			String sql = "SELECT prof_id FROM profile WHERE mem_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				int prof_id = rs.getInt("prof_id");
				sql = "SELECT pf_id FROM prof_pf WHERE prof_id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, prof_id);
				rs = stmt.executeQuery();
				while(rs.next()) {
					portfolios.add(selectOne(rs.getInt("pf_id")));
				}
			}
		} catch (Exception e) {
			System.out.println("");
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return portfolios;
	}

}