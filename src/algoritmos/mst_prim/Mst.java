package algoritmos.mst_prim;


// Programa em Java para a Árvore Geradora Mínima (MST) usando o algoritmo de Prim
// O programa utiliza a representação do grafo por lista de adjacência

import estruturas.Aresta;
import estruturas.Grafo;
import estruturas.Vertice;

import java.util.*;

public class Mst {

    public static void executarPrim(Grafo grafo, Vertice origem) {
        // Verifica se o grafo possui ciclos
        if (grafo.contemCiclos()) {
            System.out.println("Grafo possui ciclos.");
        } else {
            System.out.println("Grafo sem ciclos.");
        }
        Map<Vertice, Integer> chave = new HashMap<>(); //Guarda a Aresta com menor peso
        Map<Vertice, Vertice> pai = new HashMap<>(); //Guarda o vertice pai
        Set<Vertice> naMST = new HashSet<>(); //Guarda os vertices da MST

        // Inicializa as chaves como infinito
        for (Vertice v : grafo.getListaVertices()) {
            chave.put(v, Integer.MAX_VALUE);
            pai.put(v, null);
        }

        chave.put(origem, 0); // Aresta de origem para origem tem peso 0


        PriorityQueue<Vertice> fila = new PriorityQueue<>(Comparator.comparingInt(chave::get)); //Fila de prioridade baseada nas chaves
        fila.addAll(grafo.getListaVertices()); //Adiciona todos os vertices na fila

        while (!fila.isEmpty()) { // Enquanto a fila nao estiver vazia
            Vertice u = fila.poll(); // Remove o vertice com menor peso
            naMST.add(u); // Adiciona o vertice a MST

            for (Aresta aresta : grafo.getAdjacencias(u)) { // Para cada aresta adjacente ao vertice u
                Vertice v = aresta.getDestino(); // Obtem o vertice adjacente
                int peso = aresta.getPeso(); // Obtem o peso da aresta

                if (!naMST.contains(v) && peso < chave.get(v)) { // Se o vertice nao estiver na MST e o peso da aresta for menor que a chave do vertice
                    fila.remove(v); // Atualiza a prioridade
                    chave.put(v, peso); // Atualiza a chave
                    pai.put(v, u); // Atualiza o pai
                    fila.add(v); // Adiciona o vertice na fila
                }
            }
        }

        // Imprime o resultado da Árvore Geradora Mínima
        System.out.println("Aresta \tPeso");
        for (Vertice v : grafo.getListaVertices()) {// Para cada vertice
            Vertice paiVertice = pai.get(v); // Obtem o vertice pai
            if (paiVertice != null) { // Se o vertice pai nao for nulo
                int peso = 0; // Peso da aresta
                for (Aresta a : grafo.getAdjacencias(paiVertice)) { // Para cada aresta adjacente ao vertice pai
                    if (a.getDestino().equals(v)) { // Se o vertice adjacente for o vertice v
                        peso = a.getPeso(); // Obtem o peso da aresta
                        break; // Sai do loop
                    }
                }
                System.out.println(paiVertice.getRotulo() + " - " + v.getRotulo() + "\t" + peso);
            }
        }
    }
}

