package algoritmos;

import estruturas.*;
import java.util.*;

public class MST {
    private List<Aresta> arestas;
    private Stack<Aresta> pilha;
    private Grafo grafo;

    public MST(Grafo grafo) {
        this.grafo = grafo;
        arestas = grafo.getListaArestas();
        pilha = new Stack<>();
        if (!grafo.isDirecionado())
            gerarMST();
    }

    public void gerarMST() {
        while (!arestas.isEmpty()) {
            int maior = arestas.stream().mapToInt(Aresta::getPeso)
                    .max()
                    .orElse(Integer.MIN_VALUE);

            List<Aresta> paraRemover = new ArrayList<>();

            for (Aresta a : arestas) {
                if (a.getPeso() == maior) {
                    pilha.push(a);
                    paraRemover.add(a);
                }
            }

            arestas.removeAll(paraRemover);
        }

        verificador(pilha);
    }

     private void verificador(Stack<Aresta> pilha) {
        Grafo grafoMST = new Grafo(false);
        grafoMST.setSilencioso(true);
        List<Aresta> resultado = new ArrayList<>();

        while (!pilha.isEmpty()) {
            Aresta aresta = pilha.pop();
            grafoMST.adicionarAresta(aresta);

            if (!grafoMST.contemCiclos()) {
                resultado.add(aresta);
            } else {
                grafoMST.removerAresta(aresta);
            }

            if (resultado.size() == grafo.getNumeroVertices() - 1) {
                break;
            }
        }

        for (Aresta a : resultado) {
            System.out.println(a);
        }
    }
}
