<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript"	src="${pageContext.request.contextPath}/jquery.min.js"> </script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/design.css">
    <script>
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
            });

        function init() {
            initMagacinSelect();
            initRobaSelect();
        }

        /********* dobijanje sve robe **********/
        function initRobaSelect() {
            $.ajax({
                url: '/api/roba/getRobu',
                type: 'GET',
                contentType: 'application/json',
                async: false,
                success: function (data) {
                    $("#robaSelect").empty();
                    $.each(data, function (index, roba) {
                        $("#robaSelect").append($('<option>', {
                            value: roba.id,
                            text: roba.naziv
                        }));

                        //dodaj robu u zoom tablu
                        $("#robaZoomTable").append('<tr><td class="naziv">' + roba.naziv +'</td><td class="grupa">' + roba.grupa.naziv + '</td><td class="idCell">'+ roba.id +'</td></tr>')
                    })
                }
            });
        }

        /*******************/

        function initMagacinSelect() {
            <c:choose>
                <c:when test="${ magacin != null }">
                    $("#magacinSelect").append($('<option>', {
                        value: ${ magacin.id },
                        text: '${ magacin.naziv }'
                    }));
                    $("#magacinSelect").prop('disabled', true);
                    $("#magacinZoomOpen").prop('disabled', true);
                </c:when>
                <c:otherwise>
                    $.ajax({
                        url: '/magacin/allM',
                        type: 'GET',
                        contentType: 'application/json',
                        async: false,
                        success: function (data) {
                            $("#magacinSelect").empty();
                            $.each(data, function (index, magacin) {
                                $("#magacinSelect").append($('<option>', {
                                    value: magacin.id,
                                    text: magacin.naziv
                                }));

                                //dodaj magacine u zoom tablu
                                $("#magacinZoomTable").append('<tr><td class="naziv">' + magacin.naziv +'</td><td class="preduzece">' + magacin.preduzece.naziv + '</td><td class="idCell">'+ magacin.id +'</td></tr>')
                            })
                        }
                    });
                </c:otherwise>
            </c:choose>
        }

        function addRobnaKartica() {
            let pocetnoStanjeVr = $("#pocetnoStanjeVrInput").val();
            let pocetnoStanjeKol = $("#pocetnoStanjeKolInput").val();
            let magacinId = $("#magacinSelect").val();
            let robaId = $("#robaSelect").val();
            let cena = $("#cenaInput").val();

            let data = {
                magacin: {
                    id: magacinId
                },
                roba: {
                    id: robaId
                },
                cena,
                pocetnoStanjeVr,
                pocetnoStanjeKol
            }
            $.ajax({
                url: '/robnaKartica',
                type: 'POST',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(data),
                success: function (data) {
                    alert("Dodata robna kartica!")
                }
            });
        }

        $(document).on("click", 'tr', function(event) {
            highlightRow(this)
        });


        $(document).on("click", '#magacinZoomTable tr', function(event) {
            if(!$(this).hasClass("header")){
                $(".magacinZoomHighlighted").removeClass("magacinZoomHighlighted");
                $(this).addClass("magacinZoomHighlighted");}
        });

        $(document).on("click", '#robaZoomTable tr', function(event) {
            if(!$(this).hasClass("header")){
                $(".robaZoomHighlighted").removeClass("robaZoomHighlighted");
                $(this).addClass("robaZoomHighlighted");}
        });

        function izaberiMagacinZoom() {
            //$("#countryNameEdit").val($(".zoomHighlighted").find(".name").text());
            //$("#countryNameEdit").prop("name", $(".zoomHighlighted").find(".idCell").text());
            $("#magacinSelect").val($(".magacinZoomHighlighted").find(".idCell").text());
            $('#magacinModal').modal('toggle');

        }

        function izaberiRobuZoom() {
            $("#robaSelect").val($(".robaZoomHighlighted").find(".idCell").text());
            $('#robaModal').modal('toggle');
        }

        function sync(item){
            let roba = item.find(".roba").html();
            let magacin = item.find(".magacin").html();
            let pocetnoStanjeVred = item.find(".pocetno-stanje-vred").html();
            let pocetnoStanjeKol = item.find(".pocetno-stanje-kol").html();
            let cena = item.find(".cena").html();

            $("#magacinPanel").val(magacin);
            $("#robaPanel").val(roba);
            $("#pocetnoStanjeVrPanel").val(pocetnoStanjeVred);
            $("#pocetnoStanjeKolPanel").val(pocetnoStanjeKol);
            $("#cenaPanel").val(cena);
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
            var count = $("#dokumenti >tbody >tr").length-1;
            if (count == 0)
                return;
            index =  $("tr").index(highlighted);
            if (index < 0)
                return;
            //ako smo na poslednjem, predji na prvi (odnosno drugi red, preskacuci header)
            selectChild = 1;
            //inace
            if(index==count-2){
                selectChild = 1;
            }
            else if (index <= count - 1)
                selectChild = index + 1;
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
            index =  $("tr").index(highlighted)-1;
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

        function otvoriIzmeniModal(id, cena) {
            $("#izmeniKarticuButton").attr('onclick', 'izmeniKarticu(' + id + ')');
            $("#cenaIzmeniInput").val(cena);
            $("#izmeniModal").modal('toggle');
        }

        function izmeniKarticu(id) {
            let data = {
                cena: $("#cenaIzmeniInput").val()
            }

            $.ajax({
                url: '/robnaKartica/' + id,
                type: 'PATCH',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (data) {
                    alert("Izmenjena robna kartica!");
                    $('#izmeniModal').modal('toggle');
                }
            });
        }
    </script>
</head>
<body onload="init()">
<div>
    <ul>
        <li class="group-start"><a href="searchModal" data-toggle="modal" data-target="#searchModal"><img src="../../images/search.gif" /></a></li>
        <li><a href="#" id="refresh"><img src="../../images/refresh.gif" /></a></li>
        <li><a href="#" id="help"><img src="../../images/help.gif" /></a></li>
        <li class="group-start"><a id="first"><img src="../../images/first.gif" /></a></li>
        <li><a id="prev"><img src="../../images/prev.gif" /></a></li>
        <li><a id="next"><img src="../../images/next.gif" /></a></li>
        <li><a id="last"><img src="../../images/last.gif" /></a></li>
        <li class="group-start" onclick="find()"id="novi"><a href="#inputModal" data-toggle="modal" data-target="#inputModal"><img src="../../images/add.gif" /></a></li>
        <li class="group-start"><a href="#" id="nextform"><img src="../../images/nextform.gif" /></a></li>
    </ul>

    <table id="dokumenti" border="1">

        <tr class="header">
            <th>Magacin</th>
            <th>Roba</th>
            <th>Cena</th>
            <th>Pocetno stanje kolicinski</th>
            <th>Pocetno stanje vrednosno</th>
        </tr>

        <tbody>
        <c:forEach items="${ robneKartice }" var="robnaKartica">
            <tr>
                <td class="magacin">${ robnaKartica.magacin.naziv }</td>
                <td class="roba">${ robnaKartica.roba.naziv }</td>
                <td class="cena">${ robnaKartica.cena }</td>
                <td class="pocetno-stanje-kol">${ robnaKartica.pocetnoStanjeKol }</td>
                <td class="pocetno-stanje-vred">${ robnaKartica.pocetnoStanjeVr }</td>
                <td><a onclick="otvoriIzmeniModal(${ robnaKartica.id }, ${ robnaKartica.cena })">Izmeni</a></td>
                <td><a href="/robnaKartica/${ robnaKartica.id }/analitika">Analitika</a></td>
                <td><a href="/robnaKartica/${ robnaKartica.id }/nivelacija">Nivelacija</a></td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="panel">
        <table>
            <tr>
                <input type="hidden" name="id" id="id" />
            </tr>
            <tr>
                <td><label for="magacinPanel">Magacin</label></td>
                <td><input type="text" name="magacin" id="magacinPanel" disabled /></td>
            </tr>
            <tr>
                <td><label for="robaPanel">Roba</label></td>
                <td><input type="text" name="roba" id="robaPanel" disabled /></td>
            </tr>

            <tr>
                <td><label for="cenaPanel">Cena</label></td>
                <td><input type="text" name="cena" id="cenaPanel" disabled></td>
            </tr>

            <tr>
                <td><label for="pocetnoStanjeKolPanel">Pocetno stanje (kol.)</label></td>
                <td><input type="text" name="pocetnoStanjeKolPanel" id="pocetnoStanjeKolPanel" disabled /></td>
            </tr>
            <tr>
                <td><label for="pocetnoStanjeVrPanel">Pocetno stanje (vr.)</label></td>
                <td><input type="text" name="pocetnoStanjeVrPanel" id="pocetnoStanjeVrPanel" disabled /></td>
            </tr>
        </table>
    </div>
</div>
<div class="modal"  id="inputModal"  role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3>Nova magacinska kartica</h3>
            </div>
            <div id="form">
                <form id="inputForm" style="display:block;">
                    <div class="modal-body">
                        <table>
                            <input type="hidden" name="id" id="idD" />
                            <tr>
                                <td><label for="magacinSelect">Magacin</label></td>
                                <td><select name="magacinSelect" id="magacinSelect"></select><button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#magacinModal">...	</button></td>
                            </tr>
                            <tr>
                                <td><label for="robaSelect">Roba</label></td>
                                <td><select id="robaSelect"></select><button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#robaModal">...	</button></td>
                            </tr>
                            <tr>
                                <td><label for="cenaInput">Cena</label></td>
                                <td><input id="cenaInput" type="text"></td>
                            </tr>
                            <tr>
                                <td><label for="pocetnoStanjeKolInput">Pocetno stanje (kol.)</label></td>
                                <td><input id="pocetnoStanjeKolInput" type="text"></td>
                            </tr>
                            <tr>
                                <td><label for="pocetnoStanjeVrInput">Pocetno stanje (vr.)</label></td>
                                <td><input id="pocetnoStanjeVrInput" type="text"></td>
                            </tr>
                        </table>


                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Zatvori</button>
                        <button type="button" onclick="addRobnaKartica()" class="btn btn-primary" id="add">Potvrda</button>
                        <button type="button" class="btn btn-primary" style="display:none" id="editButton">Izmeni</button>
                        <button type="button" class="btn btn-primary" style="display:none" id="searchButton">Pretrazi</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="magacinModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                Magacini
            </div>
            <div class="modal-body">
                <table style = "padding:2em" id = "magacinZoomTable">
                    <tr class = "header">
                        <th>Naziv</th>
                        <th>Firma</th>
                    </tr>

                </table>
                <div class="modal-footer">
                    <input id="izaberiMagacinZoom" onclick="izaberiMagacinZoom()" type = "button" value = "Izaberi"/>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="robaModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                Roba
            </div>
            <div class="modal-body">
                <table style = "padding:2em" id = "robaZoomTable">
                    <tr class = "header">
                        <th>Naziv</th>
                        <th>Grupa</th>
                    </tr>

                </table>
                <div class="modal-footer">
                    <input id="izaberiRobuZoom" onclick="izaberiRobuZoom()" type = "button" value = "Izaberi"/>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="izmeniModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                Izmeni robnu karticu
            </div>
            <div class="modal-body">
                <table>
                    <tr>
                        <td>Cena:</td>
                        <td><input type="text" id="cenaIzmeniInput"></td>
                    </tr>
                </table>
            </div>

            <div class="modal-footer">
                <input id="izmeniKarticuButton" type = "button" value = "Izaberi"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>