/**
 * 
 */
package net.unbewaff.wicketcrudr.borders;

import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public abstract class StyleableBorder extends Border {

    private static final long serialVersionUID = -6899317046869278541L;

    /**
     * @param id
     */
    public StyleableBorder(String id) {
        super(id);
    }

    /**
     * @param id
     * @param model
     */
    public StyleableBorder(String id, IModel<?> model) {
        super(id, model);
    }

    /**
     * @return
     */
    public abstract IModel<String> getBorderCss();

    public abstract String getFragmentId();

}