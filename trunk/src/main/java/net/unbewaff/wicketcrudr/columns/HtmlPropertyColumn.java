/**
 * 
 */
package net.unbewaff.wicketcrudr.columns;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

/**
 * @author nicktar
 *
 */
public class HtmlPropertyColumn<T> extends PropertyColumn<T> {

	public HtmlPropertyColumn(IModel<String> displayModel, String sortProperty, String propertyExpression) {
		super(displayModel, sortProperty, propertyExpression);
	}

	@Override
	public void populateItem(Item<ICellPopulator<T>> item, String componentId,
			IModel<T> rowModel) {
		Label label = new Label(componentId, createLabelModel(rowModel));
		label.setEscapeModelStrings(false);
		item.add(label);
	}
}
