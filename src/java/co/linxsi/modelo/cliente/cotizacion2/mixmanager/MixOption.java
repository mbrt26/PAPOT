package co.linxsi.modelo.cliente.cotizacion2.mixmanager;


/**
 * Represents the MixesOption entity.
 */
public class MixOption {

    private int id;

    private Double density = 0.0;

    private Double cost = 0.0;

    private long idMix;

    private long idOption;

    private int selected;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public long getIdMix() {
        return idMix;
    }

    public void setIdMix(long idMix) {
        this.idMix = idMix;
    }

    public long getIdOption() {
        return idOption;
    }

    public void setIdOption(long idOption) {
        this.idOption = idOption;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }
}
