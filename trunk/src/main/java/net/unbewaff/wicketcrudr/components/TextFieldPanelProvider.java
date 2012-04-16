package net.unbewaff.wicketcrudr.components;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;

public class TextFieldPanelProvider implements ISurroundingContainerProvider {


	public WebMarkupContainer newSurroundingPanel(MarkupContainer parent,
			String componentId) {
		
		return new TextFieldPanel(componentId);
	}

	public WebMarkupContainer show(AjaxRequestTarget target, WebMarkupContainer container) {
		if (container == null) {
			throw new IllegalStateException("Can't set a non existing Container visible.");
		}
		if (!container.isVisible()) {
			container.setVisible(true);
			target.add(container);
		}
		return container;
	}

	public WebMarkupContainer hide(AjaxRequestTarget target, WebMarkupContainer container) {
		if (container == null) {
			throw new IllegalStateException("Can't set a non existing Container invisible.");
		}
		if (container.isVisible()) {
			container.setVisible(false);
			target.add(container);
		}
		return container;
	}
	
	private class TextFieldPanel extends Panel {

		public TextFieldPanel(String id) {
			super(id);
			setOutputMarkupPlaceholderTag(true);
			setVisible(false);
		}
		
	}


}