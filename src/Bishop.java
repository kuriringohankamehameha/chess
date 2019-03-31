import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.lang.Math.abs;

public class Bishop extends Sprite implements MouseListener {

    private final int BOARD_WIDTH = 390;
    private final int MISSILE_SPEED = 2;

    public Bishop(int x, int y) {
        super(x, y);
        super.label = "BISHOP";
        super.visible = true;

        initBishop();
    }

    private void initBishop() {

        loadImage("src/images/wbishop.png");
        this.image=this.image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);

        getImageDimensions();
        System.out.println("Bishop\n");
    }

    public void move() {

        x += MISSILE_SPEED;

        if (x > BOARD_WIDTH) {
            visible = false;
        }
    }

    public void mousePressed(MouseEvent e)
    {
        //move(0,5);
    }

    public void mouseReleased(MouseEvent e)
    {

    }

    public void mouseEntered(MouseEvent e)
    {

    }

    public void mouseExited(MouseEvent e)
    {

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

        //System.out.println("Choice is " + this.choice + "\n");


        //System.out.println("Mouse Coordinates are (" + xc + " , " + yc + ") and object position is (" + this.x +", " + this.y + ")\n");


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
            if((abs(this.x - xc)<=abs(this.y-yc)+11 && abs(this.x - xc)>=abs(this.y - yc)-11) || (abs(this.y - yc)<=abs(this.x-xc)+11 && abs(this.y - yc)>=abs(this.x - xc)-11))
            {
                moveDiagonally(this.x, this.y, xc, yc);
                decision = 2;
                //System.out.println("Moved Diagonally\n");

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

        //NEED TO REFACTOR CODE
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
        this.y+= distance;

    }

    public void moveDiagonally(int from_x, int from_y, int tox, int toy)
    {
        moveHorizontally(from_x,tox);
        moveVertically(from_y,toy);
    }

}
