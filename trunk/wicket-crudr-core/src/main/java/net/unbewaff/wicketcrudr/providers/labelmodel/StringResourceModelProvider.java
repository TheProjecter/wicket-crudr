/**
 *
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.unbewaff.wicketcrudr.annotations.InnerType;
import net.unbewaff.wicketcrudr.annotations.ResourceKey;

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



	private static final transient Logger logger = Logger.getLogger(StringResourceModelProvider.class);
	private String resourcePrefix;
	private InnerType innerType;

    /**
     * @param resourcePrefix
     * @param propertyProvider
     */
    public StringResourceModelProvider(String resourcePrefix, InnerType innerType) {
    	this.innerType = innerType;
        this.resourcePrefix = resourcePrefix;
    }


	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider#newLabelModel(org.apache.wicket.model.IModel)
	 */
	@Override
	public IModel<?> newLabelModel(IModel<T> model) {

		ResourceModel resourceModel = new ResourceModel(resourcePrefix + ".null");
		if (model != null  && model.getObject() != null) {
			Method propertyProvider = findResourceProvider(this.innerType);
			try {
				String resource = (String)propertyProvider.invoke(model.getObject(), (Object[]) null);
				resourceModel = new ResourceModel(resourcePrefix + "." + resource, propertyProvider.invoke(model.getObject(), new Object[]{}).toString());
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

	/**
	 * @param innerType
	 * @return
	 */
	private Method findResourceProvider(InnerType innerType) {
		Method resourceProvider = null;
		Class<?> type = innerType.type();
		for (Method m: type.getMethods()) {
			if (m.getAnnotation(ResourceKey.class) != null) {
				logger.debug("Using " + type.getName() + "." + m.getName() + " as resourceProvider for " + type + ".");
				resourceProvider = m;
			} else {
				logger.debug(m.getName() + " - " + m.getAnnotation(ResourceKey.class) + " - " + m.isAccessible());
			}
		}
		if (resourceProvider == null) {
			try {
				resourceProvider = type.getMethod("toString", (Class<?>[])null);
			} catch (SecurityException e) {
				logger.error("toString() method of " + type + " can't be accessed.", e);
			} catch (NoSuchMethodException e) {
				logger.error("toString() method of " + type + " can't be found.", e);
			}
		}
		return resourceProvider;
	}
}
