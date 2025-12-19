<html>
    <%@ taglib uri="/WEB-INF/tlds/listaUnAllCategoria" prefix="lst" %>

    <head>
        <title>Catalogo Linea</title>
        <link href="./styles/estiloTabla.css" rel="stylesheet" type="text/css">
        <link href="./styles/estiloLetra.css" rel="stylesheet" type="text/css">
        <link href="resourcs/css/parametrosGenerales.css" rel="stylesheet" type="text/css">
        <link href="resourcs/css/bootstrap-datepicker.min.css" rel="stylesheet"/>
        <link rel="stylesheet" href="resourcs/css/bootstrap.min.css">
    </head>
    <body>
        <form method="POST" action="GralControladorServlet">
            <input type="hidden" name="nombrePaginaRequest" value="/jsp/vtaContenedorCatalogoReferencia.jsp">
            <div class="page-header">
                <div class="h5 titulo bg-success" style="font-size: 24px;"></i>Catálogo Referencia</div>
            </div>
            <!--
            <jsp:include page="./comboLocal.jsp"/>
            <jsp:include page="./comboFechaHoy.jsp"/>
            -->
            <div class="tab-content">
                <div id="Catalogo Referencia" class="container tab-pane   active  col-12">
                    <div class="card card-body texO-white badge-light mb-3 card border-primary mb-3">
                        <div class="panel panel-primary">
                            <div class="header"><div class="panel-title" style="font-weight: bold"> </div>
                                <div class="panel-body">
                                    <div class="input-group mb-3 input-group-sm" style="display: flex; justify-content: center;">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text btn btn-primary" style="font-weight: bold">Categoría</span>
                                            <jsp:include page="./comboAllCategoria.jsp"/>
                                            <span class="input-group-text btn btn-primary" style="font-weight: bold">Destinación</span>
                                            <jsp:include page="./comboDestinacion.jsp"/>
                                            <span class="input-group-text" style="font-weight: bold">Referencia</span>
                                            <input type="text" name="xIdPlu" value="" size="10">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class = "input-group mb-3 input-group-sm mx-auto btn-group" style="display: flex; justify-content: center;" aria-label="Basic checkbox toggle button group">
                <button id ="botonTraerReferencia" type="submit" name="accionContenedor" class="btn btn-success btn-sm col-sm-1" value="Traer">
                    <span class="fa fa-plus-circle"></span>Traer
                </button>
                <button id ="botonIngresarReferencia" type="submit" name="accionContenedor" class="btn btn-success btn-sm col-sm-1" value="Ingresar">
                    <span class="fa fa-plus-circle"></span>Ingresar
                </button>
                <button id = "botonConsultarReferencia" type="submit" name="accionContenedor" class="btn btn-success btn-sm col-sm-1" value="Consultar">
                    <span class="fa fa-plus-circle"></span>Consultar
                </button>
                <button id ="botonRegresarReferencia" type="submit" name="accionContenedor" class="btn btn-success btn-sm col-sm-1" value="Regresar">
                    <span class="fa fa-plus-circle"></span>Regresar
                </button>
            </div>
        </form>
    </body>
</html>