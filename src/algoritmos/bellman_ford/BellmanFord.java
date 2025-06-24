package algoritmos.bellman_ford;

public class BellmanFord {
    private DigrafoPonderado grafo;
    private int origem;
    private boolean[] visitados;
    private int[] anteriores;
    private int[] distancia;

    public BellmanFord(DigrafoPonderado g, int origem) {
        this.grafo = g;
        this.origem = origem;
        this.visitados = new boolean[grafo.getNumeroVertices()];
        this.anteriores = new int[grafo.getNumeroVertices()];
        this.distancia = new int[grafo.getNumeroVertices()];

        for (int v = 0; v < distancia.length; v++) {
            anteriores[v] = -1;
        }
        percorrer();
    }

    private void percorrer(){
        for (int i = 0; i < distancia.length; i++) {
            distancia[i] = Integer.MAX_VALUE;
        }
        distancia[this.origem] = 0;

        for (int n = 0; n < this.grafo.getNumeroVertices() -1; n++) {
            for (int v = 0; v < this.grafo.getNumeroVertices(); v++) {
                for(DigrafoPonderado.Aresta a : this.grafo.getAdjacencias(v)){
                    int w = a.getDestino();
                    int peso = a.getPeso();
                    if(distancia[v] + peso < distancia[w]){
                        distancia[w] = distancia[v] + peso;
                        anteriores[w] = v;
                    }
                }

            }
        }
    }

    public void imprimirCaminhos() {
        System.out.println("Caminhos mais curtos a partir do vértice " + origem + ":");
        System.out.println("Vértice, Anterior, Distância");
        for (int v = 0; v < anteriores.length ; v++) {
            System.out.println(v + "," + anteriores[v] + "," + distancia[v]);
        }
    }

}
