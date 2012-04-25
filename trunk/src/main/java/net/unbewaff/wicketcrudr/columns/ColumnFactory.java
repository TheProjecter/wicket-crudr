/**
 *
 */
package net.unbewaff.wicketcrudr.columns;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.Lister;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
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

    public static <T> IColumn<T> getColumn(Lister l, Editor e, String property, Class<T> clazz) {
        String cleanProperty = getCleanPropertyName(property);
        IColumn<T> col = null;
        IModel<String> displayModel = getHeaderModel(l.headerKey(), clazz.getSimpleName(), cleanProperty);
        if (!l.editInPlace()) {
            col = new PropertyColumn<T>(displayModel, cleanProperty);
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


