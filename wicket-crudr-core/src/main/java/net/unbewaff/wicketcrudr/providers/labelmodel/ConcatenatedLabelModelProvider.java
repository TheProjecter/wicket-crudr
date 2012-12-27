/**
 * 
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.unbewaff.wicketcrudr.tools.PropertyCleaner;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 * @author DavidH
 *
 */
public class ConcatenatedLabelModelProvider<T> implements ILabelModelProvider<T>, Serializable {
	
	private List<String> properties = new ArrayList<String>();
	private String separator;

	/**
	 * @param separator TODO
	 * 
	 */
	public ConcatenatedLabelModelProvider(List<Method> methods, String separator) {
		for (Method m : methods) {
			properties.add(PropertyCleaner.getCleanPropertyName(m.getName()));
		}
		this.separator = separator;
		
	}

	@Override
	public IModel<?> newLabelModel(IModel<T> model) {
		return new ConcatenatedLabelModel<T>(properties, separator, model);
	}

	public static class ConcatenatedLabelModel<T> implements Serializable, IModel<String> {
		
		private List<PropertyModel<T>> models = new ArrayList<PropertyModel<T>>();
		private String separator;
		
		
		public ConcatenatedLabelModel(List<String> properties, String separator, IModel<T> model) {
			for (String property: properties) {
				models.add(new PropertyModel<T>(model, property));
			}
			this.separator = separator;
		}

		@Override
		public void detach() {

		}

		@Override
		public String getObject() {
			StringBuilder sb = new StringBuilder();
			String sep = "";
			for (PropertyModel<T> model: models) {
				sb.append(sep).append(model.getObject());
				sep = separator;
			}
			return sb.toString();
		}

		@Override
		public void setObject(String object) {
			throw new UnsupportedOperationException("SetObject isn't implemented on " + getClass().getName());
		}

	}
}
