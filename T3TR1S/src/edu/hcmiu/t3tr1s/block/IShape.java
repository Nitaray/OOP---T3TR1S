package edu.hcmiu.t3tr1s.block;

import java.util.Random;

public class IShape extends Shape {
    private int size = 4;
    public IShape(int[] LLCorner, Box b){
        super(LLCorner,4,4,b);
        initMatrix();
    }

    private void initMatrix() {
        for(int i=0;i<size;++i){
            for(int j=0;j<size;++j){
                shape_mat[i][j] = 0;
            }
        }

        int [][][] possible_shape = {
                {{0,1},{1,1},{2,1},{3,1}},
                {{1,0},{1,1},{1,2},{1,3}},
                {{0,2},{1,2},{2,2},{3,2}},
                {{2,0},{2,1},{2,2},{2,3}}
        };
        Random r = new Random();
        int chosen_shape = r.nextInt(4);
        for(int i=0;i<4;++i){
            shape_mat[possible_shape[chosen_shape][i][0]][possible_shape[chosen_shape][i][1]] = 1;
        }
    }
    @Override
    public void rotate(RotateDirection r){
        int[][] out = new int[size][size];
        b.removeShape();
        if(canMoveDown()) {
            if(r==RotateDirection.CLOCKWISE){
                for(int i=0;i<size;++i){
                    for(int j=0;j<size;++j){
                        out[i][j] = shape_mat[size-j-1][i];
                    }
                }
            }
            else{
                for(int i=0;i<size;++i){
                    for(int j=0;j<size;++j){
                        out[i][j] = shape_mat[j][size-i-1];
                    }
                }
            }
            shape_mat = out;
        }

        b.addShape(this);
    }
}
