package net.unbewaff.wicketcrudr.components;

import net.unbewaff.wicketcrudr.providers.editor.IEditorProvider;
import net.unbewaff.wicketcrudr.providers.editor.ISurroundingContainerProvider;
import net.unbewaff.wicketcrudr.providers.label.ILabelProvider;

/**
 * Convenience Parameter Object to encapsulate the providers needed by
 * FlexibleColumns into a single parameter
 * 
 * @author David Hendrix (Nicktarix)
 *
 * @param <T>
 */
public class ContainerConfiguration<T> {

    private final ILabelProvider<T> labelProvider;
    private final IEditorProvider<T> editorProvider;
    private final ISurroundingContainerProvider containerProvider;
    private final String propertyExpression;

    /**
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
