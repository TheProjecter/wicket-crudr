/**
 *
 */
package net.unbewaff.wicketcrudr.datablocks;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.components.ContainerConfiguration;
import net.unbewaff.wicketcrudr.components.ILabelFacade;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
class FlexibleEditingDataBlock<T extends Serializable> implements Serializable, IDataBlock<T>, ILabelFacade {

    private static final long serialVersionUID = -5616560586558642852L;
    private final ContainerConfiguration<T> configuration;
    private final IModel<String> labelModel;
    private final String name;

    public FlexibleEditingDataBlock(IModel<String> displayModel, ContainerConfiguration<T> conf, String name) {
        configuration = conf;
        this.labelModel = displayModel;
        this.name = name;
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.datablocks.IDataBlock#getLabel(java.lang.String, org.apache.wicket.model.IModel)
     */
    @Override
    public Component getValue(String componentId, IModel<T> rowModel) {
        return configuration.getLabelProvider().newLabel(this, componentId, rowModel);
    }


    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.datablocks.IDataBlock#getHeader(java.lang.String, org.apache.wicket.model.IModel)
     */
    @Override
    public Component getLabel(String componentId, IModel<T> rowModel) {
        return new Label(componentId, labelModel);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Component getEditor(String componentId, IModel<T> rowModel) {
        FormComponent<T> editor = configuration.getEditorProvider().newEditor(null, componentId, rowModel, null);
        if (editor instanceof FormComponent) {
            editor.setLabel(labelModel);
        }
        return editor;
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
