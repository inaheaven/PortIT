package portit.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Media;
import portit.model.dto.Message;
import portit.model.dto.Portfolio;
import portit.model.dto.Profile;
import portit.model.dto.Tag;
/**
 * 
 * 통합 검색시, DB에서 자료 가지고 오기.
 *
 */
public class SearchDao{
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DBConnectionMgr pool;

	public SearchDao(){
		try{
			pool = DBConnectionMgr.getInstance();
		}
		catch(Exception err){
			System.out.println("SearchDao 오류 : " + err);
		}
	}
	
	
	// index2.html -> search.jsp
	public void TotalSearch(String keytext){
		ArrayList list = new ArrayList();
		String sql = "select MEDIA_LIBRARY.ML_PATH, TAG.TAG_NAME, PORTFOLIO.PF_TITLE, PROFILE.PROF_NAME ,PORTFOLIO.PF_LIKE  "
				+ "+from MEDIA_LIBRARY ,TAG, PROFILE, PORTFOLIO +"
				+ "where PORTFOLIO.PF_TITLE like '?' or TAG.TAG_NAME like '?' ";
	
		try{
			con = pool.getConnection();		
	 		pstmt = con.prepareStatement(sql);	 
	 		
	 		while(rs.next()){
				Media media = new Media();	//이미지위(path)
				Tag tag = new Tag();	//태그명
				Portfolio portfolio = new Portfolio();	//포폴 제목, 좋아요 수
				Profile profile = new Profile();	//멤버 이름
				
				//번호 제목 이름 날짜 조회수	
				media.setMl_path(rs.getString("ml_path"));
				tag.setTag_name(rs.getString("tag_name"));
				portfolio.setPf_title(rs.getString("pf_title"));
				profile.setProf_name(rs.getString("prof_name"));
				portfolio.setPf_like(rs.getInt("pf_like"));
				
				list.add(media);
				list.add(tag);
				list.add(profile);
				list.add(portfolio);
			}
	 		
			pstmt.executeQuery();		
			
		}
		
		catch(Exception err){
			System.out.println("[DAO]: SearchDao()에서 오류");
			err.printStackTrace();
		}
		
		finally{
			pool.freeConnection(con, pstmt);
		}
	}
	
	
	
	// 목록 불러오기.
	public List getMessageList(String keyField, String keyWord){
		
		/* 
		 1.ArrayList 선언
		 2.list에 Select에 조회된 데이터 반복을 저장
		 3.
		 * */
		
		
		
		ArrayList list = new ArrayList();
		String sql = null;
		
		if(keyWord == null){
			sql = "select * from Message where MEM_ID_SENDER=11111 or MEM_ID_RECEIVER=11111;";
		}
		else{
			sql = "select * from Message where " 
				+ keyField + " like '%" + keyWord 
				+ "%' order by b_pos";
		}
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Message message = new Message();
				message.setMsg_id(rs.getInt("MSG_ID"));
				message.setMem_id_sender(rs.getInt("MEM_ID_SENDER"));
				message.setMem_id_receiver(rs.getInt("MEM_ID_RECEIVER"));
				message.setMsg_isread(rs.getString("MSG_ISREAD"));
				message.setMsg_content(rs.getString("MSG_CONTENT"));
				message.setMsg_date(rs.getString("MSG_DATE"));
				list.add(message);
			}
		}
		catch(Exception err){
			System.out.println("getMessageList()에서 오류");
			err.printStackTrace();
		}
		finally{
			pool.freeConnection(con, pstmt, rs);
		}
		return list;
	}
	
		
	
}

