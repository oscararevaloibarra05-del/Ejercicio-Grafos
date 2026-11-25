
public class MainClass {

    public static void main(String[] args) {

        // =====================================
        // CREACION DE NODOS
        // =====================================

        Nodo<String> ana = new Nodo<>(0, "Ana");
        Nodo<String> luis = new Nodo<>(1, "Luis");
        Nodo<String> marta = new Nodo<>(2, "Marta");

        Nodo<String> cali = new Nodo<>(3, "Cali");
        Nodo<String> bogota = new Nodo<>(4, "Bogota");
        Nodo<String> medellin = new Nodo<>(5, "Medellin");
        Nodo<String> pereira = new Nodo<>(6, "Pereira");


        // =====================================
        // CASO 1: GRAFO NO DIRIGIDO
        // RED SOCIAL
        // =====================================

        System.out.println("\n==================================");
        System.out.println("CASO 1: RED SOCIAL");
        System.out.println("GRAFO NO DIRIGIDO SIN ETIQUETAS");
        System.out.println("==================================");

        // LISTAS DE ADYACENCIA

        Grafo_LA<String> socialLA =
                new Grafo_LA<>(false);

        socialLA.agregarNodo(ana);
        socialLA.agregarNodo(luis);
        socialLA.agregarNodo(marta);

        socialLA.agregarArista(ana, luis);
        socialLA.agregarArista(ana, marta);
        socialLA.agregarArista(luis, marta);

        System.out.println("\nLISTAS DE ADYACENCIA:");

        socialLA.mostrarGrafo();

        // MATRIZ DE ADYACENCIA

        Grafo_MA<String> socialMA =
                new Grafo_MA<>(false);

        socialMA.agregarNodo(ana);
        socialMA.agregarNodo(luis);
        socialMA.agregarNodo(marta);

        socialMA.agregarArista(ana, luis);
        socialMA.agregarArista(ana, marta);
        socialMA.agregarArista(luis, marta);

        System.out.println("\nMATRIZ DE ADYACENCIA:");

        socialMA.mostrarGrafo();


        // =====================================
        // DFS Y BFS - RED SOCIAL
        // =====================================

        AlgoritmosGrafos.Vista<String> vistaSocialLA =
                AlgoritmosGrafos.desdeLista(socialLA);

        AlgoritmosGrafos.Vista<String> vistaSocialMA =
                AlgoritmosGrafos.desdeMatriz(socialMA);

        System.out.println("\nRECORRIDOS DE LA RED SOCIAL");

        System.out.println("\nLISTAS:");

        AlgoritmosGrafos.mostrarRecorrido(
                "DFS",
                AlgoritmosGrafos.DFS(vistaSocialLA, ana)
        );

        AlgoritmosGrafos.mostrarRecorrido(
                "BFS",
                AlgoritmosGrafos.BFS(vistaSocialLA, ana)
        );

        System.out.println("\nMATRIZ:");

        AlgoritmosGrafos.mostrarRecorrido(
                "DFS",
                AlgoritmosGrafos.DFS(vistaSocialMA, ana)
        );

        AlgoritmosGrafos.mostrarRecorrido(
                "BFS",
                AlgoritmosGrafos.BFS(vistaSocialMA, ana)
        );


        // =====================================
        // CASO 2: GRAFO DIRIGIDO
        // CONEXIONES ENTRE CIUDADES
        // =====================================

        System.out.println("\n==================================");
        System.out.println("CASO 2: CONEXIONES ENTRE CIUDADES");
        System.out.println("GRAFO DIRIGIDO SIN ETIQUETAS");
        System.out.println("==================================");

        // LISTAS DE ADYACENCIA

        Grafo_LA<String> ciudadesLA =
                new Grafo_LA<>(true);

        ciudadesLA.agregarNodo(cali);
        ciudadesLA.agregarNodo(bogota);
        ciudadesLA.agregarNodo(medellin);
        ciudadesLA.agregarNodo(pereira);

        ciudadesLA.agregarArista(cali, bogota);
        ciudadesLA.agregarArista(bogota, medellin);
        ciudadesLA.agregarArista(medellin, cali);
        ciudadesLA.agregarArista(cali, pereira);

        System.out.println("\nLISTAS DE ADYACENCIA:");

        ciudadesLA.mostrarGrafo();

        // MATRIZ DE ADYACENCIA

        Grafo_MA<String> ciudadesMA =
                new Grafo_MA<>(true);

        ciudadesMA.agregarNodo(cali);
        ciudadesMA.agregarNodo(bogota);
        ciudadesMA.agregarNodo(medellin);
        ciudadesMA.agregarNodo(pereira);

        ciudadesMA.agregarArista(cali, bogota);
        ciudadesMA.agregarArista(bogota, medellin);
        ciudadesMA.agregarArista(medellin, cali);
        ciudadesMA.agregarArista(cali, pereira);

        System.out.println("\nMATRIZ DE ADYACENCIA:");

        ciudadesMA.mostrarGrafo();


        // =====================================
        // DFS Y BFS - CIUDADES
        // =====================================

        AlgoritmosGrafos.Vista<String> vistaCiudadesLA =
                AlgoritmosGrafos.desdeLista(ciudadesLA);

        AlgoritmosGrafos.Vista<String> vistaCiudadesMA =
                AlgoritmosGrafos.desdeMatriz(ciudadesMA);

        System.out.println("\nRECORRIDOS DE LAS CIUDADES");

        System.out.println("\nLISTAS:");

        AlgoritmosGrafos.mostrarRecorrido(
                "DFS",
                AlgoritmosGrafos.DFS(vistaCiudadesLA, cali)
        );

        AlgoritmosGrafos.mostrarRecorrido(
                "BFS",
                AlgoritmosGrafos.BFS(vistaCiudadesLA, cali)
        );

        System.out.println("\nMATRIZ:");

        AlgoritmosGrafos.mostrarRecorrido(
                "DFS",
                AlgoritmosGrafos.DFS(vistaCiudadesMA, cali)
        );

        AlgoritmosGrafos.mostrarRecorrido(
                "BFS",
                AlgoritmosGrafos.BFS(vistaCiudadesMA, cali)
        );


        // =====================================
        // CASO 3: GRAFO DIRIGIDO CON ETIQUETAS
        // RUTAS DE ENVIO Y COSTOS
        // =====================================

        System.out.println("\n==================================");
        System.out.println("CASO 3: RUTAS DE ENVIO");
        System.out.println("GRAFO DIRIGIDO CON ETIQUETAS");
        System.out.println("==================================");

        // LISTAS DE ADYACENCIA

        Grafo_LA<String> enviosLA =
                new Grafo_LA<>(true);

        enviosLA.agregarNodo(cali);
        enviosLA.agregarNodo(bogota);
        enviosLA.agregarNodo(medellin);
        enviosLA.agregarNodo(pereira);

        enviosLA.agregarArista(
                cali, bogota, 15000, "R1");

        enviosLA.agregarArista(
                bogota, medellin, 20000, "R2");

        enviosLA.agregarArista(
                medellin, cali, 12000, "R3");

        enviosLA.agregarArista(
                cali, pereira, 10000, "R4");

        System.out.println("\nLISTAS DE ADYACENCIA:");

        enviosLA.mostrarGrafo();

        // MATRIZ DE ADYACENCIA

        Grafo_MA<String> enviosMA =
                new Grafo_MA<>(true);

        enviosMA.agregarNodo(cali);
        enviosMA.agregarNodo(bogota);
        enviosMA.agregarNodo(medellin);
        enviosMA.agregarNodo(pereira);

        enviosMA.agregarArista(
                cali, bogota, 15000, "R1");

        enviosMA.agregarArista(
                bogota, medellin, 20000, "R2");

        enviosMA.agregarArista(
                medellin, cali, 12000, "R3");

        enviosMA.agregarArista(
                cali, pereira, 10000, "R4");

        System.out.println("\nMATRIZ DE ADYACENCIA:");

        enviosMA.mostrarGrafo();


        // =====================================
        // DFS Y BFS - RUTAS DE ENVIO
        // =====================================

        AlgoritmosGrafos.Vista<String> vistaEnviosLA =
                AlgoritmosGrafos.desdeLista(enviosLA);

        AlgoritmosGrafos.Vista<String> vistaEnviosMA =
                AlgoritmosGrafos.desdeMatriz(enviosMA);

        System.out.println("\nRECORRIDOS DE LAS RUTAS DE ENVIO");

        System.out.println("\nLISTAS:");

        AlgoritmosGrafos.mostrarRecorrido(
                "DFS",
                AlgoritmosGrafos.DFS(vistaEnviosLA, cali)
        );

        AlgoritmosGrafos.mostrarRecorrido(
                "BFS",
                AlgoritmosGrafos.BFS(vistaEnviosLA, cali)
        );

        System.out.println("\nMATRIZ:");

        AlgoritmosGrafos.mostrarRecorrido(
                "DFS",
                AlgoritmosGrafos.DFS(vistaEnviosMA, cali)
        );

        AlgoritmosGrafos.mostrarRecorrido(
                "BFS",
                AlgoritmosGrafos.BFS(vistaEnviosMA, cali)
        );


        // =====================================
        // DIJKSTRA - MENOR COSTO DE ENVIO
        // =====================================

        System.out.println("\n==================================");
        System.out.println("DIJKSTRA: MENOR COSTO DE ENVIO");
        System.out.println("==================================");

        System.out.println("\nLISTAS DE ADYACENCIA:");

        AlgoritmosGrafos.Dijkstra(
                vistaEnviosLA,
                cali,
                medellin
        );

        System.out.println("\nMATRIZ DE ADYACENCIA:");

        AlgoritmosGrafos.Dijkstra(
                vistaEnviosMA,
                cali,
                medellin
        );

        System.out.println("\n==================================");
        System.out.println("FIN DEL PROGRAMA");
        System.out.println("==================================");
    }
}
