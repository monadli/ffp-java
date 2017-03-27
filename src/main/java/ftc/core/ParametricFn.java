package ftc.core;

/**
 * A parametric function is a special type of functions that involves in extra
 * parameters, or even resources that needs to be initialized or released. This
 * is especially useful when initialization takes certain time.
 * 
 * This function and descendants has a life-cycle of two states: started and
 * stopped.
 * 
 * At runtime, it can switch between the two states as many times as needed.
 * 
 * Such function needs to be registered via function context, and the system
 * will invoke the start() at the first time it is called. When that happens,
 * all calling threads will be on hold until start() returns. This is guaranteed
 * by using Java Memory Model.
 * 
 * The parametric and pureness are orthogonal. A parametric function can be pure
 * as well.
 * 
 * For a function with constant parameters, there is no need for this interface.
 * 
 * @author Jian Li
 *
 * @param <I>
 *            input type
 * @param <O>
 *            output type
 */
public abstract class ParametricFn<I, O> implements Fn<I, O> {

	/**
	 * This indicates if the function is stopped.
	 * 
	 * This is volatile to make sure it has visibility to all threads once the
	 * value is changed.
	 */
	private volatile boolean stopped = true;

	/**
	 * Starts this function once all dependency Fns are present. It should only
	 * take care of this function's startup, not startup of contained funcions,
	 * if any, which is handled by the system.
	 * 
	 * @return true function is stared and ready to serve.
	 */
	protected boolean start() {
		return true;
	}

	/**
	 * Stops this function. When overriding this method, it should only take
	 * care of this function's stop, not contained functions stop, if any, which
	 * is handled by the function system.
	 */
	protected void stop() {
		stopped = true;
	}

	public final boolean isStopped() {
		return stopped;
	}
}
