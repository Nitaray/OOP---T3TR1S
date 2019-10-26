public class Test {
    public static void main(String[] args) {
        Shape s = new Shape(
                new Point(1,1),
                new Point(2,1),
                new Point(3,1),
                new Point(2,2)
        );
        Shape t = new Shape(
                new Point(3,2),
                new Point(4,2),
                new Point(2,3),
                new Point(3,3)
        );
        Box.init();
        Box.addShape(s);
        s.moveDown();
//        Box.addShape(t);
        Box.render();
//        System.out.println(s.isTouch());
    }
}
