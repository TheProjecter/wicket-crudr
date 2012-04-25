/**
 *
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 * Provides a PropertyModel
 *
 * @author David Hendrix (Nicktarix)
 *
 */
public class PropertyModelProvider<T> implements ILabelModelProvider<T> {

    private static final long serialVersionUID = -8029254068315046595L;
    private final String propertyExpression;

    /**
     * @param propertyExpression The propertyExpression
     */
    public PropertyModelProvider(String propertyExpression) {
        this.propertyExpression = propertyExpression;
    }

    @Override
    public IModel<Object> newLabelModel(IModel<T> model) {
        return new PropertyModel<Object>(model, propertyExpression);
    }

}
