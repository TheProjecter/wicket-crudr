/**
 *
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import net.unbewaff.wicketcrudr.annotations.InnerType;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Lister.Display;
import net.unbewaff.wicketcrudr.annotations.ResourceKey;


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
     * @param l The Lister Annotation defining the Metadata
     * @return
     */
    public static <T> ILabelModelProvider<T> getLabelModelProvider(String cleanProperty, Lister l) {
        ILabelModelProvider<T> provider;
        if (l.displayAs().equals(Display.RESOURCE)) {
            provider = new StringResourcePropertyModelProvider<T>(cleanProperty, l.resourcePrefix());
        } else {
            provider = new PropertyModelProvider<T>(cleanProperty);
        }
        return provider;
    }

    public static <T> ILabelModelProvider<T> getLabelModelProvider(String resourcePrefix, InnerType innerType) {
    	Method resourceProvider = null;
    	Class<?> type = innerType.type();
    	for (Method m: type.getMethods()) {
    		if (m.getAnnotation(ResourceKey.class) != null) {
    			logger.debug("Using " + type.getName() + "." + m.getName() + " as resourceProvider for " + type + ".");
    			resourceProvider = m;
    		} else {
    			logger.debug(m.getName() + " - " + m.getAnnotation(ResourceKey.class) + " - " + m.isAccessible());
    		}
    	}
    	if (resourceProvider == null) {
    		try {
				resourceProvider = type.getMethod("toString", (Class<?>[])null);
			} catch (SecurityException e) {
				logger.error("toString() method of " + type + " can't be accessed.", e);
			} catch (NoSuchMethodException e) {
				logger.error("toString() method of " + type + " can't be found.", e);
			}
    	}
    	ILabelModelProvider<T> labelModelProvider;
    	switch (innerType.displayAs()) {
    	case LIST:
    		labelModelProvider = new StringResourceModelProvider<T>(resourceProvider, resourcePrefix);
    		break;
    	case CONCATENATED:
    	default:
    		labelModelProvider = new ConcatenatedCollectionLabelModelProvider(resourcePrefix, resourceProvider, innerType.separator());
    		break;
    	}

		return labelModelProvider;
    }

}
