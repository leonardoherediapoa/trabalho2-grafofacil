package algoritmos;
import estruturas.Grafo;
import estruturas.Vertice;
import visualizacao.PainelGrafo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MarcarVertices {
    private PainelGrafo painel;
    public MarcarVertices(PainelGrafo painel) {
        this.painel = painel;
    }
    public void marcarVertices(Grafo grafo, List<Vertice> vertices) throws InterruptedException {
        if(grafo == null || vertices == null || vertices.isEmpty()) return;
        for (Vertice v : vertices) {
            if (grafo.getListaVertices().contains(v)) {
                painel.setCorVertice(v, Color.RED);
                Thread.sleep(1000);
                painel.repaint();
            }
        }
        painel.repaint();
    }


}
