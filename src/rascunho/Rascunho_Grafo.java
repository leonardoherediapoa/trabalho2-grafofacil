package rascunho;

import java.util.ArrayList;

public class Rascunho_Grafo {
    protected int numeroVertices;
    protected int numeroArestas;
    protected ArrayList<Integer>[] listaAdjacencia;
    public Rascunho_Grafo(int numeroVertices) {
        this.numeroVertices = numeroVertices;
        listaAdjacencia = new ArrayList[this.numeroVertices];
        for (int v = 0; v < this.numeroVertices; v++) {
            listaAdjacencia[v] = new ArrayList<>();
        }
    }
    public void adicionarAresta(int v, int w) {
        listaAdjacencia[v].add(w);
        listaAdjacencia[w].add(v);
    }
    public ArrayList<Integer>[] getListaAdjacencia() {
        return listaAdjacencia;
    }

    public String toDot() {
        String resultado = "graph G { " + System.lineSeparator();
        for (int i = 0; i < numeroVertices; i++) {
            resultado = resultado + "\t" + i + ";" + System.lineSeparator();
        }
        for (int i = 0; i < numeroVertices; i++) {
            for (int j = 0; j < listaAdjacencia[i].size(); j++) {
                resultado += "\t" + i + "--" + listaAdjacencia[i].get(j) + ";" + System.lineSeparator();
            }
        }
        resultado += "}";
        return resultado;
    }
    public int getNumeroVertices() {
        return this.numeroVertices;
    }
}
