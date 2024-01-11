import java.util.Scanner;

public class Queen extends Piece{

    public Queen(char color, int row, int col){
        super(color, 'q', row, col);
    }

    public Queue<Integer> getPossibleMoves(Board board) {
        Queue<Integer> moves = new Queue<>();
        Board newBoard = board.clone();
        newBoard.getBoard()[row][col] = new Rook(color, row, col);
        Queue<Integer> RookMoves = newBoard.getBoard()[row][col].getPossibleMoves(newBoard);
        newBoard.getBoard()[row][col] = new Bishop(color, row, col);
        Queue<Integer> BishopMoves = newBoard.getBoard()[row][col].getPossibleMoves(newBoard);
        int RookMovesSize = RookMoves.getSize();
        int[] individualMove;
        for (int i = 0; i < RookMovesSize; i++) {
            individualMove = RookMoves.remove();
            moves.insert(individualMove[0], individualMove[1]);
        }
        int BishopMovesSize = BishopMoves.getSize();
        for (int i = 0; i < BishopMovesSize; i++) {
            individualMove = BishopMoves.remove();
            moves.insert(individualMove[0], individualMove[1]);
        }
        return moves;
    }

    @Override
    public void move(Board board){
        Scanner in = new Scanner(System.in);
        int rowCol, desirableRow, desirableCol, size, formerRow, formerCol;
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
                formerRow = this.row;
                formerCol = this.col;
                setRow(desirableRow);
                setCol(desirableCol);
                board.getBoard()[formerRow][formerCol] = null;
                if (!didMove){
                    didMove = true;
                }
                board.getBoard()[desirableRow][desirableCol] = this;
                board.printBoard();
                for (int k = 0; k < 7; k++) {
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
