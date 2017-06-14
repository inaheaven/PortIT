package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Media;
import portit.model.dto.Portfolio;
import portit.model.dto.Profile;
import portit.model.dto.Tag;

public class Portfolio_ViewDao {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DBConnectionMgr pool;

	public Portfolio_ViewDao(){
		try{
			pool = DBConnectionMgr.getInstance();
		}
		catch(Exception err){
			System.out.println("SearchDao 오류 : " + err);
		}
	}
	
	public void TotalSearch(){
		ArrayList list = new ArrayList();
		String sql = "select MEDIA_LIBRARY.ML_PATH, TAG.TAG_NAME, portfolio.PF_TITLE ,Profile.PROF_NAME, portfolio.PF_LIKE"
				+ "from MEDIA_LIBRARY, TAG, Profile, portfolio"
				+ "where portfolio.PF_TITLE like '% %'";
			//sql수정해야함. -> 카티전프로덕트 값 나옴.
		
		try{
			con = pool.getConnection();		
	 		pstmt = con.prepareStatement(sql);	 
	 		rs = pstmt.executeQuery();
	 		
	 		while(rs.next()){
				Media media = new Media();	//이미지위(path)
				Tag tag = new Tag();	//태그명
				Portfolio portfolio = new Portfolio();	//포폴 제목, 좋아요 수
				Profile profile = new Profile();	//멤버 이름				
				
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
			
		}
		
		catch(Exception err){
			System.out.println("[DAO]: SearchDao()에서 오류");
			err.printStackTrace();
		}
		
		finally{
			pool.freeConnection(con, pstmt);
		}
	}
	
	
	//연습용
	public List getEx(){
		ArrayList list = new ArrayList();
		String sql = "select * from Portfolio";
	
		try{
			con = pool.getConnection();		
	 		pstmt = con.prepareStatement(sql);	 
	 		rs = pstmt.executeQuery();
	 		
	 		while(rs.next()){				
				Portfolio portfolio = new Portfolio();				
			
				portfolio.setPf_id(rs.getInt("pf_id"));		
				portfolio.setPf_title(rs.getString("pf_title"));
				portfolio.setPf_intro(rs.getString("pf_intro"));
				portfolio.setPf_regdate(rs.getDate("pf_regdate"));
				portfolio.setPf_like(rs.getInt("pf_like"));
		
				list.add(portfolio);
			}
	 		
	 					
		}
		
		catch(Exception err){
			System.out.println("getEx에서 오류");
			err.printStackTrace();
		}
		
		finally{
			pool.freeConnection(con, pstmt, rs);
		}
		return list;
	}
}
