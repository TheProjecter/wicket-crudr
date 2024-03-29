/**
 *
 */
package net.unbewaff.wicketcrudr.columns;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.components.AjaxEditableLabelContainer;
import net.unbewaff.wicketcrudr.components.ContainerConfiguration;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
class FlexibleEditableColumn<T extends Serializable> extends AbstractColumn<T> {

    private static final long serialVersionUID = -5616560586558642852L;
    private final ContainerConfiguration<T> configuration;

    public FlexibleEditableColumn(IModel<String> displayModel, String sortProperty, ContainerConfiguration<T> conf) {
        super(displayModel, sortProperty);
        configuration = conf;
    }

    public FlexibleEditableColumn(IModel<String> displayModel, ContainerConfiguration<T> conf) {
        this(displayModel, null, conf);
    }

    @Override
    public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
        cellItem.add(new AjaxEditableLabelContainer<T>(componentId, rowModel, configuration));
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn#isSortable()
     */
    @Override
    public boolean isSortable() {
        return false;
    }

}
