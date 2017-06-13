package portit.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.MessageDto;

public class MassageDao{
	
/*
 insertMessage()	메세지 작성
 getMessageList()	메세지 리스트 조회 
 */
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	private int longin_id;				//로그인한 사용자 MEM_ID
	
	
	
	public MassageDao(int _longin_id){
		try{
			this.longin_id=_longin_id;
			pool = DBConnectionMgr.getInstance();
		}
		catch(Exception err){
			System.out.println("DBCP 인스턴스 참조 실패 : " + err);
		}
	}
	
	
	public MassageDao(){
		try{
			pool = DBConnectionMgr.getInstance();
		}
		catch(Exception err){
			System.out.println("DBCP 인스턴스 참조 실패 : " + err);
		}
	}
	
	
	// msgSend.jsp (메세지 보내기) 성공!
	public void insertMessage(MessageDto dto){
		String sql = "insert into Message"
			+"(MSG_ID, MEM_ID_SENDER, MEM_ID_RECEIVER, MSG_DATE, MSG_CONTENT, MSG_ISREAD)"
			+ "values(seq_message_msgid.nextVal,?,?,sysdate,?,?)";
	
		try{
			con = pool.getConnection();
			//updatePos(con);
	 		pstmt = con.prepareStatement(sql);
	 		pstmt.setInt(1, dto.getMem_id_sender());
			pstmt.setInt(2, dto.getMem_id_receiver());
			pstmt.setString(3, dto.getMsg_content());
			pstmt.setString(4, dto.getMsg_isread());
			pstmt.executeUpdate();
		}
		
		catch(Exception err){
			System.out.println("[DAO]: insertMessage()에서 오류");
			err.printStackTrace();
		}
		
		finally{
			pool.freeConnection(con, pstmt);
		}
	}
	
	
	
	
	
	// 목록 불러오기.
	public List getMessageList(String MEM_ID_SENDER, String MEM_EMAIL){
		
		/* 
		 1.ArrayList 선언
		 2.list에 Select에 조회된 데이터 반복을 저장
		 3.
		 * */
		
		
		//ID로 도메인을 찾아오는 쿼리가 필요해진다.
		
		
		ArrayList list = new ArrayList();
		String sql = null;
		
		if(MEM_ID_SENDER == null){
			sql = "select * from Message where MEM_ID_SENDER=? or MEM_ID_RECEIVER=?;";
		}
		else{
			sql = "select * from Message where " 
				+ MEM_ID_SENDER + " like '%" + MEM_EMAIL 
				+ "%' order by b_pos";
		}
		
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, longin_id);
			pstmt.setInt(2, longin_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MessageDto message = new MessageDto();
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
	
	
	
	
	
	
	
	
	
	private void updatePos(Connection con){
		try{
			String sql = "update tblBoard set b_pos=b_pos+1";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		}
		catch(Exception err){
			System.out.println("updatePos()에서 오류");
			err.printStackTrace();
		}
	}
	
	
	
	

	
	
	
	
	
	
	// Delete.jsp
	public void deleteMessage(int b_num){
		String sql = "delete from MESSAGE where b_num=?";
		
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			pstmt.executeUpdate();
		}
		catch(Exception err){
			System.out.println("deleteMessage()에서 오류");
			err.printStackTrace();
		}
		finally{
			pool.freeConnection(con, pstmt);
		}
	}
	
	
	
	
	
	
	// 답변글을 입력할 때 부모보다 큰 pos는 1씩 증가시킨다.
	public void replyUpdatePos(MessageDto message){
		try{
			String sql = "update tblBoard set b_pos = b_pos+1 where b_pos > ?";
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			//pstmt.setInt(1, board.getB_pos());
			pstmt.executeUpdate();
		}
		catch(Exception err){
			System.out.println("replyUpdatePos()에서 오류");
			err.printStackTrace();
		}
		finally{
			pool.freeConnection(con, pstmt);
		}
	}
	
	
	
	
	public String useDepth(int depth){
		String result ="";
		for(int i=0; i<depth*3; i++){
			result += "&nbsp;";
		}
		return result;
	}
	
	
}

