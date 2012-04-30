/**
 *
 */
package net.unbewaff.wicketcrudr.providers.label;

import java.lang.reflect.Method;

import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider;

/**
 * Factory to create ILabelProviderObjects based on Metadata
 *
 * @author David Hendrix (Nicktarix)
 *
 */
public class LabelProviderFactory {

    private LabelProviderFactory() {
        // static use only
    }
    
    public static <T> ILabelProvider<T> getLabelProvider(Method m, ILabelModelProvider<T> labelModelProvider) {
    	return getLabelProvider(m.getAnnotation(Lister.class), labelModelProvider);
    }

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
