package net.unbewaff.wicketcrudr.providers.label;

import net.unbewaff.wicketcrudr.components.ILabelFacade;
import net.unbewaff.wicketcrudr.providers.label.SimpleLabelProvider;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

public class SimpleLabelProviderTest {

    @Test
    public void testSimpleLabelProvider() {
        WicketTester tester = new WicketTester();
        SimpleLabelProvider<String> simpleLabelProvider = new SimpleLabelProvider<String>();
        tester.startComponentInPage(simpleLabelProvider.newLabel(new DummyFacade(), "componentId", Model.of("TestString")));
        tester.assertComponent("componentId", Label.class);
        tester.assertContains("TestString");
    }

    @Test
    public void testSimpleLabelProviderWithEmptyString() {
        WicketTester tester = new WicketTester();
        SimpleLabelProvider<String> simpleLabelProvider = new SimpleLabelProvider<String>();
        ILabelFacade iLabelFacade = new DummyFacade();
        tester.startComponentInPage(simpleLabelProvider.newLabel(iLabelFacade, "componentId", Model.of("")));
        tester.assertComponent("componentId", Label.class);
        tester.assertContains("specialString");
    }

    @Test
    public void testSimpleLabelProviderWithNullModel() {
        WicketTester tester = new WicketTester();
        SimpleLabelProvider<String> simpleLabelProvider = new SimpleLabelProvider<String>();
        tester.startComponentInPage(simpleLabelProvider.newLabel(new DummyFacade(), "componentId", null));
        tester.assertComponent("componentId", Label.class);
        tester.assertContains("specialString");
    }

    private static final class DummyFacade implements ILabelFacade {
        @Override
        public <C> IConverter<C> getConverter(Class<C> type) {
            return null;
        }

        @Override
        public String defaultNullLabel() {
            return "specialString";
        }
    }
}
