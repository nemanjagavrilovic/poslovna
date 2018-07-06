<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript"	src="${pageContext.request.contextPath}/jquery.min.js"> </script>
		<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/magacini.js"> </script>
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
		<li class="group-start" onclick="find()"id="novi"><a href="#inputModal" data-toggle="modal" data-target="#inputModal">Novi magacin</a></li>
		
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
		      <div id="selection" style="display:none;">
					<table id="radniciT" border=1>
						<tr>
							<th>Id</th>
							<th>Ime</th>
							<th>Prezime</th>
						</tr>
					</table>
					<button id="select"> Izaberi</button>
				</div> 
			<div id="form">
				      <form id="inputForm" style="display:block;">
		      <div class="modal-body">
					<input type="hidden" name="id" id="idD" />
				
				<p>
					<label for="naziv">Naziv</label> <input type="text" name="name"
						id="nazivM" />
				</p>
				<p>
					<label for="radnici">Radnici</label> <select id="radnici" name="radnici"></select>
					<button id="addR">Add</button>
					<button id="deleteR">Delete</button>
					
					<select id="izabraniRadnici" ></select>
					
					<a  href="#choose" data-toggle="modal" data-target="#choose" onclick="draw()">...</a>
				</p>		
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
</html>