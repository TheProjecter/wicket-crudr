/**
 *
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.datablocks.IPrototypeData;

import org.apache.log4j.Logger;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

/**
 * {@link ILabelModelProvider} that provides Models for Objects in Collections, accommodating for Objects
 * that don't contain beanystyle getters for their id-property.
 *
 * @author David Hendrix (Nicktarix)
 *
 */
class StringResourceModelProvider<T> implements Serializable, ILabelModelProvider<T> {



    private static final transient Logger logger = Logger.getLogger(StringResourceModelProvider.class);
    private String resourcePrefix;
    private IPrototypeData innerType;

    /**
     * @param resourcePrefix
     * @param propertyProvider
     */
    public StringResourceModelProvider(String resourcePrefix, IPrototypeData innerType) {
        if (innerType == null) {
            throw new IllegalArgumentException("InnerType may not be null");
        }
        this.innerType = innerType;
        this.resourcePrefix = resourcePrefix;
    }


    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider#newLabelModel(org.apache.wicket.model.IModel)
     */
    @Override
    public IModel<?> newLabelModel(IModel<T> model) {

        IModel<String> toStringModel = new ToStringModel(model);
        IModel<String> realResourceModel = toStringModel;
        if (innerType.getResourceKeyProperty() != null) {
            realResourceModel = new PropertyModel<String>(model, innerType.getResourceKeyProperty().getProperty());
        }
        ResourceModel resourceModel = new ResourceModel(resourcePrefix + ".null");
        if (model != null  && model.getObject() != null) {
            String resource = realResourceModel.getObject();
            resourceModel = new ResourceModel(resourcePrefix + "." + resource, toStringModel.getObject());
        }
        return resourceModel;
    }


    private class ToStringModel implements IModel<String> {

        private static final long serialVersionUID = -2426469060493949020L;
        private IModel<?> o;

        public ToStringModel(IModel<?> o) {
            this.o = o;
        }

        @Override
        public void detach() {
            // Do nothing
        }

        @Override
        public String getObject() {
            return o.getObject().toString();
        }

        @Override
        public void setObject(String object) {
            o = Model.of(object);
        }

    }
}
