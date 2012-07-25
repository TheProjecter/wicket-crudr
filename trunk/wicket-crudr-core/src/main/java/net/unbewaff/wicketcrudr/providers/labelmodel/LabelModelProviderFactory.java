/**
 *
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;

import java.lang.reflect.Method;

import net.unbewaff.wicketcrudr.annotations.InnerType;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Lister.Display;
import net.unbewaff.wicketcrudr.annotations.ResourceKey;

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
    	ILabelModelProvider<T> labelModelProvider;
   		labelModelProvider = new StringResourceModelProvider<T>(resourcePrefix, innerType);

		return labelModelProvider;
    }

}
