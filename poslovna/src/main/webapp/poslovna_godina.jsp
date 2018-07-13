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
            //initMagacinSelect();
        }

        function addPoslovnaGodina() {
            let godina = $("#godinaInput").val();

            let data = {
                godina,
                zakljucena: false,
                aktivna: true
            }
            $.ajax({
                url: '/poslovnaGodina',
                type: 'POST',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(data),
                success: function (data) {
                    alert("Dodata poslovna godina!")
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
        }

        $(document).on("click", 'tr', function(event) {
            highlightRow(this)
        });

        function zakljuciGodinu(id) {
            $.ajax({
                url: '/poslovnaGodina/' + id + '/zakljuci',
                type: 'POST',
                success: function (data) {
                    alert("Godina uspesno zakljucena!")
                    window.location.reload();
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
        }

        function sync(item){
            let godina = item.find(".godina").html();
            let zakljucena = item.find(".zakljucena").html();
            let aktivna = item.find(".aktivna").html();

            $("#godinaPanel").val(godina);
            $("#zakljucenaPanel").val(zakljucena);
            $("#aktivnaPanel").val(aktivna);

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
            <th>Godina</th>
            <th>Zakljucena</th>
            <th>Aktivna</th>
        </tr>

        <tbody>
        <c:forEach items="${ godine }" var="godina">
            <tr>
                <td class="godina">${ godina.godina }</td>
                <td class="zakljucena">${ godina.zakljucena }</td>
                <td class="aktivna">${ godina.aktivna }</td>
                <c:if test="${ godina.zakljucena }">
                    <td>Zakljucena</td>
                </c:if>
                <c:if test="${ !godina.zakljucena }">
                    <td><a onclick="zakljuciGodinu(${ godina.id })">Zakljuci</a></td>
                </c:if>

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
                <td><label for="godinaPanel">Godina</label></td>
                <td><input type="text" name="godinaPanel" id="godinaPanel" disabled /></td>
            </tr>
            <tr>
                <td><label for="zakljucenaPanel">Zakljucena</label></td>
                <td><input type="text" name="zakljucenaPanel" id="zakljucenaPanel" disabled /></td>
            </tr>
            <tr>
                <td><label for="aktivnaPanel">Aktivna</label></td>
                <td><input type="text" name="aktivnaPanel" id="aktivnaPanel" disabled /></td>
            </tr>
        </table>
    </div>
</div>
<div class="modal"  id="inputModal"  role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3>Nova poslovna godina</h3>
            </div>
            <div id="form">
                <form id="inputForm" style="display:block;">
                    <div class="modal-body">
                        <table>
                            <input type="hidden" name="id" id="idD" />
                            <tr>
                                <td><label for="godinaInput">Godina</label></td>
                                <td><input type="date" id="godinaInput"></td>
                            </tr>

                        </table>


                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Zatvori</button>
                        <button type="button" onclick="addPoslovnaGodina()" class="btn btn-primary" id="add">Potvrda</button>
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