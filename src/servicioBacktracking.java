import java.util.*;

public class servicioBacktracking {
    private Solucion solucion = new Solucion(null, 0);
    private Fabrica fabrica;

    public servicioBacktracking(Fabrica fabrica) {
        this.fabrica = fabrica;

    }

    public Solucion getSolucion() {
        return solucion;
    }

    public void setSolucion(Solucion solucion) {
        this.solucion = solucion;
    }

    public Fabrica getFabrica() {
        return fabrica;
    }

    public void setFabrica(Fabrica fabrica) {
        this.fabrica = fabrica;
    }


    public void CantidadMinima(){
        List<Maquina> solu = new ArrayList<>();
        Integer contador = 0;

        backtracking(solu, contador);
    }

    private void backtracking(List<Maquina> solu, Integer contador) {
        if (contador > this.fabrica.getPiezas()) {
            return;
        }

        if (contador == this.fabrica.getPiezas()){
            if (this.solucion.getTamanioMinimo() ==0) {

                this.solucion.addSoluciones(new ArrayList<>(solu));
                this.solucion.setTamanioMinimo(solu.size());

            }
            else if (solu.size() == this.solucion.getTamanioMinimo()) {
                this.solucion.addSoluciones(new ArrayList<>(solu));
            }
            return;
        }

        if (this.solucion.getTamanioMinimo() != 0 && solu.size() >= this.solucion.getTamanioMinimo()) return;
        List<Maquina> maquinas = this.fabrica.getMaquinas();
        for (Maquina maquina : maquinas) {

            solu.add(maquina);

            Integer aux = contador + maquina.getCantPiezas();
            backtracking(solu,aux);

            solu.remove(solu.size() - 1);
        }

    }



    public Integer suma(List<Maquina> solu) {
        Integer cantidad = 0;
        for (Maquina maquina : solu) {
            cantidad = maquina.getCantPiezas() + cantidad;
        }
        return cantidad;
    }

    public void imprimirSoluciones() {
        System.out.println("cantidad de soluciones: " + this.solucion.getSoluciones().size());
        for (List<Maquina> lista : this.solucion.getSoluciones()) {
            System.out.println("Solución encontrada: ");
            for (Maquina maquina : lista) {
                System.out.print(" " + maquina.getNombre());
            }
            System.out.println();
        }
    }
}
