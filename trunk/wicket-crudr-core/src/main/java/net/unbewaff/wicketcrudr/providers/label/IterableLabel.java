/**
 *
 */
package net.unbewaff.wicketcrudr.providers.label;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;

import net.unbewaff.wicketcrudr.annotations.InnerPrototype;
import net.unbewaff.wicketcrudr.annotations.InnerPrototype.DisplayType;
import net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider;
import net.unbewaff.wicketcrudr.providers.labelmodel.ListOnTheParentModel;

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
public class IterableLabel<T extends List<V>, V extends Serializable> extends Panel implements Serializable {

	private static final long serialVersionUID = 4751845396328553541L;
	private ILabelModelProvider<V> labelModelProvider;
	private InnerPrototype innerType;

	/**
	 * @param id
	 * @param model
	 */
	public IterableLabel(String id, IModel<T> model, ILabelModelProvider<V> labelModelProvider, InnerPrototype innerType) {
		super(id, model);
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
			fragment = new ListFragment("body", this, getDefaultModel()) {

				private static final long serialVersionUID = 7409868325155349573L;

				@Override
				Label addSeparator(WebMarkupContainer container) {
					return null;
				}
			};
			break;
		case CONCATENATED:
			fragment = new ListFragment("body", this, getDefaultModel()) {

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
	
	@Override
	protected void onBeforeRender() {
		// TODO Auto-generated method stub
		super.onBeforeRender();
	}
	
	@Override
	protected void onRender() {
		// TODO Auto-generated method stub
		super.onRender();
	}
	
	@Override
	protected void onModelChanged() {
		// TODO Auto-generated method stub
		super.onModelChanged();
	}
	
	@Override
	protected void onModelChanging() {
		// TODO Auto-generated method stub
		super.onModelChanging();
	}

	private abstract class ListFragment extends Fragment implements Serializable {

		private static final long serialVersionUID = 6089762601810199229L;

		/**
		 * @param id
		 * @param markupId
		 * @param markupProvider
		 */
		public ListFragment(String id, MarkupContainer markupProvider, IModel<?> model) {
			super(id, innerType.displayAs().getFragmentId(), markupProvider, model);
		}

		@Override
		@SuppressWarnings("unchecked")
		protected void onInitialize() {
			RepeatingView list = new RepeatingView("list", getDefaultModel());
			add(list);
			Label separatorLabel = null;
			if (getDefaultModelObject() != null) {
				List<V> defaultModelObject = (List<V>)getDefaultModelObject();
				for (int i = 0; i < defaultModelObject.size(); i++) {
					WebMarkupContainer container = new WebMarkupContainer(list.newChildId());
					separatorLabel = addSeparator(container);
					ListOnTheParentModel<V> itemModel = new ListOnTheParentModel<V>(list, i);
					IModel<?> labelModel = labelModelProvider.newLabelModel(itemModel);
					Label label = new Label("text", labelModel);
					container.add(label);
					list.add(container);
				}
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
	
	private class DefaultInnerType implements InnerPrototype {
		
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
