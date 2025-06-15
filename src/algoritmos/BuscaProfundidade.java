package algoritmos;

import estruturas.*;

import java.util.ArrayList;
import java.util.List;

public class BuscaProfundidade {
    private Grafo grafo;
    private int origem;
    private boolean[] percorridos;
    private int[] anteriores;
    private List<Vertice> vertices;

    public BuscaProfundidade(Grafo g, int origem) {
        this.grafo = g;
        this.origem = origem;
        this.vertices = g.getListaVertices();

        percorridos = new boolean[g.getNumeroVertices()];
        anteriores = new int[g.getNumeroVertices()];

        for (int i = 0; i < anteriores.length; i++) anteriores[i] = -1;

        buscarEmProfundidadeRecursivo(this.origem);
    }

    private void buscarEmProfundidadeRecursivo(int verticeIndex) {
        percorridos[verticeIndex] = true;

        Vertice vertice = vertices.get(verticeIndex);
        List<Aresta> arestasDoVertice = grafo.getAdjacencias(vertice);

        for (Aresta aresta : arestasDoVertice) {
            Vertice adjacente = null;

            if (grafo.isDirecionado()) {
                if (aresta.getOrigem().equals(vertice)) {
                    adjacente = aresta.getDestino();
                } else {
                    continue;
                }
            } else {
                if (aresta.getOrigem().equals(vertice)) {
                    adjacente = aresta.getDestino();
                } else {
                    adjacente = aresta.getOrigem();
                }
            }

            if (adjacente != null) {
                int indiceAdjacente = vertices.indexOf(adjacente);

                if (indiceAdjacente == -1) {
                    continue;
                }

                if (indiceAdjacente >= 0 && indiceAdjacente < vertices.size() && !percorridos[indiceAdjacente]) {
                    anteriores[indiceAdjacente] = verticeIndex;
                    buscarEmProfundidadeRecursivo(indiceAdjacente);
                }
            }
        }
    }

    public void executarDFS(int novaOrigem) {
        if (novaOrigem < 0 || novaOrigem >= vertices.size()) {
            throw new IllegalArgumentException("Invalid origin index: " + novaOrigem);
        }

        this.origem = novaOrigem;

        percorridos = new boolean[grafo.getNumeroVertices()];
        anteriores = new int[grafo.getNumeroVertices()];

        for (int i = 0; i < anteriores.length; i++) {
            anteriores[i] = -1;
        }

        buscarEmProfundidadeRecursivo(this.origem);
    }

    public List<Vertice> getVerticesVisitados() {
        List<Vertice> visitados = new ArrayList<>();
        for (int i = 0; i < percorridos.length; i++) {
            if (percorridos[i]) {
                visitados.add(vertices.get(i));
            }
        }
        return visitados;
    }

    public String getRelatorioTravessia() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("=== RELATÓRIO DFS ===\n");
        relatorio.append("Origem: ").append(vertices.get(origem).getRotulo()).append("\n");
        relatorio.append("Vértices visitados: ");

        int totalVisitados = 0;
        for (int i = 0; i < percorridos.length; i++) {
            if (percorridos[i]) {
                relatorio.append(vertices.get(i).getRotulo()).append(" ");
                totalVisitados++;
            }
        }

        relatorio.append("\nTotal visitados: ").append(totalVisitados).append(" de ").append(vertices.size());

        relatorio.append("\nÁrvore DFS (vértice <- predecessor):\n");
        for (int i = 0; i < anteriores.length; i++) {
            if (percorridos[i]) {
                String predecessor = (anteriores[i] == -1) ? "RAIZ" : vertices.get(anteriores[i]).getRotulo();
                relatorio.append("  ").append(vertices.get(i).getRotulo()).append(" <- ").append(predecessor).append("\n");
            }
        }

        return relatorio.toString();
    }
}
