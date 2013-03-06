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
    private String property;
    private IPrototypeData innerType;

    /**
     * @param property
     * @param propertyProvider
     */
    public StringResourceModelProvider(String property, IPrototypeData innerType) {
        if (innerType == null) {
            throw new IllegalArgumentException("InnerType may not be null");
        }
        this.innerType = innerType;
        this.property = property;
    }


    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider#newLabelModel(org.apache.wicket.model.IModel)
     */
    @Override
    public IModel<?> newLabelModel(IModel<T> model) {

        IModel<?> realResourceModel = new PropertyModel<Object>(model, property);
        if (innerType.getResourceKeyProperty() != null) {
            realResourceModel = new PropertyModel<Object>(model, innerType.getResourceKeyProperty().getProperty());
        }
        ResourceModel resourceModel = new ResourceModel(property + ".null");
        if (model != null  && model.getObject() != null) {
            Object resource = realResourceModel.getObject();
            resourceModel = new ResourceModel(property + "." + resource.toString(), resource.toString());
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
            Object inner = o.getObject();
            while (inner instanceof IModel) {
                inner = ((IModel)inner).getObject();
            }
            return inner.toString();
        }

        @Override
        public void setObject(String object) {
            o = Model.of(object);
        }

    }
}
