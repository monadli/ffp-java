package ftc.tuple;

/**
 * This class represents septuple or heptuple.
 * 
 * @author Jian Li
 *
 * @param <Tn> types of elements
 */
public class Tp7<T0, T1, T2, T3, T4, T5, T6> extends Tp6<T0, T1, T2, T3, T4, T5> {

  public final T6 _6;

  @SuppressWarnings("unchecked")
  protected Tp7(Object... elms) {
    super(elms);
    _6 = (T6) get(6);
  }

  public Tp7(T0 t1, T1 t2, T2 t3, T3 t4, T4 t5, T5 t6, T6 t7) {
    super(t1, t2, t3, t4, t5, t6, t7);
    this._6 = t7;
  }

  public static <T0, T1, T2, T3, T4, T5, T6> Tp7<T0, T1, T2, T3, T4, T5, T6> __(T0 t0, T1 t1, T2 t2, T3 t3,
      T4 t4, T5 t5, T6 t6) {
    return new Tp7<>(t0, t1, t2, t3, t4, t5, t6);
  }

  @Override
  public String toString() {
    return "{" + _0 + ',' + _1 + ',' + _2 + ',' + _3 + ',' + _4 + ',' + _5 + ',' + _6 + '}';
  }
}
