/**
 *
 */
package net.unbewaff.wicketcrudr.annotations.member;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface InnerPrototype {

	/**
	 * @author David Hendrix (Nicktarix)
	 *
	 */
	public enum DisplayType implements Serializable {
		CONCATENATED("concatenatedStyle"),
		LIST("listStyle");

		private final String fragmentId;

		private DisplayType(String fragmentId) {
			this.fragmentId = fragmentId;
		}

		public String getFragmentId() {
			return fragmentId;
		}
	}

	String resourcePrefix() default "";

	Class<?> type() default Object.class;

	String separator() default ", ";

	DisplayType displayAs() default DisplayType.LIST;
}
