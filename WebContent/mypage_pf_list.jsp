<%@page import="portit.model.dto.Member"%>
<%@page import="portit.model.dto.Project"%>
<%@page import="portit.model.dto.Portfolio"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>




<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<title>PORTIT - MYPAGE</title>

<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/style-responsive.css" rel="stylesheet">
<link href="assets/css/custom.css" rel="stylesheet">
<link href="assets/css/profpfproj.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<section id="container">
		<!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
		
		
		
		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->

		<!--main content start-->
		<section id="main-content">
			<section class="wrapper site-min-height">
				<div class="col-md-12 col-sm-12 col-xs-12 mt">
					<!-- BASIC FORM ELELEMNTS -->
					<div class="col-lg-12">
						<div class="form-panel">
							<form class="form-horizontal style-form" name="frmList" method="post" action="/myportporio">
							
							
										<!--fnParameter()에서 접근해서 value삽입  -->
										<input type="hidden" id="cmd" name="cmd" value="">
										<input type="hidden" id="cmdAction" name="cmdAction" value="">
										<input type="hidden" id="param" name="param" value="">
							
							
							
								<div class="col-sm-3">
									정렬&nbsp; &nbsp;&nbsp;&nbsp;
									<button type="button" class="btn btn-default">등록순</button>
									<button type="button" class="btn btn-default">인기순</button>
								</div>
								<div class="col-sm-7"></div>
								<div class="col-sm-2">
									<button type="button" class="btn btn-primary" style="margin-left:-10%" onclick="fnParameter('add','','')">
									포트폴리오 등록하기</button>
								<div class="row">&nbsp;</div>
								</div>


								<% 
								//Pageing
								// 페이징 기능 추가
								
								List portforio_list= (List)request.getAttribute("port_list");
								
								
								int totalRecord = portforio_list.size();		//총반복횟수.
								int numPerRow = 3;								//한줄에 3개씩
								int totalRow = 0;								//3개씩 몇줄?
								int nowRow = 0;									//현재 몇번째 줄
								int beginPerRow = 0;							//시작줄
								
								 
								//총 몇줄 = 총갯수/한줄당 3개
								totalRow = (int)Math.ceil((double)totalRecord/numPerRow);
								%>


								
								<!-- 자료형이 3개마다<div class="row">가 생성되어야한다...  -->
								<!-- 나중에 다시해보자.. -->
								
								
								<%		for(int i=0;i<totalRecord; i++){
											Portfolio dto = (Portfolio) portforio_list.get(i);
								%>
								
								
								<%		if(i==0||i==2||i==5){
								%>
										 <div class="row">
								<%		}	
								%>
								
								
										
								
										<!-- 반복시작 -->
										<div class="col-md-4 mb">
										<div class="image-hover">
											<div class="portfolio-simple" style="margin-left:5%;">
												<div class="pfImg"></div>
												<div class="pfInfo">
													<div class="simple-content">
														<div class="pfTag">
															<!-- 태그 -->
															<a href="javascript:fnParameter('tag' , '' , '<%=dto.getTag_name()%>')">#<%=dto.getTag_name() %>&nbsp;															</a>
														</div>
														<div class="pfTitle">
														<!-- 포트폴리오 디테일 -->
															<a href="javascript:fnParameter('detail','','<%=dto.getPf_id()%>')">
																<%=dto.getPf_title() %> 
															</a>
														</div>
														<div class="pfBottom">
														<!-- Coworker의 프로필페이지. -->
															<span class="pfmemName">
																<a href="javascript:fnParameter('profile','','<%=dto.getMem_id()%>')">
																	<%=dto.getProf_name() %>
																</a>
															</span>
															<span class="pfLikeCount"><span
																class="fa fa-heart"></span>&nbsp;&nbsp;<%=dto.getPf_like()%></span>
														</div>
													</div>
												</div>
												
												<div class="top-hover-right">
													<div class="after-hover" >
															
															<!-- 수정 -->
															<button type="button" value="btnModify" name="btnModify" id="btnModify" class="btn btn-hover" 
															onclick="fnParameter('modify','','<%=dto.getPf_id()%>')">		
																<span class="glyphicon glyphicon-edit"></span>
															</button>
														
															<!-- 삭제  -->
															<button type="button" value="btnDelete" name="btnDelete" id="btnDelete" class="btn btn-hover"  
															onclick="fnParameter('list','delete','<%=dto.getPf_id()%>')">	
																<span class="glyphicon glyphicon-remove"></span>
															</button>
													</div>
												</div>
												
											</div>
											</div>
										</div>
										<!-- 반복끝 -->
										
								<%		if(i==0||i==2||i==5){
								%>
										</div>
										<!--/END 1ST ROW OF PANELS -->
								<%		}
								%>								
								
										
								<%	}//List반복.
								%>	
								</form>
								
						</div>
					</div>
				</div>
				<!-- /row -->


			</section>
			<!-- /wrapper -->
		</section>
	</section>






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

	<!--script for this page-->
	
	
	
	<script>
	function fnParameter(_cmd,_action,_param){
		
		//전달할 데이터 setting
		document.frmList.cmd.value =_cmd;
		document.frmList.cmdAction.value =_action;
		document.frmList.param.value =_param;
		
		//해당 폼 submit
		document.frmList.submit();
		
		
		/* 	
		frmDelete.pf_id.value = pf_id;
		document.frmDelete.submit();
		
		//FnRun의 매개변수로 (cmd경로, action, DAO메서드 매개변수)
		//ex fnrun(list, delete, pf_id)
		//입력된 파라미터를 input함수에 삽입한다.
		
		
		- 태그 ID로 해당태그 value값 접근.
		var action = document.getElementById("btnDelete").value;
		if(document.search.keyWord.value == "")

		-특정태그의 value에  값 삽입. 
		frmModify.cmdAction.value =action;
		
		-폼에 접근해서 submit() 
		document.frmDelete.submit();
		*/
	}
	
	
	
	function check(){
		if(document.search.keyWord.value == ""){
			alert("검색어를 입력하세요.");
			document.search.keyWord.focus();
			return;
		}
		document.search.submit();
	}
	
</script>
	
	
</body>
</html>
