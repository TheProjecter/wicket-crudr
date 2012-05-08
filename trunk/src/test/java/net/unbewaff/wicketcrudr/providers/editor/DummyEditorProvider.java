/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;

import net.unbewaff.wicketcrudr.components.ICrudrDataProvider;
import net.unbewaff.wicketcrudr.components.IEditorFacade;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class DummyEditorProvider<T extends ICrudrDataProvider<T>> implements IEditorProvider<T> {

    private final Editor editor;

    /**
     * @param editor
     */
    public DummyEditorProvider(Editor editor) {
        this.editor = editor;
    }

    /**
     * @param editor
     */
    public DummyEditorProvider(Editor editor, ICrudrDataProvider<T> dataProvider) {
        this.editor = editor;
    }
    /**
     * @author David Hendrix (Nicktarix)
     *
     */
    public enum Editor {
        DROPDOWNCHOICE
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.providers.editor.IEditorProvider#newEditor(net.unbewaff.wicketcrudr.components.IEditorFacade, java.lang.String, org.apache.wicket.model.IModel)
     */
    @Override
    public FormComponent<T> newEditor(IEditorFacade parent, String componentId, IModel<T> model) {
        FormComponent<T> retValue;
        switch (editor) {
            case DROPDOWNCHOICE:
                retValue = new DropDownChoiceProvider<T>().newEditor(parent, componentId, model);
                break;
            default:
                retValue = null;

        }
        return retValue;
    }

}
