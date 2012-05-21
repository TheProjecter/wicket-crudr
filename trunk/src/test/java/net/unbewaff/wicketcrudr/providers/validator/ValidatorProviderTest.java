/**
 *
 */
package net.unbewaff.wicketcrudr.providers.validator;

import static org.junit.Assert.assertTrue;

import org.apache.wicket.validation.IValidator;
import org.junit.Test;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class ValidatorProviderTest {

    @Test
    public void testRequiredValidator() {
        assertTrue(ValidatorProvider.getValidator() instanceof RequiredValidator);
    }

    public static class ValidatorProvider {

        public static IValidator getValidator() {
            return new RequiredValidator();
        }
    }
}
