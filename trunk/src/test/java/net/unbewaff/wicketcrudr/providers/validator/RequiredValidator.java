/**
 *
 */
package net.unbewaff.wicketcrudr.providers.validator;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class RequiredValidator<T> extends AbstractValidator<T> {

    /**
     * Method required by {@link AbstractValidator} but not used here. Validation happens by setting
     * the {@link FormComponent.setRequired(boolean)} method to true in {@see onConfigure()}
     * @see org.apache.wicket.validation.validator.AbstractValidator#onValidate(org.apache.wicket.validation.IValidatable)
     */
    @Override
    protected void onValidate(IValidatable<T> validatable) {
        // intentionally left empty
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.validation.validator.AbstractValidator#validateOnNullValue()
     */
    @Override
    public boolean validateOnNullValue() {
        return true;
    }

    /**
     * This is where the magic of this validator happens. If the component this validator was added to is a FormComponent (and
     * this validator will only be added to FormComponents, this is made sure by checking the type of the Component), it set's
     * the components required value to true.
     */
    @Override
    public void onConfigure(Component component) {
        if (component instanceof FormComponent) {
            ((FormComponent<?>)component).setRequired(true);
        } else {
            throw new IllegalArgumentException("This validator can only be used on FormComponents.");
        }
        super.onConfigure(component);
    }
}
