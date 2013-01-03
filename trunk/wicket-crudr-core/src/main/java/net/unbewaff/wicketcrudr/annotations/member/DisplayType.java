package net.unbewaff.wicketcrudr.annotations.member;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.unbewaff.wicketcrudr.annotations.Lister.Display;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DisplayType {
	
	/**
     * Type of Display in a List.
     * @author David Hendrix (Nicktarix)
     *
     */
    public enum Display implements Serializable {
        /**
         * Plain as-is-display using wicket converters
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
     * defines the way the value is displayed.
     * @return
     */
    Display value() default Display.DEFAULT;
    
    /**
     * @return the resourcePrefix used for StringResources.
     */
    String resourcePrefix() default "";

}
