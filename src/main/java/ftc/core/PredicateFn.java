package ftc.core;

import java.util.function.Predicate;

import ftc.functional.predicate.AndFn;
import ftc.functional.predicate.NegateFn;
import ftc.functional.predicate.OrFn;

/**
 * This is predicate function that always returns boolean.
 * 
 * @author Jian Li
 *
 * @param <I> generic input type
 */
public interface PredicateFn<I> extends Predicate<I>, Fn<I, Boolean> {

  @Override
  default PredicateFn<I> and(Predicate<? super I> other) {
    return AndFn.with(this).and(other);
  }

  @Override
  default PredicateFn<I> negate() {
    return new NegateFn<>(this);
  }

  @Override
  default PredicateFn<I> or(Predicate<? super I> other) {
    return OrFn.with(this).or(other);
  }

  @Override
  default Boolean apply(I t) {
    return test(t);
  }
}
