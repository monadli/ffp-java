package ftc.tuple;

import ftc.core.Fn;

public class TpSelector<I extends Tp<?>, T> implements Fn<I, T> {

  private int index;

  public TpSelector(int index) {
    this.index = index;
  }

  public static <I extends Tp<?>, T> TpSelector<I, T> __(int index) {
    return new TpSelector<>(index);
  }

  @SuppressWarnings("unchecked")
  @Override
  public T apply(I t) {
    return (T) t.get(index);
  }
}
