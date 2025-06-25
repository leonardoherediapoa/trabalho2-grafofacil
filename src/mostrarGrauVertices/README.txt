Mostrar o Grau de Todos os Vértices - João Biasoli e Arthur Mendes Maciel. Issue #11

Para o desenvolvimento dessa funcionalidade, criamos um método chamado CalcularGrauVertices
que recebe um grafo e retorna uma string, onde as chaves são os vértices e os valores são seus respectivos graus.
Se o grafo digitado for direcionado, o grau de entrada é o numero de entrada cujo destino é o vértice
e o grau de saída é o número de arestas cujo origem é o vértice.
E adiciona essa String no formato "Vértice: Grau de Entrada, Grau de Saída".
Se o grafo for não direcionado, o grau é o número de arestas que incidem no vértice, e adiona essa String no formato "Vértice: Grau".

Na classe TelaPrincipal, adicionamos funcionalidade ao botão ja criado anteriormente pelo professor,
que chama o método CalcularGrauVertices e exibe o resultado no terminal em baixo da execução.
Para rodar essa funcionalidade, é necessário clicar no botão "Ações" e depois "Mostrar Grau dos Vértices".