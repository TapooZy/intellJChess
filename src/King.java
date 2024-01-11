import java.util.Scanner;

public class King extends Piece {

    public King(char color, int row, int col) {
        super(color, 'K', row, col);
    }

    public Queue<Integer> getPossibleMoves(Board board) {
        int row1, col1;
        Queue<Integer> moves = new Queue<>();
        int[][] directions = {{1, 0},{-1,0},{0,1},{0,-1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int i = 0; i < 8; i++) {
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
                row1 = row + directions[i][0];
                col1 = row + directions[i][1];
            }
        }

        if (canCastleLeft(board)){
            moves.insert(row, col-2);
        }
        if (canCastleRight(board)){
            moves.insert(row, col+2);
        }
        return moves;

    }

    public boolean canCastleRight (Board board)
    {
        Queue<Integer> allMoves = board.getDifferentColorMoves(board.getBoard()[row][col]);
        int[] individualMove;
        if (board.getBoard()[row][7] != null)
        {
            if (board.getBoard()[row][7].getLetter() == 'r')
            {
                if (!board.getBoard()[row][7].getDidMove())
                {
                    for (int i = 0; i < allMoves.getSize(); i++)
                    {
                        individualMove = allMoves.remove();
                        for (int col = 5; col < 7; col++)
                        {
                            if (board.getBoard()[row][col] != null)
                            {
                                return false;
                            }
                            if (individualMove[0] == row && individualMove[1] == col)
                            {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canCastleLeft (Board board){
        Queue<Integer> allMoves = board.getDifferentColorMoves(board.getBoard()[row][col]);
        int[] individualMove;
        if (board.getBoard()[row][0] != null)
        {
            if (board.getBoard()[row][0].getLetter() == 'r')
            {
                if (!board.getBoard()[row][0].getDidMove())
                {
                    for (int i = 0; i < allMoves.getSize(); i++)
                    {
                        individualMove = allMoves.remove();
                        for (int col = 3; col > 0; col--)
                        {
                            if (board.getBoard()[row][col] != null)
                            {
                                return false;
                            }
                            if (individualMove[0] == row && individualMove[1] == col)
                            {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void move(Board board) {
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
