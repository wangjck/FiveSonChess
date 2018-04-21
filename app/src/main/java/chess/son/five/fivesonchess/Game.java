package chess.son.five.fivesonchess;

public class Game {
    public static final int BOARDSIZE = 19;
    public static final int WINNING = 5;
    private int[][] board;
    public boolean ended = false;
    public int winner = 0;
    public Game() {
        board = new int[BOARDSIZE][BOARDSIZE];
        for (int[] i:board) {
            for (int j:i) {
                j = 0;
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }
    public boolean move(int x, int y, int playerid) {
        if (x < 0 || y < 0 || x >= BOARDSIZE || y >= BOARDSIZE) {
            return false;
        }
        if (board[x][y] != 0) {
            return false;
        }
        board[x][y] = playerid;
        int lr = 1;
        int ud = 1;
        int lurd = 1;
        int ldru = 1;
        for (int i = 1; i < WINNING; i++) {
            if (x + i >= 0 && x + i < BOARDSIZE && board[x + i][y] == playerid) {
                lr++;
            } else {
                break;
            }
        }
        for (int i = 1; i < WINNING; i++) {
            if (x - i >= 0 && x - i < BOARDSIZE && board[x - i][y] == playerid) {
                lr++;
            } else {
                break;
            }
        }
        for (int i = 1; i < WINNING; i++) {
            if (y + i >= 0 && y + i < BOARDSIZE && board[x][y + i] == playerid) {
                ud++;
            } else {
                break;
            }
        }
        for (int i = 1; i < WINNING; i++) {
            if (y - i >= 0 && y - i < BOARDSIZE && board[x][y - i] == playerid) {
                ud++;
            } else {
                break;
            }
        }
        for (int i = 1; i < WINNING; i++) {
            if (x + i >= 0 && x + i < BOARDSIZE && y + i >= 0 && y + i < BOARDSIZE && board[x + i][y + i] == playerid) {
                lurd++;
            } else {
                break;
            }
        }
        for (int i = 1; i < WINNING; i++) {
            if (x - i >= 0 && x - i < BOARDSIZE && y - i >= 0 && y - i < BOARDSIZE && board[x - i][y - i] == playerid) {
                lurd++;
            } else {
                break;
            }
        }
        for (int i = 1; i < WINNING; i++) {
            if (x + i >= 0 && x + i < BOARDSIZE && y - i >= 0 && y - i < BOARDSIZE && board[x + i][y - i] == playerid) {
                ldru++;
            } else {
                break;
            }
        }
        for (int i = 1; i < WINNING; i++) {
            if (x - i >= 0 && x - i < BOARDSIZE && y + i >= 0 && y + i < BOARDSIZE && board[x - i][y + i] == playerid) {
                ldru++;
            } else {
                break;
            }
        }
        if (lr >= WINNING || ud >= WINNING || ldru >= WINNING || lurd >= WINNING) {
            winner = playerid;
            ended = true;
        }
        return true;
    }
}
