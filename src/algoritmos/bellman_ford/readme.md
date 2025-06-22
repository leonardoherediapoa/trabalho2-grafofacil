# Algoritmo de Bellman-Ford

## Descrição
Esta é uma implementação em Java do algoritmo de Bellman-Ford, que é usado para encontrar os caminhos mais curtos em um dígrafo ponderado (grafo direcionado com pesos), mesmo quando existem arestas com pesos negativos.

## Funcionalidades
- Calcula o caminho mais curto de um vértice de origem para todos os outros vértices
- Permite trabalhar com grafos que possuem arestas com pesos negativos
- Detecta ciclos negativos no grafo
- Armazena os caminhos anteriores para reconstrução do caminho

## Estrutura da Classe

### Atributos
- `grafo`: O dígrafo ponderado de entrada
- `origem`: Vértice inicial para o cálculo dos caminhos
- `visitados`: Array de booleanos que marca os vértices visitados
- `anteriores`: Array que armazena o vértice anterior no caminho mais curto
- `distancia`: Array que armazena as distâncias mínimas até cada vértice

### Métodos Principais
- `BellmanFord(DigrafoPonderado g, int origem)`: Construtor que inicializa o algoritmo
- `percorrer()`: Implementa o algoritmo de Bellman-Ford
- `imprimirCaminhos()`: Exibe os resultados do algoritmo

## Como Usar
- Criar um dígrafo ponderado DigrafoPonderado grafo = new DigrafoPonderado(numeroDeVertices);
- Adicionar arestas ao grafo
- Criar uma instância do algoritmo BellmanFord bf = new BellmanFord(grafo, verticeOrigem);
- Imprimir os resultados bf.imprimirCaminhos();

## Complexidade
- Tempo: O(V × E), onde V é o número de vértices e E é o número de arestas
- Espaço: O(V), onde V é o número de vértices

## Observações
- O algoritmo assume que não existem ciclos negativos no grafo
- Os vértices são numerados de 0 a N-1
- O valor `Integer.MAX_VALUE` é usado para representar distâncias infinitas
