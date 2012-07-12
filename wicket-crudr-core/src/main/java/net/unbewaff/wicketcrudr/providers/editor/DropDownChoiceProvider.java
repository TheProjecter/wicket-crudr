/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;


import java.io.Serializable;

import net.unbewaff.wicketcrudr.components.ICrudrListProvider;
import net.unbewaff.wicketcrudr.components.IEditorFacade;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class DropDownChoiceProvider<V extends ICrudrListProvider<V> & Serializable> implements IEditorProvider<V> {

    private static final long serialVersionUID = 5116591719887727709L;
    private final ChoiceRendererProvider<V> rendererProvider;


    /**
     * @param rendererProvider
     */
    public DropDownChoiceProvider(ChoiceRendererProvider<V> renderer) {
        this.rendererProvider = renderer;
    }


    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.providers.editor.IEditorProvider#newEditor(net.unbewaff.wicketcrudr.components.IEditorFacade, java.lang.String, org.apache.wicket.model.IModel)
     */
    @Override
    public FormComponent<V> newEditor(final IEditorFacade parent, String componentId, IModel<V> model, ICrudrListProvider<V> listProvider) {
        V dataProvider = model.getObject();

        DropDownChoice<V> dropDownChoice = new DropDownChoice<V>(componentId, model, dataProvider.getList()) {

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
        Component parentComponent = null;
        if (parent instanceof Component) {
            parentComponent = (Component)parent;
        } else {
            parentComponent = dropDownChoice;
        }
        if (rendererProvider != null) {
            dropDownChoice.setChoiceRenderer(rendererProvider.getRenderer(parentComponent));
        }
        return dropDownChoice;
    }

}
