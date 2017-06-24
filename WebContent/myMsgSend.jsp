<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="assets/css/message.css" rel="stylesheet">


<script>
	var availableTags = [
							'가나',
							'가나쵸콜렛',
							'갈갈이 삼형제',
							'북마크',
							'북까페',
							'엄마',
							'아빠',
							'북소리',
							'여러분',
							'소문',
							'소문난 식당',
							'나나나',
							'쇼',
							'쇼팽',
							'모나미',
							'한강',
							'강강수월래',
							'강촌',
							'제주도',
							'삼총사',
							'먹보',
							'먹소리',
							'수박',
							'수박 겉핥기'
						];
	</script>

	<script>
	$(document).ready(function() {
	    $("#msgText").autocomplete(availableTags,{ 
	        matchContains: true,
	        selectFirst: false
	    });
	});
	</script>






<script>


/*  	
 $(document).ready(function() {
	$("#btn-send").click(function() {
		//alert("aa");
		
		var msg_content =$("#msg_content");
		var mem_id_sender =$("#mem_id_sender");
		//이 변수를 어떻게 request에 담아야하나요 ㅠㅠ?
		
		location.href="/Project2/msg";
	});
});  
*/


/*	일단 form방식으로 사용해보자.
 	dto의 변수명과 id값이 동일해야한다.
 */

</script>
	<%--sidenavbar start--%>
	<jsp:include page="my.jsp"></jsp:include>
	<%--sidenavbar end--%>

<%
//로그인 : 메일
//Session에 저장되는것 : mem_ID (DB에서 조회해야함.)
session.setAttribute("longin_id","2");
%>
		<section id="main-content">
			<section class="wrapper site-min-height">
				<div class="col-md-12 mt msg msgSend">
					<div class="msgSendContent">
						<form method="post" action="/empty/msg?cmd=list_send" class="msgForm clearfix">
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
								<button type="button" class="btn" onclick="location.href='/empty/msg?cmd=list'">돌아가기</button>
							</div>
						</form>
					</div>
				</div>
			</section>
			<!--/wrapper -->
		</section>


