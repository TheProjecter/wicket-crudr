package net.unbewaff.wicketcrudr.annotations.type;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a type as a scaffolding prototype and can define the basic layout of the resulting display.
 * The root of a data tree doesn't have to be annotated as Prototype but every child node needs this annotation.
 * Classes not annotated as Prototype will be rendered using their registered converters, ignoring annotations 
 * within these classes.
 * 
 * @author David Hendrix (Nicktarix)
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Prototype {
	
	Prototypes value() default Prototypes.DEFAULT;

}
