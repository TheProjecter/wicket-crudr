/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;

import static org.junit.Assert.assertNotNull;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import net.unbewaff.TempPanel;
import net.unbewaff.wicketcrudr.annotations.EditorType;
import net.unbewaff.wicketcrudr.datablocks.IProperty;
import net.unbewaff.wicketcrudr.providers.editorpanel.ISurroundingContainerProvider;
import net.unbewaff.wicketcrudr.providers.editorpanel.SurroundingContainerProviderFactory;

import org.apache.log4j.Logger;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.Model;
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
        final IProperty property = mockery.mock(IProperty.class);
        mockery.checking(new Expectations() {{
            allowing(property).getEditorType(); will(returnValue(EditorType.DATE));
            allowing(property).getProperty(); will(returnValue("string"));
        }});
        ISurroundingContainerProvider provider = SurroundingContainerProviderFactory.getContainerProvider(property);
        String componentId = panel.getComponentId();
        logger.debug("Component Id of panel: " + componentId);
        WebMarkupContainer component = provider.newSurroundingContainer(componentId, null);
        component.setVisible(true);
        panel.add(component);
        IEditorProvider<DateHolder> editorProvider = EditorProviderFactory.getEditorProvider(null, property);
        FormComponent<DateHolder> dtf = editorProvider.newEditor(panel, "editor", Model.of(dh), null);
        component.add(dtf);
        tester.startComponentInPage(panel);
        tester.assertComponent("panel", TempPanel.class);
        tester.assertComponent("panel:table:editor", DateTextField.class);
        DateTextField result = (DateTextField) tester.getComponentFromLastRenderedPage("panel:table:editor");
        assertNotNull(result);
        mockery.assertIsSatisfied();
    }


    @SuppressWarnings("unused")
    private class DateHolder implements Serializable {

        private static final long serialVersionUID = 2478215800345033596L;
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
