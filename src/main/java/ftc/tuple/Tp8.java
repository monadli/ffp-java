package ftc.tuple;

/**
 * This class represents octuple.
 * 
 * @author Jian Li
 *
 * @param <Tn> types of elements
 */
public class Tp8<T0, T1, T2, T3, T4, T5, T6, T7> extends Tp7<T0, T1, T2, T3, T4, T5, T6> {

  public final T7 _7;

  @SuppressWarnings("unchecked")
  public Tp8(Object... elms) {
    super(elms);
    _7 = (T7) get(7);
  }

  public Tp8(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
    super(t0, t1, t2, t3, t4, t5, t6);
    this._7 = t7;
  }

  public static <T0, T1, T2, T3, T4, T5, T6, T7> Tp8<T0, T1, T2, T3, T4, T5, T6, T7> __(T0 t0, T1 t1, T2 t2,
      T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
    return new Tp8<>(t0, t1, t2, t3, t4, t5, t6, t7);
  }

  @Override
  public String toString() {
    return "{" + _0 + ',' + _1 + ',' + _2 + ',' + _3 + ',' + _4 + ',' + _5 + ',' + _6 + ',' + _7
        + '}';
  }
}
