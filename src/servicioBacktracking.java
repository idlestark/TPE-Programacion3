import java.util.*;

public class servicioBacktracking {
    private Solucion solucion = new Solucion(null, 0);
    private Fabrica fabrica;
    private Integer cantidadEstados = 0;

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


    public Integer getCantidadEstados() {
        return cantidadEstados;
    }

    public void CantidadMinima(){
        List<Maquina> solu = new ArrayList<>();
        Integer contador = 0;

        backtracking(solu, contador);
    }


    /*
     * Estrategia de resolución - Backtracking:
     *
     * - Árbol de exploración:
     *   Se construye un árbol de decisiones en donde cada nivel representa la elección de una máquina
     *   que se agrega a la secuencia de producción. Desde la raíz (sin máquinas) se generan ramas
     *   probando cada máquina posible, y se va acumulando la cantidad total de piezas producidas.
     *
     * - Estados:
     *   Cada estado está definido por la secuencia parcial de máquinas usadas
     *   y la cantidad acumulada de piezas.
     *   El objetivo es alcanzar exactamente la cantidad de piezas requeridas.
     *
     * - Estados solución:
     *   Un estado es solución cuando la suma exacta de piezas alcanza el objetivo y se almacena
     *   si es más corta que las soluciones previas o si tiene igual longitud (cantidad de puestas en funcionamiento).
     *
     * - Podas:
     *   1. Si se supera la cantidad de piezas requeridas (contador > piezas), se corta la rama.
     *   2. Si ya se encontró una solución y la secuencia parcial actual es igual o más larga que la mejor,
     *      se evita seguir explorando esa rama (solu.size() >= tamanioMinimo).
     */


    private void backtracking(List<Maquina> solu, Integer contador) {
        cantidadEstados++;
        if (contador > this.fabrica.getPiezas()) {
            return;
        }
        if (contador == this.fabrica.getPiezas()){
            if (this.solucion.getTamanioMinimo() == 0 || solu.size() < this.solucion.getTamanioMinimo()) {
                this.solucion.clearSoluciones();
                this.solucion.addSoluciones(new ArrayList<>(solu));
                this.solucion.setTamanioMinimo(solu.size());
            } else if (solu.size() == this.solucion.getTamanioMinimo()) {
                this.solucion.addSoluciones(new ArrayList<>(solu));
            }
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

    public void imprimirSolucionesBacktracking() {
        Integer aux = 0;
        System.out.println("Backtracking");
        if (this.solucion.getSoluciones().size()== 1) {
            System.out.println("Solución obtenida: " + this.solucion.getSoluciones().size());
        } else if (this.solucion.getSoluciones().size() > 1) {
            System.out.println("Soluciones obtenidas: " + this.solucion.getSoluciones().size());
        }
        for (List<Maquina> lista : this.solucion.getSoluciones()) {
            aux++;
            System.out.println("Solución encontrada " + aux + ":");
            for (Maquina maquina : lista) {
                System.out.print(" " + "(" + maquina.getNombre() + "," + maquina.getCantPiezas() + ")");
            }
            System.out.println();
        }
    }
}
