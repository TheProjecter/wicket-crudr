/**
 *
 */
package net.unbewaff.wicketcrudr.columns;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;

/**
 * @author DavidH
 *
 */
public class StringResourceColumn<T> extends AbstractColumn<T> {

	private static final long serialVersionUID = 358092732279936235L;
	private String prefix;
	private String propertyExpression;
	private Component parent;

	public StringResourceColumn(IModel<String> displayModel, String sortProperty, String prefix,
			String propertyExpression, Component parent) {
		super(displayModel, sortProperty);
		this.prefix = prefix;
		this.propertyExpression = propertyExpression;
		this.parent = parent;
	}

	public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
		PropertyModel<?> model = new PropertyModel<Object>(rowModel, propertyExpression);
		String defaultValue = (String) model.getObject();
		if (defaultValue == null) {
			defaultValue = "";
		}
		StringResourceModel stringresource = new StringResourceModel(prefix, parent, model, defaultValue);
		cellItem.add(new Label(componentId, stringresource.getString()));
	}

}
