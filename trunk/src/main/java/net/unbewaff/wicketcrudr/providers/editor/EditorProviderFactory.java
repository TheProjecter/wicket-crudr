/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;

import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.components.ICrudrDataProvider;

/**
 * A Factory to create {@link net.unbewaff.wicketcrudr.providers.editor.IEditorProvider<T>} instances
 * @author David Hendrix (Nicktarix)
 *
 */
public class EditorProviderFactory {

    public static <T> IEditorProvider<T> getEditorProvider(Editor e, Class<?> returnType, ICrudrDataProvider<T> dataProvider) {
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
                ep = new DropDownChoiceProvider<T>();
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
