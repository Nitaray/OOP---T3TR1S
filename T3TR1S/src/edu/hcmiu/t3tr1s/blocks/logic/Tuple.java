package edu.hcmiu.t3tr1s.blocks.logic;

public class Tuple<U, V, W, T> {
    public final U u;
    public final V v;
    public final W w;
    public final T t;

    public Tuple(U u, V v, W w, T t){
        this.u = u;
        this.v = v;
        this.w = w;
        this.t = t;
    }
}
