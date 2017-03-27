package ftc.core;

import java.util.function.Predicate;

/**
 * This is predicate function that always returns boolean.
 * 
 * @author Jian Li
 *
 * @param <I> generic input type
 */
public interface PredicateFn<I> extends Predicate<I>, Fn<I, Boolean> {

	@Override
	default Boolean apply(I t) {
		return test(t);
	}
}
