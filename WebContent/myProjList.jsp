<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.ArrayList"%>
<%@page import="portit.model.dto.ProjectApp_mem"%>
<%@page import="portit.model.dto.Project"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<title>PORTIT - MYPAGE</title>


<link href="assets/css/profpfproj.css" rel="stylesheet">
<!-- for adding rows button -->
<script src="assets/js/jquery-3.2.1.min.js"></script>

<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/style-responsive.css" rel="stylesheet">
<link href="assets/css/custom.css" rel="stylesheet">
<link href="assets/css/detailpage.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<script>
$(document).ready(

    function() {
    	var httpRequest=null;
         $("#delete_my_reg").click(function() {
        	 alert("1");
        		httpRequest = new XMLHttpRequest();
        		var name = "7"
        		alert(name);
        		var param = "/project_delete?proj_id="+name;
        		
        		httpRequest.open("GET", param, true);
        		httpRequest.onreadystatechange = callback;
        		httpRequest.send(null);
        		
        		  $(this).parent().parent().parent().parent().remove();
                  $(this).next().remove();
        	});
         
});
     	function callback(){
    		if(httpRequest.readyState == 4){
    			if(httpRequest.status == 200){
    				alert("1");
    			}
    		}
    	}
     	
     	function fnParameter(_cmd,_action,_param,_param2){
			
			//전달할 데이터 setting
			document.frmPJ.cmd.value =_cmd;
			document.frmPJ.cmdAction.value =_action;
			document.frmPJ.param.value =_param;
			document.frmPJ.param2.value =_param2;
			
			//해당 폼 submit
			document.frmPJ.submit();
	}
</script>
</head>
<body>
	<%--sidenavbar start--%>
	<jsp:include page="my.jsp"></jsp:include>
	<%--sidenavbar end--%>
	<form name="frmPJ" id="frmPJ" action="/myproj">
		<%
	//1.내가 등록한 프로젝트 리스트
	ArrayList pjList = (ArrayList)request.getAttribute("regPjInform");
	
	//2.내가 지원한 프로젝트 리스트.
	ArrayList applyPj =(ArrayList) request.getAttribute("applyProjectList");
	
	%>

		<!--fnParameter()에서 접근해서 value삽입  -->
		<input type="hidden" id="cmd" name="cmd" value=""> <input
			type="hidden" id="cmdAction" name="cmdAction" value=""> <input
			type="hidden" id="param" name="param" value=""> <input
			type="hidden" id="param2" name="param2" value="">


		<section id="main-content">
			<section class="wrapper site-min-height">
				<div class="col-md-12 col-sm-12 col-xs-12 mt projlist" id="pjbox">

					<div class="projlist_top clearfix">
						<button type="button" class="btn common"
							onclick="location.href='/page?page=myProjRegister&mem_id=2'">프로젝트
							등록하기</button>
							<!-- mem_id 고치기 -->
					</div>

					<h3 class="formTitle text-center">내 프로젝트</h3>

					<div class="proj_my">
						<p>내가 등록한 프로젝트</p>
						<div class="panel panel-default">



							<%
						for(int i=0; i<pjList.size();i++){
							//첫번째 pj
							ArrayList pj =(ArrayList) pjList.get(i);
							//0: pj정보.
							Project pj_Inform=(Project) pj.get(0);
							//1:지원자리스트
							ArrayList peoples= (ArrayList)  pj.get(1);
						%>

							<!-- R프로젝트:반복시작 -->
							<div class="panel-heading clearfix" role="tab">
								<h4 class="panel-title">
									<span class="col-sm-3"> <a
										href="javascript:fnParameter('pj_detail' , '' , '<%=pj_Inform.getProj_id()%>', '')">
											<%=pj_Inform.getProj_title() %>
									</a>
									</span> <span class="col-sm-6"> D-<%=pj_Inform.getD_day() %></span> <span
										class="col-md-3 text-right">
										<button type="button" class="btn common"
											onclick="fnParameter('modify','','<%=pj_Inform.getProj_id()%>', '')">수정</button>
										<button type="button" class="btn common"
											onclick="fnParameter('list','delete','<%=pj_Inform.getProj_id()%>', '')">삭제</button>
										<a class="updown collapsed" data-toggle="collapse"
										data-parent="#accordion" href="#pj_<%=i%>"
										aria-expanded="false" aria-controls="collapseOne"> <i
											class="fa fa-chevron-down"></i>
									</a>
									</span>
								</h4>
							</div>


							<div id="pj_<%=i%>" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="headingTwo">


								<!-- pr_con -->
								<div class="panel-body">


									<!-- 지원자_S-->
									<div class="col-lg-6">
										APPLICANTS
										<hr>
										<%
										//해당프로젝트의 지원자 리스트.
										for(int j=0; j<peoples.size();j++){
											
											//첫번째 지원자 정보.
											ProjectApp_mem p1 = (ProjectApp_mem) peoples.get(j);
											
											// 지원자라면...
											if("N".equals(p1.getApp_confirm())){
											%>
										<!-- 지원자 반복. -->
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7"><%=p1.getNick() %></div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default"
												onclick="fnParameter('list','join','<%=p1.getMem_id()%>', '')">수락</button>
										</div>
										<!-- 지원자 반복끝 -->
										<%
											}//IF 문
										}//for 문.
							%>

									</div>
									<!-- 지원자_E  -->




									<!-- 팀원_S   -->
									<div class="col-lg-6">
										COWORKERS
										<hr>
										<%
										//해당프로젝트의 지원자 리스트.
										for(int j=0; j<peoples.size();j++){
											
											//첫번째 지원자 정보.
											ProjectApp_mem p1 = (ProjectApp_mem) peoples.get(j);
											
											if("Y".equals(p1.getApp_confirm())){
										%>


										<!-- 팀원반복 -->
										<div class="col-lg-3">
											<i class="fa fa-envelope-o"></i>
										</div>
										<div class="col-lg-7"><%=p1.getNick() %></div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default"
												onclick="fnParameter('list','mem_delete','<%=pj_Inform.getProj_id()%>', '<%=p1.getMem_id()%>')">삭제</button>
										</div>
										<!-- 팀원반복 끝-->
										<%
										}//IF 종료
									}// 팀원for문  종료
					%>

									</div>
									<!-- 팀원_E -->


								</div>
								<!-- pj_con_end -->
							</div>
							<!-- acodianHead_end -->


							<%
						}//PF LIST
				%>
						</div>
						<!-- RegPj_end -->
					</div>
					<!-- my -->

					<!-- 내가 지원한 PJ -->
					<hr class="line" />
					<div class="proj_apply">
						<p>내가 지원한 프로젝트</p>
						<div class="panel panel-default">

							<%  for(int i=0; i<applyPj.size();i++){
							Project app_PJ = (Project)applyPj.get(i);
							
							
							System.out.println("JSP  "+app_PJ.getProj_id());
						%>

							<div class="panel-heading clearfix" role="tab" id="headingOne">
								<h4 class="panel-title">
									<span class="col-sm-3"> <a
										href="javascript:fnParameter('pj_detail' , '' , '<%=app_PJ.getProj_id()%>', '')">
											<%=app_PJ.getProj_title() %>
									</a>
									</span> <span class="col-sm-6"> D-<%=app_PJ.getD_day() %></span> <span
										class="col-md-3 text-right">
										<button type="button" class="btn common"
											onclick="fnParameter('list','cancle','<%=app_PJ.getProj_id()%>','')">지원
											취소</button>
									</span>
								</h4>
							</div>

							<%
						}
							%>

						</div>
					</div>




				</div>
				<!-- 전체 판낼 BG: 회색 -->


			</section>
			<!-- /wrapper -->
		</section>
	</form>
	<!-- js placed at the end of the document so the pages load faster -->
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery-ui-1.9.2.custom.min.js"></script>
	<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
	<script class="include" type="text/javascript"
		src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script src="assets/js/jquery.scrollTo.min.js"></script>
	<script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>


	<!--common script for all pages-->
	<script src="assets/js/common-scripts.js"></script>

	<!-- JS Libraries -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.sticky/1.0.4/jquery.sticky.min.js"></script>
	<!-- Custom JS -->
	<script type="text/javascript">
		// 스크롤 변화에 따른 프로필 메뉴바 상단 고정
		$("#portfolioNavbar").sticky({topSpacing:0, zIndex: '50'});
		
		// 스크롤 맨 위로
		$("#BackToTop").on("click", function(e){
			e.preventDefault();
			$("html,body").animate({ scrollTop : 0 }, 500);
		});
	</script>
</body>
</html>


