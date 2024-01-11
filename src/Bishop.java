import java.util.Scanner;

public class Bishop extends Piece {

    public Bishop(char color, int row, int col) {
        super(color, 'b', row, col);
    }

    @Override
    public Queue<Integer> getPossibleMoves(Board board) {
        Queue<Integer> moves = new Queue<>();
        int row1, col1;
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int i = 0; i < 4; i++) {
            row1 = row + directions[i][0];
            col1 = col + directions[i][1];
            while (row1 >= 0 && row1 <= 7 && col1>= 0 && col1 <= 7){
                Piece piece = board.getBoard()[row1][col1];
                if (piece == null){
                    moves.insert(row1, col1);
                }
                else if (piece.getColor() != color){
                    moves.insert(row1, col1);
                    break;
                }
                else break;
                row1 += directions[i][0];
                col1 += directions[i][1];
            }
        }
        return moves;
    }

    @Override
    public void move(Board board){
        Scanner in = new Scanner(System.in);
        int rowCol, desirableRow, desirableCol, size;
        rowCol = in.nextInt();
        desirableRow = rowCol/10;
        desirableCol = rowCol%10;
        int[] desirableMove = {desirableRow, desirableCol};
        int[] availableMove;
        Queue<Integer> moves = board.getBoard()[this.row][this.col].getPossibleMoves(board);
        size = moves.getSize();
        for (int i = 0; i < size; i++) {
            availableMove = moves.remove();
            if (desirableMove[0] == availableMove[0] && desirableMove[1] == availableMove[1]){
                board.getBoard()[desirableRow][desirableCol] = this;
                board.getBoard()[this.row][this.col] = null;
                board.printBoard();
                for (int k = 0; k < 7; i++) {
                    for (int j = 0; j < 7; j++) {
                        if (board.getBoard()[k][j] != null){
                            if (board.getBoard()[k][j].getWasFirstMove()){
                                board.getBoard()[k][j].setWasFirstMove(false);
                            }
                        }
                    }
                }
                break;
            }
        }
        System.out.println("This is not a possible move");
    }

    @Override
    public void move(Board board, int row, int col){
        int formerRow, formerCol;
        formerRow = this.row;
        formerCol = this.col;
        setRow(row);
        setCol(col);
        board.getBoard()[formerRow][formerCol] = null;
        board.getBoard()[row][col] = this;
    }
}
