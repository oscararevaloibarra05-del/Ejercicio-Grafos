
import java.util.*;

public class AlgoritmosGrafos {

    // =====================================
    // ESTRUCTURAS AUXILIARES
    // =====================================

    // Conexion entre dos nodos
    static class Paso<E> {

        Nodo<E> destino;
        double peso;

        public Paso(Nodo<E> destino, double peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    // Estado para Dijkstra
    static class Estado<E> {

        Nodo<E> nodo;
        double distancia;

        public Estado(Nodo<E> nodo, double distancia) {
            this.nodo = nodo;
            this.distancia = distancia;
        }
    }

    // Estructura comun para los algoritmos
    public static class Vista<E> {

        Map<Nodo<E>, List<Paso<E>>> adyacencias =
                new LinkedHashMap<>();
    }

    // =====================================
    // CONVERTIR LISTA DE ADYACENCIA
    // =====================================

    public static <E> Vista<E> desdeLista(
            Grafo_LA<E> grafo) {

        Vista<E> vista = new Vista<>();

        for (Nodo<E> nodo : grafo.obtenerNodos()) {

            vista.adyacencias.put(
                nodo, new ArrayList<>());

            for (Grafo_LA.Arista<E> arista :
                    grafo.obtenerVecinos(nodo)) {

                vista.adyacencias.get(nodo).add(
                    new Paso<>(
                        arista.getDestino(),
                        arista.getPeso()
                    )
                );
            }
        }

        return vista;
    }

    // =====================================
    // CONVERTIR MATRIZ DE ADYACENCIA
    // =====================================

    public static <E> Vista<E> desdeMatriz(
            Grafo_MA<E> grafo) {

        Vista<E> vista = new Vista<>();

        List<Nodo<E>> nodos = grafo.obtenerNodos();

        for (Nodo<E> nodo : nodos) {

            vista.adyacencias.put(
                nodo, new ArrayList<>());
        }

        for (Nodo<E> origen : nodos) {

            for (Nodo<E> destino : nodos) {

                if (grafo.existeArista(origen, destino)) {

                    vista.adyacencias.get(origen).add(
                        new Paso<>(
                            destino,
                            grafo.obtenerPeso(origen, destino)
                        )
                    );
                }
            }
        }

        return vista;
    }

    // =====================================
    // DFS - BUSQUEDA EN PROFUNDIDAD
    // =====================================

    public static <E> List<Nodo<E>> DFS(
            Vista<E> grafo,
            Nodo<E> inicio) {

        List<Nodo<E>> visitados = new ArrayList<>();

        if (!grafo.adyacencias.containsKey(inicio)) {
            return visitados;
        }

        Set<Nodo<E>> vistos = new LinkedHashSet<>();

        Stack<Nodo<E>> pila = new Stack<>();

        pila.push(inicio);

        while (!pila.isEmpty()) {

            Nodo<E> actual = pila.pop();

            if (!vistos.contains(actual)) {

                vistos.add(actual);

                visitados.add(actual);

                List<Paso<E>> vecinos =
                        grafo.adyacencias.get(actual);

                // Insertar vecinos en orden inverso
                for (int i = vecinos.size() - 1; i >= 0; i--) {

                    Nodo<E> destino = vecinos.get(i).destino;

                    if (!vistos.contains(destino)) {

                        pila.push(destino);
                    }
                }
            }
        }

        return visitados;
    }

    // =====================================
    // BFS - BUSQUEDA EN AMPLITUD
    // =====================================

    public static <E> List<Nodo<E>> BFS(
            Vista<E> grafo,
            Nodo<E> inicio) {

        List<Nodo<E>> visitados = new ArrayList<>();

        if (!grafo.adyacencias.containsKey(inicio)) {
            return visitados;
        }

        Set<Nodo<E>> vistos = new LinkedHashSet<>();

        Queue<Nodo<E>> cola = new LinkedList<>();

        cola.add(inicio);

        vistos.add(inicio);

        while (!cola.isEmpty()) {

            Nodo<E> actual = cola.poll();

            visitados.add(actual);

            for (Paso<E> paso :
                    grafo.adyacencias.get(actual)) {

                Nodo<E> destino = paso.destino;

                if (!vistos.contains(destino)) {

                    vistos.add(destino);

                    cola.add(destino);
                }
            }
        }

        return visitados;
    }

    // =====================================
    // DIJKSTRA - MENOR COSTO
    // =====================================

    public static <E> void Dijkstra(
            Vista<E> grafo,
            Nodo<E> inicio,
            Nodo<E> destino) {

        if (!grafo.adyacencias.containsKey(inicio) ||
            !grafo.adyacencias.containsKey(destino)) {

            System.out.println("El nodo no existe.");

            return;
        }

        // Dijkstra requiere pesos no negativos
        for (List<Paso<E>> pasos :
                grafo.adyacencias.values()) {

            for (Paso<E> paso : pasos) {

                if (paso.peso < 0) {

                    throw new IllegalArgumentException(
                        "Dijkstra no admite pesos negativos.");
                }
            }
        }

        Map<Nodo<E>, Double> distancias =
                new HashMap<>();

        Map<Nodo<E>, Nodo<E>> anteriores =
                new HashMap<>();

        PriorityQueue<Estado<E>> cola =
                new PriorityQueue<>(
                    Comparator.comparingDouble(
                        estado -> estado.distancia
                    )
                );

        // Inicializar distancias
        for (Nodo<E> nodo : grafo.adyacencias.keySet()) {

            distancias.put(
                nodo, Double.POSITIVE_INFINITY);
        }

        distancias.put(inicio, 0.0);

        cola.add(new Estado<>(inicio, 0.0));

        while (!cola.isEmpty()) {

            Estado<E> estado = cola.poll();

            Nodo<E> actual = estado.nodo;

            // Ignorar datos desactualizados
            if (estado.distancia > distancias.get(actual)) {
                continue;
            }

            if (actual.equals(destino)) {
                break;
            }

            // Revisar vecinos
            for (Paso<E> paso :
                    grafo.adyacencias.get(actual)) {

                Nodo<E> vecino = paso.destino;

                double nuevaDistancia =
                        distancias.get(actual) + paso.peso;

                if (nuevaDistancia < distancias.get(vecino)) {

                    distancias.put(vecino, nuevaDistancia);

                    anteriores.put(vecino, actual);

                    cola.add(
                        new Estado<>(vecino, nuevaDistancia)
                    );
                }
            }
        }

        // Verificar si existe camino
        if (distancias.get(destino) ==
                Double.POSITIVE_INFINITY) {

            System.out.println(
                "No existe un camino entre "
                + inicio.getDato() + " y "
                + destino.getDato());

            return;
        }

        // Reconstruir camino
        List<Nodo<E>> camino = new ArrayList<>();

        Nodo<E> actual = destino;

        while (actual != null) {

            camino.add(actual);

            actual = anteriores.get(actual);
        }

        Collections.reverse(camino);

        // Mostrar resultados
        System.out.println("Camino de menor costo:");

        for (int i = 0; i < camino.size(); i++) {

            System.out.print(camino.get(i).getDato());

            if (i < camino.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println();

        System.out.println(
            "Costo total: $" + distancias.get(destino));
    }

    // =====================================
    // MOSTRAR RECORRIDOS
    // =====================================

    public static <E> void mostrarRecorrido(
            String algoritmo,
            List<Nodo<E>> recorrido) {

        System.out.print(algoritmo + ": ");

        for (Nodo<E> nodo : recorrido) {

            System.out.print(nodo.getDato() + " ");
        }

        System.out.println();
    }
}
