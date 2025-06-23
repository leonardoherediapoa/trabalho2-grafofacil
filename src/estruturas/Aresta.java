package estruturas;

public class Aresta {
    private Vertice origem;
    private Vertice destino;
    private int peso = 1;
    private boolean direcionada = false;
    public Aresta(Vertice origem, Vertice destino, boolean direcionada, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.direcionada = direcionada;
        this.peso = peso;
    }

    public Vertice getOrigem() {
        return origem;
    }

    public Vertice getDestino() {
        return destino;
    }
    public int getPeso() {
        return peso;
    }
    public boolean isDirecionada() {
        return direcionada;
    }
    @Override
    public String toString() {
        return origem.getRotulo() + " -- " + destino.getRotulo() + " (peso: " + peso + ")";
    }
}
