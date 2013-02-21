/**
 *
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;

import net.unbewaff.WicketApplication;
import net.unbewaff.wicketcrudr.annotations.member.InnerPrototype;
import net.unbewaff.wicketcrudr.annotations.member.StringResource;
import net.unbewaff.wicketcrudr.datablocks.IProperty;
import net.unbewaff.wicketcrudr.datablocks.IPrototypeData;

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
        final IPrototypeData prototype = mockery.mock(IPrototypeData.class);
        final IProperty resourceKeyProperty = mockery.mock(IProperty.class);
        mockery.checking(new Expectations() {{
            allowing(prototype).getResourceKeyProperty(); will(returnValue(resourceKeyProperty));
            allowing(resourceKeyProperty).getProperty(); will(returnValue("Id"));
        }});
        StringResourceModelProvider<WithResourceKeyWrapper> provider = new StringResourceModelProvider<WithResourceKeyWrapper>("", prototype);
        ResourceModel result = (ResourceModel) provider.newLabelModel(new Model<WithResourceKeyWrapper>(new WithResourceKeyWrapper()));
        Label label = (Label)tester.startComponent(new Label("test", result));
        assertEquals("I am the Id.", label.getDefaultModelObject());
    }

    @Test
    public void testWithIdWithResource() {
        WicketTester tester = new WicketTester(new WicketApplication());
        Mockery mockery = new Mockery();
        final IPrototypeData prototype = mockery.mock(IPrototypeData.class);
        final IProperty resourceKeyProperty = mockery.mock(IProperty.class);
        mockery.checking(new Expectations() {{
            allowing(prototype).getResourceKeyProperty(); will(returnValue(resourceKeyProperty));
            allowing(resourceKeyProperty).getProperty(); will(returnValue("Id"));
        }});

        StringResourceModelProvider<WithResourceKeyWrapper> provider = new StringResourceModelProvider<WithResourceKeyWrapper>("prefix", prototype);
        ResourceModel result = (ResourceModel) provider.newLabelModel(new Model<WithResourceKeyWrapper>(new WithResourceKeyWrapper()));
        Label label = (Label)tester.startComponent(new Label("test", result));
        assertEquals("I am the Id resource.", label.getDefaultModelObject());
    }

    @Test
    public void testPlainEntity() {
        WicketTester tester = new WicketTester(new WicketApplication());
        Mockery mockery = new Mockery();
        final InnerPrototype innerType = mockery.mock(InnerPrototype.class);
        final IPrototypeData prototype = mockery.mock(IPrototypeData.class);
        mockery.checking(new Expectations() {{
            allowing(innerType).value(); will(returnValue(TestType.class));
            allowing(prototype).getResourceKeyProperty(); will(returnValue(null));
        }});
        StringResourceModelProvider<TestType> provider = new StringResourceModelProvider<TestType>("", prototype);
        ResourceModel result = (ResourceModel) provider.newLabelModel(new Model<TestType>(new TestType()));
        Label label = (Label)tester.startComponent(new Label("test", result));
        assertEquals("I am the string representation.", label.getDefaultModelObject());

    }

    private class TestType implements Serializable {

        public String getId() {
            return "I am the Id.";
        }

        public String getNoId() {
            return "I am not.";
        }

        @Override
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
        @StringResource
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
        @Override
        public String toString() {
            return data.toString();
        }
    }

}
