import java.util.*;


public class Solucion {
    private List<List<Maquina>> soluciones;
    private Integer tamanioMinimo;

    public Solucion(List<List<Maquina>> soluciones, Integer tamanioMinimo) {
        this.soluciones = soluciones = new ArrayList<>();
        this.tamanioMinimo = tamanioMinimo;
    }

    public void addSoluciones(List<Maquina> e){

        /// verificar si esta la solucion y excluir las de diferente orden
        if (!esIgual(e)){
            this.soluciones.add(e);
        }

    }

    public boolean esIgual(List<Maquina> e){
        Integer aux = 0;
        for (List<Maquina> lista : soluciones){
            if (e == lista){
                return true;
            }
            if (e.size() != lista.size()){
                return false;
            }
            for (Maquina maquina : lista){
                if (Collections.frequency(e, maquina) != Collections.frequency(lista, maquina)){
                    return false;
                }
            }

        }

        return false;
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


}
