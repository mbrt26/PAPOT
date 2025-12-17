package com.solucionesweb.losbeans.utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacion implements IValidacion {

    // Propiedades del Bean de Validacion
    private boolean valido;
    private String codigoError;
    private String descripcionError;
    private String solucion;
    private String paginaRetorno;
    // Propiedades de los campos a Validar
    private String nombreCampo;
    private String valorCampo;

    // Metodos Get
    public boolean isValido() {
        return valido;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public String getNombreCampo() {
        return nombreCampo;
    }

    public String getValorCampo() {
        return valorCampo;
    }

    public String getSolucion() {
        return solucion;
    }

    public String getPaginaRetorno() {
        return paginaRetorno;
    }

    // Metodos Set
    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

    public void setNombreCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }

    public void setValorCampo(String valorCampo) {
        this.valorCampo = valorCampo;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public void setPaginaRetorno(String paginaRetorno) {
        this.paginaRetorno = paginaRetorno;
        // System.out.println("setPaginaRetorno del Campo que validar� " + paginaRetorno);
    }

    // reasignar
    public void reasignar(String nombreCampo, String valorCampo) {
        setValido(true);
        setCodigoError("noError");
        setDescripcionError("No hay Descripcion del Error");
        setNombreCampo(nombreCampo);
        setValorCampo(valorCampo);
        setSolucion("No se Requiere");
        setPaginaRetorno("No se Requiere");

    }

    // reasignar
    public void reasignarDescripcion(String nombreCampo, String valorCampo,
            String descripcion, String solucion) {
        setValido(true);
        setCodigoError("noError");
        setDescripcionError(descripcion);
        setNombreCampo(nombreCampo);
        setValorCampo(valorCampo);
        setSolucion(solucion);
        setPaginaRetorno("No se Requiere");

    }

    // asignarError
    public void asignarError(String nombreCampo, String valorCampo,
            String codigoError, String descripcionError,
            String solucion) {
        setValido(false);
        setCodigoError(codigoError);
        setDescripcionError(descripcionError);
        setNombreCampo(nombreCampo);
        setValorCampo(valorCampo);
        setSolucion(solucion);

    }

    // validarCampoDouble
    public void validarCampoDouble() {

        double valor;

        if (valorCampo == null) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " esta vacía");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        if (valorCampo.length() == 0) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " esta vacía");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        try {

            valor = Double.parseDouble(valorCampo);

        } catch (NumberFormatException nfe) {

            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " contiene caracteres no numéricos");
            setSolucion("Proporcione un valor numérico en el campo");
            return;
        }

        if (valor <= 0) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " no puede ser cero o numero negativo");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        setValido(true);
        setCodigoError("noError");
        return;

    }

    // validarCampoDoublePositivo 0 o mayores
    public void validarCampoDoublePositivo() {

        double valor;

        if (valorCampo.length() == 0) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " esta vacía");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        try {

            valor = Double.parseDouble(valorCampo);

        } catch (NumberFormatException nfe) {

            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " contiene caracteres no numéricos");
            setSolucion("Proporcione un valor numérico en el campo");
            return;
        }

        if (valor < 0) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " no puede ser 0 o numero negativo");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        setValido(true);
        setCodigoError("noError");
        return;

    }

    // validarCampoEntero
    public void validarCampoEntero() {

        int valor;

        if (valorCampo == null) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " esta vacía");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        if (valorCampo.length() == 0) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " esta vacía");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        try {

            valor = Integer.parseInt(valorCampo);

        } catch (NumberFormatException nfe) {

            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " contiene caracteres no numéricos");
            setSolucion("Proporcione un valor numérico en el campo");
            return;
        }

        if (valor <= 0) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " no puede ser cero o numero negativo");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        setValido(true);
        setCodigoError("noError");
        return;

    }

    // validarCampoRangoNumerico 1  o  -1
    public void validarCampoRangoNumerico() {

        int valor;

        if (valorCampo.length() == 0) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " esta vacía");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        try {

            valor = Integer.parseInt(valorCampo);

        } catch (NumberFormatException nfe) {

            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " contiene caracteres no numericos");
            setSolucion("Proporcione un valor numérico en el campo");
            return;
        }

        if (!(valor == 1 || valor == -1)) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " debe ser 1 o -1");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        setValido(true);
        setCodigoError("noError");
        return;

    }

    public void validarCampoRangoNumerico_1_2() {
        if (this.valorCampo.length() == 0) {
            setValido(false);
            setCodigoError(this.nombreCampo);
            setDescripcionError("La identificacion de " + this.nombreCampo + " esta vacía");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }
        int valor;
        try {
            valor = Integer.parseInt(this.valorCampo);
        } catch (NumberFormatException nfe) {
            setValido(false);
            setCodigoError(this.nombreCampo);
            setDescripcionError("La identificacion de " + this.nombreCampo + " contiene caracteres no numericos");
            setSolucion("Proporcione un valor numérico en el campo");
            return;
        }
        if ((valor != 1) && (valor != 2)) {
            setValido(false);
            setCodigoError(this.nombreCampo);
            setDescripcionError("La identificacion de " + this.nombreCampo + " debe ser 1 o 2");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }
        setValido(true);
        setCodigoError("noError");
    }

    // validarCampoRangoNumerico 1  o  5
    public void validarCampoRangoTipo() {

        int valor;

        if (valorCampo.length() == 0) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " esta vacía");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        try {

            valor = Integer.parseInt(valorCampo);

        } catch (NumberFormatException nfe) {

            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " contiene caracteres no numericos");
            setSolucion("Proporcione un valor numérico en el campo");
            return;
        }

        if (!(valor == 1 || valor == 5 || valor == 2)) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " debe ser 1 o 2 o 5");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        setValido(true);
        setCodigoError("noError");
        return;

    }

    // validarCampoEnteroPositivo   0 o mayor
    public void validarCampoEnteroPositivo() {

        int valor;

        if (valorCampo.length() == 0) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " esta vacía");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        try {

            valor = Integer.parseInt(valorCampo);

        } catch (NumberFormatException nfe) {

            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " contiene caracteres no numéricos");
            setSolucion("Proporcione un valor numérico en el campo");
            return;
        }

        if (valor < 0) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " no puede ser un numero negativo");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        setValido(true);
        setCodigoError("noError");
        return;

    }

    // validarCampoEnteroIndicador  1  o  2
    public void validarCampoEnteroIndicador() {

        int valor;

        if (valorCampo.length() == 0) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " esta vacía");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        try {

            valor = Integer.parseInt(valorCampo);

        } catch (NumberFormatException nfe) {

            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " contiene caracteres no numéricos");
            setSolucion("Proporcione un valor numérico en el campo");
            return;
        }

        if (valor < 1 || valor > 2) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " no esta en el rango permitido");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        setValido(true);
        setCodigoError("noError");
        return;

    }

    // validarCampoEnteroEstado  1   a    5
    public void validarCampoEnteroEstado() {

        int valor;

        if (valorCampo.length() == 0) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " esta vacía");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        try {

            valor = Integer.parseInt(valorCampo);

        } catch (NumberFormatException nfe) {

            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " contiene caracteres no numéricos");
            setSolucion("Proporcione un valor numérico en el campo");
            return;
        }

        if (valor < 1 || valor > 5) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " no esta en el rango permitido");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        setValido(true);
        setCodigoError("noError");
        return;

    }

    // validarCampoString
    public void validarCampoString() {

        if (valorCampo == null) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " esta vacio");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        if (valorCampo.trim().length() == 0) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La identificacion de " + nombreCampo + " esta vacio");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        setValido(true);
        setCodigoError("noError");
        return;
    }

    // validaCampoEmail
    public void validaCampoEmail() {

        Pattern p = Pattern.compile("^\\.|^\\@");
        Matcher m = p.matcher(valorCampo);
        if (m.find()) {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La cadena contiene caracteres ilegales");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        //comprueba que no empieze por www.
        p = Pattern.compile("^www\\.");
        m = p.matcher(valorCampo);
        if (m.find()) {

            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La cadena empieza por www.");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        // comprueba que contenga @
        p = Pattern.compile("\\@");
        m = p.matcher(valorCampo);
        if (!m.find()) {

            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La cadena no tiene arroba");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        //Comprueba que no contenga caracteres prohibidos
        p = Pattern.compile("[^A-Za-z0-9\\.\\@_\\-~#]+");
        m = p.matcher(valorCampo);
        StringBuffer sb = new StringBuffer();
        boolean resultado = m.find();
        boolean caracteresIlegales = false;
        while (resultado) {
            caracteresIlegales = true;
            m.appendReplacement(sb, "");
            resultado = m.find();
        }

        // Añade el ultimo segmento de la entrada a la cadena
        m.appendTail(sb);

        valorCampo = sb.toString();
        if (caracteresIlegales) {

            setValido(true);
            setCodigoError(nombreCampo);
            setDescripcionError("La cadena contiene caracteres ilegales");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }
        //
        setValido(true);
        return;

    }

    // validarCampoFecha
    public void validarCampoFecha() {

        String anoStr;
        String mesStr;
        String diaStr;
        int anoInt;
        int mesInt;
        int diaInt;

        if (valorCampo.length() == 10) {

            try {

                // Extrae el A�o, Mes y Dia
                anoStr = valorCampo.substring(0, 4);
                mesStr = valorCampo.substring(5, 7);
                diaStr = valorCampo.substring(8, 10);

                anoInt = Integer.parseInt(anoStr);
                mesInt = Integer.parseInt(mesStr);
                diaInt = Integer.parseInt(diaStr);

                Day fechaFormateada = new Day(anoInt, mesInt, diaInt);

            } catch (Exception e) {

                setValido(false);
                setCodigoError(nombreCampo);
                setDescripcionError("La " + nombreCampo
                        + " debe estar en el formato AAAA/MM/DD");
                setSolucion("Proporcione un valor valido en el campo");
                return;
            }

        } else {
            setValido(false);
            setCodigoError(nombreCampo);
            setDescripcionError("La " + nombreCampo + " debe ser valida");
            setSolucion("Proporcione un valor valido en el campo");
            return;
        }

        setValido(true);
        setCodigoError("noError");
        return;
    }

    // Metodo de Validacion de cada uno de los campos
    // Carga en las propiedades el resultado de Validacion
    public void validarRangoFecha(String fechaInicio, String fechaFin) {

        //
        String anoStr = fechaInicio.substring(0, 4);
        String mesStr = fechaInicio.substring(5, 7);
        String diaStr = fechaInicio.substring(8, 10);

        //
        int anoInt = Integer.parseInt(anoStr);
        int mesInt = Integer.parseInt(mesStr);
        int diaInt = Integer.parseInt(diaStr);

        //
        Day fechaInicioDate = new Day(anoInt, mesInt, diaInt);

        anoStr = fechaFin.substring(0, 4);
        mesStr = fechaFin.substring(5, 7);
        diaStr = fechaFin.substring(8, 10);

        anoInt = Integer.parseInt(anoStr);
        mesInt = Integer.parseInt(mesStr);
        diaInt = Integer.parseInt(diaStr);

        Day fechaFinDate = new Day(anoInt, mesInt, diaInt);

        if (fechaFinDate.daysBetween(fechaInicioDate) >= 0) {

            setValido(true);
            setCodigoError("noError");
            return;
        } else {

            setValido(false);
            setCodigoError("FechaInicioVigencia + FechaFinVigencia");
            setDescripcionError("Las Fechas deben estar en rangos correctos");
            setSolucion("Proporcione un valor valido en los campo fecha");
            return;
        }

    }//
}
