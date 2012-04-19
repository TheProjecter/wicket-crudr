/**
 *
 */
package net.unbewaff.wicketcrudr.columns;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.unbewaff.wicketcrudr.components.ContainerConfiguration;
import net.unbewaff.wicketcrudr.providers.IEditorProvider;
import net.unbewaff.wicketcrudr.providers.ILabelProvider;
import net.unbewaff.wicketcrudr.providers.ISurroundingContainerProvider;
import net.unbewaff.wicketcrudr.providers.SimpleLabelProvider;
import net.unbewaff.wicketcrudr.providers.TextFieldPanelProvider;
import net.unbewaff.wicketcrudr.providers.TextFieldProvider;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for FlexibleEditableColumn
 *
 * @author David Hendrix (Nicktarix)
 *
 */
public class TestFlexibleEditableColumn {

    private WicketTester tester;
    private List<StringHolder> data = new ArrayList<StringHolder>() {/**
         *
         */
        private static final long serialVersionUID = 8397804885542314329L;

    {
        add(new StringHolder("Apple"));
        add(new StringHolder("Banana"));
        add(new StringHolder("Cherry"));
    }};

    @Before
    public void setUp() {
            tester = new WicketTester();
    }

    @Test
    public void testDisplay() {

        List<IColumn<StringHolder>> cols = new ArrayList<IColumn<StringHolder>>();
        ILabelProvider<StringHolder> labelProvider = new SimpleLabelProvider<StringHolder>();
        IEditorProvider<StringHolder> editorProvider = new TextFieldProvider<StringHolder>();
        ISurroundingContainerProvider containerProvider = new TextFieldPanelProvider();
        ContainerConfiguration<StringHolder> conf = new ContainerConfiguration<StringHolder>(labelProvider, editorProvider, containerProvider, "data");
        cols.add(new FlexibleEditableColumn<StringHolder>(Model.of("data"), conf));

        DataTable<StringHolder> table = new DataTable<StringHolder>("table", cols, new ListDataProvider<StringHolder>(data), 5);
        TempPanel panel = new TempPanel("test");
        panel.add(table);
        tester.startComponentInPage(panel);
        tester.debugComponentTrees("test:table");
        tester.assertVisible("test:table:body:rows:1:cells:1:cell:label");
        tester.assertVisible("test:table:body:rows:2:cells:1:cell:label");
        tester.assertVisible("test:table:body:rows:3:cells:1:cell:label");
        tester.assertInvisible("test:table:body:rows:1:cells:1:cell:editor");
        tester.assertInvisible("test:table:body:rows:2:cells:1:cell:editor");
        tester.assertInvisible("test:table:body:rows:3:cells:1:cell:editor");
        tester.assertLabel("test:table:body:rows:1:cells:1:cell:label", "Apple");
        tester.assertLabel("test:table:body:rows:2:cells:1:cell:label", "Banana");
        tester.assertLabel("test:table:body:rows:3:cells:1:cell:label", "Cherry");
    }

    @Test
    public void testDisplayEditor() {
        List<IColumn<StringHolder>> cols = new ArrayList<IColumn<StringHolder>>();
        ILabelProvider<StringHolder> labelProvider = new SimpleLabelProvider<StringHolder>();
        IEditorProvider<StringHolder> editorProvider = new TextFieldProvider<StringHolder>();
        ISurroundingContainerProvider containerProvider = new TextFieldPanelProvider();
        ContainerConfiguration<StringHolder> conf = new ContainerConfiguration<StringHolder>(labelProvider, editorProvider, containerProvider, "data");
        cols.add(new FlexibleEditableColumn<StringHolder>(Model.of("data"), conf));

        DataTable<StringHolder> table = new DataTable<StringHolder>("table", cols, new ListDataProvider<StringHolder>(data), 5);
        TempPanel panel = new TempPanel("test");
        panel.add(table);
        tester.startComponentInPage(panel);
        tester.executeAjaxEvent("test:table:body:rows:1:cells:1:cell:label", "onclick");
        tester.assertInvisible("test:table:body:rows:1:cells:1:cell:label");
        tester.assertVisible("test:table:body:rows:2:cells:1:cell:label");
        tester.assertVisible("test:table:body:rows:3:cells:1:cell:label");
        tester.assertVisible("test:table:body:rows:1:cells:1:cell:editor");
        tester.assertInvisible("test:table:body:rows:2:cells:1:cell:editor");
        tester.assertInvisible("test:table:body:rows:3:cells:1:cell:editor");
        tester.assertLabel("test:table:body:rows:2:cells:1:cell:label", "Banana");
        tester.assertLabel("test:table:body:rows:3:cells:1:cell:label", "Cherry");
    }


    private static class StringHolder implements Serializable {

        private static final long serialVersionUID = 8070868717307881900L;
        private String data;

        public StringHolder(String data) {
            this.data = data;
        }

        @SuppressWarnings("unused")
        public String getData() {
            return data;
        }

        @SuppressWarnings("unused")
        public void setData(String data) {
            this.data = data;
        }

    }
}
