package net.unbewaff.wicketcrudr.columns;

import net.unbewaff.wicketcrudr.components.ILabelFacade;
import net.unbewaff.wicketcrudr.providers.label.ILabelProvider;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

public class FlexibleNonEditableColumn<T> extends AbstractColumn<T> implements ILabelFacade {
    private final ILabelProvider<T> labelProvider;

    public FlexibleNonEditableColumn(IModel<String> displayModel, String sortProperty, ILabelProvider<T> labelProvider) {
        super(displayModel, sortProperty);
        this.labelProvider = labelProvider;
    }

    public FlexibleNonEditableColumn(IModel<String> displayModel, ILabelProvider<T> labelProvider) {
        this(displayModel, null, labelProvider);
    }

    @Override
    public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
        cellItem.add(labelProvider.newLabel(this, componentId, rowModel));
    }

    @Override
    public <C> IConverter<C> getConverter(Class<C> type) {
        return null;
    }

    @Override
    public String defaultNullLabel() {
        return null;
    }

}
