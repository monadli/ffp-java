package ftc.tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Base for tuple.
 * <p>
 * A tuple is an immutable sequence with index 0-based, which can be used to store elements of
 * heterogeneous types.
 * <p>
 * The tuple is for supporting functions taking multiple arguments without defining different types
 * of functions. With the tuple types, all functions or suppliers or consumers root from Function.
 * <p>
 * Tuples can be nested. Any element of a tuple can be a single type, or another tuple. Thus in
 * essence, a tuple can be used to define anonymous data structure.
 * <p>
 * For example,
 * 
 * <pre>
 * Tp3&lt;Integer, Double, Tp2&lt;String, Integer&gt;&gt;
 * </pre>
 * <p>
 * For simplicity, its elements can be accessed with _0, _1, etc.
 * <p>
 * The maximum supported tuples with distinct types is TP8. TpN can be used for any tuple more than
 * 8 elements.
 * <p>
 * The tuple classes are intentionally designed to be hierarchical with Tp on top and TpN at the
 * bottom. With such hierarchy, Any function taking a tuple, e.g. Tp5, can take any tuples beyond
 * Tp5, such as Tp7. This is especially useful passing extra elements into the same tuple.
 * 
 * @author Jian Li
 *
 * @param <T0> 0-th element
 */
public class Tp<T0> {

  public static final Tp<?> EMPTY = new Tp<>();

  /**
   * Size of tuple.
   */
  protected int size;

  /**
   * Elements.
   */
  protected Object[] elms;

  protected static final Object[] empty_array = new Object[0];

  public final T0 _0;

  public Tp(T0 t0) {
    this.elms = new Object[] {t0};
    size = elms.length;
    this._0 = t0;
  }

  @SuppressWarnings("unchecked")
  protected Tp(Object... elms) {
    this.elms = elms;
    size = elms.length;
    _0 = (T0) get(0);
  }

  /**
   * Returns i-<i>th</i> element.
   * 
   * @param index 1- based index
   * @return
   */
  public Object get(int index) {
    return (index >= 0 && index < elms.length) ? elms[index] : null;
  }

  public int size() {
    return elms.length;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public Object[] toArray() {
    if (size == 0)
      return empty_array;

    Object[] copy = new Object[size];
    System.arraycopy(elms, 0, copy, 0, size);
    return copy;
  }

  public List<Object> toList() {
    if (size == 0)
      return Collections.emptyList();

    List<Object> ret = new ArrayList<>();
    for (Object e : elms)
      ret.add(e);

    return ret;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(elms);
    result = prime * result + size;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    Class<?> cls = obj.getClass();
    if (!Tp.class.isAssignableFrom(cls))
      return false;
    Tp<?> other = (Tp<?>) obj;
    if (size != other.size)
      return false;
    if (!Arrays.equals(elms, other.elms))
      return false;
    return true;
  }

  /**
   * Returns string representation of the tuple in form of json.
   */
  @Override
  public String toString() {
    return "{" + _0 + '}';
  }
}
