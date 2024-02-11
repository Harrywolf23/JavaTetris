import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class Board extends JPanel implements KeyListener{
    private final int BOX_SIZE = 30;
    private final int BOARD_WIDTH = 450;
    private final int BOARD_HEIGHT = 750;
    private int x = 6;
    private int y = 0;
    private Timer gravity;
    private int delay = 1000;
    private boolean collision = false;
    private Shapes shapes;
    private int[][] shape;
    private int[][] board;
    public Board(){
        board = new int[BOARD_WIDTH/BOX_SIZE][BOARD_HEIGHT/BOX_SIZE];
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        shapes = new Shapes();
        shape = shapes.getShape();
        System.out.println(shape.length + ", " + shape[0].length);
        //creating "gravity" using the java Timer
        ActionListener falling = new ActionListener() {
            public void actionPerformed(ActionEvent event){
                //System.out.println(x + " coords " + y);
                //int lowestpoint=0;
                //make shape lowest point
                // for(int shaperow = 0; shaperow<shape[0].length; shaperow++){
                //     for(int shapecol = 0; shapecol<shape.length; shapecol++){
                //         if(shape[shapecol][shaperow]==1){
                //             lowestpoint++;
                //             shapecol++;
                //         }
                //     }
                // }
                if(collision){
                    repaint();
                    shape = shapes.getShape();
                    x = 6;
                    y = 0;
                    collision = false;
                    gravity.restart();
                }

                for(int shaperow = 0; shaperow<shape[0].length; shaperow++){
                    System.out.println(y);
                    System.out.println("height " + (y+shape[0].length)*BOX_SIZE);
                    for(int shapecol = 0; shapecol<shape.length; shapecol++){
                        if(shape[shapecol][shaperow]==1){
                            if((y+shaperow)*BOX_SIZE==BOARD_HEIGHT || board[x+shapecol][y+1]==1){
                                System.out.println("collision2");
                                collision=true;
                                repaint();
                            }
                        }
                    }
                }
                if(collision==false){
                    y++;
                    repaint();
                }

                // if(((y+lowestpoint)*BOX_SIZE!=BOARD_HEIGHT) && board[x][y+lowestpoint]== 0){
                //    y++;
                //  repaint();  
                // }
                // else{
                //     System.out.println("collision");
                //     collision=true;
                //     repaint();
                // }
                
            }
        };
        gravity = new Timer(delay, falling);
        gravity.start();
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for(int r = 0; r<board.length; r++){
            for(int c = 0; c<board[0].length; c++){
                if(board[r][c]==0){
                    g2d.setColor(Color.white);
                    g2d.fillRect(r*BOX_SIZE, c*BOX_SIZE, BOARD_WIDTH, BOARD_HEIGHT);
                }
                else{
                    g2d.setColor(Color.blue);
                    g2d.fillRect(r*BOX_SIZE, c*BOX_SIZE, r*BOX_SIZE + r*BOX_SIZE, c*BOX_SIZE + c*BOX_SIZE);
                }
               
            }
        }
        g2d.setColor(Color.black);
        for(int rows = 0; rows<getHeight(); rows++){
            g2d.drawLine(0, rows*BOX_SIZE, BOX_SIZE*getWidth(), rows*BOX_SIZE);
        }  
        //columns
        g2d.setColor(Color.black);
        for(int cols = 0; cols<getWidth(); cols++){
            g2d.drawLine(cols*BOX_SIZE, 0, cols*BOX_SIZE, BOX_SIZE*getHeight());
        }

        //setting piece to board
        if(collision == true){
            place(g);
        }

        //creating a piece
        addPiece(g);
    }

    public void addPiece(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for(int row = 0; row<shape.length; row++){
            for(int col = 0; col<shape[0].length; col++){
                if(shape[row][col]==1 && collision != true){
                    g2d.setColor(Color.red);
                    g2d.fillRect(row*BOX_SIZE + x*BOX_SIZE, col*BOX_SIZE + y*BOX_SIZE, BOX_SIZE, BOX_SIZE);
                }
            }
        }
    }
    //making piece a part of the board
    public void place(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for(int r = 0; r<shape.length; r++){
            for(int c = 0; c<shape[0].length; c++){
                if(shape[r][c]==1){
                    board[x+r][y+c]=1;
                    g2d.setColor(Color.blue);
                    g2d.fillRect(r*BOX_SIZE + x*BOX_SIZE, c*BOX_SIZE + y*BOX_SIZE, BOX_SIZE, BOX_SIZE);
                }
            }
        }
    }
    //input for keys
    @Override
    public void keyTyped(KeyEvent e) {
       if(collision!=true){
        if(e.getKeyChar()=='a' || e.getKeyChar()==KeyEvent.VK_LEFT){
            if(x!=0){
                x--;  
                repaint();
            }
        }
        if(e.getKeyChar()=='d' || e.getKeyChar()==KeyEvent.VK_RIGHT){
            if(x!=13){
                x++;
                repaint();
            } 
        }
       }
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar()=='s' || e.getKeyChar()==KeyEvent.VK_DOWN){
            delay = 50;
            gravity.setDelay(delay);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyChar()=='s'){
            delay=1000;
            gravity.setDelay(delay);
        }
    }
}
