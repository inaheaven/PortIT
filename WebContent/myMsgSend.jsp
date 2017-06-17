<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="assets/css/message.css" rel="stylesheet">
		<section id="main-content">
			<section class="wrapper site-min-height">
				<div class="col-md-12 mt msg msgSend">
					<div class="msgSendContent">
						<form method="post" action="#" class="msgForm clearfix">
							<div class="form-group col-md-8 center" >
								<label class="control-label" for="msgReceiver">받는 사람 </label><br>
								<div class="">
									<input class="form-control" type="text" id="msgReceiver" name="msgReceiver" value=""/>
								</div>
							</div><br>
							<div class="form-group col-md-8 center">
								<label class="control-label" for="msgText">내용 </label><br>
								<div>
									<input class="form-control" type="text" id="msgText" name="msgText" value=""/>
								</div>
							</div>
							<div class="msgBtn col-md-3 center">
								<button type="submit" class="btn">보내기</button>&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn" onclick="location.href='/mypage?page=myMsgList'">돌아가기</button>
							</div>
						</form>
					</div>
				</div>
			</section>
			<!--/wrapper -->
		</section>


