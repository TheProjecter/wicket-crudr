/**
 *
 */
package net.unbewaff.wicketcrudr.columns;

import net.unbewaff.wicketcrudr.dataproviders.IManageableDataProvider;
import net.unbewaff.wicketcrudr.events.AjaxEventPayload;
import net.unbewaff.wicketcrudr.events.ListChangedEventPayLoad;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

/**
 * Column to provide Links to delete or reorder Listitems
 *
 * @author David Hendrix (Nicktarix)
 *
 */
public class ListManagemenColumn<T> extends AbstractColumn<T> {

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

        cellItem.add(new Panel(componentId, rowModel) {

            @Override
            protected void onInitialize() {
                add(new AjaxFallbackLink<T>("delete", rowModel) {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        provider.delete(getModelObject());
                        send(getPage(), Broadcast.DEPTH, new ListChangedEventPayLoad<T>(target, provider));
                    }
                });
                add(new AjaxFallbackLink<T>("first", rowModel) {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        provider.first(getModelObject());
                        send(getPage(), Broadcast.DEPTH, new ListChangedEventPayLoad<T>(target, provider));
                    }
                });
                add(new AjaxFallbackLink<T>("up", rowModel) {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        provider.up(getModelObject());
                        send(getPage(), Broadcast.DEPTH, new ListChangedEventPayLoad<T>(target, provider));
                    }
                });
                add(new AjaxFallbackLink<T>("down", rowModel) {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        provider.down(getModelObject());
                        send(getPage(), Broadcast.DEPTH, new ListChangedEventPayLoad<T>(target, provider));
                    }
                });

                super.onInitialize();
            }
        });
    }

}
