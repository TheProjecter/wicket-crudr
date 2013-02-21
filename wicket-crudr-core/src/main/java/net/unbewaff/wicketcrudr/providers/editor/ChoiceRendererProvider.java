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

    private String property;
    private String stringResourcePrefix;

    /**
     * @param resourcePrefix
     */
    public ChoiceRendererProvider(String resourcePrefix, String property) {
        this.stringResourcePrefix = resourcePrefix;
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
                    stringResourceModel = new StringResourceModel(stringResourcePrefix + ".${" + property + "}", parent, new Model((Serializable)object));
                } else {
                    PropertyModel<String> propertyModel = new PropertyModel<String>(object, property);

                    stringResourceModel = new StringResourceModel(stringResourcePrefix + "." + propertyModel.getObject(), Model.of(""));
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
