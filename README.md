Aqui está o bloco completo de README.md pra você colar direto no seu repositório:

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


Dicas
• No Formato 1: índices de 1 a 9 (sempre fixo). Ex.: "3,4,9" = linha 3, coluna 4, valor 9.
• No Formato 2 (DIO): índices 0 a 8 e isFixed true/false. Ex.: 0,0;4,false = (linha 0, col 0) valor 4, não fixo.
• Para listas longas de argumentos, use Program Arguments na IDE (Run Configurations).

🖱️ Como executar — Swing (GUI)

Compile e rode a versão gráfica:

javac src/SudokuSwingApp.java
# Exemplo (Formato 1 - 1-based)
java -cp src SudokuSwingApp "1,1,5" "1,2,3" "1,5,7"

# Exemplo (Formato 2 - DIO)
java -cp src SudokuSwingApp 0,0;4,false 1,0;7,false 2,0;9,true

🎮 Controles (Swing)

Clique numa célula para selecioná-la.

1–9 → insere número (se Rascunho estiver desligado).

N → alterna o modo Rascunho (pencil marks).

1–9 no modo Rascunho → adiciona/remove números pequenos na mini-grade 3×3 da célula.

Backspace/Delete → remove número (se não for fixo) ou limpa rascunhos se já estiver vazia.

Barra superior → Novo (args), Rascunho, Remover, Limpar, Status, Finalizar.

📊 Status do jogo

Não iniciado (sem erros): antes de carregar os fixos.

Incompleto (sem erros): há células vazias, sem conflitos.

Incompleto (com erros): há células vazias e conflitos.

Completo (sem erros): totalmente preenchido e válido.

Completo (com erros): totalmente preenchido, porém inválido.

Regras de conflito

O tabuleiro é inválido quando há números repetidos em:

Linha

Coluna

Subgrade 3×3

✏️ Rascunho (pencil marks)

Permitido apenas em células vazias e não fixas.

É possível marcar vários números (1–9) por célula.

Exibidos como mini-grade 3×3 dentro da célula.

Atalho: N (liga/desliga).

🧱 Estrutura do projeto (sugestão)
src/
  SudokuApp.java         # versão console (menu de texto)
  SudokuSwingApp.java    # versão Swing com rascunho

📸 Screenshots (opcional)

Crie imagens em docs/img/ e referencie aqui:

![Console](docs/img/console.png)
![Swing](docs/img/swing.png)

📝 Licença (opcional)

Este projeto pode ser licenciado sob a licença MIT.
Crie um arquivo LICENSE com o texto da licença (MIT, por exemplo).


Se quiser, eu também te mando um `LICENSE` (MIT) pronto e um workflow simples do GitHub Actions para compilar a cada push.
::contentReference[oaicite:0]{index=0}
