/**
 *
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
class StringResourceModelProvider<T> implements ILabelModelProvider<T> {

    private final String propertyExpression;
    private final String resourceKey;

    /**
     * @param propertyExpression
     * @param resourceKey
     */
    public StringResourceModelProvider(String propertyExpression, String resourceKey) {
        this.propertyExpression = propertyExpression;
        this.resourceKey = resourceKey;
    }

    @Override
    public IModel<?> newLabelModel(IModel<T> model) {
        return new StringResourceModel(resourceKey + ".${" + propertyExpression + "}", model);
    }

}
