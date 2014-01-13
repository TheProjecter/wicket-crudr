/**
 * 
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.unbewaff.wicketcrudr.datablocks.IProperty;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 * @author DavidH
 *
 */
public class ConcatenatedLabelModelProvider<T> implements ILabelModelProvider<T>, Serializable {

    private List<IProperty> properties;
    private String separator;

    /**
     * @param separator TODO
     * 
     */
    public ConcatenatedLabelModelProvider(List<IProperty> properties, String separator) {
        this.properties = properties;
        this.separator = separator;

    }

    @Override
    public IModel<?> newLabelModel(IModel<T> model) {
        return new ConcatenatedLabelModel<T>(properties, separator, model);
    }

    public static class ConcatenatedLabelModel<T> implements Serializable, IModel<String> {

        private List<PropertyModel<T>> models = new ArrayList<PropertyModel<T>>();
        private String separator;
        private IModel<T> target;


        public ConcatenatedLabelModel(List<IProperty> properties, String separator, IModel<T> target) {
            for (IProperty property: properties) {
                models.add(new PropertyModel<T>(target, property.getProperty()));
            }
            this.separator = separator;
            this.target = target;
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

        public T getTarget() {
            return target.getObject();
        }

    }
}
