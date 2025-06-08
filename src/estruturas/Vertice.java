package estruturas;

public class Vertice {
    private String rotulo;
    public Vertice(String rotulo) {
        this.rotulo = rotulo;
    }
    public String getRotulo() {
        return rotulo;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vertice) {
            Vertice v = (Vertice) obj;
            return rotulo.equals(v.getRotulo());
        }
        return false;
    }
    @Override
    public int hashCode() {
        return rotulo.hashCode();
    }
}
