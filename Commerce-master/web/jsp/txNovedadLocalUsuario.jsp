<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import = "com.solucionesweb.losbeans.negocio.UsuarioBean" %>
<%@ page import = "com.solucionesweb.losbeans.fachada.FachadaUsuarioBean" %>

<%

       // Bean de fachada
       FachadaUsuarioBean fachadaBean;

       UsuarioBean usuarioBean = new UsuarioBean();

       //
       String idSeqStr = request.getParameter("idSeq");

       //
       usuarioBean.setIdSeq(idSeqStr);

       Vector vectorObjetos = usuarioBean.listaUsuarioTx();

       //
       Iterator iteratorObjetos;
       iteratorObjetos      = vectorObjetos.iterator();

       // output the vote results
       StringBuffer Results = new StringBuffer();

       Results.append("<documento>");

       while (iteratorObjetos.hasNext()) {

              fachadaBean = (FachadaUsuarioBean)iteratorObjetos.next();

              Results.append("<usuario>");
	          Results.append("<idUsuario>"+ fachadaBean.getIdUsuarioSf0() +"</idUsuario>");
	          Results.append("<nombreUsuario>"+ fachadaBean.getNombreUsuario() +"</nombreUsuario>");
	          Results.append("<clave>"+ fachadaBean.getClave() +"</clave>");
	          Results.append("<idNivel>"+ fachadaBean.getIdNivelStr() +"</idNivel>");
	          Results.append("<direccion>"+ fachadaBean.getDireccion() +"</direccion>");
	          Results.append("<telefono>"+ fachadaBean.getTelefono() +"</telefono>");
	          Results.append("<fechaCambioClave>"+ fachadaBean.getFechaCambioClave() +"</fechaCambioClave>");
	          Results.append("<estado>"+ fachadaBean.getEstadoStr() +"</estado>");
	          Results.append("<email>"+ fachadaBean.getEmail() +"</email>");
	          Results.append("<idLocal>"+ fachadaBean.getIdLocalUsuarioStr() +"</idLocal>");
	          Results.append("<aliasUsuario>"+ fachadaBean.getAliasUsuario() +"</aliasUsuario>");
	          Results.append("<idSeq>"+ fachadaBean.getIdSeqStr() +"</idSeq>");
	          Results.append("</usuario>");

       }
       Results.append("</documento>");

       out.println(Results.toString() );

    %>