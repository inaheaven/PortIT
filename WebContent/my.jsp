<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
      
<!-- **********************************************************************************************************************************************************
 			My Page Sidebar Navigation
 *********************************************************************************************************************************************************** -->
<!--sidebar start-->
<aside>
	<div id="sidebar" >
		<!-- sidebar menu start-->
		<ul class="sidebar-menu">       
			<div class="myImg">
				<p class="centered"><a href="profile.html"><img src="assets/img/ui-sam.jpg" class="img-circle" width="60"></a></p>
				<h5 class="centered">김수연 님</h5>              	  
			</div>       
			<hr class="line" />
			<li class="">
				<a href="javascript:location.href='myTemplate.jsp?page=myProf'" id="myProf">
				    <span>My Profile</span>
				</a>
			</li>
			<li class="">
				<a href="javascript:location.href='myTemplate.jsp?page=myPfList'" id="myPfList">
				    <span>My Portfolio</span>
				</a>
			</li>
			<li class="">
				<a href="javascript:;" id="myProjList">
				    <span>My Project</span>
				</a>
			</li>
			<li class="">
				<a href="javascript:;" id="myBookmark">
				    <span>Bookmarks</span>
				</a>
			</li>                  
			<li class="">
				<a href="javascript:;" id="myFollowing">
				    <span>Following</span>
				</a>
			</li>                  
			<li class="">
				<a href="javascript:;"  id="myNotification">
				    <span>Notification</span>
				</a>
			</li>
			<li class="">
				<a href="javascript:;" id="myMessage">
				    <span>Message</span>
				</a>
			</li>
			<hr class="line"/>
			<li class="">
				<a href="javascript:;" id="myAccount">
				    <span>My Account</span>
				</a>
			</li>
			<li class="">
				<a href="javascript:;" id="myDeleteAccount">
				    <span>Delete Account</span>
				</a>
			</li>
		</ul>
		<!-- sidebar menu end-->
	</div>
</aside>
<!--sidebar end-->
