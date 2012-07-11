/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editorpanel;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class PasswordPanel extends Panel {

    private static final long serialVersionUID = -4665936865336538624L;
    private final PasswordTextField passwordTextField;
    private String repeatedPassword = "";

    /**
     * @param id
     */
    public PasswordPanel(String id) {
        super(id);
        setOutputMarkupPlaceholderTag(true);
        setOutputMarkupId(true);
        setVisible(false);
        passwordTextField = new PasswordTextField("repeat", Model.of(repeatedPassword));
        add(passwordTextField);
    }

    public PasswordTextField getPasswordTextField() {
        return passwordTextField;
    }

}
