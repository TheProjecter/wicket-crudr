/**
 *
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;


import net.unbewaff.wicketcrudr.datablocks.IProperty;
import net.unbewaff.wicketcrudr.datablocks.IPrototypeData;
import net.unbewaff.wicketcrudr.datablocks.IterableProperty;
import net.unbewaff.wicketcrudr.providers.label.IterableLabelProvider;

import org.apache.log4j.Logger;


/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class LabelModelProviderFactory {

    private final static transient Logger logger = Logger.getLogger(LabelModelProviderFactory.class);

    private LabelModelProviderFactory() {
        //static use only
    }

    /**
     * @param <T>
     * @param cleanProperty
     * @param d The DisplayType Annotation defining the Metadata
     * @return
     */
    public static <T> ILabelModelProvider<T> getLabelModelProvider(IterableProperty property) {
        ILabelModelProvider<T> provider;
        if (property.isUseStringResource()) {
            provider = new StringResourcePropertyModelProvider<T>(property.getProperty(), property.getStringResourcePrefix());
        } else {
            provider = new PropertyModelProvider<T>(property.getProperty());
        }
        return provider;
    }

    public static <T> ILabelModelProvider<T> getLabelModelProvider(IProperty property) {
        ILabelModelProvider<T> provider;
        if (property.isUseStringResource()) {
            logger.debug("Property " + property.getProperty() + " uses " + property.getStringResourcePrefix() + ".");
            provider = new StringResourcePropertyModelProvider<T>(property.getProperty(), property.getStringResourcePrefix());
        } else {
            logger.debug("Property " + property.getProperty() + " uses a plain PropertyModel.");
            provider = new PropertyModelProvider<T>(property.getProperty());
        }
        return provider;
    }

    public static <T> ILabelModelProvider<T> getLabelModelProvider(String property, IPrototypeData innerType) {
        ILabelModelProvider<T> labelModelProvider;

        if (innerType instanceof IterableProperty && ((IterableProperty) innerType).getInnerPrototype().getProperties().size() > 0) {
            labelModelProvider = new ConcatenatedLabelModelProvider<T>(((IterableProperty)innerType).getInnerPrototype().getProperties(), " ");
        } else {
            labelModelProvider = new StringResourceModelProvider<T>(property, innerType);
        }

        return labelModelProvider;
    }

}
