/**
 * 
 */
package net.unbewaff.wicketcrudr.providers.label;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import net.unbewaff.wicketcrudr.components.ILabelFacade;
import net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.tester.Result;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

/**
 * @author nicktar
 * 
 */
public class DisabeledCheckboxLabelProviderTest {

	@Test
	public void testCheckBoxWithTrueModel() {
		WicketTester tester = new WicketTester();
		DisabeledCheckboxLabelProvider provider = new DisabeledCheckboxLabelProvider(new PassthroughModelProvider());
		tester.startComponentInPage(provider.newLabel(new DummyFacade(), "componentId", Model.of(true)));
		tester.debugComponentTrees();
		tester.assertComponent("componentId:editor", CheckBox.class);
		CheckBox cb = (CheckBox)tester.getComponentFromLastRenderedPage("componentId:editor");
		assertFalse(cb.isEnabled());
		assertTrue(cb.getModelObject());
	}

	@Test
	public void testCheckBoxWithFalseModel() {
		WicketTester tester = new WicketTester();
		DisabeledCheckboxLabelProvider provider = new DisabeledCheckboxLabelProvider(new PassthroughModelProvider());
		tester.startComponentInPage(provider.newLabel(new DummyFacade(), "componentId", Model.of(false)));
		tester.debugComponentTrees();
		tester.assertComponent("componentId:editor", CheckBox.class);
		CheckBox cb = (CheckBox)tester.getComponentFromLastRenderedPage("componentId:editor");
		assertFalse(cb.isEnabled());
		assertFalse(cb.getModelObject());
	}

	@Test
	public void testCheckBoxWithNullModel() {
		WicketTester tester = new WicketTester();
		DisabeledCheckboxLabelProvider provider = new DisabeledCheckboxLabelProvider(new PassthroughModelProvider());
		tester.startComponentInPage(provider.newLabel(new DummyFacade(), "componentId", Model.of((Boolean)null)));
		tester.debugComponentTrees();
		tester.assertComponent("componentId:editor", CheckBox.class);
		CheckBox cb = (CheckBox)tester.getComponentFromLastRenderedPage("componentId:editor");
		assertFalse(cb.isEnabled());
		assertNull(cb.getModelObject());
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

    private static final class PassthroughModelProvider implements ILabelModelProvider<Boolean> {

        private static final long serialVersionUID = 3676455054859620797L;

        @Override
        public IModel<?> newLabelModel(IModel<Boolean> model) {
            return model;
        }
    }}
