import javax.swing.JFrame;

class Game extends JFrame{
    Board board;
    public Game(){
        this.setTitle(("Tetris"));
        board = new Board();
        this.add(board);
        this.addKeyListener(board);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.pack();
        
    }
    public static void main(String[]args){
        Game g = new Game();
    }
}