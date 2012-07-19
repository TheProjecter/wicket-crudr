/**
 *
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
class ConcatenatedCollectionLabelModelProvider<T extends Collection<V>, V> implements ILabelModelProvider<T>, Serializable {

	private final String separator;
	private final String resourcePrefix;
	private final Method propertyProvider;
	private static final transient Logger logger = Logger.getLogger(ConcatenatedCollectionLabelModelProvider.class);

	public ConcatenatedCollectionLabelModelProvider(String resourcePrefix, Method propertyProvider, String separator) {
		this.separator = separator;
		this.resourcePrefix = resourcePrefix;
		this.propertyProvider = propertyProvider;
	}

	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider#newLabelModel(org.apache.wicket.model.IModel)
	 */
	@Override
	public IModel<?> newLabelModel(IModel<T> model) {
		StringBuilder sb = new StringBuilder();
		String sep = "";
		for (V item: model.getObject()) {
			try {
				String resource = (String)propertyProvider.invoke(item, (Object[]) null);
				sb.append(sep);
				sep = separator;
				sb.append(new ResourceModel(resourcePrefix + "." + resource, "default").getObject());
			} catch (IllegalArgumentException e) {
				logger.error("Exception while invoking " + propertyProvider.getName() + " on " + item
						+ " of class " + item.getClass() + ".\nMethod-signatures may not "
						+ "contain any parameters.", e);
			} catch (IllegalAccessException e) {
				logger.error("Exception while invoking " + propertyProvider.getName() + " on " + item
						+ " of class " + item.getClass(), e);
			} catch (InvocationTargetException e) {
				logger.error("Exception while invoking " + propertyProvider.getName() + " on " + item
						+ " of class " + item.getClass(), e);
			}
		}
		return Model.of(sb.toString());
	}

}
