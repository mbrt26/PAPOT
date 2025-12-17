<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import = "com.solucionesweb.losbeans.negocio.UsuarioRutaBean" %>
<%@ page import = "com.solucionesweb.losbeans.fachada.FachadaUsuarioBean" %>

<%

       // Bean de fachada
       FachadaUsuarioBean fachadaBean;

       UsuarioRutaBean usuarioRutaBean = new UsuarioRutaBean();

       //
       String idSeqStr = request.getParameter("idSeq");

       //
       usuarioRutaBean.setIdSeq(idSeqStr);

       Vector vectorObjetos = usuarioRutaBean.listaUsuarioRutaTx();

       //
       Iterator iteratorObjetos;
       iteratorObjetos      = vectorObjetos.iterator();

       // output the vote results
       StringBuffer Results = new StringBuffer();

       Results.append("<documento>");

       while (iteratorObjetos.hasNext()) {

              fachadaBean = (FachadaUsuarioBean)iteratorObjetos.next();

              Results.append("<usuarioRuta>");
	          Results.append("<idUsuario>"+ fachadaBean.getIdUsuarioSf0() +"</idUsuario>");
	          Results.append("<idRuta>"+ fachadaBean.getIdRuta() +"</idRuta>");
	          Results.append("<estado>"+ fachadaBean.getEstadoStr() +"</estado>");
	          Results.append("<idSeq>"+ fachadaBean.getIdSeqStr() +"</idSeq>");
	          Results.append("<indicadorInicial>"+ fachadaBean.getIndicadorInicialStr() +"</indicadorInicial>");
	          Results.append("<indicadorFinal>"+ fachadaBean.getIndicadorFinalStr() +"</indicadorFinal>");
	          Results.append("</usuarioRuta>");

       }
       Results.append("</documento>");

       out.println(Results.toString() );

    %>