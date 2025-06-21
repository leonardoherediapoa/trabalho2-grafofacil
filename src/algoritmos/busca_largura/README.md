Busca em Largura (BFS) - Implementação em Java

Este módulo implementa o algoritmo de Busca em Largura (BFS) em grafos, utilizando classes auxiliares para representar vértices, arestas e a estrutura geral do grafo.

---

Classe: `BuscaEmLargura`

Essa classe executa a busca em largura a partir de um vértice de origem, marcando vértices visitados e registrando o caminho percorrido.

#Construtor: `public BuscaEmLargura(Grafo grafo, Vertice origem)`


- Executa a busca em largura no grafo a partir do vértice `origem`.
- Inicializa as estruturas de controle de visita e caminho (`visitado` e `anterior`).

---

#Método: `private void realizarBusca(Grafo grafo, Vertice origem)`

- Executa a BFS padrão usando uma fila.
- Marca os vértices visitados.
- Registra o vértice anterior de cada vértice visitado para reconstruir o caminho.

> *Nota: Este método é privado e é chamado automaticamente pelo construtor.

---

#Método: `public List<Vertice> caminhoAte(Vertice destino)`

- Reconstrói o caminho do vértice de origem até o vértice `destino` usando os dados salvos em `anterior`.
- Retorna uma lista de vértices na ordem correta (do início ao destino).

---
