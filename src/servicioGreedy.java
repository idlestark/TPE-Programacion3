import java.util.ArrayList;
import java.util.List;

public class servicioGreedy {
    private Fabrica fabrica;
    private Integer cantidadEstados = 0;

    public Integer getCantidadEstados() {
        return cantidadEstados;
    }




    public servicioGreedy(Fabrica fabrica) {
        this.fabrica = fabrica;
    }



    /*
     * Estrategia de resolución - Greedy:
     *
     * - Candidatos:
     *   Las máquinas disponibles en la fábrica, cada una con su capacidad de producción.
     *
     * - Estrategia de selección de candidatos:
     *   En cada iteración, se selecciona la máquina que produzca la mayor cantidad de piezas posible
     *   sin superar la cantidad de piezas restantes por producir. Esto se hace mediante la función `seleccionar()`.
     *
     * - Heurística:
     *   La estrategia está basada en elegir localmente la opción que más contribuye a reducir
     *   la cantidad restante (máxima producción sin pasarse), con la esperanza de minimizar la cantidad total de pasos.
     *
     * - Consideraciones sobre la solución:
     *   No siempre se garantiza una solución óptima o siquiera una solución válida.
     *   Dado que el enfoque Greedy no retrocede ni considera combinaciones, puede fallar en encontrar solución
     *   cuando sí existe una (es decir, no es completo). Por ejemplo, puede quedar atrapado si ninguna máquina
     *   puede producir exactamente las piezas restantes.
     *
     * - Métrica de exploración:
     *   Se lleva un contador de `cantidadEstados` que indica cuántas veces se evaluaron candidatos
     *   durante la selección.
     */


    public List<Maquina> greedy() {
        int piezasRestantes = this.fabrica.getPiezas();
        List<Maquina> solucion = new ArrayList<>();
        List<Maquina> maquinas = this.fabrica.getMaquinas();

        while (piezasRestantes > 0) {
            Maquina seleccionada = seleccionar(maquinas, piezasRestantes);
            if (seleccionada == null) {
                System.out.println("No se ha encontrado una solución Greedy posible.");
                return new ArrayList<>();
            }
            solucion.add(seleccionada);
            piezasRestantes -= seleccionada.getCantPiezas();
        }

        return solucion;
    }


    public Maquina seleccionar(List<Maquina> candidatos, int piezasRestantes) {
        Maquina mejor = null;
        for (Maquina m : candidatos) {
            cantidadEstados++;
            if (m.getCantPiezas() <= piezasRestantes) {
                if (mejor == null || m.getCantPiezas() > mejor.getCantPiezas()) {
                    mejor = m;
                }
            }
        }
        return mejor;
    }




    public void imprimirSolucionesGreedy(List<Maquina> solucionGreedy){
            System.out.println("Greedy");
            if (solucionGreedy.isEmpty()) {
                System.out.println("No se encontraron soluciones usando el enfoque Greedy.");
            } else {
                System.out.println("Solución encontrada: ");
                for (Maquina maquina : solucionGreedy) {
                    System.out.print(" " + "(" + maquina.getNombre() + "," + maquina.getCantPiezas() + ")");
                }
                System.out.println();
            }
    }
}