import java.util.*;

public class Fabrica {
    private List<Maquina> maquinas;
    private Integer piezas;

    public Fabrica(List<Maquina> maquinas, Integer piezas) {
        this.maquinas = maquinas;
        this.piezas = piezas;
    }

    public List<Maquina> getMaquinas() {
        return this.maquinas;
    }

    public void setMaquinas(List<Maquina> maquinas) {
        this.maquinas = maquinas;
    }

    public Integer getPiezas() {
        return piezas;
    }

    public void setPiezas(Integer piezas) {
        this.piezas = piezas;
    }
}
