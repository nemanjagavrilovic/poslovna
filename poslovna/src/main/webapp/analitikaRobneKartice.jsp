<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
</head>
<body>

	<table border="1">
		<thead>
			<tr>
				<th>R.br.</th>
				<th>Vr.prometa</th>
				<th>Smer</th>
				<th>Količina</th>
				<th>Jedinična cena</th>
				<th>Vrednost ulaza</th>
				<th>Vrednost izlaza</th>
				<th>Ukupna vrednost</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${robnaKartica.analitike}" var="analitika">
				<tr>
					<td>${analitika.rbr }</td>
					<td>${analitika.vrstaPrometa}</td>
					<td>${analitika.smerPrometa}</td>
					<td>${analitika.stavkaDokumenta.kolicina }</td>
					<td>${analitika.stavkaDokumenta.cena}</td>
					<c:if test="${analitika.smerPrometa=='U' }">
						<td>${analitika.stavkaDokumenta.vrednost }</td>
						<td></td>
					</c:if>
					<c:if test="${analitika.smerPrometa=='I' }">
						<td></td>
						<td>${analitika.stavkaDokumenta.vrednost }</td>
					</c:if>
					<td>${analitika.ukupnaVr}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
	</div>
</body>
</html>