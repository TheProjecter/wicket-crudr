package net.unbewaff.wicketcrudr.providers;

import org.apache.wicket.markup.html.panel.Panel;

class TextFieldPanel extends Panel {

	public TextFieldPanel(String id) {
		super(id);
		setOutputMarkupPlaceholderTag(true);
		setOutputMarkupId(true);
		setVisible(false);
	}

}