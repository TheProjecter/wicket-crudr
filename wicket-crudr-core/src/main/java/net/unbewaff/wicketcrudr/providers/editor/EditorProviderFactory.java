/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.member.DisplayType;
import net.unbewaff.wicketcrudr.annotations.member.StringResource;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;

import org.apache.wicket.util.time.Time;

/**
 * A Factory to create {@link net.unbewaff.wicketcrudr.providers.editor.IEditorProvider<T>} instances
 * @author David Hendrix (Nicktarix)
 *
 */
public class EditorProviderFactory {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T extends Serializable> IEditorProvider<T> getEditorProvider(Editor e, final DisplayType.Display displayType, Class<?> returnType, final String property, StringResource stringResource) {
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
            ep = new DropDownChoiceProvider(getChoiceRenderer(property, stringResource));
            break;
        case PALETTE:
            ep = new PaletteProvider<T>(getChoiceRenderer(property, stringResource));
            break;
        case DATE:
            ep = (IEditorProvider<T>) new DateEditorProvider();
            break;
        default:
            ep = getDefaultEditorProvider(returnType, getChoiceRenderer(property, stringResource));
        }
        return ep;
    }


    /**
     * @param property the property to access the data
     * @param resourcePrefix the resource Prefix
     * @return a matching ChoiceRendererProvider
     */
    @SuppressWarnings("rawtypes")
    private static ChoiceRendererProvider getChoiceRenderer(final String property, StringResource resource) {
        ChoiceRendererProvider renderer = null;
        if (resource != null) {
            renderer = new ChoiceRendererProvider(resource, property);
        }
        return renderer;
    }


    @SuppressWarnings("unchecked")
    private static <T extends Serializable> IEditorProvider<T> getDefaultEditorProvider(Class<?> returnType, ChoiceRendererProvider<T> renderer) {
        if (returnType == null) {
            throw new IllegalArgumentException("Need to know the returnType. (AKA can't be null)");
        }
        IEditorProvider<T> ep = new TextFieldProvider<T>();
        if (returnType.equals(boolean.class) || Boolean.class.isAssignableFrom(returnType)) {
            ep = (IEditorProvider<T>) new CheckBoxProvider();
        } else if (Number.class.isAssignableFrom(returnType) && Comparable.class.isAssignableFrom(returnType)) {
            ep = NumberFieldProvider.newInstance(returnType);
        } else if (returnType.isAssignableFrom(ICrudrListProvider.class)) {
            ep = new DropDownChoiceProvider(renderer);
        } else if (returnType.isAssignableFrom(Collection.class)) {
            ep = new PaletteProvider<T>(renderer);
        } else if (returnType.equals(Date.class) || returnType.equals(Time.class)) {
            ep = (IEditorProvider<T>) new DateEditorProvider();
        } else {
            ep = new TextFieldProvider<T>();
        }
        return ep;
    }
}
