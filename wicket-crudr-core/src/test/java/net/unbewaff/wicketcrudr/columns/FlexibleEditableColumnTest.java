/**
 *
 */
package net.unbewaff.wicketcrudr.columns;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.unbewaff.TempPanel;
import net.unbewaff.wicketcrudr.annotations.EditorType;
import net.unbewaff.wicketcrudr.annotations.type.Prototype;
import net.unbewaff.wicketcrudr.components.ContainerConfiguration;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;
import net.unbewaff.wicketcrudr.datablocks.IProperty;
import net.unbewaff.wicketcrudr.datablocks.IPrototypeData;
import net.unbewaff.wicketcrudr.providers.editor.EditorProviderFactory;
import net.unbewaff.wicketcrudr.providers.editor.IEditorProvider;
import net.unbewaff.wicketcrudr.providers.editorpanel.ISurroundingContainerProvider;
import net.unbewaff.wicketcrudr.providers.editorpanel.SurroundingContainerProviderFactory;
import net.unbewaff.wicketcrudr.providers.label.ILabelProvider;
import net.unbewaff.wicketcrudr.providers.label.LabelProviderFactory;
import net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider;
import net.unbewaff.wicketcrudr.providers.labelmodel.LabelModelProviderFactory;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for FlexibleEditableColumn
 *
 * @author David Hendrix (Nicktarix)
 *
 */
public class FlexibleEditableColumnTest {

    private WicketTester tester;
    private Mockery mockery;
    private List<StringHolder> data = new ArrayList<StringHolder>() {

        private static final long serialVersionUID = 8397804885542314329L;

        {
            add(new StringHolder("Apple"));
            add(new StringHolder("Banana"));
            add(new StringHolder("Cherry"));
        }};


        @Before
        public void setUp() {
            tester = new WicketTester();
            mockery = new Mockery();
        }

        @Test
        public void testDisplay() {

            final IPrototypeData prototype = mockery.mock(IPrototypeData.class);
            final IProperty dataProp = mockery.mock(IProperty.class, "dataProp");
            final IProperty odd = mockery.mock(IProperty.class, "odd");
            final List<IProperty> list = new ArrayList<IProperty>();
            list.add(dataProp);
            list.add(odd);
            mockery.checking(new Expectations() {{
                allowing(prototype).getProperties(); will(returnValue(list));
                allowing(dataProp).getProperty(); will(returnValue("Data"));
                allowing(odd).getProperty(); will(returnValue("Odd"));
                allowing(dataProp).getEditorType(); will(returnValue(EditorType.TEXTFIELD));
                allowing(prototype).getResourceKeyProperty(); will(returnValue(null));
            }});
            List<IColumn<StringHolder>> cols = new ArrayList<IColumn<StringHolder>>();
            ILabelModelProvider<StringHolder> labelModelProvider = LabelModelProviderFactory.getLabelModelProvider("data", prototype);
            ILabelProvider<StringHolder> labelProvider = LabelProviderFactory.getLabelProvider(null, labelModelProvider);
            IEditorProvider<StringHolder> editorProvider = EditorProviderFactory.getEditorProvider(null, dataProp);
            ISurroundingContainerProvider containerProvider = SurroundingContainerProviderFactory.getContainerProvider(dataProp);
            ContainerConfiguration<StringHolder> conf = new ContainerConfiguration<StringHolder>(labelProvider, editorProvider, containerProvider, getListProvider(), "data");
            cols.add(new FlexibleEditableColumn<StringHolder>(Model.of("data"), conf));

            DataTable<StringHolder> table = new DataTable<StringHolder>("table", cols, new ListDataProvider<StringHolder>(data), 5);
            TempPanel panel = new TempPanel("test");
            panel.add(table);
            tester.startComponentInPage(panel);
            tester.assertVisible("test:table:body:rows:1:cells:1:cell:label");
            tester.assertVisible("test:table:body:rows:2:cells:1:cell:label");
            tester.assertVisible("test:table:body:rows:3:cells:1:cell:label");
            tester.assertInvisible("test:table:body:rows:1:cells:1:cell:editor");
            tester.assertInvisible("test:table:body:rows:2:cells:1:cell:editor");
            tester.assertInvisible("test:table:body:rows:3:cells:1:cell:editor");
            tester.assertLabel("test:table:body:rows:1:cells:1:cell:label", "Apple");
            tester.assertLabel("test:table:body:rows:2:cells:1:cell:label", "Banana");
            tester.assertLabel("test:table:body:rows:3:cells:1:cell:label", "Cherry");
            mockery.assertIsSatisfied();
        }

        @Test
        public void testDisplayEditor() {
            final IPrototypeData prototype = mockery.mock(IPrototypeData.class);
            final IProperty dataProp = mockery.mock(IProperty.class, "dataProp");
            final IProperty odd = mockery.mock(IProperty.class, "odd");
            final List<IProperty> list = new ArrayList<IProperty>();
            list.add(dataProp);
            list.add(odd);
            mockery.checking(new Expectations() {{
                allowing(prototype).getProperties(); will(returnValue(list));
                allowing(dataProp).getProperty(); will(returnValue("Data"));
                allowing(odd).getProperty(); will(returnValue("Odd"));
                allowing(dataProp).getEditorType(); will(returnValue(EditorType.TEXTFIELD));
                allowing(prototype).getResourceKeyProperty(); will(returnValue(null));
            }});
            List<IColumn<StringHolder>> cols = new ArrayList<IColumn<StringHolder>>();
            ILabelModelProvider<StringHolder> labelModelProvider = LabelModelProviderFactory.getLabelModelProvider("data", prototype);
            ILabelProvider<StringHolder> labelProvider = LabelProviderFactory.getLabelProvider(null, labelModelProvider);
            IEditorProvider<StringHolder> editorProvider = EditorProviderFactory.getEditorProvider(null, dataProp);
            ISurroundingContainerProvider containerProvider = SurroundingContainerProviderFactory.getContainerProvider(dataProp);
            ContainerConfiguration<StringHolder> conf = new ContainerConfiguration<StringHolder>(labelProvider, editorProvider, containerProvider, getListProvider(), "data");
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
            mockery.assertIsSatisfied();
        }

        @Test
        public void testCheckboxDisplay() {

            final IPrototypeData prototype = mockery.mock(IPrototypeData.class);
            final IProperty dataProp = mockery.mock(IProperty.class, "dataProp");
            final IProperty odd = mockery.mock(IProperty.class, "odd");
            final List<IProperty> list = new ArrayList<IProperty>();
            list.add(dataProp);
            list.add(odd);
            mockery.checking(new Expectations() {{
                allowing(prototype).getProperties(); will(returnValue(list));
                allowing(dataProp).getProperty(); will(returnValue("Data"));
                allowing(odd).getProperty(); will(returnValue("OddLength"));
                allowing(dataProp).getEditorType(); will(returnValue(EditorType.TEXTFIELD));
                allowing(odd).getEditorType(); will(returnValue(EditorType.CHECKBOX));
                allowing(prototype).getResourceKeyProperty(); will(returnValue(null));
            }});
            List<IColumn<StringHolder>> cols = new ArrayList<IColumn<StringHolder>>();
            ILabelModelProvider<StringHolder> labelModelProvider = LabelModelProviderFactory.getLabelModelProvider("oddLength", prototype);
            ILabelProvider<StringHolder> labelProvider = LabelProviderFactory.getLabelProvider(odd, labelModelProvider);
            IEditorProvider<StringHolder> editorProvider = EditorProviderFactory.getEditorProvider(null, odd);
            ISurroundingContainerProvider containerProvider = SurroundingContainerProviderFactory.getContainerProvider(odd);
            ContainerConfiguration<StringHolder> conf = new ContainerConfiguration<StringHolder>(labelProvider, editorProvider, containerProvider, getListProvider(), "oddLength");
            cols.add(new FlexibleEditableColumn<StringHolder>(Model.of("oddLength"), conf));

            DataTable<StringHolder> table = new DataTable<StringHolder>("table", cols, new ListDataProvider<StringHolder>(data), 5);
            TempPanel panel = new TempPanel("test");
            panel.add(table);
            tester.startComponentInPage(panel);
            //tester.debugComponentTrees();
            tester.assertVisible("test:table:body:rows:1:cells:1:cell:label");
            tester.assertVisible("test:table:body:rows:2:cells:1:cell:label");
            tester.assertVisible("test:table:body:rows:3:cells:1:cell:label");
            tester.assertInvisible("test:table:body:rows:1:cells:1:cell:editor");
            tester.assertInvisible("test:table:body:rows:2:cells:1:cell:editor");
            tester.assertInvisible("test:table:body:rows:3:cells:1:cell:editor");
            assertTrue(CheckBox.class.isAssignableFrom(tester.getComponentFromLastRenderedPage("test:table:body:rows:1:cells:1:cell:editor:editor", false).getClass()));
            assertTrue(CheckBox.class.isAssignableFrom(tester.getComponentFromLastRenderedPage("test:table:body:rows:2:cells:1:cell:editor:editor", false).getClass()));
            assertTrue(CheckBox.class.isAssignableFrom(tester.getComponentFromLastRenderedPage("test:table:body:rows:3:cells:1:cell:editor:editor", false).getClass()));
            CheckBox cb1 = (CheckBox)tester.getComponentFromLastRenderedPage("test:table:body:rows:1:cells:1:cell:editor:editor", false);
            assertTrue(cb1.getModelObject());
            CheckBox cb2 = (CheckBox)tester.getComponentFromLastRenderedPage("test:table:body:rows:2:cells:1:cell:editor:editor", false);
            assertFalse(cb2.getModelObject());
            CheckBox cb3 = (CheckBox)tester.getComponentFromLastRenderedPage("test:table:body:rows:3:cells:1:cell:editor:editor", false);
            assertFalse(cb3.getModelObject());
            mockery.assertIsSatisfied();
        }

        /**
         * @return
         */
        private ICrudrListProvider<StringHolder> getListProvider() {
            return new ICrudrListProvider<StringHolder>() {

                @Override
                public List<StringHolder> getList() {
                    return Collections.emptyList();
                }
            };
        }

        @Prototype
        public static class StringHolder implements Serializable {

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

            public Boolean getOddLength() {
                return this.data.length() % 2 == 1;
            }

            public void setOddLength(boolean val) {
                if (val != getOddLength()) {
                    this.data += " ";
                }
            }

        }
}
