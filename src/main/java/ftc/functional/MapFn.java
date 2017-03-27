package ftc.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import ftc.core.Fn;
import ftc.tuple.Tp2;

/**
 * Map function that takes a mapper that maps one element into another of different type.
 * 
 * @author Jian Li
 *
 * @param <I> input element type
 * @param <O> output element type
 */
public class MapFn<I, O> implements Fn<List<I>, List<O>> {

  protected Fn<I, O> mapper;

  /**
   * Creates a map function that maps each output element into another element.
   * 
   * @param mapper
   * @return
   */
  public MapFn(Fn<I, O> mapper) {
    this.mapper = mapper;
  }

  /**
   * Creates a {@code Map} with a mapper.
   * 
   * @param mapper a function that maps a value into another
   * @return a {@code Map} with mapper
   */
  public static <I, O> MapFn<I, O> with(Fn<I, O> mapper) {
    return new MapFn<I, O>(mapper);
  }

  /**
   * Composes with a reducer for map/reduce.
   * 
   * @param reducer reducer
   * @param initialValue initial value for reducer
   * @return a composite function with this map and a reducer
   */
  public Fn<List<I>, O> reduce(Function<Tp2<O, O>, O> reducer, O initialValue) {
    return this.ǁ((new ReduceFn<O>(reducer, initialValue)));
  }

  /**
   * @see {@link Function#apply(Object)}
   */
  @Override
  public List<O> apply(List<I> input) {
    List<O> ret = new ArrayList<>();
    for (I in : input)
      ret.add(mapper.apply(in));
    return ret;
  }
}
