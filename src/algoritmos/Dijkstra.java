package algoritmos;

import java.util.*;
import estruturas.*;

public class Dijkstra {

    private final Grafo grafo;
    private final Vertice origem;

    private final Map<Vertice, Integer> indiceVertice = new HashMap<>();
    private final List<Vertice> verticePorIndice = new ArrayList<>();

    private final boolean[] visitados;
    private final int[] anteriores;
    private final int[] distancia;

    private final ArrayList<Integer> listaAuxiliar = new ArrayList<>();
    private final Queue<Integer> fila = new LinkedList<>();

    public Dijkstra(Grafo grafo, Vertice origem) {
        this.grafo  = grafo;
        this.origem = origem;

        int idx = 0;
        for (Vertice v : grafo.getListaVertices()) {
            indiceVertice.put(v, idx++);
            verticePorIndice.add(v);
        }

        int n = grafo.getNumeroVertices();
        visitados  = new boolean[n];
        anteriores = new int[n];
        distancia  = new int[n];

        Arrays.fill(anteriores, -1);
        Arrays.fill(distancia,  Integer.MAX_VALUE);

        executaDijkstra();
    }

    private void executaDijkstra() {
        int idxOrigem          = indiceVertice.get(origem);
        distancia[idxOrigem]   = 0;
        visitados[idxOrigem]   = true;
        listaAuxiliar.add(idxOrigem);

        while (!listaAuxiliar.isEmpty()) {
            int v = obterVerticeMenorDistancia();

            Vertice verticeAtual = verticePorIndice.get(v);
            for (Aresta e : grafo.getAdjacencias(verticeAtual)) {

                Vertice vizinhoVert =
                        verticeAtual.equals(e.getOrigem())
                        ? e.getDestino()
                        : e.getOrigem();

                int w     = indiceVertice.get(vizinhoVert);
                int peso  = e.getPeso();

                if (!visitados[w] || distancia[w] > distancia[v] + peso) {
                    fila.add(w);
                    visitados[w]  = true;
                    anteriores[w] = v;
                    distancia[w]  = distancia[v] + peso;
                    if (!listaAuxiliar.contains(w)) listaAuxiliar.add(w);
                }
            }
        }
    }

    private int obterVerticeMenorDistancia() {
        if (listaAuxiliar.isEmpty()) return -1;

        int menorVertice   = listaAuxiliar.get(0);
        int menorDistancia = distancia[menorVertice];

        for (Integer v : listaAuxiliar) {
            int distAtual = distancia[v];
            if (distAtual < menorDistancia) {
                menorVertice   = v;
                menorDistancia = distAtual;
            }
        }
        listaAuxiliar.remove(Integer.valueOf(menorVertice));
        return menorVertice;
    }

    public int getDistancia(Vertice v) {
        Integer idx = indiceVertice.get(v);
        return (idx == null) ? Integer.MAX_VALUE : distancia[idx];
    }

    public List<Vertice> getCaminho(Vertice destino) {
        LinkedList<Vertice> caminho = new LinkedList<>();
        Integer idx = indiceVertice.get(destino);
        if (idx == null || distancia[idx] == Integer.MAX_VALUE) return caminho;

        for (int v = idx; v != -1; v = anteriores[v])
            caminho.addFirst(verticePorIndice.get(v));
        return caminho;
    }
}
