package ftc.tuple;

/**
 * This class represents a triple, or triad.
 * 
 * @author Jian Li
 *
 * @param <Tn> types of elements
 */
public class Tp3<T0, T1, T2> extends Tp2<T0, T1> {

  public final T2 _2;

  public Tp3(T0 t0, T1 t1, T2 t2) {
    super(t0, t1, t2);
    this._2 = t2;
  }

  public static <T0, T1, T2> Tp3<T0, T1, T2> __(T0 t0, T1 t1, T2 t2) {
    return new Tp3<>(t0, t1, t2);
  }

  @SuppressWarnings("unchecked")
  protected Tp3(Object... tuples) {
    super(tuples);
    _2 = (T2) get(2);
  }

  @SuppressWarnings("unchecked")
  protected Tp3(int fromIndex, int toIndex, Object... tuple) {
    super(fromIndex, toIndex, tuple);
    _2 = (T2) get(3);
  }

  @Override
  public String toString() {
    return "{" + _0 + ',' + _1 + ',' + _2 + '}';
  }
}
