package ftc.core;

import java.util.function.Function;

import ftc.functional.CompositionFn;
import ftc.functional.ConstructionFn;
import ftc.functional.ConsumerFnWrapper;
import ftc.tuple.Tp2;
import ftc.tuple.Tp3;
import ftc.tuple.Tp4;
import ftc.tuple.Tp5;
import ftc.tuple.Tp6;
import ftc.tuple.Tp7;
import ftc.tuple.Tp8;
import ftc.tuple.TpN;

/**
 * This interface is the root of any function in functional tuple calculus.
 * <p>
 * Since it extends {@link java.util.function.Function Function}, it has all composition functionalities with different implementations.
 * <p>
 * Besides composition functionalities from Function, two very special symbols are carefully chosen
 * to represent two special form of function compositions:
 * 
 * <ol>
 * <li>ǁ: pipe, or chain another function which has the input type the same as the output type of
 * this function
 * <li>ǂ: fork, or distribute the same input into multiple functions that have the same input type,
 * and produce a tuple of outputs.
 * </ol>
 * 
 * @param <I> input type
 * @param <O> output type
 */
public interface Fn<I, O> extends Function<I, O> {

  /**
   * Tells if the function is pure.
   * 
   * @return true pure, false otherwise. Default is true.
   */
  default boolean isPure() {
    return true;
  }

  /**
   * Compose with another function into a new function.
   * 
   * This takes the same form of inputs and produces the same form of function as Function does. The
   * only exception is that the composed function does not take form of lambda. Instead, it returns
   * a composition form to give more transparency.
   * 
   * @see Function#compose(Function)
   */
  @Override
  default <V> Fn<V, O> compose(Function<? super V, ? extends I> before) {
    return new CompositionFn<V, I>(before).andThen(this);
  }

  /**
   * Compose with another function into a new function.
   * 
   * This takes the same form of inputs and produces the same form of function as Function does. The
   * only exception is that the composed function does not take form of lambda. Instead, it returns
   * a composition form to give more transparency.
   * 
   * @see Function#andThen(Function)
   */
  @Override
  default <V> Fn<I, V> andThen(Function<? super O, ? extends V> after) {
    return new CompositionFn<I, O>(this).andThen(after);
  }

  /**
   * Pipe this function to another function {@code after} so that the output of this function
   * becomes the input of the {@code after} function.
   * 
   * <div> <svg width="400" height=80">
   * <line x1='0' y1='30' x2='40' y2='30' style='fill:none;stroke:black' />
   * <rect x='40' y='10' width='100' height='40' style='fill:none;stroke:black' />
   * <line x1='140' y1='30' x2='200' y2='30' style='fill:none;stroke:black' />
   * <rect x='200' y='10' width='100' height='40' style='fill:none;stroke:black' />
   * <line x1='300' y1='30' x2='340' y2='30' style='fill:none;stroke:black' /> <text x='10', y='28'
   * fill='red'>I</text> <text x='170', y='28' fill='red'>O</text> <text x='330', y='28'
   * fill='red'>V</text> <text x='80', y='45'>this</text> <text x='240', y='45'>after</text>
   * 
   * </svg> </div> For example:
   * 
   * <pre>
   * Fn&lt;Double, Double&gt; f = x -> Math.sin(x);
   * f = f.ǁ(x -> Math.cos(x)).ǁ(x -> x + 1);
   * 
   * Fn&lt;Double, Double&gt; f = x -> x + 0.3;
   * Fn&lt;Double, Tp2&lt;Double, Double&gt;&gt; f1 = f.ǂ(x -> Math.cos(x));
   * </pre>
   * 
   * @param after another function with input type the same as the output type of this function
   * @return a composition function wrapping {@code this} and {@code after}
   */
  @SuppressWarnings("unchecked")
  default <V> Fn<I, V> ǁ(Function<? super O, ? extends V> after) {
    if (this instanceof CompositionFn) {
      this.andThen(after);
      return (Fn<I, V>) this;
    }

    return new CompositionFn<I, O>(this).ǁ(after);
  }

  /**
   * <p>
   * Creates a construction function where the input {@code I} is sent parallelly to both this
   * function and a consumer. Since consumer does not produce any output, the output remains the
   * same as result of this function.
   * 
   * <div> <svg width="400" height=120">
   * <rect x='40' y='0' width='200' height='110' style='fill:none;stroke:black' />
   * <line x1='0' y1='55' x2='60' y2='55' style='fill:none;stroke:black' />
   * <line x1='60' y1='30' x2='60' y2='80' style='fill:none;stroke:black' />
   * <line x1='60' y1='30' x2='80' y2='30' style='fill:none;stroke:black' />
   * <line x1='60' y1='80' x2='80' y2='80' style='fill:none;stroke:black' />
   * <rect x='80' y='10' width='100' height='40' style='fill:none;stroke:black' />
   * <line x1='180' y1='30' x2='200' y2='30' style='fill:none;stroke:black' />
   * <line x1='200' y1='30' x2='200' y2='55' style='fill:none;stroke:black' />
   * <line x1='200' y1='55' x2='280' y2='55' style='fill:none;stroke:black' />
   * <rect x='80' y='60' width='100' height='40' style='fill:none;stroke:black' /> <text x='10',
   * y='53' fill='red'>I</text> <text x='260', y='53' fill='red'>O</text> <text x='120',
   * y='45'>this</text> <text x='95', y='95'>consumer</text> </svg> </div>
   * 
   * @param consumer
   * @return a construction function returning output of this function
   */
  default Fn<I, O> ǂ0(ConsumerFn<? super I> consumer) {
    return new ConstructionFn<I, O>(this).ǂ0(consumer);
  }

  /**
   * <p>
   * Creates a construction function where the input {@code I} is sent parallelly to both this
   * function and another function. The output of the construction function is a tuple with output
   * of this function and output of f1 as the tuple elements.
   * 
   * <div> <svg width="400" height=120">
   * <rect x='40' y='0' width='200' height='110' style='fill:none;stroke:black' />
   * <line x1='0' y1='55' x2='60' y2='55' style='fill:none;stroke:black' />
   * <line x1='60' y1='30' x2='60' y2='80' style='fill:none;stroke:black' />
   * <line x1='60' y1='30' x2='80' y2='30' style='fill:none;stroke:black' />
   * <line x1='60' y1='80' x2='80' y2='80' style='fill:none;stroke:black' />
   * <rect x='80' y='10' width='100' height='40' style='fill:none;stroke:black' />
   * <line x1='180' y1='30' x2='280' y2='30' style='fill:none;stroke:black' />
   * <line x1='180' y1='80' x2='280' y2='80' style='fill:none;stroke:black' />
   * <rect x='80' y='60' width='100' height='40' style='fill:none;stroke:black' /> <text x='10',
   * y='58' fill='red'>I</text> <text x='260', y='28' fill='red'>O</text> <text x='260', y='78'
   * fill='red'>O1</text> <text x='120', y='45'>this</text> <text x='125', y='95'>f1</text> </svg>
   * </div>
   * 
   * @param f1 another function producing output O1
   * @return a construction function producing a tuple of two elements from all functions
   */
  default <O1> Fn<I, Tp2<O, O1>> ǂ(Function<? super I, ? extends O1> f1) {
    return new ConstructionFn<I, O>(this).ǂ(f1);
  }

  /**
   * <p>
   * Creates a construction function where the input {@code I} is sent parallelly to this
   * function and functions f1 and f2. The output of the construction function is a tuple with output
   * of this function and outputs of f1 and f2 as the tuple elements.
   * 
   * <div> <svg width="400" height=175">
   * <rect x='40' y='0' width='200' height='160' style='fill:none;stroke:black' />
   * <line x1='0' y1='80' x2='60' y2='80' style='fill:none;stroke:black' />
   * <line x1='60' y1='30' x2='60' y2='130' style='fill:none;stroke:black' />
   * <line x1='60' y1='30' x2='80' y2='30' style='fill:none;stroke:black' />
   * <line x1='60' y1='80' x2='80' y2='80' style='fill:none;stroke:black' />
   * <rect x='80' y='10' width='100' height='40' style='fill:none;stroke:black' />
   * <line x1='180' y1='30' x2='280' y2='30' style='fill:none;stroke:black' />
   * <line x1='180' y1='80' x2='280' y2='80' style='fill:none;stroke:black' />
   * <rect x='80' y='60' width='100' height='40' style='fill:none;stroke:black' /> <text x='10',
   * y='78' fill='red'>I</text> <text x='260', y='28' fill='red'>O</text> <text x='260', y='78'
   * fill='red'>O1</text> <text x='120', y='45'>this</text> <text x='125', y='95'>f1</text>
   * <rect x='80' y='110' width='100' height='40' style='fill:none;stroke:black' />
   * <line x1='180' y1='130' x2='280' y2='130' style='fill:none;stroke:black' />
   * <line x1='60' y1='130' x2='80' y2='130' style='fill:none;stroke:black' /> <text x='125',
   * y='145'>f2</text> <text x='260', y='128' fill='red'>O2</text> </svg> </div>
   * 
   * @param fn individual functions
   * @return a construction function producing a tuple of three elements from all functions
   */
  default <O1, O2> Fn<I, Tp3<O, O1, O2>> ǂ(Function<? super I, ? extends O1> f1,
      Function<? super I, ? extends O2> f2) {
    return new ConstructionFn<I, O>(this).ǂ(f1, f2);
  }

  /**
   * <p>
   * Creates a construction function similar to {@link #ǂ(f1, f2)}.
   *
   * @param fn individual functions
   * @return a construction function producing a tuple of four elements from all functions
   */
  default <O1, O2, O3> Fn<I, Tp4<O, O1, O2, O3>> ǂ(Function<? super I, ? extends O1> f1,
      Function<? super I, ? extends O2> f2, Function<? super I, ? extends O3> f3) {
    return new ConstructionFn<I, O>(this).ǂ(f1, f2, f3);
  }

  /**
   * <p>
   * Creates a construction function similar to {@link #ǂ(f1, f2, f3)}.
   *
   * @param fn individual functions
   * @return a construction function producing a tuple of five elements from all functions
   */
  default <O1, O2, O3, O4> Fn<I, Tp5<O, O1, O2, O3, O4>> ǂ(Function<? super I, ? extends O1> f1,
      Function<? super I, ? extends O2> f2, Function<? super I, ? extends O3> f3,
      Function<? super I, ? extends O4> f4) {
    return new ConstructionFn<I, O>(this).ǂ(f1, f2, f3, f4);
  }

  /**
   * <p>
   * Creates a construction function similar to {@link #ǂ(f1, f2, f3, f4)}.
   *
   * @param fn individual functions
   * @return a construction function producing a tuple of six elements from all functions
   */
  default <O1, O2, O3, O4, O5> Fn<I, Tp6<O, O1, O2, O3, O4, O5>> ǂ(
      Function<? super I, ? extends O1> f1, Function<? super I, ? extends O2> f2,
      Function<? super I, ? extends O3> f3, Function<? super I, ? extends O4> f4,
      Function<? super I, ? extends O5> f5) {
    return new ConstructionFn<I, O>(this).ǂ(f1, f2, f3, f4, f5);
  }

  /**
   * <p>
   * Creates a construction function similar to {@link #ǂ(f1, f2, f3, f4, f5)}.
   *
   * @param fn individual functions
   * @return a construction function producing a tuple of seven elements from all functions
   */
  default <O1, O2, O3, O4, O5, O6> Fn<I, Tp7<O, O1, O2, O3, O4, O5, O6>> ǂ(
      Function<? super I, ? extends O1> f1, Function<? super I, ? extends O2> f2,
      Function<? super I, ? extends O3> f3, Function<? super I, ? extends O4> f4,
      Function<? super I, ? extends O5> f5, Function<? super I, ? extends O6> f6) {
    return new ConstructionFn<I, O>(this).ǂ(f1, f2, f3, f4, f5, f6);
  }

  /**
   * <p>
   * Creates a construction function similar to {@link #ǂ(f1, f2, f3, f4, f5, f6)}.
   *
   * @param fn individual functions
   * @return a construction function producing a tuple of eight elements from all functions
   */
  default <O1, O2, O3, O4, O5, O6, O7> Fn<I, Tp8<O, O1, O2, O3, O4, O5, O6, O7>> ǂ(
      Function<? super I, ? extends O1> f1, Function<? super I, ? extends O2> f2,
      Function<? super I, ? extends O3> f3, Function<? super I, ? extends O4> f4,
      Function<? super I, ? extends O5> f5, Function<? super I, ? extends O6> f6,
      Function<? super I, ? extends O7> f7) {
    return new ConstructionFn<I, O>(this).ǂ(f1, f2, f3, f4, f5, f6, f7);
  }

  /**
   * <p>
   * Creates a construction function similar to {@link #ǂ(f1, f2)}.
   * 
   * @param f a list of functions
   * @return a construction function producing a tuple containing results of all functions
   */
  default Fn<I, TpN> ǂ(@SuppressWarnings("unchecked") Function<? super I, ?>... f) {
    return new ConstructionFn<I, O>(this).ǂ(f);
  }

  /**
   * Wrap this function into a consumer form.
   * 
   * @return a consumer that takes the same input without output
   */
  @SuppressWarnings("unchecked")
  default ConsumerFn<I> toConsumer() {
    return this instanceof ConsumerFn ? (ConsumerFn<I>) this : new ConsumerFnWrapper<I>(this);
  }

  /**
   * Returns an identity function that always returns its input argument.
   *
   * @param <I> the type of the input and output objects to the function
   * @return a function that always returns its input argument
   */
  @SuppressWarnings("unchecked")
  static <I> Fn<I, I> identity() {
    return (Fn<I, I>) id;
  }

  /**
   * An internal static identity function.
   */
  Fn<Object, Object> id = t -> t;
}
