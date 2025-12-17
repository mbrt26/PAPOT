<%-- gralPermisoDenegado.jsp --%>

<jsp:useBean id="permisoUsuarioBean"
  scope="request"
  type="com.solucionesweb.losbeans.utilidades.PermisoUsuarioBean" />

<h3>
 Permiso Denegado a la Pagina
</h3>

<h4>Señor(a) Usuario: </h4>
<h4>Usted no tiene acceso a esta pagina</h3>

</h4>

<h4>Usuario Actual: </h4>
<%=permisoUsuarioBean.getIdUsuario()%>

<h4>Nombre Pagina Solicitada: </h4>
<%=permisoUsuarioBean.getNombrePaginaSolicitada()%>

<h4>
 Por favor comuniquese con el administrador del sistema para que le otorgue permiso de acceso
 a esta pagina.
</h4> 
