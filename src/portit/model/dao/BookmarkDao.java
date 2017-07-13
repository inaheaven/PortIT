package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Bookmark;
import portit.model.dto.Portfolio;
import portit.model.dto.Tag;

/**
 * 
 * @author hyang
 *
 */
public class BookmarkDao {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs, rs2, rs3, rs4;
	private DBConnectionMgr pool;
	String sql = "";

	public BookmarkDao() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("而ㅻ꽖�뀡 �� �삤瑜� - FollowDao()");
			e.printStackTrace();
		}
	}

	/**
	 * 내가 북마크한 리스트 가져오기
	 * @param pf_id 
	 * 
	 * @param mem_id2
	 * @param mem_id(세션의
	 *            loginId)
	 * @return list
	 */
	
	public List myBookmark(int mem_id) {
		ArrayList bmList = new ArrayList();
		try {
			conn = pool.getConnection();
			sql = "SELECT distinct BOOK.* , PORT.PF_TITLE, PORT.PF_LIKE, P.PROF_NICK, MEDIA.ML_PATH "
				+ " FROM BOOKMARK BOOK INNER JOIN PORTFOLIO PORT ON BOOK.PF_ID = PORT.PF_ID "
		        + " JOIN MEDIA_LIBRARY MEDIA ON PORT.PF_ID = MEDIA.ML_TYPE_ID"
		        + " JOIN MEMBER M ON M.MEM_ID = BOOK.MEM_ID JOIN PROFILE P ON M.MEM_ID = P.MEM_ID "
		        + " WHERE BOOK.MEM_ID = ?";
		        
					
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs = stmt.executeQuery();

			
			while (rs.next()) {
				Bookmark bm = new Bookmark();
				bm.setBm_id(rs.getInt("bm_id"));
				bm.setMem_id(rs.getInt("mem_id"));
				bm.setPf_id(rs.getInt("pf_id"));
				bm.setBm_date(rs.getDate("bm_date"));
				//bm.setMl_path(rs.getString("ml_path"));
				bm.setProf_nick(rs.getString("prof_nick"));//여기랑
				bm.setPf_title(rs.getString("pf_title"));
				bm.setPf_like(rs.getInt("pf_like"));
				//bm.setPf_prof_img(rs.getString("pf_prof_img"));
				
				int pf_id = rs.getInt("pf_id");
				bm.setMl_path2(bookmark_media(pf_id));
				bm.setTags(getTag(bm, pf_id));
				bmList.add(bm);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, stmt, rs);
		}
		return bmList;
	}

	/**
	 * 북마크한 사람의 포트폴리오 정보 가지고오기 (이미지, 제목, 좋아요수, 작성자이름 )
	 * 
	 * @param mem_id
	 * @return
	 */
	/*
	public Bookmark getPortfolio(Bookmark bm, int mem_id) {
		/*
		 * String sql =
		 * "select * from member join bookmark on member.mem_id = bookmark.mem_id"
		 * + "join portfolio on portfolio.pf_id = bookmark.pf_id" +
		 * "where  member.mem_id = '"+ mem_id+"'" ;
		 */
/*
		try {
			sql = "select distinct Member.mem_id, PORTFOLIO.PF_ID, MEDIA_LIBRARY.ML_PATH, portfolio.PF_TITLE ,Profile.PROF_NICK, portfolio.PF_LIKE "
					+ "FROM media_library, Profile, portfolio, prof_pf, tag_use, member "
					+ "where prof_pf.PROF_ID = Profile.PROF_ID  and prof_pf.PF_ID = portfolio.PF_ID  "
					+ "and MEDIA_LIBRARY.ML_TYPE_ID = portfolio.PF_ID and Profile.mem_id=member.mem_id "
					+ "and Member.mem_id= ? " ;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			rs2 = stmt.executeQuery();

			if(rs2.next()) {
				System.out.println("구동");
				bm.setMem_id(rs.getInt("mem_id"));
				bm.setPf_id(rs.getInt("PF_ID"));
				
				bm.setProf_name(rs.getString("prof_name"));
				
			}
		}

		catch (Exception e) {
			 e.printStackTrace();
		}
		return bm;
	}
*/
	/**
	 * 북마크한 사람의 프로필 중에서 태그 부분 종합해서 가져오기
	 * 
	 * @param bm_id
	 *///여기는 태그부분 여기서 태그 찾아와서 띄워줘야행 # 옆에다가  왜 두개야? 태그 불러오는 쿼리가 복잡해서 두개로 나눠서 메소드 
	
	public List<String> getTag(Bookmark bm, int pf_id) {
		try {
			sql = "SELECT tag_name FROM (SELECT * FROM tag t, tag_use tu "
					+ " WHERE t.tag_id = tu.tag_id AND tu.tag_use_type = 'pf' "
					+ " AND tu.tag_use_type_id = ? "
					+ " ORDER BY DBMS_RANDOM.RANDOM) WHERE rownum < 4 "; // 랜덤하게
																		// 3개

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pf_id);
			rs3 = stmt.executeQuery();

			List<String> tags = new ArrayList<>();
			while (rs3.next()) {
				tags.add(rs3.getString("tag_name"));
				
			}
			return tags; 
		} catch (Exception e) {
			System.out.println("TAG() : 여기 에러나지마라 " + e);
		}
		return null;
	}

	/**
	 * 포트폴리오에서 북마크 버튼 눌렀을 때 북마크 추가 되는 부분
	 * 
	 * @param pf_id
	 * @param mem_id
	 */

	public void addBookmark(int pf_id, int mem_id) {
		try {

			// BOOKMARK에 MEM_ID=? AND PF_ID=? 조회해서 데이터가 존재하면 delete 없으면 insert
			conn = pool.getConnection();

			sql = "MERGE INTO BOOKMARK  USING DUAL ON (MEM_ID=? and PF_ID=? ) "
					+ "WHEN MATCHED THEN UPDATE SET bm_ID=1  DELETE  WHERE MEM_ID=? and PF_ID=? "
					+ "WHEN NOT MATCHED THEN INSERT (BM_ID, MEM_ID, PF_ID, BM_DATE) "
					+ "VALUES (SEQ_BM_ID.NEXTVAL,?,?,SYSDATE) ";
					/*//CREATE SEQUENCE SEQ_BM_ID
						           INCREMENT BY 1
						           START WITH   1
						           MAXVALUE 9999999999999999999999999999
						           NOCYCLE
						           NOCACHE;
						           *
						           * 시퀀스 
						           **/
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mem_id);
			stmt.setInt(2, pf_id);
			stmt.setInt(3, mem_id);
			stmt.setInt(4, pf_id);
			stmt.setInt(5, mem_id);
			stmt.setInt(6, pf_id);
			stmt.executeUpdate();

		} catch (Exception err) {
			System.out.println("getList() : 여기 에러나지마라 " + err);
		}
	}

	
	
	/**
	 * mybookmark페이지에서 x버튼 눌러서 삭제
	 * 
	 * @param bm_id
	 */
	public void deleteBookmark(int bm_id) {
		String sql = "delete from bookmark where bm_id =" + bm_id + "";

		try {
			conn = pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println("DBCP  : " + err);
		}
	}
	
	public List bookmark_media(int pf_id) {
		try {
			String sql = "SELECT ml_path FROM (SELECT * FROM portfolio pf, media_library ml "
					+ " WHERE pf.pf_id = ml.ml_type_id AND ml.ml_type = 'pf' AND ml.ml_type_id = ? ) "
					+ " WHERE rownum < 1 ";
			
			ArrayList list = new ArrayList();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pf_id);
			rs4 = stmt.executeQuery();
			
			List<String> media_path = new ArrayList<>();
			while (rs4.next()) {
				media_path.add(rs4.getString("ml_path"));
			}
			return media_path;
		} 
		catch (Exception e) {
			System.out.println("ml_path(proj) 오류" + e);
		}
		return null;
	}
}
