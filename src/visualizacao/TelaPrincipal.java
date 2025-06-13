package visualizacao;

import algoritmos.MarcarVertices;
import estruturas.Aresta;
import estruturas.Grafo;
import estruturas.Vertice;
import utils.LogManager;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;



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

    public TelaPrincipal() {
        //dadosGrafo = "A" + System.lineSeparator() + "B" + System.lineSeparator() + "C";
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

        JButton btnExportarDot = new JButton("Exportar DOT");
        btnExportarDot.addActionListener(e -> exportarParaArquivoDot());

        JButton btnCopiarDot = new JButton("Copiar DOT");
        btnCopiarDot.addActionListener(e -> exportarParaAreaTransferenciaDot());

        barraBotoes.add(btnCopiarDot);
        barraBotoes.add(btnExportarDot);
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
                                     }
        );

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


        JMenu menuAcao = new JMenu("Acao");
        JMenuItem menuItemGrau = new JMenuItem("Mostrar Grau dos Vertices");
        menuAcao.add(menuItemGrau);

        menuBar.add(menuArquivo);
        menuBar.add(menuAcao);
        setJMenuBar(menuBar);
        setVisible(true);

        LogManager.configurar(textLog, grafo);
        LogManager.updateLog("conteudo adicional");

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




}
