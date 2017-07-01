<%@page import="portit.model.dto.Portfolio"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="assets/css/search.css" rel="stylesheet">
<jsp:useBean id="portfolio_viewDao"	class="portit.model.dao.ViewDao"></jsp:useBean>

	<section class="container">
		<section class="wrapper site-min-height">
			<div class="col-md-12 mt search" id="searchPf">
				<!-- 검색어 검색 폼 -->

				<div class="col-md-12 mt mb">
					<form class="col-md-10 searchKeyword" method="post"
						action="/SearchView?cmd=PFSEARCH">
						<div class="form-group col-md-11">
							<input type="text" class="form-control" name="pfSearch"
								value="${sessionScope.search}" />
						</div>
						<button type="submit" class="col-md-1 btn common" id="pfsubmit">
							<i class="fa fa-search"></i>
						</button>
					</form>
					<div class="col-md-2 collapsed sortingBtn text-right"
						data-toggle="collapse" data-parent="#searchPf"
						href="#searchSorting">
						<button type="button" class="btn common updown">
							조건 검색&nbsp;&nbsp;<i class="fa fa-chevron-down"></i>
						</button>
					</div>
				</div>
				<!-- 조건 검색 box -->
				<div class="searchSorting col-md-12 collapse" id="searchSorting">
					<form class="" method="post" name="detailsearch"
						action="/detailSearch?cmd=PFDETAIL">
						<input type="hidden" name="list_value" />
						<div class="">
							<div class="sortKey col-md-1">
								<b>정렬</b>
							</div>
							<div class="col-md-11">
								<a href="javascript:detailSearch(1)">최신순</a> 
								<a href="javascript:detailSearch(2)">인기순</a>
							</div>
						</div>
						<br> <br>
						<div class="sortKey col-md-1">
							<b>태그</b>
						</div>
						<div class="col-md-11">
							<!-- 인기 태그 6개 띄우기 -->
							<input class="btn poptag" type="button" value="JAVA"
								name="language" onclick="fnAppendItem()" /> <input
								class="btn poptag" type="button" value="C" name="language"
								onclick="fnAppendItem()" /> <input class="btn poptag"
								type="button" value="c++" name="language"
								onclick="fnAppendItem()" /> <input class="btn poptag"
								type="button" value="Eclipse" name="language"
								onclick="fnAppendItem()" /> <input class="btn poptag"
								type="button" value="jsp" name="language"
								onclick="fnAppendItem()" /> <input class="btn poptag"
								type="button" value="servlet" name="language"
								onclick="fnAppendItem()" /> .....
						</div>
						<br> <br>
						<div class="col-md-offset-1 col-md-4">
							<input type="text" class="form-control taginput" id="language"
								name="language" placeholder="검색하고 싶은 태그를 입력하세요."
								onchange="fnAppendItem2()" />
						</div>
						<br> <br>
						<hr />
						<div id="itemList" class="col-md-9"></div>
						<button type="submit" class="btn common col-md-2">조건 검색하기</button>
					</form>
				</div>
				<!-- END - 조건 검색 box -->
<%	
 	List list = portfolio_viewDao.portfolio_info();
 
 	// 페이징 기능 추가
 		int totalRecord = list.size();	//전체 글의 갯수
 		int numPerPage = 4;				//한 페이지당 보여질 글의 갯수
 		int totalPage = 0;				//전체 페이지 수
 		int nowPage = 0;				//현재 선택한(보고있는) 페이지 번호
 		int beginPerPage = 401;			//각 페이지의 시작번호(예를 들어 한 페이지에 5개씩 담는다면 2페이지의 값은 6 3페이지는 11)
 		int pagePerBlock = 3;			//한 블록당 묶을 페이지 수 (값이 3이므로 1,2,3 / 4,5,6 / ..페이지로 묶임)
 		int totalBlock = 0;				//전체 블럭 갯수
 		int nowBlock = 0;				//현재 블럭
 		
 		totalPage = (int)Math.ceil((double)totalRecord/numPerPage);
 		
 		if(request.getParameter("nowPage")!=null)
 			nowPage = Integer.parseInt(request.getParameter("nowPage"));
 		
 		if(request.getParameter("nowBlock")!=null)
 			nowBlock = Integer.parseInt(request.getParameter("nowBlock"));
 		
 		totalBlock = (int)Math.ceil((double)totalPage/pagePerBlock);
 		
 		beginPerPage = nowPage * numPerPage;
 		
 		if(list.size() == 0){
 
 		}
 		else{
 			for(int i=beginPerPage; i< numPerPage+beginPerPage; i++){
 				if(i == totalRecord){	//마지막 페이지에 게시글이 5개가 아닐 때 오류가 나는 것 방지
 					break;
 			}
 		Portfolio port = (Portfolio) list.get(i);
 %>
 
 				<!-- 첫번째 포트폴리오 -->
 				<div class="col-md-3 mb">
 									<div class="portfolio-simple">
 										<div class="pfImg"></div>	
 										<div class="pfInfo">
 											<div class="simple-content">
 												<div class="pfTag">
 													<a href=""># <%=port.getTag_name()%>&nbsp;</a></div>
 						
 											<div class="pfTitle">
 													<a href=""><%=port.getPf_title()%></a>
 												</div>
 												<div class="pfBottom">
 													<span class="pfmemName"><a href=""><%=port.getProf_name()%></a></span> 
 													<span class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;<%=port.getPf_like()%></span>
 												</div>
 											</div>
 										</div>
 									</div>
 								</div>	
 <%
 		}
 	}
 %>							
 				</div>
 
 				<!-- 페이지네이션 -->
 	<div align="center">		
 		<% if(nowBlock > 0){%>
 			<a href="/page?page=pfList?nowBlock=<%=nowBlock-1%>&nowPage=<%=pagePerBlock*(nowBlock+1)%>">이전<%=pagePerBlock%>개</a>
 		<% }%> 
 		:::
 		<%
 			for(int i=0; i<pagePerBlock; i++){
 				if((nowBlock*pagePerBlock)+i == totalPage)
 					break;
 		%>
 				<a href="/page?page=pfList?nowPage=<%=(nowBlock*pagePerBlock)+i%>&nowBlock=<%=nowBlock%>"><%= (nowBlock*pagePerBlock)+i+1%></a>&nbsp;&nbsp;&nbsp;
 		<%
 			}
 		%>
 		::: 
 		<% if(totalBlock > nowBlock+1){%>
 			<a href="/page?page=pfList?nowBlock=<%=nowBlock+1%>&nowPage=<%=pagePerBlock*(nowBlock+1)%>">다음<%=pagePerBlock%>개</a>
 		<% }%>
 	</div>	
 		
 		</section>
 		<!--/wrapper -->
 
	</section>


	<!-- detail search bar -->
	<script src="assets/js/search.js"></script>

	<!--script for this page-->
	<script>
		$(document).ready(
				function() {
					$('.updown').on(
							'click',
							function() {
								var icon = $(this).find('i');
								if (icon.hasClass("fa-chevron-down")) {
									icon.addClass("fa-chevron-up").removeClass(
											"fa-chevron-down");
								} else {
									icon.addClass("fa-chevron-down")
											.removeClass("fa-chevron-up");
								}
							});
				});

		//list_value = 3 이면 최신순 정렬4이면 인기순
		function detailSearch(list_value) {
			document.detailsearch.list_value.value = list_value;
			document.detailsearch.submit();
		}
	</script>
