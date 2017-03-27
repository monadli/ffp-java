package ftc.tuple;

/**
 * This class represents a double, or 2-tuple.
 * 
 * @author Jian Li
 * 
 * @param <Tn> types of elements
 */
public class Tp2<T0, T1> extends Tp<T0> {

	/**
	 * Second element.
	 */
	public final T1 _1;

	@SuppressWarnings("unchecked")
	protected Tp2(Object... elms) {
		super(elms);
		_1 = (T1) get(1);
	}
	
	public Tp2(T0 t0, T1 t1) {
		super(t0, t1);
		this._1 = t1;
	}

	public static <T0, T1> Tp2<T0, T1> __(T0 t0, T1 t1) {
		return new Tp2<>(t0, t1);
	}

	@Override
	public String toString() {
		return "{" + _0 + ',' + _1 + '}';
	}
}
