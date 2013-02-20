/**
 *
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;


import net.unbewaff.wicketcrudr.datablocks.IPrototypeData;
import net.unbewaff.wicketcrudr.datablocks.IterableProperty;
import net.unbewaff.wicketcrudr.datablocks.Property;

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

    public static <T> ILabelModelProvider<T> getLabelModelProvider(Property property) {
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

    public static <T> ILabelModelProvider<T> getLabelModelProvider(String resourcePrefix, IPrototypeData innerType) {
        ILabelModelProvider<T> labelModelProvider;

        if (innerType.getProperties().size() > 0) {
            labelModelProvider = new ConcatenatedLabelModelProvider<T>(innerType.getProperties(), " ");
        } else {
            labelModelProvider = new StringResourceModelProvider<T>(resourcePrefix, innerType);
        }

        return labelModelProvider;
    }

}
