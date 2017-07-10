package portit.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Media;
import portit.model.dto.Portfolio;
import portit.model.dto.Profile;
import portit.model.dto.Tag;

/**
 * 포트폴리오 DAO
 * 
 */
public class PortfolioDao {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	
	private MediaDao mediaDao;
	private ProfileDao profileDao;
	private TagDao tagDao;
	
	public PortfolioDao() {
		try {
			pool = DBConnectionMgr.getInstance();
			conn = pool.getConnection();
			mediaDao = new MediaDao();
			profileDao = new ProfileDao();
			tagDao = new TagDao();
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
		Portfolio portfolio = new Portfolio();
		
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
				portfolio.setPf_id(rs.getInt("pf_id"))
						.setPf_title(rs.getString("pf_title"))
						.setPf_intro(rs.getString("pf_intro"))
						.setPf_regdate(rs.getDate("pf_regdate"))
						.setPf_startdate(rs.getDate("pf_startdate"))
						.setPf_enddate(rs.getDate("pf_enddate"))
						.setPf_url(rs.getString("pf_repository"));
				
				// 작성자 정보를 조회해서 DTO에 저장
				sql = "SELECT prof.prof_name, prof.prof_nick, prof.prof_img FROM profile prof"
						+ " INNER JOIN prof_pf pp "
						+ " ON prof.prof_id=pp.prof_id WHERE pp.pf_id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, portfolio.getPf_id());
				rs = stmt.executeQuery();
				while (rs.next()) {
					portfolio.setPf_prof_name(rs.getString("prof.prof_name"))
					.setPf_prof_nick(rs.getString("prof.prof_nick"))
					.setPf_prof_img(rs.getString("prof.prof_img"));
				}
				
				// 좋아요 수를 조회해서 DTO에 저장
				sql = "SELECT COUNT(1) FROM pf_like WHERE pf_id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, pf_id);
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
					if ("tool".equalsIgnoreCase(rs.getString("t.tag_type"))) {
						Tag tag_tool = new Tag()
								.setTag_id(rs.getInt("t.tag_id"))
								.setTag_type(rs.getString("t.tag_type"))
								.setTag_name("t.tag_name");
						tags_tool.add(tag_tool);
					}
					if ("field".equalsIgnoreCase(rs.getString("t.tag_type"))) {
						Tag tag_field = new Tag()
								.setTag_id(rs.getInt("t.tag_id"))
								.setTag_type(rs.getString("t.tag_type"))
								.setTag_name("t.tag_name");
						tags_field.add(tag_field);
					}
				}
				portfolio.setPf_tags_language(tags_language)
				.setPf_tags_tool(tags_tool)
				.setPf_tags_field(tags_field);
				
				// 공동 작업자를 조회해서 DTO에 저장
				List<Profile> coworkers = new ArrayList<Profile>();
				sql = "SELECT * FROM pf_coworker WHERE pf_id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, pf_id);
				rs = stmt.executeQuery();
				profileDao = new ProfileDao();
				while (rs.next()) {
					Profile profile = profileDao.getProfile(rs.getInt("mem_id"));
					coworkers.add(profile);
				}
				portfolio.setPf_coworkers(coworkers);
				
				// 미디어 데이터를 조회해서 DTO에 저장
				List<Media> mediaList = mediaDao.selectList(conn, "portfolio", portfolio.getPf_id());
				portfolio.setPf_mediaList(mediaList);
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
				
				// 작성자 정보를 조회해서 DTO에 저장
				sql = "SELECT prof.prof_name, prof.prof_nick, prof.prof_img FROM profile prof"
						+ " INNER JOIN prof_pf pp "
						+ " ON prof.prof_id=pp.prof_id WHERE pp.pf_id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, portfolio.getPf_id());
				rs = stmt.executeQuery();
				while (rs.next()) {
					portfolio.setPf_prof_name(rs.getString("prof.prof_name"))
					.setPf_prof_nick(rs.getString("prof.prof_nick"))
					.setPf_prof_img(rs.getString("prof.prof_img"));
				}
				
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
					if ("tool".equalsIgnoreCase(rs.getString("t.tag_type"))) {
						Tag tag_tool = new Tag()
								.setTag_id(rs.getInt("t.tag_id"))
								.setTag_type(rs.getString("t.tag_type"))
								.setTag_name("t.tag_name");
						tags_tool.add(tag_tool);
					}
					if ("field".equalsIgnoreCase(rs.getString("t.tag_type"))) {
						Tag tag_field = new Tag()
								.setTag_id(rs.getInt("t.tag_id"))
								.setTag_type(rs.getString("t.tag_type"))
								.setTag_name("t.tag_name");
						tags_field.add(tag_field);
					}
				}
				portfolio.setPf_tags_language(tags_language)
				.setPf_tags_tool(tags_tool)
				.setPf_tags_field(tags_field);
				
				// 공동 작업자를 조회해서 DTO에 저장
				List<Profile> coworkers = new ArrayList<Profile>();
				sql = "SELECT * FROM pf_coworker WHERE pf_id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, portfolio.getPf_id());
				rs = stmt.executeQuery();
				profileDao = new ProfileDao();
				while (rs.next()) {
					Profile profile = profileDao.getProfile(rs.getInt("mem_id"));
					coworkers.add(profile);
				}
				portfolio.setPf_coworkers(coworkers);
				
				// 미디어 데이터를 조회해서 DTO에 저장
				List<Media> mediaList = mediaDao.selectList(conn, "portfolio", portfolio.getPf_id());
				portfolio.setPf_mediaList(mediaList);
				
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
	
	public List<Portfolio> selectListByMemId(int mem_id) {
		List<Portfolio> portfolioList = new ArrayList<Portfolio>();
		
		return portfolioList;
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
					+ ") VALUES(seq_pf_id.nextVal,?,?,SYSDATE,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, portfolio.getPf_title());
			stmt.setString(2, portfolio.getPf_intro());
			stmt.setInt(3, 0);
			stmt.setDate(4, new Date(portfolio.getPf_startdate().getTime()));
			stmt.setDate(5, new Date(portfolio.getPf_enddate().getTime()));
			stmt.setInt(6, portfolio.getPf_numofperson());
			stmt.setString(7, portfolio.getPf_url());
			rows += stmt.executeUpdate();
			
			// 작성된 게시물 번호 얻기
			sql = "SELECT pf_id FROM portfolio WHERE pf_id=seq_pf_id.currVal";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			rs.next();
			int pf_id = rs.getInt("pf_id");

			// 태그 추가
			List<Tag> pf_tags_language = portfolio.getPf_tags_language();
			List<Tag> pf_tags_tool = portfolio.getPf_tags_tool();
			List<Tag> pf_tags_field = portfolio.getPf_tags_field();
			if (pf_tags_language != null) {
				for (int i = 0; i < pf_tags_language.size(); i++) {
					tagDao.insertTag(conn, pf_tags_language.get(i));
				}
			}
			if (pf_tags_tool != null) {
				for (int i = 0; i < pf_tags_tool.size(); i++) {
					tagDao.insertTag(conn, pf_tags_tool.get(i));
				}
			}
			if (pf_tags_field != null) {
				for (int i = 0; i < pf_tags_field.size(); i++) {
					tagDao.insertTag(conn, pf_tags_field.get(i));
				}
			}
			// 태그 사용 추가			
			if (pf_tags_language != null) {
				for (int i = 0; i < pf_tags_language.size(); i++) {
					tagDao.insertTagUse(conn, "portfolio", pf_id, pf_tags_language.get(i));
				}
			}
			if (pf_tags_tool != null) {
				for (int i = 0; i < pf_tags_tool.size(); i++) {
					tagDao.insertTagUse(conn, "portfolio", pf_id, pf_tags_tool.get(i));
				}
			}
			if (pf_tags_field != null) {
				for (int i = 0; i < pf_tags_field.size(); i++) {
					tagDao.insertTagUse(conn, "portfolio", pf_id, pf_tags_field.get(i));
				}
			}
			
			// 공동 작업자 추가
			List<Profile> coworkers = portfolio.getPf_coworkers();
			sql = "INSERT INTO pf_coworker(pf_co_id, pf_id, mem_id)"
					+ " VALUES(seq_pf_co_id.nextVal,seq_pf_id.currVal,?)";
			if (coworkers != null) {
				for (int i = 0; i < coworkers.size(); i++) {
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, usernameToId(coworkers.get(i).getProf_nick()));
					rows += stmt.executeUpdate();
				}
			}
			
			// 미디어 라이브러리 추가
			List<Media> mediaList = portfolio.getPf_mediaList();
			if (mediaList != null) {
				for (int i = 0; i < mediaList.size(); i++) {
					mediaDao.insert(conn, mediaList.get(i));
					rows++;
				}
			}
			
			// 프로필과 포트폴리오 연결
			sql = "INSERT INTO prof_pf(prof_pf_id, prof_id, pf_id)"
					+ " VALUES(seq_prof_pf_id.nextVal,?,seq_pf_id.currVal)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, usernameToId(portfolio.getPf_prof_name()));
			rows += stmt.executeUpdate();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return rows;
	}
	
	/**
	 * 데이터 수정
	 * @param pf_id
	 * @return 수정된 데이터 개수
	 */
	public int update(int pf_id, Portfolio portfolio) {
		int rows = 0;
		try {
			// UPDATE문 지정
			String sql = "UPDATE portfolio"
					+ " SET pf_title=?, pf_intro=?, pf_startdate=?,"
					+ " pf_enddate=?, pf_numofperson=?, pf_repository=?"
					+ " WHERE pf_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, portfolio.getPf_title());
			stmt.setString(2, portfolio.getPf_intro());
			stmt.setDate(3, new Date(portfolio.getPf_startdate().getTime()));
			stmt.setDate(4, new Date(portfolio.getPf_enddate().getTime()));
			stmt.setInt(5, portfolio.getPf_numofperson());
			stmt.setString(6, portfolio.getPf_url());
			stmt.setInt(7, portfolio.getPf_id());
			rows += stmt.executeUpdate();

			// 등록되지 않은 태그 추가
			List<Tag> pf_tags_language = portfolio.getPf_tags_language();
			List<Tag> pf_tags_tool = portfolio.getPf_tags_tool();
			List<Tag> pf_tags_field = portfolio.getPf_tags_field();
			if (pf_tags_language != null) {
				for (int i = 0; i < pf_tags_language.size(); i++) {
					tagDao.insertTag(conn, pf_tags_language.get(i));
				}
			}
			if (pf_tags_tool != null) {
				for (int i = 0; i < pf_tags_tool.size(); i++) {
					tagDao.insertTag(conn, pf_tags_tool.get(i));
				}
			}
			if (pf_tags_field != null) {
				for (int i = 0; i < pf_tags_field.size(); i++) {
					tagDao.insertTag(conn, pf_tags_field.get(i));
				}
			}
			
			// 태그 사용 수정
			for (int i = 0; i < pf_tags_language.size(); i++) {
				tagDao.updateTagUse(conn, "portfolio", portfolio.getPf_id(), pf_tags_language.get(i));
			}
			for (int i = 0; i < pf_tags_tool.size(); i++) {
				tagDao.updateTagUse(conn, "portfolio", portfolio.getPf_id(), pf_tags_tool.get(i));
			}
			for (int i = 0; i < pf_tags_field.size(); i++) {
				tagDao.updateTagUse(conn, "portfolio", portfolio.getPf_id(), pf_tags_field.get(i));
			}
			
			// 공동 작업자 수정
			List<Profile> coworkers = portfolio.getPf_coworkers();
			for (int i = 0; i < coworkers.size(); i++) {
				coworkers.get(i).setMem_id(usernameToId(coworkers.get(i).getProf_nick()));
			}
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < coworkers.size(); i++) {
				sql = "MERGE INTO pf_coworker pfc"
						+ " USING portfolio pf"
						+ " ON pfc.pf_id=pf.pf_id"
						+ " WHEN MATCHED THEN"
						+ " UPDATE SET mem_id=?"
						+ " WHEN NOT MATCHED THEN INSERT ("
						+ "pfc.pf_co_id, pf_id, mem_id"
						+ ") VALUES (seq_pf_co_id.nextVal,?,?)";
				stmt.setInt(1, coworkers.get(i).getMem_id());
				stmt.setInt(2, pf_id);
				stmt.setInt(3, coworkers.get(i).getMem_id());
				rows += stmt.executeUpdate();
			}
			
			
			// 미디어 라이브러리 수정
			List<Media> mediaList = portfolio.getPf_mediaList();
			mediaDao.delete(conn, "portfolio", portfolio.getPf_id());
			for (int i = 0; i < mediaList.size(); i++) {
				mediaDao.insert(conn, mediaList.get(i));
				rows++;
			}
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return rows;
	}
	
	/**
	 * 데이터 삭제
	 * @param prof_id
	 * @return 삭제된 데이터 개수
	 */
	public int delete(int pf_id) {
		int rows = 0;
		getConnection();
		try {
			// 미디어 데이터 삭제
			mediaDao.delete(conn, "portfolio", pf_id);
			
			// 포트폴리오의 좋아요 데이터 삭제
			String sql = "DELETE FROM pf_like WHERE pf_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pf_id);
			stmt.executeUpdate();
			
			// 공동 작업자 삭제
			sql = "DELETE FROM pf_coworker WHERE pf_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pf_id);
			stmt.executeUpdate();
			
			// 태그 사용 삭제
			sql = "DELETE FROM tag_use WHERE tag_use_type=? AND tag_use_type_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "portfolio");
			stmt.setInt(2, pf_id);
			stmt.executeUpdate();
			
			// 내가 등록한 포트폴리오 데이터 삭제
			sql = "DELETE FROM prof_pf WHERE pf_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pf_id);
			stmt.executeUpdate();
			
			// 포트폴리오 데이터 삭제
			sql = "DELETE FROM portfolio WHERE pf_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pf_id);
			rows += stmt.executeUpdate();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return rows;
	}
	
	
	/**
	 * 특정인이 작성한 데이터 조회
	 * @param mem_id
	 * @return
	 */
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
	
	/**
	 * 포트폴리오의 좋아요 데이터를 추가하고 좋아요 숫자를 반환
	 * @param mem_id
	 * @param pf_id
	 * @return
	 */
	public int addLike(int mem_id, int pf_id) {
		int likes = 0;
		getConnection();
		try {
			String sql = "INSERT INTO pf_like(pf_lk_id, mem_id, pf_id, pf_lk_regdate)"
					+ " VALUES(seq_pf_lk_id.nextVal,?,?,SYSDATE)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			stmt.setInt(2, pf_id);
			stmt.executeUpdate();
			sql = "SELECT COUNT(*) FROM pf_like WHERE pf_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pf_id);
			rs = stmt.executeQuery();
			rs.next();
			likes = rs.getInt(1);
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return likes;
	}
	
	/**
	 * 회원번호로 프로필 번호를 얻기
	 * @param mem_id
	 * @return
	 */
	private int memToProf(int mem_id) {
		int prof_id = 0;
		getConnection();
		try {
			String sql = "SELECT prof_id FROM profile WHERE mem_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs = stmt.executeQuery();
			rs.next();
			prof_id = rs.getInt("prof_id");
		} catch (Exception e) {
			System.out.println("");
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return prof_id;
	}
	
	/**
	 * 프로필 닉네임으로 회원번호 얻기
	 * @param username
	 * @return
	 */
	private int usernameToId(String username) {
		int mem_id = 0;
		getConnection();
		try {
			String sql = "SELECT mem_id FROM profile WHERE prof_nick=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			rs.next();
			mem_id = rs.getInt("mem_id");
		} catch (Exception e) {
			System.out.println("");
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return mem_id;
	}
	
	/**
	 * 태그 이름으로 번호 얻기
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
			rs.next();
			tag_id = rs.getInt("tag_id");
		} catch (Exception e) {
			System.out.println("");
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return tag_id;
	}

}