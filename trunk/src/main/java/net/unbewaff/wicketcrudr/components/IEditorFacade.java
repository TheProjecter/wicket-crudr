package net.unbewaff.wicketcrudr.components;

import org.apache.wicket.util.convert.IConverter;

public interface IEditorFacade {

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.components.ILabelFacade#getConverter(java.lang.Class)
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