<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
      
<!-- **********************************************************************************************************************************************************
 			My Page Sidebar Navigation
 *********************************************************************************************************************************************************** -->
<!--sidebar start-->
<aside>


<%		
			//String pageName = (String)session.getAttribute("pageName");
			String pageName = (String)request.getAttribute("pageName");			
%>
	<div id="sidebar" >
		<!-- sidebar menu start-->
		<ul class="sidebar-menu">       
			<div class="myImg">
				<p class="centered"><a href="profile.html"><img src="assets/img/ui-sam.jpg" class="img-circle" width="60"></a></p>
				<h5 class="centered">김수연 님</h5>              	  
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
				<a href="/myproj?cmd=list&mem_id=2" id="myProjList"> <!-- 로그인 아이디 값을 매개 변수로 줘야함 -->
				    <span>My Project</span>
				</a>
			</li>
			<li class="">
				<a href="/serial?cmd=MYBOOKMARK" id="myBookmark">
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

