<%@page import="portit.model.dto.Portfolio"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="assets/css/search.css" rel="stylesheet">

	<section class="container">
		<section class="wrapper site-min-height">
			<div class="col-md-12 mt search" id="searchPf">
				<!-- 검색어 검색 폼 -->

				<div class="col-md-12 mt mb">
					<form class="col-md-10 searchKeyword" method="post"
						action="/SearchView?cmd=PFSEARCH">
						<div class="form-group col-md-11">
					<input type="text" class="form-control" name="pfSearch" value="" />
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
					<form class="" method="post" name="detailsearch" action="/detailSearch?cmd=PFDETAIL">
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
							<input class="btn poptag" type="button" value="JAVA" name="language" onclick="fnAppendItem()" /> 
							<input class="btn poptag" type="button" value="C" name="language" onclick="fnAppendItem()" /> 
							<input class="btn poptag" type="button" value="c++" name="language" onclick="fnAppendItem()" /> 
							<input class="btn poptag" type="button" value="Eclipse" name="language" onclick="fnAppendItem()" /> 
							<input class="btn poptag" type="button" value="jsp" name="language" onclick="fnAppendItem()" /> 
							<input class="btn poptag" type="button" value="servlet" name="language" onclick="fnAppendItem()" /> .....
						</div>
						<br> <br>
						<div class="col-md-offset-1 col-md-4">
							<input type="text" class="form-control taginput" id="language" name="language"
								placeholder="검색하고 싶은 태그를 입력하세요." onchange="fnAppendItem2()" />
						</div>
						<br> <br>
						<hr />
						<div id="itemList" class="col-md-9"></div>
						<button type="submit" class="btn common col-md-2">조건 검색하기</button>
					</form>
				</div>
				<!-- END - 조건 검색 box -->
				
					<div class="pfResult mt mb" id="pfResult">
						<div class="col-md-12 mb">
				
	<c:if test="${port_list.size() != 0 && port_list.size()>0 }">
		<c:forEach begin="0" end="${port_list.size()-1}" var="i" >	
						<!-- 포트폴리오 -->
						<div class="col-md-3 mb">
							<div class="portfolio-simple">
								<div class="pfImg"></div>
								<div class="pfInfo">
									<div class="simple-content">
										<div class="pfTag">
											<a href="">#${port_list_tag[i].tag_name}&nbsp; </a>
										</div>
										<div class="pfTitle">
											<a href="">${port_list[i].pf_title} </a>
										</div>
										<div class="pfBottom">
											<span class="pfmemName"><a href="">${port_list[i].prof_name}</a></span> <span
												class="pfLikeCount"><span class="fa fa-heart"></span>&nbsp;&nbsp;${port_list[i].pf_like}</span>
										</div>
									</div>
								</div>
							</div>
						</div>		
					</c:forEach>
			</c:if>	
	<c:if test="${port_list.size() == 0 }">
		검색된 결과가 없습니다.
	</c:if>	

		
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
		$(document).ready(function(){
			event.stopPropagation();
		});		
	</script>
	
	
	<script>
		//list_value = 1 이면 최신순 정렬 2이면 인기순
		function detailSearch(list_value){
			document.detailsearch.list_value.value = list_value;
			document.detailsearch.submit();
		}	
	</script>
