/**
 * 
 */
package net.unbewaff.wicketcrudr.components;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.AutoDisplay;
import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Position;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

/**
 * @author Nicktar
 *
 */
public class AutoDisplayTest {
	
	WicketTester tester = new WicketTester();

	@Test
	public void renderForDisplayTest() {
		IModel<StringHolderDisplayWrapper> model = Model.of(new StringHolderDisplayWrapper(new StringHolder("first", "second")));
		Component c = new AutoDisplay<StringHolderDisplayWrapper>("id", model, StringHolderDisplayWrapper.class);
		tester.startComponentInPage(c);
		tester.debugComponentTrees();
		tester.assertLabel("id:border:border_body:view:1:fragmentContainer:label", "OtherString");
		tester.assertLabel("id:border:border_body:view:1:fragmentContainer:value", "second");
		tester.assertLabel("id:border:border_body:view:2:fragmentContainer:label", "String");
		tester.assertLabel("id:border:border_body:view:2:fragmentContainer:value", "first");
	}
	
	@Test
	public void renderForEditTest() {
		IModel<StringHolderEditWrapper> model = Model.of(new StringHolderEditWrapper(new StringHolder("first", "second")));
		Component c = new AutoDisplay<StringHolderEditWrapper>("id", model, StringHolderEditWrapper.class);
		tester.startComponentInPage(c);
		tester.debugComponentTrees();
		tester.assertLabel("id:border:border_body:view:1:fragmentContainer:label", "OtherString");
		tester.assertLabel("id:border:border_body:view:1:fragmentContainer:value", "second");
		tester.assertLabel("id:border:border_body:view:2:fragmentContainer:label", "String");
		tester.assertLabel("id:border:border_body:view:2:fragmentContainer:value", "first");
	}
	
	
	static class StringHolderDisplayWrapper implements Serializable {
		
		private static final long serialVersionUID = -2827254203725518250L;
		private StringHolder data;
		
		public StringHolderDisplayWrapper(StringHolder data) {
			this.data = data;
		}

		@Lister
		@Position(2)
		public String getString() {
			return data.getString();
		}

		public void setString(String string) {
			data.setString(string);
		}

		@Lister
		@Position(1)
		public String getOtherString() {
			return data.getOtherString();
		}

		public void setOtherString(String otherString) {
			data.setOtherString(otherString);
		}
	}
	
	
	private static class StringHolder implements Serializable {
		
		private String string;
		private String otherString;

		public StringHolder(String string, String otherString) {
			this.string = string;
			this.otherString = otherString;
		}

		public String getString() {
			return string;
		}

		public void setString(String string) {
			this.string = string;
		}

		public String getOtherString() {
			return otherString;
		}

		public void setOtherString(String otherString) {
			this.otherString = otherString;
		}
		
	}
	
	static class StringHolderEditWrapper implements Serializable {
		
		private StringHolder data;
		
		public StringHolderEditWrapper(StringHolder data) {
			super();
			this.data = data;
		}

		@Editor
		@Position(2)
		@Lister
		public String getString() {
			return data.getString();
		}

		public void setString(String string) {
			data.setString(string);
		}

		@Editor
		@Position(1)
		@Lister
		public String getOtherString() {
			return data.getOtherString();
		}

		public void setOtherString(String otherString) {
			data.setOtherString(otherString);
		}
	}
}
