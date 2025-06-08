package visualizacao;

import java.awt.*;

public class Circulo {
    private int x;
    private int y;
    private int raio;
    private Color cor;
    private String rotulo;
    public Circulo(int x, int y, int raio){
        this.x = x;
        this.y = y;
        this.raio = raio;
    }
    public Circulo(int x, int y, int raio, String rotulo){
        this(x, y, raio);
        this.cor = cor;
        this.rotulo = rotulo;
    }
    public void desenhar(Graphics2D g) {
        g.setColor(cor);
        Font fonte = new Font("Arial", Font.BOLD, 12);
        g.setFont(fonte);

        g.setColor(cor);
        g.drawOval(x - raio, y - raio, 2 * raio, 2 * raio);

        FontMetrics fontePosicao = g.getFontMetrics();
        int fonteX = x - 3;
        int fonteY = y + 5;
        g.setColor(Color.BLACK);
        g.drawString(rotulo, fonteX, fonteY);

    }
    public void mover(int newX, int newY) {
        x = newX;
        y = newY;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getRaio() {
        return raio;
    }
    public Color getCor() {
        return cor;
    }
    public String getRotulo() {
        return rotulo;
    }
}
