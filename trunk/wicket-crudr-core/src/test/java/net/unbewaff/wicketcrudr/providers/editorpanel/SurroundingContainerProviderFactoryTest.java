/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editorpanel;

import static org.junit.Assert.assertTrue;
import net.unbewaff.wicketcrudr.annotations.EditorType;
import net.unbewaff.wicketcrudr.datablocks.IProperty;

import org.apache.log4j.Logger;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class SurroundingContainerProviderFactoryTest {

    private Mockery mockery = new Mockery();
    private static final transient Logger logger = Logger.getLogger(SurroundingContainerProviderFactoryTest.class);


    @Test
    public void testForTextFieldAnnotation() {
        final IProperty property = mockery.mock(IProperty.class);
        mockery.checking(new Expectations() {{
            allowing(property).getEditorType(); will(returnValue(EditorType.TEXTFIELD));
        }});
        ISurroundingContainerProvider scp = SurroundingContainerProviderFactory.getContainerProvider(property);
        assertTrue(scp instanceof TextFieldPanelProvider);
        mockery.assertIsSatisfied();
    }


    @Test(expected=UnsupportedOperationException.class)
    public void testForAjaxLinkAnnotation() {
        final IProperty property = mockery.mock(IProperty.class);
        mockery.checking(new Expectations() {{
            allowing(property).getEditorType(); will(returnValue(EditorType.AJAXLINK));
        }});
        ISurroundingContainerProvider scp = SurroundingContainerProviderFactory.getContainerProvider(property);
        mockery.assertIsSatisfied();
    }

    @Test
    public void testForCheckBoxAnnotation() {
        final IProperty property = mockery.mock(IProperty.class);
        mockery.checking(new Expectations() {{
            allowing(property).getEditorType(); will(returnValue(EditorType.CHECKBOX));
        }});
        ISurroundingContainerProvider scp = SurroundingContainerProviderFactory.getContainerProvider(property);
        assertTrue(scp instanceof CheckBoxPanelProvider);
        mockery.assertIsSatisfied();
    }

    @Test
    public void testForDateAnnotation() {
        final IProperty property = mockery.mock(IProperty.class);
        mockery.checking(new Expectations() {{
            allowing(property).getEditorType(); will(returnValue(EditorType.DATE));
        }});
        ISurroundingContainerProvider scp = SurroundingContainerProviderFactory.getContainerProvider(property);
        assertTrue(scp instanceof TextFieldPanelProvider);
        mockery.assertIsSatisfied();
    }

    @Test
    public void testForDropDownChoiceAnnotation() {
        final IProperty property = mockery.mock(IProperty.class);
        mockery.checking(new Expectations() {{
            allowing(property).getEditorType(); will(returnValue(EditorType.DROPDOWNCHOICE));
        }});
        ISurroundingContainerProvider scp = SurroundingContainerProviderFactory.getContainerProvider(property);
        assertTrue(scp instanceof DropDownChoicePanelProvider);
        mockery.assertIsSatisfied();
    }

    @Test
    public void testForPaletteAnnotation() {
        final IProperty property = mockery.mock(IProperty.class);
        mockery.checking(new Expectations() {{
            allowing(property).getEditorType(); will(returnValue(EditorType.PALETTE));
        }});
        ISurroundingContainerProvider scp = SurroundingContainerProviderFactory.getContainerProvider(property);
        assertTrue(scp instanceof PalettePanelProvider);
        mockery.assertIsSatisfied();
    }

    @Test
    public void testForTextAreaAnnotation() {
        final IProperty property = mockery.mock(IProperty.class);
        mockery.checking(new Expectations() {{
            allowing(property).getEditorType(); will(returnValue(EditorType.TEXTAREA));
        }});
        ISurroundingContainerProvider scp = SurroundingContainerProviderFactory.getContainerProvider(property);
        assertTrue(scp instanceof TextAreaPanelProvider);
        mockery.assertIsSatisfied();
    }

    @Test
    public void testForPasswordAnnotation() {
        final IProperty property = mockery.mock(IProperty.class);
        mockery.checking(new Expectations() {{
            allowing(property).getEditorType(); will(returnValue(EditorType.PASSWORD));
        }});
        ISurroundingContainerProvider scp = SurroundingContainerProviderFactory.getContainerProvider(property);
        assertTrue(scp instanceof PasswordPanelProvider);
        mockery.assertIsSatisfied();
    }

}
