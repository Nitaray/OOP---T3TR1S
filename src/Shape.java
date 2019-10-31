public abstract class Shape {
    protected int[] TLCorner, TRCorner, LLCorner, LRCorner;
    protected int size;
    protected int[][] shape_mat;
    protected int leftCheck, rightCheck, downCheck;

    public int getShapeMat(int r, int c){
        return shape_mat[r][c];
    }

    public int getTLCornerX(){
        return TLCorner[0];
    }

    public int getTLCornerY(){
        return TLCorner[1];
    }

    public int getSize() {
        return size;
    }

    // boolean methods
    public boolean isBottom(){
        return (TLCorner[1] == Box.getHeight()-2);
    }

    public boolean isTouchLeftEdge(){
        return (TLCorner[0]==1);
    }

    public boolean isTouchRightEdge(){
        return (TLCorner[0]==Box.getWidth()-2);
    }

    public abstract boolean canMoveLeft();
    public abstract boolean canMoveRight();
    public abstract boolean canMoveDown();
//    public abstract boolean isCollideLeft();
//    public abstract boolean isCollideRight();
//    public abstract boolean isCollideDown();
    public abstract boolean rotate(RotateDirection r);

    public void moveLeft(){
        if(canMoveLeft()) TLCorner[0]--;
    }

    public void moveRight(){
        if(canMoveRight())
        TLCorner[0]++;
    }

    public void moveDown(){
        TLCorner[1]++;
    }

    public void render(){
        for(int i=0;i<size;++i){
            for(int j=0;j<size;++j){
                if(shape_mat[i][j]==1){
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
}
