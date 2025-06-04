import java.util.ArrayList;
import java.util.List;

public class servicioGreedy {
    private Solucion solucion = new Solucion(null, 0);
    private Fabrica fabrica;

    public servicioGreedy(Fabrica fabrica) {
        this.fabrica = fabrica;
    }

    public void greedy(Integer num) {
        List<Maquina> solucion = new ArrayList<Maquina>();
        List<Maquina> candidatos = this.fabrica.getMaquinas();

        while (!this.fabrica.getMaquinas().isEmpty() && !this.solucion.getSoluciones().contains(solucion)) {
            Maquina c = seleccionar(candidatos, num);
            candidatos.remove(c);
            if (solucionSuma(solucion, c, num)){
                solucion.add(c);
            }
        }
        if (!this.solucion.getSoluciones().contains(solucion)) {
            this.solucion.getSoluciones().add(solucion);
        }
        else{
            System.out.println("No se ha encontrado una solucion");
        }

    }

    public boolean solucionSuma(List<Maquina> solucion, Maquina c, Integer num) {
        Integer suma = 0;
        for (Maquina m : solucion) {
            suma = suma + m.getCantPiezas();
        }
        suma = suma + c.getCantPiezas();
        if (suma == num) {
            return true;
        }
        return false;
    }

    public Maquina seleccionar(List<Maquina> candidatos, Integer num) {
        Integer aux = 0;
        Maquina mayor = new Maquina("M0", 0);
        for (Maquina candidato : candidatos) {
           if ((candidato.getCantPiezas()) > aux && (candidato.getCantPiezas() <= num)) {
              mayor = candidato;
           }
        }
        return mayor;
    }
}
