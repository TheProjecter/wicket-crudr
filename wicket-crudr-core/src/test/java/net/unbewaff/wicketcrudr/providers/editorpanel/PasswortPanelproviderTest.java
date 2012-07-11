/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editorpanel;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class PasswortPanelproviderTest {
    private static WicketTester tester;

    @BeforeClass
    public static void setUp() {
        tester = new WicketTester();
    }

    @Test
    public void testPasswordPanelProvider() {
        PasswordPanelProvider provider = new PasswordPanelProvider();
        WebMarkupContainer component = provider.newSurroundingContainer("componentId", null);
        component.add(new PasswordTextField("editor", Model.of("editor")));
        tester.startComponentInPage(component);
        tester.assertInvisible("componentId");
    }

    @Test
    public void testPasswordPanelProviderVisible() {
        PasswordPanelProvider provider = new PasswordPanelProvider();
        WebMarkupContainer component = provider.newSurroundingContainer("componentId", null);
        component.setVisible(true);
        component.add(new PasswordTextField("editor", Model.of("editor")));
        tester.startComponentInPage(component);
        tester.assertVisible("componentId");
        tester.assertVisible("componentId:editor");
        tester.assertVisible("componentId:repeat");
    }
}
