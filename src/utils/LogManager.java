package utils;

import javax.swing.JTextArea;
import estruturas.Grafo;
import estruturas.Vertice;
import estruturas.Aresta;
import mostrarGrauVertices.MostrarGrausVertices;

public class LogManager {
    private static JTextArea textLog;
    private static Grafo grafo;

    public static void configurar(JTextArea areaLog, Grafo g) {
        textLog = areaLog;
        grafo = g;
    }

    public static void updateLog(String mensagem) {
        if (textLog == null || grafo == null) return;

        StringBuilder log = new StringBuilder();
        log.append("Tipo de Grafo: ").append(grafo.isDirecionado() ? "Direcionado" : "Não Direcionado").append("\n");
        log.append("Número de Vértices: ").append(grafo.getNumeroVertices()).append("\n");
        log.append("Número de Arestas: ").append(grafo.getListaArestas().size()).append("\n");

        if (mensagem != null && !mensagem.isEmpty()) {
            log.append("\n").append(mensagem).append("\n");
        }

        textLog.setText(log.toString());
        textLog.setCaretPosition(0);
    }

    public static void updateLog() {
        updateLog(null);
    }

    public static void mostrarGrausVertices() {
        if (textLog == null || grafo == null) return;
        String mensagem = MostrarGrausVertices.calcularGrausVertices(grafo);
        updateLog(mensagem);
    }

    }
