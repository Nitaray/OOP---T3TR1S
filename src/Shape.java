public class Shape {
    private Point tl,tr,ll,lr;
    public Shape(Point tl,Point tr,Point ll,Point lr){
        this.tl = tl;
        this.tr = tr;
        this.ll = ll;
        this.lr = lr;
    }

    // getters and setters
    public Point getTl() {
        return tl;
    }

    public void setTl(Point tl) {
        this.tl = tl;
    }

    public Point getTr() {
        return tr;
    }

    public void setTr(Point tr) {
        this.tr = tr;
    }

    public Point getLl() {
        return ll;
    }

    public void setLl(Point ll) {
        this.ll = ll;
    }

    public Point getLr() {
        return lr;
    }

    public void setLr(Point lr) {
        this.lr = lr;
    }

    // boolean methods
    public boolean isTouchLeft(){
        return (!canMoveLeft() ||
                (Box.getBoxmat(ll.getY(),ll.getX()-1)==1 || Box.getBoxmat(tl.getY(),tl.getX()-1)==1));
    }

    public boolean isTouchRight(){
        return (!canMoveRight() ||
                (Box.getBoxmat(lr.getY(),lr.getX()+1)==1 || Box.getBoxmat(tr.getY(),tr.getX()+1)==1));
    }

    public boolean isTouchDown(){
        return(!isBottom() ||
                (Box.getBoxmat(ll.getY()+1,ll.getX())==1 || Box.getBoxmat(lr.getY()+1,lr.getX())==1));
    }

    public boolean isTouch(){
        return (isTouchRight() || isTouchLeft() || isTouchDown());
    }

    public boolean isBottom(){
        return (ll.getY()==Box.getH()-2 || lr.getY()==Box.getH()-2);
    }

    public boolean canMoveLeft(){
        return (!isBottom() && !isTouch() && (ll.getX()>1 && tl.getX()>1));
    }

    public boolean canMoveRight(){
        return (!isBottom() && !isTouch() && (lr.getX()<Box.getW()-2 && tr.getX()<Box.getW()-2));
    }



    // control methods
    public void moveLeft(){
        if(canMoveLeft()){
            tl.setX(tl.getX()-1);
            tr.setX(tr.getX()-1);
            ll.setX(ll.getX()-1);
            lr.setX(lr.getX()-1);
        }
    }

    public void moveRight(){
        if(canMoveRight()){
            tl.setX(tl.getX()+1);
            tr.setX(tr.getX()+1);
            ll.setX(ll.getX()+1);
            lr.setX(lr.getX()+1);
        }
    }

    public void moveDown(){
        if(!isBottom() || !isTouch()){
            tl.setY(tl.getY()+1);
            tr.setY(tr.getY()+1);
            ll.setY(ll.getY()+1);
            lr.setY(lr.getY()+1);
        }
    }
}
