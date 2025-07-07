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

            // BACKTRACKING
            servicioBacktracking servicioBacktracking = new servicioBacktracking(fabrica);
            servicioBacktracking.CantidadMinima();
            Solucion solucion = servicioBacktracking.getSolucion();

            System.out.println("Backtracking");
            int cantidad = solucion.getSoluciones().size();

            if (cantidad == 1) {
                System.out.println("Solución obtenida: 1");
            } else if (cantidad > 1) {
                System.out.println("Soluciones obtenidas: " + cantidad);
            }

            int aux = 1;
            for (List<Maquina> lista : solucion.getSoluciones()) {
                System.out.println("Solución encontrada " + aux + ":");
                for (Maquina maquina : lista) {
                    System.out.print(" (" + maquina.getNombre() + "," + maquina.getCantPiezas() + ")");
                }
                System.out.println();
                aux++;
            }
            System.out.println("Cantidad de piezas requeridas: " + config.getPiezasTotales());
            System.out.println("Cantidad de máquinas puestas en funcionamiento requeridas: " + servicioBacktracking.getSolucion().getTamanioMinimo());
            System.out.println("Cantidad de estados generados: " + servicioBacktracking.getCantidadEstados() + ", se contaron todas las combinaciones válidas exploradas para alcanzar la cantidad de piezas necesarias.");

            System.out.println(" ");





                // GREEDY

                servicioGreedy servicioGreedy = new servicioGreedy(fabrica);
                List<Maquina> solucionGreedy = servicioGreedy.greedy();

                System.out.println("Greedy");
                if (solucionGreedy == null || solucionGreedy.isEmpty()) {
                    System.out.println("No se encontró una solución válida usando el enfoque Greedy.");
                    System.out.println("Cantidad de piezas requeridas: " + config.getPiezasTotales());
                    System.out.println("Cantidad de máquinas puestas en funcionamiento requeridas: -");
                } else {
                    System.out.println("Solución encontrada:");
                    for (Maquina maquina : solucionGreedy) {
                        System.out.print(" (" + maquina.getNombre() + "," + maquina.getCantPiezas() + ")");
                    }
                    System.out.println();
                    System.out.println("Cantidad de piezas requeridas: " + config.getPiezasTotales());
                    System.out.println("Cantidad de máquinas puestas en funcionamiento requeridas: " + solucionGreedy.size());
                }

                System.out.println("Cantidad de estados generados: " + servicioGreedy.getCantidadEstados() +
                        ", se contaron los usos efectivos de máquinas durante la selección greedy.");
            }
        }
    }

