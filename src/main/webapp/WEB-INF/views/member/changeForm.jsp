<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${path}/resources/css/changeForm.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/resources/js/screen.js"></script>
	<script type="text/javascript" src="${path}/resources/js/changeForm.js"></script>
	<title>회원정보수정</title>	
</head>
<body>
	<div class="wrap">
		<form action="#" name="memberInfo">
			<table>
				<tbody>
					<tr style="position: relative;">
						<td colspan="3">
							<a href="${path}/board/"><img src="${path}/resources/img/leaf2.png" id="logo" /></a>
							<h1>회원정보수정</h1>
						</td>
					</tr>
					<tr>
						<td class="col1">아 이 디</td>
						<td class="col2"><input type="text" name="id" value="${sessionScope.memInfo.m_id}" readonly="readonly"/></td>
						<td class="col3"></td>
					</tr>
					<tr>
						<td class="col1">비밀번호</td>
						<td class="col2"><input type="password" name="pw" id="pw"/></td>
						<td class="col3"></td>
					</tr>
					<tr>
						<td class="col1">이&nbsp;&nbsp;름</td>
						<td class="col2"><input type="text" name="name" value="${sessionScope.memInfo.m_name}" readonly="readonly"/></td>
						<td class="col3"></td>
					</tr>
					<tr>
						<td class="col1">휴대번호</td>
						<td class="col2" colspan="2">
							<span> 
								<select name="phone1" >
									<option value="010">010</option>
									<option value="019">019</option>
									<option value="011">011</option>
									<option value="016">016</option>
									<option value="017">017</option>
								</select> -
								<input type="text" name="phone2" id="phone2"/> -
								<input type="text" name="phone3" id="phone3"/>
							</span>
						</td>
					</tr>
					<tr>
						<td class="col1">이 메 일</td>
						<td class="col2" colspan="2"><span><input id="email1" type="text" name="email1"/> @ <input id="email2" type="text" name="email2"/></span></td>
					</tr>
					<tr>
						<td id="last" colspan="3">
							<input class="chk" type="button" value="취소" onclick="backToList('${path}')">
							<input class="chk" type="submit" value="수정" onclick="updateMember('${path}')">
							<input class="chk" type="button" style="color: red; font-weight: bold;"value="탈퇴" onclick="deleteMember('${path}', '${sessionScope.memInfo.m_num}');"/>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>