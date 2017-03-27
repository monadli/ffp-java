package ftc.tuple;

import java.util.Arrays;

/**
 * This class represents n-tuple, where n > 8.
 * 
 * @author Jian Li
 */
public class TpN extends Tp8<Object, Object, Object, Object, Object, Object, Object, Object> {

	/**
	 * Empty tuple.
	 */
	public static final TpN TP0 = new TpN();

	public TpN(Object ... tuple) {
		super(tuple);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!Tp.class.isAssignableFrom(obj.getClass()))
			return false;
		Tp<?> other = (Tp<?>) obj;
		if (!Arrays.equals(elms, other.elms))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append('{');
		for (int i = 0; i < elms.length - 1; i++) {
			buf.append(elms[i]).append(',');
		}
		buf.append(elms[elms.length - 1]);
		buf.append('}');
		return buf.toString();
	}
}
