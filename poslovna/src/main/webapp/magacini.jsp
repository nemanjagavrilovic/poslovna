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
			<ul>
				<li class="group-start"><a href="searchModal" id="search" data-toggle="modal" data-target="#inputModal"><img src="../images/search.gif" /></a></li>
				<li><a href="#" id="refresh"><img src="../images/refresh.gif" /></a></li>
				<li><a href="#" id="help"><img src="../images/help.gif" /></a></li>
				<li class="group-start"><a id="first"><img src="../images/first.gif" /></a></li>
				<li><a id="prev"><img src="../images/prev.gif" /></a></li>
				<li><a id="next"><img src="../images/next.gif" /></a></li>
				<li><a id="last"><img src="../images/last.gif" /></a></li>
				<li class="group-start" onclick="find()"id="novi"><a href="#inputModal" data-toggle="modal" data-target="#inputModal"><img src="../images/add.gif" /></a></li>
				<li class="group-start"><a  href="../prometniDokument/all" id="nextform"><img src="../images/nextform.gif" /></a></li>
		</ul>
		
	</ul>
		<table id="magacini" class="table">
			<tr class="header">
				<th>Naziv</th>
				<th width=20px></th>
				<th width=20px></th>
			</tr>
			
				<c:forEach items="${magacini}" var="magacin">
					<tr>
						<td class="idCell">${magacin.id }</td>
						<td class="naziv">${magacin.naziv }</td>
						<td ><a class="edit" href="../magacin/${magacin.id}">Edit</a></td>
						<td ><a class="delete"href="../magacin/delete/${magacin.id}">Delete</a></td>
						<td><a href="../prometniDokument/all/${magacin.id}">Dokumenti</a></td>
					</tr>
				</c:forEach>
				
		</table>
		<div class="panel">
			<p>
				
					<input type="hidden" name="id" id="id" />
					<p>
						<label for="naziv">Naziv</label> <input type="text" name="naziv"
							id="naziv" disabled />
					</p>
					
			</p>
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