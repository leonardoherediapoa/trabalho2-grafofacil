package rascunho;

import javax.swing.*;
import java.awt.*;

public class TabelaExemplo extends JFrame {
    public TabelaExemplo() {
        setTitle("Exemplo de Tabela");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Array multidimensional com os dados da tabela
        Object[][] dados = {
                {"1", "João", "joao@email.com"},
                {"2", "Maria", "maria@email.com"},
                {"3", "Pedro", "pedro@email.com"}
        };

        // Array com os nomes das colunas
        String[] colunas = {"ID", "Nome", "Email"};

        // Criar uma nova tabela com os dados e colunas
        JTable tabela = new JTable(dados, colunas);

        // Adicionar a tabela a um painel de rolagem
        JScrollPane scrollPane = new JScrollPane(tabela);

        // Adicionar o painel de rolagem ao frame
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        // Criar e exibir a janela
        SwingUtilities.invokeLater(() -> {
            TabelaExemplo exemplo = new TabelaExemplo();
            exemplo.setVisible(true);
        });
    }
}