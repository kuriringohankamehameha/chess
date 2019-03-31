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

        //int choice = 0;
        int decision = 0;

        //this.choice = !(this.choice);

        this.choice = false;
        //System.out.println("Choice is " + this.choice + "\n");

        /*
        System.out.println("Mouse Coordinates are (" + xc + " , " + yc + ") and object position is (" + this.x +", " + this.y + ")\n");
        */

        //PROBLEM WITH BELOW PORTION
        /*
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
        */
        int choice = 1; //CHANGE THIS
        //Movement works, but I must restrict it to center of squares only
            if(choice == 1)
            {
                //Choice is made and you can move
                if((this.y <= yc+22 && this.y >=yc-22 )&& !(this.x <= xc+22 & this.x >= xc-22)) {
                    moveHorizontally(this.x, xc);
                    System.out.println("Moved Horizontally\n");
                    decision = 0;

                    return ;
                }

                if((this.x <= xc+22 && this.x >=xc-22 )&& !(this.y <= yc+22 & this.y >= yc-22)) {
                    moveVertically(this.y, yc);
                    decision = 1;
                    System.out.println("Moved Vertically\n");

                    return ;
                }

                if(abs(this.x - xc)<=abs(this.y-yc)+5 && abs(this.x - xc)<=abs(this.y - yc)-5)
                {
                    moveDiagonally(this.x, this.y, xc, yc);
                    decision = 2;
                    System.out.println("Moved Diagonally\n");

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
        //this.x+= distance;
        //We need distance to be a multiple of 44
        if(distance > 0) {
            if (distance % 44 < 12) {
                distance-=(distance%44);
            } else if (distance % 44 > 27) {
                distance += (44 - distance % 44);
            }
            else
                return;
        }
        else
        {
            int pd = -(distance);
            if(pd % 44 < 12)
            {
                pd-=(pd)%44;
            }
            else if(pd%44 > 27)
            {
                pd+=(44 - pd%44);
            }
            else
                return;
            distance = -(pd);

        }


        assert distance%44 == 0;

        this.x+= distance;
    }

    public void moveVertically(int from, int to)
    {
        int distance = to - from;
        //We need distance to be a multiple of 44
        if(distance%44 < 12)
        {
            distance-= distance%44;
        }
        else if(distance%44 > 32)
        {
            distance+= (44 - distance%44);
        }
        else
            return;

        assert distance%44 == 0;
        this.y+= distance;
    }

    public void moveDiagonally(int from_x, int from_y, int tox, int toy)
    {
        //As of now, Going to 1st, 2nd quadrants work


        int xd= (tox - from_x);
        int yd= (toy - from_y);

        if(xd%44 < 9) //CHANGE THIS
        {
            xd-= xd%44;
        }
        else if(xd%44 > 32)
        {
            xd+= (44 - xd%44);
        }
        else
            return;

        if(yd%44 < 12)
        {
            yd-= yd%44;
        }
        else if(yd%44 > 32)
        {
            yd+= (44 - yd%44);
        }
        else
            return;

        this.x+= xd;
        this.y+= yd;


        //moveHorizontally(from_x,tox);
        //moveVertically(from_y,toy);
    }

    public void recentre(int x, int y)
    {
        //Recentres the piece to the center of the nearest square

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
