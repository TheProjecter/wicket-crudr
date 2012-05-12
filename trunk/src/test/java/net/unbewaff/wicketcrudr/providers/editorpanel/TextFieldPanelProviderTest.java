package net.unbewaff.wicketcrudr.providers.editorpanel;
import net.unbewaff.wicketcrudr.providers.editorpanel.TextFieldPanelProvider;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.BeforeClass;
import org.junit.Test;

public class TextFieldPanelProviderTest {

        private static WicketTester tester;

        @Test
        public void testTextFieldPanelprovider() {
                TextFieldPanelProvider provider = new TextFieldPanelProvider();
                WebMarkupContainer component = provider.newSurroundingContainer("componentId", null);
                component.add(new TextField<String>("editor", Model.of("editor")));
                tester.startComponentInPage(component);
                tester.assertInvisible("componentId");
        }

        @BeforeClass
        public static void setUp() {
                tester = new WicketTester();
        }

        @Test
        public void testTextFieldPanelproviderShow() {
                TextFieldPanelProvider provider = new TextFieldPanelProvider();
                WebMarkupContainer component = provider.newSurroundingContainer("componentId", null);
                component.add(new TextField<String>("editor", Model.of("editor")));
                provider.show(null, component);
                tester.startComponentInPage(component);

                tester.assertVisible("componentId");
        }

        @Test
        public void testTextFieldPanelproviderShowThenHide() {
                TextFieldPanelProvider provider = new TextFieldPanelProvider();
                WebMarkupContainer component = provider.newSurroundingContainer("componentId", null);
                component.add(new TextField<String>("editor", Model.of("editor")));
                provider.show(null, component);
                provider.hide(null, component);
                tester.startComponentInPage(component);

                tester.assertInvisible("componentId");
        }

        @Test(expected = IllegalArgumentException.class)
        public void testShowWithNullComponent() {
                TextFieldPanelProvider provider = new TextFieldPanelProvider();
                provider.show(null, null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testHideWithNullComponent() {
                TextFieldPanelProvider provider = new TextFieldPanelProvider();
                provider.hide(null, null);
        }
}

