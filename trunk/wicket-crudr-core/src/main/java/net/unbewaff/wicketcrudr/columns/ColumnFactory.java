/**
 *
 */
package net.unbewaff.wicketcrudr.columns;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.member.DisplayType;
import net.unbewaff.wicketcrudr.annotations.member.InnerPrototype;
import net.unbewaff.wicketcrudr.annotations.member.StringResource;
import net.unbewaff.wicketcrudr.annotations.type.LabelResourcePrefix;
import net.unbewaff.wicketcrudr.components.ContainerConfiguration;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;
import net.unbewaff.wicketcrudr.datablocks.IPrototypeData;
import net.unbewaff.wicketcrudr.datablocks.IterableProperty;
import net.unbewaff.wicketcrudr.datablocks.Property;
import net.unbewaff.wicketcrudr.datablocks.PrototypeData;
import net.unbewaff.wicketcrudr.providers.editor.EditorProviderFactory;
import net.unbewaff.wicketcrudr.providers.editor.IEditorProvider;
import net.unbewaff.wicketcrudr.providers.editorpanel.ISurroundingContainerProvider;
import net.unbewaff.wicketcrudr.providers.editorpanel.SurroundingContainerProviderFactory;
import net.unbewaff.wicketcrudr.providers.label.ILabelProvider;
import net.unbewaff.wicketcrudr.providers.label.IterableLabelProvider;
import net.unbewaff.wicketcrudr.providers.label.LabelProviderFactory;
import net.unbewaff.wicketcrudr.providers.label.SimpleLabelProvider;
import net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider;
import net.unbewaff.wicketcrudr.providers.labelmodel.LabelModelProviderFactory;
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

    public static <T extends Serializable> IColumn<T> getColumn(Method m, String property, Class<T> clazz, ICrudrListProvider<T> listProvider, LabelResourcePrefix labelResourcePrefix, String cssClass) {
        String cleanProperty = PropertyCleaner.getCleanPropertyName(property);
        Editor editor = m.getAnnotation(Editor.class);
        DisplayType displayType = m.getAnnotation(DisplayType.class);
        InnerPrototype innerPrototype = m.getAnnotation(InnerPrototype.class);
        StringResource resourceKey = m.getAnnotation(StringResource.class);
        return getColumn(editor, displayType, innerPrototype, resourceKey, cleanProperty, clazz, m.getReturnType(), listProvider, labelResourcePrefix, cssClass);
    }

    /**
     * @param e The Editor Annotation
     * @param d The DisplayType Annotation
     * @param innerType The Generic Type of a list
     * @param r The StringRespurce Annotation
     * @param property The property name
     * @param clazz the Class T
     * @param returnType the return type of the method
     * @param listProvider A listprovider
     * @param labelResourcePrefix The LabelResourcePrefix Annotation
     * @param cssClass TODO
     * @param <T>
     * @return a Column to display and maybe edit the data from the annotated method or field
     */
    public static <T extends Serializable> IColumn<T> getColumn(Editor e, DisplayType d, InnerPrototype innerType, StringResource r, String property, Class<T> clazz, Class<?> returnType, ICrudrListProvider<T> listProvider, LabelResourcePrefix labelResourcePrefix, String cssClass) {
        IColumn<T> col = null;
        IModel<String> displayModel = getHeaderModel(labelResourcePrefix, clazz.getSimpleName(), property);
        ILabelModelProvider<T> labelModelProvider = LabelModelProviderFactory.getLabelModelProvider(property, d);
        ILabelProvider<T> labelProvider = LabelProviderFactory.getLabelProvider(innerType, labelModelProvider, returnType, d);
        if (false) { //TODO reimplement
            IEditorProvider<T> editorProvider = EditorProviderFactory.getEditorProvider(e, d.value(), returnType, property, r);
            ISurroundingContainerProvider containerProvider = SurroundingContainerProviderFactory.getContainerProvider(e);
            ContainerConfiguration<T> conf = new ContainerConfiguration<T>(labelProvider, editorProvider, containerProvider, listProvider, property);
            col = new FlexibleEditableColumn<T>(displayModel, conf);
        } else {
            col = new FlexibleNonEditableColumn<T>(displayModel, labelProvider, cssClass);
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



    @SuppressWarnings("unchecked")
    public static <T extends Serializable> List<IColumn<T>> getColumns(Class<T> clazz){
        IPrototypeData prototype = new PrototypeData(clazz);
        List<IColumn<T>> columns = new ArrayList<IColumn<T>>();

        for (Property p : prototype.getProperties()) {
            columns.add((IColumn<T>) getColumn(p, prototype.getLabelResourcePrefix(), prototype.getCss()));
        }

        return columns;
    }

    public static <T extends Serializable> IColumn<T>  getColumn(Property property, String labelResourcePrefix, String css) {
        String propertyName = property.getProperty();
        IModel<String> displayModel = new StringResourceModel(labelResourcePrefix + propertyName, Model.of(""), propertyName);
        ILabelModelProvider<T> labelModelProvider = LabelModelProviderFactory.getLabelModelProvider(property);
        ILabelProvider<T> labelProvider = null;
        if (property instanceof IterableProperty) {
            IterableProperty iterableProperty = (IterableProperty) property;
            String prefix = iterableProperty.getStringResourcePrefix();
            ILabelModelProvider labelModelProvider2 = LabelModelProviderFactory.getLabelModelProvider(prefix, iterableProperty.getInnerPrototype());
            labelProvider = new IterableLabelProvider(labelModelProvider, labelModelProvider2, iterableProperty);
        } else {
            labelProvider = new SimpleLabelProvider<T>(labelModelProvider);
        }
        return new FlexibleNonEditableColumn<T>(displayModel, labelProvider, css);
    }
}


