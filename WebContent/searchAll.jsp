<%@page import="portit.model.dto.Project"%>
<%@page import="java.util.List"%>
<%@page import="portit.model.dto.Portfolio"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link href="assets/css/search.css" rel="stylesheet">

		<section class="container">
			<section class="wrapper site-min-height">
				<div class="col-md-12 mt search" id="search">
					<h2>
						<span class="fa fa-search"></span>
						<span class="keyword">${search}</span> 	
					</h2>
					<!-- 조건 검색 box -->					
					<div class="searchSorting col-md-12 mt">	
						<form class="" id="sea" name="" method="post" action="">
							
							<div class="">
								<div class="sortKey col-md-1"><b>구분</b></div>
								<div class="col-md-11">
									<a href="#pfResult">포트폴리오</a>
									<a href="#memResult">인물</a> 
									<a href="#projResult">프로젝트</a>					
								</div>
							</div>
							<br><br>
							<!-- 						
							<div class="mb">
								<span class="sortKey">정렬</span>
									<a href="#" class="active">최신순</a>
									<a href="#">인기순</a>
									<a href="#">랜덤</a>
							</div>
							 -->							
							
							<div class="sortKey col-md-1"><b>태그</b></div>										
							<div class="col-md-11">	
								<!-- 인기 태그 6개 띄우기 -->							
								<input class="btn poptag" type="button" name="language" value="java" onclick="fnAppendItem()"/>
								<input class="btn poptag" type="button" name="language" value="c" onclick="fnAppendItem()"/>
								<input class="btn poptag" type="button" name="language" value="c++" onclick="fnAppendItem()"/>
								<input class="btn poptag" type="button" name="language" value="c#" onclick="fnAppendItem()"/>
								<input class="btn poptag" type="button" name="language" value="jsp" onclick="fnAppendItem()"/>
								<input class="btn poptag" type="button" name="language" value="servlet" onclick="fnAppendItem()"/>								
								.....
							</div>
							<br><br>
							<div class="col-md-offset-1 col-md-4">
								<input type="text" class="form-control taginput" id="language" name="language" placeholder="검색하고 싶은 태그를 입력하세요." onchange="fnAppendItem2()" />
							</div>		
							<br><br>
							<hr />
							<div id="itemList" class="col-md-9"></div>		
							<button type="submit" class="btn common col-md-2">조건 검색하기</button>									
						</form>
					</div> <!-- END - 조건 검색 box -->	
					<br>	
					<br>
					<hr class="resultLine" id="pfResult"/>
	
					<!-- 포트폴리오 결과 -->									
					<div class="pfResult mt mb" id="pfResult">
						<div class="col-md-12 mb">
						<h4>포트폴리오(${port_list.size()} 건)&nbsp;&nbsp;&nbsp;<i class="fa fa-angle-double-right"></i></h4>
	<!-- 검색 결과가 4개 이상일때 -->		
		<c:if test="${port_list.size() != 0 && port_list.size()>0 && port_list.size()>3}">
				<c:forEach begin="0" end="3" var="i" >	
						<!-- 포트폴리오 -->
						<div class="col-md-3 mb">
							<div class="portfolio-simple">
								<div class="pfImg"></div>
								<div class="pfInfo">
									<div class="simple-content">
										<div class="pfTag">
											<a href="javascript:tag('${port_list[i].tag_name}')">#${port_list_tag[(3*i)+0].tag_name}&nbsp;</a>
											<a href="javascript:tag('${port_list[i].tag_name}')">#${port_list_tag[(3*i)+1].tag_name}&nbsp;</a>
											<a href="javascript:tag('${port_list[i].tag_name}')">#${port_list_tag[(3*i)+2].tag_name}&nbsp;</a>
										</div>
										<div class="pfTitle">
											<a href="javascript:pf(${port_list[i].pf_id})">${port_list[i].pf_title} </a>
										</div>
										<div class="pfBottom">
											<span class="pfmemName">
												<a href="javascript:prof('${port_list[i].prof_id}')">
													${port_list[i].prof_name} </a>
											</span> 
											<span class="pfLikeCount">
												<span class="fa fa-heart"></span>&nbsp;&nbsp;${port_list[i].pf_like}
											</span>
										</div>
									</div>
								</div>
							</div>
						</div>		
					</c:forEach>
			</c:if>	
	<!-- 검색 결과가 4개 이하일때 -->
		<c:if test="${port_list.size() != 0 && port_list.size()>0 && port_list.size()<=3}">
				<c:forEach begin="0" end="${port_list.size()-1 }" var="i" >	
						<!-- 포트폴리오 -->
						<div class="col-md-3 mb">
							<div class="portfolio-simple">
								<div class="pfImg"></div>
								<div class="pfInfo">
									<div class="simple-content">
										<div class="pfTag">
											<a href="javascript:tag('${port_list[i].tag_name}')">#${port_list_tag[(3*i)+0].tag_name}&nbsp;</a>
											<a href="javascript:tag('${port_list[i].tag_name}')">#${port_list_tag[(3*i)+1].tag_name}&nbsp;</a>
											<a href="javascript:tag('${port_list[i].tag_name}')">#${port_list_tag[(3*i)+2].tag_name}&nbsp;</a>
										</div>
										<div class="pfTitle">
											<a href="javascript:pf('${port_list[i].pf_id}')">${port_list[i].pf_title} </a>
										</div>
										<div class="pfBottom">
											<span class="pfmemName">
												<a href="javascript:prof('${port_list[i].prof_id}')">
													${port_list[i].prof_name} </a>
											</span> 
											<span class="pfLikeCount">
												<span class="fa fa-heart"></span>&nbsp;&nbsp;${port_list[i].pf_like}
											</span>
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
						</div>
						<form post="method" name="move" action="/SearchView">
							<input type ="hidden" name="totalsearch" value="${search}"/>
							<input type ="hidden" name="cmd" value="PFSEARCH"/>
						
							<div class="text-right">
								<button type="submit" class="btn moreBtn">더 보기</button>							
							</div>
						</form>
					</div>
			
					<hr class="resultLine" id="memResult"/>

					<!-- 멤버 결과 -->
					<div class="memResult mt mb" >
						<div class="col-md-12 mb">
						<h4>멤버(${mem_list.size()}명)&nbsp;&nbsp;&nbsp;<i class="fa fa-angle-double-right"></i></h4>
	<!-- 검색 결과가 4개 이상일때 -->					
		<c:if test="${mem_list.size() != 0 && mem_list.size()>0 && mem_list.size()>3}">			
				<c:forEach begin="0" end="3" var="i" >	
						<!-- member -->
						<div class="col-md-3 mb">
	          				<div class="member-simple">
		          				<div class="simple-content text-center">	      
			          				<img class="memImg img-circle" alt="avatar" src="${mem_list[i].prof_img}"/>   
			         				<div>
			         					<div class="memName">
			         						<a href="javascript:prof('${mem_list[i].prof_id}')"> ${mem_list[i].prof_name}</a></div>
				         						<a href="javascript:tag('${port_list[i].tag_name}')">#${mem_list_tag[(3*i)+0].tag_name}&nbsp;</a>
												<a href="javascript:tag('${port_list[i].tag_name}')">#${mem_list_tag[(3*i)+1].tag_name}&nbsp;</a>
												<a href="javascript:tag('${port_list[i].tag_name}')">#${mem_list_tag[(3*i)+2].tag_name}&nbsp;</a>
			         					<div class="memFollow">
			         						<span class="fa fa-user"></span>&nbsp;&nbsp;
			         						<span class="memFollowCount">${mem_list[i].prof_follower}</span>
			         					</div>
			         				</div>
		          				</div>          				
	          				</div>
	          			</div> 
        			</c:forEach>
        	</c:if>	
      <!-- 검색결과가 4개 이하일때 -->  	
		<c:if test="${mem_list.size() != 0 && mem_list.size()>0 && mem_list.size()<=3}">			
				<c:forEach begin="0" end="${mem_list.size()-1 }" var="i" >	
						<!-- member -->
						<div class="col-md-3 mb">
	          				<div class="member-simple">
		          				<div class="simple-content text-center">	      
			          				<img class="memImg img-circle" alt="avatar" src="${mem_list[i].prof_img}"/>   
			         				<div>
			         					<div class="memName">
			         					<a href="javascript:prof('${mem_list[i].prof_id}')"> ${mem_list[i].prof_name}</a></div>
			         					<div class="memTag">
			         						<a href="javascript:tag('${port_list[i].tag_name}')">#${mem_list_tag[(3*i)+0].tag_name}&nbsp;</a>
											<a href="javascript:tag('${port_list[i].tag_name}')">#${mem_list_tag[(3*i)+1].tag_name}&nbsp;</a>
											<a href="javascript:tag('${port_list[i].tag_name}')">#${mem_list_tag[(3*i)+2].tag_name}&nbsp;</a>
			         					</div>
			         					<div class="memFollow">
			         						<span class="fa fa-user"></span>&nbsp;&nbsp;
			         						<span class="memFollowCount">${mem_list[i].prof_follower}</span>
			         					</div>
			         				</div>
		          				</div>          				
	          				</div>
	          			</div> 
        			</c:forEach>
        	</c:if>	
			<c:if test="${mem_list.size() == 0 }">
				검색된 결과가 없습니다.
			</c:if>	
						</div>					
						<form post="method" name="move" action="/SearchView">
							<input type ="hidden" name="totalsearch" value="${search}"/>
							<input type ="hidden" name="cmd" value="MEMSEARCH"/>
						
							<div class="text-right">
								<button type="submit" class="btn moreBtn">더 보기</button>							
							</div>
						</form>
							
					</div>
						
					<hr class="resultLine" id="projResult"/>

					<!-- 프로젝트 결과 -->
					<div class="projResult mt mb" >
						<h4>프로젝트(${proj_list.size()}건)&nbsp;&nbsp;&nbsp;<i class="fa fa-angle-double-right"></i></h4>
	<!-- 검색 결과가 4개 이상일때 -->
			<c:if test="${proj_list.size() != 0 && proj_list.size()>0 && proj_list.size()>3}">
				<c:forEach begin="0" end="3" var="i" >	
					<!-- 프로젝트 -->
					<div class="col-md-12 mb">
		          			<div class="project-list">
								<div class="col-md-9 mb" >
			          				<span class="pjInfoText">
			          					<div class="pjTitle"><a href="javascript:proj('${proj_list[i].proj_id}')">${proj_list[i].proj_title}</a></div>
			          					<div class="pjmemName">
			          					<a href="">
			          					<span class="fa fa-user">${proj_list[i].prof_name }</span>&nbsp;&nbsp;
			          						</a></div>	          		
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
		<!-- 검색 결과가 4개 이하일때 -->	
			<c:if test="${proj_list.size() != 0 && proj_list.size()>0 && proj_list.size()<=3}">
				<c:forEach begin="0" end="${proj_list.size()-1 }" var="i" >	
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
						<form post="method" name="move" action="/SearchView">
							<input type ="hidden" name="totalsearch" value="${search}"/>
							<input type ="hidden" name="cmd" value="PROJSEARCH"/>
						
							<div class="text-right">
								<button type="submit" class="btn moreBtn">더 보기</button>							
							</div>
						</form>
					</div>
				</div>
			</section>
			<!--/wrapper -->
		</section>

	<!-- detail search bar -->
	<script src="assets/js/search.js"></script>

	<!-- 상세페이지로 이동하기 위한 폼 -->	
	<form name="pf_info" method="post" action="/detailView">
		<input type="hidden" name="pf_id" /> 
		<input type ="hidden" name="cmd" value="PORTFOLIO"/>	
	</form>
	<form name="tag_info" method="post" action="/detailView">
		<input type="hidden" name="tag_name" /> 
		<input type ="hidden" name="cmd" value="MEMBER"/>	
	</form>
	<form name="prof_info" method="post" action="/detailView">
		<input type="hidden" name="prof_id" /> 	
		<input type ="hidden" name="cmd" value="PROJECT"/>
	</form>
	<form name="proj_info" method="post" action="/detailView">
		<input type="hidden" name="proj_id" /> 
		<input type ="hidden" name="cmd" value="TAG"/>	
	</form>
	
	<!-- 포트폴리오 정보 -->
	<script>	
	function pf(pf_id){
		document.pf_info.pf_id.value = pf_id;
		document.pf_info.submit();
	}
	function tag(tag_name){
		document.tag_info.tag_name.value = tag_name;
		document.tag_info.submit();	
	}
	function prof(prof_id){
		document.prof_info.prof_id.value = prof_id;
		document.prof_info.submit();
	}
	function proj(proj_id){
		document.proj_info.proj_id.value = proj_id;
		document.proj_info.submit();
	}
	</script>
