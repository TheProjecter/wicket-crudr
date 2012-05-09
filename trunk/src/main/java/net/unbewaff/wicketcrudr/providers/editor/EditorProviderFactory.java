/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;

import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Lister.Display;

/**
 * A Factory to create {@link net.unbewaff.wicketcrudr.providers.editor.IEditorProvider<T>} instances
 * @author David Hendrix (Nicktarix)
 *
 */
public class EditorProviderFactory {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> IEditorProvider<T> getEditorProvider(Editor e, final Lister l, Class<?> returnType, final String property) {
        IEditorProvider<T> ep = null;
        switch (e.editAs()) {
            case TEXTFIELD:
                ep = new TextFieldProvider<T>();
                break;
            case TEXTAREA:
                ep = new TextAreaProvider<T>();
                break;
            case CHECKBOX:
                ep = (IEditorProvider<T>) new CheckBoxProvider();
                break;
            case AJAXLINK:
                throw new UnsupportedOperationException("AjaxLink isn't implemented yet.");
            case DROPDOWNCHOICE:
                IChoiceRenderer<T> renderer = null;
                if (l != null) {
                    if (Display.RESOURCE.equals(l.displayAs())) {
                        renderer = new IChoiceRenderer<T>() {

                            private static final long serialVersionUID = 6502141892434122186L;

                            @Override
                            public Object getDisplayValue(T object) {
                                return new StringResourceModel(l.resourcePrefix(), new PropertyModel<Object>(object, property));
                            }

                            @Override
                            public String getIdValue(T object, int index) {
                                return String.valueOf(index);
                            }
                        };
                    }
                }
                ep = new DropDownChoiceProvider(renderer);
                break;
            default:
                ep = getDefaultEditorProvider(returnType);
        }
        return ep;
    }

    private static <T> IEditorProvider<T> getDefaultEditorProvider(Class<?> returnType) {
        if (returnType == null) {
            throw new IllegalArgumentException("Need to know the returnType. (AKA can't be null)");
        }
        IEditorProvider<T> ep = new TextFieldProvider<T>();
        if (returnType.equals(boolean.class) || Boolean.class.isAssignableFrom(returnType)) {
            ep = (IEditorProvider<T>) new CheckBoxProvider();
        } else if (Number.class.isAssignableFrom(returnType) && Comparable.class.isAssignableFrom(returnType)) {
            ep = (IEditorProvider<T>) NumberFieldProvider.newInstance(returnType);
        }
        return ep;
    }



}
