/**
 *
 */
package net.unbewaff.wicketcrudr.datablocks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * @param listProvider
     *            A listprovider
     * @param property TODO
     * @param lrp TODO
     * @param clazz TODO
     * @param clazz
     *            the Class T
     * @param returnType
     *            the return type of the method
     * @param <T>
     * @return a Column to display and maybe edit the data from the annotated
     *         method or field
     */
    private static <T extends Serializable> IDataBlock<T> getColumn(ICrudrListProvider<T> listProvider, IProperty property, String lrp, Class<T> clazz) {

        String propertyName = property.getProperty();
        IDataBlock<T> dataBlock = null;
        IModel<String> displayModel = getHeaderModel(lrp, propertyName);
        //ILabelModelProvider<T> labelModelProvider = LabelModelProviderFactory.getLabelModelProvider(propertyName, null);
        ILabelModelProvider<T> labelModelProvider = LabelModelProviderFactory.getLabelModelProvider(property);
        ILabelProvider<T> labelProvider = LabelProviderFactory.getLabelProvider(property, labelModelProvider);
        if (!property.isReadOnly()) {
            IEditorProvider<T> editorProvider = EditorProviderFactory.getEditorProvider(property.getStringResourcePrefix(), property);
            ISurroundingContainerProvider containerProvider = SurroundingContainerProviderFactory.getContainerProvider(property);
            ContainerConfiguration<T> conf = new ContainerConfiguration<T>(
                    labelProvider, editorProvider, containerProvider, listProvider, propertyName);
            dataBlock = new FlexibleEditableDataBlock<T>(displayModel, conf, propertyName);
        } else {
            dataBlock = new FlexibleNonEditableDataBlock<T>(displayModel,labelProvider, propertyName);
        }
        logger.debug("Column created: " + propertyName);

        return dataBlock;
    }

    /**
     * @param lrp
     * @param clazzName
     * @param cleanProperty
     * @return
     */
    private static IModel<String> getHeaderModel(String lrp, String cleanProperty) {
        return new StringResourceModel(lrp, Model.of(""), cleanProperty);
    }

    public static <T extends Serializable> List<IDataBlock<T>> getColumns(Class<T> clazz) {
        PrototypeData pd = new PrototypeData(clazz);
        List<IDataBlock<T>> columns = new ArrayList<IDataBlock<T>>();

        for (IProperty p : pd.getProperties()) {
            columns.add(getColumn(null, p, pd.getLabelResourcePrefix(), clazz));
        }

        return columns;
    }

}
