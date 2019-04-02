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

    public Bishop(int x, int y, String color, ArrayList<Sprite> al)
    {
        super(x,y);
        super.label = "BISHOP";
        super.visible = true;
        super.list=al;
        super.color=color;
        initBishop();

    }

    private void initBishop() {

        if(color=="WHITE")
        loadImage("src/images/wbishop.png");
        else
            loadImage("src/images/bbishop.png");

        this.image=this.image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);

        getImageDimensions();
        //System.out.println("Bishop\n");
        super.moveSetQueenx = new ArrayList<>(28);
        super.moveSetQueeny = new ArrayList<>(28);
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
        int xc = e.getX();
        int yc = e.getY();
        int xoffset = -25;
        int yoffset = -55;
        xc+=xoffset;
        yc+=yoffset;

        //Make 2 threads

        //Add to moveset

        addtoSet(this,2);

        if(choice == 0)
        {
            if(abs(this.x - xc)<=11 && abs(this.y - yc)<=11)
            {
                moveDiagonally(this.x,this.y,xc,yc);
                choice=1;
                displayChoices(this);
                return;
            }

        }

        if(choice == 1)
        {
            if((abs(this.x - xc)<=abs(this.y-yc)+11 && abs(this.x - xc)>=abs(this.y - yc)-11) || (abs(this.y - yc)<=abs(this.x-xc)+11 && abs(this.y - yc)>=abs(this.x - xc)-11))
            {
                if(!allyCheck(xc,yc) && diagonalClear(this.x,this.y,xc,yc)) {
                    if(enemydiagonalCount(this.x,this.y,xc,yc)>1)
                    {
                        //Movement allowed only till nearest enemy
                        ArrayList<Integer> nearestenemy = enemydiagonalCoordinates(this.x,this.y,xc,yc);
                        if(abs(xc-nearestenemy.get(0))<=10 && abs(yc-nearestenemy.get(1))<=10)
                            moveDiagonally(this.x, this.y, xc, yc);
                        nearestenemy.clear();
                    }
                    else if(enemydiagonalClear(this.x,this.y,xc,yc))
                    moveDiagonally(this.x, this.y, xc, yc);
                }
                resolveConflicts(this.x,this.y);
                choice=0;

                this.setChoiceVisible = false;
                removeSet(this);
                return ;
            }

            /**
             * TODO: Highlight available squares based on decision value on the board (??)
             */
            choice = 0;
            this.setChoiceVisible = false;
            removeSet(this);
            return;
        }


    }

    public void moveDiagonally(int from_x, int from_y, int tox, int toy)
    {
        super.moveHorizontally(from_x,tox);
        super.moveVertically(from_y,toy);
    }


}
