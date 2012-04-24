/**
 *
 */
package net.unbewaff.wicketcrudr.columns;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.Lister;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
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
    public void testDefaultLister() throws SecurityException, NoSuchMethodException {
        List<IColumn<StringHolder>> cols = new ArrayList<IColumn<StringHolder>>();
        Method method = StringHolder.class.getMethod("getData");
        IColumn<StringHolder> column = ColumnFactory.getColumn(method.getAnnotation(Lister.class), method.getAnnotation(Editor.class), method.getName(), StringHolder.class);
        assertTrue(column instanceof PropertyColumn);
        PropertyColumn<StringHolder> pc = (PropertyColumn<ColumnFactoryTest.StringHolder>)column;
        assertTrue(pc.getDisplayModel() instanceof StringResourceModel);
        assertEquals("Data", pc.getPropertyExpression());
        cols.add(column);
        DataTable<StringHolder> table = new DataTable<StringHolder>("table", cols, new ListDataProvider<StringHolder>(data), 5);
        TempPanel panel = new TempPanel("test");
        panel.add(table);
        tester.startComponentInPage(panel);
    }

    @Test
    public void testCustomDisplay() throws SecurityException, NoSuchMethodException {
        List<IColumn<StringHolder>> cols = new ArrayList<IColumn<StringHolder>>();
        Method method = StringHolder.class.getMethod("getData2");
        IColumn<StringHolder> column = ColumnFactory.getColumn(method.getAnnotation(Lister.class), method.getAnnotation(Editor.class), method.getName(), StringHolder.class);
        assertTrue(column instanceof PropertyColumn);
        PropertyColumn<StringHolder> pc = (PropertyColumn<ColumnFactoryTest.StringHolder>)column;
        assertTrue(pc.getDisplayModel() instanceof StringResourceModel);
        assertEquals("Data2", pc.getPropertyExpression());
        cols.add(column);
        DataTable<StringHolder> table = new DataTable<StringHolder>("table", cols, new ListDataProvider<StringHolder>(data), 5);
        TempPanel panel = new TempPanel("test");
        panel.add(table);
        tester.startComponentInPage(panel);
    }


    private static class StringHolder implements Serializable {

        private static final long serialVersionUID = 8070868717307881900L;
        private String data;

        public StringHolder(String data) {
            this.data = data;
        }

        @SuppressWarnings("unused")
        @Lister
        public String getData() {
            return data;
        }

        @SuppressWarnings("unused")
        public void setData(String data) {
            this.data = data;
        }

        @Lister(displayAs="tralala")
        public String getData2() {
            return data;
        }

        public void setData2(String data) {
            this.data = data;
        }

    }

}
