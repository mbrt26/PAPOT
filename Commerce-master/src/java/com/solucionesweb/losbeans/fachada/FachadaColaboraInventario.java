package com.solucionesweb.losbeans.fachada;

public class FachadaColaboraInventario extends FachadaPluBean {

    //
    private int idLocal;
    private int idPlu;
    private double existencia;
    private String nombreCategoria;
    private String nombreMarca;
    private String nombrePlu;
    private int idMarca;
    private double vrCostoInventario;
    private double vrVentaInventario;
    private int cuentaReferencia;
    private double vrTotalCostoInventario;
    private double vrTotalVentaInventario;
    private double vrCostoInventarioSinIva;
    private double vrCostoIva;
    private double vrTotalImpoconsumo;
    private double vrTotalCostoIND;

    //
    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getIdPlu() {
        return idPlu;
    }

    public void setIdPlu(int idPlu) {
        this.idPlu = idPlu;
    }

    public double getExistencia() {
        return existencia;
    }

    public void setExistencia(double existencia) {
        this.existencia = existencia;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getNombrePlu() {
        return nombrePlu;
    }

    public void setNombrePlu(String nombrePlu) {
        this.nombrePlu = nombrePlu;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public double getVrCostoInventario() {
        return vrCostoInventario;
    }

    public String getVrCostoInventarioDf0() {
        return df0.format(getVrCostoInventario());
    }

    public void setVrCostoInventario(double vrCostoInventario) {
        this.vrCostoInventario = vrCostoInventario;
    }

    public double getVrVentaInventario() {
        return vrVentaInventario;
    }

    public void setVrVentaInventario(double vrVentaInventario) {
        this.vrVentaInventario = vrVentaInventario;
    }

    public int getCuentaReferencia() {
        return cuentaReferencia;
    }

    public void setCuentaReferencia(int cuentaReferencia) {
        this.cuentaReferencia = cuentaReferencia;
    }

    public double getVrTotalCostoInventario() {
        return vrTotalCostoInventario;
    }

    public void setVrTotalCostoInventario(double vrTotalCostoInventario) {
        this.vrTotalCostoInventario = vrTotalCostoInventario;
    }

    public double getVrTotalVentaInventario() {
        return vrTotalVentaInventario;
    }

    public void setVrTotalVentaInventario(double vrTotalVentaInventario) {
        this.vrTotalVentaInventario = vrTotalVentaInventario;
    }

    public double getVrCostoInventarioSinIva() {
        return vrCostoInventarioSinIva;
    }

    public void setVrCostoInventarioSinIva(double vrCostoInventarioSinIva) {
        this.vrCostoInventarioSinIva = vrCostoInventarioSinIva;
    }

    public double getVrCostoIva() {
        return vrCostoIva;
    }

    public void setVrCostoIva(double vrCostoIva) {
        this.vrCostoIva = vrCostoIva;
    }

    public String getVetVrCostoIvaDf0() {
        return df0.format(getVrCostoIva());
    }

    public double getVrTotalImpoconsumo() {
        return vrTotalImpoconsumo;
    }

    public String getVrTotalImpoconsumoDf0() {
        return df0.format(getVrTotalImpoconsumo());
    }

    public void setVrTotalImpoconsumo(double vrTotalImpoconsumo) {
        this.vrTotalImpoconsumo = vrTotalImpoconsumo;
    }

    public double getVrTotalCostoIND() {
        return vrTotalCostoIND;
    }

    public void setVrTotalCostoIND(double vrTotalCostoIND) {
        this.vrTotalCostoIND = vrTotalCostoIND;
    }

    public String getVrTotalCostoINDDf0() {
        return df0.format(getVrTotalCostoIND());
    }
}
