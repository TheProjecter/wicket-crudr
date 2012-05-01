/**
 *
 */
package net.unbewaff.wicketcrudr.providers.label;

import java.lang.reflect.Method;

import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider;

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
    	return getLabelProvider(m.getAnnotation(Lister.class), labelModelProvider);
    }

    /**
     * The real factory that provides matching {@link net.unbewaff.wicketcrudr.providers.label.ILabelProvider<T>} instances based on Metadata
     * @param <T>
     * @param l the {@link net.unbewaff.wicketcrudr.annotations.Lister} from the current method
     * @param labelModelProvider The {@link net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider<T>}
     * @return The matching {@link net.unbewaff.wicketcrudr.providers.label.ILabelProvider<T>}
     */
    @SuppressWarnings("unchecked")
    public static <T> ILabelProvider<T> getLabelProvider(Lister l, ILabelModelProvider<T> labelModelProvider) {
        ILabelProvider<T> provider = null;
        Class<? extends ILabelProvider<T>> lpc = (Class<? extends ILabelProvider<T>>) l.customLabelProvider();
        if (!ILabelProvider.class.equals(lpc)) {
            try {
                provider = lpc.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (provider == null) {
            provider = new SimpleLabelProvider<T>(labelModelProvider);
        }

        return provider;
    }
}
