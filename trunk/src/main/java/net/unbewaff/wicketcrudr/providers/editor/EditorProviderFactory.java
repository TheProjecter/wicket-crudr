/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Lister.Display;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;

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
                ep = getDefaultEditorProvider(returnType);
        }
        return ep;
    }


	/**
	 * @param l
	 * @param property
	 * @return
	 */
	private static ChoiceRendererProvider getChoiceRenderer(final Lister l,
			final String property) {
		ChoiceRendererProvider renderer = null;
		if (l != null) {
		    if (Display.RESOURCE.equals(l.displayAs())) {
		        renderer = new ChoiceRendererProvider(l.resourcePrefix(), property);
		    }
		}
		return renderer;
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
