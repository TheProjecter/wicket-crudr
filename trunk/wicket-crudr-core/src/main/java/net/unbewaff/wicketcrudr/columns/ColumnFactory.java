/**
 *
 */
package net.unbewaff.wicketcrudr.columns;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.unbewaff.wicketcrudr.components.ContainerConfiguration;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;
import net.unbewaff.wicketcrudr.datablocks.IProperty;
import net.unbewaff.wicketcrudr.datablocks.IPrototypeData;
import net.unbewaff.wicketcrudr.datablocks.IterableProperty;
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

    /**
     * @param prototype TODO
     * @param property The property
     * @param listProvider A listprovider
     * @param <T>
     * @return a Column to display and maybe edit the data from the annotated method or field
     */
    public static <T extends Serializable> IColumn<T> getColumn(IPrototypeData prototype, IProperty property, ICrudrListProvider<T> listProvider) {
        IColumn<T> col = null;
        IModel<String> displayModel = getHeaderModel(prototype.getLabelResourcePrefix(), property.getProperty());
        //ILabelModelProvider<T> labelModelProvider = LabelModelProviderFactory.getLabelModelProvider(property.getProperty(), prototype);
        ILabelModelProvider<T> labelModelProvider = LabelModelProviderFactory.getLabelModelProvider(property);
        ILabelProvider<T> labelProvider = LabelProviderFactory.getLabelProvider(property, labelModelProvider);
        if (!property.isReadOnly()) {
            IEditorProvider<T> editorProvider = EditorProviderFactory.getEditorProvider(property.getStringResourcePrefix(), property);
            ISurroundingContainerProvider containerProvider = SurroundingContainerProviderFactory.getContainerProvider(property);
            ContainerConfiguration<T> conf = new ContainerConfiguration<T>(labelProvider, editorProvider, containerProvider, listProvider, property.getProperty());
            col = new FlexibleEditableColumn<T>(displayModel, conf);
        } else {
            col = new FlexibleNonEditableColumn<T>(displayModel, labelProvider, prototype.getCss());
        }

        return col;
    }
    /**
     * @param labelResourcePrefix
     * @param cleanProperty
     * @return
     */
    private static IModel<String> getHeaderModel(String labelResourcePrefix, String cleanProperty) {
        String display= labelResourcePrefix + cleanProperty;
        return new StringResourceModel(display, Model.of(""), cleanProperty);
    }



    @SuppressWarnings("unchecked")
    public static <T extends Serializable> List<IColumn<T>> getColumns(Class<T> clazz){
        IPrototypeData prototype = new PrototypeData(clazz);
        List<IColumn<T>> columns = new ArrayList<IColumn<T>>();

        for (IProperty p : prototype.getProperties()) {
            columns.add((IColumn<T>) getColumn(p, prototype.getLabelResourcePrefix(), prototype.getCss()));
        }

        return columns;
    }

    public static <T extends Serializable> IColumn<T>  getColumn(IProperty property, String labelResourcePrefix, String css) {
        String propertyName = property.getProperty();
        IModel<String> displayModel = new StringResourceModel(labelResourcePrefix + propertyName, Model.of(""), propertyName);
        ILabelModelProvider<T> labelModelProvider = LabelModelProviderFactory.getLabelModelProvider(property);
        ILabelProvider<T> labelProvider = null;
        if (property instanceof IterableProperty) {
            IterableProperty iterableProperty = (IterableProperty) property;
            String prefix = iterableProperty.getStringResourcePrefix();
            ILabelModelProvider<T> internalLabelModelProvider = LabelModelProviderFactory.getLabelModelProvider(prefix, iterableProperty.getInnerPrototype());
            labelProvider = new IterableLabelProvider(labelModelProvider, internalLabelModelProvider, iterableProperty);
        } else {
            labelProvider = new SimpleLabelProvider<T>(labelModelProvider);
        }
        return new FlexibleNonEditableColumn<T>(displayModel, labelProvider, css);
    }
}


