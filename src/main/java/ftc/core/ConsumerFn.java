package ftc.core;

import java.util.function.Consumer;

import ftc.functional.ConsumerFnWrapper;

/**
 * A consumer is a special function, which does not produce output, or has void output.
 * <p>
 * The {@link Consumer} in Java is unrelated with {@link java.util.function.Function Function}. This interface inherits both of
 * them, thus can be used to compose with other {@code Function}s.
 * 
 * @param <O> output type
 */
public interface ConsumerFn<I> extends Fn<I, Void>, Consumer<I> {

  /**
   * A consumer usually produces side effect, hence impure.
   */
  @Override
  default boolean isPure() {
    return false;
  }

  /**
   * Different from Fn's combine, multiple consumers can be combined at the same level by invoking
   * this method for individual consumers multiple times.
   * 
   * Only {@link #Consumer.accept(T)} needs to be implemented.
   *
   * @param consumer
   * @return a composed consumer
   */
  @Override
  default Consumer<I> andThen(Consumer<? super I> consumer) {
    return ǂ0(x -> consumer.accept(x));
  }

  /**
   * Composes a new consumer wrapping this and the other consumer consuming the same input.
   * 
   * If there are more than two consumers need to be combined into one consumer, this method can be called consecutively as:
   * 
   * 
   * <div> <svg width="400" height=120">
   * <rect x='40' y='0' width='160' height='110' style='fill:none;stroke:black' />
   * <line x1='0' y1='55' x2='60' y2='55' style='fill:none;stroke:black' />
   * <line x1='60' y1='30' x2='60' y2='80' style='fill:none;stroke:black' />
   * <line x1='60' y1='30' x2='80' y2='30' style='fill:none;stroke:black' />
   * <line x1='60' y1='80' x2='80' y2='80' style='fill:none;stroke:black' />
   * <rect x='80' y='10' width='100' height='40' style='fill:none;stroke:black' />
   * <rect x='80' y='60' width='100' height='40' style='fill:none;stroke:black' />
   * <line x1='200' y1='55' x2='280' y2='55' style='fill:none;stroke:black' stroke-dasharray="5,
   * 5"/> <text x='10', y='53' fill='red'>I</text> <text x='120', y='45'>this</text> <text x='125',
   * y='95'>s1</text><text x='205', y='50' fill='red'>Void (null)</text> 
   * </svg> </div>
   * 
   */
  @Override
  default ConsumerFn<I> ǂ0(ConsumerFn<? super I> consumer) {
    return ConsumerFnWrapper.__(this, consumer);
  }

  @Override
  default Void apply(I t) {
    accept(t);
    return null;
  }
}
