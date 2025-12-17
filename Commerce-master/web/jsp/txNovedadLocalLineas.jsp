<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import = "com.solucionesweb.losbeans.negocio.LineaBean" %>
<%@ page import = "com.solucionesweb.losbeans.fachada.FachadaLineaBean" %>

<%
       // Bean de fachada
       FachadaLineaBean fachadaLineaBean = new FachadaLineaBean();

       LineaBean lineaBean  = new LineaBean();

       //
       String idSeqStr      = request.getParameter("idSeq");
       lineaBean.setIdSeq(idSeqStr);

       Vector vectorObjetos = lineaBean.listaLineaTx();

       //
       Iterator iteratorObjetos;
       iteratorObjetos      = vectorObjetos.iterator();

       // output the vote results
       StringBuffer Results = new StringBuffer();

       Results.append("<documento>");

       while (iteratorObjetos.hasNext()) {
              fachadaLineaBean = (FachadaLineaBean)iteratorObjetos.next();

	          Results.append("<linea>");
	          Results.append("<idLinea>"+ fachadaLineaBean.getIdLineaStr() +"</idLinea>");
	          Results.append("<nombreLinea>"+ fachadaLineaBean.getNombreLinea() +"</nombreLinea>");
	          Results.append("<estado>"+ fachadaLineaBean.getEstadoStr() +"</estado>");
	          Results.append("<idSeq>"+ fachadaLineaBean.getIdSeqStr() +"</idSeq>");
	          Results.append("</linea>");
       }
       Results.append("</documento>");

       out.println(Results.toString() );

    %>