import java.util.ArrayList;
import java.util.Random;

public class TShape extends Shape {
    public TShape(int[] TLCorner){
        this.TLCorner = TLCorner;
        size = 3;
        shape_mat = new int[size][size];
        TRCorner = new int[2];
        LLCorner = new int[2];
        LRCorner = new int[2];

        initMatrix();
        initCheckpoints();
    }

    private void initMatrix(){
        /**
         * ------INITIALIZE SHAPE MATRIX----------------
         * center of shape matrix always 1
         */
        int centerX = 1, centerY = 1;
        shape_mat[centerX][centerY] = 1;

        int[][] possible_position = {{1,0},{0,1},{1,2},{2,1}};
        int remaining_locs = 3;
        int num_possible_locs = 4;
        ArrayList<Integer> chosen_locs = new ArrayList<>();
        Random rand = new Random();

        /**
         * randomly choose 3 out of 4 remaining possible position
         */
        for(int i=0;i<remaining_locs;++i){
            int chosen_loc = rand.nextInt(num_possible_locs);
            if(i!=0){
                do{
                    chosen_loc = rand.nextInt(remaining_locs);
                }while (chosen_locs.indexOf(chosen_loc)!=-1);
            }
            chosen_locs.add(chosen_loc);
            int xpos = possible_position[chosen_loc][0], ypos = possible_position[chosen_loc][1];
            shape_mat[xpos][ypos] = 1;
        }
    }

    private void initCheckpoints(){

        /**
         * --------SET UP CHECKPOINTS----------------
         */
        TRCorner[1] = TLCorner[1];
        TRCorner[0] = TLCorner[0]+size-1;
        LLCorner[0] = TLCorner[0];
        LLCorner[1] = TLCorner[1]+size-1;
        LRCorner[0] = TRCorner[0];
        LRCorner[1] = TRCorner[1]+size-1;

        /**
         * Set up leftCheck
         */
        leftCheck = TLCorner[0];
        for(int i=0;i<size;++i){
            if(shape_mat[i][0]==1){
                leftCheck = TLCorner[0]-1;
            }
        }

        /**
         * set up rightCheck
         */
        leftCheck = TLCorner[0]+size-1;
        for(int i=0;i<size;++i){
            if(shape_mat[i][size-1]==1){
                leftCheck = TLCorner[0]+size;
            }
        }

        /**
         * set up downCheck
         */
        downCheck = LLCorner[1];
    }

    @Override
    public boolean canMoveLeft() {
        boolean isTouchleft = false;
        for(int i=TLCorner[1];i<TLCorner[1]+size;++i){
            if(Box.getBoxMat(i,leftCheck)==1){
                isTouchleft=true;
            }
        }
        return (!isBottom() && !isTouchLeftEdge() && !isTouchleft);
    }

    @Override
    public boolean canMoveRight() {
        boolean isTouchRight = false;
        for(int i=TRCorner[1];i<TRCorner[1]+size;++i){
            if(Box.getBoxMat(i,rightCheck)==1){
                isTouchRight=true;
            }
        }
        return (!isBottom() && !isTouchRightEdge() && !isTouchRight);
    }

    @Override
    public boolean canMoveDown() {
        for(int i=0;i<size;++i){
            if(shape_mat[size-1][i]==0){

            }
        }
        return false;
    }
}
