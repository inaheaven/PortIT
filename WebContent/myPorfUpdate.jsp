<%@page import="java.util.List"%>
<%@page import="portit.model.dto.Profile"%>
<%@page import="portit.model.dao.ProfileDao"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.*"%>
<%@page import="javax.sql.DataSource"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="assets/css/profpfproj.css" rel="stylesheet">
<jsp:useBean id="dao" class="portit.model.dao.ProfileDao" />
<jsp:useBean id="dto" class="portit.model.dto.Profile" />


<%
	//int mem_id = Integer.parseInt(request.getParameter("mem_id"));

	/////////////////102 -> mem_id로 //////////////
	Profile prof= dao.getProfile(103);

%>

<script src="assets/js/search.js"></script>
<script language="javascript">

// debugger;

// window.onload = function() { location.href="/register"; };

function profRegister(){
	
	alert("프로필이 정상적으로 등록 되었습니다.");
}

function profalter(){
	location.href="/register?cmd=UPDATE"
	alert("프로필이 정상적으로 수정되었습니다.");
}
//skill 추가
function addSkill() {
	var skill = document.getElementById('skill').value;
	var score = document.getElementById('score').value;
	
	if(Number(score)>Number("5")){
		alert("숫자의 범위를 확인해주세요.");
		document.getElementById('score').focus();
		return;
	}
	
	addSkillIndex++;
	
	var newSpan = document.createElement("span"); 
	
	newSpan.setAttribute("id","item_" + addSkillIndex);
	
	//상태바 길이 조정.
	var barSize =0;
	if(Number(score)==Number("1")){
		barSize=20;
	}else if(Number(score)==Number("2")){
		barSize=40;
	}else if(Number(score)==Number("3")){
		barSize=60;
	}else if(Number(score)==Number("4")){
		barSize=80;
	}else if(Number(score)==Number("5")){
		barSize=100;
	}
	
	//상태바 추가
	var idx ="";
		
	idx+="<div class='col-md-3 skill'>"+skill+"</div>";
	idx+="<div class='col-md-7'>";
		idx+="<div class='progress'>";
			idx+="<div class='progress-bar' role='progressbar' aria-valuenow='45' aria-valuemin='0' aria-valuemax='100' style='width:"+ barSize+"%''>";
				idx+="<span class='sr-only'>45% Complete</span>";
				idx+="<span class='sr-only'>45% Complete</span>";
			idx+="</div>";			
		idx+="</div>";
	idx+="</div>";
	idx+="<div class='col-md-2'>";
		idx+="<button class='btn common' type='button' onclick='fnDeleteItem("+ addSkillIndex +")'>삭제</button";
	idx+="</div>";
	idx+="</br>";
	
	newSpan.innerHTML = idx;
	
	var itemList = document.getElementById("skillList");
	itemList.appendChild(newSpan);			
}
//상태바 삭제 
function fnDeleteItem(idx){
	var item = document.getElementById("item_" + idx);
	
	if(item != null){
		item.parentNode.removeChild(item);
	}
	
}
function frmCheck(){
  var frm = document.form;
  
  for( var i = 0; i <= frm.elements.length - 1; i++ ){
     if( frm.elements[i].name == "addText" )
     {
         if( !frm.elements[i].value ){
             alert("텍스트박스에 값을 입력하세요!");
                 frm.elements[i].focus();
	 return;
          }
      }
   }
 }
 
// sns url 붙이기
function fnSelectSnsInfo(snsName){
	document.getElementById("snsText").value=snsName;
	document.getElementById("snsText").focus();	
}

</script>

<!-- for adding rows button -->
<script src="assets/js/jquery-3.2.1.min.js"></script>
<script>
	
	$(document).ready(function() {
				
		$("#snsAdd").click(function(){
			var snsUrl = $("#snsText").val();
			var snsHtml = "<tr>"+
							"<td class='col-md-3'></td>"+
							"<td class='col-md-7' style='padding-left: 30px'>"+snsUrl+"</td>"+
							"<td class='col-md-2'><button type='button' class='btn common'>삭제</button></td>"+
						"</tr>";
			$("#snsList table").append(snsHtml);
		});
	
		
		$("#snsList table").on("click", "button", function(){
			$(this).closest("tr").remove();
		});
		
	});
</script>

<%--sidenavbar start--%>
<jsp:include page="my.jsp"></jsp:include>
<%--sidenavbar end--%>
<section id="main-content">
	<section class="wrapper site-min-height">
		<div class="col-md-12 mt profreg">
			<div class="profregForm">
				<h3 class="formTitle text-center">프로필 수정</h3>
				<form action="/register?cmd=UPDATE" method="post"
					class="form-horizontal style-form">

					<!-- 프로필id hidden -->
					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-9">
							<input type="hidden" class="form-control" name="prof_id">
						</div>
					</div>
					<!-- 등록일자  hidden -->
					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-9">
							<input type="hidden" class="form-control" name="prof_regdate">
						</div>
					</div>
					<!-- 팔로워 수 hidden (초기값 0) -->
					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-9">
							<input type="hidden" class="form-control" name="prof_follower" 
								value="0">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">프로필 사진</label>
						<div class="col-md-9">
							<input type="file" class="form-control" name="prof_img" value="<%=prof.getProf_img() %>">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">배경 사진</label>
						<div class="col-md-9">
							<input type="file" class="form-control" name="prof_background" value="<%=prof.getProf_background()%>">
						</div>

					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">이름</label>
						<div class="col-md-9">
							<input class="form-control" name="prof_name" type="text"
								placeholder="한글로 입력하세요." value="<%=prof.getProf_name() %>" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">닉네임</label>
						<div class="col-md-9">
							<input class="form-control" name="prof_nick" type="text"
								placeholder="영문으로 입력하세요. 이 닉네임은 url로도 쓰입니다.(중복 불가)" value="<%=prof.getProf_nick()%>">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">소개</label>
						<div class="col-md-9">
							<textarea class="form-control" rows="5" name="prof_intro"
								placeholder="" ><%=prof.getProf_intro()%></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">관심 언어</label>
						<div class="col-md-9">
							<input class="form-control" id="Prof_language" type="text"
								name="tag_name" placeholder="ex) C, JAVA, Python 등" value="<%=prof.getTag_name()%>">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">관심 툴</label>
						<div class="col-md-9">
							<input class="form-control" id="disabledInput" type="text"
								name="tag_name2"
								placeholder="ex) Eclipse, Visual Studio2013, Bootstrap 등" value="<%=prof.getTag_name2()%>">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">관심 분야</label>
						<div class="col-md-9">
							<input class="form-control" id="disabledInput" type="text"
								name="tag_name3"
								placeholder="ex) 백엔드 개발, 프론트엔드 개발, 서버 개발, 디자이너, 기획 등" value="<%=prof.getTag_name3()%>">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Facebook</label>
						<div class="col-md-9">
							<input class="form-control" id="disabledInput" type="text"
								name="prof_facebook" value=<%=prof.getProf_facebook() %> />
						</div>
						<div id="theRemote" class="collapse in">
							<label class="col-md-3 control-label">Github</label>
							<div class="col-md-9">
								<input class="form-control" id="disabledInput" type="text"
									name="prof_github"  value=<%=prof.getProf_github() %> />
							</div>
							<label class="col-md-3 control-label">기타 내 홈페이지</label>
							<div class="col-md-9">
								<input class="form-control" id="disabledInput" type="text"
									name="prof_website"  value=<%=prof.getProf_website() %>/>
							</div>
						</div>
						<div class="col-md-12" align="right">
							<input type="button" value="더 보기" class="btn btn-info common"
								data-toggle="collapse" data-target="#theRemote" />
						</div>
					</div>
					
						<div class="form-group">
							<label class="col-md-3 control-label">Skill</label>
							<div class="col-md-9">
								<div class="col-md-3"><input class="form-control" id="skill" type="text" 
										name = "tag_name4" placeholder="기술명" value=<%=prof.getTag_name4() %> ></div>
								<div class="col-md-7"><input class="form-control" id="score" type="text" 
										name = "prof_skill_level" placeholder="숫자 1~5까지 입력하세요"  value=<%=prof.getProf_skill_level()%>></div>
								<div class="col-md-2"><button type="button" class="btn common" onclick="addSkill()">추가</button></div>
							</div>
							
							<label class="col-md-3 control-label"></label>							
							<div class="col-md-9">
								<div class="col-md-3"><input class="form-control" id="skill2" type="text" 
											name = "tag_name5" placeholder="기술명" value=<%=prof.getTag_name5() %>></div>
								<div class="col-md-7"><input class="form-control" id="score2" type="text" 
											name = "prof_skill_level2" placeholder="숫자 1~5까지 입력하세요" value=<%=prof.getProf_skill_level2()%>></div>
								<div class="col-md-2"><button type="button" class="btn common" onclick="addSkill2()">추가</button></div>
							</div>
							
							<label class="col-md-3 control-label"></label>							
							<div class="col-md-9">
								<div class="col-md-3"><input class="form-control" id="skill2" type="text" 
											name = "tag_name5" placeholder="기술명" value=<%=prof.getTag_name6() %>></div>
								<div class="col-md-7"><input class="form-control" id="score2" type="text" 
											name = "prof_skill_level2" placeholder="숫자 1~5까지 입력하세요" value=<%=prof.getProf_skill_level3()%>></div>
								<div class="col-md-2"><button type="button" class="btn common" onclick="addSkill2()">추가</button></div>
							</div>
			
				
							<br><br>
							<label class="col-md-3 control-label"></label>
								<div class="col-md-9" id="skillList"></div>								
							
					</div>
					
					<div class="form-group text-center buttonDiv">
						<button type="submit" class="btn common" onclick="profalter()">수정하기</button>
						&nbsp;&nbsp;&nbsp;

					</div>
				</form>
			</div>
		</div>
	</section>
	<!--/wrapper -->
</section>
<!-- /MAIN CONTENT -->

