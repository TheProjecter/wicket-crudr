package net.unbewaff.wicketcrudr.providers.editor;


import java.io.Serializable;

import net.unbewaff.wicketcrudr.components.ICrudrListProvider;
import net.unbewaff.wicketcrudr.components.IEditorFacade;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

/**
 * A simple implementation creating a TextField.
 *
 * @author David Hendrix (Nicktarix)
 *
 * @param <T>
 */
class TextAreaProvider<T extends Serializable> implements IEditorProvider<T> {

    private static final long serialVersionUID = -7231019074574082991L;

    @Override
    public FormComponent<T> newEditor(final IEditorFacade parent, String componentId, IModel<T> model, ICrudrListProvider<T> listProvider) {
        return new TextArea<T>(componentId, model) {
            private static final long serialVersionUID = 1L;

            @Override
            public <C> IConverter<C> getConverter(final Class<C> type) {
                IConverter<C> c = parent.getConverter(type);
                return c != null ? c : super.getConverter(type);
            }

            @Override
            protected void onModelChanged() {
                super.onModelChanged();
                if (parent != null) {
                    parent.onModelChanged();
                }
            }

            @Override
            protected void onModelChanging() {
                super.onModelChanging();
                if (parent != null) {
                    parent.onModelChanging();
                }
            }
        };
    }

}
