/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;

import net.unbewaff.wicketcrudr.components.IEditorFacade;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class NumberFieldProvider<T extends Number & Comparable<T>> implements IEditorProvider<T> {

    private final Class<T> clazz;

    /**
     * @param clazz The returnType
     */
    public NumberFieldProvider(Class<T> clazz) {
        this.clazz = clazz;
    }

    public static NumberFieldProvider newInstance(Class<?> clazz) {
        return new NumberFieldProvider(clazz);

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * net.unbewaff.wicketcrudr.providers.editor.IEditorProvider#newEditor(net
     * .unbewaff.wicketcrudr.components.IEditorFacade, java.lang.String,
     * org.apache.wicket.model.IModel)
     */
    @Override
    public FormComponent<T> newEditor(final IEditorFacade parent, String componentId, IModel<T> model) {
        NumberTextField<T> numberTextField = new NumberTextField<T>(componentId, model) {
            @Override
            public <C> IConverter<C> getConverter(final Class<C> type) {
                IConverter<C> c = parent.getConverter(type);
                return c != null ? c : super.getConverter(type);
            }

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
        numberTextField.setType(clazz);
        return numberTextField;
    }

}
