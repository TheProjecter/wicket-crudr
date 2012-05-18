/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editorpanel;

import net.unbewaff.wicketcrudr.annotations.Editor;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;
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
						return null;
					}

                };
                break;
            default:
                scp = new TextFieldPanelProvider();
        }
        return scp;
    }

}
