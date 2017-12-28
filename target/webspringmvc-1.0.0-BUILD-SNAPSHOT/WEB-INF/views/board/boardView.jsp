<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css" />





<title>Insert title here</title>
<script type="text/javascript">
	function fn_list() {
		location.href = "${pageContext.request.contextPath}/board/boardList";

	}
	function fn_delete(seqNo) {
		location.href = "${pageContext.request.contextPath}/board/boardDelete?bo_seq_no=" + seqNo;
	}
	function fn_writeForm(seqNo) {
		location.href = "${pageContext.request.contextPath}/board/boardForm?bo_seq_no="+seqNo;
	}
</script>

</head>
<body>


	<div class="container">

		<h3>글쓰기</h3>

		<table>



			<table class="table">

				<tr>
					<td width="15%">제목</td>
					<td>${board.bo_title}</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>${board.bo_writer_name}</td>
				</tr>
				<tr>
					<td>공개여부</td>
					<td>${board.bo_open_yn =='Y' ? '공개' : '비공개'}</td>
				</tr>
				<tr>
					<td>첨부파일</td>


					<td><c:forEach var="fileItem" items="${board.fileItemList}">
							<div>
								<a
									href="${pageContext.request.contextPath}/common/download?file_seq_no=${fileItem.file_seq_no}">${fileItem.file_name}
									${fileItem.file_fancy_size} </a>
							</div>
						</c:forEach></td>
				</tr>
				<tr>
					<td colspan="2" style="white-space: pre-wrap">${board.bo_content}</td>
				</tr>
			</table>



			<p align="center">




				<c:if
					test="${not empty LOGIN_USER and LOGIN_USER.mem_id == board.bo_writer}">

					<input type="button" value="수정" class="btn btn-primary"
						onclick="fn_writeForm(${board.bo_seq_no});">
					<input type="button" value="삭제" class="btn btn-primary"
						onclick="fn_delete(${board.bo_seq_no});">
				</c:if>
				<input type="button" value="목록" class="btn btn-primary"
					onclick="fn_list();">
		</table>





	</div>









</body>
</html>