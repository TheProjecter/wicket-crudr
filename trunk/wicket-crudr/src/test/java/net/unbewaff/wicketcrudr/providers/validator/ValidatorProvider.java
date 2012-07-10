/**
 *
 */
package net.unbewaff.wicketcrudr.providers.validator;

import java.io.Serializable;

import org.apache.wicket.validation.IValidator;

public class ValidatorProvider {

    /**
     * @author David Hendrix (Nicktarix)
     *
     */
    public enum ValidatorType implements Serializable {
        REQUIRED
    }

    public static IValidator<?> getValidator(ValidatorType type, Object... validatorParams) {
        IValidator<?> validator = null;
        if (ValidatorType.REQUIRED.equals(type)) {
            if (validatorParams == null || validatorParams.length == 0) {
                validator = new RequiredValidator();
            } else {
                throw new IllegalArgumentException("The RequiredValidator can't take any params.");
            }
        }
        return validator;
    }
}