/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;

import java.util.Collection;
import java.util.Date;

import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Lister.Display;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.util.time.Time;

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
			    ep = new DropDownChoiceProvider(getChoiceRenderer(l, property));
                break;
            case PALETTE:
            	ep = (IEditorProvider<T>) new PaletteProvider<T>(getChoiceRenderer(l, property));
            	break;
            case DATE:
                ep = (IEditorProvider<T>) new DateEditorProvider();
                break;
            default:
                ep = getDefaultEditorProvider(returnType, getChoiceRenderer(l, property));
        }
        return ep;
    }


	/**
	 * @param l the Lister Annotation
	 * @param property the property to access the data
	 * @return a matching ChoiceRendererProvider
	 */
	@SuppressWarnings("rawtypes")
	private static ChoiceRendererProvider getChoiceRenderer(final Lister l, final String property) {
		ChoiceRendererProvider renderer = null;
		if (l != null) {
		    if (Display.RESOURCE.equals(l.displayAs())) {
		        renderer = new ChoiceRendererProvider(l.resourcePrefix(), property);
		    }
		}
		return renderer;
	}


    @SuppressWarnings("unchecked")
    private static <T> IEditorProvider<T> getDefaultEditorProvider(Class<?> returnType, ChoiceRendererProvider<T> renderer) {
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
