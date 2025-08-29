# Projeto Sudoku (Console e Swing)

Jogo de Sudoku para **console** e **interface gráfica (Swing)** com:
- menu interativo,
- números fixos vindos dos **args** (`"linha,coluna,valor"`),
- inserir/remover número,
- **rascunho** por célula (mini-grade 3×3) no Swing,
- ver status (não iniciado / incompleto / completo + com/sem erros),
- limpar números do usuário e finalizar jogo válido.

## Como executar (Console)
```bash
javac src/SudokuApp.java
# exemplos de fixos nos args:
java -cp src SudokuApp "1,1,5" "1,2,3" "1,5,7" "2,1,6" "3,4,9"
