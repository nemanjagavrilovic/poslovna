<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"	src="${pageContext.request.contextPath}/jquery.min.js"> </script>
		<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/stavkePrometnogDokumenta.js"> </script>
	
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/design.css">
	
</head>

<body>
<c:import url = "./navbar.jsp"/>
		<ul>
				<li class="group-start"><a href="#searchModal" data-toggle="modal" data-target="#searchModal"><img src="../../images/search.gif" /></a></li>
				<li><a href="#" id="refresh"><img src="../../images/refresh.gif" /></a></li>
				<li><a href="#" id="help"><img src="../../images/help.gif" /></a></li>
				<li class="group-start"><a id="first"><img src="../../images/first.gif" /></a></li>
				<li><a id="prev"><img src="../../images/prev.gif" /></a></li>
				<li><a id="next"><img src="../../images/next.gif" /></a></li>
				<li><a id="last"><img src="../../images/last.gif" /></a></li>
				<li class="group-start" onclick="find()"id="novi"><a href="#inputModal" data-toggle="modal" data-target="#inputModal"><img src="../../images/add.gif" /></a></li>
				<li class="group-start"><a href="#" id="nextform"><img src="../../images/nextform.gif" /></a></li>
		</ul>
	<table id="stavke"  border="1" class="table">
			<tr class="header">
				<th>Roba</th>
				<th>Kolicina</th>
				<th>Cena</th>
				<th>Vrednost</th>
			</tr>
			<tbody>
				<c:forEach items="${stavke}" var="stavka">
					<tr>
						<td class="roba">${stavka.roba.naziv}</td>
						<td class="kolicina">${stavka.kolicina }</td>
						<td class="cena">${stavka.cena }</td>
						<td class="vrednost">${stavka.vrednost }</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
		<div class="panel">
			<p>
				<form>
					<input type="hidden" name="id" id="id" />
					<p>
						<label for="roba">Roba</label> <input type="text" name="roba"
							id="robaV" disabled />
					</p>
					<p>
						<label for="kolicina">Kolicina</label> <input type="text" name="kolicina"
							id="kolicinaV" disabled />
					</p>
					
					<p>
						<label for="cena">Cena</label> <input type="text" name="cena"
							id="cenaV" disabled />
					</p>
					<p>
						<label for="vrednost">Vrednost</label> <input type="text" name="vrednost"
							id="vrednostV" disabled />
					</p>
					
				</form>
			</p>
		</div>
		<div class="modal"  id="inputModal"  role="dialog">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Pretraga</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
			<div id="form">
				      <form id="inputForm" style="display:block;">
		      <div class="modal-body">
				
				<p>
					<label>Roba:</label>
					<select  id="roba"></select>
				</p>
				<p>
					<label>Kolicina:</label>
				
					<input type="text" id="kolicina">
				</p>
				<p>
					<label>Cena:</label>
				
					<input disabled type="text" id="cena">
				</p>
				<p>
					<label>Vrednost:</label>
				
					<input type="text" disabled id="vrednost">
				</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Zatvori</button>
		        <button type="button" class="btn btn-primary"  id="add">Potvrda</button>
		        
		      </div>
		      </form>
		      </div>
	
		    </div>
		  </div>
		</div>
		<div class="modal"  id="searchModal"  role="dialog">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Pretraga</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
			<div id="form">
				      <form id="inputForm" style="display:block;">
		      <div class="modal-body">
				
				<p>
					<label>Roba:</label>
					<input type="text" id="robaS">
				</p>
					<label>Kolicina:</label>
				
					<input type="text" id="kolicinaS">
				<p>
					<label>Cena:</label>
				
					<input type="text" id="cenaS">
			
				</p>
				<p>
					<label>Vrednost:</label>
				
					<input type="text" id="vrednostS">
				</p>
					
				
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Zatvori</button>
		        <button type="button" class="btn btn-primary"  id="searchButton">Pretrazi</button>
		        
		      </div>
		      </form>
		      </div>
	
		    </div>
		  </div>
		</div>
</body>
</html>