import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

    public class lectorTexto {
        private int piezasTotales;
        private List<Maquina> maquinas;

        public lectorTexto() {
            this.maquinas = new ArrayList<>();
        }

        /**
         * Carga la configuración desde un archivo ubicado en el classpath.
         * @param rutaRecurso La ruta al archivo dentro del classpath,
         * ej. "datasets/config.txt"
         */
        public void cargarConfiguracion(String rutaRecurso) {
            // Obtener el ClassLoader para cargar el recurso
            ClassLoader classLoader = getClass().getClassLoader();
            // Obtener el recurso como un InputStream
            InputStream inputStream = classLoader.getResourceAsStream(rutaRecurso);

            if (inputStream == null) {
                System.err.println("Error: No se pudo encontrar el archivo de configuración en el classpath: " + rutaRecurso);
                // Intenta con una barra inicial si la estructura lo requiere (menos común para ClassLoader)
                // inputStream = classLoader.getResourceAsStream("/" + rutaRecurso);
                // if (inputStream == null) {
                //     System.err.println("Error: Tampoco se pudo encontrar con '/' al inicio: /" + rutaRecurso);
                //     return;
                // }
                return;
            }

            // Usar InputStreamReader para leer el InputStream con una codificación específica (UTF-8 es común)
            // y BufferedReader para una lectura eficiente línea por línea.
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String linea;

                // Leer la primera línea para PiezasTotales
                if ((linea = br.readLine()) != null) {
                    this.piezasTotales = Integer.parseInt(linea.trim());
                } else {
                    System.err.println("Error: El archivo de configuración '" + rutaRecurso + "' está vacío.");
                    return;
                }

                // Leer las líneas siguientes para las máquinas
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(",");
                    if (partes.length == 2) {
                        String nombreMaquina = partes[0].trim();
                        int cantPiezasMaquina = Integer.parseInt(partes[1].trim());
                        this.maquinas.add(new Maquina(nombreMaquina, cantPiezasMaquina));
                    } else {
                        System.err.println("Advertencia: Línea mal formada ignorada en '" + rutaRecurso + "' -> " + linea);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error al leer el archivo de configuración '" + rutaRecurso + "': " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Error al convertir un número del archivo '" + rutaRecurso + "': " + e.getMessage());
            }
        }

        public int getPiezasTotales() {
            return piezasTotales;
        }

        public List<Maquina> getMaquinas() {
            return maquinas;
        }

        public void mostrarConfiguracion() {
            System.out.println("Piezas Totales a Producir: " + piezasTotales);
            System.out.println("Configuración de Máquinas:");
            if (maquinas.isEmpty() && piezasTotales == 0) {
                System.out.println("  (No se cargó ninguna configuración o el archivo estaba vacío/no se encontró)");
                return;
            }
            for (Maquina maquina : maquinas) {
                System.out.println("  - " + maquina.getNombre() + ": " + maquina.getCantPiezas() + " piezas");
            }
        }

    }

