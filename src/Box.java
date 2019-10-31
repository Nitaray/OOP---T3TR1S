public class Box {
    private static int width=20, height=20;
    private static int[][] box_mat;

    public Box(){
        box_mat = new int[height][width];
        for(int i=0;i<height;++i){
            for(int j=0;j<width;++j){
                box_mat[i][j]=0;
            }
        }
    }

    public static int getBoxMat(int r, int c){
        return box_mat[r][c];
    }

    public static void addShape(Shape s){
        int TLCornerX = s.getTLCornerX();
        int TLCornerY = s.getTLCornerY();
        int size = s.getSize();

        for(int i=TLCornerY;i<TLCornerY+size;++i){
            for(int j=TLCornerX;j<TLCornerX+size;++j){
                box_mat[i][j] = s.getShapeMat(i,j);
            }
        }
    }

    public static void render(){
        box_mat[2][3]=1;
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

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}
