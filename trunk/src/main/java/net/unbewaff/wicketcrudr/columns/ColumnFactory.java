/**
 *
 */
package net.unbewaff.wicketcrudr.columns;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.components.ContainerConfiguration;
import net.unbewaff.wicketcrudr.providers.editor.EditorProviderFactory;
import net.unbewaff.wicketcrudr.providers.editor.IEditorProvider;
import net.unbewaff.wicketcrudr.providers.editor.ISurroundingContainerProvider;
import net.unbewaff.wicketcrudr.providers.editorpanel.SurroundingContainerProviderFactory;
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
        String cleanProperty = getCleanPropertyName(property);
        return getColumn(m.getAnnotation(Lister.class), m.getAnnotation(Editor.class), cleanProperty, clazz);
    }

    public static <T> IColumn<T> getColumn(Field f, String property, Class<T> clazz) {
        return getColumn(f.getAnnotation(Lister.class), f.getAnnotation(Editor.class), property, clazz);
    }
    /**
     * @param <T>
     * @param l The Lister Annotation
     * @param e The Editor Annotation
     * @param property The property name
     * @param clazz the Class T
     * @return a Column to display and maybe edit the data from the annotated method or field
     */
    public static <T> IColumn<T> getColumn(Lister l, Editor e, String property, Class<T> clazz) {
        IColumn<T> col = null;
        IModel<String> displayModel = getHeaderModel(l.resourcePrefix(), clazz.getSimpleName(), property);
        ILabelModelProvider<T> labelModelProvider = LabelModelProviderFactory.getLabelModelProvider(property, l);
        ILabelProvider<T> labelProvider = LabelProviderFactory.getLabelProvider(l, labelModelProvider);
        if (!l.editInPlace()) {
            col = new FlexibleNonEditableColumn<T>(displayModel, labelProvider);
        } else {
            IEditorProvider<T> editorProvider = EditorProviderFactory.getEditorProvider(e);
            ISurroundingContainerProvider containerProvider = SurroundingContainerProviderFactory.getContainerProvider(e);
            ContainerConfiguration<T> conf = new ContainerConfiguration<T>(labelProvider, editorProvider, containerProvider, property);
            col = new FlexibleEditableColumn<T>(displayModel, conf);
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
            display = headerKey + "." + cleanProperty;
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


