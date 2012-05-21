/**
 *
 */
package net.unbewaff.wicketcrudr.providers.validator;

import org.apache.wicket.validation.INullAcceptingValidator;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.AbstractValidator;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class RequiredValidator<T> extends AbstractValidator<T> {

    /* (non-Javadoc)
     * @see org.apache.wicket.validation.validator.AbstractValidator#onValidate(org.apache.wicket.validation.IValidatable)
     */
    @Override
    protected void onValidate(IValidatable<T> validatable) {
        T value = validatable.getValue();
        if (value == null || (value instanceof String && ((String)value).trim().length() == 0)) {
            validatable.error(new ValidationError().addMessageKey("Required"));
        }
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.validation.validator.AbstractValidator#validateOnNullValue()
     */
    @Override
    public boolean validateOnNullValue() {
        return true;
    }

}
