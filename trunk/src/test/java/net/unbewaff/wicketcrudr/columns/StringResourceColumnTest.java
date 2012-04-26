/**
 *
 */
package net.unbewaff.wicketcrudr.columns;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.unbewaff.TempPanel;

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
public class StringResourceColumnTest {

    private WicketTester tester;
    private List<Food> data = new ArrayList<Food>() {/**
         *
         */
        private static final long serialVersionUID = 8397804885542314329L;

    {
        add(new Food("Apple"));
        add(new Food("Banana"));
        add(new Food("Cherry"));
    }};

    @Before
    public void setUp() {
            tester = new WicketTester();
    }

    @Test
    public void testDisplay() {

        List<IColumn<Food>> cols = new ArrayList<IColumn<Food>>();
        cols.add(new StringResourceColumn<Food>(Model.of("Display"), "holder", "Food.${data}"));

        DataTable<Food> table = new DataTable<Food>("table", cols, new ListDataProvider<Food>(data), 5);
        TempPanel panel = new TempPanel("test");
        panel.add(table);
        tester.startComponentInPage(panel);
        tester.assertVisible("test:table:body:rows:1:cells:1:cell");
        tester.assertVisible("test:table:body:rows:2:cells:1:cell");
        tester.assertVisible("test:table:body:rows:3:cells:1:cell");
        tester.assertLabel("test:table:body:rows:1:cells:1:cell", "Steak");
        tester.assertLabel("test:table:body:rows:2:cells:1:cell", "Burger");
        tester.assertLabel("test:table:body:rows:3:cells:1:cell", "Beer");
    }



    private static class Food implements Serializable {

        private StringHolder holder;

        public Food(String string) {
            holder = new StringHolder(string);
        }

        public StringHolder getHolder() {
            return holder;
        }

        public void setHolder(StringHolder holder) {
            this.holder = holder;
        }
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
