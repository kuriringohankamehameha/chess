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
            ArrayList<Integer> al = enemyverticalCoordinates(this.x,this.y,this.x,Board.whiteking.y);
            if((al.get(0)-Board.whiteking.x)<=10 && abs(al.get(1)-Board.whiteking.y)<=10)
            {
                Board.wchecked=true;
                System.out.println("White King checked\n");
            }
            al.clear();
            return;
        }

    }

}
