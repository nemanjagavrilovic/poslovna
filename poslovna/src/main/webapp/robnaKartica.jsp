<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
	<c:import url = "./navbar.jsp"/>
		<a href='../magacin/'+${robnaKaritca.magacin.id}>${robnaKaritca.magacin.naziv}</a>

		<div>
			<fieldset>
				<legend>Robna kartica ${robnaKartica.roba.naziv}:</legend>
				Godina:${robnaKartica.poslovnaGodina }
				Cena:${robnaKartica.cena }
			</fieldset>
		</div>
		<div>
		
			<fieldset>
				<legend>Stanje:</legend>
				<label>Pocetno stanje kolicinski:${robnaKartica.pocetnoStanjeKol }</label>&nbsp&nbsp&nbsp
				
				<label>Pocetno stanje vrednost:${robnaKartica.pocetnoStanjeVr }</label><br>
				<label>Promet ulaza kolicinski:${robnaKartica.prometUlazaKol }</label>&nbsp&nbsp&nbsp
				<label>Promet ulaza  vrednost:${robnaKartica.prometUlazaVr }</label><br>
				<label>Promet izlaza koicinski:${robnaKartica.prometIzlazaKol }</label>&nbsp&nbsp&nbsp
				<label>Promet izlaza vrednost:${robnaKartica.prometIzlazaVr }</label><br>
				<label>Ukupno kolicina:${robnaKartica.ukupnaKol }</label>&nbsp&nbsp&nbsp
				<label>Ukupno vrednost:${robnaKartica.ukupnaVr }</label>
				
				
			</fieldset>
		</div>
		<div>
			
		</div>
		<a href='/robneKaritca/'+${robnaKartica.id}>Analitika</a>
</body>
</html>