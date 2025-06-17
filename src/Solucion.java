import java.util.*;


public class Solucion {
    private List<List<Maquina>> soluciones;
    private Integer tamanioMinimo;

    public Solucion(List<List<Maquina>> soluciones, Integer tamanioMinimo) {
        this.soluciones = soluciones = new ArrayList<>();
        this.tamanioMinimo = tamanioMinimo;
    }

    public void addSoluciones(List<Maquina> e){

        if (!loContiene(e)){
            this.soluciones.add(e);
        }

    }

    public boolean loContiene(List<Maquina> e) {
        for (List<Maquina> lista : soluciones) {
            if (e.size() != lista.size()) {
                continue;
            }

            Map<Maquina, Integer> conteoE = contarFrecuencias(e);
            Map<Maquina, Integer> conteoLista = contarFrecuencias(lista);

            if (conteoE.equals(conteoLista)) {
                return true;
            }
        }
        return false;
    }

    private Map<Maquina, Integer> contarFrecuencias(List<Maquina> lista) {
        Map<Maquina, Integer> mapa = new HashMap<>();
        for (Maquina m : lista) {
            mapa.put(m, mapa.getOrDefault(m, 0) + 1);
        }
        return mapa;
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
