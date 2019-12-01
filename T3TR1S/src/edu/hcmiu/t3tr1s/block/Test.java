package edu.hcmiu.t3tr1s.block;

public class Test {
    public static void main(String[] args) {
        Box b = new Box(20,20);
        int[] LLCorner = new int[2];
        LLCorner[0] = 3;
        LLCorner[1] = 3;
        Shape s = new IShape(LLCorner,b);
        b.addShape(s);
        for(int i=0;i<3;++i){
            s.moveDown();
            b.render();
        }
    }
}
