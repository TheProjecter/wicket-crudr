/**
 *
 */
package net.unbewaff.wicketcrudr.datablocks;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.member.DisplayType;
import net.unbewaff.wicketcrudr.annotations.member.Ignore;
import net.unbewaff.wicketcrudr.annotations.member.InnerPrototype;
import net.unbewaff.wicketcrudr.annotations.member.StringResource;
import net.unbewaff.wicketcrudr.annotations.type.LabelResourcePrefix;
import net.unbewaff.wicketcrudr.annotations.type.Prototype;
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
import net.unbewaff.wicketcrudr.tools.OrderIndexComparator;
import net.unbewaff.wicketcrudr.tools.PropertyCleaner;

import org.apache.log4j.Logger;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

/**
 * @author David Hendrix (Nicktarix)
 * 
 */
public class DataBlockFactory implements Serializable {

    private static final long serialVersionUID = -3884250993203217609L;
    private static transient final Logger logger = Logger
            .getLogger(DataBlockFactory.class);

    private DataBlockFactory() {
        // static use only
    }

    public static <T extends Serializable> IDataBlock<T> getColumn(Method m,
            String property, Class<T> clazz, ICrudrListProvider<T> listProvider) {
        String cleanProperty = PropertyCleaner.getCleanPropertyName(property);
        InnerPrototype innerType = m.getAnnotation(InnerPrototype.class);
        Class<?> returnType = m.getReturnType();
        if (innerType == null) {
            if (Serializable.class.isAssignableFrom(returnType)) {
                innerType = new DefaultInnerType(
                        (Class<? extends Serializable>) returnType);
            } else {
                throw new IllegalArgumentException(
                        "Either the innerType must be defined or a Serializable returnType is expected. "
                                + returnType.getSimpleName() + " has neither.");
            }
        }
        Editor editor = m.getAnnotation(Editor.class);
        DisplayType displayType = m.getAnnotation(DisplayType.class);
        StringResource stringResource = m.getAnnotation(StringResource.class);
        return getColumn(editor, displayType, innerType, stringResource,
                cleanProperty, clazz, returnType, listProvider, clazz.getAnnotation(LabelResourcePrefix.class));
    }

    /**
     * @param e   The Editor Annotation
     * @param d   The DisplayType Annotation if present
     * @param innerType
     *            The Generic Type of a List
     * @param property
     *            The property name
     * @param clazz
     *            the Class T
     * @param returnType
     *            the return type of the method
     * @param listProvider
     *            A listprovider
     * @param lrp
     *            TODO
     * @param <T>
     * @return a Column to display and maybe edit the data from the annotated
     *         method or field
     */
    private static <T extends Serializable> IDataBlock<T> getColumn(Editor e,
            DisplayType d, InnerPrototype innerType, StringResource r,
            String property, Class<T> clazz, Class<?> returnType,
            ICrudrListProvider<T> listProvider, LabelResourcePrefix lrp) {
        IDataBlock<T> dataBlock = null;
        IModel<String> displayModel = getHeaderModel(lrp,
                clazz.getSimpleName(), property);
        ILabelModelProvider<T> labelModelProvider = LabelModelProviderFactory
                .getLabelModelProvider(property, d);
        ILabelProvider<T> labelProvider = LabelProviderFactory
                .getLabelProvider(innerType, labelModelProvider, returnType, d);
        if (false) { //TODO reimplement
            IEditorProvider<T> editorProvider = EditorProviderFactory.getEditorProvider(e, d.value(), returnType, property, r);
            ISurroundingContainerProvider containerProvider = SurroundingContainerProviderFactory.getContainerProvider(e);
            ContainerConfiguration<T> conf = new ContainerConfiguration<T>(
                    labelProvider, editorProvider, containerProvider, listProvider, property);
            dataBlock = new FlexibleEditableDataBlock<T>(displayModel, conf,property);
        } else {
            dataBlock = new FlexibleNonEditableDataBlock<T>(displayModel,labelProvider, property);
        }
        logger.debug("Column created: " + property);

        return dataBlock;
    }

    /**
     * @param lrp
     * @param clazzName
     * @param cleanProperty
     * @return
     */
    private static IModel<String> getHeaderModel(LabelResourcePrefix lrp,
            String clazzName, String cleanProperty) {
        String display;
        if (lrp != null) {
            display = lrp.value() + "." + cleanProperty;
        } else {
            display = clazzName + "." + cleanProperty;
        }
        return new StringResourceModel(display, Model.of(""), cleanProperty);
    }

    public static <T extends Serializable> List<IDataBlock<T>> getColumns(
            Class<T> clazz) {
        List<IDataBlock<T>> columns = new ArrayList<IDataBlock<T>>();
        List<Method> methods = new ArrayList<Method>();
        for (Method m :clazz.getMethods()) {
            String name = m.getName();
            if (m.getDeclaringClass().isAnnotationPresent(Prototype.class)) {
                if (!m.isAnnotationPresent(Ignore.class) && (name.startsWith("get") || name.startsWith("is"))) {
                    methods.add(m);
                }
            }
        }
        Collections.sort(methods, new OrderIndexComparator());

        for (Method m : methods) {
            columns.add(getColumn(m, m.getName(), clazz, null));
        }

        return columns;
    }

}
