/**
 *
 */
package net.unbewaff.wicketcrudr.providers.validator;

import static org.junit.Assert.assertTrue;

import net.unbewaff.wicketcrudr.providers.validator.ValidatorProvider.ValidatorType;

import org.junit.Test;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class ValidatorProviderTest {

    @Test
    public void testRequiredValidatorValidParams() {
        assertTrue(ValidatorProvider.getValidator(ValidatorType.REQUIRED, null) instanceof RequiredValidator);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testRequiredValidatorInvalidParams() {
        assertTrue(ValidatorProvider.getValidator(ValidatorType.REQUIRED, "1", 2, 3L) instanceof RequiredValidator);
    }
}
