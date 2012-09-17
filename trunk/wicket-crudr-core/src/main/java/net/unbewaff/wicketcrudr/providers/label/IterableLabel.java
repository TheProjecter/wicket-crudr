/**
 *
 */
package net.unbewaff.wicketcrudr.providers.label;

import java.io.Serializable;
import java.lang.annotation.Annotation;

import net.unbewaff.wicketcrudr.annotations.InnerType;
import net.unbewaff.wicketcrudr.annotations.InnerType.DisplayType;
import net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author David Hendrix (Nicktarix)
 * 
 */
public class IterableLabel<T extends Iterable<V>, V extends Serializable> extends Panel implements Serializable {

	private static final long serialVersionUID = 4751845396328553541L;
	private IModel<T> model;
	private ILabelModelProvider<V> labelModelProvider;
	private InnerType innerType;

	/**
	 * @param id
	 * @param model
	 */
	public IterableLabel(String id, IModel<T> model, ILabelModelProvider<V> labelModelProvider, InnerType innerType) {
		super(id, model);
		this.model = model;
		this.labelModelProvider = labelModelProvider;
		if (innerType != null) {
			this.innerType = innerType;
		} else {
			this.innerType = new DefaultInnerType(model.getObject().getClass());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.Component#onInitialize()
	 */
	@Override
	protected void onInitialize() {
		Fragment fragment = null;
		DisplayType displayAs = null;
		if (innerType != null) {
			displayAs = innerType.displayAs();
		} else {
			displayAs = DisplayType.LIST;
		}
		switch (displayAs) {
		case LIST:
			fragment = new ListFragment("body", this, model) {

				private static final long serialVersionUID = 7409868325155349573L;

				@Override
				Label addSeparator(WebMarkupContainer container) {
					return null;
				}
			};
			break;
		case CONCATENATED:
			fragment = new ListFragment("body", this, model) {

				private static final long serialVersionUID = 3871309355861640052L;

				@Override
				Label addSeparator(WebMarkupContainer container) {
					Label separatorLabel;
					container.add(separatorLabel = new Label("separator", innerType.separator()));
					return separatorLabel;
				}
			};
			break;
		}
		add(fragment);
		super.onInitialize();
	}

	private abstract class ListFragment extends Fragment implements Serializable {

		private static final long serialVersionUID = 6089762601810199229L;
		private IModel<T> model;

		/**
		 * @param id
		 * @param markupId
		 * @param markupProvider
		 */
		public ListFragment(String id, MarkupContainer markupProvider, IModel<T> model) {
			super(id, innerType.displayAs().getFragmentId(), markupProvider);
			this.model = model;
		}

		@Override
		protected void onInitialize() {
			RepeatingView list = new RepeatingView("list");
			add(list);
			Label separatorLabel = null;
			for (V item : model.getObject()) {
				WebMarkupContainer container = new WebMarkupContainer(list.newChildId());
				separatorLabel = addSeparator(container);
				container.add(new Label("text", labelModelProvider.newLabelModel(Model.of(item))));
				list.add(container);
			}
			if (separatorLabel != null) {
				separatorLabel.setVisible(false);
			}
			super.onInitialize();
		}

		/**
		 * @param container
		 * @return
		 */
		abstract Label addSeparator(WebMarkupContainer container);
	}
	
	private class DefaultInnerType implements InnerType {
		
		private Class<?> clazz;
		
		public DefaultInnerType(Class<?> clazz) {
			this.clazz = clazz;
		}

		/* (non-Javadoc)
		 * @see java.lang.annotation.Annotation#annotationType()
		 */
		@Override
		public Class<? extends Annotation> annotationType() {
			return null;
		}

		/* (non-Javadoc)
		 * @see net.unbewaff.wicketcrudr.annotations.InnerType#resourcePrefix()
		 */
		@Override
		public String resourcePrefix() {
			return "";
		}

		/* (non-Javadoc)
		 * @see net.unbewaff.wicketcrudr.annotations.InnerType#type()
		 */
		@Override
		public Class<?> type() {
			return clazz;
		}

		/* (non-Javadoc)
		 * @see net.unbewaff.wicketcrudr.annotations.InnerType#separator()
		 */
		@Override
		public String separator() {
			return "<br />";
		}

		/* (non-Javadoc)
		 * @see net.unbewaff.wicketcrudr.annotations.InnerType#displayAs()
		 */
		@Override
		public DisplayType displayAs() {
			return DisplayType.LIST;
		}
		
	}
}
