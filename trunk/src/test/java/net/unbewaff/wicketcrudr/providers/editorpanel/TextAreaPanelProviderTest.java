package net.unbewaff.wicketcrudr.providers.editorpanel;

import net.unbewaff.wicketcrudr.providers.editorpanel.TextAreaPanelProvider;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.BeforeClass;
import org.junit.Test;

public class TextAreaPanelProviderTest {

	private static WicketTester tester;

	@Test
	public void testTextAreaPanelprovider() {
		TextAreaPanelProvider provider = new TextAreaPanelProvider();
		WebMarkupContainer component = provider.newSurroundingContainer("componentId");
		component.add(new TextArea<String>("editor", Model.of("editor")));
		tester.startComponentInPage(component);
		tester.assertInvisible("componentId");
	}

	@BeforeClass
	public static void setUp() {
		tester = new WicketTester();
	}

	@Test
	public void testTextAreaPanelproviderShow() {
		TextAreaPanelProvider provider = new TextAreaPanelProvider();
		WebMarkupContainer component = provider.newSurroundingContainer("componentId");
		component.add(new TextArea<String>("editor", Model.of("editor")));
		provider.show(null, component);
		tester.startComponentInPage(component);

		tester.assertVisible("componentId");
	}

	@Test
	public void testTextAreaPanelproviderShowThenHide() {
		TextAreaPanelProvider provider = new TextAreaPanelProvider();
		WebMarkupContainer component = provider.newSurroundingContainer("componentId");
		component.add(new TextArea<String>("editor", Model.of("editor")));
		provider.show(null, component);
		provider.hide(null, component);
		tester.startComponentInPage(component);

		tester.assertInvisible("componentId");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testShowWithNullComponent() {
		TextAreaPanelProvider provider = new TextAreaPanelProvider();
		provider.show(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHideWithNullComponent() {
		TextAreaPanelProvider provider = new TextAreaPanelProvider();
		provider.hide(null, null);
	}
}
