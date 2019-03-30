import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.lang.Math.abs;

public class Queen extends Sprite implements MouseListener {

    public int offset;
    public boolean choice = false;
    public int[][] moveset;

    public Queen(int x,int y)
    {
        super(x,y);
        super.type = "QUEEN";

        initQueen();
    }

    public void initQueen()
    {
        loadImage("/Users/ramachandran/IdeaProjects/chess+networking/src/images/wqueen.png");
        this.image=this.image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        getImageDimensions();

        //Initialize the moveset of the queen
        //From it's position, check horizontal, vertical and diagonal

        //for(int i=0; i<)
    }

    public void move(Square from, Square to)
    {
        int row = from.row_index - to.row_index;
        y+=row;

        int col = from.col_index - to.col_index;
        x+=col;

        if(x > 8 ||y > 8) {
            visible = false;
        }

    }

    public void move(int from, int to)
    {
        y+=0;

        x+=to-from;

        if(x > 8 ||y > 8) {
            visible = false;
        }

        this.offset = (to-from)/2;

    }

    public void mousePressed(MouseEvent e)
    {
        //move(0,5);
    }

    public void mouseClicked(MouseEvent e) {
        //System.out.println("Mouse clicked\n");
        int xc = e.getX();
        int yc = e.getY();
        int xoffset = -25;
        int yoffset = -55;
        xc+=xoffset;
        yc+=yoffset;
        int move[] = new int[2];

        int choice = 0;
        int decision = 0;

        //this.choice = !(this.choice);

        this.choice = false;
        //System.out.println("Choice is " + this.choice + "\n");

        /*
        System.out.println("Mouse Coordinates are (" + xc + " , " + yc + ") and object position is (" + this.x +", " + this.y + ")\n");
        */

        //PROBLEM WITH BELOW PORTION
        if (xc <= this.x+5 && xc >=this.x-5 && yc <= this.y+5 && yc >= this.y-5) {
            //moveHorizontally(0, 352 / 8);
            if (!this.choice) {
                //Making a choice, i.e DO NOT MOVE
                move = makeChoice(xc, yc);
                decision = 0;
                choice = 1;
                System.out.println("Choice made\n");

            }
        }

        //Movement works, but I must restrict it to center of squares only
            if(choice == 1)
            {
                //Choice is made and you can move
                if((this.y <= yc+5 && this.y >=yc-5 )&& !(this.x <= xc+5 & this.x >= xc-5)) {
                    moveHorizontally(this.x, xc);
                    System.out.println("Moving Horizontally\n");
                    decision = 0;

                    return ;
                }

                if((this.x <= xc+5 && this.x >=xc-5 )&& !(this.y <= yc+5 & this.y >= yc-5)) {
                    moveVertically(this.y, yc);
                    decision = 1;
                    System.out.println("Moving Vertically\n");

                    return ;
                }

                if(abs(this.x - xc)<=abs(this.y-yc)+5 && abs(this.x - xc)<=abs(this.y - yc)-5)
                {
                    moveDiagonally(this.x, this.y, xc, yc);
                    decision = 2;
                    System.out.println("Moving Diagonally\n");

                    return ;
                }

                /**
                 * TODO: Highlight available squares based on decision value on the board (??)
                 */

            }


        }

    public int[] makeChoice(int x, int y)
    {
        //After this, you can only click on the highlighted squares, or click the piece itself to revert back
        //Setting the from and to coordinates for movement, if possible
        int m[] = new int[2];
        m[0] = x;
        m[1] = y;

        return m;

    }

    /*
    public int[][] getAvailableMoves()
    {

    }
    */

    public void moveHorizontally(int from, int to)
    {
        //assert to!=from;
        int distance = to - from;
        if(distance > 0)
        {
            //Move forward
            this.x+= distance;

        }
        else
        {
            //Move backward
         this.x+=distance;

        }
    }

    public void moveVertically(int from, int to)
    {
        int distance = to - from;
        if(distance > 0)
        {
            //Move forward
            this.y+= distance;

        }
        else
        {
            //Move backward
            this.y+=distance;

        }
    }

    public void moveDiagonally(int from_x, int from_y, int tox, int toy)
    {
        this.x+= (tox - from_x);
        this.y+= (toy - from_y);
    }

    public void mouseReleased(MouseEvent e)
    {

    }

    public void mouseExited(MouseEvent e)
    {

    }

    public void mouseEntered(MouseEvent e)
    {

    }


}
