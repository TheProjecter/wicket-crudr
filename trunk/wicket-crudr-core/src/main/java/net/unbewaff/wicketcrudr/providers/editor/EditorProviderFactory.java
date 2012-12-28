/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import net.unbewaff.wicketcrudr.annotations.DisplayType;
import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;

import org.apache.wicket.util.time.Time;

/**
 * A Factory to create {@link net.unbewaff.wicketcrudr.providers.editor.IEditorProvider<T>} instances
 * @author David Hendrix (Nicktarix)
 *
 */
public class EditorProviderFactory {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T extends Serializable> IEditorProvider<T> getEditorProvider(Editor e, final DisplayType.Display displayType, Class<?> returnType, final String property, String resourcePrefix) {
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
			    ep = new DropDownChoiceProvider(getChoiceRenderer(displayType, property, resourcePrefix));
                break;
            case PALETTE:
            	ep = (IEditorProvider<T>) new PaletteProvider<T>(getChoiceRenderer(displayType, property, resourcePrefix));
            	break;
            case DATE:
                ep = (IEditorProvider<T>) new DateEditorProvider();
                break;
            default:
                ep = getDefaultEditorProvider(returnType, getChoiceRenderer(displayType, property, resourcePrefix));
        }
        return ep;
    }


	/**
	 * @param displayType the Lister Annotation
	 * @param property the property to access the data
	 * @param resourcePrefix the resource Prefix
	 * @return a matching ChoiceRendererProvider
	 */
	@SuppressWarnings("rawtypes")
	private static ChoiceRendererProvider getChoiceRenderer(final DisplayType.Display displayType, final String property, String resourcePrefix) {
		ChoiceRendererProvider renderer = null;
		if (displayType != null) {
		    if (DisplayType.Display.RESOURCE.equals(displayType)) {
		        renderer = new ChoiceRendererProvider(resourcePrefix, property);
		    }
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
            ep = (IEditorProvider<T>) NumberFieldProvider.newInstance(returnType);
        } else if (returnType.isAssignableFrom(ICrudrListProvider.class)) {
            ep = (IEditorProvider<T>) new DropDownChoiceProvider(renderer);
        } else if (returnType.isAssignableFrom(Collection.class)) {
            ep = (IEditorProvider<T>) new PaletteProvider<T>(renderer);
        } else if (returnType.equals(Date.class) || returnType.equals(Time.class)) {
            ep = (IEditorProvider<T>) new DateEditorProvider();
        } else {
            ep = (IEditorProvider<T>) new TextFieldProvider<T>();
        }
        return ep;
    }
}
