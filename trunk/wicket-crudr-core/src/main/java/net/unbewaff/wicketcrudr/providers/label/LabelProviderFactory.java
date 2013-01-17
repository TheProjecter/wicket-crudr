/**
 *
 */
package net.unbewaff.wicketcrudr.providers.label;

import java.lang.reflect.Method;

import net.unbewaff.wicketcrudr.annotations.member.DisplayType;
import net.unbewaff.wicketcrudr.annotations.member.InnerPrototype;
import net.unbewaff.wicketcrudr.annotations.member.DisplayType.Display;
import net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider;
import net.unbewaff.wicketcrudr.providers.labelmodel.LabelModelProviderFactory;

/**
 * Factory to create {@link net.unbewaff.wicketcrudr.providers.label.ILabelProvider<T>}Objects based on Metadata
 *
 * @author David Hendrix (Nicktarix)
 *
 */
public class LabelProviderFactory {

    private LabelProviderFactory() {
        // static use only
    }

    /**
     * Wrapper to {@link net.unbewaff.wicketcrudr.providers.label.LabelProviderFactory.getLabelProvider(Lister, ILabelModelProvider<T>)}
     * to simplify API usage by providing a Method instead of multiple Annotations
     *
     * @param <T>
     * @param m The annotated Method
     * @param labelModelProvider The {@link net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider<T>}
     * @param d The DisplayType Annotation if present
     * @return The matching {@link net.unbewaff.wicketcrudr.providers.label.ILabelProvider<T>}
     */
    public static <T> ILabelProvider<T> getLabelProvider(Method m, ILabelModelProvider<T> labelModelProvider, DisplayType d) {
    	return getLabelProvider(m.getAnnotation(InnerPrototype.class), labelModelProvider, m.getReturnType(), d);
    }

    /**
     * The real factory that provides matching {@link net.unbewaff.wicketcrudr.providers.label.ILabelProvider<T>} instances based on Metadata
     * @param innerType TODO
     * @param labelModelProvider The {@link net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider<T>}
     * @param type the type of the Object to display
     * @param dt d The DisplayType Annotation if present
     * @param <T>
     * @return The matching {@link net.unbewaff.wicketcrudr.providers.label.ILabelProvider<T>}
     */
    @SuppressWarnings("unchecked")
    public static <T> ILabelProvider<T> getLabelProvider(InnerPrototype innerType, ILabelModelProvider<T> labelModelProvider, Class<?> type, DisplayType dt) {
        ILabelProvider<T> provider = null;
        if (type != null && Iterable.class.isAssignableFrom(type)) {
        	
        	String prefix = "";
        	if (innerType != null) {
        		prefix = innerType.resourcePrefix();
        	}
        	
        	if (prefix.isEmpty()) {
        		prefix = type.getName();
        	}
       		provider = new IterableLabelProvider(labelModelProvider, LabelModelProviderFactory.getLabelModelProvider(prefix, innerType), innerType);

        } else {
        	DisplayType.Display d = dt != null ? dt.value(): Display.DEFAULT;
	        switch (d) {
	            case DEFAULT:
	            case RESOURCE:
	                provider = new SimpleLabelProvider<T>(labelModelProvider);
	                break;
	            case CHECKBOX:
	                provider = (ILabelProvider<T>) new DisabeledCheckboxLabelProvider((ILabelModelProvider<Boolean>) labelModelProvider);
	                break;
	        }
        }
        if (provider == null) {
            provider = new SimpleLabelProvider<T>(labelModelProvider);
        }

        return provider;
    }
}
