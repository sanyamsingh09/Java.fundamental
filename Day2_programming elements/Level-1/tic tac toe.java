import java.util.*;

public class TicTacToeGame {
    private char[] board = new char[10]; // index 0 ignored
    private char playerLetter, computerLetter;
    private boolean playerTurn;
    private Scanner sc = new Scanner(System.in);

    // UC 1 - Initialize board
    public void createBoard() {
        for (int i = 1; i < 10; i++) board[i] = ' ';
    }

    // UC 2 - Input X or O
    public void chooseLetter() {
        System.out.print("Choose X or O: ");
        while (true) {
            char letter = Character.toUpperCase(sc.next().charAt(0));
            if (letter == 'X' || letter == 'O') {
                playerLetter = letter;
                computerLetter = (letter == 'X') ? 'O' : 'X';
                break;
            } else {
                System.out.print("Invalid input. Choose X or O: ");
            }
        }
    }

    // UC 3 - Show Board
    public void showBoard() {
        System.out.println("\n" +
            " " + board[1] + " | " + board[2] + " | " + board[3] + "\n" +
            "---+---+---\n" +
            " " + board[4] + " | " + board[5] + " | " + board[6] + "\n" +
            "---+---+---\n" +
            " " + board[7] + " | " + board[8] + " | " + board[9] + "\n");
    }

    // UC 4 - Is space free
    public boolean isSpaceFree(int index) {
        return board[index] == ' ';
    }

    // UC 5 - Make move
    public void makeMove(int index, char letter) {
        board[index] = letter;
    }

    // UC 6 - Toss for first move
    public void doToss() {
        System.out.print("Head or Tail? (H/T): ");
        String userCall = sc.next().toUpperCase();
        String toss = (new Random().nextBoolean()) ? "H" : "T";
        playerTurn = userCall.equals(toss);
        if(playerTurn)
            System.out.println("Player won the toss. Player starts.");
        else
            System.out.println("Computer won the toss. Computer starts.");
    }

    // UC 7 - Win logic
    public boolean isWinner(char letter) {
        return ((board[1] == letter && board[2] == letter && board[3] == letter) ||
                (board[4] == letter && board[5] == letter && board[6] == letter) ||
                (board[7] == letter && board[8] == letter && board[9] == letter) ||
                (board[1] == letter && board[4] == letter && board[7] == letter) ||
                (board[2] == letter && board[5] == letter && board[8] == letter) ||
                (board[3] == letter && board[6] == letter && board[9] == letter) ||
                (board[1] == letter && board[5] == letter && board[9] == letter) ||
                (board[3] == letter && board[5] == letter && board[7] == letter));
    }

    public boolean isBoardFull() {
        for(int i=1;i<10;i++) if(board[i]==' ') return false;
        return true;
    }

    // UC 8 & UC 9 - Computer move: win/block/player
    public int getComputerMove() {
        // Try to win
        for (int i = 1; i < 10; i++) {
            if (isSpaceFree(i)) {
                board[i] = computerLetter;
                if (isWinner(computerLetter)) {
                    board[i] = ' ';
                    return i;
                }
                board[i] = ' ';
            }
        }
        // Block player win
        for (int i = 1; i < 10; i++) {
            if (isSpaceFree(i)) {
                board[i] = playerLetter;
                if (isWinner(playerLetter)) {
                    board[i] = ' ';
                    return i;
                }
                board[i] = ' ';
            }
        }
        // Take corners
        int[] corners = {1,3,7,9};
        for (int corner : corners)
            if (isSpaceFree(corner)) return corner;
        // Take center
        if (isSpaceFree(5)) return 5;
        // Take remaining
        for (int i = 1; i < 10; i++)
            if (isSpaceFree(i)) return i;
        return 0;
    }

    public void play() {
        createBoard();
        chooseLetter();
        doToss();
        showBoard();

        while (true) {
            if (playerTurn) {
                System.out.print("Enter position (1-9): ");
                int move = sc.nextInt();
                if (move < 1 || move > 9 || !isSpaceFree(move)) {
                    System.out.println("Invalid move.");
                    continue;
                }
                makeMove(move, playerLetter);
                showBoard();
                if (isWinner(playerLetter)) {
                    System.out.println("Player Wins!");
                    break;
                }
            } else {
                int move = getComputerMove();
                makeMove(move, computerLetter);
                System.out.println("Computer chooses " + move);
                showBoard();
                if (isWinner(computerLetter)) {
                    System.out.println("Computer Wins!");
                    break;
                }
            }
            if(isBoardFull()) {
                System.out.println("It's a Tie!");
                break;
            }
            playerTurn = !playerTurn;
        }
    }

    public static void main(String[] args) {
        new TicTacToeGame().play();
    }
}
Every use case is implemented as its own method.

The main flow is clear and follows UC progression, and includes modular validation, board display, input, and AI logic.

Follows coding, naming, and modularization guidelines as specified in the workshop definition.Here is Java code structured according to the workshop problem definition provided in the PDF. Each feature is modularized into its own method, use case by use case, as instructed:

java
import java.util.*;

public class TicTacToeGame {
    private char[] board = new char[10]; // 1-based index; 0 ignored
    private char playerLetter, computerLetter;
    private boolean playerTurn;
    private Scanner sc = new Scanner(System.in);

    // UC 1: Create board
    void createBoard() {
        for (int i = 1; i < 10; i++) board[i] = ' ';
    }

    // UC 2: Choose X or O
    void chooseLetter() {
        char letter;
        do {
            System.out.print("Choose X or O: ");
            letter = Character.toUpperCase(sc.next().charAt(0));
        } while (letter != 'X' && letter != 'O');
        playerLetter = letter;
        computerLetter = (playerLetter == 'X') ? 'O' : 'X';
    }

    // UC 3: Display board
    void showBoard() {
        System.out.println(String.format(
            "\n %c | %c | %c\n---+---+---\n %c | %c | %c\n---+---+---\n %c | %c | %c\n",
            board[1], board[2], board[3], board[4], board[5], board[6], board[7], board[8], board[9]));
    }

    // UC 4: Check for free cell
    boolean isSpaceFree(int index) {
        return (index>=1 && index<=9) && board[index] == ' ';
    }

    // UC 5: Make a move
    void makeMove(int index, char letter) {
        board[index] = letter;
    }

    // UC 6: Toss
    void doToss() {
        System.out.print("Head or Tail? (H/T): ");
        String call = sc.next().toUpperCase();
        String toss = (new Random().nextBoolean()) ? "H" : "T";
        playerTurn = call.equals(toss);
        System.out.println((playerTurn ? "Player" : "Computer") + " starts.");
    }

    // UC 7: Check for win/tie
    boolean isWinner(char l) {
        return  (board[1]==l && board[2]==l && board[3]==l) ||
                (board[4]==l && board[5]==l && board[6]==l) ||
                (board[7]==l && board[8]==l && board[9]==l) ||
                (board[1]==l && board[4]==l && board[7]==l) ||
                (board[2]==l && board[5]==l && board[8]==l) ||
                (board[3]==l && board[6]==l && board[9]==l) ||
                (board[1]==l && board[5]==l && board[9]==l) ||
                (board[3]==l && board[5]==l && board[7]==l);
    }
    boolean isBoardFull() {
        for (int i = 1; i < 10; i++) if (board[i]==' ') return false;
        return true;
    }

    // UC 8/9: Computer's move (win-block-random)
    int getComputerMove() {
        // Try to win
        for (int i = 1; i < 10; i++) {
            if (isSpaceFree(i)) {
                board[i] = computerLetter;
                if (isWinner(computerLetter)) { board[i] = ' '; return i; }
                board[i] = ' ';
            }
        }
        // Try to block
        for (int i = 1; i < 10; i++) {
            if (isSpaceFree(i)) {
                board[i] = playerLetter;
                if (isWinner(playerLetter)) { board[i] = ' '; return i; }
                board[i] = ' ';
            }
        }
        // Corners
        for (int i : new int[] {1,3,7,9})
            if (isSpaceFree(i)) return i;
        // Center
        if (isSpaceFree(5)) return 5;
        // Sides
        for (int i : new int[] {2,4,6,8})
            if (isSpaceFree(i)) return i;
        return 0;
    }

    public void play() {
        createBoard();
        chooseLetter();
        doToss();
        showBoard();

        while (true) {
            if (playerTurn) {
                int move;
                do {
                    System.out.print("Enter position (1-9): ");
                    move = sc.nextInt();
                } while (!isSpaceFree(move));
                makeMove(move, playerLetter);
                showBoard();
                if (isWinner(playerLetter)) { System.out.println("Player Wins!"); break; }
            } else {
                int move = getComputerMove();
                makeMove(move, computerLetter);
                System.out.println("Computer chooses " + move);
                showBoard();
                if (isWinner(computerLetter)) { System.out.println("Computer Wins!"); break; }
            }
            if (isBoardFull()) { System.out.println("Tie!"); break; }
            playerTurn = !playerTurn;
        }
    }

    public static void main(String[] args) {
        new TicTacToeGame().play();
    }
}