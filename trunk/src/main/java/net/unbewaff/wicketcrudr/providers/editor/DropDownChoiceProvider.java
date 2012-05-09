/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;


import net.unbewaff.wicketcrudr.components.ICrudrDataProvider;
import net.unbewaff.wicketcrudr.components.IEditorFacade;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class DropDownChoiceProvider<V extends ICrudrDataProvider<V>> implements IEditorProvider<V> {

    private static final long serialVersionUID = 5116591719887727709L;
    private final IChoiceRenderer<V> renderer;


    /**
     * @param renderer
     */
    public DropDownChoiceProvider(IChoiceRenderer<V> renderer) {
        this.renderer = renderer;
    }


    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.providers.editor.IEditorProvider#newEditor(net.unbewaff.wicketcrudr.components.IEditorFacade, java.lang.String, org.apache.wicket.model.IModel)
     */
    @Override
    public FormComponent<V> newEditor(final IEditorFacade parent, String componentId, IModel<V> model) {
        V dataProvider = model.getObject();

        return new DropDownChoice<V>(componentId, model, dataProvider.getList(), renderer) {

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
