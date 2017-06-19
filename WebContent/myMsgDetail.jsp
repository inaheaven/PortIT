<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="assets/css/message.css" rel="stylesheet">
	<%--sidenavbar start--%>
	<jsp:include page="my.jsp"></jsp:include>
	<%--sidenavbar end--%>

		<section id="main-content">
			<section class="wrapper site-min-height">
				<div class="col-md-12 mt msg msgDetail">
					<div class="panel-group msgDetailContent">
						<div class="panel panel-default msgBox">
							<div class="panel-heading">
								<span class="pull-left">
									<a href="#">
										<img src="assets/img/you.png" class="img-circle">&nbsp;&nbsp;
										<span class="msgSender">발신자 이름</span>
									</a>
								</span>
								<span class="pull-right"> 
									<a href="javascript:location.href='/page?page=myMsgSend'">
										<button type="button" class="btn">메세지 보내기</button>
									</a>&nbsp;&nbsp;&nbsp;
									<a href="javascript:location.href='/page?page=myMsgList'">
										<button type="button" class="btn">목록</button>
									</a>
								</span>								
							</div>
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
										<span style="position:absolute; top: 0; right: 0;"><i class="fa fa-clock-o fa-fw"></i>17.05.12 16:34</span>											<span style="position:absolute; top: 20px; right: 0;"><button type="button" class="btn">삭제</button></span>											
									</span>										
								</div>
								
								<!-- 페이지네이션 -->
								<div class="center"> 
									1 2 3 4 5
								</div>					
							</div>			
								
						</div> <!-- END - msgBoxOpen -->
					</div> <!-- END - msgBox -->		
				</div>
			</section>
			<!--/wrapper -->
		</section>



