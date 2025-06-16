README -> Grafo.java

O arquivo Grafo.java é responsável pela implementação de estruturas do grafo, permitindo criar, armazenar e manipular grafos direcionados e não direcionados.

O arquivo base fornecido já contava com inúmeras funcionalidades: criação de grafos direcionados e não direcionados; adição/remoção de vértices e arestas; verificação de existência de um vértice no grafo; limpeza total do grafo; e atualização do grafo a partir de uma entrada textual.

Foi adicionada a funcionalidade de verificar se um grafo contém ciclos, no método contemCiclos(). 
Esse método percorre todos os vértices o grafo e para cada um que ainda não tenha sido visitado ele realiza uma busca por profundidade com o auxílio de uma pilha.
Durante a busca, ele analisa as conexões do vértice. Caso um vizinho ainda não tenha sido visitado, ele busca recursivamente, e se ele encontra o vizinho visitado e que não é o vértice pai no caminho atual, indica a presença de um ciclo no grafo, retornando true.
Caso ele encerre todo esse processo sem encontrar a correspondência de um ciclo, ele retorna false, indicando ausência de ciclos.