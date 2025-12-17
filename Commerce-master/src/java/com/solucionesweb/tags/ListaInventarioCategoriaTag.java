package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

public class ListaInventarioCategoriaTag extends TagSupport
                                    implements IterationTag
{

    // Variables de Retorno a jsp
    private String idLineaTag;
    private String idCategoriaTag;


	// Metodos recibidos JSP
    public void setIdLineaTag(String idLineaTag) {
	  this.idLineaTag = idLineaTag;
	}

	public String getIdLineaTag() {
		return idLineaTag;
	}

    public void setIdCategoriaTag(String idCategoriaTag) {
	  this.idCategoriaTag = idCategoriaTag;
	}

	public String getIdCategoriaTag() {
		return idCategoriaTag;
	}

    // Variable para usar el bean de fachada
    FachadaPluBean fachadaPluBean;

	// Variable para usar el bean de area
	ColaboraPlu colaboraPlu;

    // Iterador de objetos
    Iterator iteratorLineaBean;

    private void inicializarVariablesDeRetorno() {

      fachadaPluBean = (FachadaPluBean)iteratorLineaBean.next();

      //
      pageContext.setAttribute("idPluVar",fachadaPluBean.getIdPluStr());
      pageContext.setAttribute("nombrePluVar",fachadaPluBean.getNombrePlu());
      pageContext.setAttribute("vrGeneralVar",fachadaPluBean.getVrGeneralStr());
      pageContext.setAttribute("vrMayoristaVar",
                                            fachadaPluBean.getVrMayoristaStr());
      pageContext.setAttribute("porcentajeIvaVar",
                                          fachadaPluBean.getPorcentajeIvaStr());
      pageContext.setAttribute("idTipoVar",
                                                 fachadaPluBean.getIdTipoStr());
      pageContext.setAttribute("idLineaVar",fachadaPluBean.getIdLineaStr());
      pageContext.setAttribute("idUCompraVar",fachadaPluBean.getIdUCompraStr());
      pageContext.setAttribute("idUVentaVar",fachadaPluBean.getIdUVentaStr());
      pageContext.setAttribute("vrCostoVar",fachadaPluBean.getVrCostoStr());
      pageContext.setAttribute("idCategoriaVar",
                                            fachadaPluBean.getIdCategoriaStr());
      pageContext.setAttribute("idMarcaVar",fachadaPluBean.getIdMarcaStr());
      pageContext.setAttribute("vrSucursalVar",
                                             fachadaPluBean.getVrSucursalStr());
      pageContext.setAttribute("factorVentaVar",
                                            fachadaPluBean.getFactorVentaStr());
      pageContext.setAttribute("factorDespachoVar",
                                         fachadaPluBean.getFactorDespachoStr());
      pageContext.setAttribute("estadoVar",fachadaPluBean.getEstadoStr());
      pageContext.setAttribute("idSeqVar",fachadaPluBean.getIdSeqStr());
      pageContext.setAttribute("referenciaVar",fachadaPluBean.getReferencia());
      pageContext.setAttribute("nombreCategoriaVar",
                                           fachadaPluBean.getNombreCategoria());
      pageContext.setAttribute("nombreMarcaVar",
                                               fachadaPluBean.getNombreMarca());
      pageContext.setAttribute("vrGeneralDf0Var",
                                              fachadaPluBean.getVrGeneralDf0());
      pageContext.setAttribute("vrCostoDf2Var",fachadaPluBean.getVrCostoDf2());
      pageContext.setAttribute("existenciaDf2Var",
                                             fachadaPluBean.getExistenciaDf2());
      pageContext.setAttribute("vrMayoristaDf0Var",
                                              fachadaPluBean.getVrMayoristaDf0());
      pageContext.setAttribute("vrSucursalDf0Var",
                                              fachadaPluBean.getVrSucursalDf0());


	}

	public int doStartTag() throws JspTagException 	{

        //
        int xIdTipoInventario = 1;

		// Efectua la busqueda de los grados que estan matriculados
		colaboraPlu               = new ColaboraPlu();

        //
		colaboraPlu.setIdLinea(getIdLineaTag());
        colaboraPlu.setIdCategoria(getIdCategoriaTag());
        colaboraPlu.setIdCategoria(xIdTipoInventario);

        //
		Vector listaLineaVector = colaboraPlu.listaPluCategoria();

        //
        iteratorLineaBean       = listaLineaVector.iterator();

        if ( !iteratorLineaBean.hasNext() ) {
            try {
              pageContext.getOut().write("La consulta no devolvio ningun dato <br/>");
              pageContext.getOut().write("<br/>");
			  pageContext.getOut().write("Por favor comunique al administrador del sistema que no existen grados <br/>");
		    }
            catch(IOException ex) {
			  throw new JspTagException ("Error: la respuesta no se pudo escribir para los grados");
		    }
		    finally {
		     return SKIP_BODY;
		    }
		}

		// Asigna los valores a las variables que se muestran en jsp
		inicializarVariablesDeRetorno();

		return EVAL_BODY_INCLUDE;
	}

    public int doAfterBody() throws JspTagException {

      if (iteratorLineaBean.hasNext()) {
		  inicializarVariablesDeRetorno();
		  return EVAL_BODY_AGAIN;
	   }

	   return SKIP_BODY;

	}

	public int doEndTag() throws JspTagException
	{
       return EVAL_PAGE;
	}
}