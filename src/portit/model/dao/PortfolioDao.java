package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Portfolio;
import portit.model.dto.Tag;
import portit.model.dto.Media;

/**
 * 포트폴리오 구성 화면
 */
public class PortfolioDao {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	
	public PortfolioDao() {
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
						.setPf_enddate(rs.getDate("rs_enddate"));
				
				// 작성자명을 조회해서 DTO에 저장
				sql = "SELECT prof.prof_name FROM profile prof "
						+ "INNER JOIN prof_pf pp "
						+ "ON prof.prof_id=pp.prof_id WHERE prof.pf_id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, portfolio.getPf_id());
				rs = stmt.executeQuery();
				rs.next();
				portfolio.setPf_prof_name(rs.getString(1));
				
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
	 * @param sort 정렬 기준
	 * @return DTO 목록
	 */
	public List<Portfolio> selectList(String sort) {
		List<Portfolio> portfolios = new ArrayList<Portfolio>();
		
		// SELECT문 지정
		String sql = "SELECT * FROM portfolio";
		// 검색 조건 추가
		sql += " ORDER BY "+sort;
		
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
						.setPf_enddate(rs.getDate("rs_enddate"));
				
				// 작성자명을 조회해서 DTO에 저장
				sql = "SELECT prof.prof_name FROM profile prof "
						+ "INNER JOIN prof_pf pp "
						+ "ON prof.prof_id=pp.prof_id WHERE prof.pf_id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, portfolio.getPf_id());
				rs = stmt.executeQuery();
				rs.next();
				portfolio.setPf_prof_name(rs.getString(1));
				
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
	public int insert(int pf_id) {
		int rows = 0;
		// INSERT문 지정
		String sql = "";
		return rows;
	}
	
	/**
	 * 데이터 수정
	 * @param pf_id
	 * @return 수정된 데이터 개수
	 */
	public int update(int pf_id) {
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
	public int delete(int pf_id) {
		int rows = 0;
		// DELETE문 지정
		String sql = null;
		getConnection();
		try {
			// 미디어 데이터 삭제
			
			// 포트폴리오 데이터 삭제
			sql = "DELETE FROM portfolio WHERE pf_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pf_id);
			rows += stmt.executeUpdate();
			
			// 내가 등록한 포트폴리오 데이터 삭제
			sql = "DELETE FROM prof_pf WHERE pf_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pf_id);
			rows += stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("DB 삭제 오류 :");
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return rows;
	}

}