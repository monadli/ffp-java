package ftc.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import ftc.core.Fn;

/**
 * This function represents a composition function in FL.
 * 
 * <p>
 * In FL Language Manual, it is stated as follows:
 * 
 * <blockquote> Composition (&#9900;) combines functions f and g, giving the new
 * function f&#9900;g, defined by <br>
 * <br>
 * &nbsp;&nbsp;&nbsp;&nbsp;(f&#9900;g):x = f:(g:x) </blockquote>
 * 
 * @param <I>
 *            input type
 * @param <O>
 *            output type
 */
public class CompositionFn<I, O> implements Fn<I, O> {

	protected List<Function<?, ?>> fns;

	/**
	 * Instantiate a composition.
	 * 
	 * @param first first function in the composition.
	 */
	public CompositionFn(Function<? super I, ? extends O> first) {
		fns = new ArrayList<>();
		fns.add(first);
	}

	/**
	 * Creates a composition.
	 * 
	 * @param first first function in the composition.
	 * @return a composition containing just first function
	 */
	public static <I, O> CompositionFn<I, O> of(Function<? super I, ? extends O> first) {
		return new CompositionFn<>(first);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V> Fn<I, V> andThen(Function<? super O, ? extends V> after) {
		fns.add(after);
		return (Fn<I, V>) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public O apply(I in) {
		Object o = ((Fn<I, O>) fns.get(0)).apply(in);
		int size = fns.size();
		if (size == 1)
			return (O) o;

		for (int i = 1; i < fns.size(); i++)
			o = ((Function<Object, Object>) fns.get(i)).apply(o);

		return (O) o;
	}
}
