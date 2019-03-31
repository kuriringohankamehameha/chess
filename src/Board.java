
/*
import javafx.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
*/
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Board conatins an 8x8 grid with 2 partitions
 * for the ranks of Black and White pieces
 *
 * It has files a-h vertically and ranks 1-8 horizontally
 */

public class Board extends JPanel {

    public static final int DEFAULT_SIDES = 8;
    public static final int ROWS = DEFAULT_SIDES;
    public static final int COLS = DEFAULT_SIDES;
    public Square[][] board = new Square[DEFAULT_SIDES][DEFAULT_SIDES]; //Is this allowed?

    //Pieces
    public Image wking;
    public Image bishop;
    public Image bg;
    public Image queen;
    public Image queen2;


    int xbishop=40 + 352/8;
    int ybishop= 352 - 352/8;

    int xqueen=xbishop+352/8;
    int yqueen=ybishop;

    int xking = xqueen + 352/4;
    int yking = yqueen - 352/2;

    int xrook = xbishop - 352/8;
    int yrook = ybishop - 352/4;

    int xknight = xrook + 352/8;
    int yknight = yrook;

    int xpawn = xking + 352/8;
    int ypawn = yking;

    public Queen q;
    public Bishop bishop_object;
    public King k;
    public Rook r;
    public Knight n;
    public Pawn p;

    public static ArrayList<Sprite> spriteArrayList = new ArrayList<>(16);


    public Board() throws Exception
    {
        //Initialises 64 squares into an array board[][]

        /* NOT WORKING
        for(int i=0; i<DEFAULT_SIDES; i++)
        {
            for(int j=0; j<DEFAULT_SIDES; j++)
            {
                //Create square
                board[i][j] = new Square(i,j);

            }
        }
        */


        /**
         *
         */



        ImageIcon b = new ImageIcon("/Users/ramachandran/IdeaProjects/chess+networking/src/chessboard.png");
        bg=b.getImage();

        System.out.println("Board width = "+ bg.getWidth(null) + " ht = " + bg.getHeight(null));

        //ImageIcon ibishop = new ImageIcon("/Users/ramachandran/IdeaProjects/chess+networking/src/images/wbishop.png");
        //bishop = ibishop.getImage();
        //bishop = bishop.getScaledInstance(50, 50, Image.SCALE_DEFAULT);

        //Bishop : Image, x, y, width?, height?, visible?,type?
        //Create sprite for bishop and add it into the arrayList

        //spriteArrayList.add(new Sprite(xbishop, ybishop, bishop, "BISHOP"));

        //Simple displays image of the queen
        //ImageIcon iqueen = new ImageIcon("/Users/ramachandran/IdeaProjects/chess+networking/src/images/wqueen.png");
        //queen = iqueen.getImage();

        //queen = queen.getScaledInstance(50, 50, Image.SCALE_DEFAULT);


        q=new Queen(xqueen,yqueen - 352/2,spriteArrayList);
        spriteArrayList.add(q);


        //System.out.println(spriteArrayList + ", " + spriteArrayList.get(0).x);

        bishop_object = new Bishop(xbishop, ybishop - 352/2,spriteArrayList);
        spriteArrayList.add(bishop_object);

        //Add the King object to the SpriteList/spriteArrayList.add(new Sprite(k));
        k = new King(xking, yking,spriteArrayList);
        spriteArrayList.add(k);

        //Add rook
        r = new Rook(xrook, yrook,spriteArrayList);
        spriteArrayList.add(r);

        //Add Knight
        n = new Knight(xknight, yknight, spriteArrayList);
        spriteArrayList.add(n);

        //Add Pawn
        p = new Pawn(xpawn, ypawn, spriteArrayList);
        spriteArrayList.add(p);



        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                repaint();
                //System.out.println("Action Performed\n");

                //paintImmediately(q.x-2,q.y-2,q.image.getWidth(null),q.image.getHeight(null));
                //paintImmediately(q);
            }
        };
        Timer timer = new Timer(80,al);
        timer.start();

    }

    public void add(ArrayList list)
    {

    }

    public void paintImmediately(Queen queen) {
        //Paints the new position of the Queen
        int offset = q.offset;
        super.paintImmediately(queen.x - 2, queen.y - 2, queen.image.getWidth(null), queen.image.getHeight(null));
    }

    public void paintImmediately(Bishop bishop) {
        //Paints the new position of the Bishop
        super.paintImmediately(bishop.x - 2, bishop.y - 2, bishop.image.getWidth(null), bishop.image.getHeight(null));
    }

    public void updateBoard(Piece p)    //DOES NOT WORK
    {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                q.move(0,5);
                System.out.println("Queen has moved\n");
                repaint();
            }
        };
        Timer timer = new Timer(50,al);
        timer.start();

    }

    private void step(Queen q, Square from, Square to) {

        q.move(from,to);

        repaint(q.getX()-1, q.getY()-1,
                q.image.getWidth(null)+2, q.image.getHeight(null)+2);
    }


    public Square getSquare(int x, int y)
    {
        /**
         * Gets the square corresponding to its position on the array
         */

        return this.board[x][y];

    }

    public boolean isClearVertical(Square from, Square to)
    {
        /**
         * Checks whether the vertical traversal is clear
         */
        assert (from.col_index == to.col_index);
        int col = from.col_index;
        for(int i=from.row_index; i<=to.row_index; i++)
        {
            if(board[i][col].hasSprite)
                return false;

        }

        return true;

    }

    public boolean isClearHorizontal(Square from, Square to)
    {
        /**
         * Checks whether the horizontal traversal is clear
         */
        assert (from.row_index == to.row_index);
        int row = from.row_index;
        for(int i=from.col_index; i<=to.col_index; i++)
        {
            if(board[row][i].hasSprite)
                return false;

        }

        return true;


    }

    public boolean isClearDiagonal(Square from, Square to)
    {
        //For now, it just needs to work. Idc about Duplication

       assert ((from.row_index + from.col_index) == (to.row_index + to.col_index)) && (from.row_index != to.row_index) && (from.col_index != to.col_index);
       int from_row = from.row_index;
       int from_col =from.col_index;

       int to_row = to.row_index;
       int to_col = to.col_index;

       if(from_row < to_row)
       {
           //Increment i
           for(int i=from_row; i<=to_row; i++)
           {
               if(from_col < to_col)
               {
                   for(int j=from_col; j<=to_col; j++)
                   {
                       if(board[i][j].hasSprite)
                           return false;

                   }
               }
               else
               {
                   for(int j=from_col; j>=to_col; j--)
                   {
                       if(board[i][j].hasSprite)
                           return false;

                   }
               }

           }

       }
       else
       {
           //Decrement i
           for(int i=from_row; i>=to_row; i--)
           {
               if(from_col < to_col)
               {
                   for(int j=from_col; j<=to_col; j++)
                   {
                       if(board[i][j].hasSprite)
                           return false;

                   }
               }
               else
               {
                   for(int j=from_col; j>=to_col; j--)
                   {
                       if(board[i][j].hasSprite)
                           return false;

                   }
               }

           }

       }

       return true;

    }

    public boolean isEndRow(Square location)
    {
        return (location.row_index == 0 || location.row_index == DEFAULT_SIDES-1);
    }

    public boolean isEndCol(Square location)
    {
        return (location.col_index == 0 || location.col_index == DEFAULT_SIDES-1);
    }

    private void loadImage()
    {
        ImageIcon ii = new ImageIcon("/Users/ramachandran/IdeaProjects/chess+networking/src/images/wking.png");
        wking = ii.getImage();
    }


    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, null);

        //dbImage = createImage(getWidth(), getHeight());
        //dbg = dbImage.getGraphics();
        //paintComponent(dbg);


        //g.drawImage(bishop,xbishop, ybishop, this);

        //g.drawImage(queen, xqueen, yqueen, this);

        g.setColor(Color.WHITE);

        for(int i=0; i<spriteArrayList.size(); i++)
        {
            if(spriteArrayList.get(i).visible)
            g.drawImage(spriteArrayList.get(i).image, spriteArrayList.get(i).x,spriteArrayList.get(i).y, this);
        }

       // g.drawImage(spriteArrayList.get(0).image, q.x, q.y, this);

        //g.drawImage(bishop_object.image,bishop_object.x,bishop_object.y, this);

        //g.drawImage(k.image, xking, yking, this);

        //g.drawImage(ghost, x, y, this);
        //System.out.println("Bishop painted\n");

    }

    public void doDrawing(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        //g2d.drawImage(bishop.getImage(), bishop.getX(),bishop.getY(), this);
    }


    public void initboard() throws IOException
    {
        try {

            //BufferedImage img = ImageIO.read(new File("/Users/ramachandran/IdeaProjects/chess+networking/src/chessboard.png"));
            //ImageIcon icon = new ImageIcon(img);

            JFrame frame = new JFrame();
            frame.setLayout(new FlowLayout());
            frame.setSize(380, 380);
            JLabel lbl = new JLabel();
            //lbl.setIcon(icon);
            frame.add(lbl);

            frame.setVisible(true);
            frame.setResizable(true);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        catch(Exception ex)
        {
            System.err.print("ERROR: File containing board not found:\n");
            ex.printStackTrace();
            System.exit(1);

        }

    }

}
