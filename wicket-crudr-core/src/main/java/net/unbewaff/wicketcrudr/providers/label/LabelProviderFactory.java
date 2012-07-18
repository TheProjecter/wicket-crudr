/**
 *
 */
package net.unbewaff.wicketcrudr.providers.label;

import java.lang.reflect.Method;

import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Lister.Display;
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
     * @return The matching {@link net.unbewaff.wicketcrudr.providers.label.ILabelProvider<T>}
     */
    public static <T> ILabelProvider<T> getLabelProvider(Method m, ILabelModelProvider<T> labelModelProvider) {
    	return getLabelProvider(m.getAnnotation(Lister.class), labelModelProvider, m.getReturnType());
    }

    /**
     * The real factory that provides matching {@link net.unbewaff.wicketcrudr.providers.label.ILabelProvider<T>} instances based on Metadata
     * @param <T>
     * @param l the {@link net.unbewaff.wicketcrudr.annotations.Lister} from the current method
     * @param labelModelProvider The {@link net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider<T>}
     * @param type the type of the Object to display
     * @return The matching {@link net.unbewaff.wicketcrudr.providers.label.ILabelProvider<T>}
     */
    @SuppressWarnings("unchecked")
    public static <T> ILabelProvider<T> getLabelProvider(Lister l, ILabelModelProvider<T> labelModelProvider, Class<?> type) {
        ILabelProvider<T> provider = null;
        if (Iterable.class.isAssignableFrom(type)) {
        	String prefix = l.innerResourcePrefix();
        	if (prefix.isEmpty()) {
        		prefix = type.getName();
        	}
        	provider = new IterableLabelProvider(labelModelProvider, LabelModelProviderFactory.getLabelModelProvider(prefix, l.innerType()));
        } else {
	        Display d = l.displayAs();
	        switch (d) {
	            case DEFAULT:
	            case RESOURCE:
	                provider = new SimpleLabelProvider<T>(labelModelProvider);
	                break;
	            case CHECKBOX:
	                provider = (ILabelProvider<T>) new DisabeledCheckboxLabelProvider((ILabelModelProvider<Boolean>) labelModelProvider);
	        }
        }
        if (provider == null) {
            provider = new SimpleLabelProvider<T>(labelModelProvider);
        }

        return provider;
    }
}
