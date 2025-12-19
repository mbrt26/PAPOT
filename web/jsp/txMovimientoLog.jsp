<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import = "com.solucionesweb.losbeans.colaboraciones.ColaboraTercero" %>
<%@ page import = "com.solucionesweb.losbeans.fachada.FachadaTerceroBean" %>

<%
            // Bean de fachada
            FachadaTerceroBean fachadaBean;

            //
            ColaboraTercero colaboraTercero = new ColaboraTercero();

                        //
            String xIdLocal = request.getParameter("xIdLocal");

            //
            colaboraTercero.setIdLocal(xIdLocal);


            //
            Vector vectorObjetos =
                           colaboraTercero.listaLog();

            //
            Iterator iteratorObjetos;
            iteratorObjetos = vectorObjetos.iterator();

            // output the vote results
            StringBuffer Results = new StringBuffer();
            Results.append("<documento>");

            while (iteratorObjetos.hasNext()) {

                //
                fachadaBean = (FachadaTerceroBean) iteratorObjetos.next();

                //
                Results.append("<tercero>");
                Results.append("<idLocal>" + fachadaBean.getIdLocal() + "</idLocal>");
                Results.append("<idTipoOrden>" + fachadaBean.getIdTipoOrden() + "</idTipoOrden>");
                Results.append("<idLog>" + fachadaBean.getIdLog() + "</idLog>");
                Results.append("<idOrigenLog>" + fachadaBean.getIdOrigenLog() + "</idOrigenLog>");
                Results.append("<idTercero>" + fachadaBean.getIdCliente() + "</idTercero>");
                Results.append("<digitoVerificacion>" + fachadaBean.getDigitoVerificacionDi0() + "</digitoVerificacion>");
                Results.append("<nombreTercero>" + fachadaBean.getNombreTercero() + "</nombreTercero>");
                Results.append("<direccionTercero>" + fachadaBean.getDireccionTercero() + "</direccionTercero>");
                Results.append("<ciudadTercero>" + fachadaBean.getCiudadTercero() + "</ciudadTercero>");
                Results.append("<telefonoFijo>" + fachadaBean.getTelefonoFijo() + "</telefonoFijo>");
                Results.append("<telefonoCelular>" + fachadaBean.getTelefonoCelular() + "</telefonoCelular>");
                Results.append("<telefonoFax>" + fachadaBean.getTelefonoFax() + "</telefonoFax>");
                Results.append("<email>" + fachadaBean.getEmail() + "</email>");
                Results.append("<contactoTercero>" + fachadaBean.getContactoTercero() + "</contactoTercero>");
                Results.append("<idPersona>" + fachadaBean.getIdPersonaStr() + "</idPersona>");
                Results.append("<idAutoRetenedor>" + fachadaBean.getIdAutoRetenedorStr() + "</idAutoRetenedor>");
                Results.append("<idRegimen>" + fachadaBean.getIdRegimen() + "</idRegimen>");
                Results.append("<idDptoCiudad>" + fachadaBean.getIdDptoCiudadStr() + "</idDptoCiudad>");
                Results.append("<departamentoTercero>" + fachadaBean.getDepartamentoTercero() + "</departamentoTercero>");
                Results.append("<tipoIdTercero>" + fachadaBean.getTipoIdTercero() + "</tipoIdTercero>");

                //
                Results.append("</tercero>");
            }
            Results.append("</documento>");

            out.println(Results.toString());

%>