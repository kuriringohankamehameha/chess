import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * King is an instance of a piece that can move only one step in every direction (up, down, left, right and diagonal)
 * TODO:
 * Place the king image on the board and make it move
 * */

public class King extends Sprite implements MouseListener{

    public int choice=0;

    public King(int x, int y)
    {
        super(x,y);
        super.label = "KING";
        super.visible = true;

        initKing();

    }

    public King(int x, int y, String color, ArrayList<Sprite> al)
    {
        super(x,y);
        super.label = "KING";
        super.visible = true;
        super.list=al;
        super.color=color;

        initKing();

    }

    public void initKing() {

        if(color=="WHITE")
        loadImage("/Users/ramachandran/IdeaProjects/chess+networking/src/images/wking.png");
        else
            loadImage("/Users/ramachandran/IdeaProjects/chess+networking/src/images/bking.png");

        this.image = this.image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        getImageDimensions();
        super.moveSetQueenx = new ArrayList<>(28);
        super.moveSetQueeny = new ArrayList<>(28);

    }

    public void mouseClicked(MouseEvent e)
    {
        if(!this.visible)
            return;
        //Check condition
        //If the white king is the nearest enemy wrt opposition, he is checked

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
            }
            return;
        }

        if(choice == 1)
        {
            if((this.y <= yc + 22 && this.y >= yc -22) && !(this.x <= xc + 22 && this.x >= xc -22))
            {
                if(abs(this.x - xc)<=50) {
                    if(!allyCheck(xc,yc))
                    moveHorizontally(this.x, xc);
                    resolveConflicts(this.x, this.y);
                }
                choice = 0;
                this.setChoiceVisible = false; //Doubt
                removeSet(this);
                return ;
            }

            if((abs(this.x - xc)<=22 && !(abs(this.y - yc)<=22))) {
                if(abs(this.y - yc)<=60) {
                    if(!allyCheck(xc,yc))
                        moveVertically(this.y, yc);
                    resolveConflicts(this.x, this.y);
                }
                choice = 0;
                this.setChoiceVisible = false; //Doubt
                removeSet(this);
                return ;
            }

            if((abs(this.x - xc)<=abs(this.y-yc)+11 && abs(this.x - xc)>=abs(this.y - yc)-11) || (abs(this.y - yc)<=abs(this.x-xc)+11 && abs(this.y - yc)>=abs(this.x - xc)-11))
            {
                if(abs(this.x - xc)<=50 && abs(this.y - yc)<=50) {
                    if(!allyCheck(xc,yc))
                        moveDiagonally(this.x, this.y, xc, yc);
                    resolveConflicts(this.x, this.y);
                }
                    choice = 0;
                this.setChoiceVisible = false; //Doubt
                removeSet(this);
                return ;
            }

            choice = 0;
            this.setChoiceVisible = false; //Doubt
            removeSet(this);
            return;

        }
    }

    public void mousePressed(MouseEvent e)
    {

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


    public void addtoSet(Sprite sp, int c)
    {
        this.moveSetQueenx.add(x+44);
        this.moveSetQueeny.add(this.y);

        this.moveSetQueenx.add(x-44);
        this.moveSetQueeny.add(this.y);

        this.moveSetQueenx.add(x);
        this.moveSetQueeny.add(y+44);

        this.moveSetQueenx.add(x);
        this.moveSetQueeny.add(y-44);

        this.moveSetQueenx.add(x+44);
        this.moveSetQueeny.add(this.y+44);

        this.moveSetQueenx.add(x-44);
        this.moveSetQueeny.add(this.y+44);

        this.moveSetQueenx.add(x+44);
        this.moveSetQueeny.add(this.y-44);

        this.moveSetQueenx.add(x-44);
        this.moveSetQueeny.add(this.y-44);


    }


    public void removeSet(Sprite sp)
    {

            int number = this.moveSetQueenx.size() - 1; //Careful!
            for(int i=number;i>=0;i--)
            {
                moveSetQueenx.remove(i);
                moveSetQueeny.remove(i);
            }

            return;

    }


}
