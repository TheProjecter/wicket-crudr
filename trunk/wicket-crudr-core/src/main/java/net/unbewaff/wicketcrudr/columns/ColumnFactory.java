/**
 *
 */
package net.unbewaff.wicketcrudr.columns;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.unbewaff.wicketcrudr.annotations.DisplayType;
import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.Ignore;
import net.unbewaff.wicketcrudr.annotations.InnerType;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Lister.InPlaceEditor;
import net.unbewaff.wicketcrudr.annotations.type.LabelResourcePrefix;
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
import net.unbewaff.wicketcrudr.tools.PropertyCleaner;

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

    public static <T extends Serializable> IColumn<T> getColumn(Method m, String property, Class<T> clazz, ICrudrListProvider<T> listProvider, LabelResourcePrefix labelResourcePrefix) {
        String cleanProperty = PropertyCleaner.getCleanPropertyName(property);
        return getColumn(m.getAnnotation(Lister.class), m.getAnnotation(Editor.class), m.getAnnotation(DisplayType.class), m.getAnnotation(InnerType.class), cleanProperty, clazz, m.getReturnType(), listProvider, labelResourcePrefix);
    }

    /**
     * @param <T>
     * @param l The Lister Annotation
     * @param e The Editor Annotation
     * @param d TODO
     * @param innerType The Generic Type of a list
     * @param property The property name
     * @param clazz the Class T
     * @param returnType the return type of the method
     * @param listProvider A listprovider
     * @param labelResourcePrefix The LabelResourcePrefix Annotation
     * @return a Column to display and maybe edit the data from the annotated method or field
     */
    public static <T extends Serializable> IColumn<T> getColumn(Lister l, Editor e, DisplayType d, InnerType innerType, String property, Class<T> clazz, Class<?> returnType, ICrudrListProvider<T> listProvider, LabelResourcePrefix labelResourcePrefix) {
        IColumn<T> col = null;
        IModel<String> displayModel = getHeaderModel(labelResourcePrefix, clazz.getSimpleName(), property);
        ILabelModelProvider<T> labelModelProvider = LabelModelProviderFactory.getLabelModelProvider(property, l);
        ILabelProvider<T> labelProvider = LabelProviderFactory.getLabelProvider(l, innerType, labelModelProvider, returnType);
        InPlaceEditor editInPlace = l.editInPlace();
        if (!InPlaceEditor.NONE.equals(editInPlace) && e == null) {
            logger.error("Properties that enable inline editing must provide an Editor Annotation. " + clazz.getName() + "." + property + " doeasn't. Assuming editInPlace as false.");
            editInPlace = InPlaceEditor.NONE;
        }
        if (!InPlaceEditor.NONE.equals(editInPlace)) {
            IEditorProvider<T> editorProvider = EditorProviderFactory.getEditorProvider(e, d.value(), returnType, property, l.resourcePrefix());
            ISurroundingContainerProvider containerProvider = SurroundingContainerProviderFactory.getContainerProvider(e);
            ContainerConfiguration<T> conf = new ContainerConfiguration<T>(labelProvider, editorProvider, containerProvider, listProvider, property);
            col = new FlexibleEditableColumn<T>(displayModel, conf);
        } else {
            col = new FlexibleNonEditableColumn<T>(displayModel, labelProvider);
        }

        return col;
    }
    /**
     * @param labelResourcePrefix
     * @param clazzName
     * @param cleanProperty
     * @return
     */
    private static IModel<String> getHeaderModel(LabelResourcePrefix labelResourcePrefix, String clazzName, String cleanProperty) {
        String display;
        if (labelResourcePrefix != null) {
            display = labelResourcePrefix.value() + "." + cleanProperty;
        } else {
            display = clazzName + "." + cleanProperty;
        }
        return new StringResourceModel(display, Model.of(""), cleanProperty);
    }



    public static <T extends Serializable> List<IColumn<T>> getColumns(Class<T> clazz){
    	List<IColumn<T>> columns = new ArrayList<IColumn<T>>();
    	List<Method> methods = new ArrayList<Method>();
    	for (Method m :clazz.getMethods()) {
    		String name = m.getName();
    		if (m.getAnnotation(Ignore.class) == null && (name.startsWith("get") || name.startsWith("is"))) {
    			methods.add(m);
    		}
    	}
    	Collections.sort(methods, new PositionComparator());

    	for (Method m: methods) {
			columns.add(getColumn(m, m.getName(), clazz, null, clazz.getAnnotation(LabelResourcePrefix.class)));
		}

		return columns;
    }
}


