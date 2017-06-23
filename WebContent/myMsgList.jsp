<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="assets/css/message.css" rel="stylesheet">
<!-- JSP:useBean -->
<jsp:useBean id="dao" class="portit.model.dao.MassageDao"/>


<script>
/* 	window.onload=function(){
		//Ctrl에 접근해서 list 뽑아오기.
		if(){
		location.href="/Project2/msg?cmd=list";
		}
	}; */
</script>

	<%--sidenavbar start--%>
	<jsp:include page="my.jsp"></jsp:include>
	<%--sidenavbar end--%>

		<section id="main-content">
			<section class="wrapper site-min-height">
				<!--  Message List -->
				<div class="col-md-12 mt msg msgList">
					<div class="msgList_top">
						<span class="pull-left">
							<form class="form-inline" method="post" action="#">
								<div class="form-group">
									<input type="text" class="form-control" id="msgSearch"
										placeholder="Search Member">
									<button class="btn" type="submit">Search</button>
								</div>
							</form>
						</span> 
						<span class="pull-right"> 
							<a href="javascript:location.href='/page?page=myMsgSend'">
								<button class="btn">Send New Message</button>
							</a>
						</span>
					</div>
					
					<!-- Message List Body -->
					<div class="panel-group msgListContent" id="msgBoxList">
						<c:forEach var="msgSender" items="${msgSenderList}" begin="0" varStatus="status">
						<!--  #################반복시작#################### -->

						<div class="panel panel-default msgBox">
							<!-- Head -->
							<div class="panel-heading">
								<span class="pull-left">
									<a href="#">
										<img src="assets/img/you.png" class="img-circle">&nbsp;&nbsp;
										<span class="msgSender">${msgSender}</span>
									</a>
								</span>
								<span class="pull-right"> 
									<a href="/Project2/msg?cmd=send">
										<button type="button" class="btn">메세지 보내기</button>
									</a>
									<a data-toggle="collapse" data-parent="#msgBoxList" href="#m1_${status.count}" class="updown collapsed"> 
										<i class="fa fa-chevron-down"></i>
									</a>
								</span>								
							</div>
							<!-- End_Head -->


							<div id="m1_${status.count}" class="panel-collapse collapse">
								<div class="panel-body">
					
									<!--  Msg_Content -->
									<div class="msgContentBox mb clearfix"> 
										<span class="pull-left col-md-10 msgContent">
												내용내용내용내용내용내용내용^^
										</span>
										<span class="pull-right col-md-2">
											<span style="position:absolute; top: 0; right: 0;"><i class="fa fa-clock-o fa-fw"></i>날짜^^</span>
											<span style="position:absolute; top: 20px; right: 0;"><button type="button" class="btn">삭제</button></span>											
										</span>										
									</div>	
									<!--  END_Msg_Content -->
									
									<div class="pull-right">
										<button type="button" class="btn" onclick="location.href='/Project2/msg?cmd=detail'">더보기</button>
									</div>					
								</div>
							</div> <!-- END - msgBoxOpen -->
						</div> <!-- END - msgBox -->

									
									
						<!-- ##################### 반복 끝 ################## -->
						</c:forEach>
					</div> <!-- END - Message List Body  -->
				</div> 
			</section>
			<!--/wrapper -->
		</section>

	<script>
		$(document).ready(function(){
			$('.updown').on('click',function(){
				var icon = $(this).find('i');
				
		        if (icon.hasClass("fa-chevron-down")){
		        	icon.addClass("fa-chevron-up").removeClass("fa-chevron-down");
		    	} 
		        else {
		        	 icon.addClass("fa-chevron-down").removeClass("fa-chevron-up");
		        }
		    });
		});
		

	    
	</script>
