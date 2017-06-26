<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="assets/css/account.css" rel="stylesheet">
	<%--sidenavbar start--%>
	<jsp:include page="my.jsp"></jsp:include>
	<%--sidenavbar end--%>

      <section id="main-content">
          <section class="wrapper site-min-height">
          	<div class="col-md-12 mt myAccount">
          		<div class="col-md-8 center">
          			<h3 class="accountTitle text-center">내 계정</h3>
          			<br><br>
          			<form class="form-horizontal" method="post" action="/account?cmd=alter">
	                    <div class="form-group">
	                        <label class="col-md-4 control-label">E-Mail</label>
	                        <div class="col-md-8">
	                            <input type="text" class="form-control" id="userid" name="userid" value="email@email.com">
	                        </div>
	                    </div>
                    	<div align="center">
	                  		<div align="center" class="form-group">
	                              <label class="col-md-4 control-label">Current Password</label>
	                              <div class="col-md-8">
	                                  <input type="password" class="form-control" id="userpw" name="userpw" onchange="fnPasswordConfirm()">
	                              </div>
	                        </div>
	                        <div id="pwconfirm"></div> 
	                  		<div align="center" class="form-group">
	                              <label class="col-md-4 control-label">New Password</label>
	                              <div class="col-md-8">
	                                  <input type="password"  class="form-control" id="newpw" name="newpw">
	                              </div>
	                        </div>
	                        <div align="center" class="form-group">
	                              <label class="col-md-4 control-label">Confirm New Password</label>
	                              <div class="col-md-8">
	                                  <input type="password"  class="form-control" id="newpwcf" name="newpwcf" onchange="fnNewPasswordConfirm()">
	                              </div>
	                        </div>
	                       	<div id="newpwconfirm"></div> 
	                        <div class="modifyBtn">
			               		<div align="center">
			                    	<button type="submit" class="btn">정보 변경</button>
			                	</div>
				            </div>
                	 	 </div>
                   </form>
          		</div>
          	</div>
		</section><!--/wrapper -->
      </section><!-- /MAIN CONTENT -->


  <script>
      //custom select box
	
      function fnPasswordConfirm() {
        	var pw = document.getElementById("newpw").value;
        	var pwcf = document.getElementById("newpwcf").value;
        	var div = document.getElementById("pwconfirm");
        	
        	if(pw != pwcf){
        		div.classList.remove('greenLine');
        		div.classList.add('redLine');
        		div.innerHTML = "&nbsp;<span class='glyphicon glyphicon-remove'></span>&nbsp;&nbsp;새로운 비밀번호가 일치하지 않습니다.";
        	}
        	else {
        		div.classList.remove('redLine');
        		div.classList.add('greenLine');
        		div.innerHTML = "&nbsp;<span class='glyphicon glyphicon-ok'></span>&nbsp;&nbsp;새로운 비밀번호가 일치합니다.";
        	}
        }
      
      function fnNewPasswordConfirm() {
        	var pw = document.getElementById("userpw").value;
        	var pwcf = ""; // db 또는 세션에서 현재 비밀번호 가져오기
        	var div = document.getElementById("pwconfirm");
        	
        	if(pw != pwcf){
        		div.classList.remove('greenLine');
        		div.classList.add('redLine');
        		div.innerHTML = "&nbsp;<span class='glyphicon glyphicon-remove'></span>&nbsp;&nbsp;새로운 비밀번호가 일치하지 않습니다.";
        	}
        	else {
        		div.classList.remove('redLine');
        		div.classList.add('greenLine');
        		div.innerHTML = "&nbsp;<span class='glyphicon glyphicon-ok'></span>&nbsp;&nbsp;새로운 비밀번호가 일치합니다.";
        	}
        }

  </script>

