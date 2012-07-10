/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class ChoiceRendererProvider<T> {

    private String resourcePrefix;
    private String property;

    /**
     * @param resourcePrefix
     */
    public ChoiceRendererProvider(String resourcePrefix, String property) {
        this.resourcePrefix = resourcePrefix;
        this.property = property;
    }

    /**
     * @param <T>
     * @param l
     * @param property
     * @return
     */
    public IChoiceRenderer<T> getRenderer(final Component parent) {
        return new IChoiceRenderer<T>() {

            private static final long serialVersionUID = 6502141892434122186L;

            @Override
            public Object getDisplayValue(T object) {
                StringResourceModel stringResourceModel;
                if (object instanceof Serializable && parent != null) {
                    stringResourceModel = new StringResourceModel(resourcePrefix + ".${" + property + "}", parent, new Model((Serializable)object));
                } else {
                    PropertyModel<String> propertyModel = new PropertyModel<String>(object, property);

                    stringResourceModel = new StringResourceModel(resourcePrefix + "." + propertyModel.getObject(), Model.of(""));
                }
                return stringResourceModel.getString();
            }

            @Override
            public String getIdValue(T object, int index) {
                return String.valueOf(index);
            }
        };
    }

}
