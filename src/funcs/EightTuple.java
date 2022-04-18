package funcs;

public class EightTuple<A, B, C, D, E, F, G, H> extends SixTuple<A, B, C, D, E, F>{
    public final G seventh;
    public final H eighth;
    public EightTuple(A a, B b, C c, D d, E e, F f, G g, H h) {
        super(a, b, c, d, e, f);
        seventh = g;
        eighth = h;
    }
}
