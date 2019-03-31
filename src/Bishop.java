import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class Bishop extends Sprite implements MouseListener {

    private final int BOARD_WIDTH = 390;
    private final int MISSILE_SPEED = 2;
    public int choice=0;

    public Bishop(int x, int y) {
        super(x, y);
        super.label = "BISHOP";
        super.visible = true;

        initBishop();
    }

    public Bishop(int x, int y, ArrayList<Sprite> al)
    {
        super(x,y);
        super.label = "BISHOP";
        super.visible = true;
        super.list=al;

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

    /**
     * There are two clicks.
     * First click is on the piece and second on the destination square.
     * If second click ain't valid, go back to previous state, else move.
     *
     * IDEA: After the first click, make the main thread wait until another click
     */

    public void mouseClicked(MouseEvent e) {

        if(!this.visible)
            return;
        //System.out.println("Mouse clicked\n");
        int xc = e.getX();
        int yc = e.getY();
        int xoffset = -25;
        int yoffset = -55;
        xc+=xoffset;
        yc+=yoffset;
        int move[] = new int[2];
        int decision = 0;

        //Make 2 threads
        //Thread[] decisionThread = new Thread[2];

        if(choice == 0)
        {
            if(abs(this.x - xc)<=11 && abs(this.y - yc)<=11)
            {
                moveDiagonally(this.x,this.y,xc,yc);
                choice=1;
                return;
            }

        }

        if(choice == 1)
        {
            if((abs(this.x - xc)<=abs(this.y-yc)+11 && abs(this.x - xc)>=abs(this.y - yc)-11) || (abs(this.y - yc)<=abs(this.x-xc)+11 && abs(this.y - yc)>=abs(this.x - xc)-11))
            {
                moveDiagonally(this.x, this.y, xc, yc);
                resolveConflicts(this.x,this.y);
                decision = 2;
                choice=0;
                return ;
            }

            /**
             * TODO: Highlight available squares based on decision value on the board (??)
             */
            choice = 0;
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

    public void moveDiagonally(int from_x, int from_y, int tox, int toy)
    {
        super.moveHorizontally(from_x,tox);
        super.moveVertically(from_y,toy);
    }


}
