package rascunho;

import visualizacao.Circulo;

import java.util.ArrayList;

public class PreparaDesenhoGrafo {
    public PainelGrafo painelGrafo;
    public Rascunho_Grafo rascunhoGrafo;

    public PreparaDesenhoGrafo(Rascunho_Grafo rascunhoGrafo) {
        this.rascunhoGrafo = rascunhoGrafo;
        painelGrafo = new PainelGrafo(rascunhoGrafo.getNumeroVertices());
        painelGrafo.circulos = new ArrayList[rascunhoGrafo.getNumeroVertices()];
        for (int i = 0; i < painelGrafo.circulos.length; i++) {
            painelGrafo.circulos[i] = new ArrayList<>();
        }
        desenharVertices();
    }
    private void desenharVertices() {
        ArrayList<Integer>[] listaAdjacencia = this.rascunhoGrafo.getListaAdjacencia();
        int x = 100;
        int y = 100;
        for (int v = 0; v < listaAdjacencia.length; v++) {
            painelGrafo.circulos[v].add(new Circulo(x, y, 20,String.valueOf(v)));
            x = x + 100;
            y = y + 100;
        }

        for (int v = 0; v < listaAdjacencia.length; v++) {
            Circulo origem = painelGrafo.circulos[v].get(0);
            for (int w = 0; w < listaAdjacencia[v].size(); w++) {
                Circulo destino = painelGrafo.circulos[listaAdjacencia[v].get(w)].get(0);
                painelGrafo.circulos[v].add(destino);
            }
        }
    }


}
