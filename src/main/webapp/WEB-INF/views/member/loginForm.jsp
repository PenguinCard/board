<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${path}/resources/css/loginForm.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/resources/js/screen.js"></script>
	<script type="text/javascript" src="${path}/resources/js/loginForm.js"></script>
	<title>로그인</title>
</head>
<body>
	<div class="wrap">
		<table>
			<tbody>
				<tr>
					<td colspan="2" style="position: relative;">
						<a href="${path}/board/"><img src="${path}/resources/img/leaf2.png" id="logo" /></a>
						<h1>로그인</h1>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="text" id="id" name="m_id" placeholder="아이디"/></td>
				</tr>
				<tr>
					<td colspan="2"><input type="password" id="pw" name="m_pw" placeholder="패스워드"/></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" value="로그인" onclick="login('${path}');"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<a href="#">아이디 / 비밀번호 찾기</a> <span style="color: #fff">|</span> <a href="${path}/member/memberForm">회원가입</a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>