package funcs;

public class FourTuple<A, B, C, D> extends TwoTuple<A, B>{
    public final C third;
    public final D fourth;
    public FourTuple(A a, B b, C c, D d) {
        super(a, b);
        third = c;
        fourth = d;
    }
}
