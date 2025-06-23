README -> MST.Java

Grupo: Daniella Moraes Santos, Gabriel Caliari Botega e Maria Eduarda da Silva Ourique.

O nosso grupo ficou responsável pela criação e desenvolvimento de um MST, que seria o menor caminho possível para percorrer todos os vértices de um grafo. Para isso, tomamos como base o algoritmo de Kruskal, um dos mais utilizados quando esse tema é tratado, especialmente pelo seu fácil entendimento e implementação.
Para tal, realizamos a criação de uma classe chamada "MST" que possui uma lista de arestas, uma pilha e um grafo como atributos. Para instanciar a classe, é necessário apenas passar como parâmetro um grafo criado anteriormente.

Nessa classe, há um método público e um método privado: "gerarMST" e "verificador", respectivamente. O primeiro adiciona na pilha as arestas em ordem decrescente. Dessa forma, ao retirar os elementos da pilha, as arestas estarão em ordem crescente de peso, conforme indicado pelo algoritmo. O segundo instancia um grafo auxiliar, para realizar as verificações necessárias com as arestas sem mexer no grafo original. Ele primeiramente faz com que o atributo criado "silencioso" se torne "true", a fim de não aparecer no terminal que os vértices foram criados novamente, assim,  as informações de resposta não são duplicadas. Dentro do laço, é realizada uma verificação onde a aresta retirada da pilha (com menor peso) é adicionada ao grafo e então, vê-se caso o grafo possua ciclo. Caso note-se que há um ciclo, essa aresta não pode ser adicionada ao resultado, assim, a mesma é retirada do grafo. Caso contrário, ela entra no ArrayList chamado "resultado". Quando a quantidade de elementos no resultado for igual a quantidade de vértices menos 1, o método imprime o resultado do MST.

A fim de completar o solicitado, tivemos que fazer algumas alterações na classe "Grafo" também.
Realizou-se a criação de um atributo privado chamado "silencioso", conforme dito anteriormente. Ele é responsável por: caso ele esteja como "true", não realizar a escrita no terminal; caso "false", imprimir normalmente todas as informações. Além disso, é importante salientar que criou-se um método chamado "setSilencioso" na classe para alteração do valor do atributo.
Isso foi implementado pois, sem ele, quando era adicionada uma aresta no grafo auxiliar (dentro do método "verificador") ele tentava encontrar um vértice para adicionar as arestas, porém, sem sucesso. Então ele realizava a criação dos vértices novamente.
Caso ele não tivesse sido implementado, na resposta sairia, por exemplo:

Vértice 1 criado.
Vértice 2 criado.
Vértice 3 criado.
Vértice 1 criado.
Vértice 2 criado.
Vértice 3 criado.
resultado

Dessa forma, a equipe achou conveniente realizar esta alteração, juntamente com os ajustes no método "adicionarVertice" utilizando do novo atributo.

Além disso, realizou-se uma atualização no método "adicionarAresta", pois o mesmo não criava vértices caso eles não tivessem sido instanciados anteriormente. Não seria estritamente necessário, mas se tornou um facilitador para o método "verificador" da nossa classe.
Ele faz uma análise de, caso a aresta esteja sendo adicionada ligando dois vértices e algum dos dois não tenha sido criado, o próprio método cria.

Como continuação, tivemos de criar um novo método na classe "Grafo", chamado "removerAresta".
Na implementação, utilizou-se da classe "Iterator" do próprio Java, para assim, percorrer todas as arestas a fim de encontrar qual deve ser removida. Criou-se também, uma maneira parecida de remoção para quando o grafo não é direcionado.

Apenas para fins de testes, criou-se também o método "toString" na classe Aresta, assim, pôde-se verificar se o resultado esperado foi gerado corretamente. O método está gerando a resposta no padrão dot para visualização posterior do grafo.

Tentamos mexer na “TelaPrincipal” para fazer com que, ao apertar o botão, a resposta apareça na aplicação, porém, não conseguimos. O método está em funcionamento, mas ao pressionar o botão, a resposta apenas aparece no terminal. Assim, o que foi possível de realizar foi o vínculo do botão com a chamada do método.