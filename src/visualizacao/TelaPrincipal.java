package visualizacao;

import estruturas.Aresta;
import estruturas.Grafo;
import estruturas.Vertice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;

public class TelaPrincipal extends JFrame {
    public final Grafo grafo;
    public String dadosGrafo;
    public PainelGrafo painelGrafo;
    public TelaPrincipal() {
        //dadosGrafo = "A" + System.lineSeparator() + "B" + System.lineSeparator() + "C";
        dadosGrafo = "A -- B";
        dadosGrafo += System.lineSeparator() + "D -- C";
        grafo = Grafo.gerarGrafo(dadosGrafo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setTitle("ALEST2 - Ferramenta para apoio ao aprendizado de grafos");
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem menuItemSalvar = new JMenuItem("Salvar");
        JMenuItem menuItemSair = new JMenuItem("Sair");
        menuItemSalvar.addActionListener(e -> salvar());
        menuItemSair.addActionListener(e -> System.exit(0));
        menuArquivo.add(menuItemSalvar);
        menuArquivo.add(menuItemSair);
        menuBar.add(menuArquivo);
        setJMenuBar(menuBar);

        System.out.println(grafo.toDot());
        painelGrafo = new PainelGrafo(grafo);

        // Painel esquerdo
        JPanel painelDireito = new JPanel(new BorderLayout());
        add(painelDireito);

        // Adicionando um painel dentro do painel esquerdo
        painelGrafo.setBackground(Color.WHITE); // Cor de fundo do painel interno
        painelGrafo.setVisible(true);
        painelDireito.add(painelGrafo, BorderLayout.CENTER); // Adiciona o painel interno ao painel esquerdo

        PainelDados painelDados = new PainelDados(painelGrafo, grafo);

        // Painel direito
        JPanel painelEsquerdo = new JPanel(new BorderLayout());
        painelEsquerdo.setPreferredSize(new Dimension(200, 0));
        JButton btnAplicar = new JButton("Aplicar");
        btnAplicar.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             System.out.println("Clicou no Aplicar da Tela Principal");
                                             dadosGrafo = painelDados.textArea.getText();
                                             grafo.atualizarGrafo(dadosGrafo);
                                             painelGrafo.desenharGrafo();
                                             painelGrafo.setVisible(true);
                                             painelGrafo.repaint();
                                         }
                                     }
        );
        painelEsquerdo.add(btnAplicar, BorderLayout.SOUTH);
        painelEsquerdo.add(painelDados, BorderLayout.CENTER);
        add(painelEsquerdo, BorderLayout.WEST);



        // Barra de rodapé
        JLabel statusLabel = new JLabel("Versão 1.0");
        statusLabel.setBorder(BorderFactory.createLoweredBevelBorder());
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);

        painelDados.textArea.setText(dadosGrafo);

    }
    public void salvar() {
        System.out.println("Salvando...");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("c://temp//grafo.txt"))) {
            bw.write(dadosGrafo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
