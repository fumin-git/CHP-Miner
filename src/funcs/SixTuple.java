package funcs;

public class SixTuple<A, B, C, D, E, F> extends FourTuple<A, B, C, D>{
    public final E fifth;
    public final F sixth;
    public SixTuple(A a, B b, C c, D d, E e, F f) {
            super(a, b, c, d);
            fifth = e;
            sixth = f;
            }
}
