package net.unbewaff.wicketcrudr.providers;

import org.apache.wicket.markup.html.panel.Panel;

class TextAreaPanel extends Panel {

	public TextAreaPanel(String id) {
		super(id);
		setOutputMarkupPlaceholderTag(true);
		setOutputMarkupId(true);
		setVisible(false);
	}

}