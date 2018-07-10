<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<table id="magacini"  border="1" class="table">
			<tr class="header">
				<th>Roba</th>
				<th>Kolicina</th>
				<th>Cena</th>
				<th>Vrednost</th>
			</tr>
			<tbody>
				<c:forEach items="${stavke}" var="stavka">
					<tr>
						<td>${stavka.roba.naziv}</td>
						<td>${stavka.kolicina }</td>
						<td>${stavka.cena }</td>
						<td>${stavka.vrednost }</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
</body>
</html>