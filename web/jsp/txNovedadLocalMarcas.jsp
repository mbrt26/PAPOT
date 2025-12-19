<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import = "com.solucionesweb.losbeans.negocio.MarcaBean" %>
<%@ page import = "com.solucionesweb.losbeans.fachada.FachadaMarcaBean" %>

<%

       // Bean de fachada
       FachadaMarcaBean fachadaMarcaBean = new FachadaMarcaBean();

       MarcaBean marcaBean = new MarcaBean();
       String idSeqStr     = request.getParameter("idSeq");
       marcaBean.setIdSeq(idSeqStr);

       Vector vectorObjetos = marcaBean.listaMarcaTx();
       Iterator iteratorObjetos;
       iteratorObjetos      = vectorObjetos.iterator();

       // output the vote results
       StringBuffer Results = new StringBuffer();

       Results.append("<documento>");

       while (iteratorObjetos.hasNext()) {
              fachadaMarcaBean = (FachadaMarcaBean)iteratorObjetos.next();

	      Results.append("<marca>");
	      Results.append("<idMarca>"+ fachadaMarcaBean.getIdMarcaStr() +"</idMarca>");
	      Results.append("<nombreMarca>"+ fachadaMarcaBean.getNombreMarca() +"</nombreMarca>");
	      Results.append("<estado>"+ fachadaMarcaBean.getEstadoStr() +"</estado>");
	      Results.append("<idSeq>"+ fachadaMarcaBean.getIdSeqStr() +"</idSeq>");
	      Results.append("</marca>");
       }
       Results.append("</documento>");

       out.println(Results.toString() );

    %>