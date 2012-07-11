/**
 *
 */
package net.unbewaff.wicketcrudr.providers.validator;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public abstract class InputPanel extends Panel {

    public static final String COMPONENT_ID = "field";
    private Boolean submitted = null;

    /**
     * @param id
     */
    public InputPanel(String id) {
        super(id);
        Form<?> form = new Form("form");
        add(form);
        form.add(getFormComponent(COMPONENT_ID));
        form.add(new SubmitLink("submit") {
            /* (non-Javadoc)
             * @see org.apache.wicket.markup.html.form.SubmitLink#onSubmit()
             */
            @Override
            public void onSubmit() {
                submitted = true;
                super.onSubmit();
            }

            /* (non-Javadoc)
             * @see org.apache.wicket.markup.html.form.SubmitLink#onError()
             */
            @Override
            public void onError() {
                submitted = false;
                super.onError();
            }
        });
        add(new FeedbackPanel("errors"));
    }

    public Boolean wasSubmitted() {
        return submitted;
    }

    public abstract FormComponent getFormComponent(String componentId);
}
