package algoritmos;
import estruturas.Aresta;
import estruturas.Grafo;
import estruturas.Vertice;
import utils.LogManager;
import visualizacao.PainelGrafo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MarcarVertices {
    private PainelGrafo painel;
    public MarcarVertices(PainelGrafo painel) {
        this.painel = painel;
    }
    public void marcarVertices(Grafo grafo, List<Vertice> vertices) throws InterruptedException {
        if(grafo == null || vertices == null || vertices.isEmpty()) return;
        List<Aresta> lista = grafo.getListaArestas();
        List<Vertice> lstVertices = grafo.getListaVertices();
        for (Vertice v : lstVertices) {
            List<Vertice> lstTeste = new ArrayList<>();
            lstTeste.add(v);
            LogManager.updateLog("Marcando vértice: " + v.getRotulo());
        }
        painel.desenharVertices(lstVertices, Color.RED, 2000);

    }


}
