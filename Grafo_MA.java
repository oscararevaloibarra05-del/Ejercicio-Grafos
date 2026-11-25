
import java.util.*;

public class Grafo_MA<E> {

    // Clase para representar una conexion
    public static class Conexion {

        private double peso;
        private String etiqueta;

        public Conexion(double peso, String etiqueta) {
            this.peso = peso;
            this.etiqueta = etiqueta;
        }

        public double getPeso() {
            return peso;
        }

        public String getEtiqueta() {
            return etiqueta;
        }

        @Override
        public String toString() {

            if (etiqueta == null) {
                return String.valueOf(peso);
            }

            return etiqueta + "($" + peso + ")";
        }
    }

    private List<Nodo<E>> nodos;
    private Conexion[][] matriz;

    private boolean dirigido;

    public Grafo_MA(boolean dirigido) {

        this.dirigido = dirigido;

        nodos = new ArrayList<>();

        matriz = new Conexion[0][0];
    }

    // Agregar nodo
    public void agregarNodo(Nodo<E> nodo) {

        if (nodos.contains(nodo)) {
            return;
        }

        int n = nodos.size();

        nodos.add(nodo);

        Conexion[][] nuevaMatriz =
                new Conexion[n + 1][n + 1];

        // Copiar matriz anterior
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                nuevaMatriz[i][j] = matriz[i][j];
            }
        }

        matriz = nuevaMatriz;
    }

    // Crear nodo directamente
    public Nodo<E> agregarNodo(int id, E dato) {

        Nodo<E> nodo = new Nodo<>(id, dato);

        agregarNodo(nodo);

        return nodo;
    }

    // Obtener posicion de un nodo
    private int obtenerIndice(Nodo<E> nodo) {
        return nodos.indexOf(nodo);
    }

    // Agregar arista ponderada
    public void agregarArista(
            Nodo<E> origen,
            Nodo<E> destino,
            double peso) {

        agregarArista(origen, destino, peso, null);
    }

    // Agregar arista con etiqueta y costo
    public void agregarArista(
            Nodo<E> origen,
            Nodo<E> destino,
            double peso,
            String etiqueta) {

        int i = obtenerIndice(origen);
        int j = obtenerIndice(destino);

        if (i == -1 || j == -1) {

            throw new IllegalArgumentException(
                "Los nodos deben existir previamente");
        }

        matriz[i][j] = new Conexion(peso, etiqueta);

        // Si no es dirigido, agregar conexion inversa
        if (!dirigido) {

            matriz[j][i] = new Conexion(peso, etiqueta);
        }
    }

    // Agregar arista sin peso
    public void agregarArista(
            Nodo<E> origen,
            Nodo<E> destino) {

        agregarArista(origen, destino, 1.0);
    }

    // Consultar peso
    public double obtenerPeso(
            Nodo<E> origen,
            Nodo<E> destino) {

        int i = obtenerIndice(origen);
        int j = obtenerIndice(destino);

        if (i == -1 || j == -1 || matriz[i][j] == null) {

            return Double.POSITIVE_INFINITY;
        }

        return matriz[i][j].getPeso();
    }

    // Consultar etiqueta
    public String obtenerEtiqueta(
            Nodo<E> origen,
            Nodo<E> destino) {

        int i = obtenerIndice(origen);
        int j = obtenerIndice(destino);

        if (i == -1 || j == -1 || matriz[i][j] == null) {
            return null;
        }

        return matriz[i][j].getEtiqueta();
    }

    // Consultar nodos
    public List<Nodo<E>> obtenerNodos() {

        return Collections.unmodifiableList(nodos);
    }

    // Consultar cantidad de nodos
    public int cantidadNodos() {
        return nodos.size();
    }

    // Consultar existencia de arista
    public boolean existeArista(
            Nodo<E> origen,
            Nodo<E> destino) {

        return obtenerPeso(origen, destino)
                != Double.POSITIVE_INFINITY;
    }

    // Mostrar matriz
    public void mostrarGrafo() {

        System.out.println("Grafo mediante matriz:");

        System.out.print("      ");

        for (Nodo<E> nodo : nodos) {

            System.out.printf("%15s", nodo.getDato());
        }

        System.out.println();

        for (int i = 0; i < nodos.size(); i++) {

            System.out.printf(
                "%6s", nodos.get(i).getDato());

            for (int j = 0; j < nodos.size(); j++) {

                if (matriz[i][j] == null) {

                    System.out.printf("%15s", "INF");

                } else {

                    System.out.printf(
                        "%15s", matriz[i][j]);
                }
            }

            System.out.println();
        }
    }
}
