/**
 *
 */
package net.unbewaff.wicketcrudr.columns;

import java.io.Serializable;
import java.lang.reflect.Method;

import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.providers.label.ILabelProvider;
import net.unbewaff.wicketcrudr.providers.label.LabelProviderFactory;
import net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider;
import net.unbewaff.wicketcrudr.providers.labelmodel.LabelModelProviderFactory;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class ColumnFactory implements Serializable {

    private ColumnFactory() {
        // static use only
    }

    public static <T> IColumn<T> getColumn(Method m, String property, Class<T> clazz) {
    	return getColumn(m.getAnnotation(Lister.class), m.getAnnotation(Editor.class), property, clazz);
    }
    /**
     * @param <T>
     * @param l
     * @param e
     * @param property
     * @param clazz
     * @return
     */
    public static <T> IColumn<T> getColumn(Lister l, Editor e, String property, Class<T> clazz) {
        String cleanProperty = getCleanPropertyName(property);
        IColumn<T> col = null;
        IModel<String> displayModel = getHeaderModel(l.headerKey(), clazz.getSimpleName(), cleanProperty);
        ILabelModelProvider<T> labelModelProvider = LabelModelProviderFactory.getLabelModelProvider(cleanProperty, l.displayKey());
        if (!l.editInPlace()) {
            ILabelProvider<T> labelProvider = LabelProviderFactory.getLabelProvider(l, labelModelProvider);
            col = new FlexibleNonEditableColumn<T>(displayModel, labelProvider);
        }
        return col;
    }

    /**
     * @param headerKey
     * @param clazzName
     * @param cleanProperty
     * @return
     */
    private static IModel<String> getHeaderModel(String headerKey, String clazzName, String cleanProperty) {
        String display;
        if (!"".equals(headerKey)) {
        	display = headerKey;
        } else {
            display = clazzName + "." + cleanProperty;
        }
        return new StringResourceModel(display, Model.of(""), cleanProperty);
    }



    private static String getCleanPropertyName(String property) {
        String clean = property;
        if (clean.startsWith("get")) {
            clean = clean.substring(3);
        } else if (clean.startsWith("is")) {
            clean = clean.substring(2);
        }
        return clean;
    }
}


