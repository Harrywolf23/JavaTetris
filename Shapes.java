import java.util.ArrayList;
import java.lang.Math;

public class Shapes {
    private int[][] oBlock;
    private int[][] lBlock;
    private int[][] tBlock;
    private int[][] sBlock;
    private int[][] zBlock;
    private int[][] iBlock;
    private int[][] jBlock;
    private ArrayList<int[][]> bag;
    

    public Shapes () {
        
    }
    
    public int[][] getShape(){
        if(bag.size()==0){
            bag.add(oBlock);
            bag.add(iBlock);
            bag.add(sBlock);
            bag.add(zBlock);
            bag.add(jBlock);
            bag.add(lBlock);
            bag.add(tBlock);
        }
        int randomPiece = (int)(Math.random() * 7 );
        return bag.remove(randomPiece);
    }
}