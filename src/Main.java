import java.util.List;

public class Main {
    public static void main(String[] args) {

        lectorTexto config = new lectorTexto();
        String rutaDelRecurso = "datasets/config.txt";
        config.cargarConfiguracion(rutaDelRecurso);


        config.mostrarConfiguracion();


        if (!config.getMaquinas().isEmpty()) {
            int sumaPiezasMaquinas = 0;
            for (Maquina m : config.getMaquinas()) {
                sumaPiezasMaquinas += m.getCantPiezas();
            }

            System.out.println(" ");
            Fabrica fabrica = new Fabrica(config.getMaquinas(), config.getPiezasTotales());

            //BACKTRACKING

            servicioBacktracking servicioBacktracking = new servicioBacktracking(fabrica);
            servicioBacktracking.CantidadMinima();
            servicioBacktracking.imprimirSolucionesBacktracking();
            System.out.println("Cantidad de piezas requeridas: " + config.getPiezasTotales());
            System.out.println("Cantidad de maquinas puestas en funcionamiento requeridas: " + servicioBacktracking.getSolucion().getTamanioMinimo());
            System.out.println("Cantidad de estados generados: " + servicioBacktracking.getCantidadEstados() + ", se contaron todas las combinaciones de máquinas posibles para alcanzar la cantidad de piezas necesarias.");

            System.out.println(" ");

            // GREEDY

            servicioGreedy servicioGreedy = new servicioGreedy(fabrica);
            List<Maquina> solucionGreedy = servicioGreedy.greedy();
            servicioGreedy.imprimirSolucionesGreedy(solucionGreedy);
            System.out.println("Cantidad de piezas requeridas: " + config.getPiezasTotales());
            System.out.println("Cantidad de maquinas puestas en funcionamiento requeridas: " + solucionGreedy.size());
            System.out.println("Cantidad de estados generados: " + servicioGreedy.getCantidadEstados() + ", se contaron todos los candidatos posibles cada vez que se iba a seleccionar una máquina.");


        } else {
            System.out.println("No se cargaron máquinas desde el archivo.");


        }
    }
}