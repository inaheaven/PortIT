package portit.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.MessageDto;

public class MassageDao{
	
/*
 insertMsg()		: ~에게 메세지 보내기.
 getChatRoom()		: ~와의 대화방(송,수신 msg)리턴
 getSenderList()	: User와 생성된 대화상대 List(sender_id 리턴)
 roomList()			: User의 대화방 List
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
	public void insertMsg(MessageDto dto){
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
	
	
	
	
	
	// '특정발신자'와의 대화방.
	public ArrayList getChatRoom(String Type, String Name, String Msg_Sender, Boolean All){
		
		ArrayList list = new ArrayList();
		String sql = null;
		
		try{
			//sql->connection->pstmt-rs
			if(Type == null){
				//쿼리문이 닉네임을 검색할때와 이름을 검색할때로 나뉘어야한다.
				
				//이 쿼리문은 profile이 생성되어있어야 등록이 가능하다. 따라서 profile 개설여부를 묻는 조건문이 필요하다
				//혹은 쿼리문을 수정하여 name과 nick을 아예 빼버린다.
				//그리고 dto에 담는 시점에서 converting을 한다.
				
				//조인하는 부분을 버리고 nick네임 부분만 converting하면 간단해진다.
				
				/*
				 	sql="select MSG_ID, MEM_ID_SENDER ,PROF_NAME, PROF_NICK, MEM_ID_RECEIVER,MSG_CONTENT,MSG_ISREAD, MSG_DATE "
					+"FROM Message INNER JOIN PROFILE "
					+"on Message.mem_id_Sender = PROFILE.mem_id "
					+"WHERE (mem_id_sender ="+Msg_Sender;
				 */
				
				
				
				sql="select * "
					+"FROM Message "
					+"WHERE (mem_id_sender ="+Msg_Sender;
				sql=sql.concat(" and MEM_ID_RECEIVER="+String.valueOf(login_id)+")");
				
				//true일때 송신메세지가 출력된다.
				if(All == true){
				sql=sql.concat(" or (mem_id_sender= "+String.valueOf(login_id));
				sql=sql.concat(" and MEM_ID_RECEIVER="+Msg_Sender+")");
				}
				sql=sql.concat(" order by msg_date desc");
				
				
			}
			
			else{//검색조건  유
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
				//Dto.setSender_Name(convertToName(String.valueOf(Dto.getMem_id_sender())));
				//Dto.setSender_Nick(null);
				
				//System.out.println("dao  "+convertToName(String.valueOf(Dto.getMem_id_sender())));
				list.add(Dto);
				
			}
		}
		catch(Exception err){
			System.out.println("getChatRoom()에서 오류");
			err.printStackTrace();
		}
		finally{
			pool.freeConnection(con, pstmt, rs);
		}
		return list;
	}
	
	
	
	
	//메세지 발신자 List	(확인!)
	//From msgList.jsp
	public ArrayList getSenderList(int mem_id){
		String sql = null;
		String name = null;
		ArrayList list = new ArrayList();
		
		try{
				sql = "Select mem_ID_sender, max(MSG_DATE)" +
						"From (select * from message where MEM_ID_RECEIVER= ";
				sql = sql.concat(String.valueOf(mem_id));
				sql = sql.concat(") group by (mem_ID_sender) ");
				sql = sql.concat("order by max(MSG_DATE) desc");
			
				
				con = pool.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			
				
				
			while(rs.next()){
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
	
	
	
	
	
	
	//roomList : 대화방목록
	public ArrayList roomList(){
		
		//대화방 list 준비.
		ArrayList Roomlist = new ArrayList();
		
		//발신자 목록.
		ArrayList senderList;
		
		
		//로그인한 ID의 발신자목록을 받았습니다.
		senderList = (ArrayList) getSenderList(this.login_id);
		
		
		for(int i=0; i<senderList.size();i++){
			//발신자
			String mem_id_sender= (String)senderList.get(i);
			
			//해당 발신자의 대화방을 Room에 담는다..
			Roomlist.add(getChatRoom(null,null,mem_id_sender,false));
		}
		return Roomlist;
	}
	
	
	
	
	
	
		//MEM_id를 한글이름으로 변환. (확인!)
		//list에서 꺼내온값을 list에 다시 저장하기 때문에 mem_id를 문자열로 저장.
		public String convertToName(String mem_id){
			
			/*
			 1.이름이 등록됐다면 이름으로 반환한다.
			 2.없다면 메일ID를 반환한다.  
			 */
			
			String sql = null;
			String name = null;
			
			try{
					sql="select distinct PROF_NAME "+
						"FROM MEMBER INNER JOIN	PROFILE "+
						"on MEMBER.MEM_ID=profile.MEM_ID "+
						"where MEMBER.MEM_ID=";
					sql = sql.concat(mem_id);	
	
					con = pool.getConnection();
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
				
				while(rs.next()){
					name= rs.getString("PROF_NAME");
				}
				
				
				if(name==null){
					//name등록을 안했다면 Email반환.
					sql="select Mem_email from member "+
						"where mem_id="+mem_id;
					
					
					con = pool.getConnection();
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
				
					while(rs.next()){
						name= rs.getString("Mem_email");
						StringTokenizer token = new StringTokenizer(name, "@");
						
						name=token.nextToken();
						String domein=token.nextToken();
					}
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
	
		

	
	
	
	public String getMemId(String mem_eamil){
			/*
			 1.이름이 등록됐다면 이름으로 반환한다.
			 2.없다면 메일ID를 반환한다.  
			 */
			
			String sql = null;
			String memId = null;
			
			try{
					sql="select mem_id FROM member WHERE Mem_email like '%";
					sql = sql.concat(mem_eamil)+"%' ";	
	
					con = pool.getConnection();
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
				
				while(rs.next()){
					memId= rs.getString("mem_id");
				}
			}
			catch(Exception err){
				System.out.println("getMemId()에서 오류");
				err.printStackTrace();
			}
			finally{
				pool.freeConnection(con, pstmt, rs);
			}
			
			return memId;
	}
	
		
		
		
		
		
		
		
		
		
	//-----------------------------------[펌] 메서드------------------------------
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

