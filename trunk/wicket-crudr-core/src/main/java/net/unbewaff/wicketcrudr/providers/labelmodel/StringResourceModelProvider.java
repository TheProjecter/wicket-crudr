/**
 *
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

/**
 * {@link ILabelModelProvider} that provides Models for Objects in Collections, accommodating for Objects
 * that don't contain beanystyle getters for their id-property.
 *
 * @author David Hendrix (Nicktarix)
 *
 */
class StringResourceModelProvider<T> implements Serializable, ILabelModelProvider<T> {

	private Method propertyProvider;
	private String resourcePrefix;
	private static final transient Logger logger = Logger.getLogger(StringResourceModelProvider.class);

    /**
     * @param propertyProvider
     * @param resourcePrefix
     */
    public StringResourceModelProvider(Method propertyProvider, String resourcePrefix) {
        this.propertyProvider = propertyProvider;
        this.resourcePrefix = resourcePrefix;
    }


	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider#newLabelModel(org.apache.wicket.model.IModel)
	 */
	@Override
	public IModel<?> newLabelModel(IModel<T> model) {

		ResourceModel resourceModel = new ResourceModel(resourcePrefix + ".null");
		if (model != null  && model.getObject() != null) {
			try {
				String resource = (String)propertyProvider.invoke(model.getObject(), (Object[]) null);
				resourceModel = new ResourceModel(resourcePrefix + "." + resource, "default");
			} catch (IllegalArgumentException e) {
				logger.error("Exception while invoking " + propertyProvider.getName() + " on " + model.getObject()
						+ " of class " + model.getObject().getClass() + ".\nMethod-signatures may not "
						+ "contain any parameters.", e);
			} catch (IllegalAccessException e) {
				logger.error("Exception while invoking " + propertyProvider.getName() + " on " + model.getObject()
						+ " of class " + model.getObject().getClass(), e);
			} catch (InvocationTargetException e) {
				logger.error("Exception while invoking " + propertyProvider.getName() + " on " + model.getObject()
						+ " of class " + model.getObject().getClass(), e);
			}
		}
		return resourceModel;
	}

}
