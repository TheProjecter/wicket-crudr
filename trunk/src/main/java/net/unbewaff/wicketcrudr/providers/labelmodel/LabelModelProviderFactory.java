/**
 *
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;

import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Lister.Display;


/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class LabelModelProviderFactory {

    private LabelModelProviderFactory() {
        //static use only
    }

    /**
     * @param <T>
     * @param cleanProperty
     * @param l TODO
     * @return
     */
    public static <T> ILabelModelProvider<T> getLabelModelProvider(String cleanProperty, Lister l) {
        ILabelModelProvider<T> provider;
        if (l.displayAs().equals(Display.RESOURCE)) {
            provider = new StringResourceModelProvider<T>(cleanProperty, l.resourcePrefix());
        } else {
            provider = new PropertyModelProvider<T>(cleanProperty);
        }
        return provider;
    }

}
