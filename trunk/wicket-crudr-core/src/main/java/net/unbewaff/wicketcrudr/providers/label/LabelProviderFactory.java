/**
 *
 */
package net.unbewaff.wicketcrudr.providers.label;

import net.unbewaff.wicketcrudr.datablocks.IProperty;
import net.unbewaff.wicketcrudr.datablocks.IterableProperty;
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
     * The real factory that provides matching {@link net.unbewaff.wicketcrudr.providers.label.ILabelProvider<T>} instances based on Metadata
     * @param property The Property descriptor
     * @param labelModelProvider The {@link net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider<T>}
     * @param <T>
     * @return The matching {@link net.unbewaff.wicketcrudr.providers.label.ILabelProvider<T>}
     */
    @SuppressWarnings("unchecked")
    public static <T> ILabelProvider<T> getLabelProvider(IProperty property, ILabelModelProvider<T> labelModelProvider) {
        ILabelProvider<T> provider = null;
        if (property instanceof IterableProperty) {
            IterableProperty iterableProperty = (IterableProperty)property;
            provider = new IterableLabelProvider(labelModelProvider, LabelModelProviderFactory.getLabelModelProvider(iterableProperty), iterableProperty);

        } else {
            provider = new SimpleLabelProvider<T>(labelModelProvider);
        }
        return provider;
    }
}
