package net.unbewaff.wicketcrudr.providers.editor;


import java.util.List;

import net.unbewaff.wicketcrudr.components.ICrudrDataProvider;
import net.unbewaff.wicketcrudr.components.IEditorFacade;

import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;

/**
 * A simple implementation creating a Palette.
 *
 * @author David Hendrix (Nicktarix)
 *
 * @param <T>
 */
public class PaletteProvider<V extends List<? extends T>, T> implements IEditorProvider<V> {

    private ICrudrDataProvider<T> dataProvider;


    @Override
    public FormComponent<V> newEditor(final IEditorFacade parent, String componentId, IModel<V> model) {
        Palette palette = (Palette)parent;
        palette.getChoices().addAll(dataProvider.getList());
        
        palette.setDefaultModel(model);
        return palette.getRecorderComponent();
    }

}
