package estruturas;

import java.util.*;

public class BuscaLargura {
    private Map<Vertice, Boolean> visitado = new HashMap<>();
    private Map<Vertice, Vertice> predecessores = new HashMap<>();

    public List<Vertice> buscar(Grafo grafo, Vertice origem) {
        List<Vertice> ordemVisita = new ArrayList<>();
        Queue<Vertice> fila = new LinkedList<>();

        visitado.clear();
        predecessores.clear();

        fila.add(origem);
        visitado.put(origem, true);

        while (!fila.isEmpty()) {
            Vertice atual = fila.poll();
            ordemVisita.add(atual);

            for (Aresta aresta : grafo.getAdjacencias(atual)) {
                Vertice vizinho = aresta.getDestino();
                if (!grafo.isDirecionado() && vizinho.equals(atual)) {
                    vizinho = aresta.getOrigem(); 
                }

                if (!visitado.containsKey(vizinho)) {
                    fila.add(vizinho);
                    visitado.put(vizinho, true);
                    predecessores.put(vizinho, atual);
                }
            }
        }
        return ordemVisita;
    }

    public Map<Vertice, Vertice> getPredecessores() {
        return predecessores;
    }
}