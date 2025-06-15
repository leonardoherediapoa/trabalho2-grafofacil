package estruturas;

public class Vertice {
    private String rotulo;
    private int x = -1;
    private int y = -1;

    public Vertice(String rotulo) {
        this.rotulo = rotulo;
    }

    public Vertice(String rotulo, int x, int y){
        this.rotulo = rotulo;
        this.x = x;
        this.y = y;
    }

    public String getRotulo() {
        return rotulo;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vertice v) {
            return rotulo.equals(v.getRotulo());
        }
        return false;
    }
    @Override
    public int hashCode() {
        return rotulo.hashCode();
    }

    public int getX() {
    return x;
}

public int getY() {
    return y;
}

public void setX(int x) {
    this.x = x;
}

public void setY(int y) {
    this.y = y;
}
}
