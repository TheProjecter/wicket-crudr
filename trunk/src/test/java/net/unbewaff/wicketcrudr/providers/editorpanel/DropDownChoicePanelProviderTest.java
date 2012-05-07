package net.unbewaff.wicketcrudr.providers.editorpanel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.unbewaff.TempPanel;
import net.unbewaff.wicketcrudr.components.ICrudrDataProvider;
import net.unbewaff.wicketcrudr.components.IEditorFacade;
import net.unbewaff.wicketcrudr.providers.editor.DummyEditorProvider;
import net.unbewaff.wicketcrudr.providers.editor.DummyEditorProvider.Editor;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DropDownChoicePanelProviderTest {

    private static final int COUNT = 5;
    private static WicketTester tester;
	private List<StringHolder> list;
    private WebMarkupContainer component;
    private TempPanel panel;
    private ISurroundingContainerProvider provider;

	@BeforeClass
	public static void setUp() {
		tester = new WicketTester();
	}

	@Before
	public void init() {
	    list = new ArrayList<StringHolder>();
	    for (int i = 0; i < COUNT; i++) {
	        StringHolder sh = new StringHolder();
	        sh.setData("StringHolder instance No. " + i);
	        list.add(sh);
	    }
        panel = new TempPanel("panel");
        provider = new DropDownChoicePanelProvider();
        component = provider.newSurroundingContainer(panel.getComponentId());
        ICrudrDataProvider<StringHolder> dataProvider = getDataProvider();
        component.add(new DummyEditorProvider<StringHolder>(Editor.DROPDOWNCHOICE, dataProvider).newEditor(panel, "editor", Model.of(list.get(0))));
        panel.add(component);
	}

	@Test
	public void testDropDownChoicePanelprovider() {
		tester.startComponentInPage(panel);
		tester.assertInvisible("panel:" + panel.getComponentId());
	}

	@Test
	public void testDropDownChoicePanelproviderShow() {
        provider.show(null, component);
        tester.startComponentInPage(panel);
		tester.assertVisible("panel:" + panel.getComponentId());
	}

	@Test
	public void testDropDownChoiceproviderShowThenHide() {
		provider.show(null, component);
		provider.hide(null, component);
		tester.startComponentInPage(panel);
		tester.assertInvisible("panel:" + panel.getComponentId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testShowWithNullComponent() {
		ISurroundingContainerProvider provider = new DropDownChoicePanelProvider();
		provider.show(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHideWithNullComponent() {
        ISurroundingContainerProvider provider = new DropDownChoicePanelProvider();
		provider.hide(null, null);
	}

	/**
	 * @return
	 */
	private ICrudrDataProvider<StringHolder> getDataProvider() {
	    return new StringHolderProvider();
	}

	/**
     * @author David Hendrix (Nicktarix)
     *
     */
    private final class StringHolderProvider implements ICrudrDataProvider<StringHolder> {

        private static final long serialVersionUID = -613696692985748377L;

        @Override
        public List<StringHolder> getList() {
            return list;
        }

        @Override
        public StringHolder newInstance() {
            return new StringHolder();
        }

        @Override
        public Class<StringHolder> getType() {
            return StringHolder.class;
        }
    }

    private static class StringHolder implements Serializable{

        private String data;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

	}
}
