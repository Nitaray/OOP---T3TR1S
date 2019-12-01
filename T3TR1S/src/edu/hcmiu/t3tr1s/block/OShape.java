package edu.hcmiu.t3tr1s.block;

public class OShape extends Shape {
    public OShape(int[] LLcorner, Box b){
        super(LLcorner,3,4,b);
        initMatrix();
    }

    private void initMatrix(){
        for(int i=0;i<height;++i){
            for(int j=0;j<width;++j){
                shape_mat[i][j]=0;
            }
        }
        shape_mat[0][1] = 1;
        shape_mat[0][2] = 1;
        shape_mat[1][1] = 1;
        shape_mat[1][2] = 1;
    }

    @Override
    public void rotate(RotateDirection r) {
        /**
         * do nothing because this shape is not rotatable
         * will update later
         */
    }
}
