package mostrarGrauVertices;
import estruturas.Aresta;
import estruturas.Grafo;
import estruturas.Vertice;

public class MostrarGrausVertices {
    public static String calcularGrausVertices(Grafo grafo) {
        StringBuilder mensagem = new StringBuilder();
        for (Vertice vertice : grafo.getListaVertices()) {
            String nome = vertice.getRotulo();
            if (grafo.isDirecionado()) {
                int grauEntrada = 0;
                int grauSaida = 0;
                for (Aresta aresta : grafo.getListaArestas()) {
                    if (aresta.getDestino().equals(vertice)) grauEntrada++;
                    if (aresta.getOrigem().equals(vertice)) grauSaida++;
                }
                mensagem.append(nome)
                        .append(" grau entrada (").append(grauEntrada).append(")")
                        .append(" grau saida (").append(grauSaida).append(")\n");
            } else {
                int grau = grafo.getAdjacencias(vertice).size();
                mensagem.append(nome)
                        .append(" grau (").append(grau).append(")\n");
            }
        }
        return mensagem.toString();

    }
}
