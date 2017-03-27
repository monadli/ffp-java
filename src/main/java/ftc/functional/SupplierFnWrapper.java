package ftc.functional;

import ftc.core.Fn;
import ftc.core.SupplierFn;

/**
 * This class wraps a function that takes {@code Void} as a supplier.
 * 
 * @author Jian Li
 *
 * @param <O>
 *            output type
 */
public class SupplierFnWrapper<O> implements SupplierFn<O> {

	protected Fn<Void, O> f;

	/**
	 * Instantiates a supplier wrapper with a function taking {@link Void}.
	 * @param f
	 */
	public SupplierFnWrapper(Fn<Void, O> f) {
		this.f = f;
	}

	@Override
	public O get() {
		return f.apply(null);
	}
}
