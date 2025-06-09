package visualizacao;

import java.awt.*;

public class Linha {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private String rotulo;

    public Linha(int x1, int y1, int x2, int y2, String rotulo){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.rotulo = rotulo;
    }

    public void desenhar(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawLine(x1,y1,x2,y2);
    }

    public void desenharSeta(Graphics2D g) {
        int arrowSize = 10;
        double angle = Math.atan2(y2 - y1, x2 - x1);
        int xArrow1 = (int) (x2 - arrowSize * Math.cos(angle - Math.PI / 6));
        int yArrow1 = (int) (y2 - arrowSize * Math.sin(angle - Math.PI / 6));
        int xArrow2 = (int) (x2 - arrowSize * Math.cos(angle + Math.PI / 6));
        int yArrow2 = (int) (y2 - arrowSize * Math.sin(angle + Math.PI / 6));

        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(new int[]{x2, xArrow1, xArrow2}, new int[]{y2, yArrow1, yArrow2}, 3);

        int midX = (x1 + x2) / 2;
        int midY = (y1 + y2) / 2;

        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.setColor(Color.BLUE);
        g.drawString(rotulo, midX, midY);
    }

}
