package algoritmos.busca_largura;

import estruturas.Aresta;
import estruturas.Grafo;
import estruturas.Vertice;

import java.util.*;

public class BuscaEmLargura {
    private Set<Vertice> visitado;
    private Map<Vertice, Vertice> anterior;

    public BuscaEmLargura(Grafo grafo, Vertice origem) {
        visitado = new HashSet<>();
        anterior = new HashMap<>();

        realizarBusca(grafo, origem);
    }

    private void realizarBusca(Grafo grafo, Vertice origem) {
        Queue<Vertice> fila = new LinkedList<>();
        fila.add(origem);
        visitado.add(origem);
        anterior.put(origem, null);

        while (!fila.isEmpty()) {
            Vertice atual = fila.poll();

            for (Aresta aresta : grafo.getAdjacencias(atual)) {
                Vertice vizinho;

                if (aresta.getOrigem().equals(atual)) {
                    vizinho = aresta.getDestino();
                } else {
                    vizinho = aresta.getOrigem();
                }

                if (!visitado.contains(vizinho)) {
                    visitado.add(vizinho);
                    anterior.put(vizinho, atual);
                    fila.add(vizinho);
                }
            }
        }
    }

    public List<Vertice> caminhoAte(Vertice destino) {
        List<Vertice> caminho = new ArrayList<>();

        if (!visitado.contains(destino)) {
            return caminho;
        }

        Vertice atual = destino;
        while (atual != null) {
            caminho.add(0, atual);
            atual = anterior.get(atual);
        }

        return caminho;
    }
}
