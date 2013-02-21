/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.datablocks.IProperty;

/**
 * A Factory to create {@link net.unbewaff.wicketcrudr.providers.editor.IEditorProvider<T>} instances
 * @author David Hendrix (Nicktarix)
 *
 */
public class EditorProviderFactory {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T extends Serializable> IEditorProvider<T> getEditorProvider(String stringResourcePrefix, IProperty property) {
        IEditorProvider<T> ep = null;
        String propertyName = property.getProperty();
        switch (property.getEditorType()) {
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
            ep = new DropDownChoiceProvider(getChoiceRenderer(propertyName, stringResourcePrefix));
            break;
        case PALETTE:
            ep = new PaletteProvider<T>(getChoiceRenderer(propertyName, stringResourcePrefix));
            break;
        case DATE:
            ep = (IEditorProvider<T>) new DateEditorProvider();
            break;
        default:
            //TODO number
            ep = new TextFieldProvider<T>();
        }
        return ep;
    }


    /**
     * @param property the property to access the data
     * @param resourcePrefix the resource Prefix
     * @return a matching ChoiceRendererProvider
     */
    @SuppressWarnings("rawtypes")
    private static ChoiceRendererProvider getChoiceRenderer(final String property, String resource) {
        ChoiceRendererProvider renderer = null;
        if (resource != null) {
            renderer = new ChoiceRendererProvider(resource, property);
        }
        return renderer;
    }
}
