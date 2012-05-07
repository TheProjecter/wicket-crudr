/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;

import net.unbewaff.wicketcrudr.components.ICrudrDataProvider;
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
    private final ICrudrDataProvider<T> dataProvider;

    /**
     * @param dataProvider
     */
    public DropDownChoiceProvider(ICrudrDataProvider<T> dataProvider) {
        this.dataProvider = dataProvider;
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.providers.editor.IEditorProvider#newEditor(net.unbewaff.wicketcrudr.components.IEditorFacade, java.lang.String, org.apache.wicket.model.IModel)
     */
    @Override
    public FormComponent<T> newEditor(final IEditorFacade parent, String componentId, IModel<T> model) {
        return new DropDownChoice<T>(componentId, model, dataProvider.getList()) {

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
