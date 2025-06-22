README -> Grafo.java

O arquivo Grafo.java é responsável pela implementação de estruturas do grafo, permitindo criar, armazenar e manipular grafos direcionados e não direcionados.

O arquivo base fornecido já contava com inúmeras funcionalidades: criação de grafos direcionados e não direcionados; contem Ciclos; adição/remoção de vértices e arestas; verificação de existência de um vértice no grafo; limpeza total do grafo; e atualização do grafo a partir de uma entrada textual.

Implementação da Ordenação Topológica
Por 
 Daniel Frantz (DanFrantz)
 Fernando Neto (ferlisboaneto)
 Augusto Knob(AugustoKnob)

Método: ordenacaoTopologica()

Pacote:Estruturas, método dentro de Grafo.java

Conceito: Estabelece a ordem de precedência entre os diversos vértices no grafo,
Primeiramente verificamos se o grafo atual é direcionado e acíclico, uma vez que ambas as características impedem o uso deste algoritmo.
Após isso, um algoritmo é escolhido pra descobrir as precedências dentro do grafo e manda em forma de string.

Implementação: Utilizamos um algoritmo de Kahn, no qual calculamos o grau de entrada de cada vértice, descobrimos quais vértices tem grau 0 e os adicionamos a fila, daí então, enquanto a fila não estiver vazia
1- Removemos um vértice da fila e o adicionamos a lista de resultados.
2- Para cada vértice de destino, o grau de entrada é decrementado.
3- Caso o grau de entrada do vértice de destino seja igual a 0, ele é adicionado a fila.
Por fim, através do StringBuilder criamos uma String com a ordenação topológica. 

Estruturas Usadas:
Map: Usado pra armazenar o grau de entrada de cada vértice, vital para o algoritmo de Kahn
Queue: Usado pra garantir que os vértices estão sendo processados na ordem topologicamente correta.
List: Usada pra armazenar a ordem topológica final.


