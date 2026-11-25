
import java.util.*;

public class Grafo_LA<E> {

    // Clase interna para representar una arista
    public static class Arista<E> {

        private Nodo<E> destino;
        private double peso;
        private String etiqueta;

        public Arista(Nodo<E> destino, double peso) {
            this(destino, peso, null);
        }

        public Arista(Nodo<E> destino, double peso,
                      String etiqueta) {
            this.destino = destino;
            this.peso = peso;
            this.etiqueta = etiqueta;
        }

        public Nodo<E> getDestino() {
            return destino;
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
                return destino.getDato() + "(" + peso + ")";
            }

            return destino.getDato() + "(" + etiqueta
                    + ", $" + peso + ")";
        }
    }

    // Lista de adyacencia
    private Map<Nodo<E>, List<Arista<E>>> adyacencias;

    private boolean dirigido;

    public Grafo_LA(boolean dirigido) {
        this.dirigido = dirigido;
        adyacencias = new LinkedHashMap<>();
    }

    // Agregar nodo
    public void agregarNodo(Nodo<E> nodo) {

        if (!adyacencias.containsKey(nodo)) {
            adyacencias.put(nodo, new ArrayList<>());
        }
    }

    // Crear nodo directamente
    public Nodo<E> agregarNodo(int id, E dato) {

        Nodo<E> nodo = new Nodo<>(id, dato);
        agregarNodo(nodo);

        return nodo;
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

        if (!adyacencias.containsKey(origen) ||
            !adyacencias.containsKey(destino)) {

            throw new IllegalArgumentException(
                "Los nodos deben existir previamente");
        }

        adyacencias.get(origen).add(
            new Arista<>(destino, peso, etiqueta));

        // Si no es dirigido, agregar conexion inversa
        if (!dirigido) {

            adyacencias.get(destino).add(
                new Arista<>(origen, peso, etiqueta));
        }
    }

    // Agregar arista sin peso
    public void agregarArista(
            Nodo<E> origen,
            Nodo<E> destino) {

        agregarArista(origen, destino, 1.0);
    }

    // Consultar vecinos
    public List<Arista<E>> obtenerVecinos(Nodo<E> nodo) {

        if (!adyacencias.containsKey(nodo)) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(
            adyacencias.get(nodo));
    }

    // Consultar nodos
    public Set<Nodo<E>> obtenerNodos() {

        return Collections.unmodifiableSet(
            adyacencias.keySet());
    }

    // Consultar cantidad de nodos
    public int cantidadNodos() {
        return adyacencias.size();
    }

    // Mostrar grafo
    public void mostrarGrafo() {

        System.out.println("Grafo mediante listas:");

        for (Nodo<E> nodo : adyacencias.keySet()) {

            System.out.print(nodo.getDato() + " -> ");

            for (Arista<E> arista :
                    adyacencias.get(nodo)) {

                System.out.print(arista + " ");
            }

            System.out.println();
        }
    }
}
