<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import = "com.solucionesweb.losbeans.negocio.CategoriaBean" %>
<%@ page import = "com.solucionesweb.losbeans.fachada.FachadaCategoriaBean" %>

<%

       // Bean de fachada
       FachadaCategoriaBean fachadaCategoriaBean = new FachadaCategoriaBean();

       CategoriaBean categoriaBean = new CategoriaBean();

       String idSeqStr      = request.getParameter("idSeq");
       categoriaBean.setIdSeq(idSeqStr);

       //
       Vector vectorObjetos = categoriaBean.listaCategoriaTx();
       Iterator iteratorObjetos;
       iteratorObjetos      = vectorObjetos.iterator();

       // output the vote results
       StringBuffer Results = new StringBuffer();
       Results.append("<documento>");

       while (iteratorObjetos.hasNext()) {

              fachadaCategoriaBean = (FachadaCategoriaBean)iteratorObjetos.next();

	      Results.append("<categoria>");
	      Results.append("<idLinea>"+ fachadaCategoriaBean.getIdLineaStr() +"</idLinea>");
	      Results.append("<idCategoria>"+ fachadaCategoriaBean.getIdCategoriaStr() +"</idCategoria>");
	      Results.append("<nombreCategoria>"+ fachadaCategoriaBean.getNombreCategoria() +"</nombreCategoria>");
	      Results.append("<estado>"+ fachadaCategoriaBean.getEstadoStr() +"</estado>");
	      Results.append("<idSeq>"+ fachadaCategoriaBean.getIdSeqStr() +"</idSeq>");
	      Results.append("</categoria>");

       }
       Results.append("</documento>");

       out.println(Results.toString() );

    %>