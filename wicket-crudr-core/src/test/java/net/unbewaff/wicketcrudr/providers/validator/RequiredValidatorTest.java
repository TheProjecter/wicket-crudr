/**
 *
 */
package net.unbewaff.wicketcrudr.providers.validator;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class RequiredValidatorTest {

    /**
     * @author David Hendrix (Nicktarix)
     *
     */
    private final class MyInputPanel extends InputPanel {
        /**
         * @param id
         */
        private MyInputPanel(String id) {
            super(id);
        }

        @Override
        public FormComponent getFormComponent(String componentId) {
            TextField<String> text = new TextField<String>(componentId, Model.of(stringValue));
            text.add(new RequiredValidator());
            return text;
        }
    }

    private static WicketTester tester;
    private String stringValue;
    private static final transient Logger logger = Logger.getLogger(RequiredValidatorTest.class);

    @BeforeClass
    public static void setUp() {
        tester = new WicketTester();
    }

    @Test
    public void testValidRequiredString() {
        InputPanel ip = new MyInputPanel("id");
        tester.startComponentInPage(ip);
        FormTester ft = tester.newFormTester("id:form");
        ft.setValue(InputPanel.COMPONENT_ID, "someText");
        ft.submitLink("submit", false);
        assertNotNull(ip.wasSubmitted());
        assertTrue(ip.wasSubmitted());
        tester.assertNoErrorMessage();
    }


    @Test
    public void testInvalidNullRequiredString() {
        InputPanel ip = new MyInputPanel("id");
        tester.startComponentInPage(ip);
        FormTester ft = tester.newFormTester("id:form");
        ft.submitLink("submit", false);
        assertNotNull(ip.wasSubmitted());
        assertFalse(ip.wasSubmitted());
        tester.assertComponent("id:errors", FeedbackPanel.class);
        tester.assertErrorMessages("Dummdidumm field tummtumm");
    }

    @Test
    public void testInvalidEmptyStringRequiredString() {
        InputPanel ip = new MyInputPanel("id");
        tester.startComponentInPage(ip);
        FormTester ft = tester.newFormTester("id:form");
        ft.setValue(InputPanel.COMPONENT_ID, "");
        ft.submitLink("submit", false);
        assertNotNull(ip.wasSubmitted());
        assertFalse(ip.wasSubmitted());
        tester.assertComponent("id:errors", FeedbackPanel.class);
        tester.assertErrorMessages("Dummdidumm field tummtumm");
    }

    @Test
    public void testInvalidWitespaceStringRequiredString() {
        InputPanel ip = new MyInputPanel("id");
        tester.startComponentInPage(ip);
        FormTester ft = tester.newFormTester("id:form");
        ft.setValue(InputPanel.COMPONENT_ID, "  \t  ");
        ft.submitLink("submit", false);
        assertNotNull(ip.wasSubmitted());
        assertFalse(ip.wasSubmitted());
        tester.assertComponent("id:errors", FeedbackPanel.class);
        tester.assertErrorMessages("Dummdidumm field tummtumm");
    }
}
