package net.unbewaff.wicketcrudr.annotations;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation defining if and how a value is displayed in a List.
 * @author David Hendrix (Nicktarix)
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
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

    /**
     * Type of inplace editor to use
     * @author David Hendrix (Nicktarix)
     *
     */
    public enum InPlaceEditor implements Serializable {
    	/**
    	 * Don't create an in place editor
    	 */
    	NONE,
    	/**
    	 * Replace label with editor component on click
    	 */
    	INPLACE,
    	/**
    	 * Open a ModalWindow with the editor component
    	 */
    	MODAL,
    	/**
    	 * Pop up a floating container with a high z-index
    	 */
    	RAISEDCONTAINER
    }

	/**
	 * A resource-prefix used to define the resource loaded for displaying the header and values as [resourcePrefix].[propertyname] for headers and [resourcePrefix].[propertyvalue] for data. If you don't define this, the className will be used.
	 * @return
	 */
	String resourcePrefix() default "";
	/**
	 * Should an in-place-editor be used to edit this property
	 * @return
	 */
	InPlaceEditor editInPlace() default InPlaceEditor.NONE;
	/**
	 * Are there model-strings to escape
	 * @return
	 */
	boolean escapeModelString() default true;

}
