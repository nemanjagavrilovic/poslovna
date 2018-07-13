<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type="text/javascript"	src="${pageContext.request.contextPath}/jquery.min.js"> </script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript"	src="${pageContext.request.contextPath}/js/functions.js"> </script>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/design.css">
</head>
<body>
<c:import url = "./navbar.jsp"/>
	<h2>Analitike robne kartice</h2>
	<h4>Preduzece: ${ robnaKartica.magacin.preduzece.naziv }</h4>
	<h4>Magacin: ${ robnaKartica.magacin.naziv }</h4>
	<table border="1">
		<thead>
			<tr>
				<th>R.br.</th>
				<th>Vr.prometa</th>
				<th>Smer</th>
				<th>Količina</th>
				<th>Jedinična cena</th>
				<th>Saldo</th>
				<th>Vrednost ulaza</th>
				<th>Vrednost izlaza</th>
				<th>Ukupna vrednost</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ analitikeSortirane }" var="analitika">
				<tr>
					<td>${analitika.rbr }</td>
					<td>${analitika.vrstaPrometa}</td>
					<td>${analitika.smerPrometa}</td>
					<td>${analitika.stavkaDokumenta.kolicina }</td>
					<td>${analitika.stavkaDokumenta.cena}</td>
					<td>${analitika.ukupnaKol}</td>
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