<HTML>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
<link href="/Comercial/styles/estiloTabla.css" rel="stylesheet" type="text/css">
<link href="/Comercial/styles/estiloLetra.css" rel="stylesheet" type="text/css">
<title>Confirmar Datos</title>
</head>

<jsp:useBean id="fachadaAgendaLogVisitaBean" scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean"/>

<jsp:useBean id="fachadaColaboraTerceroBean"
  scope="request"
  type="com.solucionesweb.losbeans.fachada.FachadaColaboraTerceroBean"/>

<jsp:useBean id="day" scope="page" class="com.solucionesweb.losbeans.utilidades.Day">
<jsp:setProperty name="day" property="*"/>
</jsp:useBean>

<BODY>

    <form method="POST" action="GralControladorServlet">

    <table border="0" width="100%">
    <tr>
        <th width="100%" nowrap class="letraTitulo">CONFIRMAR DATOS PEDIDO</strong></font></th>
    </tr>
       <font size="1" face="Verdana">
    </font>
    </table>

  	<table border="1" width="100%">
    <b>
    <tr>
      <td nowrap class="letraTitulo">FECHA DESPACHO</font></b></td>
    </tr>

    <tr>
      <td nowrap class="letraDetalle"> <input name="fechaEntrega" value="<%=day.getFechaFormateada()%>" size="10" maxlength="10"></td>
    </tr>
    <tr>
      <td nowrap class="letraDetalle">#ORDEN COMPRA</td>
    </tr>
    <tr>
      <td nowrap class="letraDetalle"><input name="ordenCompra" size="10" maxlength="10"></font></font></small></small><b><font face="Verdana" size="1"></td>
    </tr>
    <tr>
      <td nowrap class="letraDetalle">CONTACTO</td>
    </tr>
    <tr>
      <td nowrap class="letraDetalle"><input name="contacto" value="<%=fachadaColaboraTerceroBean.getContacto()%>" size="30" maxlength="50">
      </td>
    </tr>
    <tr>
      <td nowrap class="letraDetalle">OBSERVACIONES</td>
    </tr>
    <tr>
      <td class="letraDetalle">	<input name="observacion" size="30" maxlength="100"></td>
    </tr>

    <tr>
      <td nowrap class="letraDetalle">CONTRASEÑA</td>
    </tr>

    <tr>
      <td nowrap class="letraDetalle"><input type="password" name="clave" size="8" maxlength="8"></td>
    </tr>

     <input type="hidden" name="idLog" value="<%=fachadaAgendaLogVisitaBean.getIdLog()%>">

  </table>

    <table border="0" width="100%">
       <tr>
       <font size="1" face="Verdana">
		<input type="submit" value="CONFIRMAR" name="accionContenedor">
        <input type="submit" value="REGRESAR" name="accionContenedor">
        <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaFrmSelClienteFinalizaPedido.jsp">
       </td>
       </font>
       </tr>
    </table>

    </form>
</BODY>
</HTML>