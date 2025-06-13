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

        for(int i = 0; i<anteriores.length; i++) anteriores[i] = -1;

        buscarEmProfundidadeRecursivo(this.origem);
    }

    private void buscarEmProfundidadeRecursivo(int verticeIndex) {
        percorridos[verticeIndex] = true;

        Vertice vertice = vertices.get(verticeIndex);

        List<Aresta> arestaDoVertice = grafo.getAdjacencias(vertice);

        for (Aresta aresta : arestaDoVertice) {
            Vertice adjacente;

            if (aresta.getOrigem().equals(vertice)) {
                adjacente = aresta.getDestino();
            } else {
                adjacente = aresta.getOrigem();
            }

            int indiceAdjacente = vertices.indexOf(adjacente);

            if (indiceAdjacente != -1 && !percorridos[indiceAdjacente]) {
                anteriores[indiceAdjacente] = verticeIndex;
                buscarEmProfundidadeRecursivo(indiceAdjacente);
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

        for(int i = 0; i < anteriores.length; i++) {
            anteriores[i] = -1;
        }

        buscarEmProfundidadeRecursivo(this.origem);
    }

    public boolean[] getPercorridos() {
        return percorridos;
    }

    public int[] getAnteriores() {
        return anteriores;
    }

    public int getOrigem() {
        return origem;
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

    public boolean foiVisitado(String rotuloVertice) {
        Vertice v = grafo.getVertice(rotuloVertice);
        if (v != null) {
            int indice = vertices.indexOf(v);
            return indice != -1 && percorridos[indice];
        }
        return false;
    }

    public String getRelatorioTraversia() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("DFS a partir do vértice: ").append(vertices.get(origem).getRotulo()).append("\n");
        relatorio.append("Vértices visitados: ");

        for (int i = 0; i < percorridos.length; i++) {
            if (percorridos[i]) {
                relatorio.append(vertices.get(i).getRotulo()).append(" ");
            }
        }

        return relatorio.toString();
    }
}
