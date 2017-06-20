package portit.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import portit.model.db.DBConnectionMgr;
<<<<<<< HEAD
import portit.model.dto.MessageDto;

public class MassageDao{
	
/*
 insertMessage()	: 메세지 보내기.
 getMessageList()	: Mem_ID 에 해당하는 대화목록을 LIST에 저장해서 리턴한다.
 */
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DBConnectionMgr pool;
	private int login_id;				//로그인한 사용자 MEM_ID
	
	
	
	public MassageDao(int _login_id){
		try{
			this.login_id=_login_id;
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
	
	
	// msgSend.jsp (메세지 보내기) 확인!!
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
	
	
	
	
	
	// '특정발신자'로 수신 And 발신 메세지.
	public ArrayList getMessageListAll(String Type, String Name, int Msg_Sender){
		
		/*
		 MemID 에 해당하는 대화목록을 LIST에 저장해서 리턴한다.
			
		 1.ArrayList 준비 (msglist를 담는다)
		 2.list에 조회된 msg를 반복해서 답는다.
		*/
		
		ArrayList list = new ArrayList();
		String sql = null;
		
	
		try{
			//sql->connection->pstmt-rs
			if(Type == null){
				//쿼리문이 닉네임을 검색할때와 이름을 검색할때로 나뉘어야한다.
				//우선 1차적으로 내가 발신or수신인 모든 데이터를 뽑는다.(MsgDetail에 보여진다)
				//다음으로 내가 수신자인것만 뽑는다.(msgList에 보여진다.)
				//사용자 정보를 출력하기 위해서 키값이 필요한데, mem_id로 사용하면된다.
				
				sql="select * from Message"+
					"where (mem_id_sender ="+String.valueOf(login_id);
				sql=sql.concat("and MEM_ID_RECEIVER="+String.valueOf(Msg_Sender));
				sql=sql.concat(")or (mem_id_sender = "+String.valueOf(login_id));
				sql=sql.concat("and MEM_ID_RECEIVER="+String.valueOf(Msg_Sender)+")");

			}
			
			else{
				//검색조건  유
				//sql =null; 
			}
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//DB 인출확인
			while(rs.next()){
				MessageDto Dto = new MessageDto();
				Dto.setMsg_id(rs.getInt("MSG_ID"));
				Dto.setMem_id_sender(rs.getInt("MEM_ID_SENDER"));
				Dto.setMem_id_receiver(rs.getInt("MEM_ID_RECEIVER"));
				Dto.setMsg_isread(rs.getString("MSG_ISREAD"));
				Dto.setMsg_content(rs.getString("MSG_CONTENT"));
				Dto.setMsg_date(rs.getString("MSG_DATE"));
				list.add(Dto);
			}
		}
		catch(Exception err){
			System.out.println("getMessageListAll()에서 오류");
			err.printStackTrace();
		}
		finally{
			pool.freeConnection(con, pstmt, rs);
		}
		return list;
	}
	
	
	
	
	// '특정발신자'로 부터받은 수신메세지.
	public ArrayList getReciveMsg(String Type, String Name, String Msg_Sender){
		
		ArrayList list = new ArrayList();
		String sql = null;
		
		
		try{
			//sql->connection->pstmt-rs
			if(Type == null){
				sql="select * from Message "+
						"where (mem_id_sender ="+Msg_Sender;
				sql=sql.concat(" and MEM_ID_RECEIVER="+String.valueOf(login_id)+")");
			}
			
			else{
				sql = "select * from Message where " 
						+ Type + " like '%" + Name + "%' ";
			}
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			//DB 인출확인
			while(rs.next()){
				//Dto는 한개의 메세지
				//list는 메세지묶음,즉 대화.
				
				MessageDto Dto = new MessageDto();
				Dto.setMsg_id(rs.getInt("MSG_ID"));
				Dto.setMem_id_sender(rs.getInt("MEM_ID_SENDER"));
				Dto.setMem_id_receiver(rs.getInt("MEM_ID_RECEIVER"));
				Dto.setMsg_isread(rs.getString("MSG_ISREAD"));
				Dto.setMsg_content(rs.getString("MSG_CONTENT"));
				Dto.setMsg_date(rs.getString("MSG_DATE"));
				list.add(Dto);
			}
		}
		catch(Exception err){
			System.out.println("getReciveMsg()에서 오류");
			err.printStackTrace();
		}
		finally{
			pool.freeConnection(con, pstmt, rs);
		}
		return list;
	}
	
	
	
		//MEM_id를 한글이름으로 변환. (확인!)
		//list에서 꺼내온값을 list에 다시 저장하기 때문에 mem_id를 문자열로 저장.
		//불필요한 casting과정을 줄인다.
		public String convertToName(String mem_id){
			String sql = null;
			String name = null;
			
			try{
					sql="select distinct PROF_NAME "+
						"FROM MEMBER INNER JOIN	PROFILE "+
						"on MEMBER.MEM_ID=profile.MEM_ID "+
						"where MEMBER.MEM_ID=";
				
				/*	sql = "select distinct PROF_NAME "+
							"FROM MESSAGE INNER JOIN PROFILE "+
							"on message.MEM_ID_SENDER=profile.mem_ID "+
							"where message.MEM_ID_SENDER=";*/
					sql = sql.concat(mem_id);	
	
					con = pool.getConnection();
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
				
				while(rs.next()){
					name= rs.getString("PROF_NAME");
				}
			}
			catch(Exception err){
				System.out.println("convertToName()에서 오류");
				err.printStackTrace();
			}
			finally{
				pool.freeConnection(con, pstmt, rs);
			}
			
			return name;
	}
	
		
	
		//메세지 발신자 List	(확인!)
		//msgList.jsp에서 호출되어야한다.
		public ArrayList getMsgSender(int mem_id){
			String sql = null;
			String name = null;
			ArrayList list = new ArrayList();
			
			try{
				
					sql = "Select mem_ID_sender, max(MSG_DATE)" +
							"From (select * from message where MEM_ID_RECEIVER= ";
					sql = sql.concat(String.valueOf(mem_id));
					sql = sql.concat(") group by (mem_ID_sender) ");
					sql = sql.concat("order by max(msg_date) desc");	
				
					
					/*
					 * sql = "select distinct MEM_ID_SENDER "+
							"FROM MESSAGE "+
							"where MEM_ID_RECEIVER=";
					sql = sql.concat(String.valueOf(mem_id));
					*/
					
					con = pool.getConnection();
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
				
				while(rs.next()){
					//정수형을 저장.
					list.add(rs.getString("MEM_ID_SENDER"));
				}
			}
			catch(Exception err){
				System.out.println("getMsgSender()에서 오류");
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
=======
import portit.model.dto.Message;

public class MassageDao{
	
/*
 insertMessage()	메세지 작성
 getMessageList()	메세지 리스트 조회 
 */
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DBConnectionMgr pool;

	
	
	public MassageDao(){
		try{
			pool = DBConnectionMgr.getInstance();
		}
		catch(Exception err){
			System.out.println("DBCP 인스턴스 참조 실패 : " + err);
		}
	}
	
	
	// msgSend.jsp (메세지 보내기) 성공!
	public void insertMessage(Message dto){
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
	public void replyUpdatePos(Message message){
>>>>>>> refs/remotes/origin/Insu2
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

