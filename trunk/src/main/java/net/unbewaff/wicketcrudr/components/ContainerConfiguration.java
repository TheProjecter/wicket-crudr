package net.unbewaff.wicketcrudr.components;

import net.unbewaff.wicketcrudr.providers.IEditorProvider;
import net.unbewaff.wicketcrudr.providers.ILabelProvider;
import net.unbewaff.wicketcrudr.providers.ISurroundingContainerProvider;

public class ContainerConfiguration<T> {

    private final ILabelProvider<T> labelProvider;
    private final IEditorProvider<T> editorProvider;
    private final ISurroundingContainerProvider containerProvider;
    private final String propertyExpression;

    /**
     * Convenience Parameter Object to encapsulate the providers needed by
     * FlexibleColumns into a single parameter
     *
     * @param labelProvider
     *            The ILabelProvider
     * @param editorProvider
     *            The IEditorProvider
     * @param containerProvider
     *            The ISurroundingContainerProvider
     */
    public ContainerConfiguration(ILabelProvider<T> labelProvider, IEditorProvider<T> editorProvider,
            ISurroundingContainerProvider containerProvider, String propertyExpression) {
        if (labelProvider == null) {
            throw new IllegalArgumentException("labelProvider may not be null!");
        }
        this.labelProvider = labelProvider;
        if (editorProvider == null) {
            throw new IllegalArgumentException("editorProvider may not be null!");
        }
        this.editorProvider = editorProvider;
        if (containerProvider == null) {
            throw new IllegalArgumentException("containerProvider may not be null!");
        }
        this.containerProvider = containerProvider;
        if (propertyExpression == null) {
            throw new IllegalArgumentException("propertyExpression may not be null!");
        }
        this.propertyExpression = propertyExpression;
    }

    /**
     * @return the ILabelProvider
     */
    public ILabelProvider<T> getLabelProvider() {
        return labelProvider;
    }

    /**
     * @return The IEditorProvider
     */
    public IEditorProvider<T> getEditorProvider() {
        return editorProvider;
    }

    /**
     * @return The ISurroundingContainerProvider
     */
    public ISurroundingContainerProvider getContainerProvider() {
        return containerProvider;
    }

    public String getPropertyExpression() {
        return propertyExpression;
    }

}
