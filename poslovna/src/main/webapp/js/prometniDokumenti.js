$(document).on('click','#novi',function(){

	$.ajax({
		url:'/robnaKartica/all',
		type:'GET',
		contentType:'application/json',
		success:function(data){
			$("#roba").empty();
			$.each(data,function(index,kartica){
				$("#roba").append('<option id="'+kartica.id+'">'+kartica.roba.naziv+'</option>')
				
			})
		}
	})
})
$(document).on("click", 'tr', function(event) {
	highlightRow(this)
});

$(document).on('click','#addR',function(e){
	e.preventDefault();
	$.ajax({
		url:'/robnaKartica/'+$("#roba option:selected").attr('id'),
		type:'GET',
		success:function(data){
			
		var row='<tr><td class="roba">'+data.roba.id+'</td><td>'+data.roba.naziv+'</td><td>'+data.roba.jedinicaMere.naziv+'</td>'
			+'<td class="cena">'+data.cena+'</td><td class="kolicina">'+$("#kolicina").val()+'</td><td class="vrednost">'+data.cena*$("#kolicina").val()+'</td>'
			+'<td><button id="deleteStavka">Delete</button></td></tr>';
			$("#stavke").append(row)
			$("#stavke").css("display","block")
		}
	})
})
$(document).on('click','#deleteStavka',function(event){
	event.preventDefault();
	$(this).parent().parent().remove();
})
$(document).on('click','#add',function(event){
	event.preventDefault();
	stavke=[]
	$("#stavke >tbody >tr").each(function(){
		var roba=$(this).find('.roba').html()
		var kolicina=$(this).find('.kolicina').html()
		var cena=$(this).find('.cena').html()
		var vrednost=$(this).find('.vrednost').html()
		
		var data=JSON.stringify({
			"roba":roba,
			"kolicina":kolicina,
			"vrednost":vrednost,
			"cena":cena
		})
		$.ajax({
			url:'/stavkaDokumenta/save',
			type:'POST',
			contentType:'application/json',
			dataType:'json',
			data:data,
			async:false,
			success:function(data){
			
				stavke.push(data.id)
			}
		})
		var dokument=JSON.stringify({
			"vrsta":$("#vrsta option:selected").val(),
			"stavke":stavke
		})
		$.ajax({
			url:'prometniDokument/save',
			type:'POST',
			contentType:'application/json',
			dataType:'json',
			data:dokument,
			async:false,
			success:function(data){
				var row='<tr><td>'+data.redniBr+'</td><td>'+data.vrsta+'</td><td>'+data.status+'</td><td>'+convertDate(data.datumFormiranja)+'</td><td>'+convertDate(data.datumKnjizenja)+'</td><td>'+data.poslovniPartner+'</td></tr>'
				$("#dokumenti").append(row);
				$('#inputModal').modal('toggle');
			
			}
	
		})
	})
})
function convertDate(stamp){
	var a=new Date(stamp);
	var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
	  var year = a.getFullYear();
	  var month = months[a.getMonth()];
	  var date = a.getDate();
	  
	  var time = date + '.' + month + '.' + year+'.' ;
	  return time;
	return date;
}
function draw(){
	$.ajax({
		url:'roba/'
	})
}