$(document).ready(
		function(){
				$("#first").click(function(){
					goFirst()
				 })
				
				$("#next").click(function(){
					goNext()
				 })
				
				$("#last").click(function(){
					goLast()
				})
				
				$("#prev").click(function(){
					goPrevious()
				})
})
$(document).on('click','#novi',function(){
	var	url=window.location.href 
	url=url.substring(url.lastIndexOf('/')+1,url.length)
	$.ajax({
		url:'/robnaKartica/allFromStavka/'+url,
		type:'GET',
		contentType:'application/json',
		async:false,
		success:function(data){
			$("#roba").empty();
			$.each(data,function(index,kartica){
				$("#roba").append('<option id="'+kartica.id+'">'+kartica.roba.naziv+'</option>')
				
			})
		}
	})
})
function sync(item){
    roba = item.find(".roba").html()
    kolicina = item.find(".kolicina").html()
    cena = item.find(".cena").html()
    vrednost = item.find(".vrednost").html()
    
    $("#robaV").val(roba);
    $("#kolicinaV").val(kolicina);
    $("#cenaV").val(cena);
    $("#vrednostV").val(vrednost);
}	
$(document).on("click", 'tr', function(event) {
	highlightRow(this)
});
function highlightRow(row){
	 //ne reagujemo na klik na header tabele, samo obicne redove
	 //this sadrzi red na koji se kliknulo
	if(!$(row).hasClass("header")){
 		//klasa highlighted postavlja pozadinu na plavu
 		//njenim dodavanjem ili uklanjanjem oznacavamo da neki red
 		//dobija, odnosno gubi selekciju
 		//uklanjamo sa trenutno selektovanog
 		$(".highlighted").removeClass("highlighted");
 		//dodajemo na novi selektovani
   	$(row).addClass("highlighted");
   	//pozivamo sinhronizaciju, prosledjujemo dati red
   	sync($(row));
   }
}

function goFirst(){
	//indeksi pocinju od 1
	//prvi red je header, zato se trazi drugo dete
	item = $("table tr:nth-child(1)");
	$(".highlighted").removeClass("highlighted");
	item.addClass("highlighted");
	sync(item);
}

function goLast(){
	var count = $("#stavke >tbody >tr").length-1;
	item = $("table tr:nth-child("+count+")");
	$(".highlighted").removeClass("highlighted");
	item.addClass("highlighted");
	sync(item);
}
function goNext(){
	highlighted = $(".highlighted");
	//nalazi poziciju trazanog u okviru selektovane selekcije
	//indeksi pocinju od 0
	var count = $("#stavke >tbody >tr").length;
	if (count == 0)
		return;
	index =  $("tr").index(highlighted);
	if (index < 0)
		return;
	//ako smo na poslednjem, predji na prvi (odnosno drugi red, preskacuci header)
	selectChild = 1;
	if(index!=0)
	index-=1
	//inace
	if(index==count-2){
		selectChild = 1;
	}
	else if (index <= count - 1)
		selectChild = index + 2; 
	item = $("tr:nth-child(" + selectChild + ")");
	$(".highlighted").removeClass("highlighted");
	item.addClass("highlighted");
   sync(item);
}
function goPrevious(){
	highlighted = $(".highlighted");
	var count = $("#stavke >tbody >tr").length-1;
	if (count == 0)
		return;
	index =  $("tr").index(highlighted);
	if (index < 0)
		return;
	selectChild = count;
	if (index >=  1)
		selectChild = index ;
	
	item = $("tr:nth-child(" + selectChild + ")");
	$(".highlighted").removeClass("highlighted");
	item.addClass("highlighted");
	sync(item);
}
$(document).on('click','#searchButton',function(){
	var data=JSON.stringify({
		"roba":$("#robaS").val(),
		"kolicina":$("#kolicinaS").val(),
		"cena":$("#cenaS").val(),
		"vrednost":$("#vrednostS").val()
	})
	$.ajax({
		url:'../stavkaDokumenta/search',
		type:'POST',
		dataType:'json',
		contentType:'application/json',
		data:data,
		success:function(data){
			$("#stavke").find("tr:not(:first)").remove();
			$.each(data,function(index,stvaka){
				var row='<tr><td>'+stvaka.roba.naziv+'</td><td>'+stvaka.kolicina+'</td><td>'+stvaka.cena+'</td><td>'+stvaka.vrednost+'</td></tr>';
					$("#stavke").append(row)
			})
		}
	})
})
$(document).on('click',"#add",function(){
	var	url=window.location.href 
	url=url.substring(url.lastIndexOf('/')+1,url.length)
	var data=JSON.stringify({
		"roba":$("#roba option:selected").attr('id'),
		"kolicina":$("#kolicina").val(),
		"cena":$("#cena").val(),
		"vrednost":$("#vrednost").val(),
		"prometniDokument":url
	})
	$.ajax({
		url:'/stavkaDokumenta/save',
		type:'POST',
		contentType:'application/json',
		dataType:'json',
		data:data,
		async:false,
		success:function(data){
			var row='<tr><td>'+data.roba.naziv+'</td><td>'+data.kolicina+'</td><td>'+data.cena+'</td><td>'+data.vrednost+'</td></tr>';
			$("#stavke").append(row)
			$('#inputModal').modal('toggle');
		}
	})
})
$(document).on('change','#kolicina',function(){
	$("#vrednost").val($("#kolicina").val()*$("#cena").val())
})
$(document).on('change','#roba',function(){
	$.ajax({
		url:'/robnaKartica/'+$("#roba option:selected").attr('id'),
		type:'GET',
		success:function(data){
			$("#cena").val(data.cena)
		
		}
	})
})