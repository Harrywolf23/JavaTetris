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
    private int x = 6;
    private int y = 0;
    private Timer gravity;
    private int delay = 1000;
    private boolean collision = false;
    private int[][] shape = {
        {1, 1, 1},
        {1, 0, 0}
    };
    public Board(){
        this.setPreferredSize(new Dimension(450, 750));

        //creating "gravity" using the java Timer
        ActionListener falling = new ActionListener() {
            public void actionPerformed(ActionEvent event){
                if(collision){
                    return;
                }
                if(((y+3)*BOX_SIZE!=getHeight())){
                   y++;
                 repaint();  
                }
                else{
                    collision=true;
                }
                
            }
        };
        gravity = new Timer(delay, falling);
        gravity.start();
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, getWidth(), getHeight()); 
        //painting the board:
        //rows
        g2d.setColor(Color.black);
        for(int rows = 0; rows<getHeight(); rows++){
            g2d.drawLine(0, rows*BOX_SIZE, BOX_SIZE*getWidth(), rows*BOX_SIZE);
        }  
        //columns
        g2d.setColor(Color.black);
        for(int cols = 0; cols<getWidth(); cols++){
            g2d.drawLine(cols*BOX_SIZE, 0, cols*BOX_SIZE, BOX_SIZE*getHeight());
        }

        //creating a piece
        for(int row = 0; row<shape.length; row++){
            for(int col = 0; col<shape[0].length; col++){
                if(shape[row][col]==1){
                    g2d.setColor(Color.red);
                    g2d.fillRect(row*BOX_SIZE + x*BOX_SIZE, col*BOX_SIZE + y*BOX_SIZE, BOX_SIZE, BOX_SIZE);
                }
            }
        }
    }

    //input for keys
    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar()=='w' || e.getKeyChar()==KeyEvent.VK_UP){
            y--;
            repaint();
        }
        if(e.getKeyChar()=='s' || e.getKeyChar()==KeyEvent.VK_DOWN){
            y++;
            repaint();
        }
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
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }

    // public void place(){
    //     if(collision==true){
            
    //     }
    // }

}
