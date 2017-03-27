package ftc.functional;

import ftc.core.ConsumerFn;
import ftc.core.Fn;

/**
 * This class wraps any function into a consumer.
 * 
 * It will be useful only for wrapping an impure function.
 * 
 * @author Jian Li
 *
 * @param <I>
 */
public class ConsumerFnWrapper<I> implements ConsumerFn<I> {

  protected Fn<I, ?> f;

  public ConsumerFnWrapper(Fn<I, ?> f) {
    this.f = f;
  }

  /**
   * Creates a consumer wrapper that wraps two consumers.
   * 
   * @param c1 consumer
   * @param c2 consumer
   * @return consumer wrapper
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <I> ConsumerFnWrapper<I> __(ConsumerFn<? super I> c1, ConsumerFn<? super I> c2) {
    if (c1 instanceof ConsumerFnWrapper) {
      ConsumerFnWrapper c = (ConsumerFnWrapper) c1;
      if (c.f instanceof ConstructionFn) {
        ((ConstructionFn) c.f).ffs.add(c2);
        return c;
      }
    }

    return new ConsumerFnWrapper<>(new ConstructionFn<I, Void>(c1).ǂ0(c2));
  }

  @Override
  public void accept(I t) {
    f.apply(t);
  }
}
