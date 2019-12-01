package edu.hcmiu.t3tr1s.block;

public class Box {
    private int width=20, height=20;
    private int[][] box_mat;
    private boolean newShape = false;
    public Shape current;

    public Box(int width, int height){
        this.width = width;
        this.height = height;
        box_mat = new int[height][width];
        for(int i=0;i<height;++i){
            for(int j=0;j<width;++j){
                if(i==0 || i==height-1 || j==0 || j==width-1){
                    box_mat[i][j]=1;
                }
                else box_mat[i][j]=0;
            }
        }
    }

    public Shape getCurrent(){
        return current;
    }

    public int getBoxMat(int r, int c){
        return box_mat[r][c];
    }

    public void addShape(Shape s){
        current = s;
        int TLCornerRow = s.getLowLeftCornerRow() - s.getHeight() + 1;
        int TLCornerCol = s.getLowLeftCornerCol();
        for(int i=TLCornerRow;i<TLCornerRow + s.getHeight();++i){
            for(int j=TLCornerCol;j<TLCornerCol+s.getWidth();++j){
                if(box_mat[i][j]!=1)
                    box_mat[i][j] = s.getShapeMat(i-TLCornerRow,j-TLCornerCol);
            }
        }
    }

    public void removeShape(){
        int LLCornerRow = current.getLowLeftCornerRow();
        int LLCornerCol = current.getLowLeftCornerCol();

        int TLCornerRow = LLCornerRow - current.getHeight() + 1;
        int TLCornerCol = LLCornerCol;
        for(int i=TLCornerRow;i<TLCornerRow + current.getHeight();++i){
            for(int j=TLCornerCol;j<TLCornerCol + current.getWidth();++j){
                if(current.getShapeMat(i-TLCornerRow,j-TLCornerCol)==1){
                    box_mat[i][j]=0;
                }
            }
        }
    }

    public void render(){
        for(int i=0;i<height;++i){
            for(int j=0;j<width;++j){
                if(i==0 || i==height-1){
                    System.out.print("=");
                }
                else{
                    if(j==0 || j==width-1){
                        System.out.print("|");
                    }
                    else if(box_mat[i][j]==1){
                        System.out.print("*");
                    }
                    else System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void export(){
        for(int i=0;i<height;++i){
            for(int j=0;j<width;++j){
                System.out.print(box_mat[i][j]);
            }
            System.out.println();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
