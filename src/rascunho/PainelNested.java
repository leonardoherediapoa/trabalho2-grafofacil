package rascunho;

import javax.swing.*;
import java.awt.*;

public class PainelNested {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Exemplo de Painel Aninhado");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            JPanel painelPai = new JPanel(new BorderLayout());
            JPanel painelFilho = new JPanel(); // O painel filho vai usar o layout padrão, que é FlowLayout
            painelFilho.setBackground(Color.RED); // Definindo o fundo do painel filho como branco

            // Adicionando o painel filho ao painel pai
            painelPai.add(painelFilho, BorderLayout.CENTER); // BorderLayout.CENTER para ocupar todo o espaço disponível

            // Adicionando alguns componentes ao painel filho para fins de demonstração
            painelFilho.add(new JButton("Botão 1"));
            painelFilho.add(new JButton("Botão 2"));
            painelFilho.add(new JButton("Botão 3"));

            frame.getContentPane().add(painelPai);
            frame.setVisible(true);
        });
    }
}
