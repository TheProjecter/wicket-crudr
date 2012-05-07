/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editorpanel;

import net.unbewaff.wicketcrudr.annotations.Editor;

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
                scp = new TextFieldPanelProvider();
                break;
            case DROPDOWNCHOICE:
                scp = new DropDownChoicePanelProvider();
                break;
            default:
                scp = new TextFieldPanelProvider();
        }
        return scp;
    }

}
