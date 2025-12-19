<html lang="es">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Inicio de Sesi�n</title>

        <!-- Custom fonts for this template-->
        <link href="resourcs/css/all.min.css" rel="stylesheet" type="text/css">

        <!-- Custom styles for this template-->
        <link href="resourcs/css/sb-admin.css" rel="stylesheet">

    </head>
    <script language="JavaScript">
        function quitarFrame()
        {
            if (self.parent.frames.length != 0)
                self.parent.location = document.location.href;
        }
        quitarFrame()
    </script>
    <body   style="background-color: rgb(241, 247, 232);">
        <div>&nbsp;</div>
        <div>&nbsp;</div>
        <div class="container">
            <img  class="rounded mx-auto d-block img-responsive" alt="Responsive image" src="/imagenes/logoPlasticosUnion2.PNG" >
            <div class="card card-login mx-auto mt-5">
                <H5 hidden="true" class="text-success" class="card-header" align="center"      ><jsp:include page="./comboLocalLogo.jsp"/></H5>
                <div class="card-header">Inicio de Sesi�n</div>
                <div class="card-body">
                    <form method="POST" action="/GralControladorServlet">
                        <input type="hidden" name="nombrePaginaRequest" value="/jsp/gralFrmLogin.jsp">
                        <div class="form-group">
                            <div class="form-label-group">
                                <input type="text" name="idUsuario" class="form-control" placeholder="Ingreso su Id de Usuario" required="required" autofocus="autofocus">
                                <label for="inputEmail">Usuario</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-label-group">
                                <input type="password" name = "clave" class="form-control" placeholder="Contrase�a" required="required">
                                <label for="inputPassword">Contrase�a</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="remember-me">
                                    Recordar Contrase�a
                                </label>
                            </div>
                        </div>
                        <button class="btn btn-success btn-block" name ="enviar" type="submit">Ingresar</button>
                    </form>
                    <div hidden="true" class="text-center">
                        <a class="d-block small mt-3" href="register.html">Register an Account</a>
                        <a class="d-block small" href="forgot-password.html">Forgot Password?</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="resourcs/js/jquery/jquery.min.js"></script>
        <script src="resourcs/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="resourcs/js/jquery.easing.min.js"></script>

    </body>
    <br>
    <br>
    <br>
    <footer class="text-center">Plasticos Union 2020</footer>

</html>
