package ftc.tuple;

/**
 * This class represents quintuple or pentuple.
 * 
 * @author Jian Li
 *
 * @param <Tn> types of elements
 */
public class Tp5<T0, T1, T2, T3, T4> extends Tp4<T0, T1, T2, T3> {

  public final T4 _4;

  @SuppressWarnings("unchecked")
  protected Tp5(Object... elms) {
    super(elms);
    _4 = (T4) get(4);
  }

  public Tp5(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4) {
    super(t0, t1, t2, t3, t4);
    this._4 = t4;
  }

  public static <T0, T1, T2, T3, T4> Tp5<T0, T1, T2, T3, T4> __(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4) {
    return new Tp5<>(t0, t1, t2, t3, t4);
  }

  @Override
  public String toString() {
    return "{" + _0 + ',' + _1 + ',' + _2 + ',' + _3 + ',' + _4 + '}';
  }
}
