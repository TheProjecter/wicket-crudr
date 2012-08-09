/**
 * 
 */
package net.unbewaff.wicketcrudr;

import java.io.Serializable;
import java.util.List;

import net.unbewaff.wicketcrudr.components.ICrudrListProvider;
import net.unbewaff.wicketcrudr.datablocks.DataBlockFactory;
import net.unbewaff.wicketcrudr.datablocks.IDataBlock;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;

/**
 * @author Nicktar
 *
 */
public class AutoDisplay<T extends Serializable> extends Panel implements Serializable {

	private IModel<T> model;
	private final Class<T> clazz;

	/**
	 * @param id
	 * @param model
	 */
	public AutoDisplay(String id, IModel<T> model, Class<T> clazz) {
		super(id, model);
		this.model = model;
		this.clazz = clazz;
	}

	@Override
	protected void onInitialize() {
		this.setVisible(model.getObject() != null);
		final RepeatingView view = new RepeatingView("view");
		List<IDataBlock<T>> list = DataBlockFactory.getColumns(clazz);
		for (IDataBlock<T> block: list) {
			WebMarkupContainer innerWmc = new WebMarkupContainer(view.newChildId());
			view.add(innerWmc);
			innerWmc.add(block.getHeader("header", model));
			Component label = block.getLabel("label", model);
			label.setOutputMarkupId(true);
			innerWmc.add(label);
		}
		add(view);
		setOutputMarkupId(true);
		setOutputMarkupPlaceholderTag(true);
		super.onInitialize();
	}
}
