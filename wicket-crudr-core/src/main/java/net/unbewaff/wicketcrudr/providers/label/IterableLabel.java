/**
 *
 */
package net.unbewaff.wicketcrudr.providers.label;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider;
import net.unbewaff.wicketcrudr.providers.labelmodel.LabelModelProviderFactory;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class IterableLabel<T extends Iterable<V>, V extends Serializable> extends Panel implements Serializable {

	private IModel<T> model;
	private ILabelModelProvider<V> labelModelProvider;

	/**
	 * @param id
	 * @param model
	 */
	public IterableLabel(String id, IModel<T> model, ILabelModelProvider<V> labelModelProvider) {
		super(id, model);
		this.model = model;
		this.labelModelProvider = labelModelProvider;
	}


	/* (non-Javadoc)
	 * @see org.apache.wicket.Component#onInitialize()
	 */
	@Override
	protected void onInitialize() {
		RepeatingView list = new RepeatingView("list");
		add(list);
		for (V item: model.getObject()) {
			list.add(new Label(list.newChildId(), labelModelProvider.newLabelModel(Model.of(item))));
		}
		super.onInitialize();
	}
}
