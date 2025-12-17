// --------------------- P.O.T. --------------------- //
    raiz = new Node("","VENTAS MOVILES");
raiz.addBranch(new Node("","Cliente"));
raiz.addBranch(new Node("","Proveedor"));
raiz.addBranch(new Node("","Catalogos"));
raiz.addBranch(new Node("","Administrador"));
raiz.addBranch(new Node("","Reportes"));
raiz.addBranch(new Node("","Utilidades"));
raiz.addBranch(new Node("","Maestros"));
raiz.addBranch(new Node("","Inventario"));
//raiz.addBranch(new Node("","Compras"));
raiz.addBranch(new Node("","Factura Elec"));
raiz.addBranch(new Node("","Salida Segura"));

// ---------------- Cliente Rapido ----------------- //
nodo = getFromRoot(0);   // Cliente Rapido
nodo.addBranch(new Node("","Selecciona","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaSelecciona.ctr"));
nodo.addBranch(new Node("","Datos Generales","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaDato.ctr"));
nodo.addBranch(new Node("","CxC Detallada","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaCxC.ctr"));
nodo.addBranch(new Node("","Cotizacion","../GralControladorServlet?nombrePaginaRequest=/potPermisoCotizacion.ctr"));
nodo.addBranch(new Node("","CotizacionFicha","../GralControladorServlet?nombrePaginaRequest=/potPermisoCotizacion2.ctr"));
nodo.addBranch(new Node("","Pago Planilla","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaPagoCxCPlanilla.ctr"));
nodo.addBranch(new Node("","Historico Pago","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaHPagoCxC.ctr"));
nodo.addBranch(new Node("","Historico Facturas","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaHPedido.ctr"));
//nodo.addBranch(new Node("","Venta Mostrador","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaVenta.ctr"));
//nodo.addBranch(new Node("","Elaborar Pedido","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaElabora.ctr"));
nodo.addBranch(new Node("","Pedido","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOrdenTrabajo.ctr"));
nodo.addBranch(new Node("","Pedido x Estado","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOTEstado.ctr"));
nodo.addBranch(new Node("","Ficha","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmFichaTecnica.ctr"));
//nodo.addBranch(new Node("","O.T. Proceso","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaSuspendido.ctr"));
//nodo.addBranch(new Node("","O.T. Progreso","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOrdenTrabajoProgreso.ctr"));
//nodo.addBranch(new Node("","O.T. Cliente","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOrdenTrabajoActivo.ctr"));
nodo.addBranch(new Node("","Produccion","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOTProducto.ctr"));
nodo.addBranch(new Node("","Editar Produccion","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOTProducto2.ctr"));
nodo.addBranch(new Node("","Touch","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOTProductoTouch.ctr"));
nodo.addBranch(new Node("","Programacion","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOTPrograma.ctr"));
nodo.addBranch(new Node("","Prog Produccion","../GralControladorServlet?nombrePaginaRequest=/potPermisoProgramacionOTProduccion.ctr"));
//nodo.addBranch(new Node("","Costos","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOTCosto.ctr"));
nodo.addBranch(new Node("","Externo(E/S)","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmExterno.ctr"));
nodo.addBranch(new Node("","Remision/Factura","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmRemisionFactura.ctr"));
//nodo.addBranch(new Node("","Cotizar","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaCotiza.ctr"));
nodo.addBranch(new Node("","Nota Credito","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaNota.ctr"));
//nodo.addBranch(new Node("","Historico Remesa","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaHRemesa.ctr"));
//nodo.addBranch(new Node("","Remesa","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaRemesa.ctr"));

// ---------------- Proveedor ----------------- //
nodo = getFromRoot(1);   // Proveedor
nodo.addBranch(new Node("","Selecciona","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaSeleccionaProveedor.ctr"));
nodo.addBranch(new Node("","Datos Generales","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaProveedorDato.ctr"));
nodo.addBranch(new Node("","CxP Detallada","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaProveedorCxC.ctr"));
nodo.addBranch(new Node("","Pago CxP","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmProveedorPagoCxPPlanilla.ctr"));
nodo.addBranch(new Node("","Historico Pago","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaHPagoCxP.ctr"));
nodo.addBranch(new Node("","Historico Compra","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmEmpresaHPedidoProveedor.ctr"));
nodo.addBranch(new Node("","Elabora Orden","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOrdenCompra.ctr"));
//nodo.addBranch(new Node("","Recibo Compra","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmReciboCompra.ctr"));
nodo.addBranch(new Node("","Legaliza Compra","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmResurtidoCompra.ctr"));
nodo.addBranch(new Node("","Legaliza Recepcion","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmResurtidoRecepcion.ctr"));
nodo.addBranch(new Node("","Legaliza Suministro","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmSuministro.ctr"));
nodo.addBranch(new Node("","Orden Servicio","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmOrdenServicio.ctr"));
nodo.addBranch(new Node("","Nota Credito","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmResurtidoNota.ctr"));

// ------------------- Salida Segura --------------------- //
nodo = getFromRoot(2);  // Catalogos
nodo.addBranch(new Node("","Tercero"));
nodo.addBranch(new Node("","Comercial"));
nodo.addBranch(new Node("","Usuario"));
nodo.addBranch(new Node("","Contable"));
nodo.addBranch(new Node("","P.O.T."));

//
nodo = getFromRoot(2,0); // Catalogos Tercero
nodo.addBranch(new Node("","Cliente","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoCliente.ctr"));
nodo.addBranch(new Node("","Cliente(+)","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoClienteLocal.ctr"));
nodo.addBranch(new Node("","Proveedor","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoProveedor.ctr"));

//
nodo = getFromRoot(2,1); // Catalogos Comercial
nodo.addBranch(new Node("","Referencia","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmAdmCatalogoReferencia.ctr"));
nodo.addBranch(new Node("","Material","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmAdmCatalogoMaterial.ctr"));
nodo.addBranch(new Node("","Marca","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmAdmCatalogoMarca.ctr"));
nodo.addBranch(new Node("","Categoria","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoCategoria.ctr"));
nodo.addBranch(new Node("","Linea","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoLinea.ctr"));
nodo.addBranch(new Node("","Combo","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoCombo.ctr"));
nodo.addBranch(new Node("","Ean","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoEan.ctr"));
nodo.addBranch(new Node("","Activa/Inactiva","../GralControladorServlet?nombrePaginaRequest=/potPermisoIncativaReferenciasClientes.ctr"));

//
nodo = getFromRoot(2,2); // Catalogos Usuario
nodo.addBranch(new Node("","Usuario","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoUsuario.ctr"));

//
nodo = getFromRoot(2,3); // Catalogos Contable
//nodo.addBranch(new Node("","Subcuenta","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoSubcuenta.ctr"));
nodo.addBranch(new Node("","Retencion","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmRetencion.ctr"));
nodo.addBranch(new Node("","ReteCree","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmReteCree.ctr"));

//
nodo = getFromRoot(2,4); // Catalogos POT
nodo.addBranch(new Node("","Operario","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoPotOperario.ctr"));
nodo.addBranch(new Node("","Maquina","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoPotMaquina.ctr"));
nodo.addBranch(new Node("","Costo MOD/CIF","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCatalogoPotCosto.ctr"));

// ------------------- Administrador --------------------- //
nodo = getFromRoot(3);  // Administrador
nodo.addBranch(new Node("","Resurtido"));
nodo.addBranch(new Node("","Inventario"));
nodo.addBranch(new Node("","Comision"));
nodo.addBranch(new Node("","Bonificacion"));
nodo.addBranch(new Node("","Cuadre Caja"));
nodo.addBranch(new Node("","Contable"));

//
nodo = getFromRoot(3,0); // Administrador Resurtido
//nodo.addBranch(new Node("","Compra","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmResurtidoCompra.ctr"));
nodo.addBranch(new Node("","Traslado","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmResurtidoTraslado.ctr"));
nodo.addBranch(new Node("","Despacho","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmResurtidoDespacho.ctr"));
nodo.addBranch(new Node("","Autoconsumo","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmResurtidoAutoconsumo.ctr"));

nodo = getFromRoot(3,1); // Administrador Inventario
nodo.addBranch(new Node("","Conteo","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmInventarioConteo.ctr"));
nodo.addBranch(new Node("","Traslado","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmInventarioMovimiento.ctr"));
nodo.addBranch(new Node("","Ajuste M.P.","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmInventarioAjuste.ctr"));
nodo.addBranch(new Node("","Ajuste O.T.","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmInventarioAjusteOT.ctr"));

nodo = getFromRoot(3,2); // Administrador Comision
//nodo.addBranch(new Node("","Senior","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmComisionSenior.ctr"));
nodo.addBranch(new Node("","Recaudo","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmComisionEfectiva.ctr"));
nodo.addBranch(new Node("","Parametros","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmParametroComision.ctr"));

nodo = getFromRoot(3,3); // Administrador Bonificacion
nodo.addBranch(new Node("","Meta Mes","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmBonificacionMes.ctr"));
nodo.addBranch(new Node("","Meta Trimestre","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmBonificacionTrimeste.ctr"));

nodo = getFromRoot(3,4); // Administrador Cuadre Caja
nodo.addBranch(new Node("","Ingreso","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmContableComprobante.ctr&xIdAlcance=4"));
nodo.addBranch(new Node("","Egreso","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmContableComprobante.ctr&xIdAlcance=5"));
nodo.addBranch(new Node("","Cierre","../GralControladorServlet?nombrePaginaRequest=/potPermisoCierreCuadreCaja.ctr"));
nodo.addBranch(new Node("","Reversa Ingreso","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCuadreCajaRevIngreso.ctr&xIdAlcance=4"));
nodo.addBranch(new Node("","Reversa Egreso","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmCuadreCajaRevIngreso.ctr&xIdAlcance=5"));


nodo = getFromRoot(3,5); // Administrador Contable
nodo.addBranch(new Node("","Importar","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmContableImporta.ctr"));

// ------------------- Reportes --------------------- //
nodo = getFromRoot(4);  // Reportes
nodo.addBranch(new Node("","Venta"));
nodo.addBranch(new Node("","Compra"));
nodo.addBranch(new Node("","Inventario"));
nodo.addBranch(new Node("","Resurtido"));
nodo.addBranch(new Node("","Margen"));
nodo.addBranch(new Node("","Cuadre Caja"));
nodo.addBranch(new Node("","Indicador"));
nodo.addBranch(new Node("","P.O.T."));
nodo.addBranch(new Node("","Produccion"));

//
nodo = getFromRoot(4,0); // Reportes Ventas
nodo.addBranch(new Node("","Detalle Ventas","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaIva.ctr"));
nodo.addBranch(new Node("","Detalle Cliente","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepCliente.ctr"));
nodo.addBranch(new Node("","CintaTM Ventas","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaCtm.ctr"));
nodo.addBranch(new Node("","Notas","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaNota.ctr"));
nodo.addBranch(new Node("","Remisiones","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaRemision.ctr"));
nodo.addBranch(new Node("","Detalle CxC","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaCxCDetalle.ctr"));
nodo.addBranch(new Node("","Cliente CxC","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaCxCCliente.ctr"));
nodo.addBranch(new Node("","Recaudos Diarios","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaIngreso.ctr"));
nodo.addBranch(new Node("","Marcas","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaMarca.ctr"));
nodo.addBranch(new Node("","Ventas Referencias","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaReferencias.ctr"));

//
nodo = getFromRoot(4,1); // Reportes Compras
nodo.addBranch(new Node("","Detalle Compras","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepCompraIva.ctr"));
nodo.addBranch(new Node("","Detalle CxP","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepCompraCxPDetalle.ctr"));
nodo.addBranch(new Node("","Pagos Diarios","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepCompraEgreso.ctr"));
nodo.addBranch(new Node("","RefciasProveedor","../GralControladorServlet?nombrePaginaRequest=/potPermisoRefciasProveedor.ctr"));

//
nodo = getFromRoot(4,2); // Reportes Inventarios
nodo.addBranch(new Node("","Referencia","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepInventarioReferencia.ctr"));
nodo.addBranch(new Node("","Categoria","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepInventarioCategoria.ctr"));
//nodo.addBranch(new Node("","Marca","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepInventarioMarca.ctr"));
//nodo.addBranch(new Node("","Operacion","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepInventarioOperacion.ctr"));
nodo.addBranch(new Node("","Kardex","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepInventarioKardex.ctr"));
nodo.addBranch(new Node("","O.T. Operacion","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepInventarioOTOperacion.ctr"));
nodo.addBranch(new Node("","O.T. Externo(E/S)","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepInventarioOTExterno.ctr"));
nodo.addBranch(new Node("","M.P. Operacion","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepInventarioMPOperacion.ctr"));
//nodo.addBranch(new Node("","M.P. Maquina","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepInventarioMPMaquina.ctr"));
nodo.addBranch(new Node("","Ajuste Positivo","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepAjustePositivo.ctr"));

//
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

nodo = getFromRoot(4,5); // Reportes Cuadre Caja
nodo.addBranch(new Node("","Cuadre Caja","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaCuadreCaja.ctr"));
nodo.addBranch(new Node("","Ingresos","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaIngresoCuadreCaja.ctr&xIdAlcance=4"));
nodo.addBranch(new Node("","Egresos","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaIngresoCuadreCaja.ctr&xIdAlcance=5"));

nodo = getFromRoot(4,6); //Auditorias
nodo.addBranch(new Node("","Rentabilidad"));
nodo.addBranch(new Node("","Inventario"));
nodo.addBranch(new Node("","Auditoria"));

nodo = getFromRoot(4,7); //P.O.T.
nodo.addBranch(new Node("","Costo","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepPotCosto.ctr"));
nodo.addBranch(new Node("","Productividad","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepPotProducto.ctr"));
nodo.addBranch(new Node("","Tiempo Perdido","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepPotTiempoPerdido.ctr"));

nodo = getFromRoot(4,8); //Produccion.
nodo.addBranch(new Node("","Retales","../GralControladorServlet?nombrePaginaRequest=vista/Touch/VistaReporteRetal.jsp"));
nodo.addBranch(new Node("","Paros","../GralControladorServlet?nombrePaginaRequest=vista/Touch/VistaReporteParo.jsp"));

nodo = getFromRoot(4,6,0); //Rentabilidad
nodo.addBranch(new Node("","Cliente","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepRentbilidadCliente.ctr"));
nodo.addBranch(new Node("","Marca","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepRentbilidadMarca.ctr"));
nodo.addBranch(new Node("","Vendedor","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepRentbilidadVendedor.ctr"));
nodo.addBranch(new Node("","Vend Plu","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepRentbilidadVendedorPlu.ctr"));
nodo.addBranch(new Node("","Vend Cliente","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepRentbilidadVendedorCliente.ctr"));
nodo.addBranch(new Node("","Proveedor","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepRentbilidadProveedor.ctr"));
nodo.addBranch(new Node("","Zona","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepRentbilidadZonaGeografica.ctr"));
nodo.addBranch(new Node("","Categoria","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepRentbilidadCategoria.ctr"));
nodo.addBranch(new Node("","Lista","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepRentbilidadListaPrecio.ctr"));

nodo = getFromRoot(4,6,1); //Auditorias

nodo.addBranch(new Node("","Sin Ventas","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepPluSinVentas.ctr"));
nodo.addBranch(new Node("","Agotados","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepPluVendosSinInv.ctr"));
nodo.addBranch(new Node("","Mas Vendido","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepPluMasVendidos.ctr"));
nodo.addBranch(new Node("","Rotacion","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepPluRotacion.ctr"));


nodo = getFromRoot(4,6,2); //Auditorias

nodo.addBranch(new Node("","Cambio Precio","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepCambioPrecio.ctr"));
nodo.addBranch(new Node("","Dscto Plu","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepPluDcto.ctr"));
nodo.addBranch(new Node("","Dscto Fra.","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepDesctoFac.ctr"));
nodo.addBranch(new Node("","Notas","../GralControladorServlet?nombrePaginaRequest=/potPermisoRepVentaNota.ctr"));
// ------------------- Utilidades --------------------- //
nodo = getFromRoot(5);  // Utilidades
nodo.addBranch(new Node("","Precios Venta","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmUtilPrecioVenta.ctr"));
nodo.addBranch(new Node("","Contrase&ntilde;a","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmUtilClave.ctr"));
nodo.addBranch(new Node("","Inactivar Proceso","../GralControladorServlet?nombrePaginaRequest=/potPermisoAdmUtilInactivo.ctr"));
nodo.addBranch(new Node("","Copia de Seguridad","../GralControladorServlet?nombrePaginaRequest=/potPermisoCopiaDeSeguridad.ctr"));
nodo.addBranch(new Node("","Ean","../GralControladorServlet?nombrePaginaRequest=/potPermisoEan.ctr"));
//Maestros
nodo = getFromRoot(6);  // Maestros
nodo.addBranch(new Node("","Bodegas","../GralControladorServlet?nombrePaginaRequest=/ControlMaestroBodegas.ctr"));
nodo.addBranch(new Node("","Procesos","../GralControladorServlet?nombrePaginaRequest=/ControlMaestroOperaciones.ctr"));
nodo.addBranch(new Node("","Maquinas","../GralControladorServlet?nombrePaginaRequest=/ControlMaestroMaquinas.ctr"));
nodo.addBranch(new Node("","Paros Maquina","../GralControladorServlet?nombrePaginaRequest=/ControlMaestroParoMaquina.ctr"));
nodo.addBranch(new Node("","Retales","../GralControladorServlet?nombrePaginaRequest=/ControlMaestroRetales.ctr"));
nodo.addBranch(new Node("","Unidades","../GralControladorServlet?nombrePaginaRequest=/ControlMaestroUnidades.ctr"));
nodo.addBranch(new Node("","Calificadores","../GralControladorServlet?nombrePaginaRequest=/ControlMaestroCalificadores.ctr"));
nodo.addBranch(new Node("","Empresa","../GralControladorServlet?nombrePaginaRequest=/ControlMaestroLocales.ctr"));

//nodo.addBranch(new Node("","Impuestos","../GralControladorServlet?nombrePaginaRequest=/ControlMaestroImpuesto.ctr"));

nodo = getFromRoot(7);  //Inventario
//nodo.addBranch(new Node("","Ajustes","../GralControladorServlet?nombrePaginaRequest=/ControlInventarioAjuste.ctr"));
nodo.addBranch(new Node("","Traslados","../GralControladorServlet?nombrePaginaRequest=/ControlInventarioTraslado.ctr"));
//nodo.addBranch(new Node("","Conteo","../GralControladorServlet?nombrePaginaRequest=/ControlInventarioConsulta.ctr"));

//nodo = getFromRoot(8);  //Compras
//nodo.addBranch(new Node("","Orden de Compra","../GralControladorServlet?nombrePaginaRequest=/ControlOrdenCompra.ctr"));
//nodo.addBranch(new Node("","Legalizar Compra","../GralControladorServlet?nombrePaginaRequest=/ControlInventarioAjuste.ctr"));
//nodo.addBranch(new Node("","Devolucion Compra","../GralControladorServlet?nombrePaginaRequest=/ControlInventarioAjuste.ctr"));

nodo = getFromRoot(8);  //Facturacion Electronica
nodo.addBranch(new Node("","Transmision","../GralControladorServlet?nombrePaginaRequest=/ControlFERetransmision.ctr"));

//
nodo = getFromRoot(9); // Salida Segura
nodo.addBranch(new Node("","Terminar","../GralControladorServlet?nombrePaginaRequest=/gralPermisosTerminar.ctr"));
console.log(raiz);