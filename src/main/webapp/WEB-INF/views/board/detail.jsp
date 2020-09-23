<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${detail.articleNo}번 게시물</title>
	<link rel="stylesheet" href="${path}/resources/css/detail.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/resources/js/common.js"></script>
</head>
<body>
	<div class="wrap">
		<header>
			<a href="${path}/board/"><img id="logo" src="${path}/resources/img/leaf.png"></a>
			<c:choose>
				<c:when test="${not empty sessionScope.memInfo}">
					<button type="button" onclick="logout('${path}');">로그아웃</button>
					<button type="button" onclick="chgInfo('${path}')">회원정보</button>
				</c:when>
				<c:otherwise>
					<button type="button" onclick="loginForm('${path}');">로그인</button>
					<button type="button" onclick="memberForm('${path}');">회원가입</button>
				</c:otherwise>
			</c:choose>
		</header>
		<h1>${detail.articleNo}번 게시물</h1>
		<table>
			<tbody>
				<tr>
					<td class="name" style="width: 11%;">작성자</td>
					<td style="width: 22%;">
						<p>${detail.writer}</p>
					</td>
					<td class="name" style="width: 11%;">작성일자</td>
					<td style="width: 22%;">${detail.writeDate}
					<td class="name" style="width: 11%;">조회수</td>
					<td style="width: 22%;">${detail.hits}</td>	
				</tr>
				<tr>
					<td colspan="6" class="name">제목</td>
				</tr>
				<tr>
					<td colspan="6">
						<p>${detail.title}</p>
					</td>
				</tr>
				<tr>
					<td colspan="6" class="name">내용</td>
				</tr>
				<tr>
					<td colspan="6">
						<p>
							${detail.contents}
						</p>
					</td>
				</tr>
				<tr>
					<td class="name">미리보기</td>
					<td colspan="5">
						<c:if test="${not empty detail.imageFileName}">
							<img src="${path}/show?img=${detail.imageFileName}&no=${detail.articleNo}" />
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="name">첨부파일</td>
					<td colspan="5">
						<c:if test="${not empty detail.imageFileName}">
							<a href="${path}/download.do?img=${detail.imageFileName}&no=${detail.articleNo}">${detail.imageFileName}</a>
						</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div class="last">
							<button type="button" onclick="location.href='${path}'+'/board/'">목록</button> 
							<c:choose>
								<c:when test="${sessionScope.memInfo.m_id eq detail.writer}">
									<button type="button" onclick="location.href='${path}'+'/board/updateForm/'+'${detail.articleNo}';">수정</button>
								</c:when>
								<c:otherwise>
									<button type="button" onclick="alert('권한이 없습니다.');">수정</button>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${not empty sessionScope.memInfo}">
									<button type="button" onclick="location.href='${path}'+'/board/writeForm/?parentNo='+'${detail.articleNo}';">답글쓰기</button>
								</c:when>
								<c:otherwise>
									<button type="button" onclick="alert('권한이 없습니다.');">답글쓰기</button>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>