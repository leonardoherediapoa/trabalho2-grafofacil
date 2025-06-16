# Exportação do Grafo para DOT

Este projeto permite exportar grafos para o formato DOT, utilizado por ferramentas como Graphviz. As funcionalidades estão disponíveis através de dois botões na interface gráfica e métodos auxiliares.

## Botões

- **Exportar DOT (`btnExportarDot`)**
    - Exporta o grafo atual para um arquivo com extensão `.txt`.
    - Ao clicar, será solicitado o local e nome do arquivo para salvar.
    - O conteúdo do arquivo segue o padrão DOT, gerado pelo método `grafo.toDot()`.

- **Copiar DOT (`btnCopiarDot`)**
    - Copia o conteúdo DOT do grafo atual para a área de transferência do sistema operacional.
    - Permite colar o conteúdo em outros aplicativos, como editores de texto ou ferramentas online de visualização de grafos.

## Métodos

- **exportarParaAreaTransferenciaDot()**
    - Método da classe `TelaPrincipal`.
    - Obtém o conteúdo DOT do grafo usando `grafo.toDot()`.
    - Copia o conteúdo para a área de transferência utilizando as classes `Toolkit` e `Clipboard` do Java.
    - Exibe uma mensagem de confirmação ao usuário.

- **exportarParaArquivoDot()**
    - Método da classe `TelaPrincipal`.
    - Abre um diálogo para o usuário escolher onde salvar o arquivo `.txt`.
    - Salva o conteúdo DOT do grafo no arquivo selecionado.
    - Exibe uma mensagem de sucesso ou erro.

## Exemplo de Uso

1. Monte ou carregue um grafo na interface.
2. Clique em **Exportar DOT** para salvar o grafo em um arquivo `.txt`.
3. Clique em **Copiar DOT** para copiar o conteúdo DOT para a área de transferência.
