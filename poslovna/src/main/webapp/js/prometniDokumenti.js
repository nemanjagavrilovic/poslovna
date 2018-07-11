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
	var reg = /^\d+$/
	url=url.substring(url.lastIndexOf('/')+1,url.length)
	if(!reg.test(url)){
		
	$.ajax({
		url:'/magacin/allM',
		type:'GET',
		contentType:'application/json',
		async:false,
		success:function(data){
				$("#magacin").empty();
				$.each(data,function(index,magacin){
					$("#magacin").append('<option selected id="'+magacin.id+'">'+magacin.naziv+'</option>')
					
					})
				}
			})
		
	}
	$.ajax({
		url:'/robnaKartica/all/'+$("#magacin option:selected").attr('id'),
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
	$.ajax({
		url:'../api/poslovniPartner/all',
		type:'GET',
		contentType:'application/json',
		async:false,
		success:function(data){
			$("#partner").empty();
			$.each(data,function(index,partner){
				$("#partner").append('<option id="'+partner.pib+'">'+partner.naziv+'</option>')
				
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
	})
		var dokument=JSON.stringify({
			"vrsta":$("#vrsta option:selected").val(),
			"stavke":stavke,
			"poslovniPartner":$("#partner option:selected").attr('id'),
			"magacin":$("#magacin option:selected").attr('id')
		})
		$.ajax({
			url:'../prometniDokument/save',
			type:'POST',
			contentType:'application/json',
			dataType:'json',
			data:dokument,
			async:false,
			success:function(data){
				var row='<tr><td>'+data.redniBr+'</td><td>'+convertDate(data.datumFormiranja)+'</td>'
				+'<td>'+convertDate(data.datumKnjizenja)+'</td><td>'+data.poslovniPartner.naziv+'</td>'
				+'<td>'+data.magacin.naziv+'</td><td>'+data.vrsta+'</td><td>'+data.status+'</td>'
				+'<td></td><td><a href=""../../stavkaDokumenta/'+data.id+'>Stavke</a></td><td><a href=""../../knjizenje/'+data.id+'>Proknjizi</a></td></tr>'
				$("#dokumenti").append(row);
				$('#inputModal').modal('toggle');
			
			}
	
		})
	})

function convertDate(stamp){
	if(stamp!=null){
		var a=new Date(stamp);
		var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
		var year = a.getFullYear();
		var month = months[a.getMonth()];
		var date = a.getDate();
		  
		var time = date + '.' + month + '.' + year+'.' ;
		return time;
	}else{
		return ""
	}
}
function draw(){
	$.ajax({
		url:'/robnaKartica/all/'+$("#magacin option:selected").attr('id'),
		type:'GET',
		contentType:'application/json',
		success:function(data){
			$("#robaT").empty();
			$("#robaSelection").css("display","block");
			$("#form").css("display","none");
			$.each(data,function(index,kartica){
				var row='<tr><td class="id">'+kartica.roba.id+'</td><td class="naziv">'+kartica.roba.naziv+'</td><td>'+kartica.roba.grupa.naziv+'</td>'
				+'<td>'+kartica.roba.jedinicaMere.naziv+'</td><td>'+kartica.cena+'</td></tr>'
				
				$("#robaT").append(row);
				
			})
		}
	})
}
function sync(item){
    rbr = item.find(".rbr").html()
    datumFormiranja = item.find(".datumFormiranja").html()
    datumKnjizenja = item.find(".datumKnjizenja").html()
    partner = item.find(".partner").html()
    vrsta = item.find(".vrsta").html()
    status = item.find(".status").html()
    magacin=item.find(".magacin").html();
    
    $("#rbr").val(rbr);
    $("#datumFormiranjaV").val(datumFormiranja);
    $("#datumKnjizenjaV").val(datumKnjizenja);
    $("#poslovniPartnerV").val(partner);
    $("#vrstaV").val(vrsta);
    $("#statusV").val(status);
    $("#magacinV").val(magacin);
}
function drawPartners(){
	$.ajax({
		url:'../../poslovniPartner/all',
		type:'GET',
		contentType:'application/json',
		dataType:'json',
		success:function(data){
			$("#partnerT").empty();
			$("#poslovniPartnerSelection").css("display","block");
			$("#form").css("display","none");
			$.each(data,function(index,partner){
				var row='<tr><td class="naziv">'+partner.naziv+'</td><td>'+partner.tip+'</td><td class="pib">'+partner.pib+'</td></tr>'
				$("#partnerT").append(row);
			})
		}
	})
}
$(document).on('click','#select',function(event){
	event.preventDefault();
	
	var id= $(".highlighted").find(".id").html();
	var ime= $(".highlighted").find(".naziv").html();
	

	
		$("#roba option").each(function(){
			if($(this).text()==ime){
				$(this).prop("selected","selected");
			}
		})
	$("#robaSelection").css("display","none");
	$("#form").css("display","block");
})
$(document).on('click','#selectPartner',function(event){
	event.preventDefault();
	
	var pib= $(".highlighted").find(".pib").html();
	var ime= $(".highlighted").find(".naziv").html();
	
	$("#partner option").each(function(){
		if($(this).text()==ime){
			$(this).prop("selected","selected");
		}
	})

	

	$("#poslovniPartnerSelection").css("display","none");
	$("#form").css("display","block");
})
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
	var count = $("#dokumenti >tbody >tr").length-1;
	item = $("table tr:nth-child("+count+")");
	$(".highlighted").removeClass("highlighted");
	item.addClass("highlighted");
	sync(item);
}
function goNext(){
	highlighted = $(".highlighted");
	//nalazi poziciju trazanog u okviru selektovane selekcije
	//indeksi pocinju od 0
	var count = $("#dokumenti >tbody >tr").length;
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
	var count = $("#dokumenti >tbody >tr").length-1;
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
$(document).on('change','#magacin',function(){
	$.ajax({
		url:'/robnaKartica/all/'+$("#magacin option:selected").attr('id'),
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

$(document).on('click',"#searchD",function(){
	$.ajax({
		url:'/magacin/allM',
		type:'GET',
		contentType:'application/json',
		async:false,
		success:function(data){
				$("#magacinS").empty();
				$.each(data,function(index,magacin){
					$("#magacinS").append('<option selected id="'+magacin.id+'">'+magacin.naziv+'</option>')
					
					})
				}
			})
		
	
			$.ajax({
	url:'http://localhost:9000//api/poslovniPartner/all',
	type:'GET',
	contentType:'application/json',
	async:false,
	success:function(data){
		$("#partnerS").empty();
		$.each(data,function(index,partner){
			$("#partnerS").append('<option id="'+partner.pib+'">'+partner.naziv+'</option>')
			
		})
	}
	})
	
})
$(document).on('click',"#searchButton",function(){
	
	var podaci=JSON.stringify({
		"vrsta":$("#vrstaS option:selected").val(),
		"status":$("#statusS option:selected").val(),
		"poslovniPartner":$("#partnerS option:selected").attr('id'),
		"magacin":$("#magacinS option:selected").attr('id'),
	})
	$.ajax({
		url:'http://localhost:9000/prometniDokument/search',
		type:'POST',
		dataType:'json',
		contentType:'application/json',
		data:podaci,
		success:function(data){
			$("#dokumenti").find("tr:not(:first)").remove();
			$.each(data,function(index,dokument){
				var row='<tr><td>'+data.redniBr+'</td><td>'+convertDate(data.datumFormiranja)+'</td>'
				+'<td>'+convertDate(data.datumKnjizenja)+'</td><td>'+data.poslovniPartner.naziv+'</td>'
				+'<td>'+data.magacin.naziv+'</td><td>'+data.vrsta+'</td><td>'+data.status+'</td>'
				+'<td></td><td><a href=""../../stavkaDokumenta/'+data.id+'>Stavke</a></td><td><a href=""../../knjizenje/'+data.id+'>Proknjizi</a></td></tr>'
				$("#dokumenti").append(row);
			})
		}
	})
	
})

function proknjizi(id) {
    $.ajax({
        url:'/prometniDokument/'+ id + '/proknjizi',
        type:'POST',
        contentType:'application/json',
        success:function(data){
            alert("Uspesno proknjizeno!");
        }
    })
}
