package ftc.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import ftc.core.PredicateFn;

/**
 * This is an abstract function container with predicates taking the same input I.
 * 
 * The behavior of test() needs to be provided by descendant classes.
 * 
 * @author Jian Li
 *
 * @param <I> input type
 */
public abstract class PredicateCombinationFn<I> implements PredicateFn<I> {

  /**
   * List of predicates.
   */
  protected List<Predicate<? super I>> predicates = new ArrayList<>();

  protected PredicateCombinationFn(Predicate<I> first) {
      predicates.add(first);
  }
}
