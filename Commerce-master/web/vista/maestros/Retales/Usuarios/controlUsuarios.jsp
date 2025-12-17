<%@page import="co.tecnnova.modelo.estado.EstadoDTO"%>
<%@page import="co.tecnnova.modelo.maestros.tipocausal.tipocausalDTO"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="co.tecnnova.modelo.usuario.UsuarioDTO"%>
<%@page import="co.tecnnova.modelo.usuario.PerfilUsuarioDTO"%>
<%@page import="co.tecnnova.modelo.usuario.PerfilUsuarioDAO"%>
<%@page import="co.tecnnova.modelo.usuario.UsuarioDAO"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Referencia</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="resourcs/css/fontawesome-all.min.css" type='text/css'>
        <link href="resourcs/css/datatables.min.css" rel="stylesheet" type="text/css">
        <link href="resourcs/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
        <link href="resourcs/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css">
        <link href="resourcs/css/bootstrap.min.css" rel="stylesheet" type="text/css">        
        <link href="resourcs/css/parametrosGenerales.css" rel="stylesheet" type="text/css">   
    </head>
    <body onload="FormularioCrearUsuarios()">
        <form method="post" action="Commercial">
            <input type="hidden" name="nombrePaginaRequest" value="/vistas/usuario/controlUsuarios.jsp">
            <div class="h6 titulo" >MODULO USUARIO</div>
            <div class="container col-12">
                <ul class="nav nav-tabs">
                    <li class="nav-item ">
                        <a  class="nav-link active" data-toggle="tab" style="font-weight: bold;" onclick="FormularioCrearUsuarios()" href="#Crear_usuario">CREAR USUARIO</a>                        
                    </li>
                    <!--li class="nav-item">
                        <a class="nav-link"  data-toggle="tab" style="font-weight: bold;"  href="#Modifica_usuario">LISTA USUARIOS</a>
                    </li-->
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" style="font-weight: bold;" onclick="Perfiles_Usuario()" href="#Perfiles_usuario">PERFILES USUARIO</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="container tab-pane active col-12" id="Crear_usuario" > 
                        <div><br></div>
                        <div id="FormularioUsuarios"></div>
                        <!--button type="button" class="btn-primary" onclick=""><i class="fas fas-user"></i></button-->
                    </div>
                    <div class="container tab-pane fade col-12"  id="Modifica_usuario">
                        <div width="100%" align="right">
                            <button type="submit" class="btn-danger" style="margin: -1px ;" name="xoption" value="PDF" title="PDF"><i class="fas fa-file-pdf"></i></button>
                            <button type="submit" class="btn-success" style="margin:" name="xoption" value="EXCEL" title="EXCEL" ><i class="fas fa-file-excel"></i></button>
                        </div> 
                        <div id="detalle"></div>                       
                    </div>
                    <div class="container tab-pane fade col-12" id="Perfiles_usuario">
                        <div><br></div>
                        <div id="detalle2"></div>
                    </div>
                </div>
                <!--div class="modal fade" id="editaUsuario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header tituloModal">
                                <h5 class="modal-title text-md-left">Edita Usuario</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>                                
                            </div>
                            <div class="modal-body container">                                   
                                <div class="tab-content">
                                    <table class="table table-sm table-striped">
                                        <tr>                                       
                                            <td class="col-5"  align="left">CEDULA</td>
                                            <td class="col-7" align="left"> 
                                                <input type="text" class="input-group col-4" name="cedula" id="skcedula"  disabled="true" onKeyPress="return soloNumeros(event)">
                                            </td>                                                
                                        </tr>
                                        <tr>
                                            <td class="col-5"  align="left">NOMBRE</td>
                                            <td class="col-7" align="left"> 
                                                <input type="text" class="input-group col-8 text-uppercase" name="nombre" id="sknombre"   >
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="col-5"  align="left">DIRECCION                                
                                            </td>
                                            <td class="col-7" align="left">
                                                <input type="text" class="input-group col-5 text-uppercase" name="direccion"  id="skdireccion"   >
                                            </td>
                                        </tr>                                                              
                                        <tr>
                                            <td class="col-5" align="left">TELEFONO                            
                                            </td>
                                            <td class="col-7" align="left">
                                                <input type="text"  class="input-group col-5" name="telefono" id="sktelefono"   onKeyPress="return soloNumeros(event)">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="col-5"  align="left">EMAIL                            
                                            </td>
                                            <td class="col-7" align="left">
                                                <input type="text" class="input-group col-10 text-uppercase" name="email"  id="skemail" >
                                            </td>
                                        </tr>
                                        <tr>
                                            <td  class="col-5"  align="left">ALIAS</td>
                                            <td class="col-7" align="left">
                                                <input type="text" class="col-6 text-uppercase text-uppercase"  name="alias" id="skalias" >
                                            </td >
                                        </tr>                                                      
                                        <tr>
                                            <td class="col-5"  align="left">PERFIL</td>
                                            <td class="col-7" align="left">
                                                <select name="skperfil" id="skperfil"> 
                                                    <!%
                                                        ArrayList<PerfilUsuarioDTO> listaperfiles = (ArrayList<PerfilUsuarioDTO>) request.getAttribute("listaperfiles");
                                                        for (PerfilUsuarioDTO l : listaperfiles) {
                                                    %>
                                                    <option value="<!%=l.getSk_perfil()%>"><!%=l.getNombre()%></option>
                                                    <!%}%>
                                                </select>          
                                            </td>
                                        </tr>                                                             
                                        <tr>
                                            <td class="col-5" align="left">ESTADO</td>
                                            <td class="col-7" align="left">
                                                <select name="xEstado" id="estadomodal">
                                                    <!%
                                                        ArrayList<EstadoDTO> listaEstado = (ArrayList<EstadoDTO>) request.getAttribute("listaEstado");
                                                        for (EstadoDTO edta : listaEstado) {
                                                    %>                                    
                                                    <option value="<!%=edta.getSk_estado()%>"><!%=edta.getNombre()%></option>                                    
                                                    <!%}%>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr >
                                            <td width="5%" align="center" width="20%" >
                                                <button type="button" name="acutalizarusuario" onclick="actualizar()" class="btn-primary" id=""  data-dismiss="modal">Guardar</button>
                                            </td>
                                            <td width="5%" align="center" width="20%">
                                                <button type="button" class="btn-danger" data-dismiss="modal">Cerrar</button>
                                            </td>
                                        </tr>
                                    </table>                         
                                </div>                                      
                            </div>                              
                        </div>
                    </div>
                </div-->
                <div class="modal fade" id="Perfiles" tabindex="-1" role="dialog" arial-labelledby="MyModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header tituloModal">
                                <h5 class="modal-title text-md-center">CREAR PERFIL</h5>
                                <button type="button" class="close" data-dismiss="modal" onclick="Principal()" arial-label="Close"><span aria-hidden="true">&times;</span></button>
                            </div>
                            <div class="modal-body container">
                                <table border="0" width="100%" class="table table-sm table-striped">
                                    <thead>
                                        <tr>
                                            <th class="col-2" align="left">CREAR PERFIL</th>
                                            <th class="col-2" align="left">
                                                <input type="text" placeholder="NOMBRE PERFIL" onkeypress="return sololetras(event)" id="xnombrePerfil" class="text-uppercase" value="" style="width: 185px"></th>
                                            <th class="col-2" align="left">
                                                <button type="button" onclick="GuardarNombrePefiles()" class="btn-primary"><i class="fas fa-save"></i></button></th>
                                            <th class="col-2" align="left">&nbsp;</th>
                                        </tr>
                                        <tr class="hidden">
                                            <th class="col-2" align="left"></th>
                                            <th class="col-2" align="left"></th>
                                            <th class="col-2" align="left"></th>
                                            <th class="col-2" align="left"></th>
                                        </tr>
                                    </thead>
                                    <tbody id="ContenidoModal">
                                        <!--tr>
                                            <td class="col-2" align="left" style="font-weight: bold;">ID PERFIL</td>
                                            <td class="col-2" align="left" style="font-weight: bold;">NOMBRE PERFIL</td>
                                            <td class="col-2" align="left" style="font-weight: bold;">ACCION</td>
                                            <td class="col-2" align="left" style="font-weight: bold;">&nbsp;</td>
                                        </tr>
                                        <tr >
                                        </tr-->
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="Usuarios" tabindex="-1" role="dialog" arial-labelledby="MyModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header tituloModal">
                                <h5 class="modal-title text-md-center">USUARIOS</h5>
                                <button type="button" class="close" data-dismiss="modal" arial-label="Close"><span aria-hidden="true">&times;</span></button>
                            </div>
                            <div class="modal-body container">
                                <table border="0" width="100%" class="table table-sm table-striped">
                                    <thead>
                                        <tr>
                                            <th class="col-2" align="left">
                                                <input type="text" placeholder="NOMBRE USUARIO" onkeypress="return sololetras(event)" id="XbuscarUsuario" class="text-uppercase" value="" onkeyup="BuscarUsuarioXNombre()"></th>
                                            <th class="col-2" align="left">&nbsp;</th><th class="col-2" align="left">&nbsp;</th>
                                            <th class="col-2" align="left">&nbsp;</th><th class="col-2" align="left">&nbsp;</th>
                                        </tr>
                                        <tr class="hidden">
                                            <th class="col-2" align="left"></th><th class="col-2" align="left"></th>
                                            <th class="col-2" align="left"></th><th class="col-2" align="left"></th>
                                            <th class="col-2" align="left"></th>
                                        </tr>
                                    </thead>
                                    <thead class="thead-light">
                                        <tr>
                                            <th class="col-2" align="left" style="font-weight: bold;">IDENTIFICACION</th>
                                            <th class="col-2" align="left" style="font-weight: bold;">NOMBRE</th>
                                            <th class="col-2" align="left" style="font-weight: bold;">ALIAS</th>
                                            <th class="col-2" align="left" style="font-weight: bold;">PERFIL</th>
                                            <th class="col-2" align="left" style="font-weight: bold;">ACCION</th>
                                        </tr>
                                    </thead>
                                    <tbody id="ContenidoModalUsuarios">
                                        <!--%
                                            ArrayList<UsuarioDTO> listaUsuarios = (ArrayList<UsuarioDTO>) request.getAttribute("");
                                            for (UsuarioDTO l : listaUsuarios) {
                                        %>
                                        <tr>
                                            <th class="col-2" align="left" style="font-weight: bold;"><!%=l.getSkUsuario()%></th>
                                            <th class="col-2" align="left" style="font-weight: bold;"><!%=l.getNombre()%></th>
                                            <th class="col-2" align="left" style="font-weight: bold;"><!%=l.getAliasUsuario()%></th>
                                            <th class="col-2" align="left" style="font-weight: bold;"><!%=l.getSk_PerfilesP()%></th>
                                            <th class="col-2" align="left" style="font-weight: bold;">
                                                <button type="button" class="btn-primary" onclick="TraerDatosUsuarios(<!%= l.getSkUsuario()%>)"><i class="fas fas-user"></i></button>
                                            </th>
                                        </tr-->
                                        <!--%}%-->
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>       
        </form>
        <script src="resourcs/js/jquery.min.js"></script>
        <script src="resourcs/js/fontawesome-all.min.js"></script>
        <script src="resourcs/js/bootstrap.min.js"></script>
        <script src="resourcs/js/usuario.js"></script>
        <script src="resourcs/js/parametrosGenerales.js"></script>
    </body>
</html>
