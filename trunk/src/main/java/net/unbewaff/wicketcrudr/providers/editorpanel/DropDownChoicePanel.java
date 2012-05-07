/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editorpanel;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class DropDownChoicePanel extends Panel {

    private static final long serialVersionUID = 2347444945576482337L;

    /**
     * @param id
     */
    public DropDownChoicePanel(String id) {
        super(id);
        setOutputMarkupPlaceholderTag(true);
        setOutputMarkupId(true);
        setVisible(false);
    }

}
