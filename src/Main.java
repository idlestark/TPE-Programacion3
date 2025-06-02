public class Main {
    public static void main(String[] args) {
        // Paso 1: Cargar la configuración desde el archivo
        lectorTexto config = new lectorTexto();
        String rutaDelRecurso = "datasets/config.txt"; // Asegúrate de que el archivo esté en el classpath
        config.cargarConfiguracion(rutaDelRecurso);

        // Mostrar la configuración cargada
        config.mostrarConfiguracion();

        // Paso 2: Verificar si hay máquinas cargadas
        if (!config.getMaquinas().isEmpty()) {
            int sumaPiezasMaquinas = 0;
            for (Maquina m : config.getMaquinas()) {
                sumaPiezasMaquinas += m.getCantPiezas();
            }

            System.out.println("\nSuma de piezas que pueden producir las máquinas: " + sumaPiezasMaquinas);
            if (sumaPiezasMaquinas < config.getPiezasTotales()) {
                System.out.println("Advertencia: Las máquinas configuradas no producen suficientes piezas para el total requerido.");
            } else if (sumaPiezasMaquinas > config.getPiezasTotales()) {
                System.out.println("Información: Las máquinas configuradas pueden producir más piezas de las requeridas.");
            } else {
                System.out.println("Información: La capacidad de producción de las máquinas coincide con las piezas totales.");
            }

            // Paso 3: Crear la fábrica con las máquinas y piezasTotales
            Fabrica fabrica = new Fabrica(config.getMaquinas(), config.getPiezasTotales());

            // Si deseas usar servicioBacktracking luego, por ejemplo:
            servicioBacktracking servicio = new servicioBacktracking(fabrica);
            servicio.CantidadMinima(); // descomenta cuando tu clase Solucion esté lista
            servicio.imprimirSoluciones();

        } else {
            System.out.println("No se cargaron máquinas desde el archivo.");
        }
    }
}