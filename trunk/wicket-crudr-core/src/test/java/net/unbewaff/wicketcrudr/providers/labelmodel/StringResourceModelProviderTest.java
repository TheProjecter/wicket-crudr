/**
 *
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.annotations.InnerType;
import net.unbewaff.wicketcrudr.annotations.ResourceKey;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.util.tester.WicketTester;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class StringResourceModelProviderTest {

	@Before
	public void setUp() {

	}

	@Test
	public void testWithIdWithoutResource() {
		WicketTester tester = new WicketTester();
		Mockery mockery = new Mockery();
		final InnerType innerType = mockery.mock(InnerType.class);
    	mockery.checking(new Expectations() {{
    		allowing(innerType).type(); will(returnValue(WithResourceKeyWrapper.class));
    	}});
		StringResourceModelProvider<WithResourceKeyWrapper> provider = new StringResourceModelProvider<WithResourceKeyWrapper>("", innerType);
		ResourceModel result = (ResourceModel) provider.newLabelModel(new Model<WithResourceKeyWrapper>(new WithResourceKeyWrapper()));
		tester.startComponent(new Label("test", result));
		tester.getLastRenderedPage();
		tester.debugComponentTrees();
		tester.assertLabel("test", "I am the Id.");
	}

	@Test
	public void testWithIdWithResource() {
		WicketTester tester = new WicketTester();
		Mockery mockery = new Mockery();
		final InnerType innerType = mockery.mock(InnerType.class);
    	mockery.checking(new Expectations() {{
    		allowing(innerType).type(); will(returnValue(WithResourceKeyWrapper.class));
    	}});
		StringResourceModelProvider<WithResourceKeyWrapper> provider = new StringResourceModelProvider<WithResourceKeyWrapper>("prefix", innerType);
		ResourceModel result = (ResourceModel) provider.newLabelModel(new Model<WithResourceKeyWrapper>(new WithResourceKeyWrapper()));
		tester.startComponent(new Label("test", result));
		tester.assertLabel("test", "prefix.I am the Id.");
	}

	private class TestType {

		public String getId() {
			return "I am the Id.";
		}

		public String getNoId() {
			return "I am not.";
		}

		public String toString() {
			return "I am the string representation.";
		}
	}

	private class WithResourceKeyWrapper implements Serializable {

		private TestType data = new TestType();

		/**
		 * @return
		 * @see net.unbewaff.wicketcrudr.providers.labelmodel.StringResourceModelProviderTest.TestType#getId()
		 */
		@ResourceKey
		public String getId() {
			return data.getId();
		}

		/**
		 * @return
		 * @see net.unbewaff.wicketcrudr.providers.labelmodel.StringResourceModelProviderTest.TestType#getNoId()
		 */
		public String getNoId() {
			return data.getNoId();
		}

		/**
		 * @return
		 * @see net.unbewaff.wicketcrudr.providers.labelmodel.StringResourceModelProviderTest.TestType#toString()
		 */
		public String toString() {
			return data.toString();
		}
	}

}
