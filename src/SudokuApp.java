// Arquitetura simples em um único arquivo para facilitar a entrega
// Compile e rode com JDK 17+:  javac SudokuApp.java && java SudokuApp "1,1,5" "1,2,3" "1,5,7"
// Formato dos argumentos: "linha,coluna,valor" (1..9). Ex.: "3,4,9" significa linha 3, coluna 4, valor 9 como número FIXO inicial.

import java.util.*;

class SudokuApp {
    // ======== Modelo de jogo ========
    static class Sudoku {
        private final int[][] grid = new int[9][9];
        private final boolean[][] fixed = new boolean[9][9];
        private boolean started = false; // controla status "não iniciado"

        public void startNewGame(String[] args) {
            // Limpa tudo e aplica números fixos vindos dos args
            for (int r = 0; r < 9; r++) {
                Arrays.fill(grid[r], 0);
                Arrays.fill(fixed[r], false);
            }
            parseFixedFromArgs(args);
            started = true;
        }

        public void parseFixedFromArgs(String[] args) {
            if (args == null) return;
            for (String token : args) {
                if (token == null || token.isBlank()) continue;
                // Formato esperado: "linha,coluna,valor" (1..9)
                String[] parts = token.trim().split("[,;]");
                if (parts.length != 3) {
                    System.out.println("Ignorando argumento inválido: " + token);
                    continue;
                }
                try {
                    int r = Integer.parseInt(parts[0].trim());
                    int c = Integer.parseInt(parts[1].trim());
                    int v = Integer.parseInt(parts[2].trim());
                    if (!inRange(r) || !inRange(c) || !inRange(v)) {
                        System.out.println("Ignorando fora do intervalo 1..9: " + token);
                        continue;
                    }
                    int ri = r - 1, ci = c - 1;
                    if (grid[ri][ci] != 0) {
                        System.out.println("Posição já ocupada nos fixos: (" + r + "," + c + ")");
                        continue;
                    }
                    grid[ri][ci] = v;
                    fixed[ri][ci] = true;
                } catch (NumberFormatException e) {
                    System.out.println("Ignorando argumento inválido: " + token);
                }
            }
        }

        private boolean inRange(int x) { return x >= 1 && x <= 9; }

        public boolean placeNumber(int r1, int c1, int v) {
            if (!inRange(r1) || !inRange(c1) || !inRange(v)) {
                System.out.println("Valores devem estar entre 1 e 9.");
                return false;
            }
            int r = r1 - 1, c = c1 - 1;
            if (fixed[r][c]) {
                System.out.println("Não é permitido sobrescrever um número FIXO.");
                return false;
            }
            if (grid[r][c] != 0) {
                System.out.println("A posição já está preenchida.");
                return false;
            }
            grid[r][c] = v; // Regras de conflito são verificadas no status; aqui só bloqueia célula ocupada
            return true;
        }

        public boolean removeNumber(int r1, int c1) {
            if (!inRange(r1) || !inRange(c1)) {
                System.out.println("Índices devem estar entre 1 e 9.");
                return false;
            }
            int r = r1 - 1, c = c1 - 1;
            if (fixed[r][c]) {
                System.out.println("Este é um número FIXO e não pode ser removido.");
                return false;
            }
            if (grid[r][c] == 0) {
                System.out.println("A posição já está vazia.");
                return false;
            }
            grid[r][c] = 0;
            return true;
        }

        public void clearUserNumbers() {
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    if (!fixed[r][c]) grid[r][c] = 0;
                }
            }
        }

        public boolean hasAnyNumber() {
            for (int[] row : grid) for (int v : row) if (v != 0) return true;
            return false;
        }

        public boolean isComplete() {
            for (int r = 0; r < 9; r++)
                for (int c = 0; c < 9; c++)
                    if (grid[r][c] == 0) return false;
            return !hasConflicts();
        }

        public boolean hasConflicts() {
            // Checa linhas e colunas
            for (int i = 0; i < 9; i++) {
                if (hasDup(grid[i])) return true; // linha i
                int[] col = new int[9];
                for (int r = 0; r < 9; r++) col[r] = grid[r][i];
                if (hasDup(col)) return true; // coluna i
            }
            // Checa subgrades 3x3
            for (int br = 0; br < 9; br += 3) {
                for (int bc = 0; bc < 9; bc += 3) {
                    int[] box = new int[9];
                    int k = 0;
                    for (int r = br; r < br + 3; r++) {
                        for (int c = bc; c < bc + 3; c++) {
                            box[k++] = grid[r][c];
                        }
                    }
                    if (hasDup(box)) return true;
                }
            }
            return false;
        }

        private boolean hasDup(int[] arr) {
            boolean[] seen = new boolean[10]; // 1..9
            for (int v : arr) {
                if (v == 0) continue;
                if (seen[v]) return true;
                seen[v] = true;
            }
            return false;
        }

        public void printBoard() {
            System.out.println("    1 2 3   4 5 6   7 8 9");
            System.out.println("  +=======+=======+=======+");
            for (int r = 0; r < 9; r++) {
                System.out.print((r + 1) + " | ");
                for (int c = 0; c < 9; c++) {
                    int v = grid[r][c];
                    char ch = v == 0 ? '.' : (char) ('0' + v);
                    System.out.print(ch + " ");
                    if ((c + 1) % 3 == 0) System.out.print("| ");
                }
                System.out.println();
                if ((r + 1) % 3 == 0) System.out.println("  +=======+=======+=======+");
            }
        }

        public String statusString() {
            if (!started) return "Não iniciado (sem erros)";
            boolean completeCells = true;
            for (int r = 0; r < 9; r++)
                for (int c = 0; c < 9; c++)
                    if (grid[r][c] == 0) completeCells = false;
            boolean conflicts = hasConflicts();
            if (completeCells && !conflicts) return "Completo (sem erros)";
            if (completeCells && conflicts) return "Completo (com erros)"; // tecnicamente completo em células, mas inválido
            return "Incompleto (" + (conflicts ? "com erros" : "sem erros") + ")";
        }
    }

    // ======== Menu interativo ========
    public static void main(String[] args) {
        Sudoku game = new Sudoku();
        Scanner sc = new Scanner(System.in);

        while (true) {
            showMenu();
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1":
                    game.startNewGame(args);
                    System.out.println("Novo jogo iniciado com números FIXOS dos argumentos.");
                    game.printBoard();
                    break;
                case "2":
                    System.out.print("Número (1-9): ");
                    int v = readInt(sc);
                    System.out.print("Índice horizontal (coluna 1-9): ");
                    int c = readInt(sc);
                    System.out.print("Índice vertical (linha 1-9): ");
                    int r = readInt(sc);
                    if (game.placeNumber(r, c, v)) System.out.println("Número inserido!");
                    break;
                case "3":
                    System.out.print("Índice horizontal (coluna 1-9): ");
                    int cRem = readInt(sc);
                    System.out.print("Índice vertical (linha 1-9): ");
                    int rRem = readInt(sc);
                    if (game.removeNumber(rRem, cRem)) System.out.println("Número removido!");
                    break;
                case "4":
                    System.out.println("Situação atual do jogo:");
                    game.printBoard();
                    break;
                case "5":
                    System.out.println("Status: " + game.statusString());
                    break;
                case "6":
                    game.clearUserNumbers();
                    System.out.println("Números do usuário removidos; fixos mantidos.");
                    break;
                case "7":
                    if (game.isComplete()) {
                        System.out.println("Parabéns! Sudoku completo e válido. Jogo encerrado.");
                        return;
                    } else {
                        System.out.println("Ainda não é possível finalizar. Preencha todas as posições corretamente.");
                        System.out.println("Status atual: " + game.statusString());
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("===== MENU SUDOKU =====");
        System.out.println("1. Iniciar novo jogo (usa args do main)");
        System.out.println("2. Colocar um novo número");
        System.out.println("3. Remover um número");
        System.out.println("4. Verificar jogo (visualizar)");
        System.out.println("5. Verificar status do jogo");
        System.out.println("6. Limpar (remove números do usuário)");
        System.out.println("7. Finalizar o jogo");
        System.out.print("Escolha: ");
    }

    private static int readInt(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print("Informe um número inteiro: ");
            }
        }
    }
}
