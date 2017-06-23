<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="login-page">
	<div class="container">
		<div class="col-md-6">
			<div class="intro">
				<h3>안녕하세요! Port IT 입니다.</h3>
				<br>
				<p>
					사이트 소개 소개소개소개소개소개.<br /> 사이트 소개 소개소개소개소개소개.<br /> 사이트 소개
					소개소개소개소개소개.<br /> 사이트 소개 소개소개소개소개소개.<br /> 사이트 소개 소개소개소개소개소개.<br />
				</p>
			</div>
		</div>
		<div class="col-md-6">
			<form class="login" method="post" action="">
				<div class="login-wrap">
					<input type="text" name="userid" class="form-control"
						placeholder="Email ID" autofocus="autofocus"> <br> <input
						type="password" name="userpw" class="form-control"
						placeholder="Password"> <br>
					<div class="find">
						<a href="">아이디 / 비밀번호 찾기</a>
					</div>
					<br> <span><input type="submit" class="btn signin"
						value="Sign In">&nbsp;&nbsp;&nbsp;</span> <span><input
						type="button" class="btn signup" value="Sign Up"
						data-toggle="modal" data-target="#signUpModal"></span>
				</div>
			</form>
		</div>

		<!-- Sign Up Modal -->
		<div class="modal fade" id="signUpModal" tabindex="-1" role="dialog"
			aria-labelledby="signUpModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="mdoal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="signUpModalLabel">회원 가입</h4>
					</div>
					<form method="post" action="">
						<div class="modal-body">						
							<div class="form-group">
								<label class="control-label">Email ID</label><br> <input
									type="text" name="userid" class="form-control">
							</div>
							<div class="form-group">
								<label class="control-label">Password</label><br> <input
									type="password" name="userpw" id="userpw" class="form-control">
							</div>
							<div class="form-group">
								<label class="control-label">Password Confirm</label><br> <input
									type="password" name="userpwcf" id="userpwcf"
									class="form-control" onchange="fnPasswordConfirm()">
							</div>
							<div id="pwconfirm" class=""></div>						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn signup">Sign Up</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>