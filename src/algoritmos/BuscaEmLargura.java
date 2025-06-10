package algoritmos;

import estruturas.Grafo;
import estruturas.Vertice;

import java.util.*;

public class BuscaEmLargura {
    private Map<Vertice, Boolean> visitado;
    private Map<Vertice, Vertice> anterior;

    public BuscaEmLargura(Grafo grafo, Vertice origem) {
        visitado = new HashMap<>();
        anterior = new HashMap<>();

        for (Vertice v : grafo.getListaVertices()) {
            visitado.put(v, false);
            anterior.put(v, null);
        }

        realizarBusca(grafo, origem);
    }

    public List<Vertice> caminhoAte(Vertice destino) {
        List<Vertice> caminho = new ArrayList<>();

        Vertice atual = destino;
        while (atual != null) {
            caminho.add(0, atual); 
            atual = anterior.get(atual);
        }

        return caminho;
    }

    private void realizarBusca(Grafo grafo, Vertice origem) {
        Queue<Vertice> fila = new LinkedList<>();
        fila.add(origem);
        visitado.put(origem, true);

        while (!fila.isEmpty()) {
            Vertice atual = fila.poll(); 


            List<Aresta> arestas = grafo.getAdjacencias(atual);

            for (Aresta aresta : arestas) {
                Vertice vizinho;
                if (aresta.getOrigem().equals(atual)) {
                    vizinho = aresta.getDestino();
                } else {
                    vizinho = aresta.getOrigem();
                }

                if (!visitado.get(vizinho)) {
                    visitado.put(vizinho, true);
                    anterior.put(vizinho, atual);
                    fila.add(vizinho);
                }
            }
        }
    }
}