import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class Knight extends Sprite implements MouseListener {
    public int choice=0;

    public Knight(int x, int y)
    {
        super(x,y);
        super.label = "KNIGHT";
        super.visible = true;

        initKnight();

    }

    public Knight(int x, int y, String color, ArrayList<Sprite> al)
    {
        super(x,y);
        super.label = "KNIGHT";
        super.visible = true;
        super.list=al;
        super.color=color;
        initKnight();

    }

    public void initKnight() {

        if(color=="WHITE")
        loadImage("/Users/ramachandran/IdeaProjects/chess+networking/src/images/wknight.png");
        else
            loadImage("/Users/ramachandran/IdeaProjects/chess+networking/src/images/bknight.png");

        this.image = this.image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        getImageDimensions();
        super.moveSetQueenx = new ArrayList<>(4);
        super.moveSetQueeny = new ArrayList<>(4);

    }

    public void mouseClicked(MouseEvent e)
    {
        if(!this.visible)
            return;
        //Default offsets for the mouse
        int xoffset = -25;
        int yoffset = -55;

        int xc = e.getX();
        int yc = e.getY();

        xc+= xoffset;
        yc+= yoffset;

        addtoSet(this,0);

        if(choice==0)
        {
            if(abs(this.x - xc)<=11 && abs(this.y - yc)<=11) {
                moveDiagonally(this.x, this.y, xc, yc);
                choice = 1;
                displayChoices(this);
                return;
            }
        }

        if(choice == 1)
        {
            //2 steps in horiz and one vertical

            //System.out.println(" xc = "+abs(xc) + " yc = "+abs(yc)+"\n");
            if(abs(this.x - xc)<=50 && abs(this.y - yc)<=110 && abs(this.x - xc)>=40 && abs(this.y - yc)>=66)
            {
                if(!allyCheck(xc,yc)) {
                    moveHorizontally(this.x, xc);
                    moveVertically(this.y, yc);
                }
                    resolveConflicts(this.x, this.y);
                    //choice = 0;
                    //this.setChoiceVisible = false; //Doubt
                    //removeSet(this);
                    //return ;
            }

            else if(abs(this.x - xc)<=110 && abs(this.y - yc)<=65 && abs(this.x - xc)>=66 && abs(this.y - yc)>=22)
            {
                if(!allyCheck(xc,yc)) {
                    moveHorizontally(this.x, xc);
                    moveVertically(this.y, yc);
                }
                    resolveConflicts(this.x, this.y);
                    //choice = 0;
                    //this.setChoiceVisible = false; //Doubt
                    //removeSet(this);
                    //return ;
            }

            choice = 0;
            this.setChoiceVisible = false; //Doubt
            removeSet(this);
            if((abs(x+44-Board.whiteking.x)<=10 && abs(y+88-Board.whiteking.y)<=10)||(abs(x-44-Board.whiteking.x)<=10 && abs(y+88-Board.whiteking.y)<=10)||(abs(x+44-Board.whiteking.x)<=10 && abs(y-88-Board.whiteking.y)<=10)||(abs(x-44-Board.whiteking.x)<=10 && abs(y-88-Board.whiteking.y)<=10)||(abs(x+88-Board.whiteking.x)<=10 && abs(y+44-Board.whiteking.y)<=10)||(abs(x+88-Board.whiteking.x)<=10 && abs(y-44-Board.whiteking.y)<=10)||(abs(x-88-Board.whiteking.x)<=10 && abs(y+44-Board.whiteking.y)<=10)||(abs(x-88-Board.whiteking.x)<=10 && abs(y-44-Board.whiteking.y)<=10))
            {
                Board.wchecked=true;
                System.out.println("White King checked\n");
            }
                return;

        }
    }

    public void addtoSet(Sprite sp, int c)
    {
        if(x+88 <= 342 && y+44 <= 342 && !(allyCheck(x+88,y+44))) {
            this.moveSetQueenx.add(x + 88);
            this.moveSetQueeny.add(this.y + 44);
        }


        if(x-88>=5 && y+44<=342 && !(allyCheck(x-88,y+44))) {
            this.moveSetQueenx.add(x - 88);
            this.moveSetQueeny.add(this.y + 44);
        }

        if(y-44>=5) {
            if(x+88<=342 && !(allyCheck(x-88,y-44))){
                this.moveSetQueenx.add(x + 88);
                this.moveSetQueeny.add(y - 44);
            }
            if(x-88>=5 && !(allyCheck(x-88,y-44))) {
                this.moveSetQueenx.add(x - 88);
                this.moveSetQueeny.add(y - 44);
            }
        }

        if(y+88<=342) {
            if(x+44<=342 && !(allyCheck(x+44,y+88))) {
                this.moveSetQueenx.add(x + 44);
                this.moveSetQueeny.add(y + 88);
            }
            if(x-44>=5 && !(allyCheck(x-44,y+88))) {
                this.moveSetQueenx.add(x - 44);
                this.moveSetQueeny.add(y + 88);
            }
        }

        if(y-88>=5) {
            if(x-44>=5 && !(allyCheck(x-44,y-88))) {
                this.moveSetQueenx.add(x - 44);
                this.moveSetQueeny.add(y - 88);
            }
            if(x+44<=342 && !(allyCheck(x+44,y-88))) {
                this.moveSetQueenx.add(x + 44);
                this.moveSetQueeny.add(y - 88);
            }
        }
    }

}
