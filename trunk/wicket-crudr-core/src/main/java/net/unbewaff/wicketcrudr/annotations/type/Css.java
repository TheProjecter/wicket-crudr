/**
 * 
 */
package net.unbewaff.wicketcrudr.annotations.type;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation defining a (set of) css-class(es) added to the components created
 * by CRUDr.
 * 
 * The class(es) will be added
 * <ul>
 * <li>to the main container for the prototype along with the class "ui-widget"</li>
 * <li>to the labels along with the class "ui-widget-header" and the name of the property</li>
 * <li>to the values along with the class "ui-widget-content" and the name of the property</li>
 * </ul>
 * 
 * @author David Hendrix (Nicktarix)
 * 
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Css {

	/**
	 * @return the css-classes added to the components
	 */
	String value();
}
