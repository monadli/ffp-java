package ftc.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import ftc.core.ConsumerFn;
import ftc.core.Fn;
import ftc.tuple.Tp2;
import ftc.tuple.Tp3;
import ftc.tuple.Tp4;
import ftc.tuple.Tp5;
import ftc.tuple.Tp6;
import ftc.tuple.Tp7;
import ftc.tuple.Tp8;
import ftc.tuple.TpN;

/**
 * This function represents function construction in FL.
 * 
 * <p>
 * In FL Language Manual, it is stated as follows: <blockquote> <i>Construction</i> ([...]) combines
 * n &#8805; 0 functions f<sub>1</sub>,...,f<sub>n</sub> giving the new function
 * [f<sub>1</sub>,...,f<sub>n</sub>] defined by:
 * 
 * <br>
 * <br>
 * &nbsp;&nbsp;&nbsp;&nbsp;[f<sub>1</sub>,...,f<sub>n</sub>]:x =
 * &lt;f<sub>1</sub>:x,...,f<sub>n</sub>:x&gt;. </blockquote>
 * 
 * The return of the construction is a tuple with outputs of all functions (excluding consumers) in
 * the construction with the same order.
 */
public class ConstructionFn<I, O> implements Fn<I, O> {

  protected List<Function<? super I, ?>> ffs = new ArrayList<>();

  public ConstructionFn(Function<? super I, ?> first) {
    ffs.add(first);
  }

  /**
   * Adds a consumer into the construction.
   * <p>
   * Since construction does not return anything, it will not leave trace into the output tuple of
   * the construction.
   * 
   * @see Fn#ǂ0(c)
   */
  @Override
  public Fn<I, O> ǂ0(ConsumerFn<? super I> c) {
    ffs.add(c);
    return this;
  }

  /**
   * @see Fn#ǂ(f1)
   */
  @SuppressWarnings("unchecked")
  @Override
  public <O2> Fn<I, Tp2<O, O2>> ǂ(Function<? super I, ? extends O2> f1) {
    ffs.add(f1);
    return (Fn<I, Tp2<O, O2>>) this;
  }

  /**
   * @see Fn#ǂ(f1, f2)
   */
  @SuppressWarnings("unchecked")
  @Override
  public <O1, O2> Fn<I, Tp3<O, O1, O2>> ǂ(Function<? super I, ? extends O1> f1,
      Function<? super I, ? extends O2> f2) {
    ffs.add(f1);
    ffs.add(f2);
    return (Fn<I, Tp3<O, O1, O2>>) this;
  }

  /**
   * @see Fn#ǂ(f1, f2, f3)
   */
  @SuppressWarnings("unchecked")
  @Override
  public <O1, O2, O3> Fn<I, Tp4<O, O1, O2, O3>> ǂ(Function<? super I, ? extends O1> f1,
      Function<? super I, ? extends O2> f2, Function<? super I, ? extends O3> f3) {
    ffs.add(f1);
    ffs.add(f2);
    ffs.add(f3);
    return (Fn<I, Tp4<O, O1, O2, O3>>) this;
  }

  /**
   * @see Fn#ǂ(f1, f2, f3, f4)
   */
  @SuppressWarnings("unchecked")
  @Override
  public <O1, O2, O3, O4> Fn<I, Tp5<O, O1, O2, O3, O4>> ǂ(Function<? super I, ? extends O1> f1,
      Function<? super I, ? extends O2> f2, Function<? super I, ? extends O3> f3,
      Function<? super I, ? extends O4> f4) {
    ffs.add(f1);
    ffs.add(f2);
    ffs.add(f3);
    ffs.add(f4);
    return (Fn<I, Tp5<O, O1, O2, O3, O4>>) this;
  }

  /**
   * @see Fn#ǂ(f1, f2, f3, f4, f5)
   */
  @SuppressWarnings("unchecked")
  @Override
  public <O1, O2, O3, O4, O5> Fn<I, Tp6<O, O1, O2, O3, O4, O5>> ǂ(
      Function<? super I, ? extends O1> f1, Function<? super I, ? extends O2> f2,
      Function<? super I, ? extends O3> f3, Function<? super I, ? extends O4> f4,
      Function<? super I, ? extends O5> f5) {
    ffs.add(f1);
    ffs.add(f2);
    ffs.add(f3);
    ffs.add(f4);
    ffs.add(f5);
    return (Fn<I, Tp6<O, O1, O2, O3, O4, O5>>) this;
  }

  /**
   * @see Fn#ǂ(f1, f2, f3, f4, f5, f6)
   */
  @SuppressWarnings("unchecked")
  @Override
  public <O1, O2, O3, O4, O5, O6> Fn<I, Tp7<O, O1, O2, O3, O4, O5, O6>> ǂ(
      Function<? super I, ? extends O1> f1, Function<? super I, ? extends O2> f2,
      Function<? super I, ? extends O3> f3, Function<? super I, ? extends O4> f4,
      Function<? super I, ? extends O5> f5, Function<? super I, ? extends O6> f6) {
    ffs.add(f1);
    ffs.add(f2);
    ffs.add(f3);
    ffs.add(f4);
    ffs.add(f5);
    ffs.add(f6);
    return (Fn<I, Tp7<O, O1, O2, O3, O4, O5, O6>>) this;
  }

  /**
   * @see Fn#ǂ(f1, f2, f3, f4, f5, f6, f7)
   */
  @SuppressWarnings("unchecked")
  @Override
  public <O1, O2, O3, O4, O5, O6, O7> Fn<I, Tp8<O, O1, O2, O3, O4, O5, O6, O7>> ǂ(
      Function<? super I, ? extends O1> f1, Function<? super I, ? extends O2> f2,
      Function<? super I, ? extends O3> f3, Function<? super I, ? extends O4> f4,
      Function<? super I, ? extends O5> f5, Function<? super I, ? extends O6> f6,
      Function<? super I, ? extends O7> f7) {
    ffs.add(f1);
    ffs.add(f2);
    ffs.add(f3);
    ffs.add(f4);
    ffs.add(f5);
    ffs.add(f6);
    return (Fn<I, Tp8<O, O1, O2, O3, O4, O5, O6, O7>>) this;
  }

  /**
   * @see Fn#ǂ(...)
   */
  @SuppressWarnings("unchecked")
  @Override
  public Fn<I, TpN> ǂ(Function<? super I, ?>... fs) {
    for (Function<? super I, ?> f : fs)
      ffs.add(f);
    return (Fn<I, TpN>) this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public O apply(I t) {
    Object[] res = new Object[ffs.size()];

    int elm = 0;
    for (int i = 0; i < ffs.size(); i++) {
      Function<? super I, ?> f = ffs.get(i);
      Object r = f.apply(t);
      if (!(f instanceof ConsumerFn))
        res[elm++] = r;
    }

    // never return a tuple with one element
    return elm == 0 ? null : elm == 1 ? (O) res[0] : (O) new TpN(res);
  }
}
