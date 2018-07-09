<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/jquery.min.js"> </script>
		<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/prometniDokumenti.js"> </script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/functions.js"> </script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/design.css">
	
</head>
<body>
	<div>
	<ul>
		<li class="group-start" onclick="find()"id="novi"><a href="#inputModal" data-toggle="modal" data-target="#inputModal">Novi dokument</a></li>
		
	</ul>
		
	
	</div>
	<div class="modal"  id="inputModal"  role="dialog">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Dodaj magacin</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div id="robaSelection" style="display:none;">
					<table id="robaT" border=1>
						<thead>
						<tr>
							<th>Id</th>
							<th>Naziv</th>
							<th>Grupa</th>
							<th>Jedinica mere</th>
							<th>Cena</th>
						</tr>
						</thead>
						<tbody>
						
						</tbody>
					</table>
					<button id="select"> Izaberi</button>
				</div> 
			<div id="form">
				      <form id="inputForm" style="display:block;">
		      <div class="modal-body">
					<input type="hidden" name="id" id="idD" />
				
				<p>
					<label for="Vrsta">Vrsta</label> <select name="vrsta"id="vrsta" >
							<option>PR</option>
							<option>OT</option>
							<option>MM</option>
						</select>
				</p>
					<p>
					<label for="Smer">Smer</label> <select name="smer" id="smer" >
							<option>U</option>
							<option>I</option>
						</select>
				</p>
				<p>
					
					
				</p>		
				<fieldset>
					<legend>Stavke dokumenta</legend>
					<table border="1"  id="stavke" style="display:none;">
						<thead>
							<tr>
								<th>Roba</th>
								<th>Kolicina</th>
								<th>Cena</th>
								<th>Vrednost</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<label for="roba">Roba</label> <select id="roba" name="roba"></select>
						<button id="addR">Add</button>
						<button id="deleteR">Delete</button>
			
					<a  href="#choose" data-toggle="modal" data-target="#choose" onclick="draw()">...</a>
			
				</fieldset>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Zatvori</button>
		        <button type="button" class="btn btn-primary" id="add">Potvrda</button>
		        <button type="button" class="btn btn-primary" style="display:none" id="editButton">Izmeni</button>
		        <button type="button" class="btn btn-primary" style="display:none" id="searchButton">Pretrazi</button>
		        
		      </div>
		      </form>
		      </div>
	
		    </div>
		  </div>
		</div>
</body>
=======
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/jquery.min.js"> </script>
		<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/prometniDokumenti.js"> </script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/functions.js"> </script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/design.css">
	
</head>
<body>
	<div>
	<ul>
		<li class="group-start" onclick="find()"id="novi"><a href="#inputModal" data-toggle="modal" data-target="#inputModal">Novi dokument</a></li>
		
	</ul>
		<table id="dokumenti" border="1">
			<thead>
			<tr>
				<th>R.br.</th>
				<th>Vrsta</th>
				<th>Status</th>
				<th>Datum formiranja</th>
				<th>Datum knjizenja</th>
				<th>Poslovni partner</th>
			</tr>
			</thead>
			<tbody></tbody>
		</table>
	
	</div>
	<div class="modal"  id="inputModal"  role="dialog">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Dodaj magacin</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div id="robaSelection" style="display:none;">
					<table id="robaT" border=1>
						<thead>
						<tr>
							<th>Id</th>
							<th>Naziv</th>
							<th>Grupa</th>
							<th>Jedinica mere</th>
							<th>Cena</th>
						</tr>
						</thead>
						<tbody>
						
						</tbody>
					</table>
					<button id="select"> Izaberi</button>
				</div> 
			<div id="form">
				      <form id="inputForm" style="display:block;">
		      <div class="modal-body">
					<input type="hidden" name="id" id="idD" />
				
				<p>
					<label for="Vrsta">Vrsta</label> <select name="vrsta"id="vrsta" >
							<option>PR</option>
							<option>OT</option>
							<option>MM</option>
						</select>
				</p>
				<fieldset>
					<legend>Stavke dokumenta</legend>
					<table border="1"  id="stavke" style="display:none;">
						<thead>
							<tr>
								<th>Sifra</th>
								<th>Roba</th>
								<th>Jedinica mere</th>
								<th>Cena</th>
								<th>Kolicina</th>
								<th>Vrednost</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<label for="roba">Roba</label> <select id="roba" name="roba"></select>
					Kolicina:<input type="text" id="kolicina">
						<button id="addR">Add</button>
			
					<a  href="#choose" data-toggle="modal" data-target="#choose" onclick="draw()">...</a>
			
				</fieldset>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Zatvori</button>
		        <button type="button" class="btn btn-primary" id="add">Potvrda</button>
		        <button type="button" class="btn btn-primary" style="display:none" id="editButton">Izmeni</button>
		        <button type="button" class="btn btn-primary" style="display:none" id="searchButton">Pretrazi</button>
		        
		      </div>
		      </form>
		      </div>
	
		    </div>
		  </div>
		</div>
</body>
>>>>>>> refs/remotes/origin/master
</html>