$(document).ready(
		function(){
				$("#first").click(function(){
					goFirst()
				 });
				
				$("#next").click(function(){
					goNext()
				 });
				
				$("#last").click(function(){
					goLast()
				});
				
				$("#prev").click(function(){
					goPrevious()
				});
		})


$(document).on('click','#novi',function(){
	$("#editButton").css("display","none");
	$("#searchButton").css("display","none");
	$("#add").css("display","block");
	$.ajax({
		url:'/radnici/unemployed',
		type:'GET',
		contentType:'application/json',
		success:function(data){
			$("#radnici").empty();
			$.each(data,function(index,radnik){
				$("#radnici").append('<option id="'+radnik.id+'">'+radnik.ime+" "+radnik.prezime+'</option>')
				
			})
		}
	})
})	
$(document).on('click','#addR',function(event){
	event.preventDefault();
	var selected=$("#radnici option:selected")
	$("#izabraniRadnici").append(selected)

})
$(document).on('click','#deleteR',function(event){

	event.preventDefault();
	var selected=$("#izabraniRadnici option:selected")
	$("#radnici").append(selected)

})
$(document).on('click','#select',function(event){
	event.preventDefault();
	var highlighted = $(".highlighted");
	index =  $("tr").index(highlighted);
	index+=1;
	item = $("tr:nth-child(" + index + ")");
	
	var id= $(".highlighted").find(".id").html();
	var ime= $(".highlighted").find(".ime").html();
	var prezime= $(".highlighted").find(".prezime").html();
	var postoji=false;
	$("izabraniRadnici option").each(function(){
		if($(this).attr('id')==id){
			postoji=true;
			break;
		}
	})
	if(postoji==false){
		$("#izabraniRadnici").append('<option id="'+id+'">'+ime+' '+prezime+'</option>')
	}
	$("#radnici option").each(function(){
			if($(this).attr('id')==id){
				$(this).remove();
			}
		})
	$("#selection").css("display","none");
	$("#form").css("display","block");
})
function draw(){
	$("#selection").css("display","block");
	$("#form").css("display","none");
	
	$.ajax({
		url:'/radnici/all',
		type:'GET',
		contentType:'application/json',
		success:function(data){
			$("#radniciT").empty();
			$.each(data,function(index,radnik){
				let row="<tr><td class='id'>"+radnik.id+"</td><td class='ime'>"+radnik.ime+"</td><td class='prezime'>"+radnik.prezime+"</td></tr>"
				$("#radniciT").append(row)
				
			})
		}
	})
}
$(document).on("click", 'tr', function(event) {
	highlightRow(this)
	
});

$(document).on('click','#add',function(event){
	radnici=[]
	$("#izabraniRadnici option").each(function(){
			radnici.push($(this).attr('id'));
		
	})
	var data=JSON.stringify({
		"naziv":$("#nazivM").val(),
		"radnici":radnici
	
	})
	
	$.ajax({
		url:"/magacin/save",
		type:"POST",
		contentType:'application/json',
		dataType:'json',
		data:data,
		success:function(data){
			let row='<tr><td class="idCell">'+data.id+'</td><td class="naziv">'+data.naziv+'</td><td><a class="edit" href="../magacin/'+data.id+'">Edit</a></td><td><a class="delete" href="../magacin/delete/'+data.id+'">Delete</a></td><td><a href="../prometniDokument/all/'+data.id+'">Dokumenti</a></td></tr>'
			$("#magacini").append(row)
		}
	})

})
$(document).on("click", ".edit", function(event){
				
					event.preventDefault(); 
					$("#editButton").css("display","block");
					$("#searchButton").css("display","none");
					$("#add").css("display","none");
						
						var url = $(this).attr("href")
						$.ajax({
				        	url: url,
				        	type: "GET",
				        	success: function(data){
				        		$("#nazivM").val(data.naziv)
				        		$("#idD").val(data.id)
				        			$("#izabraniRadnici").empty()
				        			
				        		for(i=0;i<data.radnici.length;i++){
				        			$("#izabraniRadnici").append('<option id="'+data.radnici[i].id+'">'+data.radnici[i].ime+' '+data.radnici[i].prezime+'</option>')

				        		}
				        		$("#radnici option").each(function(){
				        			if($(this).attr('id')==data.id){
				        				$(this).remove();
				        			}
				        		})
				        		$("#editButton").css("display","block");
				        		$("#add").css("display","none");
				        		
				        		$('#inputModal').modal('toggle');
				        		
					        }
						});
				});
$(document).on('click','#editButton',function(){
	radnici=[]
	$("#izabraniRadnici option").each(function(){
			radnici.push($(this).attr('id'));
		
	})
	var data=JSON.stringify({
		"id":$("#idD").val(),
		"naziv":$("#nazivM").val(),
		"radnici":radnici
	
	})
	$.ajax({
		url:'../magacin/update',
		type:'PUT',
		contentType:'application/json',
		dataType:'json',
		data:data,
		success:function(data){
			$(".highlighted").find(".naziv").html(data.naziv)
		}
	})
})
function sync(item){
	naziv = item.find(".naziv").html()
	id=item.find('.idCell').html()
    $("#naziv").val(naziv);
	$("#id").val(id);
  
    
}
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
	item = $("table tr:nth-child(2)");
	$(".highlighted").removeClass("highlighted");
	item.addClass("highlighted");
	sync(item);
}

function goLast(){
	var count = $("#magacini > tbody >tr").length;
	item = $("table tr:nth-child("+count+")");
	$(".highlighted").removeClass("highlighted");
	item.addClass("highlighted");
	sync(item);
}
function goNext(){
	highlighted = $(".highlighted");
	//nalazi poziciju trazanog u okviru selektovane selekcije
	//indeksi pocinju od 0
	var count = $("#magacini > tbody >tr").length-1;
	if (count == 0)
		return;
	index =  $("tr").index(highlighted);
	if (index < 0)
		return;
	//ako smo na poslednjem, predji na prvi (odnosno drugi red, preskacuci header)
	selectChild = 2;
	//inace
	if(index==count-2){
		selectChild = 2;
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
	var count = $("#magacini > tbody >tr").length;
	if (count == 0)
		return;
	index =  $("tr").index(highlighted)-1;
	if (index < 0)
		return;
	selectChild = count-1;
	if (index >  1)
		selectChild = index ;
	
	item = $("tr:nth-child(" + selectChild + ")");
	$(".highlighted").removeClass("highlighted");
	item.addClass("highlighted");
	sync(item);
}
$(document).on('click',"#search",function(){
	$("#editButton").css("display","none");
	$("#searchButton").css("display","block");
	$("#add").css("display","none");
})

$(document).on('click','#searchButton',function(e){
	e.preventDefault();
	$.ajax({
		url:'../magacin/search/'+$("#nazivM").val(),
		type:'GET',
		success:function(data){
			$("#magacini").find("tr:not(:first)").remove();
			$.each(data,function(index,magacin){
				let row='<tr><td class="naziv">'+magacin.naziv+'</td><td><a class="edit" href="../magacin/'+magacin.id+'">Edit</a></td><td><a class="delete" href="../magacin/delete/'+magacin.id+'">Delete</a></td><td><a href="../prometniDokument/all/'+magacin.id+'">Dokumenti</a></td></tr>'
					$("#magacini").append(row)
		
			})
		}
	})
})
$(document).on('click','#nextform',function(e){
	e.preventDefault();
	var url=$(this).attr('href');
	url=url+'/'+$('#id').val()
			window.location.href = url;
	})
$(document).on('click','.delete',function(e){
	e.preventDefault();
	var confirmed=confirm("Da li zelite da obrisete magacin");
	var url=$(this).attr('href')
	var item=$(this)
	if(confirmed){
		$.ajax({
			url:url,
			type:'DELETE',
			success:function(){
				item.parent().parent().remove();
			}
		})
	}
})
