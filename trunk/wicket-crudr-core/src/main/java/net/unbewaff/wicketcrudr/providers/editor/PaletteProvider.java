package net.unbewaff.wicketcrudr.providers.editor;


import java.io.Serializable;
import java.util.Collection;

import net.unbewaff.wicketcrudr.components.ICrudrListProvider;
import net.unbewaff.wicketcrudr.components.IEditorFacade;
import net.unbewaff.wicketcrudr.providers.editorpanel.PalettePanel;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;

/**
 * A simple implementation creating a Palette.
 *
 * @author David Hendrix (Nicktarix)
 *
 * @param <T>
 */
public class PaletteProvider<T extends Serializable> implements IEditorProvider<T> {

    private ChoiceRendererProvider<T> rendererProvider;


    /**
     * @param renderer
     */
    public PaletteProvider(ChoiceRendererProvider<T> renderer) {
        this.rendererProvider = renderer;
    }


    @Override
    @SuppressWarnings("unchecked")
    public FormComponent<T> newEditor(final IEditorFacade parent, String componentId, IModel<T> model, ICrudrListProvider<T> listProvider) {
        PalettePanel<T> palettePanel = (PalettePanel<T>)parent;
        ((Collection<T>) palettePanel.getChoices()).addAll(listProvider.getList());
        Component parentComponent;
        Palette<T> palette = new Palette<T>(componentId, palettePanel);
        if (rendererProvider != null) {
            if (parent != null && parent instanceof Component) {
                parentComponent = (Component) parent;
            } else {
                parentComponent = palette;
            }
            palettePanel.setChoiceRenderer(rendererProvider.getRenderer(parentComponent));
        }
        palettePanel.setDefaultModel(model);
        return palette;
    }

}
