// --------------------- P.O.T. --------------------- //
    raiz = new Node("","VENTAS MOVILES");
raiz.addBranch(new Node("","Cliente"));
//raiz.addBranch(new Node("","Proveedor"));
//raiz.addBranch(new Node("","Catalogos"));
//raiz.addBranch(new Node("","Administrador"));
raiz.addBranch(new Node("","Reportes"));
raiz.addBranch(new Node("","Utilidades"));
raiz.addBranch(new Node("","Salida Segura"));

// ---------------- Cliente Rapido ----------------- //
nodo = getFromRoot(0);   // Cliente Rapido
nodo.addBranch(new Node("","Selecciona","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaSelecciona.ctr"));
nodo.addBranch(new Node("","Datos Generales","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaDato.ctr"));
nodo.addBranch(new Node("","CxC Detallada","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaCxC.ctr"));
//nodo.addBranch(new Node("","Pago Planilla","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaPagoCxCPlanilla.ctr"));
//nodo.addBranch(new Node("","Historico Pago","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaHPagoCxC.ctr"));
nodo.addBranch(new Node("","Historico Facturas","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaHPedido.ctr"));
//nodo.addBranch(new Node("","Venta Mostrador","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaVenta.ctr"));
//nodo.addBranch(new Node("","Elaborar Pedido","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaElabora.ctr"));
//nodo.addBranch(new Node("","Pedido","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOrdenTrabajo.ctr"));
nodo.addBranch(new Node("","Pedido x Estado","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOTEstado.ctr"));
nodo.addBranch(new Node("","Ficha","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmFichaTecnica.ctr"));
//nodo.addBranch(new Node("","O.T. Proceso","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaSuspendido.ctr"));
//nodo.addBranch(new Node("","O.T. Progreso","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOrdenTrabajoProgreso.ctr"));
//nodo.addBranch(new Node("","O.T. Cliente","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOrdenTrabajoActivo.ctr"));
nodo.addBranch(new Node("","Produccion","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOTProducto.ctr"));
nodo.addBranch(new Node("","Programacion","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOTPrograma.ctr"));
nodo.addBranch(new Node("","Costos","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOTCosto.ctr"));
nodo.addBranch(new Node("","Externo(E/S)","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmExterno.ctr"));
nodo.addBranch(new Node("","Remision/Factura","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmRemisionFactura.ctr"));
nodo.addBranch(new Node("","Cotizar","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaCotiza.ctr"));
nodo.addBranch(new Node("","Nota Credito","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaNota.ctr"));
nodo.addBranch(new Node("","Historico Remesa","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaHRemesa.ctr"));
nodo.addBranch(new Node("","Remesa","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaRemesa.ctr"));


/* ---------------- Proveedor ----------------- //
//nodo = getFromRoot(1);   // Proveedor
//nodo.addBranch(new Node("","Selecciona","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaSeleccionaProveedor.ctr"));
//nodo.addBranch(new Node("","Datos Generales","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaProveedorDato.ctr"));
//nodo.addBranch(new Node("","CxP Detallada","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaProveedorCxC.ctr"));
nodo.addBranch(new Node("","Pago CxP","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmProveedorPagoCxPPlanilla.ctr"));
nodo.addBranch(new Node("","Historico Pago","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaHPagoCxP.ctr"));
nodo.addBranch(new Node("","Historico Compra","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaHPedidoProveedor.ctr"));
nodo.addBranch(new Node("","Elabora Orden","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOrdenCompra.ctr"));
nodo.addBranch(new Node("","Legaliza Compra","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmResurtidoCompra.ctr"));*/
//nodo.addBranch(new Node("","Nota Credito","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmResurtidoNota.ctr"));


/* ------------------- Salida Segura --------------------- //
nodo = getFromRoot(2);  // Catalogos
nodo.addBranch(new Node("","Tercero"));
nodo.addBranch(new Node("","Comercial"));
nodo.addBranch(new Node("","Usuario"));*/

/*nodo = getFromRoot(2,0); // Catalogos Tercero
nodo.addBranch(new Node("","Cliente","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoCliente.ctr"));
nodo.addBranch(new Node("","Cliente(+)","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoClienteLocal.ctr"));
nodo.addBranch(new Node("","Proveedor","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoProveedor.ctr"));

nodo = getFromRoot(2,1); // Catalogos Comercial
nodo.addBranch(new Node("","Referencia","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmAdmCatalogoReferencia.ctr"));
nodo.addBranch(new Node("","Marca","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmAdmCatalogoMarca.ctr"));
nodo.addBranch(new Node("","Categoria","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoCategoria.ctr"));
nodo.addBranch(new Node("","Linea","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoLinea.ctr"));
nodo.addBranch(new Node("","Combo","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoCombo.ctr"));
nodo.addBranch(new Node("","Ean","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoEan.ctr"));

nodo = getFromRoot(2,2); // Catalogos Usuario
nodo.addBranch(new Node("","Usuario","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoUsuario.ctr"));*/

// ------------------- Administrador --------------------- //
//nodo = getFromRoot(3);  // Administrador
//nodo.addBranch(new Node("","Resurtido"));
//nodo.addBranch(new Node("","Inventario"));
//nodo.addBranch(new Node("","Comision"));
//nodo.addBranch(new Node("","Bonificacion"));
//nodo.addBranch(new Node("","Cuadre Caja"));
//nodo.addBranch(new Node("","Contable"));

//
//nodo = getFromRoot(3,0); // Administrador Resurtido
//nodo.addBranch(new Node("","Compra","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmResurtidoCompra.ctr"));
//nodo.addBranch(new Node("","Traslado","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmResurtidoTraslado.ctr"));
//nodo.addBranch(new Node("","Despacho","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmResurtidoDespacho.ctr"));
//nodo.addBranch(new Node("","Autoconsumo","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmResurtidoAutoconsumo.ctr"));

//nodo = getFromRoot(3,1); // Administrador Inventario
//nodo.addBranch(new Node("","Conteo","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmInventarioConteo.ctr"));
//nodo.addBranch(new Node("","Interno","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmInventarioMovimiento.ctr"));
//nodo.addBranch(new Node("","Ajuste","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmInventarioAjuste.ctr"));

//nodo = getFromRoot(3,0); // Administrador Comision
//nodo.addBranch(new Node("","Senior","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmComisionSenior.ctr"));
//nodo.addBranch(new Node("","Cartera Efectiva","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmComisionEfectiva.ctr"));

/*nodo = getFromRoot(3,1); // Administrador Bonificacion
nodo.addBranch(new Node("","Meta Mes","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmBonificacionMes.ctr"));
nodo.addBranch(new Node("","Meta Trimestre","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmBonificacionTrimeste.ctr"));*/

//nodo = getFromRoot(3,2); // Administrador Contable
//nodo.addBranch(new Node("","Importar","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmContableImporta.ctr"));


// ------------------- Reportes --------------------- //
nodo = getFromRoot(1);  // Reportes
nodo.addBranch(new Node("","Ventas"));
/*nodo.addBranch(new Node("","Compras"));
nodo.addBranch(new Node("","Inventarios"));
nodo.addBranch(new Node("","Resurtido"));
nodo.addBranch(new Node("","Margen"));
nodo.addBranch(new Node("","Auditoria"));*/
nodo.addBranch(new Node("","P.O.T."));

nodo = getFromRoot(1,0); // Reportes Ventas
/*nodo.addBranch(new Node("","Detalle Ventas","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaIva.ctr"));
nodo.addBranch(new Node("","Notas","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaNota.ctr"));
nodo.addBranch(new Node("","Detalle CxC","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaCxCDetalle.ctr"));
nodo.addBranch(new Node("","Cliente CxC","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaCxCCliente.ctr"));
nodo.addBranch(new Node("","Recaudos Diarios","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaIngreso.ctr"));
nodo.addBranch(new Node("","Marcas","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaMarca.ctr"));*/
nodo.addBranch(new Node("","Detalle Cliente","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepCliente.ctr"));
nodo.addBranch(new Node("","Remisiones","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaRemision.ctr"));

/*
nodo = getFromRoot(4,1); // Reportes Compras
nodo.addBranch(new Node("","Detalle Compras","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepCompraIva.ctr"));
nodo.addBranch(new Node("","Detalle CxP","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepCompraCxPDetalle.ctr"));
nodo.addBranch(new Node("","Pagos Diarios","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepCompraEgreso.ctr"));


nodo = getFromRoot(4,2); // Reportes Inventarios
nodo.addBranch(new Node("","Referencia","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepInventarioReferencia.ctr"));
nodo.addBranch(new Node("","Categoria","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepInventarioCategoria.ctr"));
nodo.addBranch(new Node("","Marca","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepInventarioMarca.ctr"));
nodo.addBranch(new Node("","Kardex","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepInventarioKardex.ctr"));
nodo.addBranch(new Node("","Ajuste Positivo","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepAjustePositivo.ctr"));
nodo.addBranch(new Node("","Ajuste Negativo","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepAjusteNegativo.ctr"));

nodo = getFromRoot(4,3); // Reportes Resurtido
nodo.addBranch(new Node("","Compra","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepResurtidoCompra.ctr"));
nodo.addBranch(new Node("","Traslado","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepResurtidoTraslado.ctr"));
nodo.addBranch(new Node("","Despacho","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepResurtidoDespacho.ctr"));
nodo.addBranch(new Node("","Autoconsumo","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepResurtidoAutoconsumo.ctr"));

//
nodo = getFromRoot(4,4); // Reportes Margen
nodo.addBranch(new Node("","Margen Factura","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepMargen.ctr"));
nodo.addBranch(new Node("","Margen Plu","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepMargenPlu.ctr"));
nodo.addBranch(new Node("","Costos","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaCosto.ctr"));

nodo = getFromRoot(4,5); //Auditorias
nodo.addBranch(new Node("","Cambio Precio","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepCambioPrecio.ctr"));
nodo.addBranch(new Node("","Sin Ventas","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepPluSinVentas.ctr"));
nodo.addBranch(new Node("","Agotados","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepPluVendosSinInv.ctr"));
nodo.addBranch(new Node("","Mas Vendido","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepPluMasVendidos.ctr"));
nodo.addBranch(new Node("","Rotacion","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepPluRotacion.ctr"));
nodo.addBranch(new Node("","Dscto Plu","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepPluDcto.ctr"));
nodo.addBranch(new Node("","Dscto Fra.","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepDesctoFac.ctr"));
nodo.addBranch(new Node("","Rta.Cliente","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepRentbilidadCliente.ctr"));
nodo.addBranch(new Node("","Rta.Marca","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepRentbilidadMarca.ctr"));
nodo.addBranch(new Node("","Rta.Vendedor","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepRentbilidadVendedor.ctr"));
nodo.addBranch(new Node("","Rta.Prveedor","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepRentbilidadProveedor.ctr"));
nodo.addBranch(new Node("","Rta.ZonGeog","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepRentbilidadZonaGeografica.ctr"));
nodo.addBranch(new Node("","Rta.Categoria","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepRentbilidadCategoria.ctr"));
nodo.addBranch(new Node("","Rta.Lista","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepRentbilidadListaPrecio.ctr"));
*/

nodo = getFromRoot(1,1); //P.O.T.
nodo.addBranch(new Node("","Costo","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepPotCosto.ctr"));
nodo.addBranch(new Node("","Productividad","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepPotProducto.ctr"));
nodo.addBranch(new Node("","Tiempo Perdido","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepPotTiempoPerdido.ctr"));

// ------------------- Utilidades --------------------- //
nodo = getFromRoot(2);  // Utilidades
nodo.addBranch(new Node("","Precios Venta","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmUtilPrecioVenta.ctr"));
nodo.addBranch(new Node("","Contrase&ntilde;a","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmUtilClave.ctr"));
nodo.addBranch(new Node("","Inactivar Proceso","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmUtilInactivo.ctr"));
nodo.addBranch(new Node("","Copia de Seguridad","../GralControladorServlet?nombrePaginaRequest=/potPermisoCopiaDeSeguridad.ctr"));

//
nodo = getFromRoot(3) // Salida Segura
nodo.addBranch(new Node("","Terminar","../GralControladorServlet?nombrePaginaRequest=/gralPermisosTerminar.ctr"));
