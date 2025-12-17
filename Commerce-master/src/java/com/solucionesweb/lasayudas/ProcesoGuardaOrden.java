package com.solucionesweb.lasayudas;

// Importa la clase que contiene el utilidades
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;

// Importa la clase que contiene el ColaboraDctoOrdenDetalleBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;

// Importa la clase que contiene el TerceroBean
import com.solucionesweb.losbeans.negocio.TerceroBean;

// Importa la clase que contiene el PluBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa la clase que contiene el FachadaPluBean
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

public class ProcesoGuardaOrden {


    public ProcesoGuardaOrden () { }
  //
  public DctoOrdenBean guarda(int    xIdLog,
                    int    xIdTipoOrden,
                    double xIdUsuario,
                    int    xIdLocal,
                    String xIdTercero,
                    int    xIdLocalUsuario)  {

          //
          int   xIdOrdenMax             = 0;
          int   xIdOrigenWeb            = 4;
          int   xEstadoDctoOrden        = 1;
          

          //
          Day fechaHoy                  = new Day();

          //
          DctoOrdenBean dctoOrdenBean   = new DctoOrdenBean();

          //
          FachadaDctoOrdenBean fachadaDctoOrdenBean
                                        = new FachadaDctoOrdenBean();

          // Consulta si existeOrden
          dctoOrdenBean.setIdLog(xIdLog);

          //
          fachadaDctoOrdenBean          = dctoOrdenBean.listaDctoOrdenIdLog();

          //
          if (fachadaDctoOrdenBean.getIdOrden()>0) {

             // SI existeOrden
             xIdOrdenMax                = fachadaDctoOrdenBean.getIdOrden();


          } else {

             //
             FachadaTerceroBean fachadaTerceroBean
                                        = new FachadaTerceroBean();

             //
             TerceroBean terceroBean    = new TerceroBean();

             //
             terceroBean.setIdCliente(xIdTercero);

             //
             fachadaTerceroBean         = terceroBean.listaUnTerceroFachada();

             //
             String xEmail              = fachadaTerceroBean.getEmail();
             String xIdFormaPago        =
                                         fachadaTerceroBean.getIdFormaPagoStr();

             // NO existeOrden y se igual idLocal = idLocalInicial
             dctoOrdenBean.setIdLocal(xIdLocalUsuario);
             dctoOrdenBean.setIdTipoOrden(xIdTipoOrden);

             //
             xIdOrdenMax                    =
                                       dctoOrdenBean.maximaIdOrdenIdLocal() + 1;

             //
             dctoOrdenBean.setIdLocal(xIdLocalUsuario);
             dctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
             dctoOrdenBean.setIdOrden(xIdOrdenMax);
             dctoOrdenBean.setFechaOrden(fechaHoy.getFechaFormateada());
             dctoOrdenBean.setEstado(xEstadoDctoOrden);
             dctoOrdenBean.setIdCliente(xIdTercero);
             dctoOrdenBean.setIdUsuario(xIdUsuario);
             dctoOrdenBean.setIdOrigen(xIdOrigenWeb);
             dctoOrdenBean.setIdLog(xIdLog);
             dctoOrdenBean.setFechaEntrega(fechaHoy.getFechaFormateada());
             dctoOrdenBean.setTipoDcto(new Integer(xIdTipoOrden).toString());
             dctoOrdenBean.setEmail(xEmail);
             dctoOrdenBean.setFormaPago(xIdFormaPago);

             //
             boolean okIngreso            = dctoOrdenBean.ingresaDctosOrden();

          }     
          //
          return dctoOrdenBean;

  }

}
