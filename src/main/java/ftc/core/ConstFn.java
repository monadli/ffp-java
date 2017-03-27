package ftc.core;

/**
 * This form is a constant-value function in FL.
 * 
 * <p>
 * In FL Luanguage Manual, it is stated as:
 * 
 * <blockquote> The constant-valued function &#732;x is built from the value x
 * and the constant combining form &#732;; when applied to any normal value y it
 * yields x. </blockquote>
 * 
 * @author Jian Li
 *
 * @param <I>
 *            any input type
 * @param <O>
 *            constant output type
 */
public class ConstFn<I, O> implements Fn<I, O> {

  private final O constant;

  /**
   * Creates a ConstFn.
   * 
   * @param constant constant to be wrapped
   */
  public ConstFn(O constant) {
    this.constant = constant;
  }

  /**
   * Static constructor.
   * 
   * @param constant constant to be always returned
   * 
   * @return wrapped constant
   */
  public static <I, O> ConstFn<I, O> of(O constant) {
    return new ConstFn<>(constant);
  }

  @Override
  public O apply(I t) {
    return constant;
  }

  @Override
  public String toString() {
      return constant.toString();
  }
}