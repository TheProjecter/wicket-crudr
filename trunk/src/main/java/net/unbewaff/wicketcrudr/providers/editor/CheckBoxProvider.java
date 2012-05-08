package net.unbewaff.wicketcrudr.providers.editor;


import net.unbewaff.wicketcrudr.components.IEditorFacade;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;

/**
 * A simple implementation creating a CheckBox.
 *
 * @author David Hendrix (Nicktarix)
 *
 * @param <T>
 */
public class CheckBoxProvider implements IEditorProvider<Boolean> {

    private static final long serialVersionUID = -7231019074574082991L;

    @Override
    public FormComponent<Boolean> newEditor(final IEditorFacade parent, String componentId, IModel<Boolean> model) {
        return new CheckBox(componentId, model) {
            private static final long serialVersionUID = 1L;

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
