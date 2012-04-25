package net.unbewaff.wicketcrudr.providers.editorpanel;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
class CheckBoxPanel extends Panel {

	private static final long serialVersionUID = -3404892593801289102L;

	public CheckBoxPanel(String id) {
		super(id);
		setOutputMarkupPlaceholderTag(true);
		setOutputMarkupId(true);
		setVisible(false);
	}

}