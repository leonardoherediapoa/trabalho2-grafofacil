package algoritmos;

import estruturas.*;

import java.util.ArrayList;

public class BuscaProfundidade {
    private Grafo grafo;
    private int origem;
    private boolean[] percorridos;
    private int[] anteriores;

    public BuscaProfundidade(Grafo g, int origem) {
        this.grafo = g;
        this.origem = origem;
        percorridos = new boolean[g.getNumeroVertices()];
        anteriores = new int[g.getNumeroVertices()];
        for(int i = 0; i<anteriores.length; i++) anteriores[i] = -1;
        buscarEmProfundidadeRecursivo(this.grafo, this.origem);
    }

    private void buscarEmProfundidadeRecursivo(Grafo g, int v) {
        percorridos[v] = true;
        ArrayList<Integer> adjacentesDoV = g.getAdjacencias(v);
        for(Integer w:adjacentesDoV) {
            if(!percorridos[w]) {
                anteriores[w] = v;
                buscarEmProfundidadeRecursivo(g, w);
            }
        }
    }
}
