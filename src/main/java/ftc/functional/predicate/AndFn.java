package ftc.functional.predicate;

import java.util.function.Predicate;

import ftc.core.PredicateFn;
import ftc.functional.PredicateCombinationFn;

/**
 * This function represents a special predicate combining form represented as an infix N-ary logical and operator &#8896; in FL as follows:
 * 
 * <blockquote>
 * <table border="1">
 * <thead>
 * <tr>
 * <th>Function</th>
 * <th>Domain; Comment</th>
 * </tr>
 * </thead>
 * <tr>
 * <td><div>&#8896;:&lt;f<sub>1</sub>,...,f<sub>n</sub>&gt;:x</div>
 * <div>p&#8896;q = &#8896;:&lt;p,q&gt;</div></td>
 * <td><div>f<sub>i</sub> functions; true iff f<sub>i</sub>:x is true for all i, i = 1,...,n</div>
 * <div>infix</div>
 * </td>
 * </tr>
 * </table>
 * </blockquote>
 * 
 * @author Jian Li
 *
 * @param <I> input type
 */
public class AndFn<I> extends PredicateCombinationFn<I> {

  public AndFn(Predicate<I> first) {
    super(first);
  }

  public static <I> AndFn<I> with(Predicate<I> first) {
    return new AndFn<>(first);
  }

  @Override
  public PredicateFn<I> and(Predicate<? super I> other) {
    predicates.add(other);
    return this;
  }

  @Override
  public boolean test(I t) {
    for (Predicate<? super I> p : predicates)
      if (!p.test(t))
        return false;

    return true;
  }
}
