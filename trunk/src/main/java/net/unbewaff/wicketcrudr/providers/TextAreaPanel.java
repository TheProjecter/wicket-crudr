package net.unbewaff.wicketcrudr.providers;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
class TextAreaPanel extends Panel {

	private static final long serialVersionUID = -7181791676672891663L;

	/**
	 * @param id
	 */
	public TextAreaPanel(String id) {
		super(id);
		setOutputMarkupPlaceholderTag(true);
		setOutputMarkupId(true);
		setVisible(false);
	}

}