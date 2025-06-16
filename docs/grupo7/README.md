# Documentação - Persistência da Posição dos Vértices ao fechar e abrir o arquivo novamente
# Nomes: Lucas Novelly Simão e Leonardo Fagundes Wingert
# User GitHub: lnss7 e leofwingert

## Visão Geral

Esta funcionalidade implementa a persistência das posições dos vértices no diagrama do GrafoFácil, resolvendo o problema onde as posições dos vértices eram perdidas ao aplicar mudanças, adicionar novos vértices ou ao salvar/abrir um arquivo.

---

## O Problema

Originalmente, quando um grafo era manipulado no GrafoFácil, ocorriam os seguintes problemas:

- A posição dos vértices era perdida quando se clicava no botão **"Aplicar"**
- A posição não era mantida ao salvar o arquivo
- Ao reabrir um arquivo salvo, todas as posições voltavam ao padrão

---

## Solução Implementada

### 1. Modificações na Classe `Vertice`

A classe `Vertice` foi estendida para armazenar coordenadas `(x, y)`:

```java
public class Vertice {
    private String rotulo;
    private int x = -1;  // Adicionado
    private int y = -1;  // Adicionado

    // Construtor atualizado
    public Vertice(String rotulo, int x, int y) {
        this.rotulo = rotulo;
        this.x = x;
        this.y = y;
    }

    // Getters e setters para as coordenadas
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

}
```

---

### 2. Formato de Arquivo Atualizado

O formato dos arquivos de grafo agora inclui coordenadas para cada vértice:

```
1 61 60     // Vértice 1 na posição (61,60)
2 36 411    // Vértice 2 na posição (36,411)
3 412 329   // Vértice 3 na posição (412,329)
1 -> 2 1    // Aresta direcionada de 1 para 2 com peso 1
2 -> 3 1    // Aresta direcionada de 2 para 3 com peso 1
```

---

### 3. Adaptações em `PainelGrafo`

O `PainelGrafo` foi modificado para usar as coordenadas armazenadas nos vértices:

```java
// Na criação dos círculos para representar vértices
int vx = v.getX() >= 0 ? v.getX() : x;
int vy = v.getY() >= 0 ? v.getY() : y;
Circulo c = new Circulo(vx, vy, 20, v.getRotulo());
```

---

### 4. Atualização da Classe `Grafo`

O método `atualizarGrafo` foi melhorado para interpretar corretamente vértices com coordenadas e arestas com pesos:

```java
if (linha.contains("->") || linha.contains("--")) {
    // Tratar como aresta
    // ...
} else {
    // Tratar como vértice com possíveis coordenadas
    String[] partes = linha.split("\\s+");
    if (partes.length >= 3) {
        String rotulo = partes[0];
        int x = Integer.parseInt(partes[1]);
        int y = Integer.parseInt(partes[2]);
        adicionarVertice(new Vertice(rotulo, x, y));
    }
}
```

---

## Como Usar

1. **Movendo Vértices:** Arraste os vértices para qualquer posição desejada no diagrama.
2. **Visualização:** Clique em "Aplicar" para atualizar o grafo - as posições serão mantidas.
3. **Salvar Grafo:** Use a opção "Salvar" para criar um arquivo que armazenará as posições.
4. **Abrir Grafo:** Use a opção "Abrir" para restaurar o grafo com as posições originais.

---

## Problemas Resolvidos

1. **Visualização de Arestas:** Corrigido um bug onde arestas com peso não eram exibidas corretamente.
2. **Formato de Arquivo:** Melhorado o parser para interpretar corretamente vértices com coordenadas.
3. **Persistência Visual:** As posições dos vértices são agora mantidas durante toda a sessão e entre sessões diferentes.

---

## Considerações Técnicas

- Os valores padrão de `x` e `y` são `-1`, indicando que o layout automático deve ser usado.
- As coordenadas são salvas como inteiros para simplificar a representação no arquivo.
- O parser foi redesenhado para ser mais robusto e lidar com diferentes formatos de entrada.