package co.linxsi.controlador.menu;


import co.linxsi.modelo.menu.MenuDAO;
import co.linxsi.modelo.menu.MenuDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.TagSupport;

public class ListaOpcionTag extends TagSupport
  implements IterationTag
{
  private int idTipoOpcionTag;
  MenuDTO fachadaOpcionBean;
  MenuDAO opcionBean;
  Iterator iteratorBean;

  private void inicializarVariablesDeRetorno()
  {
    this.fachadaOpcionBean = ((MenuDTO)this.iteratorBean.next());

    this.pageContext.setAttribute("idOpcionPadreVar", this.fachadaOpcionBean.getIdOpcionPadreStr());
    this.pageContext.setAttribute("nombreOpcionPadreVar", this.fachadaOpcionBean.getNombreOpcion());
    this.pageContext.setAttribute("rutaOpcionPadreVar", this.fachadaOpcionBean.getRutaOpcion());
    this.pageContext.setAttribute("descripcionPadreVar", this.fachadaOpcionBean.getDescripcion());
    this.pageContext.setAttribute("iconoVar", this.fachadaOpcionBean.getIcono());
  }

  public int doStartTag()
    throws JspTagException
  {
    this.opcionBean = new MenuDAO();

    this.opcionBean.setIdPerfil(getIdTipoOpcionTag());

    ArrayList listaVector = this.opcionBean.listaOpcionPerfil();

    this.iteratorBean = listaVector.iterator();
    if (!this.iteratorBean.hasNext()) {
      try {
        this.pageContext.getOut().write("La consulta no devolvio ningun dato <br/>");
      } catch (IOException ex) {
        throw new JspTagException("Error: la respuesta no se pudo escribir para los grados");
      } finally {
        return 0;
      }
    }

    inicializarVariablesDeRetorno();
    return 1;
  }

  public int doAfterBody() throws JspTagException {
    if (this.iteratorBean.hasNext()) {
      inicializarVariablesDeRetorno();
      return 2;
    }
    return 0;
  }

  public int doEndTag() throws JspTagException {
    return 6;
  }

  public int getIdTipoOpcionTag() {
    return this.idTipoOpcionTag;
  }

  public void setIdTipoOpcionTag(int idTipoOpcionTag) {
    this.idTipoOpcionTag = idTipoOpcionTag;
  }
}