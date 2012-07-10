/**
 *
 */
package net.unbewaff.wicketcrudr.columnwrappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.unbewaff.wicketcrudr.filters.IFilterProvider;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilteredColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class FilteringColumnWrapper<T> extends AbstractColumn<T> implements IFilteredColumn<T> {

    private final AbstractColumn<T> column;
    private final IFilterProvider filterProvider;

    /**
     * @param column
     */
    private FilteringColumnWrapper(AbstractColumn<T> column, IFilterProvider filterProvider) {
        super(column.getDisplayModel());
        this.column = column;
        this.filterProvider = filterProvider;
    }

    public int hashCode() {
        return column.hashCode();
    }

    public IModel<String> getDisplayModel() {
        return column.getDisplayModel();
    }

    public String getSortProperty() {
        return column.getSortProperty();
    }

    public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
        column.populateItem(cellItem, componentId, rowModel);
    }

    public boolean isSortable() {
        return column.isSortable();
    }

    public Component getHeader(String componentId) {
        return column.getHeader(componentId);
    }

    public void detach() {
        column.detach();
    }

    public String getCssClass() {
        return column.getCssClass();
    }

    public boolean equals(Object obj) {
        return column.equals(obj);
    }

    public String toString() {
        return column.toString();
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilteredColumn#getFilter(java.lang.String, org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm)
     */
    @Override
    public Component getFilter(String componentId, FilterForm<?> form) {
        return filterProvider.getFilter(componentId, form);
    }

}
