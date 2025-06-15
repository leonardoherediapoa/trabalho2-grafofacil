package visualizacao;

import algoritmos.MarcarVertices;
import algoritmos.BuscaProfundidade;
import estruturas.Aresta;
import estruturas.Grafo;
import estruturas.Vertice;
import utils.LogManager;
import java.util.List;
import utils.DotConvert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
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
    private JTextArea textLog;
    public JTextArea textArea;
    private BuscaProfundidade dfs;
    private JMenuBar menuBar;


    public TelaPrincipal() {
        //  dadosGrafo = "A" + System.lineSeparator() + "B" + System.lineSeparator() +
        //
        // "C";
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setTitle("ALEST2 - Ferramenta para apoio ao aprendizado de grafos");

        painelEsquerdo = new JPanel(new BorderLayout());
        painelEsquerdo.setPreferredSize(new Dimension(200, 0));
        JButton btnAplicar = new JButton("Aplicar");

        painelDireito = new JPanel(new BorderLayout());

        painelEsquerdo = new JPanel(new BorderLayout());
        painelEsquerdo.setPreferredSize(new Dimension(300, 0));

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

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

        JPanel barraBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
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

                SwingUtilities.invokeLater(() -> {
                    painelGrafo.revalidate();
                    painelGrafo.repaint();
                });
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

        JButton btnMST = new JButton("MST");
        barraBotoes.add(btnMST);

        JButton btnDijkstra = new JButton("Dijkstra");
        barraBotoes.add(btnDijkstra);

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

        criarMenu();




        setJMenuBar(menuBar);
        setVisible(true);

        LogManager.configurar(textLog, grafo);

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

        JMenuItem menuInterImportarDot = new JMenuItem("Importar DOT");
        menuInterImportarDot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirArquivo(); //TROCAR AQUI PARA O IMPORTAR DOT
                System.out.println(textArea.getText());
                SwingUtilities.invokeLater(() -> {
                    painelEsquerdo.invalidate();
                    painelEsquerdo.revalidate();
                    painelEsquerdo.repaint();
                });
            }
        });

        JMenuItem menuItemSair = new JMenuItem("Sair");
        menuItemSair.addActionListener(e -> System.exit(0));


        menuArquivo.add(menuItemAbrir);
        menuArquivo.add(menuImportarDot);
        menuArquivo.add(menuItemSalvar);
        menuArquivo.add(menuItemSair);

        JMenu menuAcao = new JMenu("Acao");
        JMenuItem menuItemGrau = new JMenuItem("Mostrar Grau dos Vertices");
        menuAcao.add(menuItemGrau);

        menuBar.add(menuArquivo);
        menuBar.add(menuAcao);
        setJMenuBar(menuBar);
        setVisible(true);


    }
    private void criarBotoes() {
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

                if (arquivo.getName().toLowerCase().endsWith(".dot")) {
                    dadosGrafo = DotConvert.converterDot(dadosGrafo);
                }

                textArea.setText(dadosGrafo);
                painelEsquerdo.revalidate();
                textArea.repaint();
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
                // salva vertice com a coordenada
                for (Vertice v : grafo.getListaVertices()) {
                    writer.write(v.getRotulo() + " " + v.getX() + " " + v.getY());
                    writer.newLine();
                }
                // salva arestas
                for (Aresta a : grafo.getListaArestas()) {
                    String sep = a.isDirecionada() ? "->" : "--";
                    writer.write(a.getOrigem().getRotulo() + " " + sep + " " + a.getDestino().getRotulo() + " "
                            + a.getPeso());
                    writer.newLine();
                }
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



}
