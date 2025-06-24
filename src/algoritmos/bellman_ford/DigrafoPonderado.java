package algoritmos.bellman_ford;

import java.util.ArrayList;

public class DigrafoPonderado {
    public class Aresta{
        int destino;
        int peso;

        Aresta(int destino, int peso){
            this.destino = destino;
            this.peso = peso;
        }

        public int getPeso(){
            return peso;
        }

        public int getDestino(){
            return destino;
        }
    }

    private int numVertices;
    private ArrayList<Aresta>[] listaAdjacencias;

    public DigrafoPonderado(int numVertices){
        this.numVertices = numVertices;
        listaAdjacencias = new ArrayList[numVertices];

        for (int i = 0; i < numVertices; i++) {
            listaAdjacencias[i] = new ArrayList<>();
        }
    }

    public void adicionarAresta(int origem, int destino, int peso){
        if (origem < 0 || origem >= numVertices || destino < 0 || destino >= numVertices) {
            throw new IllegalArgumentException("Invalid vertex index");
        }
        listaAdjacencias[origem].add(new Aresta(destino, peso));
    }

    public ArrayList<Aresta> getAdjacencias(int vertice){
        if (vertice < 0 || vertice >= numVertices) {
            throw new IllegalArgumentException("Invalid vertex index");
        }
        return listaAdjacencias[vertice];
    }

    public int getNumeroVertices(){
        return numVertices;
    }
}
