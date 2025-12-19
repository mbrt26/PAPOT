package com.solucionesweb.losbeans.fachada;

import java.util.Date;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

public class FachadaColaboraCuentaClienteBean {

    // Propiedades
    private String idCliente;
    private String idSucursal;
    private String tipoCartera;
    private int numeroDctos;
    private double vrSaldo;
    private double vrTotalCxC;
    private int estado;

    private int idDcto;
    private String idTipoOrdenStr;
    private String fechaDcto;
    private String fechaVencimiento;
    private int diasMora;

    //
    DecimalFormat df0 = new DecimalFormat("###,###,###");

    // Metodos
    public void setIdCliente( String idCliente )
    {
        this.idCliente = idCliente.trim() ;
    }

    public String getIdCliente()
    {
        return idCliente;
    }

    public String getIdClientePSL()
    {
        int intLongitud = getIdCliente().length();

        //
        return getIdCliente().substring(0,1) +
               getIdCliente().substring(2,intLongitud);
    }

    public String getIdSucursal()
    {
        return idSucursal;
    }

    public String getIdSucursalOracle()
    {
        return "'" + idSucursal + "'" ;
    }

    public void setIdSucursal( String idSucursal )
    {
        this.idSucursal = idSucursal.trim();
    }

    public String getTipoCartera()
    {
        return tipoCartera;
    }

    public void setTipoCartera( String tipoCartera )
    {
        this.tipoCartera = tipoCartera;
    }

    public int getNumeroDctos()
    {
        return numeroDctos;
    }

    public String getNumeroDctosStr()
    {
        return new Integer(numeroDctos).toString();
    }

    public void setNumeroDctos( int numeroDctos )
    {
        this.numeroDctos = numeroDctos ;
    }

    public void setNumeroDctos( String numeroDctosStr )
    {
        this.numeroDctos = new Integer(numeroDctosStr).intValue() ;
    }

    public void setVrSaldo( double vrSaldo )
    {
        this.vrSaldo = vrSaldo ;
    }

    public double getVrSaldo()
    {
        return vrSaldo;
    }

    public void setVrSaldo( String vrSaldoStr )
    {
        this.vrSaldo = new Double(vrSaldoStr).doubleValue() ;
    }

    public String getVrSaldoStr()
    {
        return new Double(vrSaldo).toString();
    }

    public void setVrTotalCxC( double vrTotalCxC )
    {
        this.vrTotalCxC = vrTotalCxC ;
    }

    public double getVrTotalCxC()
    {
        return vrTotalCxC;
    }

    public void setVrTotalCxC( String vrTotalCxCStr )
    {
        this.vrTotalCxC = new Double(vrTotalCxCStr).doubleValue() ;
    }

    public String getVrTotalCxCStr()
    {
        return new Double(vrTotalCxC).toString();
    }

    public String getVrTotalCxCDf0()
    {
        return df0.format(getVrTotalCxC());
    }

    public int getEstado()
    {
        return estado;
    }

    public String getEstadoStr()
    {
        return new Integer(estado).toString();
    }

    public void setEstado( int estado )
    {
        this.estado = estado ;
    }

    public void setEstado( String estadoStr )
    {
        this.estado = new Integer(estadoStr).intValue() ;
    }

    public int getIdDcto()
    {
        return idDcto;
    }

    public String getIdDctoStr()
    {
        return new Integer(idDcto).toString();
    }

    public void setIdDcto( int idDcto )
    {
        this.idDcto = idDcto ;
    }

    public void setIdDcto( String idDctoStr )
    {
        this.idDcto = new Integer(idDctoStr).intValue() ;
    }

    public String getIdTipoOrdenStr()
    {
        return idTipoOrdenStr;
    }

    public String getIdTipoOrdenStrOracle()
    {
        return "'" + idTipoOrdenStr + "'" ;
    }

    public void setIdTipoOrdenStr( String idTipoOrdenStr )
    {
        this.idTipoOrdenStr = idTipoOrdenStr.trim();
    }

    public String getFechaDcto()
    {
        return fechaDcto;
    }

    public String getFechaDctoOracle()
    {
        return "'" + fechaDcto + "'" ;
    }

    public void setFechaDcto( String fechaDcto )
    {
        this.fechaDcto = fechaDcto.trim();
    }

    public String getFechaVencimiento()
    {
        return fechaVencimiento;
    }

    public String getFechaVencimientoOracle()
    {
        return "'" + fechaVencimiento + "'" ;
    }

    public void setFechaVencimiento( String fechaVencimiento )
    {
        this.fechaVencimiento = fechaVencimiento.trim();
    }

    public int getDiasMora()
    {
        return diasMora;
    }

    public String getDiasMoraStr()
    {
        return new Integer(diasMora).toString();
    }

    public void setDiasMora( int diasMora )
    {
        this.diasMora = diasMora ;
    }

    public void setDiasMora( String diasMoraStr )
    {
        this.diasMora = new Integer(diasMoraStr).intValue() ;
    }

    public FachadaColaboraCuentaClienteBean() { }

}