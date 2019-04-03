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
                ArrayList<Integer> al31 = enemydiagonalCoordinates(this.x,this.y,Board.whiteking.x,Board.whiteking.y);
                ArrayList<Integer> al32 = enemydiagonalCoordinates(Board.whiteking.x,this.y,this.x,Board.whiteking.y);
                ArrayList<Integer> al33 = enemydiagonalCoordinates(this.x,Board.whiteking.y,Board.whiteking.x,this.y);
                ArrayList<Integer> al34 = enemydiagonalCoordinates(Board.whiteking.x,Board.whiteking.y,this.x,this.y);

                if(enemydiagonalClear(x,y,Board.whiteking.x-44,Board.whiteking.y-44)||enemydiagonalClear(x,y,Board.whiteking.x-44,Board.whiteking.y+44)||enemydiagonalClear(x,y,Board.whiteking.x+44,Board.whiteking.y+44)||enemydiagonalClear(x,y,Board.whiteking.x-44,Board.whiteking.y+44)) {
                    if (al31.size()>0 && abs(al31.get(0) - Board.whiteking.x) <= 10 && abs(al31.get(1) - Board.whiteking.y) <= 10) {
                        Board.wchecked = true;
                        System.out.println("White King checked\n");
                        al31.clear();
                    }

                    if (al32.size()>0 && abs(al32.get(0) - Board.whiteking.x) <= 10 && abs(al32.get(1) - Board.whiteking.y) <= 10) {
                        Board.wchecked = true;
                        System.out.println("White King checked\n");
                        al31.clear();
                    }

                    if (al33.size()>0 && abs(al33.get(0) - Board.whiteking.x) <= 10 && abs(al33.get(1) - Board.whiteking.y) <= 10) {
                        Board.wchecked = true;
                        System.out.println("White King checked\n");
                        al33.clear();
                    }

                    if (al34.size()>0 && abs(al34.get(0) - Board.whiteking.x) <= 10 && abs(al34.get(1) - Board.whiteking.y) <= 10) {
                        Board.wchecked = true;
                        System.out.println("White King checked\n");
                        al34.clear();
                    }
                }
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
