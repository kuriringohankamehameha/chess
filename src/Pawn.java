import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class Pawn extends Sprite implements MouseListener {
    public int choice=0;

    public Pawn(int x, int y)
    {
        super(x,y);
        super.label = "PAWN";
        super.visible = true;

        initPawn();

    }

    public Pawn(int x, int y, String color,ArrayList<Sprite> al)
    {
        super(x,y);
        super.label = "PAWN";
        super.visible = true;
        super.list=al;
        super.color = color;

        initPawn();

    }

    public void initPawn() {

        if(color=="WHITE")
        loadImage("/Users/ramachandran/IdeaProjects/chess+networking/src/images/wpawn.png");
        else
            loadImage("/Users/ramachandran/IdeaProjects/chess+networking/src/images/bpawn.png");

        this.image = this.image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        getImageDimensions();
        super.moveSetQueenx = new ArrayList<>(3);
        super.moveSetQueeny = new ArrayList<>(3);
        twosteps=1;

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
            }
            return;
        }

        if(choice == 1)
        {
            if((this.x <= xc+22 && this.x >=xc-22 )&& !(this.y <= yc+22 & this.y >= yc-22)) {
                if(this.color=="WHITE") {
                    if (twosteps == 1 && (this.y - yc) >= 50 + 44 && this.y - 44 * 3 <= yc) {
                        if (!allyCheck(xc, yc) && verticalClear(x, y, xc, yc) && enemyverticalClear(x, y, xc, yc)) {
                            moveVertically(this.y, yc);
                        }
                        twosteps = 0;
                    } else if ((this.y - yc) >= 50 && this.y - 88 <= yc) {
                        if (!allyCheck(xc, yc) && verticalClear(x, y, xc, yc) && enemyverticalClear(x, y, xc, yc)) {
                            moveVertically(this.y, yc);
                        }
                    }
                }
                else
                {
                    if (twosteps == 1 && (yc-this.y) <= 55 + 44 && yc>=80+this.y) {
                        if (!allyCheck(xc, yc) && verticalClear(x, y, xc, yc) && enemyverticalClear(x, y, xc, yc)) {
                            moveVertically(this.y, yc);
                        }
                        twosteps = 0;
                    } else if ((yc-this.y) <= 66 && yc>=this.y+22) {
                        if (!allyCheck(xc, yc) && verticalClear(x, y, xc, yc) && enemyverticalClear(x, y, xc, yc)) {
                            moveVertically(this.y, yc);
                        }
                    }
                }
                choice = 0;
                this.setChoiceVisible = false; //Doubt
                removeSet(this);
                //If pawn goes to y=0, it becomes Queen
                getPromoted();
                Board.pawnturns++;
                return ;
            }

            if((abs(this.x - xc)<=abs(this.y-yc)+11 && abs(this.x - xc)>=abs(this.y - yc)-11) || (abs(this.y - yc)<=abs(this.x-xc)+11 && abs(this.y - yc)>=abs(this.x - xc)-11))
            {
                if(this.color=="WHITE") {
                    Boolean a = isPiece(this.x + 44, this.y - 44);
                    Boolean b = isPiece(this.x - 44, this.y - 44);
                    if ((this.y > yc) && (a || b)) {
                        if (a && this.x + 40 <= xc && this.x + 52 >= xc) {
                            if (enemyCheck(xc, yc)) {
                                moveDiagonally(this.x, this.y, xc, yc);
                                twosteps = 0;
                            }
                            resolveConflicts(this.x, this.y);
                            choice = 0;
                            this.setChoiceVisible = false; //Doubt
                            removeSet(this);
                            getPromoted();
                            Board.pawnturns++;
                            return;
                        }

                        if (b && xc + 40 <= this.x && xc + 52 >= this.x) {
                            if (enemyCheck(xc, yc)) {
                                moveDiagonally(this.x, this.y, xc, yc);
                                twosteps = 0;
                            }
                            resolveConflicts(this.x, this.y);
                            choice = 0;
                            this.setChoiceVisible = false; //Doubt
                            removeSet(this);
                            getPromoted();
                            Board.pawnturns++;
                            return;
                        }

                    }
                }
                else
                {
                    Boolean a = isPiece(this.x + 44, this.y + 44);
                    Boolean b = isPiece(this.x - 44, this.y + 44);
                    if ((this.y < yc) && (a || b)) {
                        if (a && this.x + 40 <= xc && this.x + 52 >= xc) {
                            if (enemyCheck(xc, yc)) {
                                moveDiagonally(this.x, this.y, xc, yc);
                                twosteps = 0;
                            }
                            resolveConflicts(this.x, this.y);
                            choice = 0;
                            this.setChoiceVisible = false; //Doubt
                            removeSet(this);
                            getPromoted();
                            Board.pawnturns++;
                            return;
                        }

                        if (b && xc + 40 <= this.x && xc + 52 >= this.x) {
                            if (enemyCheck(xc, yc)){
                                moveDiagonally(this.x, this.y, xc, yc);
                                twosteps = 0;}
                            resolveConflicts(this.x, this.y);
                            choice = 0;
                            this.setChoiceVisible = false; //Doubt
                            removeSet(this);
                            getPromoted();
                            Board.pawnturns++;
                            return;
                        }

                    }
                }
                choice = 0;
                this.setChoiceVisible = false; //Doubt
                removeSet(this);
                Board.pawnturns++;
                return ;
            }

            choice = 0;
            this.setChoiceVisible = false; //Doubt
            removeSet(this);
            Board.pawnturns++;
            return;

        }
    }

    public void addtoSet(Sprite sp, int c)
    {
        if(enpassant!=0)
        {
            if(sp.color=="WHITE")
            {
                //Add the diagonal square ABOVE enemy for movement
                if(enpassant==1)
                {
                    this.moveSetQueenx.add(x+44);
                    this.moveSetQueeny.add(y-44);
                }

                if(enpassant==2)
                {
                    this.moveSetQueenx.add(x-44);
                    this.moveSetQueeny.add(y-44);
                }
            }

            else
            {
                //Add the diagonal square ABOVE enemy for movement
                if(enpassant==1)
                {
                    this.moveSetQueenx.add(x+44);
                    this.moveSetQueeny.add(y+44);
                }

                if(enpassant==2)
                {
                    this.moveSetQueenx.add(x-44);
                    this.moveSetQueeny.add(y+44);
                }
            }

        }

        if(twosteps==1 && y>5 && sp.color=="WHITE")
        {
            this.moveSetQueenx.add(x);
            this.moveSetQueeny.add(y-88);
        }

        else if(twosteps==1 && y<345 && sp.color=="BLACK")
        {
            this.moveSetQueenx.add(x);
            this.moveSetQueeny.add(y+88);
        }

        if(y>5 && sp.color=="WHITE")
        {
            this.moveSetQueenx.add(x);
            this.moveSetQueeny.add(y-44);
        }

        else if(sp.color=="BLACK" && y<345)
        {
            this.moveSetQueenx.add(x);
            this.moveSetQueeny.add(y+44);
        }

        if(sp.color=="WHITE") {
            Boolean a = enemyCheck(this.x + 44, this.y - 44);
            Boolean b = enemyCheck(this.x - 44, this.y - 44);
            if (a) {
                this.moveSetQueenx.add(x + 44);
                this.moveSetQueeny.add(y - 44);
            }
            if (b) {
                this.moveSetQueenx.add(x - 44);
                this.moveSetQueeny.add(y - 44);
            }
        }

        else
        {
            Boolean a = enemyCheck(this.x - 44, this.y + 44);
            Boolean b = enemyCheck(this.x + 44, this.y + 44);
            if (a) {
                this.moveSetQueenx.add(x - 44);
                this.moveSetQueeny.add(y + 44);
            }
            if (b) {
                this.moveSetQueenx.add(x + 44);
                this.moveSetQueeny.add(y + 44);
            }
        }


    }

    void getPromoted()
    {
        if(this.color=="WHITE") {
            if (this.y <= 5) {
                int i = 0;
                int xcoord = this.x;
                int ycoord = this.y;
                for (Sprite sprite : this.list) {
                    if (this == sprite) {
                        this.list.get(i).visible = false;
                        //Queen q = new Queen(xcoord,ycoord,this.list);
                        //super.list.add(q);
                        //this.list.remove(sprite);
                        //I cannot add this to a list which won't be used
                        Board.promotion = 1;
                        Board.promotion_x = xcoord;
                        Board.promotion_y = ycoord;
                        this.list.remove(sprite);
                        return;
                    }
                    i++;
                }

            }
        }
        else
        {
            if (this.y >= 302) {
                int i = 0;
                int xcoord = this.x;
                int ycoord = this.y;
                for (Sprite sprite : this.list) {
                    if (this == sprite) {
                        this.list.get(i).visible = false;
                        //Queen q = new Queen(xcoord,ycoord,this.list);
                        //super.list.add(q);
                        //this.list.remove(sprite);
                        //I cannot add this to a list which won't be used
                        Board.promotion = 1;
                        Board.promotion_x = xcoord;
                        Board.promotion_y = ycoord;
                        Board.color=1;
                        this.list.remove(sprite);
                        return;
                    }
                    i++;
                }

            }
        }
    }
}
