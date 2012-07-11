/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import net.unbewaff.TempPanel;
import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.Editor.EditorType;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Lister.Display;
import net.unbewaff.wicketcrudr.components.ICrudrDataProvider;
import net.unbewaff.wicketcrudr.providers.editorpanel.DropDownChoicePanel;
import net.unbewaff.wicketcrudr.providers.editorpanel.ISurroundingContainerProvider;
import net.unbewaff.wicketcrudr.providers.editorpanel.SurroundingContainerProviderFactory;

import org.apache.log4j.Logger;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponent;
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
public class DateProviderTest {

    private static WicketTester tester;
    private TempPanel panel;
    private Mockery mockery = new Mockery();
    private static final transient Logger logger = Logger.getLogger(DateProviderTest.class);
    private DateHolder dh = new DateHolder();

    @BeforeClass
    public static void setUp() {
        tester = new WicketTester();

    }

    @Before
    public void init() throws ParseException {
        panel = new TempPanel("panel");
        dh.setData(DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMAN).parse("13.11.1974"));
    }

    @Test
    public void testBasicFunctionality() {
        final Lister l = mockery.mock(Lister.class);
        final Editor e = mockery.mock(Editor.class);
        mockery.checking(new Expectations() {{
            exactly(2).of(e).editAs(); will(returnValue(EditorType.DATE));
        }});
        ISurroundingContainerProvider provider = SurroundingContainerProviderFactory.getContainerProvider(e);
        String componentId = panel.getComponentId();
        logger.debug("Component Id of panel: " + componentId);
        WebMarkupContainer component = provider.newSurroundingContainer(componentId, null);
        component.setVisible(true);
        panel.add(component);
        IEditorProvider<DateHolder> editorProvider = EditorProviderFactory.getEditorProvider(e, l, DateHolder.class, "data");
        FormComponent<DateHolder> dtf = editorProvider.newEditor(panel, "editor", Model.of(dh), null);
        component.add(dtf);
        tester.startComponentInPage(panel);
        tester.debugComponentTrees();
        tester.assertComponent("panel", TempPanel.class);
        tester.assertComponent("panel:table:editor", DateTextField.class);
        DateTextField result = (DateTextField) tester.getComponentFromLastRenderedPage("panel:table:editor");
        assertNotNull(result);
        mockery.assertIsSatisfied();
    }


    private class DateHolder implements Serializable {

        private Date data;
        private Integer id = 1;

        public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Date getData() {
            return data;
        }

        public void setData(Date data) {
            this.data = data;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("DateHolder [data=").append(data).append("]");
            return builder.toString();
        }

    }
}
