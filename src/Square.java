/**
 * A square is a board with rowsize = colsize = 1
 * A square can have a piece
 * A square cannot be deleted, but a piece can
 */
public class Square extends Board{

    public boolean hasPiece = false;
    public Piece piece;
    public int row_index;
    public int col_index;

    public Square(int x, int y) throws Exception
    {
        //When the board is initialized at the very beginning, 8*8 squares are created with hasPiece = false
        this.hasPiece = false;
        this.row_index = x;
        this.col_index = y;

    }

    public Piece createPiece(String str, int x, int y)
    {
        //Sets piece p to the square based on its type

        /**Creates a new Piece of PieceType
         */
        Piece p = new Piece(str);
        this.hasPiece = true;
        assert this.hasPiece;
        this.piece = p;
        this.row_index = y;
        this.col_index = x;

        return p;
    }

    public void deletePiece(int x, int y)
    {
        //Deletes piece at position (x,y) on the board

        board[x][y].hasPiece = false; //Don't need to delete the corresponding piece object. If in the case that a piece is created at that location, simply replace.

        assert !board[x][y].hasPiece;

    }

}
