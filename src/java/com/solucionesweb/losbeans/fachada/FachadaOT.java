package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

public class FachadaOT extends FachadaPluFicha {

    //
    private String nombreReferenciaPedido;
    private String anchoPedido;
    private String largoPedido;
    private String calibrePedido;
    private String observacionPedido;
    private String colorPedido;    
    private String terminacionPedido;
    private String certificadoCalidadPedido;
    private String tipoTroquelPedido;
    private String costoFotopolimeroPedido;
    private String porcentajeFotopolimeroClientePedido;
    private String porcentajeFotopolimeroUnionPedido;
    private String tipoEmbobinadoPedido;

    //
    private String maquinaExtrusion;
    private String anchoExtrusion;
    private String calibreExtrusion;
    private String fuelle_1Extrusion;
    private String fuelle_2Extrusion;
    private String tipoRolloExtrusion;
    private String tratadoCaraExtrusion;
    private String tratadoDinaExtrusion;
    private String numeroRolloExtrusion;
    private String excedenteExtrusion;
    private String tipoSelladoExtrusion;
    private String materiaPrimaExtrusion;
    private String observacionExtrusion;
    private String destinoExtrusion;
    private String productoExtrusion;
    private String mpLineal;
    private String mpBaja;
    private String mpAlta;
    private String mpPP;
    private String mpOtraRef;
    private String mpLinealAlta;
    private String mpPigmento;
    private String mpOtraRfcia1;
    private String mpOtraRfcia2;

    //
    private String maquinaImpresion;
    private String colorImpresion;
    private String repeticionImpresion;
    private String rodilloImpresion;
    private String alturaCara1Impresion;
    private String alturaCara2Impresion;
    private String codigoBarraImpresion;
    private String cyrelCara1Impresion;
    private String cyrelCara2Impresion;
    private String destinoImpresion;
    private String observacionImpresion;
    private String tipoEmbobinadoImpresion;

    //
    private String maquinaRefilado;
    private String anchoRefilado;
    private String alturaRefilado;
    private String tipoRefilado;
    private String metroRefilado;
    private String pesoRolloRefilado;
    private String tipoRolloRefilado;
    private String observacionRefilado;
    private String destinoRefilado;
    private String tipoEmbobinadoRefilado;

    //
    private String maquinaSellado;
    private String anchoSellado;
    private String largoSellado;
    private String calibreSellado;
    private String solapaSellado;
    private String tipoSolapaSellado;
    private String fuelle1Sellado;
    private String fuelle2Sellado;
    private String alturaSellado;
    private String golpeSellado;
    private String bultoSellado;
    private String paqueteSellado;
    private String destinoSellado;
    private String tipoFuelleSellado;
    private String tipoSellado;
    private String tipoTroquelSellado;
    private String observacionSellado;

    //
    private String maquinaManualidad;
    private String observacionManualidad;
    private String destinoManualidad;

    //
    private String cara1Color1Pantone;
    private String cara1Color2Pantone;
    private String cara1Color3Pantone;
    private String cara1Color4Pantone;
    private String cara1Color5Pantone;
    private String cara1Color6Pantone;
    private String cara2Color1Pantone;
    private String cara2Color2Pantone;
    private String cara2Color3Pantone;
    private String cara2Color4Pantone;
    private String cara2Color5Pantone;
    private String cara2Color6Pantone;

    //
    DecimalFormat sf0 = new DecimalFormat("##############");

    //
    public String getMaquinaExtrusion() {
        return maquinaExtrusion;
    }

    public void setMaquinaExtrusion(String maquinaExtrusion) {
        this.maquinaExtrusion = maquinaExtrusion;
    }

    public String getAnchoExtrusion() {
        return anchoExtrusion;
    }

    public void setAnchoExtrusion(String anchoExtrusion) {
        this.anchoExtrusion = anchoExtrusion;
    }

    public String getCalibreExtrusion() {
        return calibreExtrusion;
    }

    public void setCalibreExtrusion(String calibreExtrusion) {
        this.calibreExtrusion = calibreExtrusion;
    }

    public String getFuelle_1Extrusion() {
        return fuelle_1Extrusion;
    }

    public void setFuelle_1Extrusion(String fuelle_1Extrusion) {
        this.fuelle_1Extrusion = fuelle_1Extrusion;
    }

    public String getFuelle_2Extrusion() {
        return fuelle_2Extrusion;
    }

    public void setFuelle_2Extrusion(String fuelle_2Extrusion) {
        this.fuelle_2Extrusion = fuelle_2Extrusion;
    }

    public String getTipoRolloExtrusion() {
        return tipoRolloExtrusion;
    }

    public void setTipoRolloExtrusion(String tipoRolloExtrusion) {
        this.tipoRolloExtrusion = tipoRolloExtrusion;
    }

    public String getTratadoCaraExtrusion() {
        return tratadoCaraExtrusion;
    }

    public void setTratadoCaraExtrusion(String tratadoCaraExtrusion) {
        this.tratadoCaraExtrusion = tratadoCaraExtrusion;
    }

    public String getTratadoDinaExtrusion() {
        return tratadoDinaExtrusion;
    }

    public void setTratadoDinaExtrusion(String tratadoDinaExtrusion) {
        this.tratadoDinaExtrusion = tratadoDinaExtrusion;
    }

    public String getNumeroRolloExtrusion() {
        return numeroRolloExtrusion;
    }

    public void setNumeroRolloExtrusion(String numeroRolloExtrusion) {
        this.numeroRolloExtrusion = numeroRolloExtrusion;
    }

    public String getExcedenteExtrusion() {
        return excedenteExtrusion;
    }

    public void setExcedenteExtrusion(String excedenteExtrusion) {
        this.excedenteExtrusion = excedenteExtrusion;
    }

    public String getTipoSelladoExtrusion() {
        return tipoSelladoExtrusion;
    }

    public void setTipoSelladoExtrusion(String tipoSelladoExtrusion) {
        this.tipoSelladoExtrusion = tipoSelladoExtrusion;
    }

    public String getMateriaPrimaExtrusion() {
        return materiaPrimaExtrusion;
    }

    public void setMateriaPrimaExtrusion(String materiaPrimaExtrusion) {
        this.materiaPrimaExtrusion = materiaPrimaExtrusion;
    }

    public String getObservacionExtrusion() {
        return observacionExtrusion;
    }

    public void setObservacionExtrusion(String observacionExtrusion) {
        this.observacionExtrusion = observacionExtrusion;
    }

    public String getDestinoExtrusion() {
        return destinoExtrusion;
    }

    public void setDestinoExtrusion(String destinoExtrusion) {
        this.destinoExtrusion = destinoExtrusion;
    }

    public String getProductoExtrusion() {
        return productoExtrusion;
    }

    public void setProductoExtrusion(String productoExtrusion) {
        this.productoExtrusion = productoExtrusion;
    }

    public String getMpLineal() {
        return mpLineal;
    }

    public void setMpLineal(String mpLineal) {
        this.mpLineal = mpLineal;
    }

    public String getMpBaja() {
        return mpBaja;
    }

    public void setMpBaja(String mpBaja) {
        this.mpBaja = mpBaja;
    }

    public String getMpAlta() {
        return mpAlta;
    }

    public void setMpAlta(String mpAlta) {
        this.mpAlta = mpAlta;
    }

    public String getMpPP() {
        return mpPP;
    }

    public void setMpPP(String mpPP) {
        this.mpPP = mpPP;
    }

    public String getMpLinealAlta() {
        return mpLinealAlta;
    }

    public void setMpLinealAlta(String mpLinealAlta) {
        this.mpLinealAlta = mpLinealAlta;
    }

    public String getMpOtraRef() {
        return mpOtraRef;
    }

    public void setMpOtraRef(String mpOtraRef) {
        this.mpOtraRef = mpOtraRef;
    }

    public String getMaquinaImpresion() {
        return maquinaImpresion;
    }

    public void setMaquinaImpresion(String maquinaImpresion) {
        this.maquinaImpresion = maquinaImpresion;
    }

    public String getColorImpresion() {
        return colorImpresion;
    }

    public String getColorImpresionSf0() {
        return sf0.format(new Double(getColorImpresion()).doubleValue());
    }

    public void setColorImpresion(String colorImpresion) {
        this.colorImpresion = colorImpresion;
    }

    public String getRepeticionImpresion() {
        return repeticionImpresion;
    }

    public String getRepeticionImpresionSf0() {
        return sf0.format(new Double(getRepeticionImpresion()).doubleValue());
    }

    public void setRepeticionImpresion(String repeticionImpresion) {
        this.repeticionImpresion = repeticionImpresion;
    }

    public String getRodilloImpresion() {
        return rodilloImpresion;
    }

    public void setRodilloImpresion(String rodilloImpresion) {
        this.rodilloImpresion = rodilloImpresion;
    }

    public String getAlturaCara1Impresion() {
        return alturaCara1Impresion;
    }

    public void setAlturaCara1Impresion(String alturaCara1Impresion) {
        this.alturaCara1Impresion = alturaCara1Impresion;
    }

    public String getAlturaCara2Impresion() {
        return alturaCara2Impresion;
    }

    public void setAlturaCara2Impresion(String alturaCara2Impresion) {
        this.alturaCara2Impresion = alturaCara2Impresion;
    }

    public String getCodigoBarraImpresion() {
        return codigoBarraImpresion;
    }

    public String getCodigoBarraImpresionSf0() {
        return sf0.format(new Double(getCodigoBarraImpresion()).doubleValue());
    }


    public void setCodigoBarraImpresion(String codigoBarraImpresion) {
        this.codigoBarraImpresion = codigoBarraImpresion;
    }

    public String getCyrelCara1Impresion() {
        return cyrelCara1Impresion;
    }

    public String getCyrelCara1ImpresionSf0() {
        return sf0.format(new Double(getCyrelCara1Impresion()).doubleValue());
    }

    public void setCyrelCara1Impresion(String cyrelCara1Impresion) {
        this.cyrelCara1Impresion = cyrelCara1Impresion;
    }

    public String getCyrelCara2Impresion() {
        return cyrelCara2Impresion;
    }

    public String getCyrelCara2ImpresionSf0() {
        return sf0.format(new Double(getCyrelCara2Impresion()).doubleValue());
    }

    public void setCyrelCara2Impresion(String cyrelCara2Impresion) {
        this.cyrelCara2Impresion = cyrelCara2Impresion;
    }

    public String getDestinoImpresion() {
        return destinoImpresion;
    }

    public void setDestinoImpresion(String destinoImpresion) {
        this.destinoImpresion = destinoImpresion;
    }

    public String getObservacionImpresion() {
        return observacionImpresion;
    }

    public void setObservacionImpresion(String observacionImpresion) {
        this.observacionImpresion = observacionImpresion;
    }

    public String getAnchoRefilado() {
        return anchoRefilado;
    }

    public void setAnchoRefilado(String anchoRefilado) {
        this.anchoRefilado = anchoRefilado;
    }

    public String getAlturaRefilado() {
        return alturaRefilado;
    }

    public void setAlturaRefilado(String alturaRefilado) {
        this.alturaRefilado = alturaRefilado;
    }

    public String getTipoRefilado() {
        return tipoRefilado;
    }

    public void setTipoRefilado(String tipoRefilado) {
        this.tipoRefilado = tipoRefilado;
    }

    public String getMetroRefilado() {
        return metroRefilado;
    }

    public void setMetroRefilado(String metroRefilado) {
        this.metroRefilado = metroRefilado;
    }

    public String getPesoRolloRefilado() {
        return pesoRolloRefilado;
    }

    public void setPesoRolloRefilado(String pesoRolloRefilado) {
        this.pesoRolloRefilado = pesoRolloRefilado;
    }

    public String getTipoRolloRefilado() {
        return tipoRolloRefilado;
    }

    public void setTipoRolloRefilado(String tipoRolloRefilado) {
        this.tipoRolloRefilado = tipoRolloRefilado;
    }

    public String getNombreReferenciaPedido() {
        return nombreReferenciaPedido;
    }

    public void setNombreReferenciaPedido(String nombreReferenciaPedido) {
        this.nombreReferenciaPedido = nombreReferenciaPedido;
    }

    public String getAnchoPedido() {
        return anchoPedido;
    }

    public void setAnchoPedido(String anchoPedido) {
        this.anchoPedido = anchoPedido;
    }

    public String getLargoPedido() {
        return largoPedido;
    }

    public void setLargoPedido(String largoPedido) {
        this.largoPedido = largoPedido;
    }

    public String getCalibrePedido() {
        return calibrePedido;
    }

    public void setCalibrePedido(String calibrePedido) {
        this.calibrePedido = calibrePedido;
    }

    public String getObservacionPedido() {
        return observacionPedido;
    }

    public void setObservacionPedido(String observacionPedido) {
        this.observacionPedido = observacionPedido;
    }

    public String getObservacionRefilado() {
        return observacionRefilado;
    }

    public void setObservacionRefilado(String observacionRefilado) {
        this.observacionRefilado = observacionRefilado;
    }

    public String getMaquinaRefilado() {
        return maquinaRefilado;
    }

    public void setMaquinaRefilado(String maquinaRefilado) {
        this.maquinaRefilado = maquinaRefilado;
    }

    public String getDestinoRefilado() {
        return destinoRefilado;
    }

    public void setDestinoRefilado(String destinoRefilado) {
        this.destinoRefilado = destinoRefilado;
    }

    public String getAnchoSellado() {
        return anchoSellado;
    }

    public void setAnchoSellado(String anchoSellado) {
        this.anchoSellado = anchoSellado;
    }

    public String getLargoSellado() {
        return largoSellado;
    }

    public void setLargoSellado(String largoSellado) {
        this.largoSellado = largoSellado;
    }

    public String getCalibreSellado() {
        return calibreSellado;
    }

    public void setCalibreSellado(String calibreSellado) {
        this.calibreSellado = calibreSellado;
    }

    public String getSolapaSellado() {
        return solapaSellado;
    }

    public void setSolapaSellado(String solapaSellado) {
        this.solapaSellado = solapaSellado;
    }

    public String getFuelle1Sellado() {
        return fuelle1Sellado;
    }

    public void setFuelle1Sellado(String fuelle1Sellado) {
        this.fuelle1Sellado = fuelle1Sellado;
    }

    public String getFuelle2Sellado() {
        return fuelle2Sellado;
    }

    public void setFuelle2Sellado(String fuelle2Sellado) {
        this.fuelle2Sellado = fuelle2Sellado;
    }

    public String getAlturaSellado() {
        return alturaSellado;
    }

    public void setAlturaSellado(String alturaSellado) {
        this.alturaSellado = alturaSellado;
    }

    public String getBultoSellado() {
        return bultoSellado;
    }

    public void setBultoSellado(String bultoSellado) {
        this.bultoSellado = bultoSellado;
    }

    public String getPaqueteSellado() {
        return paqueteSellado;
    }

    public void setPaqueteSellado(String paqueteSellado) {
        this.paqueteSellado = paqueteSellado;
    }

    public String getGolpeSellado() {
        return golpeSellado;
    }

    public void setGolpeSellado(String golpeSellado) {
        this.golpeSellado = golpeSellado;
    }

    public String getTipoSolapaSellado() {
        return tipoSolapaSellado;
    }

    public void setTipoSolapaSellado(String tipoSolapaSellado) {
        this.tipoSolapaSellado = tipoSolapaSellado;
    }

    public String getMaquinaSellado() {
        return maquinaSellado;
    }

    public void setMaquinaSellado(String maquinaSellado) {
        this.maquinaSellado = maquinaSellado;
    }

    public String getDestinoSellado() {
        return destinoSellado;
    }

    public void setDestinoSellado(String destinoSellado) {
        this.destinoSellado = destinoSellado;
    }

    public String getTipoFuelleSellado() {
        return tipoFuelleSellado;
    }

    public void setTipoFuelleSellado(String tipoFuelleSellado) {
        this.tipoFuelleSellado = tipoFuelleSellado;
    }

    public String getTipoSellado() {
        return tipoSellado;
    }

    public void setTipoSellado(String tipoSellado) {
        this.tipoSellado = tipoSellado;
    }

    public String getTipoTroquelSellado() {
        return tipoTroquelSellado;
    }

    public void setTipoTroquelSellado(String tipoTroquelSellado) {
        this.tipoTroquelSellado = tipoTroquelSellado;
    }

    public String getObservacionSellado() {
        return observacionSellado;
    }

    public void setObservacionSellado(String observacionSellado) {
        this.observacionSellado = observacionSellado;
    }

    public String getMaquinaManualidad() {
        return maquinaManualidad;
    }

    public void setMaquinaManualidad(String maquinaManualidad) {
        this.maquinaManualidad = maquinaManualidad;
    }

    public String getObservacionManualidad() {
        return observacionManualidad;
    }

    public void setObservacionManualidad(String observacionManualidad) {
        this.observacionManualidad = observacionManualidad;
    }

    public String getDestinoManualidad() {
        return destinoManualidad;
    }

    public void setDestinoManualidad(String destinoManualidad) {
        this.destinoManualidad = destinoManualidad;
    }

    public String getCara1Color1Pantone() {
        return cara1Color1Pantone;
    }

    public void setCara1Color1Pantone(String cara1Color1Pantone) {
        this.cara1Color1Pantone = cara1Color1Pantone;
    }

    public String getCara1Color2Pantone() {
        return cara1Color2Pantone;
    }

    public void setCara1Color2Pantone(String cara1Color2Pantone) {
        this.cara1Color2Pantone = cara1Color2Pantone;
    }

    public String getCara1Color3Pantone() {
        return cara1Color3Pantone;
    }

    public void setCara1Color3Pantone(String cara1Color3Pantone) {
        this.cara1Color3Pantone = cara1Color3Pantone;
    }

    public String getCara1Color4Pantone() {
        return cara1Color4Pantone;
    }

    public void setCara1Color4Pantone(String cara1Color4Pantone) {
        this.cara1Color4Pantone = cara1Color4Pantone;
    }

    public String getCara1Color5Pantone() {
        return cara1Color5Pantone;
    }

    public void setCara1Color5Pantone(String cara1Color5Pantone) {
        this.cara1Color5Pantone = cara1Color5Pantone;
    }

    public String getCara1Color6Pantone() {
        return cara1Color6Pantone;
    }

    public void setCara1Color6Pantone(String cara1Color6Pantone) {
        this.cara1Color6Pantone = cara1Color6Pantone;
    }

    public String getCara2Color1Pantone() {
        return cara2Color1Pantone;
    }

    public void setCara2Color1Pantone(String cara2Color1Pantone) {
        this.cara2Color1Pantone = cara2Color1Pantone;
    }

    public String getCara2Color2Pantone() {
        return cara2Color2Pantone;
    }

    public void setCara2Color2Pantone(String cara2Color2Pantone) {
        this.cara2Color2Pantone = cara2Color2Pantone;
    }

    public String getCara2Color3Pantone() {
        return cara2Color3Pantone;
    }

    public void setCara2Color3Pantone(String cara2Color3Pantone) {
        this.cara2Color3Pantone = cara2Color3Pantone;
    }

    public String getCara2Color4Pantone() {
        return cara2Color4Pantone;
    }

    public void setCara2Color4Pantone(String cara2Color4Pantone) {
        this.cara2Color4Pantone = cara2Color4Pantone;
    }

    public String getCara2Color5Pantone() {
        return cara2Color5Pantone;
    }

    public void setCara2Color5Pantone(String cara2Color5Pantone) {
        this.cara2Color5Pantone = cara2Color5Pantone;
    }

    public String getCara2Color6Pantone() {
        return cara2Color6Pantone;
    }

    public void setCara2Color6Pantone(String cara2Color6Pantone) {
        this.cara2Color6Pantone = cara2Color6Pantone;
    }

    public String getCaraColorPantone(String xCaraColor,
            String xPantone) {

        String xCaraColorPantone = "";

        //
        if ((xCaraColor.length()>0) && (xPantone.length()>0)) {

            //
            if (xCaraColor.length() <= 14) {


                xCaraColorPantone += jhFormat.fill(xCaraColor, '-', 14, 1)
                        + " "
                        + xPantone;
            } else {

                xCaraColorPantone += xCaraColor.substring(0, 14)
                        + " "
                        + xPantone;
            }
        }
        return xCaraColorPantone;
    }

    public String getColorPedido() {
        return colorPedido;
    }

    public void setColorPedido(String colorPedido) {
        this.colorPedido = colorPedido;
    }

    public String getTerminacionPedido() {
        return terminacionPedido;
    }

    public void setTerminacionPedido(String terminacionPedido) {
        this.terminacionPedido = terminacionPedido;
    }

    public String getCertificadoCalidadPedido() {
        return certificadoCalidadPedido;
    }

    public void setCertificadoCalidadPedido(String certificadoCalidadPedido) {
        this.certificadoCalidadPedido = certificadoCalidadPedido;
    }

    public String getTipoTroquelPedido() {
        return tipoTroquelPedido;
    }

    public void setTipoTroquelPedido(String tipoTroquelPedido) {
        this.tipoTroquelPedido = tipoTroquelPedido;
    }

    public String getCostoFotopolimeroPedido() {
        return costoFotopolimeroPedido;
    }

    public void setCostoFotopolimeroPedido(String costoFotopolimeroPedido) {
        this.costoFotopolimeroPedido = costoFotopolimeroPedido;
    }

    public String getPorcentajeFotopolimeroClientePedido() {
        return porcentajeFotopolimeroClientePedido;
    }

    public void setPorcentajeFotopolimeroClientePedido(String porcentajeFotopolimeroClientePedido) {
        this.porcentajeFotopolimeroClientePedido = porcentajeFotopolimeroClientePedido;
    }

    public String getPorcentajeFotopolimeroUnionPedido() {
        return porcentajeFotopolimeroUnionPedido;
    }

    public void setPorcentajeFotopolimeroUnionPedido(String porcentajeFotopolimeroUnionPedido) {
        this.porcentajeFotopolimeroUnionPedido = porcentajeFotopolimeroUnionPedido;
    }

    public String getTipoEmbobinadoPedido() {
        return tipoEmbobinadoPedido;
    }

    public void setTipoEmbobinadoPedido(String tipoEmbobinadoPedido) {
        this.tipoEmbobinadoPedido = tipoEmbobinadoPedido;
    }

    public String getTipoEmbobinadoImpresion() {
        return tipoEmbobinadoImpresion;
    }

    public String getTipoEmbobinadoImpresionSf0() {
        return sf0.format(new Double(getTipoEmbobinadoImpresion()).doubleValue());
    }


    public void setTipoEmbobinadoImpresion(String tipoEmbobinadoImpresion) {
        this.tipoEmbobinadoImpresion = tipoEmbobinadoImpresion;
    }

    public String getTipoEmbobinadoRefilado() {
        return tipoEmbobinadoRefilado;
    }

    public void setTipoEmbobinadoRefilado(String tipoEmbobinadoRefilado) {
        this.tipoEmbobinadoRefilado = tipoEmbobinadoRefilado;
    }

    /**
     * @return the mpPigmento
     */
    public String getMpPigmento() {
        return mpPigmento;
    }

    /**
     * @param mpPigmento the mpPigmento to set
     */
    public void setMpPigmento(String mpPigmento) {
        this.mpPigmento = mpPigmento;
    }

    /**
     * @return the mpOtraRfcia1
     */
    public String getMpOtraRfcia1() {
        return mpOtraRfcia1;
    }

    /**
     * @param mpOtraRfcia1 the mpOtraRfcia1 to set
     */
    public void setMpOtraRfcia1(String mpOtraRfcia1) {
        this.mpOtraRfcia1 = mpOtraRfcia1;
    }

    /**
     * @return the mpOtraRfcia2
     */
    public String getMpOtraRfcia2() {
        return mpOtraRfcia2;
    }

    /**
     * @param mpOtraRfcia2 the mpOtraRfcia2 to set
     */
    public void setMpOtraRfcia2(String mpOtraRfcia2) {
        this.mpOtraRfcia2 = mpOtraRfcia2;
    }
}
