/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.components.ICrudrListProvider;
import net.unbewaff.wicketcrudr.components.IEditorFacade;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class DummyEditorProvider<T extends ICrudrListProvider<T> & Serializable> implements IEditorProvider<T> {

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
    public DummyEditorProvider(Editor editor, ICrudrListProvider<T> dataProvider) {
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
    public FormComponent<T> newEditor(IEditorFacade parent, String componentId, IModel<T> model, ICrudrListProvider<T> listProvider) {
        FormComponent<T> retValue;
        switch (editor) {
            case DROPDOWNCHOICE:
                retValue = new DropDownChoiceProvider<T>(null).newEditor(parent, componentId, model, null);
                break;
            default:
                retValue = null;

        }
        return retValue;
    }

}
