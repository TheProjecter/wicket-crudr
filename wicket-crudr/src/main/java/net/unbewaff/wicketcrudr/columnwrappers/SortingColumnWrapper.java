/**
 *
 */
package net.unbewaff.wicketcrudr.columnwrappers;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class SortingColumnWrapper<T> extends AbstractColumn<T> implements ISortableDataProvider<T> {

    private ISortableDataProvider<T> dataProvider;
    private final AbstractColumn<T> column;

    /**
     * @param displayModel
     * @param dataProvider
     * @param column
     */
    private SortingColumnWrapper(ISortableDataProvider<T> dataProvider, AbstractColumn<T> column) {
        super(column.getDisplayModel());
        this.dataProvider = dataProvider;
        this.column = column;
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#iterator(int, int)
     */
    @Override
    public Iterator<? extends T> iterator(int first, int count) {
        return dataProvider.iterator(first, count);
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#size()
     */
    @Override
    public int size() {
        return dataProvider.size();
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#model(java.lang.Object)
     */
    @Override
    public IModel<T> model(T object) {
        return dataProvider.model(object);
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator#getSortState()
     */
    @Override
    public ISortState getSortState() {
        return dataProvider.getSortState();
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator#populateItem(org.apache.wicket.markup.repeater.Item, java.lang.String, org.apache.wicket.model.IModel)
     */
    @Override
    public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
        column.populateItem(cellItem, componentId, rowModel);
    }

}
