package com.solucionesweb.tags;

import com.solucionesweb.losbeans.fachada.FachadaContableRetencionBean;
import com.solucionesweb.losbeans.negocio.ContableRetencionBean;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.TagSupport;

public class ListaRetencionTag
  extends TagSupport
  implements IterationTag
{
  private String idLocalTag;
  FachadaContableRetencionBean fachadaBean;
  ContableRetencionBean contableRetencionBean;
  Iterator iteratorBean;
  
  public void setIdLocalTag(String idLocalTag)
  {
    this.idLocalTag = idLocalTag;
  }
  
  public String getIdLocalTag()
  {
    return this.idLocalTag;
  }
  
  private void inicializarVariablesDeRetorno()
  {
    this.fachadaBean = ((FachadaContableRetencionBean)this.iteratorBean.next());
    

    this.pageContext.setAttribute("idConceptoVar", this.fachadaBean.getIdConceptoStr());
    this.pageContext.setAttribute("idSubcuentaVar", this.fachadaBean.getIdSubcuenta());
    this.pageContext.setAttribute("idPersonaVar", this.fachadaBean.getIdPersonaStr());
    this.pageContext.setAttribute("nombreConceptoVar", this.fachadaBean.getNombreConcepto());
    this.pageContext.setAttribute("porcentajeRetencionVar", this.fachadaBean.getPorcentajeRetencionStr());
    this.pageContext.setAttribute("vrBaseRetencionVar", this.fachadaBean.getVrBaseRetencionStr());
    this.pageContext.setAttribute("estadoVar", this.fachadaBean.getEstadoStr());
    this.pageContext.setAttribute("idSeqVar", this.fachadaBean.getIdSeqStr());
    this.pageContext.setAttribute("idTipoOrdenAlcanceVar", this.fachadaBean.getIdTipoOrdenAlcanceStr());
  }
  
  public int doStartTag()
    throws JspTagException
  {
    this.contableRetencionBean = new ContableRetencionBean();
    

    Vector vectorBean = this.contableRetencionBean.listaAll();
    
        //
        iteratorBean = vectorBean.iterator();

        if (!iteratorBean.hasNext()) {
            try {
                pageContext.getOut().write("<br>No existen datos");
            } catch (IOException ex) {
                throw new JspTagException("Error: la respuesta no se pudo escribir para los datos");
            } finally {
                return SKIP_BODY;
            }
        }

        // Asigna los valores a las variables que se muestran en jsp
        inicializarVariablesDeRetorno();

        return EVAL_BODY_INCLUDE;
    }

    public int doAfterBody() throws JspTagException {

        if (iteratorBean.hasNext()) {
            inicializarVariablesDeRetorno();
            return EVAL_BODY_AGAIN;
        }

        return SKIP_BODY;

    }

    public int doEndTag() throws JspTagException {
        return EVAL_PAGE;
    }
}
