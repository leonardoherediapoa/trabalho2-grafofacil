package visualizacao;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GeradorGrafoAleatorio {
    public GeradorGrafoAleatorio() {

    }
    public String gerarGrafo(int quantidadeVertices, boolean direcionado) {
        char[] listaVertices = new char[quantidadeVertices];
        for (int i = 0; i < listaVertices.length; i++) {
            listaVertices[i] = (char) ('A' + i);
        }

        String direcao = "--";
        if(direcionado) direcao = "->";

        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        Set<String> arestasGeradas = new HashSet<>();

        for (int i = 0; i < quantidadeVertices; i++) {
            char v = listaVertices[random.nextInt(listaVertices.length)];
            char w = listaVertices[i];
            while (arestasGeradas.contains(String.valueOf(v).concat(String.valueOf(w))) ||
                    arestasGeradas.contains(String.valueOf(w).concat(String.valueOf(v))) ||
                    v==w) {
                v = listaVertices[random.nextInt(listaVertices.length)];
                w = listaVertices[random.nextInt(listaVertices.length)];
            }
            int peso = random.nextInt(10) + 1;
            sb.append(v).append(direcao).append(w).append(" ").append(peso).append(System.lineSeparator());
            arestasGeradas.add(String.valueOf(v).concat(String.valueOf(w)));
            arestasGeradas.add(String.valueOf(w).concat(String.valueOf(v)));
        }
        //gerar mais arestas aleatorias
        for (int i = 0; i < quantidadeVertices/2; i++) {
            char v = listaVertices[random.nextInt(listaVertices.length)];
            char w = listaVertices[random.nextInt(listaVertices.length)];
            while (arestasGeradas.contains(String.valueOf(v).concat(String.valueOf(w))) ||
                    arestasGeradas.contains(String.valueOf(w).concat(String.valueOf(v))) ||
                    v==w) {
                v = listaVertices[random.nextInt(listaVertices.length)];
                w = listaVertices[random.nextInt(listaVertices.length)];
            }
            int peso = random.nextInt(10) + 9;
            sb.append(v).append(direcao).append(w).append(" ").append(peso).append(System.lineSeparator());
            arestasGeradas.add(String.valueOf(v).concat(String.valueOf(w)));
            arestasGeradas.add(String.valueOf(w).concat(String.valueOf(v)));
        }

        return sb.toString();
    }
}
