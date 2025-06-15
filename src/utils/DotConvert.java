package utils;

public class DotConvert {
    public static String converterDot(String dot) {
        String[] linhas = dot.split("\n");
        StringBuilder resultado = new StringBuilder();
        boolean dentro = false;

        for (String linha : linhas) {
            linha = linha.trim();
            if (linha.isEmpty()) continue;
            if (!dentro) {
                if (linha.startsWith("graph") || linha.startsWith("digraph")) dentro = true;
                continue;
            }
            if (linha.equals("}")) break;

            String aresta = linha;
            String peso = "1";

            int iAttr = linha.indexOf("[");
            if (iAttr != -1) {
                aresta = linha.substring(0, iAttr).trim();
                String atributos = linha.substring(iAttr);
                int iLabel = atributos.indexOf("label=");
                if (iLabel != -1) {
                    int inicio = iLabel + 6;
                    int fim = atributos.indexOf("]", inicio);
                    if (fim == -1) fim = atributos.length();
                    peso = atributos.substring(inicio, fim).replaceAll("[^0-9]", "").trim();
                    if (peso.isEmpty()) peso = "1";
                }
            }

            if (aresta.endsWith(";")) aresta = aresta.substring(0, aresta.length() - 1).trim();

            if (aresta.contains("->") || aresta.contains("--")) {
                resultado.append(aresta).append(" ").append(peso).append("\n");
            }
        }

        return resultado.toString();
    }

}
