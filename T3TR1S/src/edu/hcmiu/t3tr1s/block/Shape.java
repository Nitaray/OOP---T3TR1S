package edu.hcmiu.t3tr1s.block;

public abstract class Shape {
    protected int[] LLCorner;
    protected final int height;
    protected final int width;
    protected int[][] shape_mat;
    protected final Box b;

    public Shape(int[] LLCorner, int height, int width, Box b){
        this.LLCorner = LLCorner;
        this.height = height;
        this.width = width;
        this.b = b;
        this.shape_mat = new int[height][width];
    }

    public final int getShapeMat(int r, int c){
        return shape_mat[r][c];
    }

    public final int getHeight() {
        return height;
    }

    public final int getWidth() {
        return width;
    }

    public final int getLowLeftCornerRow(){
        return LLCorner[0];
    }

    public final int getLowLeftCornerCol(){
        return LLCorner[1];
    }

    public final boolean canMoveLeft(){
        boolean movable = true;
        if(LLCorner[1]>0){
            int LLCornerSimulation = LLCorner[1]-1;
            for(int i=0;i<height;++i){
                if(shape_mat[i][0]==1 && b.getBoxMat(LLCorner[0]-height+i+1,LLCornerSimulation)==1){
                    movable = false;
                    break;
                }
            }
        }
        else movable = false;
        return movable;
    }

    public final boolean canMoveRight(){
        boolean movable = true;
        int LRCornerCol = LLCorner[1]+height-1;
        if(LRCornerCol < b.getWidth()-1){
            int LRCornerColSimulation = LRCornerCol + 1;
            for(int i=0;i<height;++i){
                if(shape_mat[i][width-1]==1 && b.getBoxMat(LLCorner[0]-height+i+1,LRCornerColSimulation)==1){
                    movable = false;
                    break;
                }
            }
        }
        else movable = false;
        return movable;
    }

    public final boolean canMoveDown(){
        boolean movable = true;
        if(LLCorner[0] < b.getHeight()-1){
            int LLCornerRowSimulation = LLCorner[0]+1;
            for(int i=0;i<width;++i){
                if(shape_mat[height-1][i]==1 && b.getBoxMat(LLCornerRowSimulation,LLCorner[1]+i)==1){
                    movable = false;
                    break;
                }
            }
        }
        else movable=false;
        return movable;
    }

    public final void moveLeft() {
        b.removeShape();
        if(canMoveLeft()){
            LLCorner[1]--;
        }
        b.addShape(this);
    }


    public final void moveRight() {
        b.removeShape();
        if(canMoveRight()){
            LLCorner[1]++;
        }
        b.addShape(this);
    }


    public final void moveDown() {
        b.removeShape();
        if(canMoveDown()){
            LLCorner[0]++;
        }
        b.addShape(this);
    }

    public abstract void rotate(RotateDirection r);

    public final void render(){
        for(int i=0;i<height;++i){
            for(int j=0;j<width;++j){
                if(shape_mat[i][j]==1){
                    System.out.print("*");
                }
                else System.out.print(" ");
            }
            System.out.println();
        }
    }

    public final void export(){
        for(int i=0;i<height;++i){
            for(int j=0;j<width;++j){
                System.out.print(shape_mat[i][j]);
            }
            System.out.println();
        }
    }
}
