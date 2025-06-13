package algoritmos;

import estruturas.*;

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


}
