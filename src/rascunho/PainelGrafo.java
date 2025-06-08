package rascunho;

import visualizacao.Circulo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class PainelGrafo extends JPanel {
    public ArrayList<Circulo>[] circulos;
    private Circulo circuloAtivo;
    private int numeroCirculos;
    public PainelGrafo(int numeroCirculos) {
        this.numeroCirculos = numeroCirculos;
        circulos = new ArrayList[numeroCirculos];
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (int i = 0; i < circulos.length; i++) {
                    Circulo c = circulos[i].get(0);
                    int dx = e.getX() - c.getX();
                    int dy = e.getY() - c.getY();
                    int distanciaQuadrado = dx * dx + dy * dy;
                    int raioQuadrado = c.getRaio() * c.getRaio();
                    if(distanciaQuadrado<raioQuadrado) {
                        circuloAtivo = c;
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                circuloAtivo = null;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
                                   @Override
                                   public void mouseDragged(MouseEvent e) {
                                       if(circuloAtivo!=null) {
                                           circuloAtivo.mover(e.getX(), e.getY());
                                           repaint();
                                       }
                                   }
                               }
        );

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (int v = 0; v < circulos.length; v++) {
            Circulo c = circulos[v].get(0);
            c.desenhar(g2d);
        }

        for (int v = 0; v < circulos.length; v++) {
            Circulo origem = circulos[v].get(0);
            for (int w = 1; w < circulos[v].size(); w++) {
                Circulo destino = circulos[v].get(w);
                System.out.println(" origem  " + origem.getX() + " " + origem.getY());
                System.out.println(" destino " + destino.getX() + " " + destino.getY());
                g2d.drawLine(origem.getX(), origem.getY(), destino.getX(), destino.getY());
            }
        }
    }
}
