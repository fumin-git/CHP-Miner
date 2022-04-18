package funcs;

public class TenTuple<A, B, C, D, E, F, G, H, I, J> extends EightTuple<A, B, C, D, E, F, G, H>{
    public final I ninth;
    public final J tenth;
    public TenTuple(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j) {
        super(a, b, c, d, e, f, g, h);
        ninth = i;
        tenth = j;
    }
}
