/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editorpanel;

import static org.junit.Assert.assertTrue;
import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.Editor.EditorType;

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
        final Editor e = mockery.mock(Editor.class);
        mockery.checking(new Expectations() {{
            exactly(1).of(e).editAs(); will(returnValue(EditorType.TEXTFIELD));
        }});
        ISurroundingContainerProvider scp = SurroundingContainerProviderFactory.getContainerProvider(e);
        assertTrue(scp instanceof TextFieldPanelProvider);
        mockery.assertIsSatisfied();
    }


    @Test(expected=UnsupportedOperationException.class)
    public void testForAjaxLinkAnnotation() {
        final Editor e = mockery.mock(Editor.class);
        mockery.checking(new Expectations() {{
            exactly(1).of(e).editAs(); will(returnValue(EditorType.AJAXLINK));
        }});
        SurroundingContainerProviderFactory.getContainerProvider(e);
        mockery.assertIsSatisfied();
    }

    @Test
    public void testForCheckBoxAnnotation() {
        final Editor e = mockery.mock(Editor.class);
        mockery.checking(new Expectations() {{
            exactly(1).of(e).editAs(); will(returnValue(EditorType.CHECKBOX));
        }});
        ISurroundingContainerProvider scp = SurroundingContainerProviderFactory.getContainerProvider(e);
        assertTrue(scp instanceof CheckBoxPanelProvider);
        mockery.assertIsSatisfied();
    }

    @Test
    public void testForDateAnnotation() {
        final Editor e = mockery.mock(Editor.class);
        mockery.checking(new Expectations() {{
            exactly(1).of(e).editAs(); will(returnValue(EditorType.DATE));
        }});
        ISurroundingContainerProvider scp = SurroundingContainerProviderFactory.getContainerProvider(e);
        assertTrue(scp instanceof TextFieldPanelProvider);
        mockery.assertIsSatisfied();
    }

    @Test
    public void testForDropDownChoiceAnnotation() {
        final Editor e = mockery.mock(Editor.class);
        mockery.checking(new Expectations() {{
            exactly(1).of(e).editAs(); will(returnValue(EditorType.DROPDOWNCHOICE));
        }});
        ISurroundingContainerProvider scp = SurroundingContainerProviderFactory.getContainerProvider(e);
        assertTrue(scp instanceof DropDownChoicePanelProvider);
        mockery.assertIsSatisfied();
    }

    @Test
    public void testForPaletteAnnotation() {
        final Editor e = mockery.mock(Editor.class);
        mockery.checking(new Expectations() {{
            exactly(1).of(e).editAs(); will(returnValue(EditorType.PALETTE));
        }});
        ISurroundingContainerProvider scp = SurroundingContainerProviderFactory.getContainerProvider(e);
        assertTrue(scp instanceof PalettePanelProvider);
        mockery.assertIsSatisfied();
    }

    @Test
    public void testForTextAreaAnnotation() {
        final Editor e = mockery.mock(Editor.class);
        mockery.checking(new Expectations() {{
            exactly(1).of(e).editAs(); will(returnValue(EditorType.TEXTAREA));
        }});
        ISurroundingContainerProvider scp = SurroundingContainerProviderFactory.getContainerProvider(e);
        assertTrue(scp instanceof TextAreaPanelProvider);
        mockery.assertIsSatisfied();
    }

    @Test
    public void testForPasswordAnnotation() {
        final Editor e = mockery.mock(Editor.class);
        mockery.checking(new Expectations() {{
            exactly(1).of(e).editAs(); will(returnValue(EditorType.PASSWORD));
        }});
        ISurroundingContainerProvider scp = SurroundingContainerProviderFactory.getContainerProvider(e);
        assertTrue(scp instanceof PasswordPanelProvider);
        mockery.assertIsSatisfied();
    }

}
