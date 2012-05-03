package net.unbewaff.wicketcrudr.annotations;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.unbewaff.wicketcrudr.providers.label.ILabelProvider;

/**
 * Annotation defining if and how a value is displayed in a List.
 * @author David Hendrix (Nicktarix)
 *
 */
/**
 * @author David Hendrix (Nicktarix)
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Lister {
    /**
     * Type of Display in a List.
     * @author David Hendrix (Nicktarix)
     *
     */
    public enum Display implements Serializable {
        /**
         * Plain as-is-display
         */
        DEFAULT,
        /**
         * Display using a StringResource. A prefix can be defined by {@link resourcePrefix}.
         */
        RESOURCE,
        /**
         * Display as a disabeled CheckBox (only for Boolean values)
         */
        CHECKBOX;
    }
    Display displayAs() default Display.DEFAULT;
    /**
     * Defines the order of display. With Java 7 you'll get inconsistent display sequences even between calls if you don't define this.
     * @return
     */
    int position() default -1;
	/**
	 * A resource-prefix used to define the resource loaded for displaying the header and values as [resourcePrefix].[propertyname] for headers and [resourcePrefix].[propertyvalue] for data. If you don't define this, the className will be used.
	 * @return
	 */
	String resourcePrefix() default "";
	/**
	 * Should an in-place-editor be used to edit this property
	 * @return
	 */
	boolean editInPlace() default false;
	/**
	 * Are there model-strings to escape
	 * @return
	 */
	boolean escapeModelString() default true;
}
