package estruturas;

import java.util.*;

public class Grafo {
    private LinkedHashMap<Vertice, List<Aresta>> listaAdjacencia;
    private boolean direcionado = false;

    public Grafo() {
        listaAdjacencia = new LinkedHashMap<>();
    }
    public Grafo(boolean direcionado) {
        this.direcionado = direcionado;
        listaAdjacencia = new LinkedHashMap<>();
    }
    public boolean isDirecionado() {
        return direcionado;
    }

    public void adicionarVertice(Vertice v) {
        listaAdjacencia.put(v, new ArrayList<>());
        System.out.println("Vertice " + v.getRotulo() + " adicionado.");
    }
    public void adicionarAresta(Aresta e) {
        Vertice origem = e.getOrigem();
        Vertice destino = e.getDestino();
        List<Aresta> listaOrigem = listaAdjacencia.get(origem);
        listaOrigem.add(e);
        if(!e.isDirecionada()) {
            List<Aresta> listaDestino = listaAdjacencia.get(destino);
            listaDestino.add(e);
        }
    }
    public int getNumeroVertices(){
        return listaAdjacencia.size();
    }
    public String toDot() {
        String resultado = "graph G { " + System.lineSeparator();
        for (Vertice v:getListaVertices()) {
            resultado = resultado + "\t" + v.getRotulo() + ";" + System.lineSeparator();
        }
        for(Aresta e:getListaArestas()) {
            resultado += "\t" + e.getOrigem().getRotulo() + "--" + e.getDestino().getRotulo() + ";" + System.lineSeparator();
        }
        resultado += "}";
        return resultado;
    }
    public Vertice getVertice(String rotulo) {
       Set<Vertice> vertices = listaAdjacencia.keySet();
        for (Vertice v:vertices) if(v.getRotulo().equals(rotulo)) return v;
        return null;
    }
    public List<Vertice> getListaVertices() {
        return new ArrayList<>(listaAdjacencia.keySet());

    }
    public List<Aresta> getListaArestas() {
        List<Aresta> lista = new ArrayList<>();
        for (Map.Entry<Vertice, List<Aresta>> item: listaAdjacencia.entrySet()) {
            for (Aresta e:item.getValue()) {
                if(!lista.contains(e)) lista.add(e);
            }
        }
        return lista;
    }

    public List<Aresta> getAdjacencias(Vertice v) {
        return listaAdjacencia.getOrDefault(v, new ArrayList<>());
    }

    public void limpar() {
        listaAdjacencia = new LinkedHashMap<>();
    }
    public void atualizarGrafo(String dadosGrafo) {
        this.limpar();
        System.out.println("Atualizar Grafo");
        dadosGrafo = dadosGrafo.replace("\r\n", ";");
        dadosGrafo = dadosGrafo.replace("\n", ";");
        String[] linhas = dadosGrafo.split(";");
        for (String linha:linhas) {
            String[] colunas = linha.trim().split("\\s*(->|--)\\s*|\\s+");
            if(colunas.length==1) {
                String rotuloVertice = colunas[0].trim();
                adicionarVertice(new Vertice(rotuloVertice));
            }
            else if (colunas.length>=2) {
                String rotuloOrigem = colunas[0].trim();
                String rotuloDestino = colunas[1].trim();
                Vertice vOrigem = getVertice(rotuloOrigem);
                int peso = 1;
                if(colunas.length==3) peso = Integer.parseInt(colunas[2].trim());
                this.direcionado = linha.contains("->");
                if(vOrigem==null) {
                    vOrigem = new Vertice(rotuloOrigem);
                    adicionarVertice(vOrigem);
                }
                Vertice vDestino = getVertice(rotuloDestino);
                if(vDestino==null) {
                    vDestino = new Vertice(rotuloDestino);
                    adicionarVertice(vDestino);
                }
                Aresta e = new Aresta(vOrigem, vDestino, this.direcionado, peso);
                adicionarAresta(e);
            }
        }
        System.out.println (contemCiclos());
    }

    public boolean contemCiclos() {
    Set<Vertice> visitados = new HashSet<>();

    for (Vertice v : this.getListaVertices()) {
        if (!visitados.contains(v)) {
            Stack<Vertice> pilha = new Stack<>();
            Map<Vertice, Vertice> pai = new HashMap<>();

            pilha.push(v);
            pai.put(v, null);

            while (!pilha.isEmpty()) {
                Vertice atual = pilha.pop();
                visitados.add(atual);

                List<Vertice> vizinhos = new ArrayList<>();
                for (Aresta a : this.getListaArestas()) {
                    if (a.getOrigem().equals(atual)) {
                        vizinhos.add(a.getDestino());
                    } else if (a.getDestino().equals(atual)) {
                        vizinhos.add(a.getOrigem());
                    }
                }

                for (Vertice vizinho : vizinhos) {
                    if (!visitados.contains(vizinho)) {
                        pilha.push(vizinho);
                        pai.put(vizinho, atual);
                    } else if (!vizinho.equals(pai.get(atual))) {
                        // Vizinho já visitado e não é o pai, então há ciclo
                        return true;
                    }
                }
            }
        }
    }
    return false;
}

}
