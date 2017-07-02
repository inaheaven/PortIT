<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="login-page">
	<div class="container">
		<div class="col-md-6">
			<div class="intro">
				<h3>PortIT</h3>
				<br>
				<p>여러분의 포트폴리오를 만들어 공유해 보세요.</p>
			</div>
		</div>
		<div class="col-md-6">
			<form class="login" method="post" action="/login">
				<div class="login-wrap">
					<input type="text" name="userid" class="form-control" placeholder="Email ID" autofocus="autofocus"> <br> 
					<input type="password" name="userpw" class="form-control" placeholder="Password"> <br>
					<br>
					<br> 
					<span><input type="submit" class="btn signin" value="Sign In">&nbsp;&nbsp;&nbsp;</span> 
					<span><input type="button" class="btn signup" value="Sign Up" data-toggle="modal" data-target="#signUpModal"></span>
				</div>
			</form>
		</div>

		<!-- Sign Up Modal -->
		<div class="modal fade" id="signUpModal" tabindex="-1" role="dialog"
			aria-labelledby="signUpModalLabel" aria-hidden="true" data-backdrop="static">
			<div class="modal-dialog">
				<div class="mdoal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="signUpModalLabel">회원 가입</h4>
					</div>
					<form id="join" method="post" action="/join">
						<div class="modal-body">						
							<div class="form-group">
								<label class="control-label">Email ID</label><br> 
								<input type="text" name="userid" id="userid" class="form-control">
							</div>
							<div class="form-group">
								<label class="control-label">Password</label><br> 
								<input type="password" name="userpw" id="userpw" class="form-control">
							</div>
							<div class="form-group">
								<label class="control-label">Password Confirm</label><br> 
								<input type="password" name="userpwcf" id="userpwcf"
									class="form-control" onkeyup="fnPasswordConfirm()">
							</div>
							<div id="pwconfirm"></div>						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn signup"  data-toggle="modal" data-target="#signUpModal" onclick="fnSubmit()">Sign Up</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>