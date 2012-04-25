/**
 *
 */
package net.unbewaff.wicketcrudr.columns;

import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;

/**
 * Extension to {@link PropertyColumn<T>} to provide a StringResourceModel to the Label
 *
 * @author David Hendrix (Nicktarix)
 *
 */
public class StringResourceColumn<T> extends PropertyColumn<T> {

    private static final long serialVersionUID = 740826471748724758L;
    private  String resourceKey;

    public StringResourceColumn(IModel<String> displayModel, String sortProperty, String propertyExpression, String resourceKey) {
        super(displayModel, sortProperty, propertyExpression);
    }

    public StringResourceColumn(IModel<String> displayModel, String propertyExpression, String resourceKey) {
        this(displayModel, null, propertyExpression, resourceKey);
    }

    @Override
    protected IModel<?> createLabelModel(IModel<T> rowModel) {
        IModel<String> model = new StringResourceModel(resourceKey, new PropertyModel<Object>(rowModel, getPropertyExpression()));
        return model;
    }

}
