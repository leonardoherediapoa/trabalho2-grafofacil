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
}