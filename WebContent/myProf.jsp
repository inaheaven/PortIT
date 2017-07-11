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

<script src="assets/js/search.js"></script>
<script language="javascript">

// debugger;

// window.onload = function() { location.href="/register"; };

function profRegister(){
	var prof_name = document.getElementById('prof_name').value;
	var prof_nick = document.getElementById('prof_nick').value;
	
	if(prof_name==""){
		alert("이름을 입력해주세요.");
	}
	else if(prof_nick==""){
		alert("닉네임을 입력해주세요.");
	}
	else{
		document.frof_reg.submit();
		alert("프로필이 정상적으로 등록 되었습니다.");
	}
}

function profalter(){
	location.href="/register?cmd=UPDATE"
	alert("프로필이 정상적으로 수정되었습니다.");
}
//skill 추가
var addSkillIndex = 1;
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
	      idx+="<button class='btn common' type='button' onclick='fnDeleteItem("+ addSkillIndex +")'>삭제</button>";
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




//skill 추가
var addSkillIndex2 = 1;
function addSkill2() {
	var skill2 = document.getElementById('skill2').value;
	var score2 = document.getElementById('score2').value;
	
	if(Number(score)>Number("5")){
		alert("숫자의 범위를 확인해주세요.");
		document.getElementById('score2').focus();
		return;
	}
	
	addSkillIndex2++;
	
	var newSpan = document.createElement("span"); 
	
	newSpan.setAttribute("id","item_" + addSkillIndex2);

	//상태바 길이 조정.
	var barSize =0;
	if(Number(score2)==Number("1")){
		barSize=20;
	}else if(Number(score2)==Number("2")){
		barSize=40;
	}else if(Number(score2)==Number("3")){
		barSize=60;
	}else if(Number(score2)==Number("4")){
		barSize=80;
	}else if(Number(score2)==Number("5")){
		barSize=100;
	}

	//상태바 추가
	var idx ="";
		
	idx+="<div class='col-md-3 skill'>"+skill2+"</div>";
	   idx+="<div class='col-md-7'>";
	      idx+="<div class='progress'>";
	         idx+="<div class='progress-bar' role='progressbar' aria-valuenow='45' aria-valuemin='0' aria-valuemax='100' style='width:"+ barSize+"%''>";
	            idx+="<span class='sr-only'>45% Complete</span>";
	            idx+="<span class='sr-only'>45% Complete</span>";
	         idx+="</div>";         
	      idx+="</div>";
	   idx+="</div>";
	   idx+="<div class='col-md-2'>";
	      idx+="<button class='btn common' type='button' onclick='fnDeleteItem("+ addSkillIndex2 +")'>삭제</button>";
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
//skill 추가
var addSkillIndex3 = 1;
function addSkill3() {
	var skill3 = document.getElementById('skill3').value;
	var score3 = document.getElementById('score3').value;
	
	if(Number(score)>Number("5")){
		alert("숫자의 범위를 확인해주세요.");
		document.getElementById('score3').focus();
		return;
	}
	
	addSkillIndex3++;
	
	var newSpan = document.createElement("span"); 
	
	newSpan.setAttribute("id","item_" + addSkillIndex3);

	//상태바 길이 조정.
	var barSize =0;
	if(Number(score3)==Number("1")){
		barSize=20;
	}else if(Number(score3)==Number("2")){
		barSize=40;
	}else if(Number(score3)==Number("3")){
		barSize=60;
	}else if(Number(score3)==Number("4")){
		barSize=80;
	}else if(Number(score3)==Number("5")){
		barSize=100;
	}

	//상태바 추가
	var idx ="";
		
	idx+="<div class='col-md-3 skill'>"+skill3+"</div>";
	   idx+="<div class='col-md-7'>";
	      idx+="<div class='progress'>";
	         idx+="<div class='progress-bar' role='progressbar' aria-valuenow='45' aria-valuemin='0' aria-valuemax='100' style='width:"+ barSize+"%''>";
	            idx+="<span class='sr-only'>45% Complete</span>";
	            idx+="<span class='sr-only'>45% Complete</span>";
	         idx+="</div>";         
	      idx+="</div>";
	   idx+="</div>";
	   idx+="<div class='col-md-2'>";
	      idx+="<button class='btn common' type='button' onclick='fnDeleteItem("+ addSkillIndex3 +")'>삭제</button>";
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

<%--sidenavbar start--%>
<jsp:include page="my.jsp"></jsp:include>
<%--sidenavbar end--%>
<section id="main-content">
	<section class="wrapper site-min-height">
		<div class="col-md-12 mt profreg">
			<div class="profregForm">
				<h3 class="formTitle text-center">프로필 수정</h3>
				<form action="/profReg?cmd=REGISTER" method="post" class="form-horizontal style-form">

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
							<input type="hidden" class="form-control" name="prof_follower" value="0">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">프로필 사진</label>
						<div class="col-md-9">
							<input type="file" class="form-control" name="prof_img"">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">배경 사진</label>
						<div class="col-md-9">
							<input type="file" class="form-control" name="prof_background" ">
						</div>

					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">이름</label>
						<div class="col-md-9">
							<input class="form-control" name="prof_name" type="text"
								placeholder="한글로 입력하세요."" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">닉네임</label>
						<div class="col-md-9">
							<input class="form-control" name="prof_nick" type="text"
								placeholder="영문으로 입력하세요. 이 닉네임은 url로도 쓰입니다.(중복 불가)"">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">소개</label>
						<div class="col-md-9">
							<textarea class="form-control" rows="5" name="prof_intro"
								placeholder="" ></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">관심 언어</label>
						<div class="col-md-9">
							<input class="form-control tagInput" id="Prof_language1" type="text" name="tag_lang">&nbsp;,&nbsp;							
							<input class="form-control tagInput" id="Prof_language2" type="text" name="tag_lang">&nbsp;,&nbsp;							
							<input class="form-control tagInput" id="Prof_language3" type="text" name="tag_lang">&nbsp;,&nbsp;
							<input class="form-control tagInput" id="Prof_language4" type="text" name="tag_lang">&nbsp;,&nbsp;					
							<input class="form-control tagInput" id="Prof_language5" type="text" name="tag_lang">
							<br><br>* 태그로 작성됩니다. ( 예시 : C, JAVA, Python 등 )
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">관심 툴</label>
						<div class="col-md-9">
							<input class="form-control tagInput" id="Prof_tool1" type="text" name="tag_tool">&nbsp;,&nbsp;							
							<input class="form-control tagInput" id="Prof_tool2" type="text" name="tag_tool">&nbsp;,&nbsp;							
							<input class="form-control tagInput" id="Prof_tool3" type="text" name="tag_tool">&nbsp;,&nbsp;
							<input class="form-control tagInput" id="Prof_tool4" type="text" name="tag_tool">&nbsp;,&nbsp;					
							<input class="form-control tagInput" id="Prof_tool5" type="text" name="tag_tool">
							<br><br>* 태그로 작성됩니다. ( 예시 : Window7, OracleDB, Eclipse, Visual Studio2013  등 )
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">관심 분야</label>
						<div class="col-md-9">
							<input class="form-control tagInput" id="Prof_field1" type="text" name="tag_field">&nbsp;,&nbsp;							
							<input class="form-control tagInput" id="Prof_field2" type="text" name="tag_field">&nbsp;,&nbsp;							
							<input class="form-control tagInput" id="Prof_field3" type="text" name="tag_field">&nbsp;,&nbsp;
							<input class="form-control tagInput" id="Prof_field4" type="text" name="tag_field">&nbsp;,&nbsp;					
							<input class="form-control tagInput" id="Prof_field5" type="text" name="tag_field">
							<br><br>* 태그로 작성됩니다. ( 예시 : 백엔드 개발, 프론트엔드 개발, 서버 개발, 디자이너, 기획 등 )
						</div>
					</div>
				
					<div class="form-group">
						<label class="col-md-3 control-label">Facebook</label>
						<div class="col-md-9">
							<input class="form-control" id="disabledInput" type="text"
								name="prof_facebook" />
						</div>
						<div id="theRemote" class="collapse">
							<label class="col-md-3 control-label">Github</label>
							<div class="col-md-9">
								<input class="form-control" id="disabledInput" type="text"
									name="prof_github" />
							</div>
							<label class="col-md-3 control-label">기타 내 홈페이지</label>
							<div class="col-md-9">
								<input class="form-control" id="disabledInput" type="text"
									name="prof_website" />
							</div>
						</div><br><br><br>
						<div class="col-md-12" align="right">
							<button type="button" class="btn common" data-toggle="collapse" data-target="#theRemote" >더보기</button>
						</div>
					</div>
				
					<div class="form-group">
						<label class="col-md-3 control-label">Skill</label>
						<div class="col-md-9">
							<div class="col-md-3"><input class="form-control" id="skill" type="text" name = "tag_name4" placeholder="기술명" ></div>
							<div class="col-md-7"><input class="form-control" id="score" type="text" name = "prof_skill_level" placeholder="숫자 1~5까지 입력하세요" ></div>
							<div class="col-md-2"><button type="button" class="btn common" onclick="addSkill()">등록</button></div>
						</div>
						<div id="Skill" class="collapse">
							<label class="col-md-3 control-label"></label>
							<div class="col-md-9">
								<div class="col-md-3"><input class="form-control" id="skill2" type="text" name = "tag_name5" placeholder="기술명" ></div>
								<div class="col-md-7"><input class="form-control" id="score2" type="text" name = "prof_skill_level2" placeholder="숫자 1~5까지 입력하세요" ></div>
								<div class="col-md-2"><button type="button" class="btn common" onclick="addSkill2()">등록</button></div>
							</div>
							<label class="col-md-3 control-label"></label>
							<div class="col-md-9">
								<div class="col-md-3"><input class="form-control" id="skill3" type="text" name = "tag_name6" placeholder="기술명" ></div>
								<div class="col-md-7"><input class="form-control" id="score3" type="text" name = "prof_skill_level3" placeholder="숫자 1~5까지 입력하세요" ></div>
								<div class="col-md-2"><button type="button" class="btn common" onclick="addSkill3()">등록</button></div>
							</div>
						</div>
							<br><br>
							<label class="col-md-3 control-label"></label>
								<div class="col-md-9" id="skillList"></div>
								
						<div class="col-md-12" align="right">
							<button type="button" class="btn common" data-toggle="collapse" data-target="#Skill">목록 추가</button>
						</div>
					</div>
					<div class="form-group text-center buttonDiv">
						<button type="submit" class="btn common" >등록하기</button>
						&nbsp;&nbsp;&nbsp;
						<button type="reset" class="btn cancel">다시 쓰기</button>


					</div>
				</form>
			</div>
		</div>
	</section>
	<!--/wrapper -->
</section>
<!-- /MAIN CONTENT -->


