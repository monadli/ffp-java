package ftc.tuple;

/**
 * This class represents sextuple or hextuple.
 * 
 * @author Jian Li
 *
 * @param <Tn> types of elements
 */
public class Tp6<T0, T1, T2, T3, T4, T5> extends Tp5<T0, T1, T2, T3, T4> {

  public final T5 _5;

  @SuppressWarnings("unchecked")
  protected Tp6(Object... elms) {
    super(elms);
    _5 = (T5) get(5);
  }

  public Tp6(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
    super(t0, t1, t2, t3, t4, t5);
    this._5 = t5;
  }

  public static <T0, T1, T2, T3, T4, T5> Tp6<T0, T1, T2, T3, T4, T5> __(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
    return new Tp6<>(t0, t1, t2, t3, t4, t5);
  }

  @Override
  public String toString() {
    return "{" + _0 + ',' + _1 + ',' + _2 + ',' + _3 + ',' + _4 + ',' + _5 + '}';
  }
}
