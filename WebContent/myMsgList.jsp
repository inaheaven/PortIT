<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="assets/css/message.css" rel="stylesheet">
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
							<a href="javascript:location.href='/mypage?page=myMsgSend'">
								<button class="btn">Send New Message</button>
							</a>
						</span>
					</div>
					
					<!-- Message List Body -->
					<div class="panel-group msgListContent" id="msgBoxList">
						<!--  반복  -->
						<div class="panel panel-default msgBox">
							<div class="panel-heading">
								<span class="pull-left">
									<a href="#">
										<img src="assets/img/you.png" class="img-circle">&nbsp;&nbsp;
										<span class="msgSender">발신자 이름</span>
									</a>
								</span>
								<span class="pull-right"> 
									<a href="javascript:location.href='/mypage?page=myMsgSend'">
										<button type="button" class="btn">메세지 보내기</button>
									</a>
									<a data-toggle="collapse" data-parent="#msgBoxList" href="#msgBoxOpen" class="updown collapsed"> 
										<i class="fa fa-chevron-down"></i>
									</a>
								</span>								
							</div>

							<div id="msgBoxOpen" class="panel-collapse collapse">
								<div class="panel-body">
									<div class="msgContentBox mb clearfix"> <!-- 반복 -->
										<span class="pull-left col-md-10 msgContent">
											메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.
											메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.
											메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.
											메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.
										</span>
										<span class="pull-right col-md-2">
											<span style="position:absolute; top: 0; right: 0;"><i class="fa fa-clock-o fa-fw"></i>17.05.12 16:34</span>
											<span style="position:absolute; top: 20px; right: 0;"><button type="button" class="btn">삭제</button></span>											
										</span>										
									</div>	
									<div class="msgContentBox mb clearfix"> <!-- 반복 -->
										<span class="pull-left col-md-10 msgContent">
											메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.
											메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.
											메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.
											메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.메세지 내용입니다.
										</span>
										<span class="pull-right col-md-2">
											<span style="position:absolute; top: 0; right: 0;"><i class="fa fa-clock-o fa-fw"></i>17.05.12 16:34</span>
											<span style="position:absolute; top: 20px; right: 0;"><button type="button" class="btn">삭제</button></span>											
										</span>										
									</div>
									<div class="pull-right">
										<button type="button" class="btn" onclick="location.href='/mypage?page=myMsgDetail'">더보기</button>
									</div>					
								</div>
							</div> <!-- END - msgBoxOpen -->
						</div> <!-- END - msgBox -->
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
