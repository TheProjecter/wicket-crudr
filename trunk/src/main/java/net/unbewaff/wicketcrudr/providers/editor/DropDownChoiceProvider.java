/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;

import java.util.List;

import net.unbewaff.wicketcrudr.components.IEditorFacade;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class DropDownChoiceProvider<T> implements IEditorProvider<T> {

    private static final long serialVersionUID = 5116591719887727709L;


    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.providers.editor.IEditorProvider#newEditor(net.unbewaff.wicketcrudr.components.IEditorFacade, java.lang.String, org.apache.wicket.model.IModel)
     */
    @Override
    public FormComponent<T> newEditor(final IEditorFacade parent, String componentId, IModel<T> model, List<T> choices) {
        return new DropDownChoice<T>(componentId, model, choices) {

            private static final long serialVersionUID = -3549287926576880455L;

            @Override
            protected void onModelChanged() {
                super.onModelChanged();
                parent.onModelChanged();
            }

            @Override
            protected void onModelChanging() {
                super.onModelChanging();
                parent.onModelChanging();
            }
        };
    }

}
