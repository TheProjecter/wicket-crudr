/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editorpanel;

import net.unbewaff.wicketcrudr.annotations.Editor;

import org.apache.wicket.markup.html.form.IChoiceRenderer;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class SurroundingContainerProviderFactory {

    public static  ISurroundingContainerProvider getContainerProvider(Editor e) {
        ISurroundingContainerProvider scp = null;
        switch (e.editAs()) {
            case CHECKBOX:
                scp = new CheckBoxPanelProvider();
                break;
            case AJAXLINK:
                throw new UnsupportedOperationException("Not implemented yet.");
            case TEXTAREA:
                scp = new TextAreaPanelProvider();
                break;
            case TEXTFIELD:
            case DATE:
                scp = new TextFieldPanelProvider();
                break;
            case DROPDOWNCHOICE:
                scp = new DropDownChoicePanelProvider();
                break;
            case PALETTE:
                scp = new PalettePanelProvider() {

                    @Override
                    public IChoiceRenderer newChoicesRenderer() {
                        return new IChoiceRenderer() {

                            @Override
                            public Object getDisplayValue(Object object) {
                                throw new IllegalStateException("PalettePanel has to have a custom IChoicerenderer.");
                            }

                            @Override
                            public String getIdValue(Object object, int index) {
                                throw new IllegalStateException("PalettePanel has to have a custom IChoicerenderer.");
                            }
                        };
                    }

                };
                break;
            case PASSWORD:
                scp = new PasswordPanelProvider();
                break;
            default:
                scp = new TextFieldPanelProvider();
        }
        return scp;
    }

}
