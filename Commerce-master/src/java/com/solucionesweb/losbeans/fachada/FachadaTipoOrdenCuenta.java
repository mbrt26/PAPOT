package com.solucionesweb.losbeans.fachada;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class FachadaTipoOrdenCuenta implements IConstantes {

    //Propiedad
    private int idLocal;
    private int idTipoOrden;
    private String idAsiento;
    private String idSubcuenta;
    private String nombreMovimiento;
    private double porcentajeIva;
    private int estado;
    private int idSeq;
    private String idComprobanteContable;

    //
    public void setIdLocal( int idLocal )
    {
        this.idLocal = idLocal ;
    }

    public int getIdLocal()
    {
        return idLocal;
    }

    public void setIdLocal( String idLocalStr )
    {
        this.idLocal = new Integer(idLocalStr).intValue();
    }

    public String getIdLocalStr()
    {
        return new Integer(idLocal).toString();
    }

    public void setIdTipoOrden( int idTipoOrden )
    {
        this.idTipoOrden = idTipoOrden ;
    }

    public int getIdTipoOrden()
    {
        return idTipoOrden;
    }

    public void setIdTipoOrden( String idTipoOrdenStr )
    {
        this.idTipoOrden = new Integer(idTipoOrdenStr).intValue();
    }

    public String getIdTipoOrdenStr()
    {
        return new Integer(idTipoOrden).toString();
    }

    public void setIdAsiento(String idAsiento) {
        this.idAsiento = idAsiento;
    }

    public String getIdAsiento() {
        return idAsiento;
    }

    public void setIdSubcuenta(String idSubcuenta) {
        this.idSubcuenta = idSubcuenta;
    }

    public String getIdSubcuenta() {
        return idSubcuenta;
    }

    public void setEstado( int estado )
    {
        this.estado = estado ;
    }

    public int getEstado()
    {
        return estado;
    }

    public void setEstado( String estadoStr )
    {
        this.estado = new Integer(estadoStr).intValue();
    }

    public String getEstadoStr()
    {
        return new Integer(estado).toString();
    }

    public void setIdSeq( int idSeq )
    {
        this.idSeq = idSeq ;
    }

    public int getIdSeq()
    {
        return idSeq;
    }

    public void setIdSeq( String idSeqStr )
    {
        this.idSeq = new Integer(idSeqStr).intValue();
    }

    public String getIdSeqStr()
    {
        return new Integer(idSeq).toString();
    }

    public void setPorcentajeIva( double porcentajeIva )
    {
        this.porcentajeIva = porcentajeIva ;
    }

    public double getPorcentajeIva()
    {
        return porcentajeIva;
    }

    public void setPorcentajeIva( String porcentajeIvaStr )
    {
        this.porcentajeIva = new Double(porcentajeIvaStr).doubleValue();
    }

    public String getPorcentajeIvaStr()
    {
        return new Double(porcentajeIva).toString();
    }
    public String getNombreMovimiento() {
        return nombreMovimiento;
    }

    public String getNombreMovimientoMayuscula() {
        return getNombreMovimiento().toUpperCase();
    }

    public void setNombreMovimiento(String nombreMovimiento) {
        this.nombreMovimiento = nombreMovimiento;
    }

    public void setIdComprobanteContable(String idComprobanteContable) {
        this.idComprobanteContable = idComprobanteContable;
    }

    public String getIdComprobanteContable() {
        return idComprobanteContable;
    }
}
