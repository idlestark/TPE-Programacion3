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

        public void cargarConfiguracion(String rutaRecurso) {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(rutaRecurso);

            if (inputStream == null) {
                System.err.println("Error: No se pudo encontrar el archivo de configuración en el classpath: " + rutaRecurso);
                return;
            }


            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String linea;


                if ((linea = br.readLine()) != null) {
                    this.piezasTotales = Integer.parseInt(linea.trim());
                } else {
                    System.err.println("Error: El archivo de configuración '" + rutaRecurso + "' está vacío.");
                    return;
                }


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

