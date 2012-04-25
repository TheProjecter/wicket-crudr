package net.unbewaff.wicketcrudr.providers.editorpanel;

import net.unbewaff.wicketcrudr.providers.editor.ISurroundingContainerProvider;
import net.unbewaff.wicketcrudr.providers.editorpanel.CheckBoxPanelProvider;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.BeforeClass;
import org.junit.Test;

public class CheckBoxPanelProviderTest {

	private static WicketTester tester;

	@BeforeClass
	public static void setUp() {
		tester = new WicketTester();
	}

	@Test
	public void testCheckBoxPanelprovider() {
		ISurroundingContainerProvider provider = new CheckBoxPanelProvider();
		WebMarkupContainer component = provider.newSurroundingContainer("componentId");
		component.add(new CheckBox("editor", Model.of(true)));
		tester.startComponentInPage(component);
		tester.assertInvisible("componentId");
	}

	@Test
	public void testCheckBoxPanelproviderShow() {
		ISurroundingContainerProvider provider = new CheckBoxPanelProvider();
		WebMarkupContainer component = provider.newSurroundingContainer("componentId");
		component.add(new CheckBox("editor", Model.of(true)));
		provider.show(null, component);
		tester.startComponentInPage(component);

		tester.assertVisible("componentId");
	}

	@Test
	public void testTextAreaPanelproviderShowThenHide() {
		ISurroundingContainerProvider provider = new CheckBoxPanelProvider();
		WebMarkupContainer component = provider.newSurroundingContainer("componentId");
		component.add(new CheckBox("editor", Model.of(true)));
		provider.show(null, component);
		provider.hide(null, component);
		tester.startComponentInPage(component);
		tester.assertInvisible("componentId");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testShowWithNullComponent() {
		ISurroundingContainerProvider provider = new CheckBoxPanelProvider();
		provider.show(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHideWithNullComponent() {
		ISurroundingContainerProvider provider = new CheckBoxPanelProvider();
		provider.hide(null, null);
	}
}
