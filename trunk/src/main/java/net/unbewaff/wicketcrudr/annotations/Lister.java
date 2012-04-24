package net.unbewaff.wicketcrudr.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.unbewaff.wicketcrudr.providers.ILabelProvider;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Lister {
	int position() default -1;
	String displayAs() default "";
	boolean editInPlace() default false;
	boolean displayHtml() default false;
	@SuppressWarnings("rawtypes")
    Class<? extends ILabelProvider> customLabelProvider() default ILabelProvider.class;
}
