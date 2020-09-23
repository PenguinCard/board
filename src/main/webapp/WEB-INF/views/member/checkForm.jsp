<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${path}/resources/css/checkForm.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/resources/js/screen.js"></script>
	<script type="text/javascript" src="${path}/resources/js/checkForm.js"></script>
	<title>정보확인</title>	
</head>
<body>
	<div class="wrap">
		<table>
			<tbody>
				<tr>
					<td colspan="2"><h1 style="font-size: 16px;">본인인증을 위해 비밀번호를 입력해주세요</h1></td>
				</tr>
				<tr>
					<td colspan="2"><input type="password" id="pw" placeholder="비밀번호"/></td>
				</tr>
				<tr>
					<td>
						<input type="button" value="뒤로" onclick="backToList('${path}')"/>
					</td>
					<td>
						<input type="button" value="확인" onclick="checkPassword('${path}','${sessionScope.memInfo.m_pw}')"/>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>