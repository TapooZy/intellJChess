public class  Main {

    public static void printMoves(Piece piece, Board board){
        Queue<Integer> moves = piece.getPossibleMoves(board);
        int size = moves.getSize();
        int[] arr;
        if (size == 0){
            System.out.println("There are no available moves");
        }
        else {
            for (int i = 0; i < size; i++) {
                arr = moves.remove();
                System.out.print("(" + arr[0] + "," + arr[1] + ")" +"\t");
            }
        }
    }

    public static void printAllMoves(Piece piece, Board board, boolean doYouWantSame){
        Queue<Integer> moves;
        if (doYouWantSame) {
            moves = board.getSameColorMoves(piece);
        }
        else {
            moves = board.getDifferentColorMoves(piece);
        }
        int size = moves.getSize();
        int[] arr;
        if (size == 0){
            System.out.println("There are no available moves");
        }
        else {
            for (int i = 0; i < size; i++) {
                arr = moves.remove();
                System.out.print("(" + arr[0] + "," + arr[1] + ")" +"\t");
            }
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
//        board.getBoard()[0][4] = new Queen('w', 0, 4);
//        board.getBoard()[0][0] = new King('w', 0, 0);
//        board.getBoard()[4][7] = new Pawn('b', 4, 7);
        board.getBoard()[6][4] = new Pawn('b', 6, 4);
        board.getBoard()[6][7] = new Queen('w', 6, 7);
        board.getBoard()[7][7] = new King('b', 7, 4);
        board.getBoard()[5][7] = new Rook('b', 7, 0);
//        board.getBoard()[0][4] = new King('b', 0, 4);
//        board.getBoard()[3][4] = new Pawn('b', 5, 4);
//        board.printBoard();
//        board.getBoard()[6][6].move(board);
//        printMoves(board.getBoard()[4][7], board);
//        board.getBoard()[4][7].move(board);
//        printMoves(board.getBoard()[3][2], board);
//        printMoves(board.getBoard()[7][4], board);
//        board.getBoard()[2][4].move(board);
//        printMoves(board.getBoard()[4][4], board);
//        board.getBoard()[4][4].move(board);
        board.printBoard();
        printMoves(board.getBoard()[6][7], board);

    }
}