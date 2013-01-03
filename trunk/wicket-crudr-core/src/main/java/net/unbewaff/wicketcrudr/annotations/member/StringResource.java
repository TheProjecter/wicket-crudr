/**
 * 
 */
package net.unbewaff.wicketcrudr.annotations.member;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation defining a member as a key for a StringResource. An optional prefix can be defined as a parameter to
 * this annotation.
 * 
 * Example:
 * <pre>
 * @Prototype
 * public class CreditCard implements Serializable {
 * 
 *     public enum CreditCardType implements Serializable {
 *         MasterCard, Visa, Amex
 *     }
 * 
 *     private CreditCardType type;
 *     private String cardNumber;
 *     private Integer expityMonth;
 *     private Integer expiryYear;
 * 
 *     @StringResource("cardType")
 *     public CreditCardType getType() {
 *         return type;
 *     }
 * 
 *     public void setType(CreditCardType type) {
 *         this.type = type;
 *     }
 * 
 *     public String getCardNumber() {
 *         return cardNumber;
 *     }
 * 
 *     public void setCardNumber(String cardNumber) {
 *         this.cardNumber = cardNumber;
 *     }
 * 
 *     @StringResource("month")
 *     public Integer getExpityMonth() {
 *         return expityMonth;
 *     }
 * 
 *     public void setExpityMonth(Integer expityMonth) {
 *         this.expityMonth = expityMonth;
 *     }
 * 
 *     public Integer getExpiryYear() {
 *         return expiryYear;
 *     }
 * 
 *     public void setExpiryYear(Integer expiryYear) {
 *         this.expiryYear = expiryYear;
 *     }
 * 
 * }
 * </pre>
 * This will use the StringResources cardtype.MasterCard, cardtype.Visa and cardtype.Amex to display (possibly)
 * localized Names of these companies and month.1 to month.12 for localized months names. If these aren't found,
 * the usual String representations of these values are used.
 *
 * @author David Hendrix (Nicktarix)
 *
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface StringResource {

    /**
     * Defines the ResourcePrefix.
     * 
     * @return
     */
    String value() default "";
}
