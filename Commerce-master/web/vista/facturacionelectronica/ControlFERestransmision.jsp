<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="resourcs/css/bootstrap.min.css">
        <link href="resourcs/css/parametrosGenerales.css" rel="stylesheet" type="text/css">     
    </head>
    <body onload="ListaFe()">
        <!-- Boton hacia arriba -->
        <div> <a id="ir-arriba" class="ir-arriba"   title="Volver arriba">
                <span class="fa-stack">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-arrow-up fa-stack-1x fa-inverse"></i>
                </span>
            </a></div>
        <!-- Boton hacia arriba -->
        <div> <a id="ir-abajo" class="ir-abajo"   title="Ir Abajo">
                <span class="fa-stack">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-arrow-down fa-stack-1x fa-inverse"></i>
                </span>
            </a></div>
        <div class="page-header">
            <div  class="h5 titulo bg-success" > FACTURACION ELECTRONICA TRANSMISION </div> </div>
        <div id ="tabs" class="container col-12">

            <div id ="alertas"></div>
            <ul class="nav nav-tabs" id="contenedor">
                <li class="nav-item" style="font-weight: bold" >
                    <a class="nav-link active" data-toggle="tab" href="#PRINCIPAL">TRANSMISION</a>
                </li>
                <!--li class="nav-item" style="font-weight: bold">
                    <a class="nav-link" data-toggle="tab" href="#HISTORIAL">HISTORIAL</a>

                </li-->
            </ul>
            <p>

            <p>
            <div class="tab-content">
                <div id="PRINCIPAL" class="container tab-pane   active  col-12">                    
                    <form method="POST" action="GralControladorServlet">
                        <input type="hidden" name="nombrePaginaRequest" value="/vista/facturacionelectronica/ControlFERestransmision.jsp">
                        <input hidden = "true" class="col-1 text-md-center col-md-2" style="font-weight:bold" readOnly="readOnly" id="campoNumeroOrden" name="campoNumeroOrden">

                        <div class="input-group mb-3 input-group-sm">
                            <div class="card card-body  mb-3 bg-transparent col-md-12">                                
                                    <div id = "detalle"></div>
                            </div>
                        </div>                        
                </div>
                </form>
                <br>
            </div>

        </div>
    </div>       

    <script src="resourcs/js/jquery.min.js"></script>
    <script src="resourcs/js/bootstrap.min.js"></script>
    <script src="resourcs/js/popper.min.js"></script>
    <script src="resourcs/js/fe/fe.js"></script>
    <script src="resourcs/js/fontawesome-all.min.js"></script>  
    <script src="resourcs/js/bootstrap-datepicker.min.js"></script>        
</body>

</html>