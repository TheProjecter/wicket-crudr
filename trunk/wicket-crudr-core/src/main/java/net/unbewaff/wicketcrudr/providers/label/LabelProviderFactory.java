/**
 *
 */
package net.unbewaff.wicketcrudr.providers.label;

import net.unbewaff.wicketcrudr.annotations.member.InnerPrototype.DisplayType;
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
     * @param innerType TODO
     * @param labelModelProvider The {@link net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider<T>}
     * @param type the type of the Object to display
     * @param dt d The DisplayType Annotation if present
     * @param <T>
     * @return The matching {@link net.unbewaff.wicketcrudr.providers.label.ILabelProvider<T>}
     */
    @SuppressWarnings("unchecked")
    public static <T> ILabelProvider<T> getLabelProvider(IterableProperty innerType, ILabelModelProvider<T> labelModelProvider, Class<?> type, DisplayType dt) {
        ILabelProvider<T> provider = null;
        if (type != null && Iterable.class.isAssignableFrom(type)) {

            String prefix = innerType.getStringResourcePrefix();
            if (prefix.isEmpty()) {
                prefix = type.getName();
            }
            provider = new IterableLabelProvider(labelModelProvider, LabelModelProviderFactory.getLabelModelProvider(innerType), innerType);

        } else {
            provider = new SimpleLabelProvider<T>(labelModelProvider);
        }
        return provider;
    }
}
