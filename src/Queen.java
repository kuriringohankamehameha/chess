import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static java.lang.Math.abs;


public class Queen extends Sprite implements MouseListener {

    public int offset;
    public int[][] moveset;
    public int choice = 0;

    public Queen(int x,int y)
    {
        super(x,y);
        super.label = "QUEEN";
        super.visible = true;


        initQueen();
    }

    public Queen(int x, int y, String color, ArrayList<Sprite> al)
    {
        super(x,y);
        super.label = "QUEEN";
        super.visible = true;
        super.list = al;
        super.color=color;


        initQueen();
    }

    public void initQueen()
    {
        if(color=="WHITE")
        loadImage("/Users/ramachandran/IdeaProjects/chess+networking/src/images/wqueen.png");
        else
            loadImage("/Users/ramachandran/IdeaProjects/chess+networking/src/images/bqueen.png");

        this.image=this.image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        getImageDimensions();
        super.moveSetQueenx = new ArrayList<>(28);
        super.moveSetQueeny = new ArrayList<>(28);
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

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(!this.visible)
            return;

        int xc = e.getX();
        int yc = e.getY();

        int xoffset = -25;
        int yoffset = -55;

        xc+=xoffset;
        yc+=yoffset;

        //System.out.println(this.x+" " + this.y + "\n");


        //Set movesetqueen
        //First horizontally
        addtoSet(this,0);
        //Vertically
        addtoSet(this,1);
        //Diagonally
        addtoSet(this,2);

        if(choice == 0)
        {
            if(abs(this.x - xc)<=11 && abs(this.y - yc)<=11) {
                moveDiagonally(this.x, this.y, xc, yc);
                choice = 1;
                //Display all choice squares
                displayChoices(this);
            }
            return;
        }

        if(choice==1)
        {
            super.mouseClicked(e);
            super.resolveConflicts(this.x,this.y);
            choice=0;

            this.setChoiceVisible = false; //Doubt
            removeSet(this);
            //Checking white king
            //Assuming king stays in same half - Please debug this
            ArrayList<Integer> al11 = enemyverticalCoordinates(this.x,this.y,this.x,Board.whiteking.y);
            ArrayList<Integer> al12 = enemyverticalCoordinates(this.x,Board.whiteking.y,this.x,this.y);
            ArrayList<Integer> al21 = enemyhorizontalCoordinates(this.x,this.y,Board.whiteking.x,y);
            ArrayList<Integer> al22 = enemyhorizontalCoordinates(Board.whiteking.x,this.y,this.x,y);
            ArrayList<Integer> al31 = enemydiagonalCoordinates(this.x,this.y,Board.whiteking.x,Board.whiteking.y);
            ArrayList<Integer> al32 = enemydiagonalCoordinates(Board.whiteking.x,this.y,this.x,Board.whiteking.y);
            ArrayList<Integer> al33 = enemydiagonalCoordinates(this.x,Board.whiteking.y,Board.whiteking.x,this.y);
            ArrayList<Integer> al34 = enemydiagonalCoordinates(Board.whiteking.x,Board.whiteking.y,this.x,this.y);


            if(enemyverticalClear(x,y,x,Board.whiteking.y-44)||enemyverticalClear(x,Board.whiteking.y,x,y)) {
                if (al11.size()>0 &&(al11.get(0) - Board.whiteking.x) <= 10 && abs(al11.get(1) - Board.whiteking.y) <= 10) {
                    Board.wchecked = true;
                    System.out.println("White King checked\n");
                    al11.clear();
                }
                if (al12.size()>0 &&(al12.get(0) - Board.whiteking.x) <= 10 && abs(al12.get(1) - Board.whiteking.y) <= 10) {
                    Board.wchecked = true;
                    System.out.println("White King checked\n");
                    al12.clear();
                }

            }

            if(enemyhoriontalClear(x,y,Board.whiteking.x-44,y)||enemyhoriontalClear(x,y,Board.whiteking.x+44,y)) {
                if (al21.size()>0 && abs(al21.get(0) - Board.whiteking.x) <= 10 && abs(al21.get(1) - Board.whiteking.y) <= 10) {
                    Board.wchecked = true;
                    System.out.println("White King checked\n");
                    al21.clear();
                }
                if (al22.size()>0 && abs(al22.get(0) - Board.whiteking.x) <= 10 && abs(al22.get(1) - Board.whiteking.y) <= 10) {
                    Board.wchecked = true;
                    System.out.println("White King checked\n");
                    al22.clear();
                }

            }


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


            return;
        }

    }

}
