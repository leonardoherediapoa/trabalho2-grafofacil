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
        if (!e.isDirecionada()) {
            List<Aresta> listaDestino = listaAdjacencia.get(destino);
            listaDestino.add(e);
        }
    }

    public int getNumeroVertices() {
        return listaAdjacencia.size();
    }

    public String toDot() {
        String resultado = "graph G { " + System.lineSeparator();
        for (Vertice v : getListaVertices()) {
            resultado = resultado + "\t" + v.getRotulo() + ";" + System.lineSeparator();
        }
        for (Aresta e : getListaArestas()) {
            resultado += "\t" + e.getOrigem().getRotulo() + "--" + e.getDestino().getRotulo() + ";"
                    + System.lineSeparator();
        }
        resultado += "}";
        return resultado;
    }

    public Vertice getVertice(String rotulo) {
        Set<Vertice> vertices = listaAdjacencia.keySet();
        for (Vertice v : vertices) {
            if (v.getRotulo().equals(rotulo)) {
                return v;
            }
        }
        return null;
    }

    public List<Vertice> getListaVertices() {
        return new ArrayList<>(listaAdjacencia.keySet());

    }

    public List<Aresta> getListaArestas() {
        List<Aresta> lista = new ArrayList<>();
        for (Map.Entry<Vertice, List<Aresta>> item : listaAdjacencia.entrySet()) {
            for (Aresta e : item.getValue()) {
                if (!lista.contains(e)) {
                    lista.add(e);
                }
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
        for (String linha : linhas) {
            linha = linha.trim();
            if (linha.isEmpty()) {
                continue;
            }

            if (linha.contains("->") || linha.contains("--")) {
                boolean isDirecionada = linha.contains("->");
                String[] partes;
                if (isDirecionada) {
                    partes = linha.split("->");
                } else {
                    partes = linha.split("--");
                }

                if (partes.length < 2) {
                    continue;
                }

                String rotuloOrigem = partes[0].trim();
                String[] destinoPeso = partes[1].trim().split("\\s+");
                String rotuloDestino = destinoPeso[0].trim();

                int peso = 1; // peso padrao
                if (destinoPeso.length > 1) {
                    try {
                        peso = Integer.parseInt(destinoPeso[1].trim());
                    } catch (NumberFormatException ex) {
                        System.out.println("Peso inválido para aresta: " + destinoPeso[1].trim());
                        peso = 1; // se o peso não for um número usa o padrão
                    }
                }

                Vertice vOrigem = getVertice(rotuloOrigem);
                if (vOrigem == null) {
                    vOrigem = new Vertice(rotuloOrigem);
                    adicionarVertice(vOrigem);
                }

                Vertice vDestino = getVertice(rotuloDestino);
                if (vDestino == null) {
                    vDestino = new Vertice(rotuloDestino);
                    adicionarVertice(vDestino);
                }

                this.direcionado = isDirecionada;
                Aresta e = new Aresta(vOrigem, vDestino, isDirecionada, peso);
                adicionarAresta(e);
            } else {
                String[] partes = linha.split("\\s+");
                if (partes.length == 1) {
                    adicionarVertice(new Vertice(partes[0]));
                } else if (partes.length >= 3) {
                    try {
                        String rotulo = partes[0];
                        int x = Integer.parseInt(partes[1]);
                        int y = Integer.parseInt(partes[2]);
                        adicionarVertice(new Vertice(rotulo, x, y));
                    } catch (NumberFormatException e) {
                        adicionarVertice(new Vertice(partes[0]));
                    }
                }
            }
        }
    }

    public boolean contemCiclos() {
        // IMPLEMENTAR
        // ISSUE
        return false;
    }
}
