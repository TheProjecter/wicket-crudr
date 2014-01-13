/**
 * 
 */
package net.unbewaff.wicketcrudr.annotations.member;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface StringProvider {

    /**
     * Defines the ResourcePrefix.
     * 
     * @return
     */
    String value() default "";
}
