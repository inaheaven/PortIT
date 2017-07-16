package portit.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.jsp.tagext.Tag;

import portit.model.db.DBConnectionMgr;
import portit.model.dto.MessageDto;
import portit.model.dto.Profile;
import portit.model.dto.Project;

public class Pj_Dao{
//0705
	
	
			
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
	
	
	public Pj_Dao( ){
		try{
			pool = DBConnectionMgr.getInstance();
		}
		catch(Exception err){
			System.out.println("DBCP 인스턴스 참조 실패 : " + err);
		}
	}
	
	
	
	
	//싱글톤 실험.
	private static Pj_Dao instance = new Pj_Dao();
	
	public static Pj_Dao getInstance() {
		if(instance==null){
			instance=new Pj_Dao();
		}
		return instance;
	}
	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}
	
	
	
	
	

	//1.프로젝트정보 
	//dto: Project
	public Project getPj_inform(int proj_id) {
		
		String sql ="select * from project  where proj_id="+proj_id;
		
		//dto 선언
		Project dto = new Project();
		try{
				con = pool.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				   
				/*
				 * PROJ_ID	PROJ_TITLE	PROJ_INTRO	PROJ_TO	PROJ_REGDATE
				PROJ_STARTDATE	PROJ_PERIOD 	PROJ_REGENDDATE
				 */
				while(rs.next()){
					dto.setProj_id(rs.getInt("PROJ_ID"));
					dto.setProj_title(rs.getString("PROJ_TITLE"));
					dto.setProj_intro(rs.getString("PROJ_INTRO"));
					dto.setProj_to(rs.getInt("PROJ_TO"));
					dto.setProj_regdate(rs.getDate("PROJ_REGDATE"));
					dto.setProj_startdate(rs.getDate("PROJ_STARTDATE"));
					dto.setProj_period(rs.getInt("PROJ_PERIOD"));
					dto.setProj_regenddate(rs.getDate("PROJ_REGENDDATE"));
				}
		}
		catch(Exception err){
			System.out.println("getPj_inform()에서 오류"+err);
		}
		finally{
			pool.freeConnection(con, pstmt, rs);
		}
		return dto;
	}
	
	
	
	
	
	
	//2.미디어정보
	// dto: Project
	public ArrayList getPj_media(int proj_id) {
	
		ArrayList result= new ArrayList();
		String sql ="select * from media_library   where ML_TYPE='proj'and  ML_TYPE_ID="+proj_id;
		
		//dto 선언
		Project dto = new Project();
		try{
				con = pool.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				   
				/*
				 	ML_ID
					ML_TYPE
					ML_TYPE_ID
					ML_PATH
					*/
				while(rs.next()){
					result.add(rs.getString("ML_PATH"));
				}
		}
		catch(Exception err){
			System.out.println("getPj_media()에서 오류"+err);
		}
		finally{
			pool.freeConnection(con, pstmt, rs);
		}
		return result;
	}
	
	
	//select * from profile where mem_id=(select mem_id from mem_proj where proj_id=2);
	
	
	
	

	//3.pj 개설자 정보
	//dto: Profile
	public Profile getWhoMade(int proj_id) {
	
		ArrayList result= new ArrayList();
		String sql ="select * from profile where mem_id=(select mem_id from mem_proj where proj_id="+proj_id+")";
		
		//dto 선언
		Profile dto = new Profile();
		try{
				con = pool.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				   
				
				while(rs.next()){
					dto.setProf_id(rs.getInt("PROF_ID"));
					dto.setMem_id(rs.getInt("MEM_ID"));
					dto.setProf_nick(rs.getString("PROF_NICK"));
					dto.setProf_name(rs.getString("PROF_NAME"));
					dto.setProf_intro(rs.getString("PROF_INTRO"));
					dto.setProf_img(rs.getString("PROF_IMG"));
					dto.setProf_background(rs.getString("PROF_BACKGROUND"));
					dto.setProf_website(rs.getString("PROF_WEBSITE"));
					dto.setProf_github(rs.getString("PROF_GITHUB"));
					dto.setProf_facebook(rs.getString("PROF_FACEBOOK"));
					dto.setProf_regdate(rs.getDate("PROF_REGDATE"));
					dto.setProf_follower(rs.getInt("PROF_FOLLOWER"));
				}
		}
		catch(Exception err){
			System.out.println("getWhoMade()에서 오류"+err);
		}
		finally{
			pool.freeConnection(con, pstmt, rs);
		}
		return dto;
	}
	
	
	

	
	//4.어떤 기술을 요구하는가?
	//요구되는 기수들을 반환한다. (language,tool, field)
	public ArrayList getWanted(int proj_id) {
		
		ArrayList result= new ArrayList();
			String sql ="select * from tag "+
				" where tag_id in (select tag_id from tag_use where TAG_USE_TYPE_ID="+proj_id
						+ " and tag_use_type='proj')";
			
		//dto 선언
		try{
				con = pool.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				   
				/*TAG_ID
				TAG_TYPE
				TAG_NAME */
				while(rs.next()){
					portit.model.dto.Tag dto = new  portit.model.dto.Tag();
					dto.setTag_id(rs.getInt("TAG_ID"));
					dto.setTag_type(rs.getString("TAG_TYPE"));
					dto.setTag_name(rs.getString("TAG_NAME"));
					result.add(dto);
				}
		}
		catch(Exception err){
			System.out.println("getWanted()에서 오류"+err);
		}
		finally{
			pool.freeConnection(con, pstmt, rs);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	//5.누가 지원했나
	//10,13
	public ArrayList getWhoApply(int proj_id) {
		
		ArrayList result= new ArrayList();
		String sql ="select mem_id from proj_app where proj_id="+proj_id;
		
		//dto 선언
		try{
				con = pool.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				   
				while(rs.next()){
					result.add(rs.getInt("mem_id"));
				}
		}
		catch(Exception err){
			System.out.println("getWhoApply()에서 오류"+err);
		}
		finally{
			pool.freeConnection(con, pstmt, rs);
		}
		return result;
	}
	
	
	
	
	
	//6.프로젝트 참여자의 태그정보.
	public ArrayList getCoworker_inform(int mem_id) {
		
		ArrayList result= new ArrayList();
		String sql ="select * from tag where tag_id in"
				+"(select tag_id from tag_use where tag_use_type='prof' and tag_use_type_id="+mem_id+")";
		
		try{
				con = pool.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				   
				while(rs.next()){
					portit.model.dto.Tag dto = new  portit.model.dto.Tag();
					dto.setTag_id(rs.getInt("TAG_ID"));
					dto.setTag_type(rs.getString("TAG_TYPE"));
					dto.setTag_name(rs.getString("TAG_NAME"));
					result.add(dto);
				}
		}
		catch(Exception err){
			System.out.println("getCoworker_inform()에서 오류"+err);
		}
		finally{
			pool.freeConnection(con, pstmt, rs);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	

	/*
	

	// msgSend.jsp (메세지 보내기) 확인!!
	public void insertMsg(MessageDto dto){
		String sql = "insert into Message"
			+"(MSG_ID, MEM_ID_SENDER, MEM_ID_RECEIVER, MSG_DATE, MSG_CONTENT, MSG_ISREAD)"
			+ "values(seq_msg_id.nextVal,?,?,sysdate,?,?)";
	
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
			if(keyWord == null||"".equals(keyWord.trim())){
				
				System.out.println("검색어가없습니다="+keyWord);
				
				
				sql="select * "
					+"FROM Message "
					+"WHERE (mem_id_sender ="+Msg_Sender
					+" and MEM_ID_RECEIVER="+String.valueOf(login_id)+")";
				
				
				//true일때 송신메세지가 출력된다.
				if(All == true){
				sql=sql.concat(" or (mem_id_sender= "+String.valueOf(login_id));
				sql=sql.concat(" and MEM_ID_RECEIVER="+Msg_Sender+")");
				}
				sql=sql.concat(" order by msg_date desc");
				
				
				
			}
			
			else{//검색조건  유
				
				System.out.println("[DAO]앗 야생 검색어를 발견했다!="+keyWord);
				
				//내용검색.
				if(keyField.equals("search_content")){
					sql="select * from(select * FROM Message WHERE (mem_id_sender ="
						+ Msg_Sender+" and MEM_ID_RECEIVER="+login_id+")) "+
						"where MSG_Content like '%"+keyWord+"%' "+
						"order by msg_date desc ";
				}
				
				//사람검색
				else{
					sql="select * from(select * FROM Message WHERE (mem_id_sender ="
							+ Msg_Sender+" and MEM_ID_RECEIVER="+login_id+")) "+
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
			//	System.out.println("dao체크");
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
			if (null==keyWord||"".equals(keyWord.trim())) {

				System.out.println("[DAO] 검색조건이 없습니다!"+keyWord);
				
				sql = "Select mem_ID_sender, max(MSG_DATE)" + "From (select * from message where MEM_ID_RECEIVER= "+mem_id
						+") group by (mem_ID_sender) "
						+"order by max(MSG_DATE) desc";

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
				if ("search_name".equals(keyField)) {
					
					System.out.println("[DAO] 검색조건이 있습니다!"+keyWord);
					
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
			else if ("search_content".equals(keyField)) {
				System.out.println("[DAO] 검색조건이 있습니다!"+keyWord);
				
						
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
			
			
			 1.이름이 등록됐다면 이름으로 반환한다.
			 2.없다면 메일ID를 반환한다.  
			 
			
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
		
		 * mem_id to Email
		 
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
	
	
	
	public MessageDto getPartnerInform(String mem_id_sender) {
		
		String sql ="select * from profile   where mem_id='"+mem_id_sender+"'";
		
		MessageDto msg = new MessageDto();
		
		try{
				con = pool.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				   
				
				 prof_id
					mem_id
					prof_nick
					prof_name
					prof_img
					
				while(rs.next()){
					
					msg.setPartner_id(rs.getInt("mem_id"));
					msg.setProf_id(rs.getInt("prof_id"));
					msg.setSender_Name(rs.getString("prof_name"));
					msg.setSender_Nick(rs.getString("prof_nick"));
					msg.setProf_img(rs.getString("prof_img"));
					
				}
		}
		catch(Exception err){
			System.out.println("getPartnerInform()에서 오류"+err);
		}
		finally{
			pool.freeConnection(con, pstmt, rs);
		}
		
		
		
		return msg;
	}*/
}
	
