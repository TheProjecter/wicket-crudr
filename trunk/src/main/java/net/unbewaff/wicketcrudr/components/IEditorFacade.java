package net.unbewaff.wicketcrudr.components;

import net.unbewaff.wicketcrudr.providers.editor.IEditorProvider;

import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.util.convert.IConverter;

/**
 * A facade decoupling {@link IEditorProvider}s from {@link AjaxEditableLabelContainer}s
 *
 * @author David Hendrix (Nicktarix)
 *
 */
public interface IEditorFacade {

    /**
     * @see WebComponent
     * @param <C>
     * @param type
     * @return
     */
    public abstract <C> IConverter<C> getConverter(final Class<C> type);

    /**
     * Dummy override to fix WICKET-1239
     */
   public abstract void onModelChanged();

    /**
     * Dummy override to fix WICKET-1239
     */
    public abstract void onModelChanging();

}