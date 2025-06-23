## Algoritmo de Dijkstra
### Autores: Larissa Oliveira e Maria Eduarda Schüler
Esta funcionalidade adiciona ao GrafoFacil a capacidade de calcular os caminhos mínimos a partir de um vértice de origem utilizando o algoritmo de Dijkstra. A implementação percorre todos os vértices do grafo representado por uma lista de adjacência, inicializando distâncias com valor infinito e utilizando uma fila de prioridade (baseada na menor distância) para explorar os vértices.

A cada iteração, o vértice com a menor distância conhecida é retirado da fila, e suas arestas são analisadas para atualizar os caminhos mais curtos aos seus vizinhos. O algoritmo retorna um mapa contendo a menor distância entre o vértice de origem e os demais vértices alcançáveis do grafo.
