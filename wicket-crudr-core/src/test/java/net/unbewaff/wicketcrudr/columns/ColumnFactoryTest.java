/**
 *
 */
package net.unbewaff.wicketcrudr.columns;

import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.unbewaff.TempPanel;
import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.member.DisplayType;
import net.unbewaff.wicketcrudr.annotations.member.StringResource;
import net.unbewaff.wicketcrudr.annotations.type.LabelResourcePrefix;
import net.unbewaff.wicketcrudr.annotations.type.Prototype;
import net.unbewaff.wicketcrudr.annotations.type.Prototypes;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;


/**
 * Testing the ColumnFactory class
 *
 * @author David Hendrix (Nicktarix)
 *
 */
public class ColumnFactoryTest {

    private WicketTester tester;
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
        }

        @Test
        public void testDefaultLister() throws SecurityException, NoSuchMethodException {
            List<IColumn<StringHolder>> cols = new ArrayList<IColumn<StringHolder>>();
            Method method = StringHolder.class.getMethod("getData");
            Editor e = method.getAnnotation(Editor.class);
            DisplayType dt = method.getAnnotation(DisplayType.class);
            StringResource sr = method.getAnnotation(StringResource.class);
            LabelResourcePrefix lrp = StringHolder.class.getAnnotation(LabelResourcePrefix.class);
            IColumn<StringHolder> column = ColumnFactory.getColumn(e, dt, null, sr, "Data", StringHolder.class, null, getListProvider(), lrp, "");
            assertTrue(column instanceof FlexibleNonEditableColumn);
            FlexibleNonEditableColumn<StringHolder> pc = (FlexibleNonEditableColumn<ColumnFactoryTest.StringHolder>)column;
            assertTrue(pc.getDisplayModel() instanceof StringResourceModel);
            cols.add(column);
            DataTable<StringHolder> table = new DataTable<StringHolder>("table", cols, new ListDataProvider<StringHolder>(data), 5);
            TempPanel panel = new TempPanel("test");
            HeadersToolbar toolbar = new HeadersToolbar(table, null);
            table.addTopToolbar(toolbar);
            panel.add(table);
            tester.startComponentInPage(panel);
            System.out.println();
            tester.assertLabel(toolbar.getPath().substring(2) + ":headers:1:header:label", "Data");
            tester.assertLabel("test:table:body:rows:3:cells:1:cell", "Cherry");
            tester.assertLabel("test:table:body:rows:2:cells:1:cell", "Banana");
            tester.assertLabel("test:table:body:rows:1:cells:1:cell", "Apple");
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

        @Test
        public void testCustomHeader() throws SecurityException, NoSuchMethodException {
            List<IColumn<StringHolder>> cols = new ArrayList<IColumn<StringHolder>>();
            Method method = StringHolder.class.getMethod("getData2");
            Editor e = method.getAnnotation(Editor.class);
            DisplayType dt = method.getAnnotation(DisplayType.class);
            StringResource sr = method.getAnnotation(StringResource.class);
            LabelResourcePrefix lrp = StringHolder.class.getAnnotation(LabelResourcePrefix.class);
            IColumn<StringHolder> column = ColumnFactory.getColumn(e, dt, null, sr, "Data2", StringHolder.class, null, getListProvider(), lrp, "");
            assertTrue(column instanceof FlexibleNonEditableColumn);
            cols.add(column);
            DataTable<StringHolder> table = new DataTable<StringHolder>("table", cols, new ListDataProvider<StringHolder>(data), 5);
            TempPanel panel = new TempPanel("test");
            HeadersToolbar toolbar = new HeadersToolbar(table, null);
            table.addTopToolbar(toolbar);
            panel.add(table);
            tester.startComponentInPage(panel);

            tester.assertLabel(toolbar.getPath().substring(2) + ":headers:1:header:label", "Dummdidumm");
            tester.assertLabel("test:table:body:rows:3:cells:1:cell", "Cherry");
            tester.assertLabel("test:table:body:rows:2:cells:1:cell", "Banana");
            tester.assertLabel("test:table:body:rows:1:cells:1:cell", "Apple");

        }


        @SuppressWarnings("unused")
        @Prototype(Prototypes.SHORT)
        @LabelResourcePrefix("tralala")
        private static class StringHolder implements Serializable {

            private static final long serialVersionUID = 8070868717307881900L;
            private String data;

            public StringHolder(String data) {
                this.data = data;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            @StringResource("tralala")
            public String getData2() {
                return data;
            }

            public void setData2(String data) {
                this.data = data;
            }

        }

}
