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
public class DummyEditorProvider<T> implements IEditorProvider<T> {

    private final Editor editor;
    private ICrudrDataProvider<T> dataProvider;

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
        this.dataProvider = dataProvider;
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
    public FormComponent<T> newEditor(IEditorFacade parent, String componentId, IModel<T> model, List<T> choices) {
        FormComponent<T> retValue;
        switch (editor) {
            case DROPDOWNCHOICE:
                if (dataProvider == null) {
                    throw new IllegalStateException("Need to set DataProvider before creating a DropDownChoice.");
                }
                retValue = new DropDownChoiceProvider<T>(dataProvider).newEditor(parent, componentId, model, choices);
                break;
            default:
                retValue = null;

        }
        return retValue;
    }

}
