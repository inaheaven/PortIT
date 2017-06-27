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

	
	//private String Name= convertToName(String.valueOf(login_id));
	
	
	public MassageDao( ){
		try{
			pool = DBConnectionMgr.getInstance();
		}
		catch(Exception err){
			System.out.println("DBCP 인스턴스 참조 실패 : " + err);
		}
	}
	
	
	
	
	
	//싱글톤 실험.
	private static MassageDao instance = new MassageDao();
	
	public static MassageDao getInstance() {
		if(instance==null){
			instance=new MassageDao();
		}
		return instance;
	}
	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}
	
	
	
	
	
	
	
	//roomList : 대화방목록
	public ArrayList roomList(String keyField, String keyWord){
		
		//대화방 list 준비.
		ArrayList Roomlist = new ArrayList();
		
		//발신자 목록.
		ArrayList senderList;
		
		
		//로그인한 계정의  발신자목록 (검색조건 추가가능)
		//필터링 : 발신자 목록에서 한다.
		senderList = (ArrayList) getSenderList(this.login_id,keyField, keyWord);
	
		
		
		for(int i=0; i<senderList.size();i++){
			String mem_id_sender= (String)senderList.get(i);
			
			
			//해당 발신자의 대화방을 Roomlist에 담는다..
			Roomlist.add(getChatRoom(keyField,keyWord,mem_id_sender,false));
		}
		return Roomlist;
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
	public ArrayList getChatRoom(String keyField, String keyWord, String Msg_Sender, Boolean All){
		
		ArrayList list = new ArrayList();
		String sql = null;
		
		try{
			//sql->connection->pstmt-rs
			if(keyWord == null){
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
				
				if(keyField.equals("search_content")){
					sql="select * from(select * FROM Message WHERE (mem_id_sender ="
						+ Msg_Sender+" and MEM_ID_RECEIVER="+login_id+")) "+
						"where MSG_Content like '%"+keyWord+"%' "+
						"order by msg_date desc ";
					
				}
				
			}
			
			
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			
			//메세지 삽입.
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
	public ArrayList getSenderList(int mem_id,String keyField, String keyWord){
		String sql = null;
		String name = null;
		ArrayList list = new ArrayList();
		
		try {

			// 검색조건이 없다면...
			if (keyWord == null) {

				sql = "Select mem_ID_sender, max(MSG_DATE)" + "From (select * from message where MEM_ID_RECEIVER= ";
				sql = sql.concat(String.valueOf(mem_id));
				sql = sql.concat(") group by (mem_ID_sender) ");
				sql = sql.concat("order by max(MSG_DATE) desc");

				con = pool.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					list.add(rs.getString("MEM_ID_SENDER"));
				}

			}
			// 검색조건이 있다면.
			else {
				
				//1.이름 검색.
				if (keyField.equals("search_name")) {
					// 1차적으로 이름을 조회한다. 
					//resultset의 길이가 null인경우 mail을 키워드로 다시 조회한다.

					sql = "SELECT distinct MEM_ID " + " FROM MESSAGE INNER JOIN PROFILE "
							+ " ON MESSAGE.MEM_ID_SENDER = PROFILE.MEM_ID " + " WHERE PROF_NAME like'%";
					sql = sql.concat(keyWord + "%'");

					con = pool.getConnection();
					pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					rs = pstmt.executeQuery();

					rs.last(); // 총 레코드 수구하기
					int totalRecord = rs.getRow();
					rs.beforeFirst(); // 커서를 다시 원상태로 복귀해서 자료를 읽을 준비를 해줌

					// 메일을 조회해라.
					if (totalRecord == 0) {
						System.out.println("DAO메일  " + sql);
						sql = "select MEM_ID from member where MEM_EMAIL like '%" + keyWord + "%' ";

						con = pool.getConnection();
						pstmt = con.prepareStatement(sql);
						rs = pstmt.executeQuery();
					}

					while (rs.next()) {
						list.add(rs.getString("MEM_ID"));
					}
				} 
			
			//2.내용 검색.
			else if (keyField.equals("search_content")) {
						
					sql = "select distinct MEM_ID_SENDER from MESSAGE where msg_content like'%"+keyWord+"%'";

					con = pool.getConnection();
					pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						list.add(rs.getString("MEM_ID_SENDER"));
					}
				}
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
	
	
	
	
	
	
	
		//MEM_id를 한글이름으로 변환. (확인!)
		//list에서 꺼내온값을 list에 다시 저장하기 때문에 mem_id를 문자열로 저장.
		public  String convertToName(String mem_id){
			
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
	

	
	
	//MsgSend에서 보낼때...
	public String emailToMemId(String mem_eamil){
			
			String sql = null;
			String memId="0";
			
			try{
				System.out.println(mem_eamil.trim());
				//null이 들어가면 조건문이 죽게된다... 모든값조회.
				if(!mem_eamil.equals("")){
						sql="select mem_id FROM member WHERE Mem_email like '%"+mem_eamil.trim()+"%'" ;
				
						con = pool.getConnection();
						pstmt = con.prepareStatement(sql);
						rs = pstmt.executeQuery();
					
					while(rs.next()){
						memId= rs.getString("mem_id");
					}
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
	
		
		
	public String toEamil(String mem_id) {
		/*
		 * mem_id to Email
		 */
		String sql = null;
		String e_mail=null;

		try {
			sql = "select Mem_email FROM member WHERE mem_id="+ mem_id;

			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				e_mail = rs.getString("Mem_email");
			}
		} catch (Exception err) {
			System.out.println("toEamil()에서 오류");
			err.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return e_mail;
	}
	
	
		
	
	// Delete.jsp
	public void deleteMsg(String msg_id){
		
		String sql = "delete from Message where msg_id=? ";
		
		
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(msg_id));
			pstmt.executeUpdate();
		}
		catch(Exception err){
			System.out.println("deleteMsg()에서 오류");
			err.printStackTrace();
		}
		finally{
			pool.freeConnection(con, pstmt);
		}
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

