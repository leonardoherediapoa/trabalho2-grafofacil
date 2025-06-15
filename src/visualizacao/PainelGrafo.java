package visualizacao;

import estruturas.Aresta;
import estruturas.Grafo;
import estruturas.Vertice;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class PainelGrafo extends JPanel {

    private Grafo grafo;
    private List<Circulo> listaCirculo;
    private Map<Vertice, Circulo> mapaVerticeCirculo;
    private Circulo circuloAtivo;

    public PainelGrafo(Grafo grafo) {
        this.grafo = grafo;
        listaCirculo = new ArrayList<>();
        mapaVerticeCirculo = new HashMap<>();
        circuloAtivo = null;
    }

    public void desenharGrafo() {
        if (grafo == null) {
            return;
        }
        int x = 100;
        int y = 100;
        int q = 0;
        for (Vertice v : grafo.getListaVertices()) {
            if (!mapaVerticeCirculo.containsKey(v)) {
                int vx = v.getX() >= 0 ? v.getX() : x;
                int vy = v.getY() >= 0 ? v.getY() : y;
                Circulo c = new Circulo(vx, vy, 20, v.getRotulo());
                listaCirculo.add(c);
                mapaVerticeCirculo.put(v, c);
                q++;
                if (q == 4) {
                    y = y + 100;
                    x = 100;
                    q = 0;
                } else {
                    x = x + 100;
                }
            }
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (int i = 0; i < listaCirculo.size(); i++) {
                    Circulo c = listaCirculo.get(i);
                    int dx = e.getX() - c.getX();
                    int dy = e.getY() - c.getY();
                    int distanciaQuadrado = dx * dx + dy * dy;
                    int raioQuadrado = c.getRaio() * c.getRaio();
                    if (distanciaQuadrado < raioQuadrado) {
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
                if (circuloAtivo != null) {
                    circuloAtivo.mover(e.getX(), e.getY());
                    Vertice v = mapaVerticeCirculo.entrySet().stream()
                            .filter(entry -> entry.getValue().equals(circuloAtivo))
                            .map(Map.Entry::getKey)
                            .findFirst()
                            .orElse(null);

                    v.setX(e.getX());
                    v.setY(e.getY());
                    repaint();
                }
            }
        }
        );

    }

    @Override
    public void paintComponent(Graphics g) {
        if (grafo == null) {
            return;
        }
        if (listaCirculo.isEmpty()) {
            return;
        }
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        System.out.println("Total de circulos: " + listaCirculo.size());
        for (Circulo c : listaCirculo) {
            System.out.println("desenhando " + c.getRotulo() + " em " + c.getX() + " " + c.getY());
            c.desenhar(g2d);
        }

        //percorre as arestas do grafo para conectar os vertices
        List<Aresta> listaArestas = grafo.getListaArestas();
        for (Aresta e : listaArestas) {
            Vertice vOrigem = e.getOrigem();
            Vertice vDestino = e.getDestino();
            Circulo cOrigem = mapaVerticeCirculo.get(vOrigem);
            Circulo cDestino = mapaVerticeCirculo.get(vDestino);
            Point pOrigem = getInterseccao(new Point(cOrigem.getX(),
                    cOrigem.getY()),
                    cOrigem.getRaio(),
                    new Point(cDestino.getX(), cDestino.getY()));
            Point pDestino = getInterseccao(new Point(cDestino.getX(),
                    cDestino.getY()),
                    cDestino.getRaio(),
                    new Point(cOrigem.getX(), cOrigem.getY()));

            Linha l = new Linha(pOrigem.x, pOrigem.y, pDestino.x, pDestino.y, String.valueOf(e.getPeso()));
            if (e.isDirecionada()) {
                l.desenharSeta(g2d);
            } else {
                l.desenhar(g2d);
            }
        }
    }

    private Point getInterseccao(Point center, int radius, Point otherCenter) {
        double dx = otherCenter.x - center.x;
        double dy = otherCenter.y - center.y;
        double dist = Math.sqrt(dx * dx + dy * dy);

        double ratio = radius / dist;

        int x = (int) (center.x + ratio * dx);
        int y = (int) (center.y + ratio * dy);

        return new Point(x, y);
    }

    public void limpar() {
        listaCirculo.clear();
        mapaVerticeCirculo.clear();
        circuloAtivo = null;
        grafo.limpar();
        repaint();
    }

    public void setCorVertice(Vertice v, Color cor) {
        Circulo c = mapaVerticeCirculo.get(v);
        if (c != null) {
            c.setCor(cor);
            repaint();
        }
    }

    public void desenharArestas(List<Aresta> arestas, Color cor, int delayMs) {
        if (arestas == null || arestas.isEmpty()) {
            return;
        }

        new Thread(() -> {
            try {
                for (Aresta a : arestas) {
                    Vertice origem = a.getOrigem();
                    Vertice destino = a.getDestino();

                    setCorVertice(origem, cor);
                    setCorVertice(destino, cor);

                    SwingUtilities.invokeLater(this::repaint);

                    Thread.sleep(delayMs);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void desenharVertices(List<Vertice> vertices, Color cor, int delayMs) {
        if (vertices == null || vertices.isEmpty()) {
            return;
        }

        new Thread(() -> {
            try {
                for (Vertice v : vertices) {
                    setCorVertice(v, cor); // muda a cor do vértice
                    SwingUtilities.invokeLater(this::repaint); // força atualização visual
                    Thread.sleep(delayMs); // espera
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void moverCirculo(Vertice v, int newX, int newY) {
        Circulo c = mapaVerticeCirculo.get(v);
        if (c != null) {
            c.mover(newX, newY);
            v.setX(newX);
            v.setY(newY);
            repaint();
        }
    }

}
