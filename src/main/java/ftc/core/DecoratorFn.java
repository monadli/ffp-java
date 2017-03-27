package ftc.core;

import java.util.Objects;
import java.util.function.Function;

/**
 * A decorator is a very special type of function that wraps a function of the same input and output
 * type.
 * 
 * The purpose of a decorator is to cause some side effects at system level while keeping the
 * functionality of the decorated functionality. For example, side effects for monitoring.
 * 
 * The side effects can be modification of input and output as well. When that happens, such
 * function becomes impure. Make sure to return false from {@link #isPure()}.
 * 
 * @author Jian Li
 */
public abstract class DecoratorFn<I, O> extends ParametricFn<I, O> {

  protected Function<I, O> decorated;

  protected DecoratorFn(Function<I, O> decorated) {
    Objects.requireNonNull(decorated);
    this.decorated = decorated;
  }

  /**
   * Override this method to create side effects based on or to in.
   * 
   * @param in input
   * @return same input or input with possible modification
   */
  protected I before(I in) {
    return in;
  }

  /**
   * Override this method to create side effects based on or to in and out.
   * 
   * @param in
   * @param out
   * @return same output or output with possible modification
   */
  protected O after(I in, O out) {
    return out;
  }

  /**
   * Perform the the decorated function based on possible side effects to the input and output. This
   * method is final thus there is no way to alter the original functionality of the decorated
   * function.
   */
  @Override
  public final O apply(I in) {
    I modified = before(in);
    return after(modified, decorated.apply(modified));
  }
}
