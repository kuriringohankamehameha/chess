/**
    A piece is a class that can be placed on a square.
    A piece must have a position on the board/square
    But it can be removed from the board/square
 */

public class Piece{

    enum PieceType
    {
        PAWN, KING, QUEEN, KNIGHT, ROOK, BISHOP
    }

    public PieceType ptype;


    public Piece()
    {

    }

    public Piece(String str)
    {
        /**Creates a new Piece of PieceType
         */

         if(str.equals("KING"))
        {

            this.ptype = PieceType.KING;
        }
        else if(str.equals("QUEEN"))
        {
            this.ptype = PieceType.QUEEN;
        }
        else if(str.equals("KNIGHT"))
        {
            this.ptype = PieceType.KNIGHT;
        }
        else if(str.equals("ROOK"))
        {
            this.ptype = PieceType.ROOK;
        }
        else if(str.equals("PAWN"))
        {
            this.ptype = PieceType.PAWN;
        }
        else if(str.equals("BISHOP"))
        {
            this.ptype = PieceType.BISHOP;
        }


    }

    public void removePiece()
    {
        /**
         * Removes the piece from the board
         */
    }

    public boolean movePiece(Square from, Square to)
    {
        /**
         * Moves a piece on the FROM square to the TO square
         * A piece can move to a location where there is another piece too, provided that piece is in the opposite color
         * I also need to place the image icon onto the frame and move it as well
         */

        assert from.hasPiece;

        boolean status = false;

        return status;

    }
    /*
    public boolean isAlive()
    {
        //Since Board is a super class, we can involve Board by super()
        boolean status = false;

        for(int i=0;i<super.ROWS;i++)
        {
            for(int j=0;j<super.COLS;j++)
            {
                if(board[i][j])
            }
        }
        return status;

    }
    */
}
