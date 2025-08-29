# Projeto Sudoku (Console + Swing)

Jogo de **Sudoku** em Java com:
- **Interface gráfica (Swing)** com rascunho por célula (mini-grade 3×3).
- **Versão console** com menu interativo.
- Números **fixos** passados nos argumentos do `main`.
- Operações: iniciar novo jogo, inserir/remover número, visualizar, verificar **status**, **limpar** números do usuário e **finalizar**.

---

## ⚙️ Requisitos
- **Java 17+**
- (Opcional) IntelliJ IDEA ou outra IDE

---

## ▶️ Como executar — Console

Compile e rode a versão de console:

```bash
javac src/SudokuApp.java
# Formato 1 (1-based, sempre fixo): "linha,coluna,valor"
java -cp src SudokuApp "1,1,5" "1,2,3" "1,5,7"

# Formato 2 (DIO, 0-based + boolean): "x,y;valor,isFixed"
java -cp src SudokuApp 0,0;4,false 1,0;7,false 2,0;9,true
