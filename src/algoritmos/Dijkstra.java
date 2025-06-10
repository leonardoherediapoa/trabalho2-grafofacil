package algoritmos;

import estruturas.*;
import java.util.*;

/**
 * Classe que implementa o algoritmo de Dijkstra para encontrar o caminho mais
 * curto
 * entre dois vértices em um grafo ponderado.
 */

public class Dijkstra {
    public static Map<Vertice, Integer> calcularDistancias(Grafo grafo, Vertice origem) {
        Map<Vertice, Integer> distancias = new HashMap<>();
        Map<Vertice, Vertice> anteriores = new HashMap<>();
        PriorityQueue<Vertice> fila = new PriorityQueue<>(Comparator.comparingInt(distancias::get));

        for (Vertice v : grafo.getListaAdjacencia().keySet()) {
            distancias.put(v, Integer.MAX_VALUE);
            anteriores.put(v, null);
        }

        distancias.put(origem, 0);
        fila.add(origem);

        while (!fila.isEmpty()) {
            Vertice atual = fila.poll();

            List<Aresta> arestas = grafo.getListaAdjacencia().get(atual);
            if (arestas != null) {
                for (Aresta aresta : arestas) {
                    Vertice vizinho = aresta.getDestino(); // ou aresta.getOutroExtremo(atual)
                    int novaDistancia = distancias.get(atual) + aresta.getPeso();

                if (novaDistancia < distancias.get(vizinho)) {
                    distancias.put(vizinho, novaDistancia);
                    fila.add(vizinho);
                }
            }
        }
    }

    return distancias; // Ou retornar também os "anteriores" se quiser os caminhos
}
}
