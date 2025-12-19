<html>
<%@ taglib uri="/WEB-INF/tlds/listaClientesVendedorCadena" prefix="lista" %>
<jsp:useBean id="fachadaColaboraAgendaControlBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaColaboraAgendaControlBean" />

  <head>
    <title>Confirmar Frecuencia</title>
    <link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
    <link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
  </head>
  <body>

    <%
       String idClienteAll  = "";
    %>
    <font face="Verdana" size="2">

    </font>

    <form method="POST" action="GralControladorServlet">

    <table border="0" width="100%">
    <tr>
        <th width="100%" nowrap colspan="7">
		<p align="left"><font face="Verdana" size="2" color="#0000A0"><strong>CONFIRMAR FRECUENCIA</strong></font></th>
    </tr>
    </table>
    <table border="1" width="100%">
      <tr>
        <td width="100%" class="letraTitulo">NOMBRE CLIENTE</td>
      </tr>

    <lista:listaClientesVendedorCadena idUsuarioTag = "<%=fachadaColaboraAgendaControlBean.getIdUsuarioStr()%>"
                                 idClienteCadenaTag = "<%=fachadaColaboraAgendaControlBean.getIdClienteCadena()%>">

  <tr>
    <td width="100%" class="letraDetalle"><%=idClienteVar.trim() + '-' + nombreClienteVar.trim() %></td>
  </tr>
    <% idClienteAll += idClienteVar.trim() + "," ; %>
    </lista:listaClientesVendedorCadena>

    </table>


    <table border="1" width="100%">
    <tr>
        <td width="100%" class="letraTitulo">PERIODO VISITA</td>
    </tr>
    </table>

    <table border="0" width="23%">
    <tr>
        <td width="14%" class="letraDetalle">SEMANA</td>
        <td width="14%" class="letraDetalle">
			<input type="radio"  name="idFrecuencia" value="1"></td>
        <td width="14%" class="letraDetalle">
        <td width="14%" class="letraDetalle">QUINCENA</td>

        <td width="14%" class="letraDetalle">
		<font size="3" class="letraDetalle">
			<input type="radio"  name="idFrecuencia" value="2" style="font-weight: 700"></font></td>
        <td width="15%" class="letraDetalle">
		<b>
		<font face="Verdana">MES</font></b></td>
        <td width="14%" class="letraDetalle">
 		    <input type="radio"  name="idFrecuencia" value="3" style="font-weight: 700"></font></td>
        <td width="15%" nowrap align="left" valign="top" height="100%">&nbsp;</td>
    </tr>
    </table>

    <table border="1" width="100%">
    <tr><td width="100%" class="letraTitulo">DÍA VISITA</font></b></td>
    </tr>
    </table>

    <table border="0" width="28%">
    <tr>
        <td width="11%"  class="letraDetalle">LU
		<input type="radio"  name="idDiaVisita" value="2"></td>
        <td width="11%"  class="letraDetalle">MA
		<input type="radio"  name="idDiaVisita" value="3"></td>
        <td width="11%"  class="letraDetalle">MI
		<input type="radio"  name="idDiaVisita" value="4"></td>
        <td width="11%"  class="letraDetalle">JU
		<input type="radio"  name="idDiaVisita" value="5"></td>
        <td width="11%"  class="letraDetalle">VI
		<input type="radio"  name="idDiaVisita" value="6"></td>
        <td width="11%"  class="letraDetalle">SA
		<input type="radio"  name="idDiaVisita" value="7"></td>
    </tr>

    </table>

      <table border="0" width="100%">
        <tr>
            <b><font face="Verdana"><input type="submit" value="Confirmar Frecuencia" name="accionContenedor"></font></b><font face="Verdana" size="2"></td>
        	</font>
        </tr>
        <tr>
            <b><font face="Verdana"><input type="submit" value="Regresar" name="accionContenedor"></font></b><font face="Verdana" size="2"></td>
        	</font>
        </tr>

       	<font face="Verdana">
       <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelAgendaProgramar.jsp">
       <input type="hidden" name="txtIdClienteAll" value="<%=idClienteAll%>">
    	</font>
    </table>

  </form>

  </body>
</html>