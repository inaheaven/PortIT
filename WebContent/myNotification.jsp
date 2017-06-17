<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="assets/css/notification.css" rel="stylesheet">
		<!--main content start-->
		<section id="main-content">
			<section class="wrapper site-min-height">
				<div class="col-md-12 col-sm-12 col-xs-12 mt noti">				
					<div class="notiTable">			
						<div class="form-group col-md-2">
						  <select class="form-control pull-left" id="sort">
						    <option selected>전체</option>
						    <option>좋아요</option>
						    <option>업로드</option>
						    <option>신청</option>
						    <option>메세지</option>
						    <option>팔로잉</option>
						  </select>
						</div>	
						
						<table class="table">							
	                        <thead>
	                         <tr>
	                             <th>#</th>
	                             <th>내용</th>
	                             <th>날짜</th>
	                             <th></th>
	                         </tr>
	                        </thead>
	                        <tbody> 
	                         <tr> <!-- 반복 -->
	                             <td class="sort"><span class="glyphicon glyphicon-heart"></span></td>
	                             <td class="content">[사용자]님이 내 포트폴리오를 좋아합니다.</td>
	                             <td class="date">2017-06-17 12:00</td>
	                             <td class="delete"><button type="button" class="btn">삭제</button></td>
	                         </tr>
	                         <tr> 
	                             <td><span class="glyphicon glyphicon-upload"></span></td>
	                             <td>[사용자]님이 새로운 포트폴리오를 업로드했습니다.</td>
	                             <td>2017-06-17 12:00</td>
	                             <td><button type="button" class="btn">삭제</button></td>
	                         </tr>
	                         <tr> 
	                             <td><span class="glyphicon glyphicon-envelope"></span></td>
	                             <td>[사용자]님이 메세지를 보냈습니다.</td>
	                             <td>2017-06-17 12:00</td>
	                             <td><button type="button" class="btn">삭제</button></td>
	                         </tr>
	                         <tr> 
	                             <td><span class="glyphicon glyphicon-ok-sign"></span></td>
	                             <td>[사용자]님이 내 [프로젝트]에 신청했습니다.</td>
	                             <td>2017-06-17 12:00</td>
	                             <td><button type="button" class="btn">삭제</button></td>
	                         </tr>
	                         <tr> 
	                             <td><span class="glyphicon glyphicon-plus"></span></td>
	                             <td>[사용자]님이 나를 팔로잉했습니다.</td>
	                             <td>2017-06-17 12:00</td>
	                             <td><button type="button" class="btn">삭제</button></td>
	                         </tr>
	                        </tbody>	                        
                       	</table>
						<div class="row" style="text-align:center;">
							<a href="#"><i class="fa fa-angle-left"></i> 이전글</a>&nbsp;&nbsp;&nbsp;
								1 2 3 4 5&nbsp;&nbsp;&nbsp;
							<a href="#">다음글 <i class="fa fa-angle-right"></i></a>
						</div>
					</div>	
				</div>
			</section>
			<!-- /wrapper --> 
		</section>
