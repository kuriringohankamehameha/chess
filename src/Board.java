
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

    public Pawn[] pawnArray;
    public Rook[] rookArray;
    public Knight[] knightArray;
    public Bishop[] bishopArray;


    public static ArrayList<Sprite> spriteArrayList = new ArrayList<>(16);

    public static int promotion=0;
    public static int color = 0; //WHITE
    public static int promotion_x;
    public static int promotion_y;

    public static int pawnturns=0;
    public static boolean wchecked = false;

    static King whiteking = new King(-4 + 4*44,7*44,"WHITE",spriteArrayList);
    static King blackking = new King(-4 + 4*44,0,"BLACK",spriteArrayList);

    int alpha = 127/2; // 75% transparent
    Color myColour = new Color(155, 150, 150, alpha);

    public static int getXC(Sprite sp)
    {
        return sp.x;
    }

    public static int getYC(Sprite sp)
    {
        return sp.y;
    }

    public void setupWhitePieces()
    {
        //Sets up white pieces
        int xoffset = -4;
        int xcoord=xoffset;
        int ycoord=0;

        pawnArray = new Pawn[8];
        knightArray = new Knight[2];
        rookArray = new Rook[2];
        bishopArray = new Bishop[2];

        int xpawn = xcoord;
        int ypawns = 6*44;

        for(int i=0; i<8; i++)
        {
            pawnArray[i] = new Pawn((44*i + xoffset),ypawns,"WHITE",spriteArrayList);
            spriteArrayList.add(pawnArray[i]);
        }

        int yrooks = 7*44;

        rookArray[0] = new Rook(xoffset,yrooks,"WHITE",spriteArrayList);
        rookArray[1] = new Rook((40 + 6*44),yrooks, "WHITE",spriteArrayList);

        int yknights = yrooks;

        knightArray[0] = new Knight(xoffset + 44,yknights,"WHITE",spriteArrayList);
        knightArray[1] = new Knight((40 + 5*44),yknights,"WHITE", spriteArrayList);

        int ybishops = yrooks;
        bishopArray[0] = new Bishop(xoffset + 2*44,yknights,"WHITE",spriteArrayList);
        bishopArray[1] = new Bishop((40 + 4*44),yknights,"WHITE", spriteArrayList);

        for(int i=0; i<2; i++) {
            spriteArrayList.add(rookArray[i]);
            spriteArrayList.add(knightArray[i]);
            spriteArrayList.add(bishopArray[i]);
        }

        //Add White King (on black square)
        spriteArrayList.add(whiteking);

        //Add White Queen
        spriteArrayList.add(new Queen(xoffset + 3*44,yrooks,"WHITE",spriteArrayList));


    }

    public void setupBlackPieces()
    {
        //Sets up black pieces
        int xoffset = -4;
        int xcoord=xoffset;
        int ycoord=0;

        pawnArray = new Pawn[8];
        knightArray = new Knight[2];
        rookArray = new Rook[2];
        bishopArray = new Bishop[2];

        int xpawn = xcoord;
        int ypawns = 44;

        for(int i=0; i<8; i++)
        {
            pawnArray[i] = new Pawn((44*i + xoffset),ypawns,"BLACK",spriteArrayList);
            spriteArrayList.add(pawnArray[i]);
        }

        int yrooks = 0;

        rookArray[0] = new Rook(xoffset,yrooks,"BLACK",spriteArrayList);
        rookArray[1] = new Rook((40 + 6*44),yrooks, "BLACK",spriteArrayList);

        int yknights = yrooks;

        knightArray[0] = new Knight(xoffset + 44,yknights,"BLACK",spriteArrayList);
        knightArray[1] = new Knight((40 + 5*44),yknights,"BLACK", spriteArrayList);

        int ybishops = yrooks;
        bishopArray[0] = new Bishop(xoffset + 2*44,yknights,"BLACK",spriteArrayList);
        bishopArray[1] = new Bishop((40 + 4*44),yknights,"BLACK", spriteArrayList);

        for(int i=0; i<2; i++) {
            spriteArrayList.add(rookArray[i]);
            spriteArrayList.add(knightArray[i]);
            spriteArrayList.add(bishopArray[i]);
        }

        //Add Black King (on white square)
        spriteArrayList.add(blackking);

        //Add Black Queen
        spriteArrayList.add(new Queen(xoffset + 3*44,yrooks,"BLACK",spriteArrayList));


    }

    public Board() throws Exception
    {
        ImageIcon b = new ImageIcon("/Users/ramachandran/IdeaProjects/chess+networking/src/chessboard.png");
        bg=b.getImage();

        System.out.println("Board width = "+ bg.getWidth(null) + " ht = " + bg.getHeight(null));

        /*
        //Add BLACK queen
        q=new Queen(xqueen,yqueen - 352/2,"BLACK",spriteArrayList);
        spriteArrayList.add(q);


        //Add BLACK bishop
        bishop_object = new Bishop(xbishop, ybishop - 352/2,"BLACK",spriteArrayList);
        spriteArrayList.add(bishop_object);

        //Add the King object to the SpriteList/spriteArrayList.add(new Sprite(k));
        k = new King(xking, yking,"WHITE",spriteArrayList);
        spriteArrayList.add(k);

        //Add rook
        r = new Rook(xrook, yrook,"WHITE",spriteArrayList);
        spriteArrayList.add(r);

        //Add Knight
        n = new Knight(xknight, yknight, "WHITE",spriteArrayList);
        spriteArrayList.add(n);

        //Add BLACK Pawn
        p = new Pawn(xpawn, ypawn, "BLACK",spriteArrayList);
        spriteArrayList.add(p);
        */

        setupWhitePieces();
        setupBlackPieces();

        Square sq = new Square(5,5);
        Main.f.addMouseListener(sq);

        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                repaint();
            }
        };
        Timer timer = new Timer(80,al);
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

        //Set Graphics Color
        g.setColor(myColour);

        if(promotion==1)
        {
            //System.out.println("Promotion "+promotion_x+ " , "+ promotion_y+ "\n");
            if(color == 0) {
                Queen qe = new Queen(promotion_x, promotion_y, "WHITE", spriteArrayList);
                spriteArrayList.add(qe);
            }
            else
            {
                Queen qe = new Queen(promotion_x, promotion_y, "BLACK", spriteArrayList);
                spriteArrayList.add(qe);
            }
            //System.out.println("New queen added at position "+qe.x+", "+qe.y+"\n");
            //System.out.println(spriteArrayList.get(spriteArrayList.size()-1).label);
            promotion=0;
            Main.addListenertoend(Main.f,spriteArrayList);
        }

        for(int i=0; i<spriteArrayList.size(); i++)
        {
            if(spriteArrayList.get(i).visible)
            g.drawImage(spriteArrayList.get(i).image, spriteArrayList.get(i).x,spriteArrayList.get(i).y, this);
            //Draw choices, if any
            if(spriteArrayList.get(i).setChoiceVisible) {
                for(int j=0;j<spriteArrayList.get(i).moveSetQueenx.size();j++)
                g.fillRect(spriteArrayList.get(i).moveSetQueenx.get(j), spriteArrayList.get(i).moveSetQueeny.get(j), 44, 44);
            }
        }

    }

    public void doDrawing(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
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
