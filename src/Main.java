import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static javafx.application.Application.launch;

public class Main extends Application {

    public static JFrame f;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root, 500, 275));
        primaryStage.show();

    }
/*

    public static void main(String[] args)
    {

        Die d = new Die(6);
        int rolls = d.roll();
        int result = d.getResult();
        System.out.println("Result is " + result);

        //launch(args);


    }

    */
    public static void addListenertoend(JFrame f,ArrayList<Sprite> al)
    {
        if(al.get(al.size()-1).label=="QUEEN")
        f.addMouseListener((Queen)al.get(al.size()-1));

    }

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            try {
                f = new JFrame("Chess");
                f.setSize(500, 500);
                f.setResizable(false);
                f.setVisible(true);
                //f.addMouseListener(new Queen(40,60));
                f.setBackground(Color.BLUE);
                Board board=new Board();
                f.setContentPane(board);
                //f.addMouseListener(board.q);
                //f.addMouseListener(board.bishop_object);

                // TODO : Make multiple MouseListeners using enhanced for loop (Status: DONE)
                for(int i=0;i<board.spriteArrayList.size();i++)
                {
                    if(board.spriteArrayList.get(i).label.equals("QUEEN")) {
                        f.addMouseListener((Queen)board.spriteArrayList.get(i));
                    }
                    else if(board.spriteArrayList.get(i).label.equals("BISHOP"))
                        f.addMouseListener(((Bishop)board.spriteArrayList.get(i)));

                    else if(board.spriteArrayList.get(i).label.equals("KING")) {
                        f.addMouseListener(((King) board.spriteArrayList.get(i)));
                    }
                    else if(board.spriteArrayList.get(i).label.equals("ROOK")) {
                        f.addMouseListener(((Rook) board.spriteArrayList.get(i)));
                    }
                    else if(board.spriteArrayList.get(i).label.equals("KNIGHT")) {
                        f.addMouseListener(((Knight) board.spriteArrayList.get(i)));
                    }
                    else if(board.spriteArrayList.get(i).label.equals("PAWN")) {
                        f.addMouseListener(((Pawn) board.spriteArrayList.get(i)));
                    }

                }


                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
}
}
