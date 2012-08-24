/**
 * 
 */
package net.unbewaff.wicketcrudr;

import java.io.Serializable;
import java.util.List;

import net.unbewaff.wicketcrudr.borders.StyleableBorder;
import net.unbewaff.wicketcrudr.borders.TableBorder;
import net.unbewaff.wicketcrudr.datablocks.DataBlockFactory;
import net.unbewaff.wicketcrudr.datablocks.IDataBlock;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

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
		StyleableBorder border = new TableBorder("border", getBorderCss());
		WebMarkupContainer webMarkupContainer = new WebMarkupContainer("table");
		webMarkupContainer.add(new AttributeAppender("class", getBorderCss()));
		border.addToBorder(webMarkupContainer);

		final RepeatingView view = new RepeatingView("view");
		List<IDataBlock<T>> list = DataBlockFactory.getColumns(clazz);
		for (IDataBlock<T> block: list) {
			Fragment fragment = new DisplayFragment(view.newChildId(), border.getFragmentId(), border);
			WebMarkupContainer container = new WebMarkupContainer("fragmentContainer");
			fragment.add(container);
			view.add(fragment);
			Component label = block.getLabel("label", model).add(new AttributeAppender("class", Model.of(getLabelCss())));
			Component value = block.getValue("value", model).add(new AttributeAppender("class", Model.of(getValueCss())));
			container.add(label);
			container.add(value);
		}
		border.add(view);
		add(border);
		setOutputMarkupId(true);
		setOutputMarkupPlaceholderTag(true);
		super.onInitialize();
	}


	/**
	 * @return
	 */
	protected String getValueCss() {
		return "ui-widget-content";
	}

	/**
	 * @return
	 */
	protected String getLabelCss() {
		return "ui-widget-header";
	}
	
	protected IModel<String> getBorderCss() {
		return Model.of("autoDisplaytable");
	}
	
	protected static class DisplayFragment extends Fragment implements Serializable {

		/**
		 * @param id
		 * @param markupId
		 * @param markupProvider
		 */
		public DisplayFragment(String id, String markupId, MarkupContainer markupProvider) {
			super(id, markupId, markupProvider);
		}
	}
}
