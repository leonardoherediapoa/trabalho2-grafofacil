package visualizacao;

import java.awt.*;

public class Circulo {
    private int x;
    private int y;
    private int raio;
    private Color cor = Color.LIGHT_GRAY;
    private String rotulo;
    public Circulo(int x, int y, int raio){
        this.x = x;
        this.y = y;
        this.raio = raio;
    }
    public Circulo(int x, int y, int raio, String rotulo){
        this(x, y, raio);
        this.rotulo = rotulo;
    }
    public void desenhar(Graphics2D g) {
        g.setColor(cor);
        g.fillOval(x - raio, y - raio, 2 * raio, 2 * raio);

        g.setColor(Color.BLACK);
        g.drawOval(x - raio, y - raio, 2 * raio, 2 * raio);

        Font fonte = new Font("Arial", Font.BOLD, 12);
        g.setFont(fonte);
        FontMetrics fontePosicao = g.getFontMetrics();
        int fonteX = x - fontePosicao.stringWidth(rotulo) / 2;
        int fonteY = y + fontePosicao.getAscent() / 2;
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

    public void setCor(Color cor) {
        this.cor = cor;
    }
}
