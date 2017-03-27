package ftc.functional;

import java.util.function.Function;
import java.util.function.Predicate;

import ftc.core.Fn;

/**
 * This function form represents a condition form in FL.
 * 
 * In FL Language Manual, it is stated as:
 * 
 * <blockquote> Condition (&#8594;) combines three functions p, f and g, giving
 * the new function p&#8594;f ; g defined by:
 * <p>
 * <table>
 * <tr>
 * <td></td>
 * <td rowspan="4" style='font-size:5em;font-weight:lighter'>{</td>
 * <td>x</td>
 * <td>if x is abnormal</td>
 * </tr>
 * <tr>
 * <td></td>
 * <td>f:x</td>
 * <td>if p:x = true (a normal value &#8800; <u>false</u>)</td>
 * </tr>
 * <tr>
 * <td>&nbsp;(p&#8594;f ; g):x =</td>
 * <td>g:x</td>
 * <td>if p:x = <u>false</u></td>
 * </tr>
 * <tr>
 * <td></td>
 * <td>exception</td>
 * <td>if p:x = exception</td>
 * </tr>
 * <tr>
 * <td></td>
 * <td></td>
 * <td>&perp;</td>
 * <td>if p:x = &perp;</td>
 * </tr>
 * </table>
 * </blockquote> Note that in FL, it is stated that
 * 
 * <blockquote> the definition of condition means that any function p can serve
 * as a "predicate"; ... the words "function" and "predicate" are used
 * interchangeably. The phrases "p:x is true" and "x satisfies p" indicate that
 * p:x is any normal value other than <u>false</u>. </quote> </blockquote>
 * 
 * Considering an explicit {@code Predicate} defined in Java 8, we will make
 * slight change to {@code p} to enforce it as a {@code Predicate}.
 * 
 * @author Jian Li
 *
 * @param <I>
 *            input type
 * @param <O>
 *            output type
 */
public class ConditionFn<I, O> implements Fn<I, O> {

	private Predicate<I> condition;

	private Function<I, O> then;

	private Function<I, O> otherwise;

	private ConditionFn(Predicate<I> condition) {
		this.condition = condition;
	}

	/**
	 * Returns a new {@code Condition} by taking a {@link Predicate}.
	 * 
	 * @param condition a predicate
	 * @return a new condition
	 */
	public static <I, O> ConditionFn<I, O> ifTrue(Predicate<I> condition) {
		return new ConditionFn<>(condition);
	}

	/**
	 * Take {@code then} function and return the same {@code Condition}. The
	 * {@code then} function will be executed if {@code condition} returns true.
	 * 
	 * @param then the function that will be executed if {@code condition} returns true
	 * @return the same {@code Condition}
	 */
	public ConditionFn<I, O> then(Function<I, O> then) {
		this.then = then;
		return this;
	}

	/**
	 * Takes an function as {@code otherwise} and returns an {@code Fn}.
	 * 
	 * The otherwise function will be invoked if {@code condition} returns false.
	 * 
	 * @param otherwise function that will be invoked if {@code condition} returns false
	 * @return an {@code Fn}
	 */
	public Fn<I, O> otherwise(Function<I, O> otherwise) {
		this.otherwise = otherwise;
		return this;
	}

	@Override
	public O apply(I in) {
		return condition.test(in) ? then.apply(in) : otherwise == null ? null : otherwise.apply(in);
	}
}
