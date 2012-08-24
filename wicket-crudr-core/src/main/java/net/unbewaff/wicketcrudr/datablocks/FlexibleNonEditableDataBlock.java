package net.unbewaff.wicketcrudr.datablocks;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.components.ILabelFacade;
import net.unbewaff.wicketcrudr.providers.label.ILabelProvider;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

public class FlexibleNonEditableDataBlock<T extends Serializable> implements Serializable, IDataBlock<T>, ILabelFacade {

    private static final long serialVersionUID = 4158102566457762813L;
    private final ILabelProvider<T> valueProvider;
    private final IModel<String> labelProvider;

    public FlexibleNonEditableDataBlock(IModel<String> headerProvider, ILabelProvider<T> labelProvider) {
        this.valueProvider = labelProvider;
        this.labelProvider = headerProvider;
    }

    @Override
	public Component getLabel(String componentId, IModel<T> model) {
    	return new Label(componentId, labelProvider);
    }

    @Override
	public Component getValue(String componentId, IModel<T> rowModel) {
        return valueProvider.newLabel(this, componentId, rowModel);
    }

    public <C> IConverter<C> getConverter(Class<C> type) {
        return null;
    }

    public String defaultNullLabel() {
        return null;
    }

    public ILabelProvider<T> getValueProvider() {
        return valueProvider;
    }

	/* (non-Javadoc)
	 * @see org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn#isSortable()
	 */
	public boolean isSortable() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn#getCssClass()
	 */
	public String getCssClass() {
		return getCssClassForBody();
	}

	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.columns.IMultipleStyledColumn#getCssClassForHeader()
	 */
	public String getCssClassForHeader() {
		return "ui-widget-header";
	}

	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.columns.IMultipleStyledColumn#getCssClassForBody()
	 */
	public String getCssClassForBody() {
		return "ui-widget-content";
	}
}
