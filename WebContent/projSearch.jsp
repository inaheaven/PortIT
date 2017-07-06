<%@page import="portit.model.dto.Project"%>
<%@page import="portit.model.dto.Portfolio"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="assets/css/search.css" rel="stylesheet">
	<section class="container">
		<section class="wrapper site-min-height">
			<div class="col-md-12 mt search" id="searchPf">
				<!-- 검색어 검색 폼 -->

				<div class="col-md-12 mt mb">
					<form class="col-md-10 searchKeyword" method="post"
						action="/SearchView?cmd=PROJSEARCH">
						<div class="form-group col-md-11">
							<input type="text" class="form-control" name="projSearch" value="${sessionScope.search}"/>
						</div>
						<button type="submit" class="col-md-1 btn common" id="">
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
					<form class="" method="post" name="detailsearch" action="/detailSearch?cmd=PROJDETAIL">
						<input type="hidden" name="list_value"/>
						<div class="">
							<div class="sortKey col-md-1">
								<b>정렬</b>
							</div>
							<div class="col-md-11">
								<a href="javascript:detailSearch(5)">최신순</a> 
								<a href="javascript:detailSearch(6)">D-day</a> 
							</div>
						</div>
						<br> <br>
						<div class="sortKey col-md-1">
							<b>태그</b>
						</div>
						<div class="col-md-11">
							<!-- 인기 태그 6개 띄우기 -->
							<input class="btn poptag" type="button" value="JAVA" name="language" onclick="fnAppendItem()" /> 
							<input class="btn poptag" type="button" value="C" name="language" onclick="fnAppendItem()" /> 
							<input class="btn poptag" type="button" value="c++" name="language" onclick="fnAppendItem()" /> 
							<input class="btn poptag" type="button" value="c#" name="language" onclick="fnAppendItem()" /> 
							<input class="btn poptag" type="button" value="jsp" name="language" onclick="fnAppendItem()" /> 
							<input class="btn poptag" type="button" value="servlet" name="language" onclick="fnAppendItem()" /> .....
						</div>
						<br> <br>
						<div class="col-md-offset-1 col-md-4">
							<input type="text" class="form-control taginput" id="language" name="language2"
								placeholder="검색하고 싶은 태그를 입력하세요." onchange="fnAppendItem2()" />
						</div>
						<br> <br>
						<hr />
						<div id="itemList" class="col-md-9"></div>
						<button type="submit" class="btn common col-md-2">조건 검색하기</button>
					</form>
				</div>
				<!-- END - 조건 검색 box -->
			

		<c:if test="${proj_list.size() != 0 && proj_list.size()>0 }">
				<c:forEach begin="0" end="${proj_list.size()-1}" var="i" >	
					<!-- 프로젝트 -->
						<div class="col-md-12 mb">
		          			<div class="project-list">
								<div class="col-md-9 mb" >
			          				<span class="pjInfoText">
			          					<div class="pjTitle"><a href="javascript:proj_title('${proj_list[i].proj_id}')">${proj_list[i].proj_title}</a></div>
			          					<div class="pjmemName"><span class="fa fa-user">${proj_list[i].prof_name }</span>&nbsp;&nbsp;<a href=""></a></div>
			          							          		
			          					<div class="pjIntro">${proj_list[i].proj_intro}</div><br><br><br>
			          					<div class="pjTag">
			          						<a href="javascript:tag_name('${port_list[i].tag_name}')">#${proj_list_tag[(3*i)+0].tag_name}&nbsp;</a>
											<a href="javascript:tag_name('${port_list[i].tag_name}')">#${proj_list_tag[(3*i)+1].tag_name}&nbsp;</a>
											<a href="javascript:tag_name('${port_list[i].tag_name}')">#${proj_list_tag[(3*i)+2].tag_name}&nbsp;</a>
			          					</div>         					
	          						</span>
	          					</div>
	          				<div class = "col-md-3">
	          					<span class="pjInfoTable">
	          						<table class="table text-center">
	          							<tr><td>백엔드개발자</td></tr>
	          							<tr><td>${proj_list[i].proj_to} 명</td></tr>
	          							<tr><td>마감일까지 D&nbsp;-&nbsp;${proj_list[i].d_day}</td></tr>
	          							<tr><td></td></tr>
	          						</table>
	          					</span>
	          				</div>	          				
	          			</div>          			
					</div>
						<br><br>
				</c:forEach>
			</c:if>	
			<c:if test="${proj_list.size() == 0 }">
				검색된 결과가 없습니다.
			</c:if>			
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
	</script>
	
	<script>
		//list_value = 5 이면 최신순 정렬 6이면 인기순
		function detailSearch(list_value){
			document.detailsearch.list_value.value = list_value;
			document.detailsearch.submit();
		}	
	</script>
