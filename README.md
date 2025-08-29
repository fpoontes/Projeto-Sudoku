# Projeto Sudoku (Console e Swing)

Jogo de **Sudoku** em Java com:
- **Interface gráfica (Swing)** com rascunhos por célula (mini-grade 3×3).
- **Versão console** com menu interativo.
- Números **fixos** passados nos **args** do `main` no formato `"linha,coluna,valor"`.
- Operações: iniciar novo jogo, inserir/remover número, visualizar tabuleiro, verificar status, limpar números do usuário e finalizar.

> **Status do jogo**:  
> - **Não iniciado** (sem erros) – antes de carregar os fixos.  
> - **Incompleto (com/sem erros)** – se há espaços vazios; “com erros” quando existe conflito em linha, coluna ou bloco 3×3.  
> - **Completo (sem erros)** – solução válida.  
> - **Completo (com erros)** – todas as células preenchidas, mas inválido.

---

## 📦 Estrutura

src/
  SudokuApp.java         # versão console (menu de texto)
  SudokuSwingApp.java    # versão Swing com rascunho (mini-grade 3×3)

## ▶️ Como executar (Console)

Compile e rode a versão de console:
```bash
javac src/SudokuApp.java
java -cp src SudokuApp "1,1,5" "1,2,3" "1,5,7"

## 🔗 Repositórios de referência (DIO)
- **Branch main (terminal)**: https://github.com/digitalinnovationone/sudoku  
- **Branch UI (interface gráfica)**: https://github.com/digitalinnovationone/sudoku/tree/ui

> Dica: você pode dar **fork** nesses repositórios para versionar suas evoluções mantendo referência ao original.

## 🧰 Links úteis
- **Draw.io**: https://app.diagrams.net

## ▶️ Execução com argumentos (formato DIO)
O projeto também aceita o **formato de argumentos da DIO**:


java -cp src SudokuApp "1,1,5" "1,2,3" "1,5,7" "2,1,6" "3,4,9"

javac src/SudokuSwingApp.java
java -cp src SudokuSwingApp "1,1,5" "1,2,3" "1,5,7"

