package ftc.tuple;

/**
 * This class represents quadruple.
 * 
 * @author Jian Li
 *
 * @param <Tn> types of elements
 */
public class Tp4<T0, T1, T2, T3> extends Tp3<T0, T1, T2> {

  public final T3 _3;

  public Tp4(T0 t0, T1 t1, T2 t2, T3 t3) {
    super(t0, t1, t2, t3);
    this._3 = t3;
  }

  @SuppressWarnings("unchecked")
  protected Tp4(Object... tuples) {
    super(tuples);
    _3 = (T3) get(3);
  }

  @SuppressWarnings("unchecked")
  protected Tp4(int fromIndex, int toIndex, Object... tuple) {
    super(fromIndex, toIndex, tuple);
    _3 = (T3) get(4);
  }

  public static <T0, T1, T2, T3> Tp4<T0, T1, T2, T3> __(T0 t0, T1 t1, T2 t2, T3 t3) {
    return new Tp4<>(t0, t1, t2, t3);
  }

  @Override
  public String toString() {
    return "{" + _0 + ',' + _1 + ',' + _2 + ',' + _3 + '}';
  }
}
