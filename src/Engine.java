import java.util.*;

public class Engine {
    private Board board;

    public Engine(){
        this.board = new Board();
        board.startGame();
    }

    public void game(Board board){
        int Piece, Move, row, col;
        String color = "White";
        Piece piece;
        Scanner in = new Scanner(System.in);
        while(board.findKing('b') != null && board.findKing('w') != null){
            System.out.println(color + " move");
            while (true) {
                System.out.println("Which piece do you want to move");
                Piece = in.nextInt();
                row = Piece / 10;
                col = Piece % 10;
                if (board.getBoard()[row][col] != null){
                    piece = board.getBoard()[row][col];
                    break;
                }
                else {
                    System.out.println("This square is empty");
                }
            }
            while (true) {
                System.out.println("Where");
                Move = in.nextInt();
                row = Move / 10;
                col = Move % 10;
                if (piece.move(board, row, col)){
                    break;
                }
                else{
                    System.out.println("This is an illegal move");
                }
            }
            if (color.equals("White")){
                color = "Black";
            }
            else {
                color = "White";
            }
        }
    }
}
