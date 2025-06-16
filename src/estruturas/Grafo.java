package estruturas;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

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
            resultado += "\t" + e.getOrigem().getRotulo() + "--" + e.getDestino().getRotulo() + ";" + System.lineSeparator();
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
            String[] colunas = linha.trim().split("\\s*(->|--)\\s*|\\s+");
            if (colunas.length == 1) {
                String rotuloVertice = colunas[0].trim();
                adicionarVertice(new Vertice(rotuloVertice));
            } else if (colunas.length >= 2) {
                String rotuloOrigem = colunas[0].trim();
                String rotuloDestino = colunas[1].trim();
                Vertice vOrigem = getVertice(rotuloOrigem);
                int peso = 1;
                if (colunas.length == 3) {
                    peso = Integer.parseInt(colunas[2].trim());
                }
                this.direcionado = linha.contains("->");
                if (vOrigem == null) {
                    vOrigem = new Vertice(rotuloOrigem);
                    adicionarVertice(vOrigem);
                }
                Vertice vDestino = getVertice(rotuloDestino);
                if (vDestino == null) {
                    vDestino = new Vertice(rotuloDestino);
                    adicionarVertice(vDestino);
                }
                Aresta e = new Aresta(vOrigem, vDestino, this.direcionado, peso);
                adicionarAresta(e);
            }
        }
    }

    private boolean dfsDirecionado(Vertice v, Set<Vertice> visitados, Set<Vertice> emRecursao) {
        visitados.add(v);
        emRecursao.add(v);
        for (Aresta a : getAdjacencias(v)) {
            Vertice vizinho = a.getDestino();
            if (!visitados.contains(vizinho)) {
                if (dfsDirecionado(vizinho, visitados, emRecursao)) {
                    return true;
                }
            } else if (emRecursao.contains(vizinho)) {
                return true;
            }
        }
        emRecursao.remove(v);
        return false;
    }

    private boolean dfsNaoDirecionado(Vertice v, Set<Vertice> visitados, Vertice pai) {
        visitados.add(v);
        for (Aresta a : getAdjacencias(v)) {
            Vertice vizinho = a.getDestino().equals(v) ? a.getOrigem() : a.getDestino();
            if (!visitados.contains(vizinho)) {
                if (dfsNaoDirecionado(vizinho, visitados, v)) {
                    return true;
                }
            } else if (!vizinho.equals(pai)) {
                return true;
            }
        }
        return false;
    }

    public boolean contemCiclos() {
        Set<Vertice> visitados = new HashSet<>();
        Set<Vertice> emRecursao = new HashSet<>();

        for (Vertice v : getListaVertices()) {
            if (!visitados.contains(v)) {
                if (direcionado) {
                    if (dfsDirecionado(v, visitados, emRecursao)) {
                        return true;
                    }
                } else {
                    if (dfsNaoDirecionado(v, visitados, null)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void exportarParaAreaTransferenciaDot() {
        String dot = this.toDot();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable = new StringSelection(dot);
        clipboard.setContents(transferable, null);
        JOptionPane.showMessageDialog(null, "Conteúdo DOT copiado para a área de transferência!");
    }

    public void exportarParaArquivoDot(Grafo grafo) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos DOT", "dot"));

        int escolha = fileChooser.showSaveDialog(null);

        if (escolha == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();

            if (!arquivo.getName().toLowerCase().endsWith(".dot")) {
                arquivo = new File(arquivo.getAbsolutePath() + ".dot");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {

                writer.write(grafo.toDot());

                JOptionPane.showMessageDialog(null, "Arquivo DOT exportado com sucesso!");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao exportar arquivo DOT: " + ex.getMessage());
            }
        }
    }
}
