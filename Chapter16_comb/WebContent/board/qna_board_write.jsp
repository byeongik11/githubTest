<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/menu_top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<style type="text/css">
	#registForm {
		width: 500px;
		height: 610px;
		border: 1px solid red;
		margin: auto;
	}
	h2 {
		text-align: center;
	}
	
	table {
	margin: auto;
	width: 450px;
}
	.td_left {
		width: 150px;
		background: orange;
	}
	
	.td_right {
		width: 300px;
		background: skyblue;
	}
	
	#commandCell {
		text-align: center;
	}
</style>
</head>
<body>
	<!-- 게시판 등록 -->
	<section id="registForm">
		<h2>게시판 글등록</h2>
		<form action="boardWritePro.bo" method="post" enctype="multipart/form-data" name="boardForm">
			<table>
				<tr>
					<td class="td_left"><label for="BOARD_NAME">글쓴이</label></td>
					 <td class="td_right">
					 	<input type="text" name="BOARD_NAME" id="BOARD_NAME" required="required">
					 </td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_PASS">비밀번호</label></td>
					 <td class="td_right">
					 	<input type="password" name="BOARD_PASS" id="BOARD_PASS" required="required">
					 </td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_SUBJECT">제목</label></td>
					 <td class="td_right">
					 	<input type="text" name="BOARD_SUBJECT" id="BOARD_SUBJECT" required="required">
					 </td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_CONTENT">내용</label></td>
					 <td>
					 	<textarea id="BOARD_CONTENT" name="BOARD_CONTENT" rows="15" cols="40"  required="required"></textarea>
					 </td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_FILE">파일첨부</label></td>
					 <td class="td_right">
					 	<input type="file" name="BOARD_FILE" id="BOARD_FILE" required="required">
					 </td>
				</tr>
			</table>
		<section id="commandCell">
			<input type="submit" value="등록">&nbsp;&nbsp;
			<input type="reset" value="다시쓰기" />
		</section>
			
		</form>
	</section>
</body>
</html>