/**
 *
 */
package net.unbewaff.wicketcrudr.columnwrappers;

import net.unbewaff.wicketcrudr.components.ILabelFacade;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class LabelFassadeWrapper<T> extends AbstractColumn<T> implements ILabelFacade {

    private AbstractColumn<T> wrappedObject;

    /**
     * @param displayModel
     * @param wrappedObject
     */
    private LabelFassadeWrapper(AbstractColumn<T> wrappedObject) {
        super(wrappedObject.getDisplayModel());
        this.wrappedObject = wrappedObject;
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.components.ILabelFacade#getConverter(java.lang.Class)
     */
    @Override
    public <T> IConverter<T> getConverter(Class<T> type) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.components.ILabelFacade#defaultNullLabel()
     */
    @Override
    public String defaultNullLabel() {
        // TODO Auto-generated method stub
        return null;
    }

    public int hashCode() {
        return wrappedObject.hashCode();
    }

    public IModel<String> getDisplayModel() {
        return wrappedObject.getDisplayModel();
    }

    public String getSortProperty() {
        return wrappedObject.getSortProperty();
    }

    public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
        wrappedObject.populateItem(cellItem, componentId, rowModel);
    }

    public boolean isSortable() {
        return wrappedObject.isSortable();
    }

    public Component getHeader(String componentId) {
        return wrappedObject.getHeader(componentId);
    }

    public void detach() {
        wrappedObject.detach();
    }

    public String getCssClass() {
        return wrappedObject.getCssClass();
    }

    public boolean equals(Object obj) {
        return wrappedObject.equals(obj);
    }

    public String toString() {
        return wrappedObject.toString();
    }

}
