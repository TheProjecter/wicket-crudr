/**
 *
 */
package net.unbewaff.wicketcrudr.providers.label;

import java.lang.reflect.Method;

import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.providers.labelmodel.PropertyModelProvider;

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

    @SuppressWarnings("unchecked")
    public static <T> ILabelProvider<T> getLabelProvider(Lister l) {
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
            new SimpleLabelProvider<T>(new PropertyModelProvider<T>(""));
        }

        return provider;
    }
}
