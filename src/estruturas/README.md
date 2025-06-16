README -> Grafo.java

O arquivo Grafo.java é responsável pela implementação de estruturas do grafo, permitindo criar, armazenar e manipular grafos direcionados e não direcionados.

O arquivo base fornecido já contava com inúmeras funcionalidades: criação de grafos direcionados e não direcionados; adição/remoção de vértices e arestas; verificação de existência de um vértice no grafo; limpeza total do grafo; e atualização do grafo a partir de uma entrada textual.

Foi adicionada a funcionalidade de verificar se um grafo contém ciclos, no método contemCiclos(). 
O método contemCiclos() verifica se o grafo possui ciclos, funcionando tanto para grafos direcionados quanto não direcionados. Se o grafo for não direcionado, ele realiza uma busca em profundidade (DFS) utilizando controle de "pai", identificando ciclos quando encontra um vizinho já visitado que não seja o pai do vértice atual.Se o grafo for direcionado, a DFS utiliza uma marcação de estados (naoVisitado, visitando, visitado). Um ciclo é detectado quando, durante a busca, um vértice encontra um vizinho que está no estado "visitando", ou seja, que ainda está no caminho atual da busca. O método retorna true se encontrar um ciclo e false caso contrário.