package algoritmos;
import java.util.*;
import estruturas.*;

public class MST {
    private List<Aresta> arestas;
    private Stack<Aresta> pilha;
    private Grafo grafo;

    public MST(Grafo grafo){
        this.grafo = grafo;
        arestas = grafo.getListaArestas();
        pilha = new Stack<>();
        if(!grafo.isDirecionado()) gerarMST();
    }

    private void gerarMST() {
        while(!arestas.isEmpty()){
            int maior = arestas.stream().
                mapToInt(Aresta::getPeso)
                .max()
                .orElse(Integer.MIN_VALUE);

            List<Aresta> paraRemover = new ArrayList<>();

            for(Aresta a : arestas){
                if (a.getPeso() == maior){
                    pilha.push(a);
                    paraRemover.add(a);
                }
            }

            arestas.removeAll(paraRemover);
        }

        imprimePilha();
    }

    private void imprimePilha(){
        while (!pilha.isEmpty()) {
            System.out.println(pilha.pop());
        }
    }
}
