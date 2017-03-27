package ftc.functional;

import java.util.List;
import java.util.function.Function;

import ftc.core.Fn;
import ftc.tuple.Tp2;

/**
 * Reduce function that reduces a list of elements into one.
 * 
 * @author Jian Li
 *
 * @param <T> input element type
 */
public class ReduceFn<T> implements Fn<List<T>, T> {

	protected Function<Tp2<T, T>, T> reducer;
	protected T initialVal;

	/**
	 * Creates a reduce with a reducer and initial value.
	 * 
	 * @param reducer a function that reduces two values into one
	 * @param initialVal initial value
	 */
	public ReduceFn(Function<Tp2<T, T>, T> reducer, T initialVal) {
		this.reducer = reducer;
		this.initialVal = initialVal;
	}
	
	/**
	 * @see {@link Function#apply(Object)}
	 */
	@Override
	public T apply(List<T> t) {
		T r = initialVal;
		for (T v : t)
			r = reducer.apply(new Tp2<T, T>(r, v));

		return r;
	}
}
