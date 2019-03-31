/**
 * A square is a board with rowsize = colsize = 1
 * A square can have a piece
 * A square cannot be deleted, but a piece can
 */
public class Square extends Board{

    public boolean hasSprite = false;
    public int row_index;
    public int col_index;
    public Sprite sprite;

    public Square(int x, int y) throws Exception
    {
        //When the board is initialized at the very beginning, 8*8 squares are created with hasPiece = false
        this.hasSprite = false;
        this.row_index = x;
        this.col_index = y;
        this.sprite = null;

    }

    public Sprite createSprite(String str, int x, int y)
    {
        //Sets piece p to the square based on its type

        /**Creates a new Piece of PieceType
         */
        Sprite sp = new Sprite();
        if(str.equals("QUEEN"))
            this.sprite = new Queen(x,y);
        this.hasSprite = true;
        this.row_index = y;
        this.col_index = x;

        return sp;
    }
    public void deleteSprite()
    {
        //Deletes piece at current position
        this.hasSprite = false;
        this.sprite = null;
    }

    public void deleteSprite(Square from)
    {
        //Deletes piece at position (x,y) on the board

        //board[x][y].hasPiece = false; //Don't need to delete the corresponding piece object. If in the case that a piece is created at that location, simply replace.

        //assert !board[x][y].hasPiece;

        //from -> to piece capture and replace
        this.sprite = from.sprite;
        from.hasSprite = false;
        from.sprite = null;


    }

}
