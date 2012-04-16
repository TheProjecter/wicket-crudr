package net.unbewaff.wicketcrudr.providers;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

public class SimpleLabelProviderTest {

        @Test
        public void testSimpleLabelProvider() {
                WicketTester tester = new WicketTester();
                SimpleLabelProvider<String> simpleLabelProvider = new SimpleLabelProvider<String>();
                tester.startComponentInPage(simpleLabelProvider.newLabel("componentId", Model.of("TestString")));
                tester.assertComponent("componentId", Label.class);
                tester.assertContains("TestString");
        }

}
