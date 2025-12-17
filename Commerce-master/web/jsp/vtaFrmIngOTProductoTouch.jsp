<!DOCTYPE html>
<html>
    <%@page import="co.linxsi.modelo.maestro.operaciones.Operaciones_DAO"%>
    <%@page import="co.linxsi.modelo.touch.retal.Touch_Retal_DTO"%>
    <%@page import="java.util.List"%>
    <%@page import="co.linxsi.modelo.touch.retal.Touch_Retal_DAO"%>

    <%@page import="com.solucionesweb.losbeans.fachada.FachadaTerceroBean"%>
    <%@page import="java.util.Vector"%>
    <%@page import="co.linxsi.modelo.maestro.retales.Retales_DTO"%>
    <%@page import="co.linxsi.modelo.maestro.unidades.Unidades_DTO"%>
    <%@page import="co.linxsi.modelo.maestro.operaciones.Operaciones_DTO"%>
    <%@page import="co.linxsi.modelo.maestro.maquinas.Maquinas_DAO"%>
    <%@page import="co.linxsi.modelo.maestro.maquinas.Maquinas_DTO"%>
    <%@page import="co.linxsi.modelo.maestro.paro_maquina.Paro_Maquina_DAO"%>
    <%@page import="co.linxsi.modelo.maestro.paro_maquina.Paro_Maquina_DTO"%>


    <%@page import="java.util.ArrayList"%>
    <%@ taglib uri="/WEB-INF/tlds/listaOperacionTouch" prefix="lsv" %>
    <%@ taglib uri="/WEB-INF/tlds/listaUsuarioOperacion" prefix="lst" %>
    <%@ taglib uri="/WEB-INF/tlds/listaJobCausa" prefix="lsu" %>

    <jsp:useBean id="fachadaPluFicha"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" />
    <jsp:useBean id="fachadaDctoOrdenBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean" />
    <jsp:useBean id="fachadaTerceroBean"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaTerceroBean" />
    <jsp:useBean id="fachadaPluFichaAcumula"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaPluFicha" />

    <jsp:useBean id="fachadaDctoOrdenProgreso"
                 scope="request"
                 type="com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso" />

    <% String xIdTipoCausaRetal = "2";%>

    <head>
        <title>Registra Produccion</title>
        <meta http-equiv="content-type" content="text/html; charset=iso-8601" />
        <link type="text/css" href="./styles/jquery-ui-1.8.13.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="./js/jquery-1.5.1.min.js"></script>
        <script type="text/javascript" src="./js/jquery-ui-1.8.13.custom.min.js"></script>
        <script type="text/javascript" src="./js/jquery-ui-timepicker-addon.js"></script>
        <style>

            div.ex1 {
                background-color: lightblue;
                overflow: scroll;
                width: 660px;
                height:120px;
            }

            body {
                font-family: Arial, sans-serif;
                font-size: 16px;
                line-height: 1.5;
                padding: 20px;
            }
            label {
                display: block;
                margin-bottom: 10px;
                font-weight: bold;
            }
            input[type="datetime-local"] {
                display: block;
                padding: 10px;
                font-size: 16px;
                border-radius: 5px;
                border: 1px solid #ccc;
                box-shadow: inset 0 1px 3px #ddd;
                width: 100%;
                box-sizing: border-box;
            }
        </style>


    </style>
</head>

<body onload="obtenerPesoBascula()">
    <form method="POST" action="GralControladorServlet">
        <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmIngOTProductoTouch.jsp">

        <input type="hidden" name="xIdLocal" value="<%=fachadaPluFicha.getIdLocalStr()%>">
        <input type="hidden" name="xIdTipoOrden" value="<%=fachadaPluFicha.getIdTipoOrdenStr()%>">
        <input type="hidden" name="xIdLog" value="<%=fachadaPluFicha.getIdLogStr()%>">            
        <input type="hidden" name="xItemPadre" value="<%=fachadaPluFicha.getItemPadreStr()%>">                                    
        <input type="hidden"  id="xIdOperacion" name="xIdOperacion" value="<%=fachadaPluFicha.getIdOperacionStr()%>">
        <input  readonly id="xFechaFin"  name="xFechaFin"></input>
        <input  type="hidden" name="xIdPlu" value="<%=fachadaPluFicha.getIdPlu()%>">            

        <table border="0" cellpadding="0" cellspacing="0" width="800" height="400" bgcolor="#000000">

            <!--table border="0" cellpadding="0" cellspacing="0" width="800" height="400"-->
            <!-- MSTableType="layout" -->
            <tr>
                <td valign="top" colspan="3" height="80" align="center">
                    <!-- MSCellType="ContentHead" -->
                    <table>
                        <tr>
                            <td width="25%" align="center" bgcolor="#FFFFFF">
                                PEDIDO <%=fachadaDctoOrdenBean.getNumeroOrden()%>
                                <input type="hidden" name="xIdDcto" id="xIdDcto" value="<%=fachadaDctoOrdenBean.getNumeroOrden()%>">            

                            </td>
                            <td width="25%" align="left" bgcolor="#FFFFFF">
                                REFERENCIA CLIENTE
                                <br><%=fachadaPluFicha.getReferenciaCliente()%>
                                <br>REFERENCIA
                                <br><%=fachadaPluFicha.getReferencia()%>
                            </td>
                            <td width="25%" align="left" bgcolor="#FFFFFF">
                                CLIENTE
                                <br><%=fachadaTerceroBean.getNombreTercero()%>
                            </td>
                            <td width="25%" align="left" bgcolor="#FFFFFF">
                                KG.PEDIDO <%=fachadaPluFichaAcumula.getPesoPendienteDf0()%>
                                <br>CANTIDAD PEDIDA <%=fachadaPluFichaAcumula.getCantidadPendienteDf0()%>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td valign="top" width="100">

                    <div style="height:400px; overflow:auto">

                        <table cellpadding="15" cellspacing="3">
                            <input hidden="true" value="0" id="idOperario">
                            <lst:listaUsuarioOperacion idLocalTag="<%=fachadaPluFicha.getIdLocalStr()%>"
                                                       idOperacionTag="<%=fachadaPluFicha.getIdOperacionStr()%>">
                                <tr>
                                    <td align="left" bgcolor="#FFFFFF">
                                        <input type="radio" name="xIdOperario" value="<%=idUsuarioVar%>" onclick="capturarOperario('<%=idUsuarioVar%>')" style="width:30px;height:30px;border-width:thin;border-style:solid;border-color:green;color:#000000;"/>
                                        <br><%=nombreUsuarioVar%>
                                    </td>
                                </tr>
                            </lst:listaUsuarioOperacion>

                        </table>
                    </div>
                </td>
                <td valign="top" width="100" align="center">
                    <table>
                        <tr>
                            <td width="20%" align="center" bgcolor="#FFFFFF" ><br>MAQUINA
                                <select class="col-2" name="listaMaquinas" onchange='obtenerFechaFinMaquina()' id="listaMaquinas">
                                    <option value="0">Seleccione</option>
                                    <%
                            Maquinas_DAO dao = new Maquinas_DAO();
                            dao.setSk_operacion(fachadaPluFicha.getIdOperacion());
                           ArrayList<Maquinas_DTO> lista = (ArrayList<Maquinas_DTO>) dao.listaAllMachineByProcess();
                     
                     
                           for (Maquinas_DTO t : lista) {
                                    %>
                                    <option value="<%=t.getSk_maquina()%>" ><%=t.getNombreMaquina()%></option>
                                    <% }%></select></td>                                

                            <td width="20%" align="center" bgcolor="#FFFFFF">PESO(KG)
                                <br>FINALIZADO
                                <br><input name="xPesoTerminado" title="Verifique bien la cantidad a ingresar que posee el signo decimal '.' correctamete no utilize ','" id ="xPesoTerminado" size="10" maxlength="10" value="0">
                            </td>
                            <td width="20%" align="center" bgcolor="#FFFFFF">PESO(KG)
                                <br>TARA
                                <br><input name="xPesoTara" size="10" maxlength="10" value="0">
                            </td>
                            <% if ((fachadaPluFicha.getIdOperacion() != 2) && 
                                   (fachadaPluFicha.getIdOperacion() != 3) && 
                                   (fachadaPluFicha.getIdOperacion() != 4)) {%>
                            <td width="20%" align="center" bgcolor="#FFFFFF">CANTIDAD
                                <br>UNIDADES
                                <br><input name="xCantidadTerminada" size="10" maxlength="10" value="0">
                            </td>
                            <% } else {%>
                            <td width="20%" align="center">&nbsp;</td>                                
                            <% }%>                                
                            <td width="40%" align="center">&nbsp;</td>
                        </tr>
                        <tr>
                            <td> </td>
                            <td>  <input  id ="botonLeerPeso" onclick = "obtenerPesoBascula()" type="button" style="width:115px; height:30px" name="accionContenedor" value="Leer Peso" /></td>
                            <td> ></td>
                        </tr>

                    </table>

                    <table border="0" width="90%" cellpadding="15" cellspacing="3">
                        <% if (fachadaDctoOrdenProgreso.getFechaFin() == null || (fachadaPluFicha.getIdOperacion() == 5 || fachadaPluFicha.getIdOperacion() == 4)) {%>
                        <% if ((fachadaPluFicha.getIdOperacion() == 5 || fachadaPluFicha.getIdOperacion() == 4)){ %>
                        <tr>
                            <td width="33%" align="center"  bgcolor="#FFFFFF">
                                <br>TURNO

                                <select class="col-2" name="listaTurnos" onchange='habilitarFecha()' disabled id="listaTurnos">
                                    <option value="<%=0%>" >SELECCIONE</option>                                        
                                    <option value="<%=1%>" >1- 06:00 am - 02:00 pm</option>
                                    <option value="<%=2%>" >2- 02:00 pm - 09:00 pm</option>
                                    <option value="<%=3%>" >3- 09:00 pm - 06:00 am</option>
                                    <option value="<%=4%>" >4- 06:00 am - 06:00 pm</option>
                                    <option value="<%=5%>" >5- 06:00 pm - 06:00 am</option>
                                    <option value="<%=6%>" >6- 06:00 am - 04:00 pm</option>
                                    <option value="<%=7%>" >7- 07:00 am - 05:00 pm</option>
                                    <option value="<%=8%>" >8- 02:00 pm - 10:00 pm</option>
                                    <option value="<%=9%>" >9-10:00 pm - 06:00 am</option>
                                    <option value="<%=10%>" >10- 06:00 am - 03:00 pm</option>
                                    <option value="<%=11%>" >11- 08:00 am - 04:00 pm</option>
                                </select>

                            </td>  

                        </tr>

                        <tr>
                            <td width="33%" align="center"  bgcolor="#FFFFFF">
                                <br>FECHA PRODUCCION
                                <!--input type="text" name="xFechaPdn" id="xFechaPdn" value="" readonly="readonly"/-->
                                <input type="datetime-local" disabled id="fecha" name="fecha" onchange="validarHora()" required > <span class="validity"></span>
                                </div>
                            </td>
                            <td width="34%" align="center">&nbsp;</td>
                            <td width="33%" align="center">&nbsp;</td>
                        </tr>
                        <% }else if(fachadaDctoOrdenProgreso.getFechaFin() == null){ %>
                        <tr>
                            <td width="33%" align="center"  bgcolor="#FFFFFF">
                                <br>FECHA/HORA INICIO
                                <input type="text" name="xFechaHoraInicio" id="xFechaHoraInicio" value="" readonly="readonly"/>
                            </td>
                            <td width="34%" align="center">&nbsp;</td>
                            <td width="33%" align="center">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="33%" align="center"  bgcolor="#FFFFFF">
                                <br>FECHA/HORA FIN
                                <input type="text" name="xFechaHoraFin" id="xFechaHoraFin" value="" readonly="readonly" />
                            </td>
                            <td width="34%" align="center" bgcolor="#FFFFFF>

                                </tr>
                                <% }}%>
                                </table>

                                </div>

                                </td>
                                </tr>
                                <tr>
                                <td valign="top" colspan="3" height="80" align="center">
                                <!-- MSCellType="ContentFoot" -->
                                <table border="0" width="90%" cellpadding="15" cellspacing="3">
                                    <tr>
                                        <td width="33%" align="center">&nbsp;</td>
                                        <td width="34%" align="center">&nbsp;</td>
                                        <td width="33%" align="center">                                    
                                            <input  id ="botonConfirmar" disabled type="submit" style="width:120px; height:50px" name="accionContenedor" value="Confirmar" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    </form>
                    </body>

                    </html>
                    <script src="resourcs/js/touch/paro.js"></script>
                    <script type="text/javascript">
                                    $(function () {
                                        $('#xFechaHoraInicio').datetimepicker({
                                            showSecond: true,
                                            timeFormat: 'hh:mm:ss'
                                        });
                                    });


                    </script>

                    <script type="text/javascript">
                        var horaValida = false;
                        function habilitarBotonConfirmar() {
                            var selected = document.getElementById('listaMaquinas');
                            var indice = selected.selectedIndex;
                            if (indice != 0) {
                                document.getElementById('botonConfirmar').disabled = false;
                            } else {
                                document.getElementById('botonConfirmar').disabled = true;
                            }
                            var xIdOperacion = document.getElementById('xIdOperacion').value;

                            if (xIdOperacion == '5' && indice != 0 || xIdOperacion == '6' && indice != 0|| xIdOperacion == '4' && indice != 0) {
                                document.getElementById('listaTurnos').disabled = false;


                            } else {
                                document.getElementById('listaTurnos').disabled = true;

                            }
                        }
                        function habilitarBotonConfirmar2(horaValida) {
                            var selected = document.getElementById('listaMaquinas');
                            var indice = selected.selectedIndex;
                            if (indice != 0 && horaValida) {
                                document.getElementById('botonConfirmar').disabled = false;
                            } else {
                                document.getElementById('botonConfirmar').disabled = true;
                            }
                        }
                        function habilitarFecha() {
                            var lista = document.getElementById('listaTurnos');
                            var indice = lista.selectedIndex;
                            var inputHora = document.getElementById('fecha');
                            if (indice != 0) {
                                inputHora.disabled = false;
                            } else {
                                inputHora.disabled = true;
                                inputHora.value = '';
                            }
                        }

                        function validarHora() {
                            var inputHora = document.getElementById('fecha');
                            var selectTurno = document.getElementById("listaTurnos");
                            var turnoSeleccionado = selectTurno.value;
                            var horaSeleccionada = new Date(inputHora.value);
                            switch (turnoSeleccionado) {
                                case "<%=1%>": // 1- 06:00 am - 02:00 pm
                                    if (horaSeleccionada.getHours() < 6 || horaSeleccionada.getHours() >= 14) {
                                        alert("La hora seleccionada no corresponde al turno " + turnoSeleccionado);
                                        document.getElementById('botonConfirmar').disabled = true;
                                    } else {
                                        habilitarBotonConfirmar2(true);
                                    }
                                    break;
                                case "<%=2%>": // 2- 02:00 pm - 09:00 pm
                                    if (horaSeleccionada.getHours() < 14 || horaSeleccionada.getHours() >= 21) {
                                        alert("La hora seleccionada no corresponde al turno " + turnoSeleccionado);
                                        document.getElementById('botonConfirmar').disabled = true;
                                    } else {
                                        habilitarBotonConfirmar2(true);
                                    }
                                    break;
                                case "<%=3%>": // 3- 09:00 pm - 06:00 am
                                    if (horaSeleccionada.getHours() < 21 && horaSeleccionada.getHours() >= 6) {
                                        alert("La hora seleccionada " + horaSeleccionada.getHours() + " no corresponde al turno " + turnoSeleccionado);
                                        document.getElementById('botonConfirmar').disabled = true;
                                    } else {
                                        habilitarBotonConfirmar2(true);
                                    }
                                    break;
                                case "<%=4%>": // 4- 06:00 am - 06:00 pm
                                    if (horaSeleccionada.getHours() < 6 || horaSeleccionada.getHours() >= 18) {
                                        alert("La hora seleccionada no corresponde al turno " + turnoSeleccionado);
                                        document.getElementById('botonConfirmar').disabled = true;
                                    } else {
                                        habilitarBotonConfirmar2(true);
                                    }
                                    break;
                                case "<%=5%>":// 5- 06:00 pm - 06:00 am
                                    if (horaSeleccionada.getHours() < 18  && horaSeleccionada.getHours() >= 6) {
                                        alert("La hora seleccionada no corresponde al turno " + turnoSeleccionado);
                                        document.getElementById('botonConfirmar').disabled = true;
                                        horaValida = false;
                                    } else {
                                        habilitarBotonConfirmar2(true);
                                    }
                                    break;
                                case "<%=6%>":// 6- 06:00 am - 04:00 pm
                                    if (horaSeleccionada.getHours() < 6 || horaSeleccionada.getHours() >= 16) {
                                        alert("La hora seleccionada no corresponde al turno " + turnoSeleccionado);
                                        document.getElementById('botonConfirmar').disabled = true;
                                        horaValida = false;
                                    } else {
                                        habilitarBotonConfirmar2(true);
                                    }
                                    break;
                                case "<%=7%>":
                                    if (horaSeleccionada.getHours() < 7 || horaSeleccionada.getHours() >= 17) {
                                        alert("La hora seleccionada no corresponde al turno " + turnoSeleccionado);
                                        document.getElementById('botonConfirmar').disabled = true;
                                        horaValida = false;
                                    } else {
                                        habilitarBotonConfirmar2(true);
                                    }
                                    break;
                                case "<%=8%>":
                                    if (horaSeleccionada.getHours() < 14 || horaSeleccionada.getHours() >= 22) {
                                        alert("La hora seleccionada no corresponde al turno " + turnoSeleccionado);
                                        document.getElementById('botonConfirmar').disabled = true;
                                        horaValida = false;
                                    } else {
                                        habilitarBotonConfirmar2(true);
                                    }
                                    break;
                                case "<%=9%>":
                                    if (horaSeleccionada.getHours() < 22 && horaSeleccionada.getHours() >= 6) {
                                        alert("La hora seleccionada " + horaSeleccionada.getHours() + " no corresponde al turno " + turnoSeleccionado);
                                        document.getElementById('botonConfirmar').disabled = true;
                                        horaValida = false;
                                    } else {
                                        habilitarBotonConfirmar2(true);
                                    }
                                    break;
                                case "<%=10%>":
                                    if (horaSeleccionada.getHours() < 6 || horaSeleccionada.getHours() >= 15) {
                                        alert("La hora seleccionada no corresponde al turno " + turnoSeleccionado);
                                        document.getElementById('botonConfirmar').disabled = true;
                                        horaValida = false;
                                    } else {
                                        habilitarBotonConfirmar2(true);
                                    }
                                    break;
                                case "<%=11%>":
                                    if (horaSeleccionada.getHours() < 8 || horaSeleccionada.getHours() >= 16) {
                                        alert("La hora seleccionada " + horaSeleccionada.getHours() + " no corresponde al turno " + turnoSeleccionado);
                                        document.getElementById('botonConfirmar').disabled = true;
                                        horaValida = false;
                                    } else {
                                        habilitarBotonConfirmar2(true);
                                    }
                                    break;
                             
                            }

                          
                        }



                        function obtenerFechaFinMaquina() {
                            document.getElementById('botonConfirmar').disabled = false;
                            var idMaquina = document.getElementById('listaMaquinas').value;
                            $.post('TouchPrincipalServlet', {xopcion: 'printFechaFinMaquina', idMaquina: idMaquina}, function (e) {
                                $('#xFechaFin').val(e);
                                habilitarBotonConfirmar();
                            });
                        }
                        function obtenerPesoBascula() {
                            $.post('TouchPrincipalServlet', {xopcion: 'printPesoBascula'}, function (e) {
                                if (e.toString() == '0.0') {
                                    alert("No hay mediciones nuevas en buffer de pesos bascula");

                                } else {

                                    $('#xPesoTerminado').val(e);
                                }
                            });
                        }

                        $(function () {
                            $('#xFechaHoraFin').datetimepicker({
                                showSecond: true,
                                timeFormat: 'hh:mm:ss'
                            });
                        });


                    </script>
                    <script src="resourcs/js/fontawesome-all.min.js"></script>
