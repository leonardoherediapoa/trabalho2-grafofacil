package estruturas;

import java.util.*;

public class Grafo {
    private LinkedHashMap<Vertice, List<Aresta>> listaAdjacencia;
    public Grafo() {
        listaAdjacencia = new LinkedHashMap<>();
    }
    public void adicionarVertice(Vertice v) {
        listaAdjacencia.put(v, new ArrayList<>());
        System.out.println("Vertice " + v.getRotulo() + " adicionado.");
    }
    public void adicionarAresta(Aresta e) {
        Vertice origem = e.getOrigem();
        Vertice destino = e.getDestino();
        List<Aresta> listaOrigem = listaAdjacencia.get(origem);
        List<Aresta> listaDestino = listaAdjacencia.get(destino);
        listaOrigem.add(e);
        listaDestino.add(e);
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
        return listaAdjacencia.keySet().stream().toList();
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
    public static Grafo gerarGrafo(String dadosGrafo) {
        if(dadosGrafo==null || dadosGrafo.isEmpty()) return null;
        Grafo grafo = new Grafo();
        String[] linhas = dadosGrafo.split(System.lineSeparator());
        for (String s:linhas) {
            s = s.trim();
            if(s.isEmpty()) continue;
            String[] colunas = s.split("\\s*--\\s*");
            if(colunas.length==2) {
                String rotuloVerticeV = colunas[0].trim();
                grafo.adicionarVertice(new Vertice(rotuloVerticeV));

                String rotuloVerticeW = colunas[1].trim();
                grafo.adicionarVertice(new Vertice(rotuloVerticeW));

                Vertice v = grafo.getVertice(rotuloVerticeV);
                Vertice w = grafo.getVertice(rotuloVerticeW);
                Aresta e = new Aresta(v, w);
                grafo.adicionarAresta(e);
            }
            /*String[] colunas = s.split(" ");
            if(colunas.length==1) {
                String rotuloVertice = colunas[0].trim();
                grafo.adicionarVertice(new Vertice(rotuloVertice));
            }
            else if (colunas.length==2) {
                String rotuloOrigem = colunas[0].trim();
                String rotuloDestino = colunas[1].trim();
                Vertice vOrigem = grafo.getVertice(rotuloOrigem);
                Vertice vDestino = grafo.getVertice(rotuloDestino);
                Aresta e = new Aresta(vOrigem, vDestino);
                grafo.adicionarAresta(e);
            }
             */
        }
        return grafo;
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
        for (String s:linhas) {
            String[] colunas = s.split("\\s*--\\s*");
            if(colunas.length==1) {
                String rotuloVertice = colunas[0].trim();
                adicionarVertice(new Vertice(rotuloVertice));
            }
            else if (colunas.length==2) {
                String rotuloOrigem = colunas[0].trim();
                String rotuloDestino = colunas[1].trim();
                Vertice vOrigem = getVertice(rotuloOrigem);
                if(vOrigem==null) {
                    vOrigem = new Vertice(rotuloOrigem);
                    adicionarVertice(vOrigem);
                }
                Vertice vDestino = getVertice(rotuloDestino);
                if(vDestino==null) {
                    vDestino = new Vertice(rotuloDestino);
                    adicionarVertice(vDestino);
                }
                Aresta e = new Aresta(vOrigem, vDestino);
                adicionarAresta(e);
            }
        }
    }
}
