import java.util.*;


public class Solucion {
    private List<List<Maquina>> soluciones;
    private Integer tamanioMinimo;

    public Solucion(List<List<Maquina>> soluciones, Integer tamanioMinimo) {
        this.soluciones = soluciones = new ArrayList<>();
        this.tamanioMinimo = tamanioMinimo;
    }

    public void addSoluciones(List<Maquina> e){
        this.soluciones.add(e);
    }



    public List<List<Maquina>> getSoluciones() {
        return soluciones;
    }

    public void setTamanioMinimo(Integer tamanioMinimo) {
        this.tamanioMinimo = tamanioMinimo;
    }

    public Integer getTamanioMinimo() {
        return tamanioMinimo;
    }

    public void clearSoluciones(){
        this.soluciones.clear();
    }
}
