package co.linxsi.controlador.menu;


import co.linxsi.modelo.menu.MenuDAO;
import co.linxsi.modelo.menu.MenuDTO;
import co.linxsi.modelo.menu.PerfilDAO;
import co.linxsi.modelo.menu.PerfilDTO;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.TagSupport;

public class ListaUnNivelTag extends TagSupport
        implements IterationTag {

    private String idPerfilTag;
    private String idOpcionTag;
    MenuDTO fachadaOpcionBean;
    PerfilDTO fachadaOpcionPerfil;
    MenuDAO opcionBean;
    PerfilDAO opcionPerfilBean;
    Iterator iteratorBean;
    Iterator iteratorOpcionPerfilBean;

    public String getIdPerfilTag() {
        return this.idPerfilTag;
    }

    public void setIdPerfilTag(String idPerfilTag) {
        this.idPerfilTag = idPerfilTag;
    }

    public int getIdPerfil() {
        return Integer.parseInt(getIdPerfilTag());
    }

    public String getIdOpcionTag() {
        return this.idOpcionTag;
    }

    public void setIdOpcionTag(String idOpcionTag) {
        this.idOpcionTag = idOpcionTag;
    }

    private void inicializarVariablesDeRetorno() {
        this.fachadaOpcionBean = ((MenuDTO) this.iteratorBean.next());

        this.pageContext.setAttribute("idOpcionVar", this.fachadaOpcionBean.getIdOpcionStr());
        this.pageContext.setAttribute("nombreOpcionVar", this.fachadaOpcionBean.getNombreOpcion());
        this.pageContext.setAttribute("rutaOpcionVar", this.fachadaOpcionBean.getRutaOpcion());
        this.pageContext.setAttribute("idSubOpcionPadreVar", this.fachadaOpcionBean.getIdOpcionStr());
    }

    public int doStartTag()
            throws JspTagException {
        this.opcionBean = new MenuDAO();

        this.opcionBean.setIdOpcion(getIdOpcionTag());
        this.opcionBean.setIdTipoOpcion(2);

        Vector listaVector = this.opcionBean.listaUnNivelPerfil(getIdPerfil());

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

    public int doAfterBody()
            throws JspTagException {
        if (this.iteratorBean.hasNext()) {
            inicializarVariablesDeRetorno();
            return 2;
        }

        return 0;
    }

    public int doEndTag()
            throws JspTagException {
        return 6;
    }
}
