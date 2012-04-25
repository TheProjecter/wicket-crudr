/**
 *
 */
package net.unbewaff.wicketcrudr.columns;

import net.unbewaff.wicketcrudr.dataproviders.IManageableDataProvider;
import net.unbewaff.wicketcrudr.events.AjaxEventPayload;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

/**
 * Column to provide Links to delete or reorder Listitems
 *
 * @author David Hendrix (Nicktarix)
 *
 */
class ListManagemenColumn<T> extends AbstractColumn<T> {

    IManageableDataProvider<T> provider;

    /**
     * @param displayModel
     * @param provider
     */
    public ListManagemenColumn(IModel<String> displayModel, IManageableDataProvider<T> provider) {
        super(displayModel);
        this.provider = provider;
    }

    /**
     * @param displayModel
     * @param sortProperty
     * @param provider
     */
    public ListManagemenColumn(IModel<String> displayModel, String sortProperty, IManageableDataProvider<T> provider) {
        super(displayModel, sortProperty);
        this.provider = provider;
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator#populateItem(org.apache.wicket.markup.repeater.Item, java.lang.String, org.apache.wicket.model.IModel)
     */
    @Override
    public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, final IModel<T> rowModel) {

        cellItem.add(new LinkPanel<T>(componentId, rowModel, provider));
    }

}
