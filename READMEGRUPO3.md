Busca em Largura - GrafoFácil

Grupo:
    João Vitor Araújo - J-araju
    Rafaela Mello - mellorafaa

Implementação do algoritmo de Busca em Largura para percorrer grafos.

Funcionalidade
    Explora o grafo em camadas (níveis de distância) a partir de um vértice de origem.
    Garante que cada vértice seja visitado apenas uma vez.
    Retorna a ordem de visitação dos vértices.
    Armazena os predecessores de cada vértice visitado, permitindo a reconstrução de caminhos mínimos entre a origem e qualquer outro vértice alcançável.

Localização do Código
    Arquivo: BuscaLargura.java
    Pacote: estruturas

Uso Básico
    BuscaLargura bfs = new BuscaLargura();
    Vertice origem = grafo.getVertice("A");
    List<Vertice> ordemVisita = bfs.buscar(grafo, origem);
    Map<Vertice, Vertice> predecessores = bfs.getPredecessores();
        Parâmetro: Objeto Grafo e o Vertice de origem.
        Resultado: Lista de visitação em ordem de camadas + mapa de predecessores.

Detalhes da Implementação
    Suporte a Grafos Direcionados e Não Direcionados:
    Em grafos não direcionados, a implementação trata laços para evitar voltar ao próprio vértice.

Estruturas Usadas:
    Fila (Queue): Para controle da ordem de expansão dos vértices.
    Mapas (Map): Para registrar vértices visitados e predecessores.

Lógica Principal:
    Inicia a busca a partir do vértice de origem.
    Visita os vizinhos nível por nível.
    Marca vértices como visitados para evitar revisitas.
    Atualiza o predecessor de cada vértice no momento em que é descoberto.