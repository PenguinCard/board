<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 목록</title>
	<link rel="stylesheet" href="${path}/resources/css/boardList.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/resources/js/common.js"></script>
	<script type="text/javascript" src="${path}/resources/js/boardList.js"></script>
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
		<div class="clear"></div>
		<h1>게시글 목록</h1>
		<table>
			<tbody>
				<tr>
					<td class="row1" colspan="5">
						<ul>
							<li class="search">
								<select name="item">
									<option value="title">제목</option>
									<option value="writer">작성자</option>
									<option value="contents">내용</option>
								</select>
							</li>
							<li class="search"><input type="text" id="keyword" /></li>
							<li class="search"><button type="button" onclick="search('${path}');">검색</button></li>
						</ul>
					</td>
				</tr>
				<tr>
					<th class="col1" style="color: #fff">게시글 번호</th>
					<th class="col2" style="text-align: center;">게시글 제목</th>
					<th class="col3" style="color: #fff">작성자</th>
					<th class="col4" style="color: #fff">작성일시</th>
					<th class="col5" style="color: #fff">조회수</th>
				</tr>
				<c:choose>
					<c:when test="${empty boardList}">
						<tr>
							<td class="col" colspan="5"><div id="reload">등록된 글이 없습니다.</div></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="data" items="${boardList}">
							<tr>
								<td class="col1">${data.articleNo}</td>
								<td class="col2">
									<a href="${path}/board/detailBoard?articleNo=${data.articleNo}">
										<span></span>
										<c:if test="${data.layer gt 0}">
											<c:forEach begin="1" end="${data.layer}">
												RE:
											</c:forEach>
										</c:if>
										${data.title}
									</a>
								</td>
								<td class="col3">${data.writer}</td>
								<td class="col4">${data.writeDate}</td>
								<td class="col5">${data.hits}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<section>
			<!-- Paging Area -->
			<article>
				<%-- 페이지가 5이상일 때 이전페이지 화살표 띄우는 조건 --%>
				<c:if test="${sessionScope.endPage gt 5}">
					<%-- 맨처음 페이지로 이동 --%>
					<button type="button" class="count" onclick="move('${path}', '1', '${item}', '${value}')">&lt;&lt;</button>
					<c:choose>
						<%-- 현재 페이지가 1보다 작아질 때 --%>
						<c:when test="${sessionScope.currentPage le 1}">
							<%--알림창을 띄우고 현재페이지의 이동을 막음 --%>
							<button type="button" class="count" onclick="alert('페이지의 처음입니다.');">&lt;</button>
						</c:when>
						<c:otherwise>
							<button type="button" class="count" onclick="move('${path}', '${sessionScope.currentPage-1}',  '${item}', '${value}');">&lt;</button>
						</c:otherwise>
					</c:choose>
				</c:if>
				<div id="area">
					<c:choose>
						<%-- 시작페이지부터 시작페이지+4까지 표시 (5개를 표시하므로) --%>
						<c:when test="${sessionScope.endPage ge sessionScope.startPage+4}">
							<c:forEach var="count" begin="${sessionScope.startPage}" end="${sessionScope.startPage+4}">
								<c:choose>
									<%-- 현재페이지의 글씨를 두껍게 표시 --%>
									<c:when test="${count eq sessionScope.currentPage}">
										<button type="button" style="font-weight: bold;" class="count" onclick="move('${path}', '${count}', '${item}', '${value}')">${count}</button>
									</c:when>
									<c:otherwise>
										<button type="button" class="count" onclick="move('${path}', '${count}', '${item}', '${value}')">${count}</button>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach var="count" begin="${sessionScope.startPage}" end="${sessionScope.endPage}">
								<c:choose>
									<c:when test="${count eq sessionScope.currentPage}">
										<button type="button" style="font-weight: bold;" class="count" onclick="move('${path}', '${count}', '${item}', '${value}')">${count}</button>
									</c:when>
									<c:otherwise>
										<button type="button" class="count" onclick="move('${path}', '${count}', '${item}', '${value}')">${count}</button>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
				<c:if test="${sessionScope.endPage gt 5}">
					<c:choose>
						<c:when test="${sessionScope.currentPage ge sessionScope.endPage}">
							<button type="button" class="count" onclick="alert('페이지의 마지막입니다');">&gt;</button>
						</c:when>
						<c:otherwise>
							<button type="button" class="count" onclick="move('${path}', '${sessionScope.currentPage+1}', '${item}', '${value}');">&gt;</button>
						</c:otherwise>
					</c:choose>
					<button type="button" class="count" onclick="move('${path}', '${sessionScope.endPage}', '${item}', '${value}')">&gt;&gt;</button>
				</c:if>
			</article>
			<c:choose>
				<c:when test="${not empty sessionScope.memInfo}">
					<button type="button" style="float: right;" onclick="location.href='${path}'+'/board/writeForm';">글쓰기</button>
				</c:when>
				<c:otherwise>
					<button type="button" style="float: right;" onclick="alert('글쓰기 권한이 없습니다.');">글쓰기</button>
				</c:otherwise>
			</c:choose>
		</section>
	</div>
</body>
</html>