package net.unbewaff.wicketcrudr.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.unbewaff.wicketcrudr.providers.label.ILabelProvider;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Lister {
	int position() default -1;
	String headerKey() default "";
	String displayKey() default "";
	boolean editInPlace() default false;
	boolean escapeModelString() default true;
	@SuppressWarnings("rawtypes")
    Class<? extends ILabelProvider> customLabelProvider() default ILabelProvider.class;
}
