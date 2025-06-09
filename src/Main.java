import visualizacao.TelaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal telaPrincipal = new TelaPrincipal();
        });
    }
}