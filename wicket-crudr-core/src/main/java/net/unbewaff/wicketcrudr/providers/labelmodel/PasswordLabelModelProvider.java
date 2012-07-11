/**
 *
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class PasswordLabelModelProvider implements ILabelModelProvider<String> {

    private static final String PASSWORT_STARS = "********";
    private static final long serialVersionUID = -6946782296934942827L;

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider#newLabelModel(org.apache.wicket.model.IModel)
     */
    @Override
    public IModel<?> newLabelModel(IModel<String> model) {
        return Model.of(PASSWORT_STARS);
    }

}
