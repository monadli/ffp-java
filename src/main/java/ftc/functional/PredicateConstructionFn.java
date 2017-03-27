package ftc.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import ftc.tuple.Tp;
import ftc.core.PredicateFn;

/**
 * In FL,
 * 
 * <blockquote> <i>Predicate construction</i> &#10214;..&#10215; combines
 * n&#8805;0 functions p<sub>1</sub>, ..., p<sub>n</sub> (regarded as
 * predicates), giving the new total predicate defined by:
 * 
 * <table>
 * <tr>
 * <td></td>
 * <td rowspan="3" style='font-size:5em;font-weight:lighter'>{</td>
 * <td>p<sub>i</sub>:x</td>
 * <td>if p<sub>i</sub>:x is abnormal (smallest i)</td>
 * </tr>
 * 
 * <tr>
 * <td>&nbsp;&#10214;p<sub>1</sub>, ..., p<sub>n</sub>&#10215;:x =</td>
 * <td><u>true</u></td>
 * <td>if x = &lt;x<sub>1</sub>,...x<sub>n</sub>&gt; and p<sub>i</sub>:x is true for each i = 1,...,n</td>
 * </tr>
 * <tr>
 * <td></td>
 * <td><u>false</u></td>
 * <td>otherwise</td>
 * </tr>
 * </table>
 * 
 * A total predicate is a function that yields <u>true</u> or <u>false</u> for all normal values.
 * </blockquote>
 * 
 * This requires that each predicate takes any type of object.
 * 
 * @author Jian Li
 */
@SuppressWarnings("rawtypes")
public class PredicateConstructionFn implements PredicateFn<Tp> {

	protected List<Predicate<? super Object>> predicates = new ArrayList<>();

	@SafeVarargs
	public PredicateConstructionFn(Predicate<? super Object>... predicates) {
		for (Predicate<? super Object> p : predicates)
			this.predicates.add(p);
	}

	public static PredicateConstructionFn of(Predicate<? super Object> predicate) {
		return new PredicateConstructionFn(predicate);
	}
	
	public void combine(Predicate<? super Object> predicate) {
		predicates.add(predicate);
	}
	
	@Override
	public boolean test(Tp t) {
		if (t.size() != predicates.size())
			return false;

		int i = 0;
		for (Predicate<? super Object> p : predicates) {
			if (!p.test(t.get(i++)))
				return false;
		}
		return true;
	}
}
