/**
 *
 */
package net.unbewaff.wicketcrudr.columns;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.InnerType;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Lister.InPlaceEditor;
import net.unbewaff.wicketcrudr.components.ContainerConfiguration;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;
import net.unbewaff.wicketcrudr.providers.editor.EditorProviderFactory;
import net.unbewaff.wicketcrudr.providers.editor.IEditorProvider;
import net.unbewaff.wicketcrudr.providers.editorpanel.ISurroundingContainerProvider;
import net.unbewaff.wicketcrudr.providers.editorpanel.SurroundingContainerProviderFactory;
import net.unbewaff.wicketcrudr.providers.label.ILabelProvider;
import net.unbewaff.wicketcrudr.providers.label.LabelProviderFactory;
import net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider;
import net.unbewaff.wicketcrudr.providers.labelmodel.LabelModelProviderFactory;
import net.unbewaff.wicketcrudr.tools.PositionComparator;

import org.apache.log4j.Logger;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class ColumnFactory implements Serializable {


	private static final long serialVersionUID = -3884250993203217609L;
    private static transient final Logger logger = Logger.getLogger(ColumnFactory.class);

    private ColumnFactory() {
        // static use only
    }

    public static <T extends Serializable> IColumn<T> getColumn(Method m, String property, Class<T> clazz, ICrudrListProvider<T> listProvider) {
        String cleanProperty = getCleanPropertyName(property);
        return getColumn(m.getAnnotation(Lister.class), m.getAnnotation(Editor.class), m.getAnnotation(InnerType.class), cleanProperty, clazz, m.getReturnType(), listProvider);
    }

    public static <T extends Serializable> IColumn<T> getColumn(Field f, String property, Class<T> clazz, ICrudrListProvider<T> listProvider) {
        return getColumn(f.getAnnotation(Lister.class), f.getAnnotation(Editor.class), f.getAnnotation(InnerType.class), property, clazz, f.getType(), listProvider);
    }

    /**
     * @param <T>
     * @param l The Lister Annotation
     * @param e The Editor Annotation
     * @param innerType TODO
     * @param property The property name
     * @param clazz the Class T
     * @param returnType the return type of the method
     * @param listProvider A listprovider
     * @return a Column to display and maybe edit the data from the annotated method or field
     */
    public static <T extends Serializable> IColumn<T> getColumn(Lister l, Editor e, InnerType innerType, String property, Class<T> clazz, Class<?> returnType, ICrudrListProvider<T> listProvider) {
        IColumn<T> col = null;
        IModel<String> displayModel = getHeaderModel(l.resourcePrefix(), clazz.getSimpleName(), property);
        ILabelModelProvider<T> labelModelProvider = LabelModelProviderFactory.getLabelModelProvider(property, l);
        ILabelProvider<T> labelProvider = LabelProviderFactory.getLabelProvider(l, innerType, labelModelProvider, returnType);
        InPlaceEditor editInPlace = l.editInPlace();
        if (!InPlaceEditor.NONE.equals(editInPlace) && e == null) {
            logger.error("Properties that enable inline editing must provide an Editor Annotation. " + clazz.getName() + "." + property + " doeasn't. Assuming editInPlace as false.");
            editInPlace = InPlaceEditor.NONE;
        }
        if (!InPlaceEditor.NONE.equals(editInPlace)) {
            IEditorProvider<T> editorProvider = EditorProviderFactory.getEditorProvider(e, l, returnType, property);
            ISurroundingContainerProvider containerProvider = SurroundingContainerProviderFactory.getContainerProvider(e);
            ContainerConfiguration<T> conf = new ContainerConfiguration<T>(labelProvider, editorProvider, containerProvider, listProvider, property);
            col = new FlexibleEditableColumn<T>(displayModel, conf);
        } else {
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

    public static <T extends Serializable> List<IColumn<T>> getColumns(Class<T> clazz){
    	List<IColumn<T>> columns = new ArrayList<IColumn<T>>();
    	List<Method> methods = new ArrayList<Method>();
    	for (Method m :clazz.getMethods()) {
    		Lister lister = m.getAnnotation(Lister.class);
    		if (lister != null) {
    			methods.add(m);
    		}
    	}
    	Collections.sort(methods, new PositionComparator());

    	for (Method m: methods) {
			columns.add(getColumn(m, m.getName(), clazz, null));
		}

		return columns;
    }
}


