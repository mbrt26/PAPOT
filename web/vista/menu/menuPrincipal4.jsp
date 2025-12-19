<%@page import="co.tecnnova.modelo.parametro.empresa.EmpresaDTO"%>
<%@page import="co.tecnnova.modelo.parametro.empresa.EmpresaDAO"%>
<%@page import="co.tecnnova.modelo.usuario.UsuarioDAO"%>
<%@page import="co.tecnnova.modelo.usuario.UsuarioDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="imagenes/icono.ico">
    <title>Plasticos Union</title>                     

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="resourcs/css/fontawesome-all.min.css" type='text/css'>

    <link href="resourcs/css/bootstrap.min_old.css" rel="stylesheet" type="text/css">
    <link href="resourcs/css/estilos.css" rel="stylesheet" type="text/css">
</head>
<%@ taglib uri="/WEB-INF/tlds/listaOpcion" prefix="lst"%>
<%@ taglib uri="/WEB-INF/tlds/listaUnNivel" prefix="lsp"%>

<jsp:useBean id="ldto"
             scope="request"
             type="co.tecnnova.modelo.login.LoginDTO" />
<% session.setAttribute("ldto", ldto);%>
<%
    HttpSession sesion = request.getSession();

    UsuarioDAO udao
            = (UsuarioDAO) sesion.getAttribute("udao");

    String idUsuario = udao.getSkUsuarioStr();
    UsuarioDTO udto = new UsuarioDTO();
    udao.setSkUsuario(udao.getSkUsuarioStr());
    udto = udao.listaUsuario();
    sesion.setAttribute("udto", udto);
    //EmpresaDAO edao = new EmpresaDAO();
    //  EmpresaDTO lb = new EmpresaDTO();

    //    lb = edao.listaLocalResolucionResolucion();
%>
<body  id="page-top" onload="fecha()">
    <!--form method="post" action="Commercial">
        <input type="hidden" name="nombrePaginaRequest" value="/jsp/menuPrincipal.jsp"-->
    <!--nav class="navbar" data-spy="affix" data-offset-top="197">
        <div class="container-fluid">                
            <div class="col-xs-2 "><img src="./resourcs/imagenes/encabezado.png" alt="..." width="180" height="50"  class="img-rounded "></div>
            <div class="col-xs-6 col-xs-offset-1"><label class="h6 "><!%=lb.getNombreEmpresa()%> - <!%=lb.getCiudad()%></label></div>
            <div class="col-xs-4 "><label class="h6 text-center"><!%=ldto.getNombreUsuario()%><br><text id="fecha"></label></div>
        </div>
    </nav-->        
    <div class="container-fluid">            
        <div class="row">
            <div class="menu col-xs-2" style="overflow:auto"> 
                <nav>
                    <div class="nav-side-menu"  data-role="collapsible" data-collapsed="false"  data-mini="true">                            
                        <div class="brand"><img src="/imagenes/logoPlasticosUnion2.PNG" alt="..." width="180" height="50"></div>
                        <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
                        <div class="menu-list">
                            <lst:listaOpcion idTipoOpcionTag="<%=ldto.getIdNivel()%>">
                                <ul id="menu-content" class="menu-content collapse out">

                                    <li  data-toggle="collapse" data-target="#<%=nombreOpcionPadreVar%>" class="collapsed">
                                        <a href="#"><i class="fa fa-<%=iconoVar%> fa-lg"></i>&nbsp;<%=nombreOpcionPadreVar%></a>
                                    </li>

                                    <ul class="sub-menu collapse" id="<%=nombreOpcionPadreVar%>">
                                        <lsp:listaUnNivel idOpcionTag ="<%=idOpcionPadreVar%>"
                                        idPerfilTag ="<%=ldto.getIdNivelStr()%>">
                                            <li><a href="<%=rutaOpcionVar%>" rel="external"  target="workarea"><%=nombreOpcionVar%></a></li>                                                
                                            </lsp:listaUnNivel>
                                    </ul>
                                </lst:listaOpcion>
                            </ul>
                        </div>
                    </div>
                </nav>                   
            </div>
            <div class="col-xs-10 embed-container">
                <iframe src="/vista/menu/empty.html" width="560" height="315" frameborder="0" allowfullscreen name="workarea"></iframe>
            </div>
        </div>
    </div>
    <script src="resourcs/js/jquery.min.js"></script>
    <script src="resourcs/js/fontawesome-all.min.js"></script>
    <script src="resourcs/js/bootstrap.min_old.js"></script>
    <script type="text/javascript">
    function fecha() {
        var d = new Date();
        var dia = new Array(7);
        dia[0] = "Domingo";
        dia[1] = "Lunes";
        dia[2] = "Martes";
        dia[3] = "Miercoles";
        dia[4] = "Jueves";
        dia[5] = "Viernes";
        dia[6] = "Sabado";

        //var mm=new Date();
        var m2 = d.getMonth() + 1;
        var mesok = (m2 < 10) ? '0' + m2 : m2;
        var mesok = new Array(12);
        mesok[0] = "Enero";
        mesok[1] = "Febrero";
        mesok[2] = "Marzo";
        mesok[3] = "Abril";
        mesok[4] = "Mayo";
        mesok[5] = "Junio";
        mesok[6] = "Julio";
        mesok[7] = "Agosto";
        mesok[8] = "Septiembre";
        mesok[9] = "Octubre";
        mesok[10] = "Noviembre";
        mesok[11] = "Diciembre";

        //alert("fecha" + dia[d.getDay()]);



        //document.write("Hoy es: " + dia[d.getDay()]);
        document.getElementById('fecha').innerHTML = dia[d.getDay()] + ' ' + d.getDate() + ' ' + mesok[d.getMonth()] + ' ' + d.getFullYear();
    
    }
    ;
    </script>
</body>
</html>
