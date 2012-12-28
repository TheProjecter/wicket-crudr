/**
 * 
 */
package net.unbewaff.wicketcrudr.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation defining the order of List and Edit elements for display
 * @author David Hendrix (Nicktarix)
 *
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Order {
    /**
     * Defines the order of display. With Java 7 you'll get inconsistent display sequences even between calls if you don't define this.
     * @return
     */
    int value() default -1;
}
