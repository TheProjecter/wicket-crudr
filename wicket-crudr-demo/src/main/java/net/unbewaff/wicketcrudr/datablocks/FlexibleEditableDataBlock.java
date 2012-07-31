/**
 *
 */
package net.unbewaff.wicketcrudr.datablocks;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.components.AjaxEditableLabelContainer;
import net.unbewaff.wicketcrudr.components.ContainerConfiguration;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
class FlexibleEditableDataBlock<T extends Serializable> implements Serializable, IDataBlock<T> {

	private static final long serialVersionUID = -5616560586558642852L;
	private final ContainerConfiguration<T> configuration;
	private final IModel<String> headerModel;

	public FlexibleEditableDataBlock(IModel<String> displayModel, ContainerConfiguration<T> conf) {
		configuration = conf;
		this.headerModel = displayModel;
	}

	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.datablocks.IDataBlock#getLabel(java.lang.String, org.apache.wicket.model.IModel)
	 */
	@Override
	public Component getLabel(String componentId, IModel<T> rowModel) {
		return new AjaxEditableLabelContainer<T>(componentId, rowModel, configuration);
	}


	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.datablocks.IDataBlock#getHeader(java.lang.String, org.apache.wicket.model.IModel)
	 */
	@Override
	public Component getHeader(String componentId, IModel<T> model) {
		return new Label(componentId, headerModel);
	}

}
