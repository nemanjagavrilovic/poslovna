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
	var count = $("#dataTable tr").length;
	item = $("table tr:nth-child(" + count + ")");
	$(".highlighted").removeClass("highlighted");
	item.addClass("highlighted");
	sync(item);
}

function goNext(){
	highlighted = $(".highlighted");
	//nalazi poziciju trazanog u okviru selektovane selekcije
	//indeksi pocinju od 0
	var count = $("#dataTable tr").length;
	if (count == 0)
		return;
	index =  $("tr").index(highlighted);
	console.log(index);
	if (index < 0)
		return;
	//ako smo na poslednjem, predji na prvi (odnosno drugi red, preskacuci header)
	selectChild = 2;
	//inace
	if (index < count - 1)
		selectChild = index + 2; //povecavamo za 1, i jos dodajemo 1 jer nth child pocinje od 1, indeksi od 0
	item = $("#dataTable tr:nth-child(" + selectChild + ")");
	$(".highlighted").removeClass("highlighted");
	item.addClass("highlighted");
	sync(item);
}

function goPrev(){
	highlighted = $(".highlighted");
	var count = $("#dataTable tr").length;
	if (count == 0)
		return;
	index =  $("tr").index(highlighted);
	console.log(index);
	if (index < 0)
		return;

	selectChild = count;
	if (index != 1)
		selectChild = index;
	item = $("#dataTable tr:nth-child(" + selectChild + ")");
	$(".highlighted").removeClass("highlighted");
	item.addClass("highlighted");
	sync(item);
}
function includeHTML() {
    var z, i, elmnt, file, xhttp;
    /*loop through a collection of all HTML elements:*/
    z = document.getElementsByTagName("*");
    for (i = 0; i < z.length; i++) {
        elmnt = z[i];
        /*search for elements with a certain atrribute:*/
        file = elmnt.getAttribute("w3-include-html");
        if (file) {
            /*make an HTTP request using the attribute value as the file name:*/
            xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4) {
                    if (this.status == 200) {elmnt.innerHTML = this.responseText;}
                    if (this.status == 404) {elmnt.innerHTML = "Page not found.";}
                    /*remove the attribute, and call this function once more:*/
                    elmnt.removeAttribute("w3-include-html");
                    includeHTML();
                }
            }
            xhttp.open("GET", file, true);
            xhttp.send();
            /*exit the function:*/
            return;
        }
    }
}
