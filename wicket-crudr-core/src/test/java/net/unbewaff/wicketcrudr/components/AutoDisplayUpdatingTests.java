/**
 * 
 */
package net.unbewaff.wicketcrudr.components;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.unbewaff.wicketcrudr.AutoDisplay;
import net.unbewaff.wicketcrudr.annotations.InnerType;
import net.unbewaff.wicketcrudr.annotations.InnerType.DisplayType;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Lister.InPlaceEditor;
import net.unbewaff.wicketcrudr.annotations.Order;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

/**
 * @author Nicktar
 *
 */
public class AutoDisplayUpdatingTests {
	
	WicketTester tester = new WicketTester();
	
	@Test
	public void testConstructor() {
		DataContainer dc = new DataContainer("data", "list1", "list2", "list3");
		assertEquals("data", dc.getData());
		assertEquals(3, dc.getList().size());
		assertEquals("list3", dc.getList().get(2));
	}
	
	@Test
	public void testRendering() {
		DataContainer dc = new DataContainer("data", "list1", "list2", "list3");
		AutoDisplay<DataContainer> display = new AutoDisplay<DataContainer>("id", Model.of(dc), DataContainer.class);
		tester.startComponentInPage(display);
		tester.assertComponent("id", AutoDisplay.class);
		tester.debugComponentTrees();
		String listpath = "id:border:border_body:view:2:fragmentContainer:value:body:list";
		tester.assertLabel(listpath + ":1:separator", "sep");
		tester.assertComponent(listpath + ":2:separator", Label.class);
		tester.assertLabel(listpath + ":2:separator", "sep");
		tester.assertLabel(listpath + ":1:text", "list1");
		tester.assertLabel(listpath + ":2:text", "list2");
		tester.assertLabel(listpath + ":3:text", "list3");
	}
	
	@Test
	public void testRenderingAfterUpdate() {
		DataContainer dc = new DataContainer("data", "list1", "list2", "list3");
		DataContainer dc2 = new DataContainer("data2", "lista", "listb", "listc", "listd");
		Model<DataContainer> model = Model.of(dc);
		final AutoDisplay<DataContainer> display = new AutoDisplay<DataContainer>("id", model, DataContainer.class);
		AjaxEventBehavior ajaxEventBehavior = new AjaxEventBehavior("onchange") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				target.add(display);
			}
			
		};
		display.add(ajaxEventBehavior);
		tester.startComponentInPage(display);
		tester.assertComponent("id", AutoDisplay.class);
		tester.debugComponentTrees();
		String listpath = "id:border:border_body:view:2:fragmentContainer:value:body:list";
		tester.assertLabel(listpath + ":1:separator", "sep");
		tester.assertLabel(listpath + ":2:separator", "sep");
		tester.assertLabel(listpath + ":1:text", "list1");
		tester.assertLabel(listpath + ":2:text", "list2");
		tester.assertLabel(listpath + ":3:text", "list3");
		model.setObject(dc2);
		tester.executeAjaxEvent(display, "onchange");
		tester.assertComponentOnAjaxResponse(display);
		String ajaxresponse = tester.getLastResponseAsString();
		System.out.println(ajaxresponse);
	}

	private class DataContainer implements Serializable {
		
		String data;
		List<String> list = new ArrayList<String>();
		
		public DataContainer(String...data) {
			this.data = data[0];
			for (int i = 1; i < data.length; i++) {
				list.add(data[i]);
			}
		}

		@Lister(editInPlace=InPlaceEditor.NONE)
		@Order(1)
		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		@Lister(editInPlace=InPlaceEditor.NONE)
		@Order(2)
		@InnerType(type=String.class, separator = "sep", displayAs=DisplayType.CONCATENATED)
		public List<String> getList() {
			return list;
		}

		public void setList(List<String> list) {
			this.list = list;
		}
	}

}
