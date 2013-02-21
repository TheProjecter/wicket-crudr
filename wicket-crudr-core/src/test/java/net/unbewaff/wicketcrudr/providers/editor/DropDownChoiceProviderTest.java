/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.unbewaff.TempPanel;
import net.unbewaff.wicketcrudr.annotations.EditorType;
import net.unbewaff.wicketcrudr.components.ICrudrDataProvider;
import net.unbewaff.wicketcrudr.datablocks.IProperty;
import net.unbewaff.wicketcrudr.providers.editorpanel.DropDownChoicePanel;
import net.unbewaff.wicketcrudr.providers.editorpanel.DropDownChoicePanelProvider;
import net.unbewaff.wicketcrudr.providers.editorpanel.ISurroundingContainerProvider;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.tester.WicketTester;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class DropDownChoiceProviderTest {

    private static Integer counter = 0;
    private static final int COUNT = 5;
    private static WicketTester tester;
    private List<StringHolder> list;
    private WebMarkupContainer component;
    private TempPanel panel;
    private ISurroundingContainerProvider provider;
    private Mockery mockery = new Mockery();
    private static final transient Logger logger = Logger.getLogger(DropDownChoiceProviderTest.class);

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
        String componentId = panel.getComponentId();
        logger.debug("Component Id of panel: " + componentId);
        component = provider.newSurroundingContainer(componentId, null);
        component.setVisible(true);
        panel.add(component);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void primitiveToStringRendering() {
        final IProperty property = mockery.mock(IProperty.class);
        mockery.checking(new Expectations() {{
            allowing(property).getProperty(); will(returnValue("string"));
            allowing(property).getEditorType(); will(returnValue(EditorType.DROPDOWNCHOICE));
        }});
        IEditorProvider<StringHolder> editorProvider = EditorProviderFactory.getEditorProvider(null, property);
        DropDownChoice<StringHolder> ddc = (DropDownChoice<StringHolder>) editorProvider.newEditor(panel, "editor", Model.of(list.get(1)), null);
        component.add(ddc);
        tester.startComponentInPage(panel);
        tester.assertComponent("panel", TempPanel.class);
        tester.assertComponent("panel:table", DropDownChoicePanel.class);
        tester.assertComponent("panel:table:editor", DropDownChoice.class);
        DropDownChoice<StringHolder> result = (DropDownChoice<StringHolder>) tester.getComponentFromLastRenderedPage("panel:table:editor");
        String markupId = ddc.getMarkupId();
        logger.debug("Markup Id of DropDownChoice: " + markupId);
        TagTester tt = tester.getTagByWicketId("editor");
        assertNotNull(tt);
        String markup = tt.getMarkup();
        logger.debug("Markup of DropDownChoice: " + markup);
        assertTrue(tt.hasChildTag("option"));
        for (StringHolder sh : list) {

            int indexOf = list.indexOf(sh);
            String selected = "";
            if (sh.equals(result.getModelObject())) {
                selected = "selected=\"selected\" ";
            }
            String s = "<option " + selected + "value=\"" + indexOf + "\">" + sh.toString() + "</option>";
            assertTrue("Didn't find " + s + " in markup.", markup.contains(s));
        }
        mockery.assertIsSatisfied();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testStringResourceRendering() {
        final IProperty property = mockery.mock(IProperty.class);
        mockery.checking(new Expectations() {{
            allowing(property).getProperty(); will(returnValue("Data"));
            allowing(property).getEditorType(); will(returnValue(EditorType.DROPDOWNCHOICE));
        }});
        IEditorProvider<StringHolder> editorProvider = EditorProviderFactory.getEditorProvider("ddctest", property);
        DropDownChoice<StringHolder> ddc = (DropDownChoice<StringHolder>) editorProvider.newEditor(panel, "editor", Model.of(list.get(1)), null);
        component.add(ddc);
        tester.startComponentInPage(panel);
        tester.assertComponent("panel", TempPanel.class);
        tester.assertComponent("panel:table", DropDownChoicePanel.class);
        tester.assertComponent("panel:table:editor", DropDownChoice.class);
        DropDownChoice<StringHolder> result = (DropDownChoice<StringHolder>) tester.getComponentFromLastRenderedPage("panel:table:editor");
        String markupId = ddc.getMarkupId();
        logger.debug("Markup Id of DropDownChoice: " + markupId);
        TagTester tt = tester.getTagByWicketId("editor");
        assertNotNull(tt);
        String markup = tt.getMarkup();
        logger.debug("Markup of DropDownChoice: " + markup);
        assertTrue(tt.hasChildTag("option"));
        for (StringHolder sh : list) {

            int indexOf = list.indexOf(sh);
            String selected = "";
            if (sh.equals(result.getModelObject())) {
                selected = "selected=\"selected\" ";
            }
            String string = new StringResourceModel("ddctest." + sh.getData(), panel, Model.of("")).getString();
            String s = "<option " + selected + "value=\"" + indexOf + "\">" + string + "</option>";
            assertTrue("Didn't find " + s + " in markup.", markup.contains(s));
        }
        mockery.assertIsSatisfied();
    }

    private class StringHolder implements Serializable, ICrudrDataProvider<StringHolder>{

        private static final long serialVersionUID = -7573591466251022526L;
        private String data;
        private Integer id = counter++;

        @Override
        public Integer getId() {
            return id;
        }

        @SuppressWarnings("unused")
        public void setId(Integer id) {
            this.id = id;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        /* (non-Javadoc)
         * @see net.unbewaff.wicketcrudr.components.ICrudrDataProvider#getList()
         */
        @Override
        public List<StringHolder> getList() {
            return list;
        }

        /* (non-Javadoc)
         * @see net.unbewaff.wicketcrudr.components.ICrudrDataProvider#newInstance()
         */
        @Override
        public StringHolder newInstance() {
            return new StringHolder();
        }

        /* (non-Javadoc)
         * @see net.unbewaff.wicketcrudr.components.ICrudrDataProvider#getType()
         */
        @Override
        public Class<StringHolder> getType() {
            return StringHolder.class;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("StringHolder [data=").append(data).append("]");
            return builder.toString();
        }

    }
}
