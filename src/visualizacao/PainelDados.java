package visualizacao;

import estruturas.Grafo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelDados extends JPanel {
    public JTextArea textArea;
    public PainelDados(PainelGrafo painelGrafo, Grafo grafo) {
        this.setLayout(new BorderLayout());
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        this.add(scrollPane, BorderLayout.CENTER);
        setVisible(true);

    }
}
