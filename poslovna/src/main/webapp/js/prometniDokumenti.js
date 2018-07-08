$(document).on('click','#novi',function(){

	$.ajax({
		url:'/robnaKartica/all',
		type:'GET',
		contentType:'application/json',
		success:function(data){
			$("#roba").empty();
			$.each(data,function(index,kartica){
				$("#roba").append('<option id="'+kartica.roba.id+'">'+kartica.roba.naziv+'</option>')
				
			})
		}
	})
})