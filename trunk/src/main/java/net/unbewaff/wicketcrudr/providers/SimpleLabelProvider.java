package net.unbewaff.wicketcrudr.providers;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 * Base Implementation to create a simple {@link Label} to display the data.
 *
 * @author David Hendrix (Nicktarix)
 *
 * @param <T>
 */
public class SimpleLabelProvider<T> implements ILabelProvider<T> {

    private static final long serialVersionUID = -7292107981087842284L;

    public Component newLabel(String componentId, IModel<T> model) {
        Label label = new Label(componentId, model) {

            private static final long serialVersionUID = 7754015393510391867L;

            /**
             * {@inheritDoc}
             */
            @Override
            public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
                Object modelObject = getDefaultModelObject();
                if ((modelObject == null) || "".equals(modelObject)) {
                    replaceComponentTagBody(markupStream, openTag, defaultNullLabel());
                } else {
                    super.onComponentTagBody(markupStream, openTag);
                }
            }
        };
        label.setOutputMarkupId(true);

        return label;
    }

    /**
     * Override this to display a different value when the model object is null.
     * Default is <code>...</code>
     *
     * @return The string which should be displayed when the model object is
     *         null.
     */
    protected String defaultNullLabel() {
        return "...";
    }

}
