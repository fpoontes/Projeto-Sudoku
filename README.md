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



## ▶️ Executar — Console
Compile e rode:
```bash
javac src/SudokuApp.java
# exemplos de fixos nos args do main (formato "linha,coluna,valor"):
java -cp src SudokuApp "1,1,5" "1,2,3" "1,5,7" "2,1,6" "3,4,9"

javac src/SudokuSwingApp.java
java -cp src SudokuSwingApp "1,1,5" "1,2,3" "1,5,7"

