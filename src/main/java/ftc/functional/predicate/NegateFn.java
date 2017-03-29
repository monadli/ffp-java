package ftc.functional.predicate;

import java.util.function.Predicate;

import ftc.core.PredicateFn;

/**
 * This form represents a special predicate combining form represented as an infix not operator &#0172; in FL as follows:
 * 
 * <blockquote>
 * <table border="1">
 * <thead>
 * <tr>
 * <th>Function/examples</th>
 * <th>Domain; Comment</th>
 * </tr>
 * </thead>
 * <tr>
 * <td><div>&#0172;:p:x</div>
 * <div>&#0172;:isint:3 = false</div></td>
 * <td><div>p a functions; true iff p:x = false</div>
 * <div>&#0172;:p = not&#8728;p</div>
 * </td>
 * </tr>
 * </table>
 * </blockquote>
 * 
 * @author Jian Li
 *
 * @param <I> input type
 */
public class NegateFn<I> implements PredicateFn<I> {
  
  private Predicate<? super I> predicate;

  /**
   * Predicate wrapper that wraps predicate.
   */
  private PredicateFn<I> wrapper = x -> predicate.test(x);

  public NegateFn(Predicate<I> p) {
      predicate = p;
  }

  /**
   * Returns the wrapped predicate that represents the logical negation of this
   * predicate.
   */
  @SuppressWarnings("unchecked")
  @Override
  public PredicateFn<I> negate() {
      return predicate instanceof PredicateFn ? (PredicateFn<I>) predicate : wrapper;
  }

  @Override
  public boolean test(I t) {
      return !predicate.test(t);
  }
}
