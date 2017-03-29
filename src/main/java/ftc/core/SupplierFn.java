package ftc.core;

import java.util.function.Supplier;

import ftc.functional.SupplierFnWrapper;
import ftc.tuple.Tp2;
import ftc.tuple.Tp3;
import ftc.tuple.Tp4;
import ftc.tuple.Tp5;
import ftc.tuple.Tp6;
import ftc.tuple.Tp7;
import ftc.tuple.Tp8;
import ftc.tuple.TpN;

/**
 * A supplier is a special function, which does not have input.
 * <p>
 * The {@link Supplier} in Java is unrelated with {@link java.util.function.Function Function}. This
 * interface inherits both of them, thus making it a special function that takes {@link Void} as
 * input. Since {@code Void} is uninstantiable, this class as a function always takes null as input
 * to produce output.
 * <p>
 * This is a special type of functions that takes null and returns something.
 * <p>
 * Implementation of this interface requires implementation of {@link #Supplier.get()} only.
 * 
 * @param <O> output type
 */
public interface SupplierFn<O> extends Fn<Void, O>, Supplier<O> {

  /**
   * Composes a new supplier by combining with another supplier s1. The new supplier is with a tuple
   * as the output that has outputs from this and s1 as elements.
   * <p>
   * The topology is as follows:
   * 
   * <div> <svg width="400" height=120">
   * <rect x='80' y='0' width='160' height='110' style='fill:none;stroke:black' />
   * <line x1='0' y1='55' x2='100' y2='55' style='fill:none;stroke:black' stroke-dasharray="5, 5"/>
   * <line x1='100' y1='30' x2='100' y2='80' style='fill:none;stroke:black' stroke-dasharray="5,
   * 5"/>
   * <line x1='100' y1='30' x2='120' y2='30' style='fill:none;stroke:black' stroke-dasharray="5,
   * 5"/>
   * <line x1='100' y1='80' x2='120' y2='80' style='fill:none;stroke:black' stroke-dasharray="5,
   * 5"/> <rect x='120' y='10' width='100' height='40' style='fill:none;stroke:black' />
   * <line x1='220' y1='30' x2='280' y2='30' style='fill:none;stroke:black' />
   * <line x1='220' y1='80' x2='280' y2='80' style='fill:none;stroke:black' />
   * <rect x='120' y='60' width='100' height='40' style='fill:none;stroke:black' /> <text x='10',
   * y='53' fill='red'>Void (null)</text> <text x='260', y='28' fill='red'>O</text> <text x='260',
   * y='78' fill='red'>O1</text> <text x='120', y='45'>this</text> <text x='125', y='95'>s1</text>
   * </svg> </div>
   * 
   * @param s1 another supplier
   * @return a composed supplier
   */
  default <O1> SupplierFn<Tp2<O, O1>> ǂ(SupplierFn<? extends O1> s1) {
    return new SupplierFnWrapper<>(Fn.super.ǂ(s1));
  }

  /**
   * Composes a new supplier by combining with two other suppliers s1 and s2. The new supplier is
   * with a tuple as the output that has outputs from this, s1 and s2 as elements.
   * <p>
   * The topology is as follows:
   * 
   * <div> <svg width="400" height=160">
   * <rect x='80' y='0' width='160' height='160' style='fill:none;stroke:black' />
   * <line x1='0' y1='80' x2='100' y2='80' style='fill:none;stroke:black' stroke-dasharray="5, 5"/>
   * <line x1='100' y1='30' x2='100' y2='130' style='fill:none;stroke:black' stroke-dasharray="5,
   * 5"/>
   * <line x1='100' y1='30' x2='120' y2='30' style='fill:none;stroke:black' stroke-dasharray="5,
   * 5"/>
   * <line x1='100' y1='80' x2='120' y2='80' style='fill:none;stroke:black' stroke-dasharray="5,
   * 5"/>
   * <line x1='100' y1='130' x2='120' y2='130' style='fill:none;stroke:black' stroke-dasharray="5,
   * 5"/> <rect x='120' y='10' width='100' height='40' style='fill:none;stroke:black' />
   * <line x1='220' y1='30' x2='280' y2='30' style='fill:none;stroke:black' />
   * <line x1='220' y1='80' x2='280' y2='80' style='fill:none;stroke:black' />
   * <line x1='220' y1='130' x2='280' y2='130' style='fill:none;stroke:black' />
   * <rect x='120' y='60' width='100' height='40' style='fill:none;stroke:black' />
   * <rect x='120' y='110' width='100' height='40' style='fill:none;stroke:black' /> <text x='10',
   * y='73' fill='red'>Void (null)</text> <text x='260', y='28' fill='red'>O</text> <text x='260',
   * y='78' fill='red'>O1</text> <text x='150', y='45'>this</text> <text x='150', y='95'>s1</text>
   * <text x='150' y='145'>s2</text><text x='260', y='128' fill='red'>O2</text> </svg> </div>
   * 
   * @param s1 supplier 1
   * @param s2 supplier 2
   * @return a composed supplier
   */
  default <O1, O2> SupplierFn<Tp3<O, O1, O2>> ǂ(SupplierFn<? extends O1> s1,
      SupplierFn<? extends O2> s2) {
    return new SupplierFnWrapper<>(Fn.super.ǂ(s1, s2));
  }

  /**
   * Similar to {@link #ǂ(s1, s2)} but takes one more supplier.
   * 
   * @param s1 supplier 1
   * @param s2 supplier 2
   * @param s3 supplier 3
   * @return a composed supplier
   */
  default <O1, O2, O3> SupplierFn<Tp4<O, O1, O2, O3>> ǂ(SupplierFn<? extends O1> s1,
      SupplierFn<? extends O2> s2, SupplierFn<? extends O3> s3) {
    return new SupplierFnWrapper<>(Fn.super.ǂ(s1, s2, s3));
  }

  /**
   * Similar to {@link #ǂ(s1, s2, s3)} but takes one more supplier.
   * 
   * @param s1 supplier 1
   * @param s2 supplier 2
   * @param s3 supplier 3
   * @param s4 supplier 4
   * @return a composed supplier
   */
  default <O1, O2, O3, O4> SupplierFn<Tp5<O, O1, O2, O3, O4>> ǂ(SupplierFn<? extends O1> s1,
      SupplierFn<? extends O2> s2, SupplierFn<? extends O3> s3, SupplierFn<? extends O4> s4) {
    return new SupplierFnWrapper<>(Fn.super.ǂ(s1, s2, s3, s4));
  }

  /**
   * Similar to {@link #ǂ(s1, s2, s3, s4)} but takes one more supplier.
   * 
   * @param s1 supplier 1
   * @param s2 supplier 2
   * @param s3 supplier 3
   * @param s4 supplier 4
   * @param s5 supplier 5
   * @return a composed supplier
   */
  default <O1, O2, O3, O4, O5> SupplierFn<Tp6<O, O1, O2, O3, O4, O5>> ǂ(SupplierFn<? extends O1> s1,
      SupplierFn<? extends O2> s2, SupplierFn<? extends O3> s3, SupplierFn<? extends O4> s4,
      SupplierFn<? extends O5> s5) {
    return new SupplierFnWrapper<>(Fn.super.ǂ(s1, s2, s3, s4, s5));
  }

  /**
   * Similar to {@link #ǂ(s1, s2, s3, s4, s5)} but takes one more supplier.
   * 
   * @param s1 supplier 1
   * @param s2 supplier 2
   * @param s3 supplier 3
   * @param s4 supplier 4
   * @param s5 supplier 5
   * @param s6 supplier 6
   * @return a composed supplier
   */
  default <O1, O2, O3, O4, O5, O6> SupplierFn<Tp7<O, O1, O2, O3, O4, O5, O6>> ǂ(
      SupplierFn<? extends O1> s1, SupplierFn<? extends O2> s2, SupplierFn<? extends O3> s3,
      SupplierFn<? extends O4> s4, SupplierFn<? extends O5> s5, SupplierFn<? extends O6> s6) {
    return new SupplierFnWrapper<>(Fn.super.ǂ(s1, s2, s3, s4, s5, s6));
  }

  /**
   * Similar to {@link #ǂ(s1, s2, s3, s4, s5, s6)} but takes one more supplier.
   * 
   * @param s1 supplier 1
   * @param s2 supplier 2
   * @param s3 supplier 3
   * @param s4 supplier 4
   * @param s5 supplier 5
   * @param s6 supplier 6
   * @param s7 supplier 7
   * @return a composed supplier
   */
  default <O1, O2, O3, O4, O5, O6, O7> SupplierFn<Tp8<O, O1, O2, O3, O4, O5, O6, O7>> ǂ(
      SupplierFn<? extends O1> s1, SupplierFn<? extends O2> s2, SupplierFn<? extends O3> s3,
      SupplierFn<? extends O4> s4, SupplierFn<? extends O5> s5, SupplierFn<? extends O6> s6,
      SupplierFn<? extends O7> s7) {
    return new SupplierFnWrapper<>(Fn.super.ǂ(s1, s2, s3, s4, s5, s6, s7));
  }

  /**
   * Similar to {@link #ǂ(s1, s2, s3, s4, s5, s6)} but takes an array of supplier.
   * 
   * @param s array suppliers
   * @return a composed supplier
   */
  default SupplierFn<TpN> ǂ(SupplierFn<?>... s) {
    return new SupplierFnWrapper<>(Fn.super.ǂ(s));
  }

  @Override
  default O apply(Void t) {
    return get();
  }
}
