<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="portit.model.dto.Profile"%>
<%@page import="portit.model.dao.MemberDao"%>
      
<!-- **********************************************************************************************************************************************************
 			My Page Sidebar Navigation
 *********************************************************************************************************************************************************** -->
<!--sidebar start-->
<aside>
<%		
			/*
			My페이지로 이동할때 거치는 Frontcontroller가 있어야한다.
			그래서 my의 하위 카테고리인 profile, project 등의 하위 CTRL로 이동할때
			"이름, 이미지URL, member_ID "등의 user정보가 담긴 dto를 request에 담아서 
			View에 도착해야한다...
			
			현재같은 구조에서는 View에 도착한뒤에 다시 DB에 접근해서 USER 정보를 얻어와야하는 구조다...
			그렇지 않으면 my의 모든 하위카테고리의 컨트롤러에 user정보를 rpuest에 담는 문법이 중복해서 선언해야한다.
			
			현재는 임시방편으로 MemberDao에 쿼리를 만들어서 직접 인스턴스를 생성해서 사용한다.
			*/
			
			int mem_id = (int)session.getAttribute("loginId");
			
			MemberDao dao = new MemberDao();
			
			Profile mem_prof= (Profile)dao.getUserInformation(mem_id);
			
			String userName= "(이름없음)";
			
			//이름 미등록일시 메일출력.
			if(mem_prof.getProf_name()==null){
				userName=mem_prof.getProf_email();
			}else{
				userName=mem_prof.getProf_name();
			}

			//String pageName = (String)session.getAttribute("pageName");
			String pageName = (String)request.getAttribute("pageName");			
%>
	<div id="sidebar" >
		<!-- sidebar menu start-->
		<ul class="sidebar-menu">       
			<div class="myImg">
				<p class="centered"><a onclick="window.open('/view?type=profile&id=prof_nick<%=mem_prof.getProf_id_list()%>');"><img src="<%=mem_prof.getProf_img() %>" class="img-circle" width="60"></a></p>
				<h5 class="centered"><%=userName %> 님</h5>              	  
			</div>       
			<hr class="line" />
			<li class="">
				<a href="/page?page=myProf" id="myProf">
				    <span>My Profile</span>
				</a>
			</li>
			<li class="">
				<a href="/page?page=myPfList" id="myPfList">
				    <span>My Portfolio</span>
				</a>
			</li>
			<li class="">
				<a href="/myproj?cmd=list" id="myProjList"> 
				<!-- 로그인 아이디 값을 매개 변수로 줘야함 -->
				    <span>My Project</span>	
				</a>
			</li>
			<li class="">
				<a href="/bmk?cmd=MYBOOKMARK" id="myBookmark">
				    <span>Bookmarks</span>
				</a>
			</li>                  
			<li class="">
				<a href="/page?page=myFollowing" id="myFollowing">
				    <span>Following</span>
				</a>
			</li>                  
			<li class="">
				<a href="/page?page=myNotification&sort=all"  id="myNotification">
				    <span>Notification</span>
				</a>
			</li>
			<li class="">
				<a href="/msg?cmd=list" id="myMsgList">
				    <span>Message</span>
				</a>
			</li>
			<hr class="line"/>
			<li class="">
				<a href="/account?cmd=alter" id="myAccount">
				    <span>My Account</span>
				</a>
			</li>
			<li class="">
				<a href="/account?cmd=delete" id="myDeleteAccount">
				    <span>Delete Account</span>
				</a>
			</li>
		</ul>
		<!-- sidebar menu end-->
	</div>
</aside>
<!--sidebar end-->
<script src="assets/js/jquery-3.2.1.min.js"></script>
	<script>
		// sidenavbar 클릭했을 때 .active 클래스 추가
		$(document).ready(function(){
			var li = $("ul > li");    //  ul > li 이를 sBtn으로 칭한다. (클릭이벤트는 li에 적용 된다.)
			
			li.find("a").click(function(){   // sBtn에 속해 있는  a 찾아 클릭 하면.
				li.removeClass("active");     // sBtn 속에 (active) 클래스를 삭제 한다.
				$(this).parent().addClass("active"); // 클릭한 a에 (active)클래스를 넣는다.
			});
		});
	</script>

