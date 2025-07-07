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

        List<Maquina> maquinasOrdenadas = new ArrayList<>(this.fabrica.getMaquinas());
        maquinasOrdenadas.sort((m1, m2) -> Integer.compare(m2.getCantPiezas(), m1.getCantPiezas()));

        for (Maquina m : maquinasOrdenadas) {
            while (m.getCantPiezas() <= piezasRestantes) {
                solucion.add(m);
                piezasRestantes -= m.getCantPiezas();
                cantidadEstados++;
            }
        }

        if (piezasRestantes == 0) {
            return solucion;
        } else {
            return new ArrayList<>(); // No hay solución greedy válida
        }
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



}