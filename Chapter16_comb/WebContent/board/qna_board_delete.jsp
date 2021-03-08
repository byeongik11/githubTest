<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/menu_top.jsp" %>
<% 
	String nowPage = (String)request.getAttribute("page");
	int board_num = (Integer)(request.getAttribute("board_num"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<style type="text/css">
	#passForm {
		width: 400px;
		margin: auto;
		border: 1px solid orange;
	}
</style>
<script type="text/javascript">
function button_event(){
	if (confirm("정말 삭제하시겠습니까??") == true){    //확인
	    document.form.submit();
	}else{   //취소
	    return;
	}
	}

</script>
</head>
<body>
	<section id="passForm">
		<form action="boardDeletePro.bo?board_num=<%=board_num %>" method="post" name="deleteForm">
			<input type="hidden" name="page" value="<%=nowPage%>" />
			<table>
				<tr>
					<td>
						<label>글 비밀번호 : </label>
					</td>
					<td>
						<input name="BOARD_PASS" type="password">
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="삭제" />
						&nbsp;&nbsp;
						<input type="button" value="돌아가기" onclick="javascript:history.go(-1)" />
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>