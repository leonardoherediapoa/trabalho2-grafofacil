package visualizacao;

import algoritmos.MarcarVertices;
import algoritmos.BuscaProfundidade;
import algoritmos.busca_largura.Dijkstra.Dijkstra;
import estruturas.Aresta;
import estruturas.BuscaLargura;
import estruturas.Grafo;
import estruturas.Vertice;
import utils.LogManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;

import utils.DotConvert;



public class TelaPrincipal extends JFrame {
    public final Grafo grafo;
    public String dadosGrafo;
    public PainelGrafo painelGrafo;
    private JPanel painelDireito;
    private JPanel painelEsquerdo;
    private JPanel painelDados;
    private JPanel painelLog;
    private JPanel barraBotoes;
    private JTextArea textLog;
    public JTextArea textArea;
    private JMenuBar menuBar;
    private JScrollPane scrollPane;

    private BuscaProfundidade dfs;

    public TelaPrincipal() {
        // dadosGrafo = "A" + System.lineSeparator() + "B" + System.lineSeparator() +
        // "C";
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setTitle("ALEST2 - Ferramenta para apoio ao aprendizado de grafos");

        painelEsquerdo = new JPanel(new BorderLayout());
        painelEsquerdo.setPreferredSize(new Dimension(200, 0));

        painelDireito = new JPanel(new BorderLayout());

        painelEsquerdo = new JPanel(new BorderLayout());
        painelEsquerdo.setPreferredSize(new Dimension(300, 0));

        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);

        grafo = new Grafo();
        painelGrafo = new PainelGrafo(grafo);
        painelGrafo.setBackground(Color.WHITE);
        painelGrafo.setVisible(true);

        painelLog = new JPanel(new BorderLayout());
        painelLog.setPreferredSize(new Dimension(800, 100));
        textLog = new JTextArea();
        textLog.setEditable(false);
        textLog.setLineWrap(true);
        textLog.setWrapStyleWord(true);
        painelLog.add(new JScrollPane(textLog), BorderLayout.CENTER);

        painelDireito.add(painelGrafo, BorderLayout.CENTER);
        painelDireito.add(painelLog, BorderLayout.SOUTH);

        criarBotoes();

        criarMenu();
        setJMenuBar(menuBar);
        setVisible(true);

        GeradorGrafoAleatorio grafoAleatorio = new GeradorGrafoAleatorio();
        textArea.setText(grafoAleatorio.gerarGrafo(5, true));
        aplicar();




    }

    private void criarMenu() {
        menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");

        JMenuItem menuItemAbrir = new JMenuItem("Abrir");
        menuItemAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirArquivo();
                System.out.println(textArea.getText());
                SwingUtilities.invokeLater(() -> {
                    painelEsquerdo.invalidate();
                    painelEsquerdo.revalidate();
                    painelEsquerdo.repaint();
                });
            }
        });

        JMenuItem menuItemSalvar = new JMenuItem("Salvar");
        menuItemSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarArquivo();
            }
        });

        JMenuItem menuImportarDot = new JMenuItem("Importar DOT");
        menuImportarDot.addActionListener(e -> importarDot());

        JMenuItem menuItemSair = new JMenuItem("Sair");
        menuItemSair.addActionListener(e -> System.exit(0));
        menuArquivo.add(menuItemAbrir);
        menuArquivo.add(menuImportarDot);
        menuArquivo.add(menuItemSalvar);
        menuArquivo.add(menuItemSair);

        JMenu menuAcao = new JMenu("Acao");
        JMenuItem menuItemGrau = new JMenuItem("Mostrar Grau dos Vertices");
        JMenuItem menuGrafoRandomico = new JMenuItem("Gerar Grafo");
        menuGrafoRandomico.addActionListener(e-> gerarGrafoAleatorio());
        menuAcao.add(menuItemGrau);
        menuAcao.add(menuGrafoRandomico);

        menuBar.add(menuArquivo);
        menuBar.add(menuAcao);
        setJMenuBar(menuBar);
        setVisible(true);


    }
    private void criarBotoes() {
        barraBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAplicar = new JButton("Aplicar");
        JButton botaoAbrir = new JButton("Abrir");

        botaoAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirArquivo();
            }
        });

        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarArquivo();
            }
        });

        barraBotoes.add(botaoAbrir);
        barraBotoes.add(botaoSalvar);

        btnAplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicou no Aplicar da Tela Principal");
                dadosGrafo = textArea.getText();
                painelGrafo.limpar();
                grafo.atualizarGrafo(dadosGrafo);
                painelGrafo.desenharGrafo();
                painelGrafo.setVisible(true);
                painelGrafo.repaint();
            }
        });

        JButton btnMarcar = new JButton("Marcar Vertices");
        btnMarcar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String origem = JOptionPane.showInputDialog("Informe o vértice de origem para DFS:");
                if (origem != null && !origem.trim().isEmpty()) {
                    MarcarVertices marcarVertices = new MarcarVertices(painelGrafo);
                    try {
                        marcarVertices.marcarVertices(grafo, grafo.getListaVertices());
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        barraBotoes.add(btnMarcar);

        JButton btnDFS = new JButton("Profundidade");
        btnDFS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String origem = JOptionPane.showInputDialog("Informe o vértice de origem para DFS:");
                if (origem != null && !origem.trim().isEmpty()) {
                    try {
                        Vertice verticeOrigem = grafo.getVertice(origem.trim());
                        if (verticeOrigem != null) {
                            int indiceOrigem = grafo.getListaVertices().indexOf(verticeOrigem);

                            if (indiceOrigem == -1) {
                                throw new RuntimeException("Inconsistência no estado do grafo");
                            }

                            if (dfs == null) {
                                dfs = new BuscaProfundidade(grafo, indiceOrigem);
                            } else {
                                dfs.executarDFS(indiceOrigem);
                            }

                            LogManager.updateLog(dfs.getRelatorioTravessia());

                            List<Vertice> verticesVisitados = dfs.getVerticesVisitados();

                            for (Vertice v : grafo.getListaVertices()) {
                                painelGrafo.setCorVertice(v, Color.YELLOW);
                            }
                            painelGrafo.repaint();

                            painelGrafo.desenharVertices(verticesVisitados, Color.RED, 1000);

                        } else {
                            JOptionPane.showMessageDialog(TelaPrincipal.this,
                                    "Vértice '" + origem + "' não encontrado no grafo!");
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(TelaPrincipal.this,
                                "Erro ao executar DFS: " + ex.getMessage());
                    }
                }
            }
        });

        barraBotoes.add(btnDFS);

        JButton btnLargura = new JButton("Largura");
        barraBotoes.add(btnLargura);
        btnLargura.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String origem = JOptionPane.showInputDialog("Informe o vértice de origem para BFS:");
            if (origem != null && !origem.trim().isEmpty()) {
                Vertice verticeOrigem = grafo.getVertice(origem.trim());
                if (verticeOrigem != null) {
                    try {
                        BuscaLargura bfs = new BuscaLargura();
                        List<Vertice> visitados = bfs.buscar(grafo, verticeOrigem);
                        StringBuilder log = new StringBuilder("Ordem de visita (BFS): ");
                        for (Vertice v : visitados) {
                            log.append(v.getRotulo()).append(" ");
                        }
                        LogManager.updateLog(log.toString());
                        for (Vertice v : grafo.getListaVertices()) {
                            painelGrafo.setCorVertice(v, Color.YELLOW);
                        }
                        painelGrafo.repaint();

                        painelGrafo.desenharVertices(visitados, Color.RED, 1000);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(TelaPrincipal.this,
                                "Erro ao executar BFS: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(TelaPrincipal.this,
                            "Vértice '" + origem + "' não encontrado no grafo!");
                }
            }
        }
    });

        JButton btnMST = new JButton("MST");
        barraBotoes.add(btnMST);

        JButton btnDijkstra = new JButton("Dijkstra");
        barraBotoes.add(btnDijkstra);

        btnDijkstra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rotOrigem = JOptionPane.showInputDialog(
                        TelaPrincipal.this,
                        "Rótulo do vértice de ORIGEM:",
                        "Dijkstra – Escolha a origem",
                        JOptionPane.QUESTION_MESSAGE);

                if (rotOrigem == null || rotOrigem.trim().isEmpty())
                    return;
                Vertice vOrigem = grafo.getVertice(rotOrigem.trim());
                if (vOrigem == null) {
                    JOptionPane.showMessageDialog(TelaPrincipal.this,
                            "Vértice \"" + rotOrigem + "\" não existe.");
                    return;
                }

                String rotDestino = JOptionPane.showInputDialog(
                        TelaPrincipal.this,
                        "Rótulo do vértice DESTINO (deixe em branco para ver todos):",
                        "Dijkstra – Destino",
                        JOptionPane.QUESTION_MESSAGE);

                // Executa o algoritmo
                Dijkstra d = new Dijkstra(grafo, vOrigem);

                if (rotDestino != null && !rotDestino.trim().isEmpty()) {
                    Vertice vDestino = grafo.getVertice(rotDestino.trim());
                    if (vDestino == null) {
                        JOptionPane.showMessageDialog(TelaPrincipal.this,
                                "Vértice \"" + rotDestino + "\" não existe.");
                        return;
                    }

                    java.util.List<Vertice> caminho = d.getCaminho(vDestino);
                    if (caminho.isEmpty()) {
                        JOptionPane.showMessageDialog(TelaPrincipal.this,
                                "Não há caminho de " + rotOrigem + " até " + rotDestino + ".");
                        return;
                    }

                    // texto no log
                    StringBuilder sb = new StringBuilder();
                    sb.append("Caminho mínimo ").append(rotOrigem).append(" → ")
                            .append(rotDestino).append(" (custo ")
                            .append(d.getDistancia(vDestino)).append("): ");
                    for (Vertice v : caminho)
                        sb.append(v.getRotulo()).append(" ");
                    sb.append('\n');
                    textLog.append(sb.toString());

                    painelGrafo.desenharVertices(caminho, Color.ORANGE, 400);
                }

                else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Dijkstra a partir de ").append(rotOrigem).append(":\n");
                    for (Vertice v : grafo.getListaVertices()) {
                        int dMin = d.getDistancia(v);
                        sb.append("  ").append(v.getRotulo()).append(" = ");
                        sb.append(dMin == Integer.MAX_VALUE ? "∞" : dMin).append('\n');
                    }
                    textLog.append(sb.toString());
                }

                textLog.setCaretPosition(textLog.getDocument().getLength());
            }
        });

        JButton btnFord = new JButton("Bellman-Ford");
        barraBotoes.add(btnFord);

        painelEsquerdo.add(scrollPane, BorderLayout.CENTER);
        painelEsquerdo.add(barraBotoes, BorderLayout.NORTH);
        painelEsquerdo.add(scrollPane, BorderLayout.CENTER);

        painelDados = new JPanel(new BorderLayout());
        painelDados.add(painelEsquerdo, BorderLayout.CENTER);
        painelDados.add(btnAplicar, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(barraBotoes, BorderLayout.NORTH);
        add(painelDireito, BorderLayout.CENTER);
        add(painelDados, BorderLayout.WEST);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");

        JMenuItem menuItemAbrir = new JMenuItem("Abrir");
        menuItemAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirArquivo();
                System.out.println(textArea.getText());
                SwingUtilities.invokeLater(() -> {
                    painelEsquerdo.invalidate();
                    painelEsquerdo.revalidate();
                    painelEsquerdo.repaint();
                });
            }
        });

        JMenuItem menuItemSalvar = new JMenuItem("Salvar");
        menuItemSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarArquivo();
            }
        });

        JMenuItem menuItemSair = new JMenuItem("Sair");
        menuItemSair.addActionListener(e -> System.exit(0));
        menuArquivo.add(menuItemAbrir);
        menuArquivo.add(menuItemSalvar);
        menuArquivo.add(menuItemSair);
        menuBar.add(menuArquivo);
        setJMenuBar(menuBar);
        setVisible(true);

        LogManager.configurar(textLog, grafo);
        LogManager.updateLog("conteudo adicional");

    }

    private void abrirArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        int escolha = fileChooser.showOpenDialog(this);
        if (escolha == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            try (Scanner scanner = new Scanner(arquivo)) {
                StringBuilder conteudo = new StringBuilder();
                while (scanner.hasNextLine()) {
                    conteudo.append(scanner.nextLine()).append("\n");
                }
                dadosGrafo = conteudo.toString();
                textArea.setText(dadosGrafo);
                painelEsquerdo.revalidate();
                textArea.repaint();
                textArea.setText(dadosGrafo);
                painelEsquerdo.revalidate();
                painelEsquerdo.repaint();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao abrir arquivo: " + ex.getMessage());
            }
        }
    }

    private void salvarArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        int escolha = fileChooser.showSaveDialog(this);
        if (escolha == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
                String conteudo = dadosGrafo;
                writer.write(conteudo);
                JOptionPane.showMessageDialog(this, "Arquivo salvo com sucesso!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar arquivo: " + ex.getMessage());
            }
        }
    }
    private void importarDot() {
        //IMPLEMENTAR AQUI
        System.out.println("importar dot...");
    }
    private void aplicar() {
        System.out.println("Clicou no Aplicar da Tela Principal");
        dadosGrafo = textArea.getText();
        painelGrafo.limpar();
        grafo.atualizarGrafo(dadosGrafo);
        painelGrafo.desenharGrafo();
        painelGrafo.setVisible(true);
        painelGrafo.repaint();
        LogManager.configurar(textLog, grafo);
        LogManager.updateLog("Contem ciclos: " + grafo.contemCiclos());

    }

    private void gerarGrafoAleatorio() {
        JPanel painel = new JPanel(new GridLayout(0, 1));
        String[] opcoes = {"Não direcionado", "Direcionado"};
        JComboBox<String> comboDirecionado = new JComboBox<>(opcoes);
        JTextField campoVertices = new JTextField();

        painel.add(new JLabel("Tipo de grafo:"));
        painel.add(comboDirecionado);
        painel.add(new JLabel("Quantidade de vértices:"));
        painel.add(campoVertices);

        int resultado = JOptionPane.showConfirmDialog(
                TelaPrincipal.this, painel, "Gerar Grafo Aleatório",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (resultado == JOptionPane.OK_OPTION) {
            boolean direcionado = comboDirecionado.getSelectedIndex() == 1;

            int quantidadeVertices;
            try {
                quantidadeVertices = Integer.parseInt(campoVertices.getText());
                if (quantidadeVertices <= 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        TelaPrincipal.this,
                        "Quantidade de vértices inválida.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            GeradorGrafoAleatorio g = new GeradorGrafoAleatorio();
            textArea.setText(g.gerarGrafo(quantidadeVertices, direcionado));
        }


    }


}
