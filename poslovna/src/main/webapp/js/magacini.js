$(document).on('click','#novi',function(){

	$.ajax({
		url:'/radnici/all',
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
	var selected=$("#radnici option")
	$("#izabraniRadnici").append(selected)

})
$(document).on('click','#deleteR',function(event){

	event.preventDefault();
	var selected=$("#izabraniRadnici option")
	$("#radnici").append(selected)

})
$(document).on('click','#select',function(event){
	event.preventDefault();
	var highlighted = $(".highlighted");
	index =  $("tr").index(highlighted);
	index+=1;
	item = $("tr:nth-child(" + index + ")");
	
	var id=item.find(".id").html();
	var ime=item.find(".ime").html();
	var prezime=item.find(".prezime").html();
	
	$("#izabraniRadnici").append('<option id="'+id+'">'+ime+' '+prezime+'</option>')
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
			alert("Sacuvano")
		}
	})

})
function sync(item){
	prezime = item.find(".prezime").html()
    ime = item.find(".ime").html()
    id = item.find(".id").html()
    $("#prezime").val(prezime);
    $("#ime").val(ime);
    $("#id").val(id);
    
}
