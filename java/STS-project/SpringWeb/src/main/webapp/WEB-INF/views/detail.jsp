<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail Page</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<div align="center" class="body">
		<h2>상품 상세 화면</h2>
		<table>
			<tr>
				<td><img src="../img/${item.pictureUrl}"></td>
				<td align="center">
					<table>
						<tr height="50">
							<td width="80">상품명</td>
							<td width="160">${item.itemname}</td>
						</tr>
						<tr height="50">
							<td width="80">가격</td>
							<td width="160">${item.price}원</td>
						</tr>
						<tr height="50">
							<td width="80">비고</td>
							<td width="160">${item.description}</td>
						</tr>
						<tr>
							<td colspan="2" align="center" width="240"><a href="../">■목록으로 돌아가기</a></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>