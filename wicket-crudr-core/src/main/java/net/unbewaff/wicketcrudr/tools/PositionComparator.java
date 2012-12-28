package net.unbewaff.wicketcrudr.tools;

import java.lang.reflect.Method;
import java.util.Comparator;

import net.unbewaff.wicketcrudr.annotations.Order;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class PositionComparator implements Comparator<Method> {
	@Override
	public int compare(Method o1, Method o2) {
		Integer p1 = getPosition(o1);
		Integer p2 = getPosition(o2);
		return p1.compareTo(p2);
	}

	/**
	 * @param o1
	 * @return
	 */
	private int getPosition(Method o1) {
		int value = Integer.MAX_VALUE;
		Order annotation = o1.getAnnotation(Order.class);
		if (annotation != null) {
			value = annotation.value();
		}
		return value;
	}
}