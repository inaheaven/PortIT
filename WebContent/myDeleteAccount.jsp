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
          			<h3 class="accountTitle text-center">계정 탈퇴</h3>
          			<br><br>
          			<form class="form-horizontal" method="post" action="/empty/account?cmd=delete">
          				<div class="deleteInfo center text-center">
          					계정을 삭제하시면, 모든 정보들이 삭제됩니다.<br>
          					정말 삭제하시겠습니까?
          				</div>
	                    <div class="form-group">
	                        <label class="col-md-4 control-label">Password</label>
	                        <div class="col-md-6">
	                            <input type="password" class="form-control" id="userdeletepw" name="userdeletepw" onchange="fnPasswordConfirm()">
	                        </div>
	                    </div>
	                    <div id="pwconfirm"></div> 
                        <div class="deleteBtn">
		               		<div align="center">
		                    	<button type="submit" class="btn">계정 삭제</button>
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

  </script>

