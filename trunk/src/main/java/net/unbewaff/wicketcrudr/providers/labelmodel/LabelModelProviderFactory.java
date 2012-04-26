/**
 *
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;


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
     * @param displayKey
     * @return
     */
    public static <T> ILabelModelProvider<T> getLabelModelProvider(String cleanProperty, String displayKey) {
        ILabelModelProvider<T> provider;
        if ("".equals(displayKey)) {
            provider = new PropertyModelProvider<T>(cleanProperty);
        } else {
            provider = new StringResourceModelProvider<T>(cleanProperty, displayKey);
        }
        return provider;
    }

}
