package portit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.Portfolio;

/**
 * 포트폴리오 구성 화면
 */
public class Portfolio_ViewDao {

   private Connection con;
   private PreparedStatement pstmt;
   private ResultSet rs;
   private DBConnectionMgr pool;
      
   /**
    * DB연결 생성자
    */
   public Portfolio_ViewDao() {
      try {
         pool = DBConnectionMgr.getInstance();
         con = pool.getConnection();
      } catch (Exception err) {
         System.out.println("DB접속 오류 : " + err);
      }
   }

   /**
    * DB 접속 해제
    */
   private void freeConnection() {
      try {
         pool.freeConnection(con, pstmt, rs);
         if (con != null) {
            System.out.println("DB 접속 해제");
         }
      } catch (Exception e) {
         System.out.println("DB 접속해제 오류 - freeConnection()");
         e.printStackTrace();
      }
   }

   /**
    * 포트폴리오 정보를 불러오는 메서드     
    */
   public List portfolio_info(int login_id) {
      ArrayList list = new ArrayList();
      String sql = "select distinct Member.mem_id, PORTFOLIO.PF_ID, MEDIA_LIBRARY.ML_PATH, TAG.TAG_NAME, portfolio.PF_TITLE ,Profile.PROF_NAME, portfolio.PF_LIKE "
            + "FROM media_library, tag, Profile, portfolio, prof_pf, tag_use, member "
            + "where prof_pf.PROF_ID = Profile.PROF_ID  "
            + "and prof_pf.PF_ID = portfolio.PF_ID  "
            + "and TAG_USE.TAG_ID = TAG.TAG_ID "
            + "and TAG_USE.TAG_USE_TYPE_ID= prof_pf.PF_ID "
            + "and MEDIA_LIBRARY.ML_TYPE_ID = portfolio.PF_ID "
            + "and Profile.mem_id=member.mem_id "
            + "and Member.mem_id='"+String.valueOf(login_id)+"'";
      
            System.out.println("DAO=  "+sql);
      
      try {
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            Portfolio portfolio = new Portfolio();
            portfolio.setMem_id(rs.getInt("mem_id"));
            portfolio.setPf_id(rs.getInt("PF_ID"));
            portfolio.setMl_path(rs.getString("ml_path"));
            portfolio.setTag_name(rs.getString("tag_name"));
            portfolio.setPf_title(rs.getString("pf_title"));
            portfolio.setPf_like(rs.getInt("pf_like"));
            portfolio.setProf_name(rs.getString("prof_name"));
            list.add(portfolio);
         }
      }

      catch (Exception err) {
         System.out.println("portfolio_info() 에서 오류");
         err.printStackTrace();
      }

      finally {
         freeConnection();
      }
      return list;
   }   
   
   
   // Delete.jsp
   public void deletePortforio(String Portforio_Id){
      
      
      String sql = "delete from project where proj_id='"+Portforio_Id+"'";
      //cascade 옵션을 삽입해야합니다.
      
      
      try{
         con = pool.getConnection();
         pstmt = con.prepareStatement(sql);
         pstmt.executeUpdate();
      }
      catch(Exception err){
         System.out.println("deleteAccount()에서 오류");
         err.printStackTrace();
      }
      finally{
         pool.freeConnection(con, pstmt);
      }
   }

   

   
   
}