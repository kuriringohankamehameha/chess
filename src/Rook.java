import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class Rook extends Sprite implements MouseListener {

    public int choice = 0;

    public Rook(int x, int y) {
        super(x, y);
        super.label = "ROOK";
        super.visible = true;

        initRook();

    }

    public Rook(int x, int y, String color, ArrayList<Sprite> al) {
        super(x, y);
        super.label = "ROOK";
        super.visible = true;
        super.list = al;
        super.color = color;
        initRook();

    }


    public void initRook() {

        if (color == "WHITE")
            loadImage("/Users/ramachandran/IdeaProjects/chess+networking/src/images/wrook.png");
        else
            loadImage("/Users/ramachandran/IdeaProjects/chess+networking/src/images/brook.png");
        this.image = this.image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        getImageDimensions();
        super.moveSetQueenx = new ArrayList<>(28);
        super.moveSetQueeny = new ArrayList<>(28);

    }

    public void mouseClicked(MouseEvent e) {
        if (!this.visible)
            return;
        //Default offsets for the mouse
        int xoffset = -25;
        int yoffset = -55;

        int xc = e.getX();
        int yc = e.getY();

        xc += xoffset;
        yc += yoffset;

        addtoSet(this, 0);
        addtoSet(this, 1);


        if (choice == 0) {
            if (abs(this.x - xc) <= 11 && abs(this.y - yc) <= 11) {
                moveDiagonally(this.x, this.y, xc, yc);
                choice = 1;
                displayChoices(this);
            }
            return;
        }

        if (choice == 1) {
            if ((this.y <= yc + 22 && this.y >= yc - 22) && !(this.x <= xc + 22 & this.x >= xc - 22)) {
                if (!allyCheck(xc, yc) && horizontalClear(x, y, xc, yc)) {
                    if (enemyhorizontalCount(this.x, this.y, xc, yc) >= 1) {
                        ArrayList<Integer> nearestenemy = enemyhorizontalCoordinates(this.x, this.y, xc, yc);
                        if (abs(xc - nearestenemy.get(0)) <= 10 && abs(yc - nearestenemy.get(1)) <= 10)
                            moveHorizontally(this.x, xc);
                        nearestenemy.clear();
                    } else if (enemyhoriontalClear(this.x, this.y, xc, yc))
                        moveHorizontally(this.x, xc);
                }
                // moveHorizontally(this.x, xc);
                resolveConflicts(this.x, this.y);
                choice = 0;
                this.setChoiceVisible = false;
                removeSet(this);
                //return ;
            } else if ((this.x <= xc + 22 && this.x >= xc - 22) && !(this.y <= yc + 22 & this.y >= yc - 22)) {
                if (!allyCheck(xc, yc) && verticalClear(x, y, xc, yc)) {
                    if (enemyverticalCount(this.x, this.y, xc, yc) >= 1) {
                        ArrayList<Integer> nearestenemy = enemyverticalCoordinates(this.x, this.y, xc, yc);
                        if (abs(xc - nearestenemy.get(0)) <= 10 && abs(yc - nearestenemy.get(1)) <= 10)
                            moveVertically(this.y, yc);
                        nearestenemy.clear();
                    } else if (enemyverticalClear(this.x, this.y, xc, yc))
                        moveVertically(this.y, yc);
                }
                //moveVertically(this.y, yc);
                resolveConflicts(this.x, this.y);
                choice = 0;
                this.setChoiceVisible = false;
                removeSet(this);
                //return ;
            }

            choice = 0;
            this.setChoiceVisible = false;
            removeSet(this);
            ArrayList<Integer> al11 = enemyverticalCoordinates(this.x, this.y, this.x, Board.whiteking.y);
            ArrayList<Integer> al12 = enemyverticalCoordinates(this.x, Board.whiteking.y, this.x, this.y);
            ArrayList<Integer> al21 = enemyhorizontalCoordinates(this.x, this.y, Board.whiteking.x, y);
            ArrayList<Integer> al22 = enemyhorizontalCoordinates(Board.whiteking.x, this.y, this.x, y);


            if (enemyverticalClear(x, y, x, Board.whiteking.y - 44) || enemyverticalClear(x, Board.whiteking.y, x, y)) {
                if (al11.size() > 0 && (al11.get(0) - Board.whiteking.x) <= 10 && abs(al11.get(1) - Board.whiteking.y) <= 10) {
                    Board.wchecked = true;
                    System.out.println("White King checked v1\n");
                    al11.clear();
                }
                if (al12.size() > 0 && (al12.get(0) - Board.whiteking.x) <= 10 && abs(al12.get(1) - Board.whiteking.y) <= 10) {
                    Board.wchecked = true;
                    System.out.println("White King checked v2\n");
                    al12.clear();
                }

            }

            if (enemyhoriontalClear(x, y, Board.whiteking.x - 44, y) || enemyhoriontalClear(x, y, Board.whiteking.x + 44, y)) {
                if (al21.size() > 0 && abs(al21.get(0) - Board.whiteking.x) <= 10 && abs(al21.get(1) - Board.whiteking.y) <= 10) {
                    Board.wchecked = true;
                    System.out.println("White King checked h1\n");
                    al21.clear();
                }
                if (al22.size() > 0 && abs(al22.get(0) - Board.whiteking.x) <= 10 && abs(al22.get(1) - Board.whiteking.y) <= 10) {
                    Board.wchecked = true;
                    System.out.println("White King checked h2\n");
                    al22.clear();
                }

            }
        }
        return;
    }
}
