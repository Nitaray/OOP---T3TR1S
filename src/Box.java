public class Box {
    private static int h=10,w=10;
    private static int[][] box_mat = new int[h][w];
    private Box(){

    }

    public static int getH() {
        return h;
    }

    public static void setH(int h) {
        Box.h = h;
    }

    public static int getW() {
        return w;
    }

    public static void setW(int w) {
        Box.w = w;
    }

    public static int getBoxmat(int r, int c){
        return box_mat[r][c];
    }

    public static void init(){
        for(int i=0;i<h;++i){
            for(int j=0;j<w;++j){
                box_mat[i][j]=0;
            }
        }
    }

    public static void addShape(Shape s){
        box_mat[s.getTl().getY()][s.getTl().getX()] = 1;
        box_mat[s.getTr().getY()][s.getTr().getX()] = 1;
        box_mat[s.getLl().getY()][s.getLl().getX()] = 1;
        box_mat[s.getLr().getY()][s.getLr().getX()] = 1;
    }

    public static void removeShape(Shape s){

    }

    public static void render(){
        for(int i=0;i<h;++i){
            for(int j=0;j<w;++j){
                if(i==0 || i==h-1){
                    System.out.print("-");
                }
                else{
                    if(j==0||j==w-1){
                        System.out.print("|");
                    }
                    else {
                        if(box_mat[i][j]==1){
                            System.out.print("*");
                        }
                        else System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }

    public static void export_box(){
        for(int i=0;i<h;++i){
            for(int j=0;j<w;++j){
                System.out.print(box_mat[i][j]);
            }
            System.out.println();
        }
    }
}
