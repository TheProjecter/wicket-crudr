package net.unbewaff.wicketcrudr.columns;

import net.unbewaff.wicketcrudr.components.ILabelFacade;
import net.unbewaff.wicketcrudr.datablocks.IMultipleStyledColumn;
import net.unbewaff.wicketcrudr.providers.label.ILabelProvider;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

public class FlexibleNonEditableColumn<T> extends AbstractColumn<T> implements ILabelFacade, IMultipleStyledColumn<T> {

    private static final long serialVersionUID = 4158102566457762813L;
    private final ILabelProvider<T> labelProvider;
    private String cssClass;

    public FlexibleNonEditableColumn(IModel<String> displayModel, String sortProperty, ILabelProvider<T> labelProvider, String cssClass) {
        super(displayModel, sortProperty);
        this.labelProvider = labelProvider;
        this.cssClass = " " + cssClass;
    }

    public FlexibleNonEditableColumn(IModel<String> displayModel, ILabelProvider<T> labelProvider, String cssClass) {
        this(displayModel, null, labelProvider, cssClass);
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

    public ILabelProvider<T> getLabelProvider() {
        return labelProvider;
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn#isSortable()
     */
    @Override
    public boolean isSortable() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn#getCssClass()
     */
    @Override
    public String getCssClass() {
        return getCssClassForBody();
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.columns.IMultipleStyledColumn#getCssClassForHeader()
     */
    @Override
    public String getCssClassForHeader() {
        return "ui-widget-header" + cssClass;
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.columns.IMultipleStyledColumn#getCssClassForBody()
     */
    @Override
    public String getCssClassForBody() {
        return "ui-widget-content" + cssClass;
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.columns.IMultipleStyledColumn#getCssClassForFooter()
     */
    @Override
    public String getCssClassForFooter() {
        return "ui-widget-content" + cssClass;
    }

}
