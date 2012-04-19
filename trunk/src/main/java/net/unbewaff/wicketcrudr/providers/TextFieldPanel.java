package net.unbewaff.wicketcrudr.providers;

import org.apache.wicket.markup.html.panel.Panel;

class TextFieldPanel extends Panel {

	private static final long serialVersionUID = 145787574046271066L;

	public TextFieldPanel(String id) {
		super(id);
		setOutputMarkupPlaceholderTag(true);
		setOutputMarkupId(true);
		setVisible(false);
	}

}