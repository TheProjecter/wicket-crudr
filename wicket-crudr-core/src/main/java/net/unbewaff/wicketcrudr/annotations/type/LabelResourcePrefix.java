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
 * Annotation defining the ResourcePrefix used to create the StringRecourceModels for displaying the class' 
 * Labels.
 * 
 * Example:
 * <pre>
 * @LabelResourcePrefix("Example")
 * public class Name implements Serializable {
 * 
 *     private String firstName;
 *     private String lastName;
 *     
 *     public String getFirstName() {
 *         return firstName;
 *     }
 *     
 *     public void setFirstName(String firstName) {
 *         this.firstName = firstName;
 *     }
 *     
 *     public String getLastName() {
 *         return lastName;
 *     }
 *     
 *     public void setLastName(String lastName) {
 *         this.lastName = lastName;
 *     }
 * }
 * </pre>
 * This will use Example.FirstName and Example.LastName from a matching property-file as Label for the respective 
 * fields. If there is no LabelResourcePrefix annotation is present, the simple classname will be used as a prefix. 
 * If wicket can't find matching resources "FirstName" and "LastName" will be displayed.
 *
 * @author David Hendrix (Nicktarix)
 *
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface LabelResourcePrefix {

	/**
     * Defines the ResourcePrefix.
     * 
     * @return
     */
    String value() default "";
}
